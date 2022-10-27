package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.common.Helper;
import com.humana.pharmacy.common.cache.model.CCodeValue;
import com.humana.pharmacy.common.service.impl.BaseServiceImpl;
import com.humana.pharmacy.constants.RsRxLinkWorkflowMinUtilizationCheckError;
import com.humana.pharmacy.dto.RsRxLinkWorkflowMinUtilizationCheckDTO;
import com.humana.pharmacy.common.dto.RsMultiLinkDTO;
import com.humana.pharmacy.dto.RsRxLinkWorkflowMinUtilizationCheckResult;
import com.humana.pharmacy.common.entity.QCityStateZip;
import com.humana.pharmacy.common.entity.QEScriptMsgAttributes;
import com.humana.pharmacy.common.entity.QOrderBillship;
import com.humana.pharmacy.common.entity.QOrderHeader;
import com.humana.pharmacy.common.entity.QOrderLines;
import com.humana.pharmacy.common.entity.QPatientAddress;
import com.humana.pharmacy.common.entity.QPatientAddressTypeAssignments;
import com.humana.pharmacy.common.entity.QProducts;
import com.humana.pharmacy.common.entity.QRxFillAux;
import com.humana.pharmacy.common.entity.QRxMultiLink;
import com.humana.pharmacy.common.entity.QRxParams;
import com.humana.pharmacy.common.entity.QRxParamsGpis;
import com.humana.pharmacy.common.entity.QStateCsProducts;
import com.humana.pharmacy.common.entity.QStates;
import com.humana.pharmacy.common.entity.QWorkflowTransactions;
import com.humana.pharmacy.common.model.RxParams;
import com.humana.pharmacy.common.model.RxParamsGpis;
import com.humana.pharmacy.service.RsRxLinkWorkflowMinUtilizationCheckService;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.types.Projections;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;

/**
 * Implementation of RsRxLinkWorkflowMinUtilizationCheckService using QueryDSL. It extends BaseServiceImpl.
 */
public class RsRxLinkWorkflowMinUtilizationCheckServiceImpl extends BaseServiceImpl implements RsRxLinkWorkflowMinUtilizationCheckService {
    /**
     * The string 'NEWRX'.
     */
    private static final String NEWRX = "NEWRX";

    /**
     * The string 'RXSANITY'.
     */
    private static final String RXSANITY = "RXSANITY";

    /**
     * The string 'REFILLRX'.
     */
    private static final String REFILLRX = "REFILLRX";

    /**
     * The default state name 'DEFAULT SETTINGS:'.
     */
    private static final String DEFAULT_STATE_NAME = "DEFAULT SETTINGS:";

    /**
     * The string 'A'.
     */
    private static final String VALUE_A = "A";

    /**
     * The string 'Y'.
     */
    private static final String VALUE_Y = "Y";

    /**
     * The date format 'MM/dd/yyy'.
     */
    private static final String DATE_FORMAT = "MM-dd-yyyy";

    /**
     * The fill status num '5'.
     */
    private static final byte FILL_STATUS_NUM_5 = (byte) 5;

    /**
     * The fill status num '9'.
     */
    private static final byte FILL_STATUS_NUM_9 = (byte) 9;

    /**
     * The number '100'.
     */
    private static final int ONE_HUNDRED = 100;

    /**
     * The number '10'.
     */
    private static final int VALUE_10 = 10;

    /**
     * The fill status num '2' and '9'.
     */
    private static final List<Byte> FILL_STATUS_NUMS_1 = Arrays.asList((byte) 2, (byte) 9);

    /**
     * The fill status num '2', '5' and '9'.
     */
    private static final List<Byte> FILL_STATUS_NUMS_2 = Arrays.asList((byte) 2, (byte) 5, (byte) 9);

