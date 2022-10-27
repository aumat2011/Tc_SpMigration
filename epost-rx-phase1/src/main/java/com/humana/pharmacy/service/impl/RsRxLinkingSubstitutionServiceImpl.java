package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.dto.RsRxLinkingSubstitutionDTO;
import com.humana.pharmacy.service.RsRxLinkingSubstitutionService;
import com.humana.pharmacy.common.Helper;
import com.humana.pharmacy.common.dto.RsMultiLinkDTO;
import com.humana.pharmacy.common.dto.RsRxScriptDTO;
import com.humana.pharmacy.common.entity.QAutoEscriptFiller;
import com.humana.pharmacy.common.entity.QEScriptMsgAttributes;
import com.humana.pharmacy.common.entity.QOrderHeader;
import com.humana.pharmacy.common.entity.QOrderLines;
import com.humana.pharmacy.common.entity.QPatientAttributes;
import com.humana.pharmacy.common.entity.QPatientPlans;
import com.humana.pharmacy.common.entity.QPayorPlans;
import com.humana.pharmacy.common.entity.QProducts;
import com.humana.pharmacy.common.entity.QQuantityChangeTracking;
import com.humana.pharmacy.common.entity.QRuleQueueException;
import com.humana.pharmacy.common.entity.QRxFillAux;
import com.humana.pharmacy.common.entity.QRxLinkSubstitutionLog;
import com.humana.pharmacy.common.entity.QTradingPartnerGeneral;
import com.humana.pharmacy.common.entity.QWorkflowTransactions;
import com.humana.pharmacy.common.service.FunctionsService;
import com.humana.pharmacy.common.service.impl.BaseServiceImpl;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation of RsRxLinkingSubstitutionService using QueryDSL. It extends BaseServiceImpl.
 */
public class RsRxLinkingSubstitutionServiceImpl extends BaseServiceImpl implements RsRxLinkingSubstitutionService {
    /**
     * The string 'NEWRX'.
     */
    private static final String VALUE_NEWRX = "NEWRX";

    /**
     * The string 'REFILLRX'.
     */
    private static final String VALUE_REFILLRX = "REFILLRX";

    /**
     * The string 'Y'.
     */
    private static final String VALUE_Y = "Y";

    /**
     * The string 'A'.
     */
    private static final String VALUE_A = "A";

    /**
     * The order status numbers '1', '2' and '4'.
     */
    private static final List<Byte> ORDER_STATUS_NUM = Arrays.asList((byte) 1, (byte) 2, (byte) 4);

    /**
     * The order status numbers '1', '2', '4' and '98'.
     */
    private static final List<Byte> ORDER_STATUS_NUM_2 = Arrays.asList((byte) 1, (byte) 2, (byte) 4, (byte) 98);

    /**
     * The fill days supply.
     */
    private static final int FILL_DAYS_SUPPLY = 90;

    /**
     * The code value.
     */
    private static final String CODE_VALUE = "QTY_CHNG";

    /**
     * The code value keyword.
     */
    private static final String CODE_VALUE_KEYWORD = "QTY_CHANGE_FLAG";

    /**
     * The coverage type id.
     */
    private static final byte COVERAGE_TYPE_ID = 4;

    /**
     * The rx status code 1 and 7.
     */
    private static final List<Byte> RX_STATUS_CODE = Arrays.asList((byte) 1, (byte) 7);

    /**
     * Reference to FunctionsService
     */
    private FunctionsService functionsService;

    /**
     * Constructor of RxSanityCgeckServiceImpl.
     *
     * @param epostrxDataSource  DataSource for epostrx database. Required
     * @param workflowDataSource DataSource for epostrx_workflow database. Required
     */
    public RsRxLinkingSubstitutionServiceImpl(DataSource epostrxDataSource, DataSource workflowDataSource) {
        super(epostrxDataSource, workflowDataSource);
    }

