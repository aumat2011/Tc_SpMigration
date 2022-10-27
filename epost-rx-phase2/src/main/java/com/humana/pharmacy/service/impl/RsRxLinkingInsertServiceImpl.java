package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.common.Helper;
import com.humana.pharmacy.common.cache.model.CCodeValue;
import com.humana.pharmacy.common.dto.RsMultiLinkDTO;
import com.humana.pharmacy.common.entity.QEScriptMsgAttributes;
import com.humana.pharmacy.common.entity.QOrderHeader;
import com.humana.pharmacy.common.entity.QOrderLines;
import com.humana.pharmacy.common.entity.QProducts;
import com.humana.pharmacy.common.entity.QRxFillAux;
import com.humana.pharmacy.common.entity.QRxLinkDccoGPIList;
import com.humana.pharmacy.common.entity.QRxLinkLogging;
import com.humana.pharmacy.common.entity.QRxMultiLink;
import com.humana.pharmacy.common.entity.QSigGroups;
import com.humana.pharmacy.common.entity.QSystemUsers;
import com.humana.pharmacy.common.model.RxMultiLink;
import com.humana.pharmacy.common.service.FunctionsService;
import com.humana.pharmacy.common.service.impl.BaseServiceImpl;
import com.humana.pharmacy.dto.RsRxLinkingInsertDTO;
import com.humana.pharmacy.service.RsRxLinkingInsertService;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.dml.SQLUpdateClause;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Implementation of RsRxLinkingInsertService using QueryDSL. It extends BaseServiceImpl.
 */
