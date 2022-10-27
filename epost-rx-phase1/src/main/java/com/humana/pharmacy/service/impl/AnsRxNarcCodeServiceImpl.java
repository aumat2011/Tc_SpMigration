package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.service.AnsRxNarcCodeService;
import com.humana.pharmacy.common.Helper;
import com.humana.pharmacy.common.dto.AnsRxNarcCodeEScriptDTO;
import com.humana.pharmacy.common.entity.QCityStateZip;
import com.humana.pharmacy.common.entity.QEScriptMsgAttributes;
import com.humana.pharmacy.common.entity.QOrderBillship;
import com.humana.pharmacy.common.entity.QOrderHeader;
import com.humana.pharmacy.common.entity.QPatientAddress;
import com.humana.pharmacy.common.entity.QProducts;
import com.humana.pharmacy.common.entity.QRxFillAux;
import com.humana.pharmacy.common.entity.QRxParams;
import com.humana.pharmacy.common.entity.QRxParamsGpis;
import com.humana.pharmacy.common.entity.QStateCsProducts;
import com.humana.pharmacy.common.entity.QStates;
import com.humana.pharmacy.common.entity.QTradingPartnerAddress;
import com.humana.pharmacy.common.service.FunctionsService;
import com.humana.pharmacy.common.service.impl.BaseServiceImpl;
import com.humana.pharmacy.constants.AnsRxNarcCodeError;
import com.humana.pharmacy.dto.AnsRxNarcCodeResult;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.sql.SQLExpressions;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Implementation of AnsRxNarcCodeService using QueryDSL. It extends BaseServiceImpl.
 */
public class AnsRxNarcCodeServiceImpl extends BaseServiceImpl implements AnsRxNarcCodeService {
    /**
     * The join flag ' WITH (NOLOCK) '.
     */
    private static final String JOIN_FLAG_NOLOCK = " WITH (NOLOCK) ";

    /**
     * The string 'LA'.
     */
    private static final String SHIP_STATE_LA = "LA";

    /**
     * The string 'NEWRX'.
     */
    private static final String VALUE_NEWRX = "NEWRX";

    /**
     * The string 'REFILLRX'.
     */
    private static final String VALUE_REFILLRX = "REFILLRX";

    /**
     * The string 'DEFAULT SETTINGS'.
     */
    private static final String VALUE_DEFAULT_SETTINGS = "DEFAULT SETTINGS:";

    /**
     * The string 'Y'.
     */
    private static final String VALUE_Y = "Y";

    /**
     * The string 'N'.
     */
    private static final String VALUE_N = "N";

    /**
     * The fill status numbers '2' and '9'.
     */
    private static final List<Byte> FILL_STATUS_NUMS_1 = Arrays.asList((byte) 2, (byte) 9);

    /**
     * The fill status numbers '2', '5' and '9'.
     */
    private static final List<Byte> FILL_STATUS_NUMS_2 = Arrays.asList((byte) 2, (byte) 5, (byte) 9);

    /**
     * The numbers '2', '3'.
     */
    private static final List<Short> PROD_DEAS_1 = Arrays.asList((short) 2, (short) 3);

    /**
     * The numbers '2', '3', '4', '5'.
     */
    private static final List<Short> PROD_DEAS_2 = Arrays.asList((short) 2, (short) 3, (short) 4, (short) 5);

    /**
     * The index of tuple element 'eScriptMsgAttributeSeq'.
     */
    private static final int INDEX_ESCRIPT_MSG_ATTRIBUTE_SEQ = 0;

    /**
     * The index of tuple element 'fillDaysSupply'.
     */
    private static final int INDEX_FILL_DAYS_SUPPLY = 1;

    /**
     * The index of tuple element 'fillStatusNum'.
     */
    private static final int INDEX_FILL_STATUS_NUM = 2;

    /**
     * The index of tuple element 'ediTransactionCode'.
     */
    private static final int INDEX_EDI_TRANSACTION_CODE = 3;

    /**
     * The number '4'.
     */
    private static final int NUM_4 = 4;

    /**
     * The number '5'.
     */
    private static final int NUM_5 = 5;

    /**
     * Reference to FunctionsService.
     */
    private FunctionsService functionsService;