    /**
     * Get subEScriptMsgAttributeSeq for eScriptMsgAttributeSeq.
     * Code from file: P_rs_rxlinking_substitution.sql
     *
     * @param eScriptMsgAttributeSeq The scriptId. Required
     * @return the sub scriptId
     */
    public BigInteger getSubEScriptMsgAttributeSeq(BigInteger eScriptMsgAttributeSeq) {
        Helper.checkNull(eScriptMsgAttributeSeq, "eScriptMsgAttributeSeq");
        Helper.checkNull(functionsService, "functionsService");

        BigInteger subEScriptMsgAttributeSeq = eScriptMsgAttributeSeq;
        // Query rsRxScript by rx_number
        List<RsRxScriptDTO> rsRxEScriptDTOs = getRsRxScriptDTO(eScriptMsgAttributeSeq);
        //Line: 87-99
        if (rsRxEScriptDTOs.stream().anyMatch(rsRx -> rsRx.getEScriptMsgAttributeSeq().equals(eScriptMsgAttributeSeq) &&
                VALUE_NEWRX.equals(rsRx.getEdiTransactionCode()) &&
                rsRx.getRxExpirationDate() != null && rsRx.getRxExpirationDate().toInstant().isAfter(Instant.now()) &&
                rsRx.getFillDaysSupply() != null && rsRx.getFillDaysSupply() == FILL_DAYS_SUPPLY)) {
            return subEScriptMsgAttributeSeq;
            //Line: 105-126
        } else if (rsRxEScriptDTOs.stream().anyMatch(rsRx -> rsRx.getEScriptMsgAttributeSeq().equals(eScriptMsgAttributeSeq) &&
                VALUE_REFILLRX.equals(rsRx.getEdiTransactionCode()) &&
                rsRx.getRxExpirationDate() != null && rsRx.getRxExpirationDate().toInstant().isAfter(Instant.now()) &&
                rsRx.getFillDaysSupply() != null && rsRx.getFillDaysSupply() == FILL_DAYS_SUPPLY &&
                functionsService.getRemainingRefills(rsRx.getRxNumber(), rsRxEScriptDTOs) > 0)) {
            return subEScriptMsgAttributeSeq;
        }
        RsRxScriptDTO cur = rsRxEScriptDTOs.stream()
                .filter(e -> e.getEScriptMsgAttributeSeq().equals(eScriptMsgAttributeSeq)).findFirst()
                .orElse(null);
        List<RsMultiLinkDTO> rsMultiLinkDTOList = new ArrayList<>();

        //Line: 134-230
        if (cur != null) {
            rsMultiLinkDTOList.addAll(functionsService.getRsRxLinkingParentChild(cur.getRxNumber(), VALUE_Y));
        }
        List<RsRxLinkingSubstitutionDTO> refillEligibleList = new ArrayList<>();
        if (!rsMultiLinkDTOList.isEmpty()) {
            List<String> rxNumberList = rsMultiLinkDTOList.stream()
                    .flatMap(rml -> Stream.of(rml.getParentRxNumber(), rml.getChildRxNumber()))
                    .collect(Collectors.toList());
            refillEligibleList.addAll(getRsRxLinkingSubstitutions(rxNumberList));
            subEScriptMsgAttributeSeq = Optional.ofNullable(getScriptIndividual(refillEligibleList, cur.getRxNumber()))
                    .orElse(eScriptMsgAttributeSeq);
        }

        //Line: 236
        if (!subEScriptMsgAttributeSeq.equals(eScriptMsgAttributeSeq)) {

            String comment = null;
            BigInteger subScriptId = subEScriptMsgAttributeSeq;
            RsRxLinkingSubstitutionDTO subRx = refillEligibleList.stream()
                    .filter(d -> d.getEScriptMsgAttributeSeq().equals(subScriptId))
                    .findFirst().get();
            RsRxLinkingSubstitutionDTO curRx = refillEligibleList.stream()
                    .filter(d -> d.getEScriptMsgAttributeSeq().equals(eScriptMsgAttributeSeq))
                    .findFirst().get();
            //Line: 255-313
            if (getCodeValueModels(CODE_VALUE).stream()
                    .anyMatch(code -> CODE_VALUE_KEYWORD.equals(code.getCodeValueKeyword()) && VALUE_A.equals(code.getStatusFlag())) &&
                    isQuantityLowered(eScriptMsgAttributeSeq, refillEligibleList)) {
                subEScriptMsgAttributeSeq = eScriptMsgAttributeSeq;
                comment = "Rx number " + curRx.getRxNumber() +
                        " has been quantity lowered in current or immediate previous fill. So Rx linking substitution would not happen for this fill ";
                //Line: 316-337
            } else if (isBeenSelected(subRx.getRxNumber(), refillEligibleList)) {
                subEScriptMsgAttributeSeq = eScriptMsgAttributeSeq;
                comment = "Rx number " + subRx.getRxNumber() + " has already been selected to be filled on order " +
                        subRx.getOhOrderInvoiceNumber() + " at location " + subRx.getTpName();
                //Line: 340-380
            } else if (isNotEligible(subRx.getRxNumber(), refillEligibleList)) {
                subEScriptMsgAttributeSeq = eScriptMsgAttributeSeq;
                comment = "Rx number " + subRx.getRxNumber() + " is not eligible for Refill " +
                        subRx.getOrderInvoiceNumber() +
                        "as it has an existing ERX entry in ERX ITEM Entry Queue or it is in the process of order creation";
                //Line: 382-396
            } else if (isBeenSelectedByCurrentOrder(subRx.getRxNumber(), curRx.getOrderNum(), refillEligibleList)) {
                subEScriptMsgAttributeSeq = eScriptMsgAttributeSeq;
                comment = "Rx number " + subRx.getRxNumber() +
                        " has already been selected to be filled on the current order:" + curRx.getOrderNum();
                //Line: 399-417
            } else if (patientPlanNotFound(subScriptId, refillEligibleList)) {
                subEScriptMsgAttributeSeq = eScriptMsgAttributeSeq;
                comment = "Patient Plan Information not found on profile";
                //Line: 419-434
            } else if (patientPlanInactive(subScriptId, refillEligibleList)) {
                subEScriptMsgAttributeSeq = eScriptMsgAttributeSeq;
                comment = "Patient Plan:" + subRx.getPpPlanName() + " is INACTIVE";
            }
            //Line: 437-467
            if (comment != null) {
                saveLog(subRx, curRx, comment);
            }
        }
        return subEScriptMsgAttributeSeq;
    }

