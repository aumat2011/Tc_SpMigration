package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.common.Helper;
import com.humana.pharmacy.common.cache.model.CShippingMethod;
import com.humana.pharmacy.common.entity.QAutoRouteRules;
import com.humana.pharmacy.common.entity.QCityStateZip;
import com.humana.pharmacy.common.entity.QEScriptMsgAttributes;
import com.humana.pharmacy.common.entity.QOrderBillship;
import com.humana.pharmacy.common.entity.QOrderHeader;
import com.humana.pharmacy.common.entity.QOrderLines;
import com.humana.pharmacy.common.entity.QPatientAddress;
import com.humana.pharmacy.common.entity.QProducts;
import com.humana.pharmacy.common.entity.QRxFillAux;
import com.humana.pharmacy.common.entity.QStateCsProducts;
import com.humana.pharmacy.common.entity.QStates;
import com.humana.pharmacy.common.service.impl.BaseServiceImpl;
import com.humana.pharmacy.constants.AnsRxProductError;
import com.humana.pharmacy.dto.AnsRxProductDTO;
import com.humana.pharmacy.dto.AnsRxProductResult;
import com.humana.pharmacy.service.AnsRxProductService;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.types.Projections;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Implementation of AnsRxProductService using QueryDSL. It extends BaseServiceImpl.
 */
public class AnsRxProductServiceImpl extends BaseServiceImpl implements AnsRxProductService {

    /**
     * The override queue name
     */
    private static final String OVERRIDE_QNAME = "DR. CALL TECH FAX";
    /**
     * The prod dea filter
     */
    private static final List<Byte> PROD_DEA_FILTER =
            Arrays.asList((byte) 2, (byte) 3, (byte) 4, (byte) 5);

    /**
     * The state prod dea filter
     */
    private static final List<Short> STATE_PROD_DEA_FILTER =
            Arrays.asList((short) 2, (short) 3, (short) 4, (short) 5);
    /**
     * The UPS ship method
     */
    private static final String UPS_SHIP_METHOD = "UPS";
    /**
     * The P.O. BOX ship address
     */
    private static final String P_O_BOX_SHIP_ADDRESS = "POBOX";
    /**
     * The ny state code
     */
    private static final String STATE_CODE_NY = "NY";
    /**
     * The TX state code
     */
    private static final String STATE_CODE_TX = "TX";
    /**
     * The default value of state code
     */
    private static final String DEFAULT_STATE_CODE = "XX";
    /**
     * The gpi filter
     */
    private static final String GPI = "GPI";
    /**
     * The gpi class filter
     */
    private static final String GPI_CLASS = "GPICLASS";
    /**
     * The active flag
     */
    private static final String ACTIVE_FLAG = "Y";

    /**
     * Constructor of AnsRxProductServiceImpl.
     *
     * @param epostrxDataSource  DataSource for epostrx database. Required
     * @param workflowDataSource DataSource for epostrx_workflow database. Required
     * @throws RuntimeException if epostrxDataSource or workflowDataSource is null
     */
    public AnsRxProductServiceImpl(DataSource epostrxDataSource, DataSource workflowDataSource) {
        super(epostrxDataSource, workflowDataSource);
    }

