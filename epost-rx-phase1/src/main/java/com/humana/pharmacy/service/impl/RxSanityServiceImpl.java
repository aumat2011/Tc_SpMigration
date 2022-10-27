package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.dto.RxSanityResult;
import com.humana.pharmacy.service.RxSanityService;
import com.humana.pharmacy.common.Helper;
import com.humana.pharmacy.common.dto.RxEScriptDTO;
import com.humana.pharmacy.common.entity.QCityStateZip;
import com.humana.pharmacy.common.entity.QEScriptMsgAttributes;
import com.humana.pharmacy.common.entity.QOKHydrocodoneSanityProducts;
import com.humana.pharmacy.common.entity.QOrderBillship;
import com.humana.pharmacy.common.entity.QOrderLines;
import com.humana.pharmacy.common.entity.QPatientAddress;
import com.humana.pharmacy.common.entity.QProducts;
import com.humana.pharmacy.common.entity.QStates;
import com.humana.pharmacy.common.service.FunctionsService;
import com.humana.pharmacy.common.service.impl.BaseServiceImpl;
import com.humana.pharmacy.constants.RxSanityError;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.Tuple;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of RxSanityService using QueryDSL. It extends BaseServiceImpl.
 */
public class RxSanityServiceImpl extends BaseServiceImpl implements RxSanityService {
    /**
     * The join flag ' WITH (NOLOCK) '.
     */
    private static final String JOIN_FLAG_NOLOCK = " WITH (NOLOCK) ";

    /**
     * The shipping state 'OK'.
     */
    private static final String SHIPPING_STATE_OK = "OK";

    /**
     * The order line status number '1'.
     */
    private static final byte OL_STATUS_1 = (byte) 1;

    /**
     * The constant replaced gpi null value.
     */
    private static final String GPI_NULL_REPLACEMENT = "XX";

    /**
     * Reference to FunctionsService
     */
    private FunctionsService functionsService;

    /**
     * Constructor of RxSanityServiceImpl.
     *
     * @param epostrxDataSource  DataSource for epostrx database. Required
     * @param workflowDataSource DataSource for epostrx_workflow database. Required
     * @throws RuntimeException if epostrxDataSource or workflowDataSource is null
     */
    public RxSanityServiceImpl(DataSource epostrxDataSource, DataSource workflowDataSource) {
        super(epostrxDataSource, workflowDataSource);
    }

    /**
     * Check if the string is null/empty.
     *
     * @param str the string
     * @return <code>true</code> if the string is null/empty; <code>false</code> otherwise.
     */
    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * Check Rx sanity.
     *
     * <p>Code from file: OK_rxsanitycheck.sql
     *
     * @param scriptId The scriptId. Required
     * @param rxNumber The rxNumber.
     * @return Rx sanity check result. Will return NULL if no errors found.
     * @throws RuntimeException if the scriptId is null or functionsService is null (not configured)
     */
    public RxSanityResult checkRxSanity(BigInteger scriptId, String rxNumber) {
        Helper.checkNull(scriptId, "scriptId");
        Helper.checkNull(functionsService, "functionsService");

        RxSanityResult rxSanityResult = null;

        CompletableFuture<Tuple> tupleFuture = CompletableFuture.supplyAsync(() -> getShippingStateAndRxNumber(scriptId));
        if (isNullOrEmpty(rxNumber)) {
            rxNumber = Optional.ofNullable(tupleFuture.join()).map(t -> t.get(1, String.class)).orElse(null);
        }
        if (!isNullOrEmpty(rxNumber)) {
            String finalRxNumber = rxNumber;
            CompletableFuture<List<RxEScriptDTO>> rxEScriptDTOs = CompletableFuture.supplyAsync(() -> getRxEScripts(finalRxNumber));
            Tuple tuple = tupleFuture.join();
            String shippingState = tuple.get(0, String.class);
            Boolean isGpiOk = !GPI_NULL_REPLACEMENT.equals(tuple.get(2, String.class));

            // Line # : 143 - 160
            if (SHIPPING_STATE_OK.equalsIgnoreCase(shippingState) && isGpiOk) {
                Integer remainingRefills = functionsService.getRemainingRefills(rxNumber, rxEScriptDTOs.join());
                if (remainingRefills != null && remainingRefills.compareTo(1) > 0) {
                    rxSanityResult = new RxSanityResult();
                    rxSanityResult.getErrors().add(RxSanityError.HYDROCODONE_PRODUCT_WITH_REFILLS);
                }
            }
        }
        return rxSanityResult;
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
     * Get shipping state and Rx number by script ID.
     *
     * @param scriptId The scriptId. Required
     * @return Shipping state and Rx number
     */
    private Tuple getShippingStateAndRxNumber(BigInteger scriptId) {
        // Line # : 123 - 137 of OK_rxsanitycheck.sql
        QEScriptMsgAttributes qEScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        QOrderLines qOrderLine = QOrderLines.orderLines;
        QOrderBillship qOrderBillship = QOrderBillship.orderBillship;
        QPatientAddress qPatientAddress = QPatientAddress.patientAddress1;
        QCityStateZip qCityStateZip = QCityStateZip.cityStateZip;
        QStates qStates = QStates.states;
        QProducts qProducts = QProducts.products;
        QOKHydrocodoneSanityProducts qOKHydrocodoneSanityProducts = QOKHydrocodoneSanityProducts.OKHydrocodoneSanityProducts;

        return epostrxQueryFactory.select(qStates.stateCode, qEScriptMsgAttributes.rxNumber,
                        qOKHydrocodoneSanityProducts.gpi.coalesce(GPI_NULL_REPLACEMENT).as("gpi"))
                .from(qEScriptMsgAttributes).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .join(qOrderLine).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qEScriptMsgAttributes.eScriptMsgAttributeSeq.eq(qOrderLine.eScriptMsgAttributeSeq))
                .join(qOrderBillship).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderBillship.orderNum.eq(qOrderLine.orderNum))
                .join(qPatientAddress).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientAddress.patientAddrSeq.eq(qOrderBillship.patientShipAddressSeq))
                .join(qCityStateZip).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qCityStateZip.cszZipNum.eq(qPatientAddress.cszZipNum))
                .join(qStates).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qStates.stateNum.eq(qCityStateZip.stateNum))
                .join(qProducts).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qProducts.prodId.eq(qEScriptMsgAttributes.dispensedProductId))
                .leftJoin(qOKHydrocodoneSanityProducts)
                .on(qOKHydrocodoneSanityProducts.gpi.eq(qProducts.prodGenericRefNum))
                .where(qEScriptMsgAttributes.eScriptMsgAttributeSeq.eq(scriptId)
                        .and(qOrderLine.orderLineStatusNum.eq(OL_STATUS_1)))
                .fetchFirst();
    }
}