    /**
     * Constructor of RsRxLinkWorkflowMinUtilizationCheckServiceImpl.
     *
     * @param epostrxDataSource  DataSource for epostrx database. Required
     * @param workflowDataSource DataSource for epostrx_workflow database. Required
     * @throws RuntimeException if epostrxDataSource or workflowDataSource is null
     */
    public RsRxLinkWorkflowMinUtilizationCheckServiceImpl(DataSource epostrxDataSource, DataSource workflowDataSource) {
        super(epostrxDataSource, workflowDataSource);
    }

    /**
     * Check Rs Rx link workflow min utilization.
     * Code from file: P_RS_RXLINK_WORKFLOW_MINUTILIZATION_CHECK.sql
     *
     * @param rxNumber The rx number. Required
     * @return The Rs Rx link workflow min utilization check result
     * @throws RuntimeException if rxNumber is null or empty
     */
    public RsRxLinkWorkflowMinUtilizationCheckResult checkRsRxLinkWorkflowMinUtilization(String rxNumber) {
        Helper.checkString(rxNumber, "rxNumber");

        // Line#: 88 - 91
        if (!isRxMultiLinkExist(rxNumber)) {
            return null;
        }

        // Line#: 108 - 115
        List<RsMultiLinkDTO> rsMultiLinkDTOS = getRsRxLinkingParentChild(rxNumber, VALUE_Y);
        Set<String> rxNumbers = rsMultiLinkDTOS.stream()
                .flatMap(e -> Stream.of(e.getChildRxNumber(), e.getParentRxNumber()))
                .collect(Collectors.toSet());

        rxNumbers.add(rxNumber);
        // Get data
        List<RsRxLinkWorkflowMinUtilizationCheckDTO> dtosByRxNumbers =
                getRsRxLinkWorkflowMinUtilizationCheckDTOs(rxNumbers);

        rxNumbers.remove(rxNumber);

        RsRxLinkWorkflowMinUtilizationCheckResult result = new RsRxLinkWorkflowMinUtilizationCheckResult();
        for (String rxNumberIndividual : rxNumbers) {

            // Line#: 137 - 145
            if (isWorkflowTransactionsExist(rxNumberIndividual)) {
                result.setError(RsRxLinkWorkflowMinUtilizationCheckError.ITEM_IN_WORKFLOW);
            }

            // CHECK MIN UTILIZATION

            // Line#: 149 - 172
            String rxNumberInUse = rxNumberIndividual;

            BigInteger eScriptMsgAttributeSeq = dtosByRxNumbers.stream().filter(e -> Objects.equals(e.getRxNumber(), rxNumberIndividual)
                            && ((FILL_STATUS_NUMS_1.contains(e.getFillStatusNum()) && !Objects.equals(e.getEdiTransactionCode(), NEWRX))
                            || (FILL_STATUS_NUMS_2.contains(e.getFillStatusNum()) && Objects.equals(e.getEdiTransactionCode(), NEWRX))))
                    .map(RsRxLinkWorkflowMinUtilizationCheckDTO::getEScriptMsgAttributeSeq)
                    .max(BigInteger::compareTo)
                    .orElse(BigInteger.ZERO);

            if (eScriptMsgAttributeSeq.equals(BigInteger.ZERO)) {
                rxNumberInUse = rxNumber;
                eScriptMsgAttributeSeq = dtosByRxNumbers.stream().filter(e -> Objects.equals(e.getRxNumber(), rxNumber)
                                && Objects.equals(e.getFillStatusNum(), FILL_STATUS_NUM_5)
                                && Objects.equals(e.getEdiTransactionCode(), REFILLRX))
                        .map(RsRxLinkWorkflowMinUtilizationCheckDTO::getEScriptMsgAttributeSeq)
                        .max(BigInteger::compareTo).orElse(BigInteger.ZERO);
            }

            if (eScriptMsgAttributeSeq.compareTo(BigInteger.ZERO) > 0) {
                // Line#: 175
                RsRxLinkWorkflowMinUtilizationCheckError error =
                        calculateRsRxLinkMinUtilization(eScriptMsgAttributeSeq, rxNumberInUse, dtosByRxNumbers);
                if (error != null) {
                    result.setError(error);
                }
            }

            if (result.getError() != null) {
                break;
            }

        }

        // Line#: 176 - 181
        List<CCodeValue> codeValues = getCodeValueModels(RXSANITY);
        CCodeValue codeValue = codeValues.stream().filter(cv -> VALUE_A.equals(cv.getStatusFlag()))
                .findFirst()
                .orElse(new CCodeValue());
        result.setOverrideQname(codeValue.getCodeValueDesc());
        result.setOverrideQueue(codeValue.getCodeValueKeyword());

        return result;
    }