    /**
     * Set implementation object of FunctionService.
     *
     * @param functionsService Implementation object of FunctionsService. Required
     */
    public void setFunctionsService(FunctionsService functionsService) {
        this.functionsService = functionsService;
    }

    /**
     * Check if patient plan is inactive.
     *
     * @param scriptId           The scriptId. Required
     * @param refillEligibleList the list of refill eligible
     * @return if patient plan is inactive
     */
    private Boolean patientPlanInactive(BigInteger scriptId, List<RsRxLinkingSubstitutionDTO> refillEligibleList) {
        //Line: 419-427
        return refillEligibleList.stream().anyMatch(r -> r.getEScriptMsgAttributeSeq().equals(scriptId) &&
                r.getPpNum() != null && r.getPpNum().equals(r.getPayorPpNum()) &&
                r.getCoverageTypeId() != null && r.getCoverageTypeId() == COVERAGE_TYPE_ID);
    }

    /**
     * Check if patient play is not found.
     *
     * @param scriptId           the script id
     * @param refillEligibleList the list of refill eligible
     * @return if patient play is not found
     */
    private Boolean patientPlanNotFound(BigInteger scriptId, List<RsRxLinkingSubstitutionDTO> refillEligibleList) {
        //Line: 399-411
        return refillEligibleList.stream().noneMatch(r -> r.getEScriptMsgAttributeSeq().equals(scriptId) &&
                r.getPpNum() != null && r.getPpNum().equals(r.getPayorPpNum()));
    }

    /**
     * Check if Rx number is not eligible for Refill.
     *
     * @param rxNumber           the rx number
     * @param refillEligibleList the list of refill eligible
     * @return if Rx number is not eligible for Refill
     */
    private Boolean isNotEligible(String rxNumber, List<RsRxLinkingSubstitutionDTO> refillEligibleList) {
        List<BigInteger> workflowTransactions = getWorkflowTransactions();
        //Line: 340-373
        return refillEligibleList.stream()
                .filter(r -> r.getRxNumber().equals(rxNumber))
                .anyMatch(r -> (refillEligibleList.stream()
                        .anyMatch(re ->
                                re.getRxNumber().equals(r.getRxNumber()) &&
                                        ORDER_STATUS_NUM.contains(re.getOrderStatusNum()) &&
                                        re.getOrderLineStatusNum() != null &&
                                        re.getOrderLineStatusNum() == (byte) 1)) ||
                        (r.getOriginationNum() != null && r.getOriginationNum() == 3 &&
                                r.getOrderInvoiceNumber() == null &&
                                workflowTransactions.contains(r.getEScriptMsgAttributeSeq())) ||
                        r.getAutoEfillerSeq() != null ||
                        (ORDER_STATUS_NUM.contains(r.getOrderStatusNum()) && r.getRuleScriptId() == null));
    }