    /**
     * Sanity check for customers to add their own custom product level checks. eturns null if no
     * hits, a message(s) if there are hits. Code from file: P_ansrxproductcheck.sql
     *
     * @param scriptId The scriptId. Required.
     * @return product level check result. Will return null if no errors found.
     */
    public AnsRxProductResult checkAnsRxProduct(BigInteger scriptId) {
        Helper.checkNull(scriptId, "scriptId");

        // Get data
        List<AnsRxProductDTO> ansRxProductDTOs = getAnsRxProductDTOs(scriptId);
        if (ansRxProductDTOs.isEmpty()) {
            return null;
        }

        AnsRxProductDTO currDTO = ansRxProductDTOs.get(0);

        // Line# 149 - 163
        boolean activePobox =
                isActivePobox(
                        currDTO.getDispensedProductId(),
                        currDTO.getProdGenericRefNum(),
                        currDTO.getStateCode());

        // Line: 182 - 188
        // get shipping method
        String shipMethodDesc =
                getShippingMethods().stream()
                        .filter(e -> Objects.equals(currDTO.getShipMethodId(), e.getShipMethodId()))
                        .map(CShippingMethod::getShipMethodDesc)
                        .findFirst()
                        .orElse("");
        shipMethodDesc = shipMethodDesc.replaceAll("[\\. ]", "");

        AnsRxProductResult result = new AnsRxProductResult();
        // Line#: 199 - 226
        // any COLDPAK or COntrolled Substance cannot be shipped to POBOX
        if (currDTO.getTargetValue() != null
                || Objects.equals(currDTO.getProdDea(), (byte) 2)
                || Objects.equals(currDTO.getStateCsProductsProdDea(), (short) 2)
                || activePobox
                || shipMethodDesc.contains(UPS_SHIP_METHOD)) {
            String shipAddress =
                    (currDTO.getPatientAddress() + currDTO.getPatientAddress2()).replaceAll("[\\. ]", "");

            if (shipAddress.toUpperCase().contains(P_O_BOX_SHIP_ADDRESS)) {
                result.addError(AnsRxProductError.ITEM_CANNOT_BE_SHIPPED_TO_POBOX);
                return result;
            }
        }

        // Line#: 232 - 253
        if (STATE_CODE_NY.equals(currDTO.getStateCode())
                && (PROD_DEA_FILTER.contains(currDTO.getProdDea())
                || STATE_PROD_DEA_FILTER.contains(currDTO.getStateCsProductsProdDea()))) {
            if (ansRxProductDTOs.stream().noneMatch(e -> e.getTriplicateSerialNum() != null)) {
                result.addError(AnsRxProductError.STATE_REQ_NY_TRIPLICATE_SERIAL_NUM_ON_FILE);
                result.setOverrideQname(OVERRIDE_QNAME);
                result.setOverrideQueue(
                        "" + getWorkflowQueueNumber(result.getOverrideQname(), currDTO.getTradingPartnerNum()));
            }
        }

        // Line#: 254 - 275
        if (STATE_CODE_TX.equals(currDTO.getStateCode())
                && (Objects.equals((byte) 2, currDTO.getProdDea())
                || Objects.equals((short) 2, currDTO.getStateCsProductsProdDea()))) {
            if (ansRxProductDTOs.stream().noneMatch(e -> e.getTriplicateSerialNum() != null)) {
                result.addError(AnsRxProductError.STATE_REQ_TX_TRIPLICATE_SERIAL_NUM_ON_FILE);
                result.setOverrideQname(OVERRIDE_QNAME);
                result.setOverrideQueue(
                        "" + getWorkflowQueueNumber(result.getOverrideQname(), currDTO.getTradingPartnerNum()));
            }
        }

        return result.getErrors().isEmpty() ? null : result;
    }

