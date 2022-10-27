package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.common.service.impl.BaseServiceImpl;
import com.humana.pharmacy.dto.AnsDefaultRxPlanParamsDTO;
import com.humana.pharmacy.dto.AnsDefaultRxPlanParamsResult;
import com.humana.pharmacy.common.entity.QCityStateZip;
import com.humana.pharmacy.common.entity.QEScriptMsgAttributes;
import com.humana.pharmacy.common.entity.QOrderBillship;
import com.humana.pharmacy.common.entity.QOrderHeader;
import com.humana.pharmacy.common.entity.QOrderLines;
import com.humana.pharmacy.common.entity.QPatientAddress;
import com.humana.pharmacy.common.entity.QPatientAddressTypeAssignments;
import com.humana.pharmacy.common.entity.QPayorPlans;
import com.humana.pharmacy.common.entity.QProducts;
import com.humana.pharmacy.common.entity.QRxParams;
import com.humana.pharmacy.common.entity.QStateCsProducts;
import com.humana.pharmacy.service.AnsDefaultRxPlanParamsService;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Implementation of AnsDefaultRxPlanParamsService using QueryDSL. It extends BaseServiceImpl.
 */
public class AnsDefaultRxPlanParamsServiceImpl extends BaseServiceImpl implements AnsDefaultRxPlanParamsService {
    /**
     * The address type num '10'.
     */
    private static final int ADDRESS_TYPE_NUM_10 = 10;

    /**
     * The string 'N'.
     */
    private static final String VALUE_N = "N";


    /**
     * Constructor of AnsDefaultRxPlanParamsServiceImpl.
     *
     * @param epostrxDataSource  DataSource for epostrx database. Required
     * @param workflowDataSource DataSource for epostrx_workflow database. Required
     * @throws RuntimeException if epostrxDataSource or workflowDataSource is null
     */
    public AnsDefaultRxPlanParamsServiceImpl(DataSource epostrxDataSource, DataSource workflowDataSource) {
        super(epostrxDataSource, workflowDataSource);
    }

    /**
     * Get the Rx/Plan Parameters.
     * Code from file: P_ansdefaultrxplanparams.sql
     *
     * @param prodId   The prod id. Optional
     * @param ppNum    The pp num. Optional
     * @param scriptId The script id. Optional
     * @return The result
     * @throws RuntimeException if any error occurs
     */
    public AnsDefaultRxPlanParamsResult getAnsDefaultRxPlanParams(Long prodId, Long ppNum, BigInteger scriptId) {

        AnsDefaultRxPlanParamsResult result = new AnsDefaultRxPlanParamsResult();

        // Line#: 100 - 136
        // Rx Params data
        CompletableFuture<AnsDefaultRxPlanParamsResult> rxParamsFuture =
                CompletableFuture.supplyAsync(() -> getRxParamsData(result, prodId, 0, null));
        // Payor Plans data
        CompletableFuture<AnsDefaultRxPlanParamsResult> payorPlansFuture =
                CompletableFuture.supplyAsync(() -> getPayorPlansData(result, ppNum));

        rxParamsFuture.join();
        payorPlansFuture.join();

        // Line#: 140 - 205
        if (scriptId != null) {
            Integer stateNum = getOrderNarcCodeStateNum(scriptId);
            List<AnsDefaultRxPlanParamsDTO> list = getAnsDefaultRxPlanParamsDTOs(scriptId, stateNum, prodId);

            List<AnsDefaultRxPlanParamsDTO> listFiltered1 =
                    list.stream().filter(e -> Objects.equals(e.getDispensedProductId(), e.getProdId()))
                            .collect(Collectors.toList());
            Integer prodDea = getDeaProduct(listFiltered1);

            List<AnsDefaultRxPlanParamsDTO> listFiltered2 =
                    list.stream().filter(e -> Objects.equals(e.getProdId(), prodId)
                                    && Objects.equals(e.getStateCsProductProdId(), prodId))
                            .collect(Collectors.toList());
            if (prodDea == 0 && !listFiltered2.isEmpty()) {
                AnsDefaultRxPlanParamsDTO currDto = listFiltered2.get(0);
                if (currDto.getDispensedProductId() == null) {
                    prodDea = (int) ofNullable(currDto.getProdDea()).orElse((byte) 0);

                    if (currDto.getOrderInvoiceNumber() != null) {
                        prodDea = getStateCsProductProdDea(listFiltered2, prodDea);
                    }
                }

            }
            // get narcode for prod from default record
            getRxParamsData(result, prodId, stateNum, ofNullable(prodDea).orElse(0).toString());
        }

        return result;
    }