    /**
     * Constructor of AnsRxNarcCodeServiceImpl.
     *
     * @param workflowDataSource DataSource for epostrx_workflow database. Required
     * @param epostrxDataSource  DataSource for epostrx database. Required
     * @throws RuntimeException if epostrxDataSource or workflowDataSource is null
     */
    public AnsRxNarcCodeServiceImpl(DataSource epostrxDataSource, DataSource workflowDataSource) {
        super(epostrxDataSource, workflowDataSource);
    }

    /**
     * Check narcotic code by scriptId.
     * <p>Code from file: P_ansrxnarccodecheck.sql
     *
     * @param scriptId The scriptId. Required
     * @param rxNumber The rxNumber. Optional
     * @return Narcotic code check result. Will return null if no errors found.
     * @throws RuntimeException if the scriptId is null,  or functionsService is null (not configured)
     */
    public AnsRxNarcCodeResult checkAnsRxNarcCode(BigInteger scriptId, String rxNumber) {
        // Line: 113 ensure we have the correct params passed in
        Helper.checkNull(scriptId, "scriptId");
        Helper.checkNull(functionsService, "functionsService");

        CompletableFuture<List<AnsRxNarcCodeEScriptDTO>> escriptsFuture = CompletableFuture.supplyAsync(() -> getAnsRxNarcCodeEScripts(scriptId));
        if (rxNumber == null || rxNumber.isEmpty()) {
            rxNumber = escriptsFuture.join().stream().map(e -> e.getRxNumber()).filter(e -> e != null).findFirst().orElse("");
        }
        String finalRxNumber = rxNumber;
        CompletableFuture<Integer> daysSupply = CompletableFuture.supplyAsync(() -> getDaysSupply(finalRxNumber));
        // get scripts info
        List<AnsRxNarcCodeEScriptDTO> escripts = escriptsFuture.join();

        if (escripts.isEmpty()) {
            return null;
        }
        AnsRxNarcCodeEScriptDTO orderNarcCode = functionsService.getOrderNarcCode(escripts);
        AnsRxNarcCodeEScriptDTO eScriptDTO = escripts.get(0);
        // orders with scriptId haven't narc code

        AnsRxNarcCodeResult result = new AnsRxNarcCodeResult();
        AnsRxNarcCodeError err;

        // Line: 153
        boolean narcoticCheck = VALUE_Y.equals(getRxDefaults().getCheckNarcoticRefills());

        // Line: 155 - 176

        // Line: 180 - 182
        String gpiClass = functionsService.getGPIClass(escripts);

        // Line: 252, 354
        Byte prodDea = functionsService.getDeaProduct(orderNarcCode, escripts);

        // get state name
        String stateName = ofNullable(orderNarcCode).flatMap(or -> ofNullable(or.getStateName())).orElse(VALUE_DEFAULT_SETTINGS);

        // check if a state restricted GPI
        boolean isRestrictedGPI = escripts.stream()
                .anyMatch(es -> orderNarcCode != null && Objects.equals(gpiClass, es.getGpiClassId())
                        && Objects.equals(es.getStateNum(), orderNarcCode.getStateNum()));

        if (isRestrictedGPI) {
            if (VALUE_NEWRX.equals(eScriptDTO.getEdiTransactionCode()) ||
                    (VALUE_REFILLRX.equals(eScriptDTO.getEdiTransactionCode()) && narcoticCheck)) {

                int narcNumreFills = isNull(eScriptDTO.getNarcNumRefillsGpi(), 0);
                // check num fills
                if (eScriptDTO.getNumRefills() != null && eScriptDTO.getNumRefills() > narcNumreFills) {
                    result.addError(AnsRxNarcCodeError.STATE_GPI_CLASS_LIMITS_NUMBER_REFILLS
                            .withParams(stateName, narcNumreFills, eScriptDTO.getNumRefills()));
                }
                // check num days original date
                if (VALUE_NEWRX.equals(eScriptDTO.getEdiTransactionCode())) {
                    err = checkNumDaysOriginalDate(eScriptDTO, true, escripts, prodDea.toString(), AnsRxNarcCodeError.STATE_GPI_CLASS_LIMITS_DAYS_ORIGINAL);
                    if (err != null) {
                        result.addError(err);
                    }
                }
                // check days expire
                checkDaysExpire(eScriptDTO, true, escripts, prodDea.toString());

                // check days supply
                if (eScriptDTO.getNarcNumDaysSupplyGpi() != null
                        && daysSupply.join() > eScriptDTO.getNarcNumDaysSupplyGpi()) {
                    result.addError(AnsRxNarcCodeError.STATE_GPI_CLASS_LIMITS_DAYS_SUPPLY
                            .withParams(stateName, eScriptDTO.getNarcNumDaysSupplyGpi(), daysSupply.join()));
                }
            }
            // fax accept only for Controls and origination is fax
            if (Objects.equals(eScriptDTO.getOriginationNum(), NUM_4)
                    && VALUE_N.equals(isNull(eScriptDTO.getNarcAcceptFaxGpi(), VALUE_N))) {
                result.addError(AnsRxNarcCodeError.STATE_GPI_CLASS_PROHIBITS_ELECTRONIC
                        .withParams(stateName));
            }
        } else { // isRestrictedGPI = false
            if (VALUE_NEWRX.equals(eScriptDTO.getEdiTransactionCode()) ||
                    (VALUE_REFILLRX.equals(eScriptDTO.getEdiTransactionCode()) && narcoticCheck)) {

                // Louisiana Sanity Check issue
                err = checkLouisianaSanity(eScriptDTO, escripts);
                if (err != null) {
                    result.addError(err);
                }

                // check num fills
                // Line: 392 - 398
                Integer narcNumreFills = escripts.stream()
                        .filter(e -> e.getNarcNumRefills() != null)
                        .filter(e -> e.getNarcoticCode().equals(prodDea.toString()))
                        .map(e -> e.getNarcNumRefills()).findFirst().orElse(null);
                if (narcNumreFills != null && eScriptDTO.getNumRefills() != null && eScriptDTO.getNumRefills() > narcNumreFills) {
                    result.addError(AnsRxNarcCodeError.STATE_LAW_LIMITS_NUMBER_REFILLS
                            .withParams(stateName, narcNumreFills, eScriptDTO.getNumRefills()));
                }

                // check num days original date
                if (VALUE_NEWRX.equals(eScriptDTO.getEdiTransactionCode())) {
                    err = checkNumDaysOriginalDate(eScriptDTO, false, escripts, prodDea.toString(), AnsRxNarcCodeError.STATE_LAW_LIMITS_DAYS_ORIGINAL);
                    if (err != null) {
                        result.addError(err);
                    }
                }
                // check days expire
                checkDaysExpire(eScriptDTO, false, escripts, prodDea.toString());
                Integer narcNumDaysSupply = escripts.stream().filter(e -> prodDea.toString().equals(e.getNarcoticCode()))
                        .filter(e -> e.getNarcNumDaysSupply() != null)
                        .map(e -> e.getNarcNumDaysSupply()).findFirst().orElse(null);

                // check days supply
                if (narcNumDaysSupply != null && daysSupply.join() > narcNumDaysSupply) {
                    result.addError(AnsRxNarcCodeError.STATE_LAW_LIMITS_DAYS_SUPPLY
                            .withParams(stateName, narcNumDaysSupply, daysSupply.join()));
                }
            }
            // ok now set global flag to indicate if control or not
            boolean isControlRx = PROD_DEAS_2.contains(Short.valueOf(prodDea));

            // fax accept only for Controls and origination is fax
            if (Objects.equals(eScriptDTO.getOriginationNum(), NUM_4)
                    && isControlRx && VALUE_N.equals(isNull(eScriptDTO.getNarcAcceptFax(), VALUE_N))) {
                result.addError(AnsRxNarcCodeError.STATE_LAW_PROHIBITS_ELECTRONIC.withParams(stateName));
            }
        }

        return result.getErrors().isEmpty() ? null : result;
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
     * Check num days original date.
     *
     * @param orderNarcCode the order narc code
     * @param escripts      the escripts
     * @param prodDea       the prod dea
     * @param error         the error
     * @return the ans rx narc code error
     */
    private AnsRxNarcCodeError checkNumDaysOriginalDate(AnsRxNarcCodeEScriptDTO orderNarcCode, boolean isRestrictedGPI,
                                                        List<AnsRxNarcCodeEScriptDTO> escripts, String prodDea, AnsRxNarcCodeError error) {
        // Line: 251 - 268, 404 - 418
        Integer narcNumDaysOriginal = ofNullable(orderNarcCode.getNarcNumDaysOriginalGpi())
                .filter(days -> isRestrictedGPI)
                .filter(days -> days != 0)
                .orElseGet(() -> escripts.stream()
                        .filter(es -> prodDea.equals(es.getNarcoticCode()))
                        .map(es -> {
                            return isNull(es.getNarcNumDaysOriginal(), 0);
                        })
                        .findFirst()
                        .orElse(isRestrictedGPI ? 0 : null));

        if (orderNarcCode.getWrittenDate() != null && narcNumDaysOriginal != null) {
            Long daysBetween = Duration.between(orderNarcCode.getWrittenDate().toInstant(),
                    new Date().toInstant()).toDays();

            if (daysBetween > narcNumDaysOriginal) {
                return error.withParams(isNull(orderNarcCode.getStateName(), VALUE_DEFAULT_SETTINGS), narcNumDaysOriginal, daysBetween);
            }
        }
        return null;
    }

    /**
     * Check days expire and update expiration date.
     *
     * @param orderNarcCode   the order narcotic code
     * @param isRestrictedGPI the restricted GPI flag
     */
    private void checkDaysExpire(AnsRxNarcCodeEScriptDTO orderNarcCode, boolean isRestrictedGPI,
                                 List<AnsRxNarcCodeEScriptDTO> escripts, String prodDea) {
        Integer narcNumDaysExpire = isRestrictedGPI ? orderNarcCode.getNarcNumDaysExpireGpi()
                : escripts.stream().filter(e -> e.getNarcNumDaysExpire() != null && prodDea.equals(e.getNarcoticCode()))
                .map(e -> e.getNarcNumDaysExpire()).findFirst().orElse(null);
        if (orderNarcCode.getWrittenDate() == null || orderNarcCode.getRxExpirationDate() == null || narcNumDaysExpire == null) {
            return;
        }

        // Line: 275 - 287, 423 - 431
        LocalDateTime writtenDate = orderNarcCode.getWrittenDate().toLocalDateTime();
        LocalDateTime rxExpirationDate = orderNarcCode.getRxExpirationDate().toLocalDateTime();
        LocalDateTime calcUpdateDate = writtenDate.plusDays(narcNumDaysExpire);
        // if exceed flag error
        if (rxExpirationDate.isAfter(calcUpdateDate)) {
            // we're just updating the exp date FOR NARCS now versus reporting it back as an exception
            // not sure we want that for GPI exceptions, so we will still flag them
            QEScriptMsgAttributes qeScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
            epostrxQueryFactory
                    .update(qeScriptMsgAttributes)
                    .set(qeScriptMsgAttributes.rxExpirationDate, Timestamp.valueOf(calcUpdateDate))
                    .where(qeScriptMsgAttributes.eScriptMsgAttributeSeq.eq(orderNarcCode.getEScriptMsgAttributeSeq()))
                    .execute();
        }
    }

    /**
     * Check louisiana sanity.
     *
     * @param orderNarcCode the order narc code
     * @param escripts      the escripts
     * @return the ans rx narc code error
     */
    private AnsRxNarcCodeError checkLouisianaSanity(AnsRxNarcCodeEScriptDTO orderNarcCode,
                                                    List<AnsRxNarcCodeEScriptDTO> escripts) {
        String shipState = orderNarcCode.getStateCode();
        String physicianState = orderNarcCode.getTpaStateCode();

        // Louisiana Sanity Check issue
        Byte federalProdDea = escripts.stream()
                .map(AnsRxNarcCodeEScriptDTO::getProdDea)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse((byte) 0);
        Short stateLevelProdDea = escripts.stream()
                .map(AnsRxNarcCodeEScriptDTO::getScpProdDea)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse((short) 0);

        // Line: 373 - 380
        if ((PROD_DEAS_1.contains(federalProdDea.shortValue()) || PROD_DEAS_1.contains(stateLevelProdDea))
                && SHIP_STATE_LA.equals(shipState) && !SHIP_STATE_LA.equals(physicianState)) {
            return AnsRxNarcCodeError.STATE_LA_DOCTOR_RULE;
        }
        return null;
    }

    /**
     * Collect ans rx escripts by script id.
     *
     * @param scriptId the script id
     * @return the list of AnsRxNarcCodeEScriptDTO
     */
    private List<AnsRxNarcCodeEScriptDTO> getAnsRxNarcCodeEScripts(BigInteger scriptId) {
        QEScriptMsgAttributes qeScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        QProducts qProducts = QProducts.products;
        QStateCsProducts qStateCsProducts = QStateCsProducts.stateCsProducts;
        QOrderHeader qOrderHeader = QOrderHeader.orderHeader;
        QOrderBillship qOrderBillship = QOrderBillship.orderBillship;
        QPatientAddress qPatientAddress = QPatientAddress.patientAddress1;
        QCityStateZip qCityStateZip = QCityStateZip.cityStateZip;
        QCityStateZip qCityStateZip1 = new QCityStateZip("cityStateZip1");
        QStates qStates = QStates.states;
        QStates qStates1 = new QStates("states1");
        QRxParams qRxParams = QRxParams.rxParams;
        QRxParamsGpis qRxParamsGpis = QRxParamsGpis.rxParamsGpis;
        QTradingPartnerAddress qTradingPartnerAddress = QTradingPartnerAddress.tradingPartnerAddress;

        SimpleExpression<String> tpaStateCode =
                SQLExpressions.select(qStates1.stateCode)
                        .from(qTradingPartnerAddress).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                        .join(qCityStateZip1).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                        .on(qCityStateZip1.cszZipNum.eq(qTradingPartnerAddress.cszZipNum))
                        .join(qStates1).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                        .on(qStates1.stateNum.eq(qCityStateZip1.stateNum))
                        .where(qTradingPartnerAddress.tradingPartnerNum.eq(qeScriptMsgAttributes.doctorNum)
                                .and(qTradingPartnerAddress.active.eq(VALUE_Y)))
                        .limit(1)
                        .as("tpaStateCode");

        List<AnsRxNarcCodeEScriptDTO> list = epostrxQueryFactory.select(
                        Projections.bean(AnsRxNarcCodeEScriptDTO.class,
                                qeScriptMsgAttributes.eScriptMsgAttributeSeq,
                                qeScriptMsgAttributes.ediTransactionCode,
                                qeScriptMsgAttributes.rxExpirationDate,
                                qeScriptMsgAttributes.numRefills,
                                qeScriptMsgAttributes.writtenDate,
                                qeScriptMsgAttributes.originationNum,
                                qeScriptMsgAttributes.orderInvoiceNumber,
                                qeScriptMsgAttributes.rxNumber,

                                qProducts.prodGenericRefNum,
                                qStateCsProducts.stateCsProductSeq,
                                qStateCsProducts.prodDea.coalesce((short) 0).as("scpProdDea"),
                                qProducts.prodDea,

                                qOrderHeader.orderNum,
                                qCityStateZip.stateNum,
                                qRxParams.narcoticCode.trim().as("narcoticCode"),
                                qRxParams.numRefillsAllowed.coalesce(0).as("narcNumRefills"),
                                qRxParams.numDaysExpire.coalesce(0).as("narcNumDaysExpire"),
                                qRxParams.maxDaysSupply.coalesce(0).as("narcNumDaysSupply"),
                                qRxParams.numDaysFromOrigDate.coalesce(0).as("narcNumDaysOriginal"),
                                qRxParams.acceptFax.coalesce(VALUE_N).as("narcAcceptFax"),

                                qStates.stateName,
                                qStates.stateCode,
                                tpaStateCode,

                                qRxParamsGpis.gpiClassId,
                                qRxParamsGpis.numRefillsAllowed.as("narcNumRefillsGpi"),
                                qRxParamsGpis.numDaysExpire.as("narcNumDaysExpireGpi"),
                                qRxParamsGpis.maxDaysSupply.as("narcNumDaysSupplyGpi"),
                                qRxParamsGpis.numDaysFromOrigDate.coalesce(0).as("narcNumDaysOriginalGpi"),
                                qRxParamsGpis.acceptFax.coalesce(VALUE_N).as("narcAcceptFaxGpi")
                        )
                )
                .from(qeScriptMsgAttributes).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)

                .leftJoin(qProducts).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qProducts.prodId.eq(qeScriptMsgAttributes.dispensedProductId))