public class RsRxLinkingInsertServiceImpl extends BaseServiceImpl
        implements RsRxLinkingInsertService {
    /**
     * The links not deactivating edi transaction code
     */
    private static final List<String> LINKS_NOT_DEACTIVATING = Arrays.asList("REFILLRX", "OTC");

    /**
     * The rx link fk code cat id
     */
    private static final String RX_LINK_FK_CODE = "RXLINK";
    /**
     * The rx link limit code
     */
    private static final String RX_LINKING_LIMIT = "RX_LINKING_LIMIT";
    /**
     * The rx link switch code
     */
    private static final String RX_LINK_SWITCH = "RX_LINK_SWITCH";
    /**
     * The the ans user login name
     */
    private static final String ANS_USR = "ANSUSR";
    /**
     * The active flag
     */
    private static final String ACTIVE_FLAG = "Y";
    /**
     * The inactive flag
     */
    private static final String INACTIVE_FLAG = "N";
    /**
     * The action name of maximum link count reached
     */
    private static final String MAXIMUM_LINK_COUNT_REACHED = "MAXIMUM LINK COUNT REACHED";
    /**
     * The action name of insert new rx linking
     */
    private static final String INSERT_NEW_RX_LINKING_RECORD = "INSERT NEW RX LINKING RECORD";
    /**
     * The insert mode
     */
    private static final String INSERT_MODE = "I";
    /**
     * The delete mode
     */
    private static final String DELETE_MODE = "D";
    /**
     * The parent flag under delete mode
     */
    private static final String DELETE_PARENT = "P";
    /**
     * The deactivate action name
     */
    private static final String DEACTIVATE_ACTION = "DEACTIVATE THE RX LINKING. CHILD RX ";
    /**
     * The rx link sequence
     */
    private static final String RX_LINK_SEQ = "[DBO].[S_rx_link_seq]";
    /**
     * The rx link logging sequence
     */
    private static final String RX_LINK_LOGGING_SEQ = "[DBO].[s_rx_link_logging_seq]";
    /**
     * The active flag of code status
     */
    private static final String STATUS_ACTIVE_FLAG = "A";

    /**
     * Reference to FunctionsService.
     */
    private FunctionsService functionsService;

    /**
     * Constructor of RsRxLinkingInsertServiceImpl.
     *
     * @param epostrxDataSource  DataSource for epostrx database. Required
     * @param workflowDataSource DataSource for epostrx_workflow database. Required
     * @throws RuntimeException if epostrxDataSource or workflowDataSource is null
     */
    public RsRxLinkingInsertServiceImpl(DataSource epostrxDataSource, DataSource workflowDataSource) {
        super(epostrxDataSource, workflowDataSource);
    }

    /**
     * Set implementation object of FunctionsService.
     *
     * @param functionsService Implementation object of FunctionsService. Required
     * @throws RuntimeException if the functionsService is null
     */
    public void setFunctionsService(FunctionsService functionsService) {
        Helper.checkNull(functionsService, "functionsService");

        this.functionsService = functionsService;
    }

    /**
     * Insert RS RX linking. Code from file: P_rs_rxlinking_insert.sql
     *
     * @param eScriptMsgAttributeSeq The EScript msg attribute seq. Required
     * @param userNum                The user number. Required
     * @param mode                   The mode ('I': INSERT; 'D': DEACTIVATE). Required
     * @param deactivationComment    The deactivation comment. Optional
     * @param rFlag                  The R flag. Optional
     * @throws RuntimeException if eScriptMsgAttributeSeq or userNum is null, or mode is null/empty
     */
    public void insertRsRxLinking(
            BigInteger eScriptMsgAttributeSeq,
            Long userNum,
            String mode,
            String deactivationComment,
            String rFlag) {
        Helper.checkNull(eScriptMsgAttributeSeq, "eScriptMsgAttributeSeq");

        List<CCodeValue> codeValues = getCodeValueModels(RX_LINK_FK_CODE);

        // Line: 57 - 60
        int rxLinkLimit =
                codeValues.stream()
                        .filter(
                                c ->
                                        RX_LINKING_LIMIT.equals(c.getCodeValueKeyword())
                                                && c.getCodeValueDesc() != null)
                        .map(c -> Integer.valueOf(c.getCodeValueDesc()))
                        .findFirst()
                        .orElse(-1);

        // Line: 64 - 69
        // RX LINK IS NOT ENABLE RETURN IT AT THE START OF THE STORED PROCEDURE
        if (codeValues.stream()
                .noneMatch(
                        c ->
                                RX_LINK_SWITCH.equals(c.getCodeValueKeyword())
                                        && STATUS_ACTIVE_FLAG.equals(c.getStatusFlag()))) {
            return;
        }

        QSystemUsers qSystemUsers = QSystemUsers.systemUsers;
        Long sysUserNum =
                epostrxQueryFactory
                        .select(qSystemUsers.sysUserNum)
                        .from(qSystemUsers)
                        .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                        .where(qSystemUsers.sysUserLogin.eq(ANS_USR))
                        .fetchFirst();

        List<RsRxLinkingInsertDTO> escripts = getRsRxLinkingInsertDTOs(eScriptMsgAttributeSeq);
        String rxNumberInput = null;
        if (!escripts.isEmpty()) {
            rxNumberInput = escripts.get(0).getRxNumber();
        }

        if (INSERT_MODE.equals(mode)) {
            if (escripts.isEmpty()) {
                return;
            }
            RsRxLinkingInsertDTO currEScript = escripts.get(0);
            // Line: 81 - 85
            if (LINKS_NOT_DEACTIVATING.contains(currEScript.getEdiTransactionCode())
                    || (currEScript.getRxExpirationDate() != null
                    && !currEScript
                    .getRxExpirationDate()
                    .toLocalDateTime()
                    .isAfter(LocalDateTime.now()))) {
                return;
            }

            // Line: 88 - 103
            if (currEScript.getHasMultiLink() != null) {
                return;
            }

            // Line: 123 - 144
            RsRxLinkingInsertDTO dto =
                    getRsRxLinkingInsertDTO(
                            currEScript.getPatientNum(),
                            currEScript.getRxNumber(),
                            currEScript.getProdGenericRefNum(),
                            currEScript.getDispensedDrugSig(),
                            currEScript.getSigGroup(),
                            currEScript.getDispensedDrugDaw());
            // Line: 236
            if (dto == null) {
                return;
            }

            String rxNumberParent = dto.getRxNumber();
            BigInteger eScriptMsgAttributeSeqParent = dto.geteScriptMsgAttributeSeq();

            // Line: 146
            if (rxNumberParent != null) {

                // Line: 152 - 154
                List<RsMultiLinkDTO> rsMultiLinkDTOs =
                        functionsService.getRsRxLinkingParentChild(rxNumberParent, ACTIVE_FLAG);
                Long minSeq =
                        rsMultiLinkDTOs.stream()
                                .map(RsMultiLinkDTO::getRxLinkSeq)
                                .min(Long::compare)
                                .orElse(0L);
                int rxCount = rsMultiLinkDTOs.size();

                // Line: 156 - 177
                if (rxLinkLimit == rxCount) {
                    // Line: 164 - 174
                    RxMultiLink rxMultiLink = getRxMultiLink(minSeq, null, null, null, null);
                    QRxMultiLink qRxMultiLink = QRxMultiLink.rxMultiLink;
                    epostrxQueryFactory
                            .delete(qRxMultiLink)
                            .where(qRxMultiLink.rxLinkSeq.eq(minSeq))
                            .execute();
                    // Insert rx link logging
                    saveRxLinkLogging(
                            rxMultiLink.getParentRxNumber(),
                            rxMultiLink.getParentEScriptMsgAttributeSeq(),
                            rxMultiLink.getChildRxNumber(),
                            rxMultiLink.getChildEScriptMsgAttributeSeq(),
                            MAXIMUM_LINK_COUNT_REACHED,
                            Expressions.currentTimestamp(),
                            userNum);
                }

                // Line: 181
                if (getRxMultiLink(null, rxNumberParent, null, rxNumberInput, null) == null) {
                    // Line: 186 - 228

                    saveRxMultiLink(
                            rxNumberParent,
                            eScriptMsgAttributeSeqParent,
                            rxNumberInput,
                            eScriptMsgAttributeSeq,
                            sysUserNum);

                    saveRxLinkLogging(
                            rxNumberParent,
                            eScriptMsgAttributeSeqParent,
                            rxNumberInput,
                            eScriptMsgAttributeSeq,
                            INSERT_NEW_RX_LINKING_RECORD,
                            null,
                            sysUserNum);
                }
            }
        } else if (DELETE_MODE.equals(mode)) {
            BigInteger eScriptMsgAttributeSeqParent = null;
            if (DELETE_PARENT.equals(rFlag)) {
                eScriptMsgAttributeSeqParent = eScriptMsgAttributeSeq;
                RxMultiLink rxMultiLink =
                        getRxMultiLink(null, null, eScriptMsgAttributeSeqParent, null, null);
                if (rxMultiLink != null) {
                    eScriptMsgAttributeSeq = rxMultiLink.getChildEScriptMsgAttributeSeq();
                    updateRxMultiLink(
                            rxMultiLink.getRxLinkSeq(),
                            eScriptMsgAttributeSeqParent,
                            userNum,
                            deactivationComment,
                            false);
                }
            } else {
                RxMultiLink rxMultiLink = getRxMultiLink(null, null, null, null, eScriptMsgAttributeSeq);
                if (rxMultiLink != null) {
                    eScriptMsgAttributeSeqParent = rxMultiLink.getParentEScriptMsgAttributeSeq();
                    updateRxMultiLink(
                            rxMultiLink.getRxLinkSeq(),
                            eScriptMsgAttributeSeq,
                            userNum,
                            deactivationComment,
                            true);
                }
            }
            saveRxLinkLogging(
                    null,
                    eScriptMsgAttributeSeqParent,
                    rxNumberInput,
                    eScriptMsgAttributeSeq,
                    DEACTIVATE_ACTION + (rxNumberInput == null ? "" : rxNumberInput),
                    null,
                    sysUserNum);
        }
    }

    /**
     * Get rs rx linking insert data dto.
     *
     * @param patientNum             The patient num
     * @param rxNumber               The rx number
     * @param dispensedGpiChild      The dispensed gpi child
     * @param dispensedDrugSigChild  The dispensed drug sig child
     * @param dispensedSigGroupChild The dispensed sig group child
     * @param dispensedDrugDawChild  The dispensed drug daw child
     * @return The rs rx linking insert dto
     */
    private RsRxLinkingInsertDTO getRsRxLinkingInsertDTO(
            BigInteger patientNum,
            String rxNumber,
            String dispensedGpiChild,
            String dispensedDrugSigChild,
            Integer dispensedSigGroupChild,
            String dispensedDrugDawChild) {
        if (patientNum == null
                || rxNumber == null
                || dispensedGpiChild == null
                || dispensedDrugSigChild == null
                || dispensedSigGroupChild == null
                || dispensedDrugDawChild == null) {
            return null;
        }
        Timestamp lastYear = Timestamp.valueOf(LocalDateTime.now().minusYears(1));

        QEScriptMsgAttributes qeScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        QProducts qProducts = QProducts.products;
        QSigGroups qSigGroups = QSigGroups.sigGroups;

        QRxFillAux qRxFillAux = QRxFillAux.rxFillAux;
        QOrderLines qOrderLines = QOrderLines.orderLines;
        QOrderHeader qOrderHeader = QOrderHeader.orderHeader;

        QRxLinkDccoGPIList qRxLinkDccoGPIList = QRxLinkDccoGPIList.rxLinkDccoGPIList;

        List<RsRxLinkingInsertDTO> list =
                epostrxQueryFactory
                        .select(
                                Projections.bean(
                                        RsRxLinkingInsertDTO.class,
                                        qeScriptMsgAttributes.eScriptMsgAttributeSeq,
                                        qeScriptMsgAttributes.rxNumber,
                                        qRxLinkDccoGPIList.gpi.as("rxLinkDccoGpi")))
                        .from(qeScriptMsgAttributes)
                        .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                        .join(qOrderLines)
                        .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                        .on(qOrderLines.eScriptMsgAttributeSeq.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq))
                        .join(qOrderHeader)
                        .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                        .on(qOrderHeader.orderNum.eq(qOrderLines.orderNum))
                        .join(qRxFillAux)
                        .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                        .on(qRxFillAux.eScriptMsgAttributeSeq.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq))
                        .join(qProducts)
                        .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                        .on(qProducts.prodId.eq(qeScriptMsgAttributes.dispensedProductId))
                        .leftJoin(qSigGroups)
                        .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                        .on(
                                removeSpaces(qSigGroups.sig)
                                        .eq(removeSpaces(qeScriptMsgAttributes.dispensedDrugSig)))
                        .leftJoin(qRxLinkDccoGPIList)
                        .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                        .on(qProducts.prodGenericRefNum.eq(qRxLinkDccoGPIList.gpi))
                        .where(
                                qeScriptMsgAttributes
                                        .patientNum
                                        .eq(patientNum)
                                        .and(qOrderHeader.orderDate.goe(lastYear))
                                        .and(
                                                removeSpaces(qeScriptMsgAttributes.dispensedDrugSig)
                                                        .eq(dispensedDrugSigChild.replaceAll(" ", ""))
                                                        .or(qSigGroups.sigGroup.eq(dispensedSigGroupChild)))
                                        .and(qProducts.prodGenericRefNum.eq(dispensedGpiChild))
                                        .and(
                                                qeScriptMsgAttributes
                                                        .rxNumber
                                                        .castToNum(Integer.class)
                                                        .lt(Integer.valueOf(rxNumber)))
                                        .and(
                                                qRxFillAux
                                                        .fillStatusNum
                                                        .eq((byte) 9)
                                                        .and(qeScriptMsgAttributes.dispensedDrugDaw.eq(dispensedDrugDawChild))))
                        .orderBy(
                                qeScriptMsgAttributes.rxNumber.desc(),
                                qeScriptMsgAttributes.eScriptMsgAttributeSeq.desc())
                        .fetch();

        return list.stream().filter(e -> e.getRxLinkDccoGPIListGpi() == null).findFirst().orElse(null);
    }

    /**
     * Collect rs rx linking insert data by script id.
     *
     * @param scriptId The escript ID
     * @return The list of rs rx linking insert dto
     */
    private List<RsRxLinkingInsertDTO> getRsRxLinkingInsertDTOs(BigInteger scriptId) {
        QEScriptMsgAttributes qeScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        QRxMultiLink qRxMultiLink = QRxMultiLink.rxMultiLink;
        QProducts qProducts = QProducts.products;
        QSigGroups qSigGroups = QSigGroups.sigGroups;

        QRxFillAux qRxFillAux = QRxFillAux.rxFillAux;
        QOrderLines qOrderLines = QOrderLines.orderLines;
        QOrderHeader qOrderHeader = QOrderHeader.orderHeader;

        return epostrxQueryFactory
                .select(
                        Projections.bean(
                                RsRxLinkingInsertDTO.class,
                                qeScriptMsgAttributes.eScriptMsgAttributeSeq,
                                qeScriptMsgAttributes.ediTransactionCode,
                                qeScriptMsgAttributes.rxExpirationDate,
                                qeScriptMsgAttributes.rxNumber,
                                qeScriptMsgAttributes.patientNum,
                                qeScriptMsgAttributes.dispensedDrugSig,
                                qeScriptMsgAttributes.dispensedDrugDaw,
                                SQLExpressions.selectOne()
                                        .distinct()
                                        .from(qRxMultiLink)
                                        .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                                        .where(
                                                qRxMultiLink
                                                        .active
                                                        .eq(ACTIVE_FLAG)
                                                        .and(
                                                                qRxMultiLink
                                                                        .childRxNumber
                                                                        .eq(qeScriptMsgAttributes.rxNumber)
                                                                        .or(
                                                                                qRxMultiLink.parentRxNumber.eq(
                                                                                        qeScriptMsgAttributes.rxNumber))))
                                        .as("hasMultiLink"),
                                qProducts.prodGenericRefNum,
                                qSigGroups.sigGroup.coalesce(0).as("sigGroup")))
                .from(qeScriptMsgAttributes)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .leftJoin(qProducts)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qProducts.prodId.eq(qeScriptMsgAttributes.dispensedProductId))
                .leftJoin(qSigGroups)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(removeSpaces(qSigGroups.sig).eq(removeSpaces(qeScriptMsgAttributes.dispensedDrugSig)))
                .leftJoin(qOrderLines)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderLines.eScriptMsgAttributeSeq.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq))
                .leftJoin(qOrderHeader)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderHeader.orderNum.eq(qOrderLines.orderNum))
                .leftJoin(qRxFillAux)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillAux.eScriptMsgAttributeSeq.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq))
                .where(qeScriptMsgAttributes.eScriptMsgAttributeSeq.eq(scriptId))
                .fetch();
    }

    /**
     * Update the Rx multi link.
     *
     * @param rxLinkSeq         The RX link seq
     * @param escriptMsgAttrSeq The escript msg attribute seq
     * @param userNum           The user num
     * @param comment           The comment
     * @param childEScript      The child/parent escript flag
     */
    private void updateRxMultiLink(
            Long rxLinkSeq,
            BigInteger escriptMsgAttrSeq,
            Long userNum,
            String comment,
            boolean childEScript) {
        // Line: 258-265, 279-286
        QRxMultiLink qRxMultiLink = QRxMultiLink.rxMultiLink;

        SQLUpdateClause updateClause = epostrxQueryFactory.update(qRxMultiLink);
        updateClause =
                updateClause
                        .set(qRxMultiLink.active, INACTIVE_FLAG)
                        .set(qRxMultiLink.deactivatedSysUserNum, userNum)
                        .set(qRxMultiLink.deactivatedDatetime, (Expression) Expressions.currentTimestamp())
                        .set(qRxMultiLink.comment, comment);
        BooleanExpression expression = qRxMultiLink.rxLinkSeq.eq(rxLinkSeq);
        if (childEScript) {
            updateClause = updateClause.set(qRxMultiLink.childEScriptMsgAttributeSeq, escriptMsgAttrSeq);
            expression.and(qRxMultiLink.childEScriptMsgAttributeSeq.eq(escriptMsgAttrSeq));
        } else {
            updateClause = updateClause.set(qRxMultiLink.parentEScriptMsgAttributeSeq, escriptMsgAttrSeq);
            expression.and(qRxMultiLink.parentEScriptMsgAttributeSeq.eq(escriptMsgAttrSeq));
        }

        updateClause.where(expression).execute();
    }

    /**
     * Save the Rx multi link.
     *
     * @param parentRxNumber          The parent Rx number
     * @param parentEScriptMsgAttrSeq The parent escript msg attribute seq
     * @param childRxNumber           The child Rx number
     * @param childEScriptMsgAttrSeq  The child escript msg attribute seq
     * @param createdSysUserNum       The created sys user num
     */
    private void saveRxMultiLink(
            String parentRxNumber,
            BigInteger parentEScriptMsgAttrSeq,
            String childRxNumber,
            BigInteger childEScriptMsgAttrSeq,
            Long createdSysUserNum) {
        // Line: 188 - 206
        QRxMultiLink qRxMultiLink = QRxMultiLink.rxMultiLink;
        epostrxQueryFactory
                .insert(qRxMultiLink)
                .columns(
                        qRxMultiLink.rxLinkSeq,
                        qRxMultiLink.parentRxNumber,
                        qRxMultiLink.parentEScriptMsgAttributeSeq,
                        qRxMultiLink.childRxNumber,
                        qRxMultiLink.childEScriptMsgAttributeSeq,
                        qRxMultiLink.active,
                        qRxMultiLink.createdSysUserNum,
                        qRxMultiLink.createdDatetime)
                .values(
                        SQLExpressions.nextval(BigInteger.class, RX_LINK_SEQ),
                        parentRxNumber,
                        parentEScriptMsgAttrSeq,
                        childRxNumber,
                        childEScriptMsgAttrSeq,
                        "Y",
                        createdSysUserNum,
                        Timestamp.from(Instant.now()))
                .execute();
    }

    /**
     * Save the Rx link logging.
     *
     * @param parentRxNumber          The parent Rx number
     * @param parentEScriptMsgAttrSeq The parent escript msg attribute seq
     * @param childRxNumber           The child Rx number
     * @param childEScriptMsgAttrSeq  The child escript msg attribute seq
     * @param action                  The action
     * @param logDatetime             The log datetime
     * @param logUser                 The log user
     */
    private void saveRxLinkLogging(
            String parentRxNumber,
            BigInteger parentEScriptMsgAttrSeq,
            String childRxNumber,
            BigInteger childEScriptMsgAttrSeq,
            String action,
            DateTimeExpression<Date> logDatetime,
            Long logUser) {
        // Line: 165-173, 212-228, 293-310
        QRxLinkLogging qRxLinkLogging = QRxLinkLogging.rxLinkLogging;
        epostrxQueryFactory
                .insert(qRxLinkLogging)
                .columns(
                        qRxLinkLogging.rxLinkLogSeq,
                        qRxLinkLogging.parentRxNumber,
                        qRxLinkLogging.parentEScriptMsgAttrSeq,
                        qRxLinkLogging.childRxNumber,
                        qRxLinkLogging.childEScriptMsgAttrSeq,
                        qRxLinkLogging.action,
                        qRxLinkLogging.logDatetime,
                        qRxLinkLogging.logUser)
                .values(
                        SQLExpressions.nextval(BigInteger.class, RX_LINK_LOGGING_SEQ),
                        parentRxNumber,
                        parentEScriptMsgAttrSeq,
                        childRxNumber,
                        childEScriptMsgAttrSeq,
                        action,
                        logDatetime,
                        logUser)
                .execute();
    }

    /**
     * Get Rx multi link.
     *
     * @param rxLinkSeq               The rx link seq
     * @param parentRxNumber          The parent Rx number
     * @param parentEScriptMsgAttrSeq The parent escript msg attribute seq
     * @param childRxNumber           The child Rx number
     * @param childEScriptMsgAttrSeq  The child escript msg attribute seq
     * @return The Rx multi link or null if not found.
     */
    private RxMultiLink getRxMultiLink(
            Long rxLinkSeq,
            String parentRxNumber,
            BigInteger parentEScriptMsgAttrSeq,
            String childRxNumber,
            BigInteger childEScriptMsgAttrSeq) {
        QRxMultiLink qRxMultiLink = QRxMultiLink.rxMultiLink;

        BooleanExpression expression = qRxMultiLink.active.eq(ACTIVE_FLAG);
        if (rxLinkSeq != null) {
            expression = expression.and(qRxMultiLink.rxLinkSeq.eq(rxLinkSeq));
        }
        if (parentRxNumber != null) {
            expression = expression.and(qRxMultiLink.parentRxNumber.eq(parentRxNumber));
        }
        if (parentEScriptMsgAttrSeq != null) {
            expression =
                    expression.and(qRxMultiLink.parentEScriptMsgAttributeSeq.eq(parentEScriptMsgAttrSeq));
        }
        if (childRxNumber != null) {
            expression = expression.and(qRxMultiLink.childRxNumber.eq(childRxNumber));
        }
        if (childEScriptMsgAttrSeq != null) {
            expression =
                    expression.and(qRxMultiLink.childEScriptMsgAttributeSeq.eq(childEScriptMsgAttrSeq));
        }
        return epostrxQueryFactory
                .select(qRxMultiLink)
                .from(qRxMultiLink)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .where(expression)
                .fetchFirst();
    }

    /**
     * Remove spaces of the string path.
     *
     * @param path the path
     * @return the expression
     */
    private static StringExpression removeSpaces(StringPath path) {
        return Expressions.stringTemplate("replace({0},'','')", path);
    }
}