    /**
     * Get state cs product prod dea
     *
     * @param dtos       the AnsDefaultRxPlanParamsDTO data
     * @param defaultDea the default dea
     * @return the state cs product seq
     */
    private static Integer getStateCsProductProdDea(List<AnsDefaultRxPlanParamsDTO> dtos, int defaultDea) {
        Long idSeq = dtos.stream()
                .map(AnsDefaultRxPlanParamsDTO::getStateCsProductSeq)
                .filter(Objects::nonNull)
                .min(Long::compare)
                .orElse(0L);

        int dea = defaultDea;
        // Line#: 75 - 82
        if (idSeq > 0) {
            dea = (int) dtos.stream()
                    .map(AnsDefaultRxPlanParamsDTO::getStateCsProductProdDea)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse((short) 0);
        }
        return dea;
    }

    /**
     * Get Rx Params data.
     *
     * @param result   the result.
     * @param prodId   the prod id.
     * @param stateNum the state num.
     * @param prodDea  the prod dea.
     * @return the passed in result
     */
    private AnsDefaultRxPlanParamsResult getRxParamsData(AnsDefaultRxPlanParamsResult result, Long prodId,
                                                         Integer stateNum, String prodDea) {
        if (prodId == null) {
            return result;
        }

        // Line#: 104 - 119, 189 - 204
        QRxParams qRxParams = QRxParams.rxParams;
        QProducts qProducts = QProducts.products;

        BooleanExpression booleanExpression =
                qRxParams.stateNum.eq(stateNum);
        if (prodDea != null) {
            booleanExpression = booleanExpression.and(qRxParams.narcoticCode.eq(prodDea));
        } else {
            booleanExpression = booleanExpression.and(qRxParams.narcoticCode.castToNum(Byte.class).eq(qProducts.prodDea.coalesce((byte) 0)));
        }

        AnsDefaultRxPlanParamsResult res = epostrxQueryFactory.select(Projections.bean(AnsDefaultRxPlanParamsResult.class,
                        qRxParams.narcoticCode,
                        qRxParams.numRefillsAllowed.as("numRefillsAllowed"),
                        qRxParams.numDaysFromOrigDate.as("numDaysFromOrigDate"),
                        qRxParams.numDaysExpire.as("numDaysExpire"),
                        qRxParams.maxDaysSupply.as("maxDaysSupply"),
                        qRxParams.minPctUtilization.as("minPctUtil"),
                        qRxParams.acceptFax.coalesce(VALUE_N).as("acceptFax")
                ))
                .from(qRxParams).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .leftJoin(qProducts).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qProducts.prodId.eq(prodId))
                .where(booleanExpression)
                .fetchFirst();

        if (res != null) {
            result.setNarcoticCode(res.getNarcoticCode());
            result.setNumRefillsAllowed(res.getNumRefillsAllowed());
            result.setNumDaysFromOrigDate(res.getNumDaysFromOrigDate());
            result.setNumDaysExpire(res.getNumDaysExpire());
            result.setMaxDaysSupply(res.getMaxDaysSupply());
            result.setMinPctUtil(res.getMinPctUtil());
            result.setAcceptFax(res.getAcceptFax());
        }

