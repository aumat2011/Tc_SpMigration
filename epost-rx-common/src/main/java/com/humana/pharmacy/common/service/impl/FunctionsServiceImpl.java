package com.humana.pharmacy.common.service.impl;

import com.humana.pharmacy.common.Helper;
import com.humana.pharmacy.common.cache.model.CRxDefaults;
import com.humana.pharmacy.common.dto.AnsRxNarcCodeEScriptDTO;
import com.humana.pharmacy.common.dto.RsMultiLinkDTO;
import com.humana.pharmacy.common.dto.RxDTO;
import com.humana.pharmacy.common.dto.RxEScriptDTO;
import com.humana.pharmacy.common.entity.QBenefitResponseCodes;
import com.humana.pharmacy.common.entity.QEScriptMsgAttributes;
import com.humana.pharmacy.common.entity.QEcsResponses;
import com.humana.pharmacy.common.entity.QPatientPlans;
import com.humana.pharmacy.common.entity.QPayorPlans;
import com.humana.pharmacy.common.entity.QPayors;
import com.humana.pharmacy.common.entity.QProducts;
import com.humana.pharmacy.common.entity.QRxFillAux;
import com.humana.pharmacy.common.entity.QRxMultiLink;
import com.humana.pharmacy.common.entity.QSystemUsers;
import com.humana.pharmacy.common.entity.QThirdPartyClaimRequests;
import com.humana.pharmacy.common.entity.QThirdPartyClaimResponses;
import com.humana.pharmacy.common.service.FunctionsService;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLQuery;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of FunctionsService using QueryDSL. It extends BaseServiceImpl.
 */
public class FunctionsServiceImpl extends BaseServiceImpl implements FunctionsService {
    /**
     * The string 'Y'.
     */
    private static final String FLAG_Y = "Y";

    /**
     * The string 'N'.
     */
    private static final String FLAG_N = "N";

    /**
     * The status '2'.
     */
    private static final byte FILL_STAT_2 = 2;

    /**
     * The status '9'.
     */
    private static final byte FILL_STAT_9 = 9;

    /**
     * The status 'P'.
     */
    private static final String ESC_STAT_TYPE_P = "P";

    /**
     * The status 'C'.
     */
    private static final String ESC_STAT_TYPE_C = "C";

    /**
     * The status 'D'.
     */
    private static final String ESC_STAT_TYPE_D = "D";

    /**
     * The join flag ' WITH (NOLOCK) '.
     */
    private static final String JOIN_FLAG = " WITH (NOLOCK) ";

    /**
     * The fill status numbers 2 and 9.
     */
    private static final List<Byte> FILL_STATUS_NUMS = Arrays.asList((byte) 2, (byte) 9);

    /**
     * The number 100.
     */
    private static final long NUM_HUNDRED = 100;

    /**
     * The number 10.
     */
    private static final int NUM_10 = 10;

    /**
     * The int value '1'.
     */
    private static final Integer PAID_VALUE = 1;

    /**
     * The int value '0'.
     */
    private static final Integer NOT_PAID_VALUE = 0;

    /**
     * The default date "01/01/1990".
     */
    private static final Timestamp DEFAULT_DATE;

    static {
        Timestamp defaultDate = null;
        try {
            defaultDate = new Timestamp(new SimpleDateFormat("MM/dd/yyyy").parse("01/01/1990").getTime());
        } catch (ParseException e) {
            // Ignore
        }
        DEFAULT_DATE = defaultDate;
    }

    /**
     * Constructor of OrderSanityCheckServiceImpl.
     *
     * @param epostrxDataSource  DataSource for epostrx database. Required
     * @param workflowDataSource DataSource for epostrx_workflow database. Required
     * @throws RuntimeException if epostrxDataSource or workflowDataSource is null
     */
    public FunctionsServiceImpl(DataSource epostrxDataSource, DataSource workflowDataSource) {
        super(epostrxDataSource, workflowDataSource);
    }