    /**
     * Check if it's active pobox.
     *
     * @param disProdId the dispensed prod id
     * @param gpi       the gpi
     * @param shipState the ship state
     * @return true if it's active pobox; false otherwise.
     */
    private Boolean isActivePobox(Long disProdId, String gpi, String shipState) {
        gpi = ofNullable(gpi).orElse("");

        // Line# 149 - 163
        QStates qStates = QStates.states;
        QProducts qProducts = QProducts.products;
        QStateCsProducts qStateCsProducts = QStateCsProducts.stateCsProducts;
        String gpiClass = Helper.substring(gpi, 10);

        return epostrxQueryFactory
                .query()
                .from(qStateCsProducts)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .join(qProducts)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qProducts.prodId.eq(qStateCsProducts.prodId))
                .join(qStates)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qStates.stateNum.eq(qStateCsProducts.stateNum))
                .where(
                        qStates
                                .stateCode
                                .eq(shipState)
                                .and(qStateCsProducts.prodDea.in((short) 3, (short) 4, (short) 5))
                                .and(qStateCsProducts.poboxActive.eq(ACTIVE_FLAG))
                                .or(
                                        qProducts
                                                .prodGenericRefNum
                                                .eq(gpi)
                                                .and(qStateCsProducts.prodFilter.eq(GPI))
                                                .or(
                                                        qProducts
                                                                .prodGenericRefNum
                                                                .like("%" + gpiClass + "%")
                                                                .and(qStateCsProducts.prodFilter.eq(GPI_CLASS)))
                                                .or(qProducts.prodId.eq(disProdId))))
                .fetchCount()
                > 0;
    }

    /**
     * Collect AnsRxProductDTO data.
     *
     * @param scriptId the script id
     * @return the list of AnsRxProductDTO
     */
    private List<AnsRxProductDTO> getAnsRxProductDTOs(BigInteger scriptId) {
        QEScriptMsgAttributes qeScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        QOrderLines qOrderLines = QOrderLines.orderLines;
        QOrderBillship qOrderBillship = QOrderBillship.orderBillship;
        QPatientAddress qPatientAddress = QPatientAddress.patientAddress1;
        QCityStateZip qCityStateZip = QCityStateZip.cityStateZip;
        QStates qStates = QStates.states;
        QStates qStates2 = new QStates("states2");
        QProducts qProducts = QProducts.products;
        QStateCsProducts qStateCsProducts = QStateCsProducts.stateCsProducts;
        QAutoRouteRules qAutoRouteRules = QAutoRouteRules.autoRouteRules;
        QOrderHeader qOrderHeader = QOrderHeader.orderHeader;
        QPatientAddress qPatientAddress2 = new QPatientAddress("patientAddress2");
        QOrderBillship qOrderBillship2 = new QOrderBillship("orderBillship2");
        QRxFillAux qRxFillAux = QRxFillAux.rxFillAux;

        return epostrxQueryFactory
                .select(
                        Projections.bean(
                                AnsRxProductDTO.class,
                                qeScriptMsgAttributes.eScriptMsgAttributeSeq,
                                qeScriptMsgAttributes.dispensedProductId,
                                qeScriptMsgAttributes.tradingPartnerNum,
                                qRxFillAux.triplicateSerialNum,
                                qStates.stateCode.coalesce("XX").as("stateCode"),
                                qProducts.prodDea,
                                qProducts.prodGenericRefNum,
                                qStateCsProducts.prodDea.coalesce((short) 0).as("stateCsProductsProdDea"),
                                qAutoRouteRules.targetValue,
                                qPatientAddress2.patientAddress.coalesce("N").as("patientAddress"),
                                qPatientAddress2.patientAddress2.coalesce("N").as("patientAddress2"),
                                qOrderBillship2.shipMethodId))
                .from(qeScriptMsgAttributes)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                // Line: 237 - 240, 259 - 262
                .leftJoin(qRxFillAux)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillAux.eScriptMsgAttributeSeq.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq))
                // Line: 116 - 129
                .leftJoin(qOrderLines)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(
                        qOrderLines
                                .eScriptMsgAttributeSeq
                                .eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq)
                                .and(qOrderLines.orderLineStatusNum.eq((byte) 1)))
                .leftJoin(qOrderBillship)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderBillship.orderNum.eq(qOrderLines.orderNum))
                .leftJoin(qPatientAddress)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientAddress.patientAddrSeq.eq(qOrderBillship.patientShipAddressSeq))
                .leftJoin(qCityStateZip)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qCityStateZip.cszZipNum.eq(qPatientAddress.cszZipNum))
                .leftJoin(qStates)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qStates.stateNum.eq(qCityStateZip.stateNum))
                // Line: 134 - 135
                .leftJoin(qStates2)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qStates2.stateCode.eq(qStates.stateCode.coalesce(DEFAULT_STATE_CODE)))
                .leftJoin(qStateCsProducts)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(
                        qStateCsProducts
                                .prodId
                                .eq(qeScriptMsgAttributes.dispensedProductId)
                                .and(qStateCsProducts.stateNum.eq(qStates2.stateNum)))
                // Line: 137, 147
                .leftJoin(qProducts)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qProducts.prodId.eq(qeScriptMsgAttributes.dispensedProductId))
                .leftJoin(qAutoRouteRules)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(
                        qAutoRouteRules
                                .targetValue
                                .eq(qProducts.prodNumber)
                                .and(qAutoRouteRules.priority.eq(300)))
                // Line: 168 - 180
                .leftJoin(qOrderHeader)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(
                        qOrderHeader
                                .orderInvoiceNumber
                                .eq(qeScriptMsgAttributes.orderInvoiceNumber)
                                .and(qOrderHeader.tradingPartnerNum.eq(qeScriptMsgAttributes.tradingPartnerNum)))
                .leftJoin(qOrderBillship2)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderBillship2.orderNum.eq(qOrderHeader.orderNum))
                .leftJoin(qPatientAddress2)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientAddress2.patientAddrSeq.eq(qOrderBillship2.patientShipAddressSeq))
                .where(qeScriptMsgAttributes.eScriptMsgAttributeSeq.eq(scriptId))
                .fetch();
    }
}