    /**
     * The Rs Rx link min utilization calculation.
     * Code from file: F_RS_RXLINK_MINUTILIZATION_CALCULATION.sql
     *
     * @param eScriptMsgAttributeSeq The eScript msg attribute seq
     * @param rxNumber               The rx number
     * @param dtosByRxNumbers        The DTOs by rx numbers
     * @return The error or null if no error found
     */
    private RsRxLinkWorkflowMinUtilizationCheckError calculateRsRxLinkMinUtilization(BigInteger eScriptMsgAttributeSeq, String rxNumber,
                                                                                     List<RsRxLinkWorkflowMinUtilizationCheckDTO> dtosByRxNumbers) {
        // Line#: 70 - 96
        List<RsRxLinkWorkflowMinUtilizationCheckDTO> dtos =
                getRsRxLinkWorkflowMinUtilizationCheckDTOs(eScriptMsgAttributeSeq);


        // Line#: 79
        Long timesFilled = getRxTotalFills(rxNumber, dtosByRxNumbers);

        RsRxLinkWorkflowMinUtilizationCheckDTO currDto = dtos.get(0);
        String prodGenericRefNum = ofNullable(currDto.getProdGenericRefNum()).orElse("");
        String gpiClass = prodGenericRefNum.substring(0, Math.min(VALUE_10, prodGenericRefNum.length()));

        BigInteger eScriptMsgAttributeSeqIndividual = currDto.getChildEScriptMsgAttributeSeq();
        // Line#: 98
        Integer stateNum = getOrderNarcCodeStateNum(eScriptMsgAttributeSeqIndividual);

        // Line#: 100 - 106
        String stateName = DEFAULT_STATE_NAME;

        if (stateNum > 0) {
            QStates qStates = QStates.states;
            stateName = ofNullable(epostrxQueryFactory.select(qStates.stateName)
                    .from(qStates).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                    .where(qStates.stateNum.eq(stateNum))
                    .fetchFirst()).orElse(stateName);
        }

        // CHECK IF A STATE RESTRICTED GPI

        // Line#: 111 - 114
        List<RxParamsGpis> rxParamsGpisList = getRxParamsGpis(gpiClass, stateNum);

        List<RxParamsGpis> filteredRxParamsGpisList =
                rxParamsGpisList.stream().filter(e -> Objects.equals(e.getStateNum(), stateNum))
                        .collect(Collectors.toList());
        Boolean isRestrictedGpi = filteredRxParamsGpisList.size() > 0;

        // Line#: 123
        if (timesFilled > 0) {
            // Line#: 126

            if (isRestrictedGpi) {
                // Line#: 129 - 171

                Integer narcMinUtilPct =
                        ofNullable(filteredRxParamsGpisList.get(0).getMinPctUtilization()).orElse(0);
                if (narcMinUtilPct == 0) {
                    narcMinUtilPct = rxParamsGpisList.stream().filter(e -> Objects.equals(e.getStateNum(), 0))
                            .map(RxParamsGpis::getMinPctUtilization)
                            .filter(Objects::nonNull)
                            .findFirst()
                            .orElse(0);
                }

                if (narcMinUtilPct > 0) {
                    LocalDateTime lastFillDate = getRxLastFillDateAsDate(rxNumber, dtosByRxNumbers);
                    Integer days = ofNullable(currDto.getFillDaysSupply()).orElse(0) * narcMinUtilPct / ONE_HUNDRED;
                    LocalDateTime narcEligRefillDate = lastFillDate.plusDays(days);
                    int narcDaysRts = (int) ChronoUnit.DAYS.between(LocalDateTime.now(), narcEligRefillDate);
                    if (narcDaysRts > 0) {
                        return RsRxLinkWorkflowMinUtilizationCheckError.STATE_GPI_MIN_UTILIZATION_NOT_MET
                                .withParams(stateName, narcEligRefillDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
                    }
                }

            } else {
                // Line#: 175 - 215
                Integer stateNum2 = getOrderNarcCodeStateNum(eScriptMsgAttributeSeq);
                String narcoticCode = "" + getDeaProduct(dtos, stateNum2);
                List<RxParams> rxParamsList = getRxParams(narcoticCode, stateNum2);
                Integer narcMinUtilPct = rxParamsList.stream().filter(e -> e.getStateNum().equals(stateNum2))
                        .map(RxParams::getMinPctUtilization)
                        .filter(Objects::nonNull)
                        .findFirst()
                        .orElse(0);
                if (narcMinUtilPct == 0) {
                    narcMinUtilPct = rxParamsList.stream().filter(e -> Objects.equals(e.getStateNum(), 0))
                            .map(RxParams::getMinPctUtilization)
                            .filter(Objects::nonNull)
                            .findFirst()
                            .orElse(0);
                }
                if (narcMinUtilPct > 0) {
                    LocalDateTime lastFillDate = getRxLastFillDateAsDate(rxNumber, dtosByRxNumbers);
                    Integer days = ofNullable(currDto.getFillDaysSupply()).orElse(0) * (narcMinUtilPct / ONE_HUNDRED);
                    LocalDateTime narcEligRefillDate = lastFillDate.plusDays(days);
                    Integer narcDaysRts = (int) ChronoUnit.DAYS.between(LocalDateTime.now(), narcEligRefillDate);
                    if (narcDaysRts > 0) {
                        return RsRxLinkWorkflowMinUtilizationCheckError.STATE_NARC_MIN_UTILIZATION_NOT_MET
                                .withParams(stateName, narcEligRefillDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
                    }
                }
            }
        }

        return null;
    }

    /**
     * Get product dea.
     * Code from file: F_getDeaProductByScriptId.sql
     *
     * @param dtos     The DTOs
     * @param stateNum The state num
     * @return product dea
     */
    private Short getDeaProduct(List<RsRxLinkWorkflowMinUtilizationCheckDTO> dtos, Integer stateNum) {
        // Code from file: F_getDeaProductByScriptId.sql

        RsRxLinkWorkflowMinUtilizationCheckDTO currDto = dtos.get(0);

        Short prodDea = (short) ofNullable(currDto.getProdDea()).orElse((byte) 0);

        // Line#: 58 - 60
        Long idSeq = dtos.stream().filter(e -> Objects.equals(e.getAddressTypeNum(), VALUE_10))
                .map(RsRxLinkWorkflowMinUtilizationCheckDTO::getPatientAddrSeq)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(0L);

        if (currDto.getOrderInvoiceNumber() != null) {
            // Line#: 68 - 72
            idSeq = dtos.stream().filter(e -> Objects.equals(e.getStateCsProductStateNum(), stateNum))
                    .map(RsRxLinkWorkflowMinUtilizationCheckDTO::getStateCsProductSeq)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(idSeq);

            // Line#: 75 - 82
            if (idSeq > 0) {
                prodDea = dtos.stream().filter(e -> Objects.equals(e.getStateCsProductStateNum(), stateNum))
                        .map(RsRxLinkWorkflowMinUtilizationCheckDTO::getStateCsProductProdDea)
                        .filter(Objects::nonNull)
                        .findFirst()
                        .orElse((short) 0);
            }

        }

        return prodDea;
    }

    /**
     * Get RX last fill date.
     * Code from file: F_getRxLastFillDateAsDate.sql
     *
     * @param rxNumber        The rx numbers
     * @param dtosByRxNumbers The DTOs by rx numbers
     * @return the RX last fill date
     */
    private LocalDateTime getRxLastFillDateAsDate(String rxNumber,
                                                  List<RsRxLinkWorkflowMinUtilizationCheckDTO> dtosByRxNumbers) {
        // Code from file: F_getRxLastFillDateAsDate.sql

        // Line#: 31 - 37
        BigInteger idSeq = dtosByRxNumbers.stream().filter(e -> Objects.equals(e.getRxNumber(), rxNumber)
                        && Objects.equals(e.getFillStatusNum(), FILL_STATUS_NUM_9))
                .map(RsRxLinkWorkflowMinUtilizationCheckDTO::getEScriptMsgAttributeSeq)
                .max(BigInteger::compareTo)
                .orElse(BigInteger.ZERO);

        // Line#: 40 - 46
        LocalDateTime lastFillDate = null;
        if (idSeq.compareTo(BigInteger.ZERO) > 0) {
            lastFillDate = dtosByRxNumbers.stream()
                    .filter(e -> Objects.equals(e.getEScriptMsgAttributeSeq(), idSeq) && e.getFillDispenseDate() != null)
                    .map(e -> e.getFillDispenseDate().toLocalDateTime())
                    .findFirst()
                    .orElse(null);
        }

        return ofNullable(lastFillDate).orElse(LocalDateTime.now());
    }

    /**
     * Get RxParams data.
     * Code from file: F_RS_RXLINK_MINUTILIZATION_CALCULATION.sql
     *
     * @param narcoticCode the narcotic code
     * @param stateNum     the stat num
     * @return the RxParams data.
     */
    private List<RxParams> getRxParams(String narcoticCode, Integer stateNum) {
        QRxParams qRxParams = QRxParams.rxParams;

        return epostrxQueryFactory.select(Projections.bean(RxParams.class,
                        qRxParams.stateNum,
                        qRxParams.minPctUtilization))
                .from(qRxParams).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .where(qRxParams.narcoticCode.eq(narcoticCode)
                        .and(qRxParams.stateNum.eq(stateNum).or(qRxParams.stateNum.eq(0))))
                .fetch();
    }

    /**
     * Get RxParamsGpis data.
     * Code from file: F_RS_RXLINK_MINUTILIZATION_CALCULATION.sql
     *
     * @param gpiClass the gpi class
     * @param stateNum the stat num
     * @return the RxParamsGpis data.
     */
    private List<RxParamsGpis> getRxParamsGpis(String gpiClass, Integer stateNum) {
        QRxParamsGpis qRxParamsGpis = QRxParamsGpis.rxParamsGpis;

        return epostrxQueryFactory.select(Projections.bean(RxParamsGpis.class,
                        qRxParamsGpis.stateNum,
                        qRxParamsGpis.minPctUtilization))
                .from(qRxParamsGpis).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .where(qRxParamsGpis.gpiClassId.eq(gpiClass)
                        .and(qRxParamsGpis.stateNum.eq(stateNum).or(qRxParamsGpis.stateNum.eq(0))))
                .fetch();
    }

    /**
     * Get total fills.
     * Code from file: F_getRxTotalFills_inline
     *
     * @param rxNumber        The rx numbers
     * @param dtosByRxNumbers The DTOs by rx numbers
     * @return Total fills
     */
    private Long getRxTotalFills(String rxNumber, List<RsRxLinkWorkflowMinUtilizationCheckDTO> dtosByRxNumbers) {

        return dtosByRxNumbers.stream()
                .filter(e -> Objects.equals(rxNumber, e.getRxNumber())
                        && FILL_STATUS_NUMS_1.contains(e.getFillStatusNum()))
                .count();
    }

    /**
     * Get RsRxLinkWorkflowMinUtilizationCheckDTO data by rx numbers.
     *
     * @param rxNumbers the rx numbers.
     * @return the RsRxLinkWorkflowMinUtilizationCheckDTO data
     */
    private List<RsRxLinkWorkflowMinUtilizationCheckDTO> getRsRxLinkWorkflowMinUtilizationCheckDTOs(Collection<String> rxNumbers) {
        // Line#: 149 - 158, 164 - 171
        QEScriptMsgAttributes qeScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        QRxFillAux qRxFillAux = QRxFillAux.rxFillAux;

        return epostrxQueryFactory.select(Projections.bean(RsRxLinkWorkflowMinUtilizationCheckDTO.class,
                        qeScriptMsgAttributes.eScriptMsgAttributeSeq,
                        qeScriptMsgAttributes.rxNumber,
                        qeScriptMsgAttributes.ediTransactionCode,
                        qRxFillAux.fillStatusNum
                ))
                .from(qeScriptMsgAttributes).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .join(qRxFillAux).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillAux.eScriptMsgAttributeSeq.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq))
                .where(qeScriptMsgAttributes.rxNumber.in(rxNumbers))
                .fetch();
    }