    /**
     * Get escripts which are precheck required.
     *
     * @param escriptIds The list of escript ids to test if they are precheck required. Required to be non-empty
     * @return List of escript ids which are precheck required. May be empty
     */
    public List<BigInteger> getPrecheckRequired(List<BigInteger> escriptIds) {
        Helper.checkList(escriptIds, "escriptIds");

        // Corresponding to original database function F_itemPrecheckRequired.
        QEScriptMsgAttributes qEscript = QEScriptMsgAttributes.eScriptMsgAttributes;
        QRxFillAux qRxFillAux = QRxFillAux.rxFillAux;
        QProducts qProduct = QProducts.products;

        CRxDefaults rxDefaults = getRxDefaults();

        BooleanExpression conditions = qEscript.eScriptMsgAttributeSeq.in(escriptIds);

        if (FLAG_Y.equalsIgnoreCase(rxDefaults.getPrecheckAll())) {
            // Refer to F_itemPrecheckRequired, line 52 ~ 60
            // check if the item has already been screened
            conditions = conditions.and(qRxFillAux.fillPrecheckDatetime.isNull());
        } else {
            // Refer to F_itemPrecheckRequired, line 63 ~ 72
            // check if the item has already been screened at some point in RX life
            QEScriptMsgAttributes qEscriptSub = new QEScriptMsgAttributes("eSub");
            QRxFillAux qRxFillAuxSub = new QRxFillAux("rxSub");

            conditions = conditions.and(SQLExpressions
                    .select(Expressions.stringTemplate("1"))
                    .from(qEscriptSub).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                    .join(qRxFillAuxSub).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                    .on(qRxFillAuxSub.eScriptMsgAttributeSeq.eq(qEscriptSub.eScriptMsgAttributeSeq))
                    .where(qEscriptSub.rxNumber.eq(qEscript.rxNumber).and(qRxFillAuxSub.fillPrecheckDatetime.isNotNull()))
                    .notExists());
        }

        // Refer to F_itemPrecheckRequired, line 86 ~ 88
        // Remove DME items if DME check not needed
        if (!FLAG_Y.equalsIgnoreCase(rxDefaults.getPwoPrecheck())) {
            conditions = conditions.and(qEscript.pwoItem.coalesce(FLAG_N).ne(FLAG_Y));
        }

        // Refer to F_itemPrecheckRequired, line 86 ~ 88
        // Remove OTC items if OTC check not needed
        if (!FLAG_Y.equalsIgnoreCase(rxDefaults.getOtcAsRxPrecheck())) {
            conditions = conditions.and(qProduct.prodRxRequired.coalesce(FLAG_Y).ne(FLAG_N));
        }

        return epostrxQueryFactory
                .select(qEscript.eScriptMsgAttributeSeq)
                .from(qEscript).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .leftJoin(qRxFillAux).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillAux.eScriptMsgAttributeSeq.eq(qEscript.eScriptMsgAttributeSeq))
                .leftJoin(qProduct).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qProduct.prodId.eq(qEscript.dispensedProductId))
                .where(conditions)
                .fetch();
    }

    /**
     * Get escripts which are first time to fill.
     *
     * @param escriptIds The list of escript ids to test if they are first time to fill. Required to be non-empty
     * @return List of escript ids which are first time to fill. May be empty
     */
    public List<BigInteger> getFirstTimeFill(List<BigInteger> escriptIds) {
        Helper.checkList(escriptIds, "escriptIds");

        // Corresponding to original database function F_IsRxFirstTimeFill.
        QEScriptMsgAttributes qEscript = QEScriptMsgAttributes.eScriptMsgAttributes;
        QRxFillAux qRxFillAux = QRxFillAux.rxFillAux;
        QEScriptMsgAttributes qEscriptSub = new QEScriptMsgAttributes("e1");
        QRxFillAux qRxFillAuxSub = new QRxFillAux("r1");

        // Refer to F_IsRxFirstTimeFill, check if the item has not been dispense filled
        BooleanExpression firstTimeFill = SQLExpressions
                .select(Expressions.stringTemplate("1"))
                .from(qEscriptSub).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .join(qRxFillAuxSub).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillAuxSub.eScriptMsgAttributeSeq.eq(qEscriptSub.eScriptMsgAttributeSeq))
                .where(qEscriptSub.rxNumber.eq(qEscript.rxNumber).and(qRxFillAuxSub.fillStatusNum.in(FILL_STAT_2, FILL_STAT_9)))
                .notExists();

        return epostrxQueryFactory
                .select(qEscript.eScriptMsgAttributeSeq)
                .from(qEscript).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .join(qRxFillAux).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillAux.eScriptMsgAttributeSeq.eq(qEscript.eScriptMsgAttributeSeq))
                .where(qEscript.eScriptMsgAttributeSeq.in(escriptIds).and(firstTimeFill))
                .fetch();
    }

    /**
     * Get escripts which exceed max PV days.
     *
     * @param escriptIds The list of escript ids to test if they exceed max PV days. Required to be non-empty
     * @return List of escript ids to which exceed max PV days. May be empty
     */
    public List<BigInteger> getExceedMaxPVDays(List<BigInteger> escriptIds) {
        Helper.checkList(escriptIds, "escriptIds");

        // Corresponding to original database function F_IsRxExceedMaxPVDays.
        QEScriptMsgAttributes qEscript = QEScriptMsgAttributes.eScriptMsgAttributes;
        QRxFillAux qRxFillAux = QRxFillAux.rxFillAux;
        QEScriptMsgAttributes qEscriptSub = new QEScriptMsgAttributes("eSub");
        QRxFillAux qRxFillAuxSub = new QRxFillAux("rxSub");

        // Refer to F_IsRxFirstTimeFill, check if the item has not been dispense filled
        BooleanExpression firstTimeFill = SQLExpressions
                .select(Expressions.stringTemplate("1"))
                .from(qEscriptSub).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .join(qRxFillAuxSub).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillAuxSub.eScriptMsgAttributeSeq.eq(qEscriptSub.eScriptMsgAttributeSeq))
                .where(qEscriptSub.rxNumber.eq(qEscript.rxNumber).and(qRxFillAuxSub.fillStatusNum.in(FILL_STAT_2, FILL_STAT_9)))
                .notExists();

        CRxDefaults rxDefaults = getRxDefaults();

        Short maxPVDays = rxDefaults.getMaxPvDays();
        if (maxPVDays == null) {
            maxPVDays = 0;
        }
        // Refer to F_IsRxExceedMaxPVDays, check if exceed max PV days
        BooleanExpression exceed = SQLExpressions.addDays(qEscript.writtenDate, maxPVDays)
                .before(DateTimeExpression.currentTimestamp(Timestamp.class));

        return epostrxQueryFactory
                .select(qEscript.eScriptMsgAttributeSeq)
                .from(qEscript).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .join(qRxFillAux).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillAux.eScriptMsgAttributeSeq.eq(qEscript.eScriptMsgAttributeSeq))
                .where(qEscript.eScriptMsgAttributeSeq.in(escriptIds).and(firstTimeFill).and(exceed))
                .fetch();
    }

    /**
     * Get escripts paid.
     *
     * @param escriptIds The escript ids to check whether they are paid. Required to be non-empty
     * @return List of escript ids which are paid
     */
    public List<BigInteger> getPrimaryEcsPaid(List<BigInteger> escriptIds) {
        Helper.checkList(escriptIds, "escriptIds");

        // Corresponding to original database function F_isPrimaryEcsPaid.
        QRxFillAux qRxFillAux = QRxFillAux.rxFillAux;
        QEcsResponses qEcsResponse = QEcsResponses.ecsResponses;
        QBenefitResponseCodes qBenifitCode = QBenefitResponseCodes.benefitResponseCodes;
        QThirdPartyClaimRequests qClaimRequest = QThirdPartyClaimRequests.thirdPartyClaimRequests;
        QThirdPartyClaimResponses qClaimRepsonse = QThirdPartyClaimResponses.thirdPartyClaimResponses;

        SubQueryExpression<BigInteger> claimEcs = SQLExpressions
                .select(qClaimRequest.eScriptMsgAttributeSeq.castToNum(BigInteger.class))
                .from(qClaimRequest).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .distinct()
                .join(qClaimRepsonse).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qClaimRequest.transactionId.eq(qClaimRepsonse.transactionId))
                .join(qBenifitCode).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qClaimRepsonse.responseCode.eq(qBenifitCode.id))
                .where(qClaimRequest.transactionCode.eq("LS")
                        .and(qBenifitCode.approvalCode.eq("AA"))
                        .and(qClaimRequest.eScriptMsgAttributeSeq.castToNum(BigInteger.class).in(escriptIds)));

        SubQueryExpression<BigInteger> fillEcs = SQLExpressions
                .select(qRxFillAux.eScriptMsgAttributeSeq)
                .from(qRxFillAux).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .distinct()
                .join(qEcsResponse).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillAux.eScriptMsgAttributeSeq.eq(qEcsResponse.eScriptMsgAttributeSeq)
                        .and(qRxFillAux.fillEcsStatus.eq(qEcsResponse.ecsRespSeqNum)))
                .where(qEcsResponse.ecsResponseStatusType.in(ESC_STAT_TYPE_P, ESC_STAT_TYPE_C, ESC_STAT_TYPE_D)
                        .and(qRxFillAux.eScriptMsgAttributeSeq.in(escriptIds)));

        return epostrxQueryFactory.query().union(Arrays.asList(claimEcs, fillEcs)).fetch();
    }

    /**
     * Get total number of remaining refills for the specified Rx number.
     *
     * @param rxNumber      Rx number. Required.
     * @param rxEScriptDTOs List of RxEScriptDTO. Optional.
     * @return Total number of remaining refills.
     * @throws RuntimeException if the rxNumber is null/empty
     */
    public Integer getRemainingRefills(String rxNumber, List<? extends RxEScriptDTO> rxEScriptDTOs) {
        Helper.checkString(rxNumber, "rxNumber");

        // Code from file : F_getRemainingRefills.sql

        if (Helper.isNullOrEmpty(rxEScriptDTOs)) {
            rxEScriptDTOs = getRxEScripts(rxNumber);
        }

        // Line # : 39 - 41
        BigInteger idSeq = rxEScriptDTOs.stream()
                .filter(rxEScriptDTO -> rxNumber.equalsIgnoreCase(rxEScriptDTO.getRxNumber()))
                .max(Comparator.comparing(RxEScriptDTO::getEScriptMsgAttributeSeq))
                .map(RxEScriptDTO::getEScriptMsgAttributeSeq)
                .orElse(BigInteger.ZERO);

        // Line # : 43 - 48
        BigInteger finalIdSeq = idSeq;
        RxDTO rxDTO = rxEScriptDTOs.stream()
                .filter(rxEScriptDTO -> Objects.equals(rxEScriptDTO.getEScriptMsgAttributeSeq(), finalIdSeq))
                .map(rxEScriptDTO -> {
                    RxDTO dto = new RxDTO();
                    dto.setScriptId(rxEScriptDTO.getEScriptMsgAttributeSeq());
                    dto.setRefillsRemaining(rxEScriptDTO.getRxRefillsLeft());
                    dto.setWrittenQuantity(rxEScriptDTO.getWrittenQuantity());
                    dto.setDispensedQuantity(rxEScriptDTO.getDispensedQuantity());
                    return dto;
                })
                .findFirst()
                .orElse(new RxDTO());

        // Line # : 51 - 55
        // Last Fill Date
        final List<RxEScriptDTO> filteredByFillStatusNumRxEscripts = rxEScriptDTOs.stream()
                .filter(rxEScriptDTO -> FILL_STATUS_NUMS.contains(rxEScriptDTO.getFillStatusNum()))
                .collect(Collectors.toList());

        idSeq = rxEScriptDTOs.stream()
                .filter(rxEScriptDTO -> rxNumber.equalsIgnoreCase(rxEScriptDTO.getRxNumber()))
                .max(Comparator.comparing(RxEScriptDTO::getEScriptMsgAttributeSeq))
                .map(RxEScriptDTO::getEScriptMsgAttributeSeq)
                .orElse(BigInteger.ZERO);

        // Line # : 56
        if (idSeq.compareTo(BigInteger.ZERO) > 0) {
            // Line # : 60 - 66
            // If controlled, and fills exceeds written fills (even if quantity remains)
            // we return 0 fills remaining - CANNOT exceed written fills
            boolean isControlledFillsExceeded = filteredByFillStatusNumRxEscripts.stream()
                    .anyMatch(rxEScriptDTO ->
                            rxNumber.equalsIgnoreCase(rxEScriptDTO.getRxNumber())
                                    && (rxEScriptDTO.getProdDea() != null && rxEScriptDTO.getProdDea() > 0)
                                    && (rxEScriptDTO.getOriginalNumRefills() != null && getRxTotalFills(rxEScriptDTO.getEScriptMsgAttributeSeq(), filteredByFillStatusNumRxEscripts) > rxEScriptDTO.getOriginalNumRefills()));

            // Line # : 68 - 71
            if (isControlledFillsExceeded) {
                return 0;
            }

            // Line # : 76 - 78
            // Get last fill qty for use later
            BigInteger finalIdSeq1 = idSeq;
            rxDTO.setLastFillQuantity(rxEScriptDTOs.stream()
                    .filter(rxEScriptDTO -> Objects.equals(rxEScriptDTO.getEScriptMsgAttributeSeq(), finalIdSeq1))
                    .map(RxEScriptDTO::getFillQtyDispensed)
                    .findFirst().orElse(0L)
            );

        }

        // Line # : 83
        // Total Qty
        long totalQuantity = (rxDTO.getScriptId() == null) ? 0 : getRxTotalQty(rxDTO.getScriptId(), rxEScriptDTOs);

        // Line # : 90
        // Grab any ORTF records
        Long totalOrtfQuantityDispensed = getRxTotalORTFQtyFilled(rxNumber, filteredByFillStatusNumRxEscripts);

        // Line # : 87, 93 - 101
        // Get total Qty Filled.
        // Adjust for any ORTF records.
        // If total dispensed quantity is negative then make it positive.
        long totalQuantityDispensed = Math.abs(getRxTotalQtyFilled(rxNumber, filteredByFillStatusNumRxEscripts) + totalOrtfQuantityDispensed);

        Integer remainingRefills = rxDTO.getRefillsRemaining();
        // Line # : 104 - 126
        // Nothing dispensed, use total quantity
        if (totalQuantityDispensed == 0) {
            if (rxDTO.getDispensedQuantity() != null && rxDTO.getDispensedQuantity() > 0) {
                remainingRefills = Math.toIntExact(Math.floorDiv(totalQuantity, rxDTO.getDispensedQuantity()));
            } else if (rxDTO.getWrittenQuantity() != null && rxDTO.getWrittenQuantity() > 0) {
                remainingRefills = Math.toIntExact(Math.floorDiv(totalQuantity, rxDTO.getWrittenQuantity()));
            }
        } else if (rxDTO.getLastFillQuantity() != null && rxDTO.getLastFillQuantity() > 0) {
            // If dispensed qty passed in calculate refills remaining and qty remaining.
            // Otherwise, use data from record and just pass back
            remainingRefills = Math.toIntExact(Math.floorDiv(totalQuantity - totalQuantityDispensed, rxDTO.getLastFillQuantity()));
        }
        if (remainingRefills != null && remainingRefills < 0) {
            remainingRefills = 0;
        }

        // Line # : 128 - 134
        return remainingRefills;
    }

    /**
     * Get total quantity.
     *
     * @param scriptId      The script ID. Required.
     * @param rxEScriptDTOs List of RxEScriptDTO. Optional.
     * @return Total quantity.
     * @throws RuntimeException if the scriptId is null
     */
    public Long getRxTotalQty(BigInteger scriptId, List<? extends RxEScriptDTO> rxEScriptDTOs) {
        Helper.checkNull(scriptId, "scriptId");

        // Code from file: F_getRxTotalQty_inline.sql
        if (Helper.isNullOrEmpty(rxEScriptDTOs)) {
            rxEScriptDTOs = getRxEScripts(scriptId);
        }

        return rxEScriptDTOs.stream()
                .filter(rxEScriptDTO -> Objects.equals(rxEScriptDTO.getEScriptMsgAttributeSeq(), scriptId))
                .map(rxEScriptDTO -> rxEScriptDTO.getWrittenQuantity() == null
                        ? 0L : (((rxEScriptDTO.getNumRefills() == null ? 0 : rxEScriptDTO.getNumRefills()) + 1) * rxEScriptDTO.getWrittenQuantity()))
                .findFirst()
                .orElse(0L);
    }

    /**
     * Get total quantity filled.
     *
     * @param rxNumber      The Rx number. Required
     * @param rxEScriptDTOs List of RxEScriptDTO. Optional
     * @return Total quantity filled.
     * @throws RuntimeException if the rxNumber is null/empty
     */
    public Long getRxTotalQtyFilled(String rxNumber, List<? extends RxEScriptDTO> rxEScriptDTOs) {
        Helper.checkString(rxNumber, "rxNumber");

        // Code from file: F_getRxTotalQtyFilled_inline.sql
        if (Helper.isNullOrEmpty(rxEScriptDTOs)) {
            rxEScriptDTOs = getRxEScripts(rxNumber);
        }

        return rxEScriptDTOs.stream()
                .filter(rxEScriptDTO -> rxNumber.equalsIgnoreCase(rxEScriptDTO.getRxNumber())
                        && FILL_STATUS_NUMS.contains(rxEScriptDTO.getFillStatusNum())
                        && (rxEScriptDTO.getOrtfQty() == null || rxEScriptDTO.getOrtfQty() == 0L)
                        && (rxEScriptDTO.getFillQtyDispensed() != null))
                .map(RxEScriptDTO::getFillQtyDispensed)
                .reduce(0L, Long::sum);
    }

    /**
     * Get total ORTF quantity filled.
     *
     * @param rxNumber      The Rx number. Required.
     * @param rxEScriptDTOs List of RxEScriptDTO. Optional.
     * @return The total ORTF quantity filled.
     * @throws RuntimeException if the rxNumber is null/empty
     */
    public Long getRxTotalORTFQtyFilled(String rxNumber, List<? extends RxEScriptDTO> rxEScriptDTOs) {
        Helper.checkString(rxNumber, "rxNumber");

        // Code from file: F_getRxTotalORTFQtyFilled_Inline.sql
        if (Helper.isNullOrEmpty(rxEScriptDTOs)) {
            rxEScriptDTOs = getRxEScripts(rxNumber);
        }

        return rxEScriptDTOs.stream()
                .filter(rxEScriptDTO -> rxNumber.equalsIgnoreCase(rxEScriptDTO.getRxNumber())
                        && FILL_STATUS_NUMS.contains(rxEScriptDTO.getFillStatusNum())
                        && (rxEScriptDTO.getOrtfQty() != null))
                .map(RxEScriptDTO::getOrtfQty)
                .reduce(0L, Long::sum) / NUM_HUNDRED;
    }

    /**
     * Get total fills.
     *
     * @param scriptId      The script ID. Required.
     * @param rxEScriptDTOs List of RxEScriptDTO. Optional
     * @return Total fills
     * @throws RuntimeException if the scriptId is null
     */
    public Long getRxTotalFills(BigInteger scriptId, List<? extends RxEScriptDTO> rxEScriptDTOs) {
        Helper.checkNull(scriptId, "scriptId");

        // Code from file: F_getRxTotalFills_inline.sql
        if (Helper.isNullOrEmpty(rxEScriptDTOs)) {
            rxEScriptDTOs = getRxEScripts(scriptId);
        }
        String rxNumber = rxEScriptDTOs.isEmpty() ? "" : rxEScriptDTOs.get(0).getRxNumber();
        long cnt = rxEScriptDTOs.stream()
                .filter(rxEScriptDTO -> rxNumber.equals(rxEScriptDTO.getRxNumber())
                        && FILL_STATUS_NUMS.contains(rxEScriptDTO.getFillStatusNum()))
                .count();

        return cnt;
    }

    /**
     * Gets claim paid non cash non self adjudicating.
     * <p>Code from file: fnIsClaimPaid_NonCash_NonSelfAdjudicating.sql
     *
     * @param scriptId The scriptId. Required.
     * @return The paid/not paid flag.
     * @throws RuntimeException if the scriptId is null
     */
    public Integer getClaimPaidNonCashNonSelfAdjudicating(BigInteger scriptId) {
        Helper.checkNull(scriptId, "scriptId");

        QEcsResponses qEcsResponses = QEcsResponses.ecsResponses;
        QEcsResponses qEcsResponses1 = new QEcsResponses("ecsResponses1");
        QEScriptMsgAttributes qEScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        QRxFillAux qRxFillAux = QRxFillAux.rxFillAux;
        QPayorPlans qPayorPlans = QPayorPlans.payorPlans;
        QPayors qPayors = QPayors.payors;

        Long count = epostrxQueryFactory.select(qEcsResponses.eScriptMsgAttributeSeq)
                .from(qEcsResponses).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .join(qEScriptMsgAttributes).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qEScriptMsgAttributes.eScriptMsgAttributeSeq.eq(qEcsResponses.eScriptMsgAttributeSeq))
                .join(qRxFillAux).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillAux.eScriptMsgAttributeSeq.eq(qEScriptMsgAttributes.eScriptMsgAttributeSeq))
                .join(qPayorPlans).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPayorPlans.ppNum.eq(qRxFillAux.ppNum).and(qPayorPlans.payorNum.notIn(1, 2, 3, 4)))
                .join(qPayors).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPayors.payorNum.eq(qPayorPlans.payorNum).and(qPayors.payorBillTypeNum.notIn(3, 4)))
                .where(qEcsResponses.eScriptMsgAttributeSeq.eq(scriptId).and(qEcsResponses.ecsResponseStatusType.in("P", "D"))
                        .and(qEcsResponses.ecsRespDate.gt(SQLExpressions.select(qEcsResponses1.ecsRespDate.max().coalesce(DEFAULT_DATE))
                                .from(qEcsResponses1).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                                .where(qEcsResponses1.eScriptMsgAttributeSeq.eq(qEcsResponses.eScriptMsgAttributeSeq)
                                        .and(qEcsResponses1.ecsResponseStatusType.in("A", "S")))
                        )))
                .fetchCount();

        return count > 0 ? PAID_VALUE : NOT_PAID_VALUE;
    }

    /**
     * Get product GRI class.
     * <p>Code from file: F_getGPIClassByScriptId.sql
     *
     * @param escripts the escripts
     * @return the GPI class
     */
    public String getGPIClass(List<AnsRxNarcCodeEScriptDTO> escripts) {
        if (Helper.isNullOrEmpty(escripts)) {
            return "";
        }

        return escripts.stream()
                .filter(es -> es.getProdGenericRefNum() != null)
                .map(es -> Helper.substring(es.getProdGenericRefNum(), NUM_10))
                .findFirst()
                .orElse("");
    }

    /**
     * Get the order that has the narcotic code.
     * <p>Code from file: F_getOrderNarcCodeStateNum.sql
     *
     * @param escripts the escripts
     * @return the narcotic code order
     */
    public AnsRxNarcCodeEScriptDTO getOrderNarcCode(List<AnsRxNarcCodeEScriptDTO> escripts) {
        if (Helper.isNullOrEmpty(escripts)) {
            return null;
        }

        // Line: 32 - 85
        return escripts.stream()
                .filter(es -> es.getOrderNum() != null
                        && es.getNarcoticCode() != null
                        && es.getStateNum() != null)
                .findFirst()
                .orElse(null);
    }

    /**
     * Get product dea.
     * <p>Code from file: F_getDeaProductByScriptId.sql
     *
     * @param orderNarcCode the order narcotic code
     * @param escripts      the escripts
     * @return the product dea
     */
    public Byte getDeaProduct(AnsRxNarcCodeEScriptDTO orderNarcCode, List<AnsRxNarcCodeEScriptDTO> escripts) {
        if (orderNarcCode == null || Helper.isNullOrEmpty(escripts)) {
            return 0;
        }

        AnsRxNarcCodeEScriptDTO escript = escripts.stream()
                .filter(es -> es.getOrderInvoiceNumber() != null
                        && es.getStateCsProductSeq() != null
                        && Objects.equals(es.getStateNum(), orderNarcCode.getStateNum()))
                .min(Comparator.comparing(AnsRxNarcCodeEScriptDTO::getStateCsProductSeq))
                .orElse(new AnsRxNarcCodeEScriptDTO());

        return escript.getScpProdDea() != null ? escript.getScpProdDea().byteValue() : (byte) 0;
    }

    /**
     * Get numberics from string.
     * <p>Code from file: F_pullNumericsFromString.sql
     *
     * @param numStr the string include numbers
     * @return the number in the string
     */
    public String getNumericsFromString(String numStr) {
        return numStr == null ? null : numStr.replaceAll("[^0-9]", "");
    }

    /**
     * Check if the patient num is only active coverage cash.
     * <p>Code from file: F_IsOnlyActiveCoverageCash.sql
     *
     * @param patientNum the patient number
     * @return <code>true</code> if only active coverage cash; <code>false</code> otherwise
     */
    public Boolean isOnlyActiveCoverageCash(BigInteger patientNum) {
        if (patientNum == null) {
            return false;
        }

        Timestamp now = Timestamp.from(Instant.now());
        QPatientPlans qPatientPlans = QPatientPlans.patientPlans;
        QPayorPlans qPayorPlans = QPayorPlans.payorPlans;
        List<Tuple> ppNumList = epostrxQueryFactory.select(qPatientPlans.ppNum, qPatientPlans.coverageTypeId)
                .from(qPatientPlans).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .join(qPayorPlans).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientPlans.ppNum.eq(qPayorPlans.ppNum))
                .where(qPatientPlans.patientNum.eq(patientNum).and(qPatientPlans.coverageTypeId.ne((byte) 4))
                        .and(qPatientPlans.cpDeactivationDate.isNull().or(qPatientPlans.cpDeactivationDate.gt(now)))
                        .and(qPatientPlans.cpExpirationDate.isNull().or(qPatientPlans.cpExpirationDate.gt(now)))
                        .and(qPayorPlans.terminationDate.isNull().or(qPayorPlans.terminationDate.gt(now))))
                .fetch();
        Long minPpNum = ppNumList.stream().min(Comparator.comparingInt(t -> t.get(qPatientPlans.coverageTypeId)))
                .map(t -> t.get(qPatientPlans.ppNum)).orElse(0L);
        return minPpNum == 2;
    }

    /**
     * Get rsRxLinking parent and child.
     * Code from file: F_getRxFillsRemaining.sql
     *
     * @param rxNumber The rx number
     * @param active   The active
     * @return The list of RsMultiLinkDTO
     */
    public List<RsMultiLinkDTO> getRsRxLinkingParentChild(String rxNumber, String active) {
        Helper.checkString(rxNumber, "rxNumber");
        Helper.checkString(active, "active");

        // Code from file: F_RS_RXLINKING_GETPARENTCHILD.sql
        QRxMultiLink qRxMultiLink = QRxMultiLink.rxMultiLink;
        QSystemUsers qSystemUsers = QSystemUsers.systemUsers;
        SQLQuery<RsMultiLinkDTO> query = epostrxQueryFactory.select(Projections.bean(RsMultiLinkDTO.class,
                        qRxMultiLink.rxLinkSeq,
                        qRxMultiLink.childRxNumber,
                        qRxMultiLink.parentRxNumber,
                        qRxMultiLink.parentEScriptMsgAttributeSeq,
                        qRxMultiLink.childEScriptMsgAttributeSeq,
                        qRxMultiLink.active,
                        qRxMultiLink.createdSysUserNum,
                        qSystemUsers.sysUserLogin,
                        qRxMultiLink.createdDatetime,
                        qRxMultiLink.deactivatedSysUserNum,
                        qRxMultiLink.deactivatedDatetime,
                        qRxMultiLink.comment
                ))
                .from(qRxMultiLink).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .join(qSystemUsers).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qSystemUsers.sysUserNum.eq(qRxMultiLink.createdSysUserNum));
        if (!"All".equals(active)) {
            query = query.where(qRxMultiLink.active.eq(active));
        }
        List<RsMultiLinkDTO> rsMultiLinkDTOList = query.distinct().fetch();
        List<RsMultiLinkDTO> result = new ArrayList<>();
        result.addAll(rsMultiLinkDTOList.stream()
                .filter(r -> r.getParentRxNumber().equals(rxNumber) || r.getChildRxNumber().equals(rxNumber))
                .collect(Collectors.toList()));
        //Line: 64-124
        while (true) {
            List<String> parentNum = result.stream().map(r -> r.getParentRxNumber()).collect(Collectors.toList());
            List<String> childNum = result.stream().map(r -> r.getChildRxNumber()).collect(Collectors.toList());
            List<RsMultiLinkDTO> parents = rsMultiLinkDTOList.stream()
                    .filter(r -> parentNum.contains(r.getChildRxNumber()) && !childNum.contains(r.getChildRxNumber()))
                    .collect(Collectors.toList());
            List<String> newParentNum = parents.stream().map(r -> r.getParentRxNumber()).collect(Collectors.toList());
            List<RsMultiLinkDTO> children = rsMultiLinkDTOList.stream()
                    .filter(r -> childNum.contains(r.getParentRxNumber()) &&
                            !parentNum.contains(r.getParentRxNumber()) &&
                            !newParentNum.contains(r.getParentRxNumber()))
                    .collect(Collectors.toList());
            if (parents.isEmpty() && children.isEmpty()) {
                break;
            }
            result.addAll(parents);
            result.addAll(children);
        }
        return result;
    }

    /**
     * Get rx qty fills remaining.
     * Code from file: F_getRxQtyRemaining.sql
     *
     * @param rxNumber      The rx number
     * @param rxEScriptDTOs The list of rx escript
     * @return The count of rx qty remaining
     */
    public Long getRxQtyRemaining(String rxNumber, List<? extends RxEScriptDTO> rxEScriptDTOs) {
        Helper.checkString(rxNumber, "rxNumber");

        // Code from file: F_getRxQtyRemaining.sql
        if (Helper.isNullOrEmpty(rxEScriptDTOs)) {
            rxEScriptDTOs = getRxEScripts(rxNumber);
        }
        if (rxEScriptDTOs.isEmpty()) {
            return null;
        }

        //Line: 39-51
        RxEScriptDTO max = rxEScriptDTOs.stream()
                .max(Comparator.comparingLong(d -> d.getEScriptMsgAttributeSeq().longValue())).get();
        Long quantityRemaining = Optional.ofNullable(max.getRxQtyLeft()).map(x -> x.longValue()).orElse(null);
        //Line: 57-63
        RxEScriptDTO filterMax = rxEScriptDTOs.stream().filter(d -> FILL_STATUS_NUMS.contains(d.getFillStatusNum()))
                .max(Comparator.comparingLong(d -> d.getEScriptMsgAttributeSeq().longValue())).orElse(null);
        //Line: 101-114
        Long totalQtyDispensed = getRxTotalQtyFilled(rxNumber, rxEScriptDTOs) + getRxTotalORTFQtyFilled(rxNumber, rxEScriptDTOs);
        if (totalQtyDispensed < 0) {
            totalQtyDispensed = totalQtyDispensed * (-1);
        }
        //Line: 89
        Long totalQty = getRxTotalQty(max.getEScriptMsgAttributeSeq(), rxEScriptDTOs);
        if (totalQtyDispensed == 0) {
            //Line: 120
            quantityRemaining = totalQty;
        } else if (filterMax != null && filterMax.getFillQtyDispensed() != null && filterMax.getFillQtyDispensed() > 0) {
            //Line: 131-135
            quantityRemaining = totalQty - totalQtyDispensed;
        }
        //Line: 141
        if (quantityRemaining == null || quantityRemaining < 0) {
            quantityRemaining = 0L;
        }
        //Line: 146
        if (quantityRemaining > totalQty) {
            quantityRemaining = totalQty;
        }
        return quantityRemaining;
    }
}