        return result;
    }

    /**
     * Get Payor Plans data.
     *
     * @param result the result
     * @param ppNum  the pp num
     * @return the passed in result
     */
    private AnsDefaultRxPlanParamsResult getPayorPlansData(AnsDefaultRxPlanParamsResult result, Long ppNum) {
        if (ppNum == null) {
            return result;
        }

        // Line#: 125 - 135
        QPayorPlans qPayorPlans = QPayorPlans.payorPlans;

        AnsDefaultRxPlanParamsResult res = epostrxQueryFactory.select(Projections.bean(AnsDefaultRxPlanParamsResult.class,
                        qPayorPlans.ppMaxRefillAllowed.coalesce((short) 0).as("planNumRefillsAllowed"),
                        qPayorPlans.ppMinUtilPct.coalesce(0).as("planMinPctUtil"),
                        qPayorPlans.ppMaxUtilPct.coalesce(0).as("planMaxPctUtil"),
                        qPayorPlans.ppMinQuantity.coalesce((long) 0).as("planMinQuantity"),
                        qPayorPlans.ppMaxQuantity.coalesce((long) 0).as("planMaxQuantity"),
                        qPayorPlans.ppMinDaysSupply.coalesce((short) 0).as("planMinDaysSupply"),
                        qPayorPlans.ppMaxDaysSupply.coalesce((short) 0).as("planMaxDaysSupply")
                ))
                .from(qPayorPlans).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .where(qPayorPlans.ppNum.eq(ppNum))
                .fetchFirst();

        if (res != null) {
            result.setPlanNumRefillsAllowed(res.getPlanNumRefillsAllowed());
            result.setPlanMinPctUtil(res.getPlanMinPctUtil());
            result.setPlanMaxPctUtil(res.getPlanMaxPctUtil());
            result.setPlanMinQuantity(res.getPlanMinQuantity());
            result.setPlanMaxQuantity(res.getPlanMaxQuantity());
            result.setPlanMinDaysSupply(res.getPlanMinDaysSupply());
            result.setPlanMaxDaysSupply(res.getPlanMaxDaysSupply());
        }

        return result;
    }

    /**
     * Get AnsDefaultRxPlanParamsDTO data.
     *
     * @param eScriptMsgAttributeSeq the eScript msg attribute seq.
     * @param stateNum               the state num
     * @param prodId                 the prod ID
     * @return the AnsDefaultRxPlanParamsDTO data
     */
    private List<AnsDefaultRxPlanParamsDTO> getAnsDefaultRxPlanParamsDTOs(BigInteger eScriptMsgAttributeSeq,
                                                                          Integer stateNum, Long prodId) {

        QEScriptMsgAttributes qeScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        QProducts qProducts = QProducts.products;

        QPatientAddressTypeAssignments qPatientAddressTypeAssignments =
                QPatientAddressTypeAssignments.patientAddressTypeAssignments;
        QStateCsProducts qStateCsProducts = QStateCsProducts.stateCsProducts;

        return epostrxQueryFactory.select(Projections.bean(AnsDefaultRxPlanParamsDTO.class,
                        qeScriptMsgAttributes.eScriptMsgAttributeSeq,
                        qeScriptMsgAttributes.orderInvoiceNumber,
                        qeScriptMsgAttributes.dispensedProductId,
                        qProducts.prodId,
                        qProducts.prodDea,
                        qPatientAddressTypeAssignments.patientAddrSeq,
                        qStateCsProducts.stateCsProductSeq,
                        qStateCsProducts.prodDea.as("stateCsProductProdDea"),
                        qStateCsProducts.prodId.as("stateCsProductProdId")
                ))
                .from(qeScriptMsgAttributes).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .leftJoin(qProducts).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qProducts.prodId.eq(qeScriptMsgAttributes.dispensedProductId).or(qProducts.prodId.eq(prodId)))
                .leftJoin(qPatientAddressTypeAssignments).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientAddressTypeAssignments.patientNum.eq(qeScriptMsgAttributes.patientNum)
                        .and(qPatientAddressTypeAssignments.addressTypeNum.eq(ADDRESS_TYPE_NUM_10)))
                .leftJoin(qStateCsProducts).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qStateCsProducts.prodId.eq(prodId)
                        .and(qStateCsProducts.stateNum.eq(stateNum)))
                .where(qeScriptMsgAttributes.eScriptMsgAttributeSeq.eq(eScriptMsgAttributeSeq))
                .fetch();
    }

    /**
     * Get product dea.
     * Code from file: F_getDeaProductByScriptId.sql
     *
     * @param dtos The AnsDefaultRxPlanParamsDTOs data
     * @return the product dea
     */
    private Integer getDeaProduct(List<AnsDefaultRxPlanParamsDTO> dtos) {
        // Code from file: F_getDeaProductByScriptId.sql
        if (dtos.isEmpty()) {
            return 0;
        }

        AnsDefaultRxPlanParamsDTO currDto = dtos.get(0);

        int prodDea = ofNullable(currDto.getProdDea()).orElse((byte) 0);

        // Line#: 58 - 60
        Long idSeq = dtos.stream()
                .map(AnsDefaultRxPlanParamsDTO::getPatientAddrSeq)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(0L);

        if (currDto.getOrderInvoiceNumber() != null) {
            // Line#: 68 - 72
            idSeq = dtos.stream()
                    .map(AnsDefaultRxPlanParamsDTO::getStateCsProductSeq)
                    .filter(Objects::nonNull)
                    .min(Long::compare)
                    .orElse(idSeq);

            // Line#: 75 - 82
            if (idSeq > 0) {
                prodDea = dtos.stream()
                        .map(AnsDefaultRxPlanParamsDTO::getStateCsProductProdDea)
                        .filter(Objects::nonNull)
                        .findFirst()
                        .orElse((short) 0);
            }

        }

        return prodDea;
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
}