    /**
     * Get RsRxLinkWorkflowMinUtilizationCheckDTO data by eScript msg attribute seq.
     * Code from file: F_RS_RXLINK_MINUTILIZATION_CALCULATION.sql
     *
     * @param eScriptMsgAttributeSeq the eScript msg attribute seq.
     * @return the RsRxLinkWorkflowMinUtilizationCheckDTO data
     */
    private List<RsRxLinkWorkflowMinUtilizationCheckDTO> getRsRxLinkWorkflowMinUtilizationCheckDTOs(BigInteger eScriptMsgAttributeSeq) {
        // Code from file: F_RS_RXLINK_MINUTILIZATION_CALCULATION.sql
        // Line#: 70 - 96

        QEScriptMsgAttributes qeScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        QRxMultiLink qRxMultiLink = QRxMultiLink.rxMultiLink;
        QRxFillAux qRxFillAux = QRxFillAux.rxFillAux;
        QProducts qProducts = QProducts.products;

        QPatientAddressTypeAssignments qPatientAddressTypeAssignments =
                QPatientAddressTypeAssignments.patientAddressTypeAssignments;
        QStateCsProducts qStateCsProducts = QStateCsProducts.stateCsProducts;

        return epostrxQueryFactory.select(Projections.bean(RsRxLinkWorkflowMinUtilizationCheckDTO.class,
                        qeScriptMsgAttributes.eScriptMsgAttributeSeq,
                        qeScriptMsgAttributes.rxNumber,
                        qeScriptMsgAttributes.orderInvoiceNumber,
                        qRxMultiLink.childEScriptMsgAttributeSeq.coalesce(BigInteger.ZERO).as("childEScriptMsgAttributeSeq"),
                        qRxFillAux.fillDaysSupply,
                        qRxFillAux.fillDispenseDate,
                        qRxFillAux.fillStatusNum,
                        qProducts.prodGenericRefNum,
                        qProducts.prodDea,
                        qPatientAddressTypeAssignments.addressTypeNum,
                        qPatientAddressTypeAssignments.patientAddrSeq,
                        qStateCsProducts.stateCsProductSeq,
                        qStateCsProducts.stateNum.as("stateCsProductStateNum"),
                        qStateCsProducts.prodDea.as("stateCsProductProdDea")
                ))
                .from(qeScriptMsgAttributes).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .leftJoin(qRxMultiLink).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxMultiLink.parentEScriptMsgAttributeSeq.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq))
                .leftJoin(qRxFillAux).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillAux.eScriptMsgAttributeSeq.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq))
                .leftJoin(qProducts).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qProducts.prodId.eq(qeScriptMsgAttributes.dispensedProductId))
                .leftJoin(qPatientAddressTypeAssignments).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientAddressTypeAssignments.patientNum.eq(qeScriptMsgAttributes.patientNum))
                .leftJoin(qStateCsProducts).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qStateCsProducts.prodId.eq(qeScriptMsgAttributes.dispensedProductId))
                .where(qeScriptMsgAttributes.eScriptMsgAttributeSeq.eq(eScriptMsgAttributeSeq))
                .fetch();
    }

    /**
     * Get order narc code state num.
     * Code from file: F_getOrderNarcCodeStateNum.sql
     *
     * @param scriptId the script id
     * @return order narc code state num or 0 if null or not found
     */
    private Integer getOrderNarcCodeStateNum(BigInteger scriptId) {
        QEScriptMsgAttributes qeScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        QProducts qProducts = QProducts.products;
        QOrderHeader qOrderHeader = QOrderHeader.orderHeader;
        QOrderLines qOrderLines = QOrderLines.orderLines;
        QOrderBillship qOrderBillship = QOrderBillship.orderBillship;
        QPatientAddress qPatientAddress = QPatientAddress.patientAddress1;
        QCityStateZip qCityStateZip = QCityStateZip.cityStateZip;
        QRxParams qRxParams = QRxParams.rxParams;

        Integer stateNum = epostrxQueryFactory.select(qCityStateZip.stateNum)
                .from(qeScriptMsgAttributes).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .join(qProducts).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qProducts.prodId.eq(qeScriptMsgAttributes.dispensedProductId))
                .join(qOrderHeader).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderHeader.orderInvoiceNumber.eq(qeScriptMsgAttributes.orderInvoiceNumber))
                .join(qOrderLines).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderLines.orderNum.eq(qOrderHeader.orderNum)
                        .and(qOrderLines.eScriptMsgAttributeSeq.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq)))
                .join(qOrderBillship).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderBillship.orderNum.eq(qOrderHeader.orderNum))
                .join(qPatientAddress).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientAddress.patientAddrSeq.eq(qOrderBillship.patientShipAddressSeq))
                .join(qCityStateZip).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qCityStateZip.cszZipNum.eq(qPatientAddress.cszZipNum))
                .join(qRxParams).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxParams.stateNum.eq(qCityStateZip.stateNum))
                .where(qeScriptMsgAttributes.eScriptMsgAttributeSeq.eq(scriptId))
                .fetchFirst();

        return ofNullable(stateNum).orElse(0);
    }

    /**
     * Check if workflow transactions exists.
     *
     * @param rxNumber The Rx number
     * @return true if workflow transactions exists; false otherwise.
     */
    private Boolean isWorkflowTransactionsExist(String rxNumber) {
        QWorkflowTransactions qWorkflowTransactions = QWorkflowTransactions.workflowTransactions;

        return workflowQueryFactory.select(qWorkflowTransactions)
                .from(qWorkflowTransactions).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .where(qWorkflowTransactions.rxnumber.eq(rxNumber))
                .fetchCount() > 0;
    }

    /**
     * Check if Rx multi link exists.
     *
     * @param rxNumber The Rx number
     * @return true if Rx multi link exists; false otherwise.
     */
    private Boolean isRxMultiLinkExist(String rxNumber) {
        QRxMultiLink qRxMultiLink = QRxMultiLink.rxMultiLink;

        return epostrxQueryFactory.select(qRxMultiLink)
                .from(qRxMultiLink).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .where(qRxMultiLink.parentRxNumber.eq(rxNumber).or(qRxMultiLink.childRxNumber.eq(rxNumber)))
                .fetchCount() > 0;
    }
}