    /**
     * Collect if rx number has been quantity lowered in current or immediate previous fill.
     *
     * @param subScriptId        the script id
     * @param refillEligibleList the list of refill eligible
     * @return if rx number has been quantity lowered in current or immediate previous fill
     */
    private Boolean isQuantityLowered(BigInteger subScriptId, List<RsRxLinkingSubstitutionDTO> refillEligibleList) {
        RsRxLinkingSubstitutionDTO cur = refillEligibleList.stream().filter(r -> r.getEScriptMsgAttributeSeq().equals(subScriptId))
                .findFirst().get();
        //Line: 289-292
        if (VALUE_Y.equals(cur.getIsQuantityLowered())) {
            return true;
        }
        //Line: 274-280
        String isQuantityLoweredLastfill = refillEligibleList.stream()
                .filter(r -> r.getRxNumber().equals(cur.getRxNumber()) &&
                        r.getEScriptMsgAttributeSeq().compareTo(cur.getEScriptMsgAttributeSeq()) < 0)
                .max(Comparator.comparingLong(x -> x.getEScriptMsgAttributeSeq().longValue()))
                .map(r -> r.getIsQuantityLowered()).orElse(null);
        //Line: 293
        if (VALUE_Y.equals(isQuantityLoweredLastfill)) {
            List<RsRxLinkingSubstitutionDTO> scripts = refillEligibleList.stream()
                    .filter(r -> r.getRxNumber().equals(cur.getRxNumber()))
                    .collect(Collectors.toList());
            Long refillRemaining = functionsService.getRemainingRefills(cur.getRxNumber(), scripts).longValue();
            Long qtyRemaining = functionsService.getRxQtyRemaining(cur.getRxNumber(), scripts);
            //Line: 286 295
            if (!(cur.getRxStatusCodeNum() != null && cur.getRxStatusCodeNum() != (byte) 6 &&
                    cur.getRxExpirationDate() != null && cur.getRxExpirationDate().toInstant().isAfter(Instant.now()) &&
                    refillRemaining > 0 && cur.getDispensedQuantity() != null &&
                    cur.getDispensedQuantity() <= qtyRemaining)) {
                return false;
                //Line: 297
            } else if (VALUE_Y.equals(cur.getAllowConsolidate()) && VALUE_Y.equals(cur.getProdConsolidate())) {
                return false;
                //Line: 299
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if rx number has already been selected to be filled.
     *
     * @param rxNumber           The rx number.
     * @param refillEligibleList the list of refill eligible
     * @return if rx number has already been selected to be filled
     */
    private Boolean isBeenSelected(String rxNumber, List<RsRxLinkingSubstitutionDTO> refillEligibleList) {
        //Line: 316-327
        return refillEligibleList.stream().anyMatch(r -> r.getRxNumber().equals(rxNumber) &&
                ORDER_STATUS_NUM.contains(r.getOrderStatusNum()) &&
                r.getOrderLineStatusNum() != null && r.getOrderLineStatusNum() == (byte) 1 &&
                r.getOhOrderInvoiceNumber() != null && !r.getOhOrderInvoiceNumber().isEmpty());
    }

    /**
     * Check if rx number has already been selected to be filled on the current order.
     *
     * @param rxNumber           The rx number
     * @param orderNum           The order number
     * @param refillEligibleList the list of refill eligible
     * @return if rx number has already been selected to be filled on the current orde
     */
    private Boolean isBeenSelectedByCurrentOrder(String rxNumber, BigInteger orderNum, List<RsRxLinkingSubstitutionDTO> refillEligibleList) {
        //Line: 382-391
        return refillEligibleList.stream().anyMatch(r -> r.getRxNumber().equals(rxNumber) &&
                ORDER_STATUS_NUM_2.contains(r.getOrderStatusNum()) &&
                r.getOrderNum() != null && r.getOrderNum().equals(orderNum) &&
                r.getOrderLineStatusNum() != null && r.getOrderLineStatusNum() == (byte) 1);
    }

    /**
     * Save the error log.
     *
     * @param subRx   The rs rx linking substitution dto
     * @param cur     The current rs rx linking substitution dto
     * @param comment The error comment
     */
    private void saveLog(RsRxLinkingSubstitutionDTO subRx, RsRxLinkingSubstitutionDTO cur, String comment) {
        //Line: 437-467
        QRxLinkSubstitutionLog qRxLinkSubstitutionLog = QRxLinkSubstitutionLog.rxLinkSubstitutionLog;
        QProducts qProducts = QProducts.products;
        List<Tuple> products = epostrxQueryFactory.select(qProducts.prodId, qProducts.prodName)
                .from(qProducts).where(qProducts.prodId.in(cur.getWrittenProductId(), cur.getDispensedProductId(),
                        subRx.getWrittenProductId(), subRx.getDispensedProductId())).fetch();
        epostrxQueryFactory.insert(qRxLinkSubstitutionLog)
                .columns(qRxLinkSubstitutionLog.originalRxNumber,
                        qRxLinkSubstitutionLog.substitutedRxNumber,
                        qRxLinkSubstitutionLog.originalWrittenProdName,
                        qRxLinkSubstitutionLog.originalDispensedProdName,
                        qRxLinkSubstitutionLog.substitutedWrittenProdName,
                        qRxLinkSubstitutionLog.substitutedDispensedProdName,
                        qRxLinkSubstitutionLog.orderNumber,
                        qRxLinkSubstitutionLog.userNum,
                        qRxLinkSubstitutionLog.typeOfAction,
                        qRxLinkSubstitutionLog.logDate)
                .values(cur.getRxNumber(),
                        subRx.getRxNumber(),
                        products.stream().filter(t -> t.get(qProducts.prodId).equals(cur.getWrittenProductId()))
                                .map(t -> t.get(qProducts.prodName)).findFirst().orElse(null),
                        products.stream().filter(t -> t.get(qProducts.prodId).equals(cur.getDispensedProductId()))
                                .map(t -> t.get(qProducts.prodName)).findFirst().orElse(null),
                        products.stream().filter(t -> t.get(qProducts.prodId).equals(subRx.getWrittenProductId()))
                                .map(t -> t.get(qProducts.prodName)).findFirst().orElse(null),
                        products.stream().filter(t -> t.get(qProducts.prodId).equals(subRx.getDispensedProductId()))
                                .map(t -> t.get(qProducts.prodName)).findFirst().orElse(null),
                        subRx.getOrderNum(),
                        new BigInteger("1"),
                        comment,
                        Timestamp.from(Instant.now()))
                .execute();
    }

    /**
     * Get script individual record.
     *
     * @param inRxNumber         The rx number
     * @param refillEligibleList The list of refill eligible
     * @return The sub individual record
     */
    private BigInteger getScriptIndividual(List<RsRxLinkingSubstitutionDTO> refillEligibleList, String inRxNumber) {
        //Line: 149-162
        List<String> rxNumOrdered = refillEligibleList.stream()
                .sorted(Comparator.comparing(r -> Optional.ofNullable(((RsRxLinkingSubstitutionDTO) r).getWrittenDate())
                                .orElse(new Timestamp(0L)))
                        .reversed()
                        .thenComparing(r -> ((RsRxLinkingSubstitutionDTO) r).getRxNumber())
                        .reversed())
                .map(r -> r.getRxNumber())
                .filter(rn -> !rn.equals(inRxNumber))
                .distinct()
                .collect(Collectors.toList());
        for (String rxNumberIndividual : rxNumOrdered) {
            //Line: 177-191
            BigInteger scriptIndividual = refillEligibleList.stream()
                    .filter(r -> rxNumberIndividual.equals(r.getRxNumber()) &&
                            r.getFillStatusNum() != null && r.getFillStatusNum() != (byte) 4)
                    .map(r -> r.getEScriptMsgAttributeSeq())
                    .max(Comparator.comparing(m -> m)).orElseGet(() ->
                            refillEligibleList.stream().filter(r -> rxNumberIndividual.equals(r.getRxNumber()))
                                    .map(r -> r.getEScriptMsgAttributeSeq())
                                    .max(Comparator.comparing(m -> m)).orElse(null));
            //Line: 197-217
            if (refillEligibleList.stream().anyMatch(x -> x.getRxExpirationDate() != null &&
                    x.getRxExpirationDate().toInstant().isAfter(Instant.now()) &&
                    x.getFillDaysSupply() != null && x.getFillDaysSupply() == FILL_DAYS_SUPPLY
                    && RX_STATUS_CODE.contains(x.getRxStatusCodeNum()) &&
                    functionsService.getRemainingRefills(rxNumberIndividual, refillEligibleList.stream()
                            .filter(r -> r.getRxNumber().equals(rxNumberIndividual))
                            .collect(Collectors.toList())) > 0)) {
                return scriptIndividual;
            }
        }
        return null;
    }

    /**
     * Collect rs rx escripts by script id.
     *
     * @param scriptId The script id
     * @return The list of rs rx script dto.
     */
    private List<RsRxScriptDTO> getRsRxScriptDTO(BigInteger scriptId) {
        QEScriptMsgAttributes qEScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        QEScriptMsgAttributes qEScriptMsgAttributesR = new QEScriptMsgAttributes("qEScriptMsgAttributesR");
        QRxFillAux qRxFillAux = QRxFillAux.rxFillAux;
        return epostrxQueryFactory.select(
                        Projections.bean(RsRxScriptDTO.class,
                                qEScriptMsgAttributes.eScriptMsgAttributeSeq,
                                qEScriptMsgAttributes.rxRefillsLeft,
                                qEScriptMsgAttributes.writtenQuantity,
                                qEScriptMsgAttributes.dispensedQuantity,
                                qEScriptMsgAttributes.rxNumber,
                                qEScriptMsgAttributes.originalNumRefills,
                                qEScriptMsgAttributes.numRefills,
                                qEScriptMsgAttributes.rxQtyLeft,
                                qEScriptMsgAttributes.ediTransactionCode,
                                qEScriptMsgAttributes.rxExpirationDate,
                                qRxFillAux.fillStatusNum,
                                qRxFillAux.fillQtyDispensed,
                                qRxFillAux.ortfQty,
                                qRxFillAux.fillDaysSupply
                        ))
                .from(qEScriptMsgAttributes).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .join(qEScriptMsgAttributesR).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qEScriptMsgAttributes.rxNumber.eq(qEScriptMsgAttributesR.rxNumber))
                .leftJoin(qRxFillAux).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillAux.eScriptMsgAttributeSeq.eq(qEScriptMsgAttributes.eScriptMsgAttributeSeq))
                .where(qEScriptMsgAttributesR.eScriptMsgAttributeSeq.eq(scriptId))
                .fetch();
    }

    /**
     * Collect rs rx linking substitutions by list of rx number.
     *
     * @param rxNumbers The rx number
     * @return The list of rs rx linking substitution dto
     */
    private List<RsRxLinkingSubstitutionDTO> getRsRxLinkingSubstitutions(List<String> rxNumbers) {
        QEScriptMsgAttributes qeScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        QRxFillAux qRxFillAux = QRxFillAux.rxFillAux;
        QOrderLines qOrderLines = QOrderLines.orderLines;
        QOrderHeader qOrderHeader = QOrderHeader.orderHeader;
        QPatientAttributes qPatientAttributes = QPatientAttributes.patientAttributes;
        QRuleQueueException qRuleQueueException = QRuleQueueException.ruleQueueException;
        QPatientPlans qPatientPlans = QPatientPlans.patientPlans;
        QPayorPlans qPayorPlans = QPayorPlans.payorPlans;
        QTradingPartnerGeneral qTradingPartnerGeneral = QTradingPartnerGeneral.tradingPartnerGeneral;
        QQuantityChangeTracking qQuantityChangeTracking = QQuantityChangeTracking.quantityChangeTracking;
        QAutoEscriptFiller qAutoEscriptFiller = QAutoEscriptFiller.autoEscriptFiller;
        return epostrxQueryFactory.select(
                        Projections.bean(RsRxLinkingSubstitutionDTO.class,
                                qeScriptMsgAttributes.eScriptMsgAttributeSeq,
                                qeScriptMsgAttributes.rxRefillsLeft,
                                qeScriptMsgAttributes.writtenQuantity,
                                qeScriptMsgAttributes.dispensedQuantity,
                                qeScriptMsgAttributes.rxNumber,
                                qeScriptMsgAttributes.originalNumRefills,
                                qeScriptMsgAttributes.numRefills,
                                qeScriptMsgAttributes.rxQtyLeft,
                                qeScriptMsgAttributes.rxStatusCodeNum,
                                qRxFillAux.fillStatusNum,
                                qRxFillAux.fillQtyDispensed,
                                qRxFillAux.ortfQty,
                                qeScriptMsgAttributes.rxExpirationDate,
                                qeScriptMsgAttributes.writtenDate,
                                qeScriptMsgAttributes.writtenProductId,
                                qeScriptMsgAttributes.dispensedProductId,
                                qeScriptMsgAttributes.tradingPartnerNum,
                                qeScriptMsgAttributes.originationNum,
                                qeScriptMsgAttributes.orderInvoiceNumber,
                                qRxFillAux.fillDaysSupply,
                                qRxFillAux.allowConsolidate,
                                qRxFillAux.ppNum,
                                qOrderLines.orderNum,
                                qOrderLines.orderLineStatusNum,
                                qQuantityChangeTracking.isQuantityLowered,
                                qAutoEscriptFiller.autoEfillerSeq,
                                qPatientAttributes.prodConsolidate,
                                qPatientPlans.coverageTypeId,
                                qOrderHeader.orderInvoiceNumber.as("ohOrderInvoiceNumber"),
                                qOrderHeader.orderStatusNum,
                                qPayorPlans.ppNum.as("payorPpNum"),
                                qPayorPlans.ppPlanName,
                                qRuleQueueException.eScriptMsgAttributeSeq.as("ruleScriptId"),
                                qTradingPartnerGeneral.tpName
                        ))
                .from(qeScriptMsgAttributes).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .leftJoin(qRxFillAux).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION).on(qRxFillAux.eScriptMsgAttributeSeq.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq))
                .leftJoin(qOrderLines).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION).on(qOrderLines.eScriptMsgAttributeSeq.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq))
                .leftJoin(qOrderHeader).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION).on(qOrderHeader.orderNum.eq(qOrderLines.orderNum))
                .leftJoin(qPatientAttributes).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION).on(qPatientAttributes.patientNum.eq(qeScriptMsgAttributes.patientNum))
                .leftJoin(qPatientPlans).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION).on(qPatientPlans.patientNum.eq(qeScriptMsgAttributes.patientNum))
                .leftJoin(qPayorPlans).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION).on(qPayorPlans.ppNum.eq(qPatientPlans.ppNum))
                .leftJoin(qRuleQueueException).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION).on(qRuleQueueException.eScriptMsgAttributeSeq.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq))
                .leftJoin(qTradingPartnerGeneral).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION).on(qTradingPartnerGeneral.tradingPartnerNum.eq(qeScriptMsgAttributes.tradingPartnerNum))
                .leftJoin(qQuantityChangeTracking).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION).on(qQuantityChangeTracking.eScriptMsgAttributeSeq.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq))
                .leftJoin(qAutoEscriptFiller).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION).on(qAutoEscriptFiller.escriptId.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq).and(qAutoEscriptFiller.orderNum.isNull()))
                .where(qeScriptMsgAttributes.rxNumber.in(rxNumbers))
                .fetch();
    }

    /**
     * Collect workflow transactions which workflow num is 2.
     *
     * @return The list of workflow transaction
     */
    private List<BigInteger> getWorkflowTransactions() {
        QWorkflowTransactions qWorkflowTransactions = QWorkflowTransactions.workflowTransactions;
        return workflowQueryFactory.select(qWorkflowTransactions.eScriptMsgAttributeSeq)
                .from(qWorkflowTransactions).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .where(qWorkflowTransactions.workflowNum.eq(2))
                .fetch();
    }
}