                .leftJoin(qOrderHeader).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderHeader.orderInvoiceNumber.eq(qeScriptMsgAttributes.orderInvoiceNumber))
                .leftJoin(qOrderBillship).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderBillship.orderNum.eq(qOrderHeader.orderNum))
                .leftJoin(qPatientAddress).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientAddress.patientAddrSeq.eq(qOrderBillship.patientShipAddressSeq))
                .leftJoin(qCityStateZip).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qCityStateZip.cszZipNum.eq(qPatientAddress.cszZipNum))
                .leftJoin(qStates).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qStates.stateNum.eq(qCityStateZip.stateNum))

                .leftJoin(qStateCsProducts).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qStateCsProducts.prodId.eq(qProducts.prodId).and(qStateCsProducts.stateNum.eq(qCityStateZip.stateNum)))
                .leftJoin(qRxParams).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxParams.stateNum.eq(qCityStateZip.stateNum)/*.and(qRxParams.narcoticCode.trim().eq(qStateCsProducts.prodDea.toString()))*/)

                .leftJoin(qRxParamsGpis).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxParamsGpis.stateNum.eq(qCityStateZip.stateNum).and(qRxParamsGpis.gpiClassId.eq(qProducts.prodGenericRefNum.substring(0, 10))))
                .where(qeScriptMsgAttributes.eScriptMsgAttributeSeq.eq(scriptId))
                .fetch();

        return list.stream().filter(e ->
                e.getNarcoticCode() == null || e.getScpProdDea() == (byte) 0
                        || e.getNarcoticCode().trim().equals(String.valueOf(e.getScpProdDea()))).collect(Collectors.toList());
    }

    /**
     * Get days supply by rxNumber.
     *
     * @param rxNumber The rx number.
     * @return The days supply.
     */
    private Integer getDaysSupply(String rxNumber) {
        QEScriptMsgAttributes qeScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        QRxFillAux qRxFillAux = QRxFillAux.rxFillAux;

        List<Tuple> rxList = epostrxQueryFactory.select(
                        qRxFillAux.eScriptMsgAttributeSeq,
                        qRxFillAux.fillDaysSupply,
                        qRxFillAux.fillStatusNum,
                        qeScriptMsgAttributes.ediTransactionCode)
                .from(qRxFillAux).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .join(qeScriptMsgAttributes).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qeScriptMsgAttributes.eScriptMsgAttributeSeq.eq(qRxFillAux.eScriptMsgAttributeSeq))
                .where(qeScriptMsgAttributes.rxNumber.eq(rxNumber))
                .fetch();

        // Line: 155 - 161
        BigInteger maxFillScriptId = rxList.stream().filter(t ->
                        (FILL_STATUS_NUMS_1.contains(t.get(INDEX_FILL_STATUS_NUM, Byte.class))
                                && !VALUE_NEWRX.equals(t.get(INDEX_EDI_TRANSACTION_CODE, String.class)))
                                || (FILL_STATUS_NUMS_2.contains(t.get(INDEX_FILL_STATUS_NUM, Byte.class))
                                && VALUE_NEWRX.equals(t.get(INDEX_EDI_TRANSACTION_CODE, String.class))))
                .max(Comparator.comparing(t -> t.get(INDEX_ESCRIPT_MSG_ATTRIBUTE_SEQ, BigInteger.class)))
                .map(t -> t.get(INDEX_ESCRIPT_MSG_ATTRIBUTE_SEQ, BigInteger.class))
                .orElse(BigInteger.ZERO);

        if (BigInteger.ZERO.equals(maxFillScriptId)) {
            //Line: 165 - 170
            maxFillScriptId = rxList.stream().filter(t ->
                            (Objects.equals(t.get(INDEX_FILL_DAYS_SUPPLY, Integer.class), NUM_5)
                                    && VALUE_REFILLRX.equals(t.get(INDEX_EDI_TRANSACTION_CODE, String.class))))
                    .max(Comparator.comparing(t -> t.get(INDEX_ESCRIPT_MSG_ATTRIBUTE_SEQ, BigInteger.class)))
                    .map(t -> t.get(INDEX_ESCRIPT_MSG_ATTRIBUTE_SEQ, BigInteger.class))
                    .orElse(BigInteger.ZERO);
        }

        // fill aux
        // Line: 173 - 176
        final BigInteger finalMaxFillScriptId = maxFillScriptId;
        return rxList.stream()
                .filter(t -> t.get(INDEX_FILL_DAYS_SUPPLY, Integer.class) != null
                        && finalMaxFillScriptId.equals(t.get(INDEX_ESCRIPT_MSG_ATTRIBUTE_SEQ, BigInteger.class)))
                .map(t -> t.get(INDEX_FILL_DAYS_SUPPLY, Integer.class))
                .findFirst()
                .orElse(0);
    }

    /**
     * Check if the object is null.
     *
     * @param obj          The object
     * @param defaultValue The default value
     * @return the object or default value if object is null
     */
    private static <T> T isNull(Object obj, T defaultValue) {
        return obj == null ? defaultValue : (T) obj;
    }
}

