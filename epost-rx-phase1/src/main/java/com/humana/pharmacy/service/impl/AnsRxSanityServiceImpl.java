package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.dto.AnsRxEScriptDTO;
import com.humana.pharmacy.dto.AnsRxSanityResult;
import com.humana.pharmacy.service.AnsRxSanityService;
import com.humana.pharmacy.common.Helper;
import com.humana.pharmacy.common.dto.RxEScriptDTO;
import com.humana.pharmacy.common.entity.QCityStateZip;
import com.humana.pharmacy.common.entity.QEScriptMsgAttributes;
import com.humana.pharmacy.common.entity.QEcsResponses;
import com.humana.pharmacy.common.entity.QOrderBillship;
import com.humana.pharmacy.common.entity.QOrderHeader;
import com.humana.pharmacy.common.entity.QOrderLines;
import com.humana.pharmacy.common.entity.QPatientAddress;
import com.humana.pharmacy.common.entity.QPatientGeneral;
import com.humana.pharmacy.common.entity.QPayorPlans;
import com.humana.pharmacy.common.entity.QPayors;
import com.humana.pharmacy.common.entity.QProducts;
import com.humana.pharmacy.common.entity.QRxFillAux;
import com.humana.pharmacy.common.entity.QRxFillDur;
import com.humana.pharmacy.common.entity.QStateCsProducts;
import com.humana.pharmacy.common.entity.QStates;
import com.humana.pharmacy.common.entity.QTradingPartnerAddress;
import com.humana.pharmacy.common.service.FunctionsService;
import com.humana.pharmacy.common.service.impl.BaseServiceImpl;
import com.humana.pharmacy.constants.AnsRxSanityError;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.sql.SQLExpressions;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Implementation of AnsRxSanityService using QueryDSL. It extends BaseServiceImpl.
 */
public class AnsRxSanityServiceImpl extends BaseServiceImpl implements AnsRxSanityService {
    /**
     * The join flag ' WITH (NOLOCK) '.
     */
    private static final String JOIN_FLAG_NOLOCK = " WITH (NOLOCK) ";

    /**
     * The string 'REFILLRX'.
     */
    private static final String REFILLRX = "REFILLRX";

    /**
     * The string 'DR. CALL RPH'.
     */
    private static final String DR_CALL_RPH = "DR. CALL RPH";

    /**
     * The product number '000000000'.
     */
    private static final String ZERO_PROD_NUM = "000000000";
    /**
     * The string '?Unknown'.
     */
    private static final String VALUE_UNKNOWN = "?Unknown";

    /**
     * The string '39400010100320'.
     */
    private static final String VALUE_39400010100320 = "39400010100320";

    /**
     * The string '34000003100330'.
     */
    private static final String VALUE_34000003100330 = "34000003100330";

    /**
     * The string '83200030200315'.
     */
    private static final String VALUE_83200030200315 = "83200030200315";

    /**
     * The string '28100010100310'.
     */
    private static final String VALUE_28100010100310 = "28100010100310";

    /**
     * The string '005258049'.
     */
    private static final String VALUE_005258049 = "005258049";

    /**
     * The string 'XX'.
     */
    private static final String VALUE_XX = "XX";

    /**
     * The string 'A'.
     */
    private static final String VALUE_A = "A";

    /**
     * The string 'G'.
     */
    private static final String VALUE_G = "G";

    /**
     * The string 'T'.
     */
    private static final String VALUE_T = "T";

    /**
     * The string 'KY'.
     */
    private static final String VALUE_KY = "KY";

    /**
     * The string 'FL'.
     */
    private static final String VALUE_FL = "FL";

    /**
     * The string 'HI'.
     */
    private static final String VALUE_HI = "HI";

    /**
     * The string '01'.
     */
    private static final String VALUE_01 = "01";

    /**
     * The string '72'.
     */
    private static final String VALUE_72 = "72";

    /**
     * The string '02'.
     */
    private static final String VALUE_02 = "02";

    /**
     * The string '04'.
     */
    private static final String VALUE_04 = "04";

    /**
     * The string '02' and '04.
     */
    private static final List<String> VALUE_02_04 = Arrays.asList(VALUE_02, VALUE_04);

    /**
     * The string 'NEWRX'.
     */
    private static final String VALUE_NEWRX = "NEWRX";

    /**
     * The string '1'.
     */
    private static final String VALUE_1 = "1";

    /**
     * The string '2'.
     */
    private static final String VALUE_2 = "2";

    /**
     * The string '8'.
     */
    private static final String VALUE_8 = "8";

    /**
     * The string '9'.
     */
    private static final String VALUE_9 = "9";

    /**
     * The number '2'.
     */
    private static final int NUM_2 = 2;

    /**
     * The number '9'.
     */
    private static final int NUM_9 = 9;

    /**
     * The string '0'.
     */
    private static final String ZERO_STRING = "0";

    /**
     * The max number of refills '5'.
     */
    private static final long NUM_REFILLS_MAX = (byte) 5;

    /**
     * The order line status number '1'.
     */
    private static final byte OL_STATUS_1 = (byte) 1;
    /**
     * The string 'Y'.
     */
    private static final String FLAG_Y = "Y";

    /**
     * The string 'N'.
     */
    private static final String FLAG_N = "N";

    /**
     * The RX status '2'.
     */
    private static final byte RX_STAT_7 = 7;

    /**
     * The fill status numbers 2 and 9.
     */
    private static final List<Byte> FILL_STATUS_NUMS = Arrays.asList((byte) 2, (byte) 9);

    /**
     * The ecs response status types 'P' and 'D'.
     */
    private static final List<String> ECS_RESP_STATUS_TYPES = Arrays.asList("P", "D");

    /**
     * The prod dea 2, 3, 4, 5.
     */
    private static final List<Integer> PROD_DEA_LIST = Arrays.asList(2, 3, 4, 5);

    /**
     * The prod dea 1, 2, 3, 4, 5.
     */
    private static final List<Integer> PROD_DEA_LIST2 = Arrays.asList(1, 2, 3, 4, 5);

    /**
     * Reference to FunctionsService.
     */
    private FunctionsService functionsService;

    /**
     * Constructor of AnsRxSanityServiceImpl.
     *
     * @param epostrxDataSource  DataSource for epostrx database. Required
     * @param workflowDataSource DataSource for epostrx_workflow database. Required
     * @throws RuntimeException if epostrxDataSource or workflowDataSource is null
     */
    public AnsRxSanityServiceImpl(DataSource epostrxDataSource, DataSource workflowDataSource) {
        super(epostrxDataSource, workflowDataSource);
    }

    /**
     * Check Ans rx sanity.
     * <p>Code from file: P_ansrxsanitycheck.sql
     *
     * @param scriptId The scriptId. Required
     * @param rxNumber The rxNumber.
     * @return Ans rx sanity check result. Will return null if no errors found.
     * @throws RuntimeException if the scriptId is null,  or functionsService is null (not configured)
     */
    public AnsRxSanityResult checkAnsRxSanity(BigInteger scriptId, String rxNumber) {
        Helper.checkNull(scriptId, "scriptId");
        Helper.checkNull(functionsService, "functionsService");


        AnsRxSanityResult result = new AnsRxSanityResult();
        AnsRxSanityError err;

        // Line: 166-207
        CompletableFuture<List<AnsRxEScriptDTO>> escriptsFuture = CompletableFuture.supplyAsync(() -> getAnsRxEScripts(scriptId));
        if (rxNumber == null || rxNumber.isEmpty()) {
            rxNumber = escriptsFuture.join().stream().map(e -> e.getRxNumber()).filter(e -> e != null).findFirst().orElse("");
        }
        String finalRxNumber = rxNumber;
        CompletableFuture<List<AnsRxEScriptDTO>> escriptsByRxNumberFuture = CompletableFuture.supplyAsync(() -> getAnsRxEScripts(finalRxNumber));

        List<AnsRxEScriptDTO> escripts = escriptsFuture.join();
        // filter current item
        AnsRxEScriptDTO currEscript = escripts.stream()
                .filter(es -> Objects.equals(OL_STATUS_1, es.getOrderLineStatusNum()))
                .findFirst()
                .orElse(new AnsRxEScriptDTO());

        // SanityCheck1.1
        // Check that rx sig and gpi match prechecked rx
        List<AnsRxEScriptDTO> escriptsByRxNumber = escriptsByRxNumberFuture.join();
        if (getMatchesPrecheck(currEscript.getEScriptMsgAttributeSeq(), escriptsByRxNumber) == 0) {
            result.addError(AnsRxSanityError.RX_SIG_OR_GPI_DOES_NOT_MATCH);
        }

        // SanityCheck1.2
        // Check that if controlled, this refill doesn't exceed the number of fills written
        err = checkControlledFillsExceeded(currEscript.getRxNumber(), escripts);
        if (err != null) {
            result.addError(AnsRxSanityError.CONTROLLED_FILL_COUNT_EXCEEDS);
        }

        // SanityCheck1.3
        // Check for the number of refils for controlled substances does *not* exceed 5
        Long controlled = escripts.stream()
                .filter(es -> (es.getProdDea() != null && es.getProdDea() > 0)
                        && (es.getNumRefills() != null && es.getNumRefills() > NUM_REFILLS_MAX))
                .map(AnsRxEScriptDTO::getNumRefills)
                .findFirst()
                .orElse(null);

        if (controlled != null) {
            result.addError(AnsRxSanityError.SHOULD_BE_LESS_THAN_6, controlled.toString());
        }

        // SanityCheck1.4
        // Check if rx is anything but active
        Byte active = escripts.stream()
                .filter(es -> (es.getRxStatusCodeNum() != null && es.getRxStatusCodeNum() != RX_STAT_7)
                        && getRxStatusCode(es.getRxStatusCodeNum()) != null)
                .map(AnsRxEScriptDTO::getRxStatusCodeNum)
                .findFirst()
                .orElse(null);

        if (active != null) {
            result.addError(AnsRxSanityError.RX_IS_NOT_ACTIVE, getRxStatusCode(active));
        }

        // SanityCheck1.5
        // Check if rx/doctor is eligible for erx, if so, that we have a doc addr assigned to the rx record
        long epxElig = escripts.stream()
                .filter(es -> REFILLRX.equals(es.getEdiTransactionCode())
                        && es.getTpAddrSeq() == null
                        && es.getPrescriberAddress() != null)
                .count();

        if (epxElig > 0) {
            result.addError(AnsRxSanityError.PRESCRIBER_IS_ERX_ELIGIBLE);
        }

        // SanityCheck1.6
        // Check if rx cash
        long cash = escripts.stream()
                .filter(es -> !VALUE_A.equals(es.getPpTypeCode()))
                .count();

        if (cash == 0) {
            // SanityCheck1.7
            // Check to insure if NON CASH plan, claim is PAID -- get 'P'
            Integer paidCount = functionsService.getClaimPaidNonCashNonSelfAdjudicating(scriptId);

            if (paidCount == 0) {
                String paid = escripts.stream().filter(es -> VALUE_A.equals(es.getPpTypeCode())
                                && (es.getEcsResponseStatusType() == null
                                || !ECS_RESP_STATUS_TYPES.contains(es.getEcsResponseStatusType())))
                        .map(AnsRxEScriptDTO::getEcsResponseStatusType)
                        .filter(Objects::nonNull)
                        .findFirst()
                        .orElse(VALUE_UNKNOWN);

                result.addError(AnsRxSanityError.RX_NOT_PAID, paid);
            }
        }

        // SanityCheck1.10
        // Check if script is being filled out more than one year from date written
        if (currEscript.getWrittenDate() != null) {
            LocalDateTime writtenDate = currEscript.getWrittenDate().toLocalDateTime();
            LocalDateTime currentDate = LocalDateTime.now();

            if (currentDate.isAfter(writtenDate.plusYears(1))) {
                result.addError(AnsRxSanityError.WRITTEN_PAST_DATE);
            }

            // SanityCheck1.11
            // Check if script written date is in the future
            if (writtenDate.isAfter(currentDate)) {
                result.addError(AnsRxSanityError.WRITTEN_FUTURE_DATE);
            }
        }

        // SanityCheck1.12
        // Check attempt to refill control in state=ky; as of 02/2013 no longer requires drivers license on file, but needs ssn
        // changes for state = 'in' for ssn sanity check
        err = checkKyAttemptToRefill(currEscript);
        if (err != null) {
            result.addError(AnsRxSanityError.REQUIRES_SSN_ON_FILE, currEscript.getShipState());
        }

        // SanityCheck1.15
        // Check for anti-epileptic drug substitution going to florida; requires patient and/or doctor contact
        err = checkFlAntiEpileptic(currEscript, escriptsByRxNumber);
        if (err != null) {
            result.addError(AnsRxSanityError.FL_EPILEPTIC_SUBSTITION);
        }

        // SanityCheck1.16
        // Check attempt to refill control
        long controlRefill = escripts.stream()
                .filter(es -> !VALUE_NEWRX.equals(es.getEdiTransactionCode())
                        && (es.getProdDea() != null && es.getProdDea() == NUM_2))
                .count();

        if (controlRefill > 0) {
            result.addError(AnsRxSanityError.CONTROLLED_REFILL_ATTEMPT);
        }

        // SanityCheck1.20
        // Check that written prod and disp prod are gpi equiv
        List<Tuple> gpis = getDispensedWrittenProducts(scriptId);

        List<Tuple> notEqGpis = gpis.stream()
                .filter(g -> !Objects.equals(g.get(0, String.class), g.get(3, String.class)))
                .collect(Collectors.toList());

        if (!notEqGpis.isEmpty()) {
            String dispGPI = notEqGpis.get(0).get(0, String.class);
            String writGPI = notEqGpis.get(0).get(3, String.class);

            Integer overrideQueue = getWorkflowQueueNumber(DR_CALL_RPH, currEscript.getTradingPartnerNum());

            result.addError(AnsRxSanityError.DISP_WRIT_GPI_NOT_EQUIVILENT, dispGPI, writGPI);
            result.setOverrideQname(DR_CALL_RPH);
            result.setOverrideQueue(overrideQueue);
        }

        // SanityCheck1.27
        // Check for incorrect substitution
        String dProdNameType, dProdNumber,
                wProdGenericRefNum, wProdNameType, wProdNumber,
                dispensedDrugDaw;
        int incorrectSub = 0;
        for (Tuple gpi : gpis) {
            dProdNameType = gpi.get(1, String.class);
            dProdNumber = gpi.get(2, String.class);
            wProdGenericRefNum = gpi.get(3, String.class);
            wProdNameType = gpi.get(4, String.class);
            wProdNumber = gpi.get(5, String.class);
            dispensedDrugDaw = Optional.ofNullable(gpi.get(6, String.class)).orElse("").trim();

            if (VALUE_39400010100320.equals(wProdGenericRefNum)
                    && ZERO_STRING.equals(dispensedDrugDaw) && !VALUE_G.equals(dProdNameType)) {
                incorrectSub = 1;
            } else if (VALUE_34000003100330.equals(wProdGenericRefNum)
                    && ZERO_STRING.equals(dispensedDrugDaw) && VALUE_T.equals(wProdNameType) && VALUE_T.equals(dProdNameType)) {
                incorrectSub = 2;
            } else if (VALUE_34000003100330.equals(wProdGenericRefNum)
                    && ZERO_STRING.equals(dispensedDrugDaw) && VALUE_G.equals(wProdNameType) && !VALUE_G.equals(dProdNameType)) {
                incorrectSub = 2;
            } else if ((VALUE_83200030200315.equals(wProdGenericRefNum) || VALUE_28100010100310.equals(wProdGenericRefNum))
                    && (VALUE_1.equals(dispensedDrugDaw) || VALUE_2.equals(dispensedDrugDaw))
                    && VALUE_T.equals(wProdNameType) && VALUE_G.equals(dProdNameType)) {
                incorrectSub = 3;
            } else if (VALUE_005258049.equals(substring(wProdNumber, NUM_9)) && VALUE_8.equals(dispensedDrugDaw)
                    && !VALUE_005258049.equals(substring(dProdNumber, NUM_9))) {
                incorrectSub = 4;
            }
        }
        if (incorrectSub != 0) {
            switch (incorrectSub) {
                case 1:
                    err = AnsRxSanityError.ALERT_GPI_WO_SPECIAL_TEE_CODES;
                    break;
                case 2:
                    err = AnsRxSanityError.ALERT_GPI_W_SPECIAL_TEE_CODES;
                    break;
                case 3:
                    err = AnsRxSanityError.ALERT_DAW_1_2_DRUG;
                    break;
                default:
                    // 4
                    err = AnsRxSanityError.ALERT_GPI_WITH_DAW_8_DRUG;
                    break;
            }
            result.addError(err);
        }

        // SanityCheck1.28
        // Check for unresolved dur
        long durNotResolved = escripts.stream().filter(es -> es.getRfdScriptId() != null &&
                es.getOrderNum() != null &&
                es.getDurResolveDate() == null).count();
        if (durNotResolved != 0) {
            result.addError(AnsRxSanityError.UNRESOLVED_DUR_FOUND);
        }

        // Hawaii Compliance Sanity Check BR01 and BR01
        checkHiCompliance(currEscript, result);

        return result.getErrors().isEmpty() ? null : result;
    }

    /**
     * Convert AnsRxEScriptDTO list to RxEScriptDTO list.
     *
     * @param ansRxEScriptDTOs the RxEScriptDTO list
     * @return the RxEScriptDTO list
     */
    private static List<RxEScriptDTO> from(List<AnsRxEScriptDTO> ansRxEScriptDTOs) {
        return ansRxEScriptDTOs.stream().map(ansRxEScriptDTO -> {
            RxEScriptDTO dto = new RxEScriptDTO();
            dto.setEScriptMsgAttributeSeq(ansRxEScriptDTO.getEScriptMsgAttributeSeq());
            dto.setRxNumber(ansRxEScriptDTO.getRxNumber());
            dto.setOriginalNumRefills(ansRxEScriptDTO.getOriginalNumRefills());
            dto.setNumRefills(ansRxEScriptDTO.getNumRefills());
            dto.setFillStatusNum(ansRxEScriptDTO.getFillStatusNum());
            dto.setProdDea(ansRxEScriptDTO.getProdDea());

            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * Gets RX SIG and GPI match prechecked RX.
     *
     * @param scriptId the scriptId
     * @param escripts the escripts
     * @return the count of matches
     */
    private long getMatchesPrecheck(BigInteger scriptId, List<AnsRxEScriptDTO> escripts) {
        //Line: 218-240
        AnsRxEScriptDTO recentPreChecked = escripts.stream()
                .filter(es -> es.getRxNumber() != null
                        && es.getFillPrecheckDatetime() != null)
                .max(Comparator.comparing(AnsRxEScriptDTO::getFillPrecheckDatetime))
                .orElse(null);

        return recentPreChecked == null ? 0 : escripts.stream()
                .filter(es -> es.getEScriptMsgAttributeSeq().equals(scriptId) && es.getDispensedDrugSig() != null
                        && es.getDispensedDrugSig().equals(recentPreChecked.getDispensedDrugSig()) &&
                        Objects.equals(es.getProdGenericRefNum(), recentPreChecked.getProdGenericRefNum()))
                .count();
    }

    /**
     * Check controlled fills exceeded.
     *
     * @param rxNumber the rx number
     * @param escripts the escripts
     * @return the ans rx sanity error
     */
    private AnsRxSanityError checkControlledFillsExceeded(String rxNumber, List<AnsRxEScriptDTO> escripts) {
        // Line: 278 - 287

        final List<AnsRxEScriptDTO> filteredByFillStatusNumEscripts =
                escripts.stream().filter(es -> FILL_STATUS_NUMS.contains(es.getFillStatusNum())).collect(Collectors.toList());

        // conditions to check for products
        boolean controlledFillsExceeded = filteredByFillStatusNumEscripts.stream()
                .anyMatch(es -> es.getRxNumber() != null && es.getRxNumber().equals(rxNumber)
                        && es.getProdDea() != null && es.getProdDea() > 0
                        && es.getOriginalNumRefills() != null
                        && functionsService.getRxTotalFills(es.getEScriptMsgAttributeSeq(), from(filteredByFillStatusNumEscripts)) > es.getOriginalNumRefills());

        if (!controlledFillsExceeded) {
            // Line 305- 318
            // conditions to check for state specific products
            controlledFillsExceeded = filteredByFillStatusNumEscripts.stream()
                    .anyMatch(es -> es.getRxNumber() != null && es.getRxNumber().equals(rxNumber)
                            && es.getStateProdDea() != null && es.getStateProdDea() > 0
                            && es.getOriginalNumRefills() != null
                            && functionsService.getRxTotalFills(es.getEScriptMsgAttributeSeq(), from(filteredByFillStatusNumEscripts)) > es.getOriginalNumRefills());

        }
        return controlledFillsExceeded ? AnsRxSanityError.CONTROLLED_FILL_COUNT_EXCEEDS : null;
    }

    /**
     * Check attempt to refill control in Kentucky.
     *
     * @param escript the ans rx escript dto
     * @return the ans rx sanity error
     */
    private AnsRxSanityError checkKyAttemptToRefill(AnsRxEScriptDTO escript) {
        String kyControlRefill = FLAG_N;

        // Line: 493 - 535
        if (VALUE_KY.equals(escript.getShipState()) &&
                ((escript.getProdDea() != null && PROD_DEA_LIST.contains(escript.getProdDea().intValue()))
                        || (escript.getStateProdDea() != null && PROD_DEA_LIST.contains(escript.getStateProdDea().intValue())))) {

            if (isNullOrEmpty(escript.getPatientIdCode())) {
                kyControlRefill = FLAG_Y;
            } else {
                if (!VALUE_01.equals(escript.getPatientIdCode()) && !VALUE_01.equals(escript.getAdditionalPatientIdTypeCode())) {
                    kyControlRefill = FLAG_Y;
                } else if (!VALUE_01.equals(escript.getPatientIdCode()) ||
                        (VALUE_01.equals(escript.getPatientIdCode()) &&
                                (isNullOrEmpty(escript.getPatientId()) || escript.getPatientId().startsWith(VALUE_9)))) {
                    kyControlRefill = FLAG_Y;
                }
            }

            if (kyControlRefill.equals(FLAG_Y) && VALUE_01.equals(escript.getAdditionalPatientIdTypeCode()) &&
                    !isNullOrEmpty(escript.getAdditionalPatientId()) && !escript.getAdditionalPatientId().startsWith(VALUE_9)) {
                kyControlRefill = FLAG_N;
            }
        }
        return FLAG_Y.equals(kyControlRefill) ? AnsRxSanityError.REQUIRES_SSN_ON_FILE : null;
    }

    /**
     * Check for anti-epileptic drug substitution going to Florida.
     *
     * @param currEscript the actual ans rx escript
     * @return the ans rx sanity error
     */
    private AnsRxSanityError checkFlAntiEpileptic(AnsRxEScriptDTO currEscript,
                                                  List<AnsRxEScriptDTO> escripts) {
        // Line: 654 - 688
        if (VALUE_FL.equals(currEscript.getShipState())) {
            boolean currFlEpileptic = currEscript.getProdGenericRefNum() != null
                    && currEscript.getProdGenericRefNum().startsWith(VALUE_72)
                    && Objects.equals((byte) 1, currEscript.getOrderLineStatusNum());

            // if current item is FL epiletic item, get the info on the previous fill of this rx
            if (currFlEpileptic) {
                AnsRxEScriptDTO prevEscript = escripts.stream()
                        .filter(es -> FILL_STATUS_NUMS.contains(es.getFillStatusNum())
                                && es.getProdGenericRefNum() != null && es.getProdGenericRefNum().startsWith(VALUE_72)
                                && es.getEScriptMsgAttributeSeq().compareTo(currEscript.getEScriptMsgAttributeSeq()) < 0
                                && es.getConversionLink() == null).max(Comparator.comparing(AnsRxEScriptDTO::getEScriptMsgAttributeSeq))
                        .orElse(null);
                boolean prevFlEpileptic = prevEscript != null;

                // if PREV fill was a FL epileptic item, then compare curr and previous fill for a substitution
                if (prevFlEpileptic && (!prevEscript.getProdGenericRefNum().equals(currEscript.getProdGenericRefNum())
                        || (prevEscript.getProdGenericRefNum().equals(currEscript.getProdGenericRefNum())
                        && !substring(currEscript.getProdNumber(), NUM_9).equals(substring(prevEscript.getProdNumber(), NUM_9))))) {
                    return AnsRxSanityError.FL_EPILEPTIC_SUBSTITION;
                }
            }
        }
        return null;
    }

    /**
     * Get a substring of this string begins at 0.
     *
     * @param str      the string
     * @param endIndex the ending index, exclusive.
     * @return the substring
     */
    private static String substring(String str, int endIndex) {
        return str.substring(0, Math.min(str.length(), endIndex));
    }

    /**
     * Gets dispensed written products by given scriptId.
     *
     * @param scriptId the script id
     * @return the list of tuple dispensed written products
     */
    private List<Tuple> getDispensedWrittenProducts(BigInteger scriptId) {
        QEScriptMsgAttributes qeScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        QProducts qProducts1 = QProducts.products;
        QProducts qProducts2 = new QProducts("products2");

        return epostrxQueryFactory
                .select(
                        qProducts1.prodGenericRefNum.coalesce(ZERO_STRING).as("dProdGenericRefNum"),
                        qProducts1.prodNameType.coalesce(ZERO_STRING).as("dProdNameType"),
                        qProducts1.prodNumber.coalesce(ZERO_PROD_NUM).as("dProdNumber"),
                        qProducts2.prodGenericRefNum.coalesce(ZERO_STRING).as("wProdGenericRefNum"),
                        qProducts2.prodNameType.coalesce(ZERO_STRING).as("wProdNameType"),
                        qProducts2.prodNumber.coalesce(ZERO_PROD_NUM).as("wProdNumber"),
                        qeScriptMsgAttributes.dispensedDrugDaw.coalesce(VALUE_XX).as("dispensedDrugDaw"))
                .from(qeScriptMsgAttributes).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .join(qProducts1).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION).on(qProducts1.prodId.eq(qeScriptMsgAttributes.dispensedProductId))
                .join(qProducts2).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION).on(qProducts2.prodId.eq(qeScriptMsgAttributes.writtenProductId))
                .where(qeScriptMsgAttributes.eScriptMsgAttributeSeq.eq(scriptId))
                .fetch();
    }

    /**
     * Check Hawaii Compliance BR01.
     *
     * @param escript the ans rx escript dto
     * @param errors  the list to hold errors
     */
    private void checkHiCompliance(AnsRxEScriptDTO escript, AnsRxSanityResult errors) {
        if (VALUE_HI.equals(escript.getShipState())
                && ((escript.getProdDea() != null && PROD_DEA_LIST2.contains(escript.getProdDea().intValue()))
                || (escript.getStateProdDea() != null && PROD_DEA_LIST2.contains(escript.getStateProdDea().intValue())))) {

            if (escript.getTpAddrSeq() == null || escript.getTpAddrSeq() <= 0L) {
                errors.addError(AnsRxSanityError.HI_MUST_HAVE_PRESCRIBER_ADDRESS);
            } else if (escript.getHiPrescriberAddress() == null) {
                errors.addError(AnsRxSanityError.HI_MUST_HAVE_PRESCRIBER_ADDRESS);
            }

            if (escript.getPatientIdCode() == null || Integer.parseInt(escript.getPatientIdCode()) <= 0) {
                errors.addError(AnsRxSanityError.HI_REQUIRES_DOCUMENTS);
            } else {
                String hiControlRefill = FLAG_N;
                // Line: 1122 - 1158

                if (!VALUE_02_04.contains(escript.getPatientIdCode())
                        && !VALUE_02_04.contains(escript.getAdditionalPatientIdTypeCode())) {
                    hiControlRefill = FLAG_Y;
                } else if (!VALUE_02_04.contains(escript.getPatientIdCode())
                        || (VALUE_02_04.contains(escript.getPatientIdCode()) && isNullOrEmpty(escript.getPatientId()))) {
                    hiControlRefill = FLAG_Y;
                }

                if (FLAG_Y.equals(hiControlRefill) && VALUE_02_04.contains(escript.getAdditionalPatientIdTypeCode())
                        && !isNullOrEmpty(escript.getAdditionalPatientId())) {
                    hiControlRefill = FLAG_N;
                }
                if (FLAG_Y.equals(hiControlRefill)) {
                    errors.addError(AnsRxSanityError.HI_REQUIRES_DOCUMENTS);
                }
            }
        }
    }

    /**
     * Collect ans rx escripts by RX number.
     *
     * @param rxNumber the RX number.
     * @return the list of AnsRxEScriptDTO
     */
    private List<AnsRxEScriptDTO> getAnsRxEScripts(String rxNumber) {
        if (rxNumber == null) {
            return new ArrayList<>();
        }

        QEScriptMsgAttributes qeScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        QProducts qProducts = QProducts.products;
        QRxFillAux qRxFillAux = QRxFillAux.rxFillAux;

        return epostrxQueryFactory.select(
                        Projections.bean(AnsRxEScriptDTO.class,
                                qeScriptMsgAttributes.eScriptMsgAttributeSeq,

                                qProducts.prodId,
                                qProducts.prodDea.coalesce((byte) 0).as("prodDea"),
                                qeScriptMsgAttributes.rxNumber,
                                qeScriptMsgAttributes.tradingPartnerNum.coalesce(0l).as("tradingPartnerNum"),
                                qeScriptMsgAttributes.dispensedDrugSig,

                                qeScriptMsgAttributes.patientNum,
                                qeScriptMsgAttributes.tpAddrSeq,
                                qeScriptMsgAttributes.doctorNum,

                                qRxFillAux.fillDaysSupply.as("daysSupply"),
                                qeScriptMsgAttributes.numRefills,
                                qeScriptMsgAttributes.rxStatusCodeNum,

                                qeScriptMsgAttributes.ediTransactionCode,
                                qeScriptMsgAttributes.writtenDate,
                                qRxFillAux.fillStatusNum,
                                qRxFillAux.fillPrecheckDatetime,
                                qeScriptMsgAttributes.originalNumRefills,

                                qProducts.prodGenericRefNum.coalesce(ZERO_STRING).as("prodGenericRefNum"),
                                qRxFillAux.conversionLink,
                                qProducts.prodNumber
                        )
                )
                .from(qeScriptMsgAttributes).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .join(qProducts).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qProducts.prodId.eq(qeScriptMsgAttributes.dispensedProductId))
                .join(qRxFillAux).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillAux.eScriptMsgAttributeSeq.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq))
                .where(qeScriptMsgAttributes.rxNumber.eq(rxNumber))
                .fetch();
    }

    /**
     * Collect ans rx escripts by script id.
     *
     * @param scriptId the script id
     * @return the list of AnsRxEScriptDTO
     */
    private List<AnsRxEScriptDTO> getAnsRxEScripts(BigInteger scriptId) {
        QEScriptMsgAttributes qeScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        QOrderLines qOrderLine = QOrderLines.orderLines;
        QOrderBillship qOrderBillship = QOrderBillship.orderBillship;
        QPatientAddress qPatientAddress = QPatientAddress.patientAddress1;
        QCityStateZip qCityStateZip = QCityStateZip.cityStateZip;
        QCityStateZip qCityStateZip1 = new QCityStateZip("qCityStateZip1");
        QStates qStates = QStates.states;
        QStates qStates1 = new QStates("qStates1");
        QProducts qProducts = QProducts.products;
        QStateCsProducts qStateCsProducts = QStateCsProducts.stateCsProducts;
        QRxFillAux qRxFillAux = QRxFillAux.rxFillAux;
        QTradingPartnerAddress qTradingPartnerAddress = QTradingPartnerAddress.tradingPartnerAddress;
        QPayorPlans qPayorPlans = QPayorPlans.payorPlans;
        QEcsResponses qEcsResponses = QEcsResponses.ecsResponses;
        QPayors qPayors = QPayors.payors;
        QPatientGeneral qPatientGeneral = QPatientGeneral.patientGeneral;
        QRxFillDur qRxFillDur = QRxFillDur.rxFillDur;
        QOrderHeader qOrderHeader = QOrderHeader.orderHeader;

        // Subquery to get order based queue
        SimpleExpression<String> prescriberAddress =
                SQLExpressions.select(Expressions.stringTemplate("1")).distinct()
                        .from(qTradingPartnerAddress).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                        .where(qTradingPartnerAddress.tradingPartnerNum.eq(qeScriptMsgAttributes.doctorNum)
                                .and(qTradingPartnerAddress.active.eq(VALUE_A)).and(qTradingPartnerAddress.spi.isNotNull()))
                        .as("prescriberAddress");

        SimpleExpression<String> hiPrescriberAddress =
                SQLExpressions.select(Expressions.stringTemplate("1")).distinct()
                        .from(qTradingPartnerAddress).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                        .join(qCityStateZip1).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                        .on(qCityStateZip1.cszZipNum.eq(qTradingPartnerAddress.cszZipNum))
                        .join(qStates1).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                        .on(qStates1.stateNum.eq(qCityStateZip1.stateNum).and(qStates1.stateCode.eq("HI")))
                        .where(qTradingPartnerAddress.tradingPartnerNum.eq(qeScriptMsgAttributes.doctorNum)
                                .and(qTradingPartnerAddress.tpAddrSeq.eq(qeScriptMsgAttributes.tpAddrSeq))
                                .and(qTradingPartnerAddress.active.eq(FLAG_Y)))
                        .as("hiPrescriberAddress");

        return epostrxQueryFactory.select(
                        Projections.bean(AnsRxEScriptDTO.class,
                                qeScriptMsgAttributes.eScriptMsgAttributeSeq,
                                qOrderLine.orderLineStatusNum,
                                qStates.stateCode.coalesce(VALUE_XX).as("shipState"),

                                qProducts.prodId,
                                qProducts.prodDea.coalesce((byte) 0).as("prodDea"),

                                qStateCsProducts.prodDea.as("stateProdDea"),
                                qeScriptMsgAttributes.rxNumber,
                                qeScriptMsgAttributes.tradingPartnerNum.coalesce(0l).as("tradingPartnerNum"),
                                qeScriptMsgAttributes.dispensedDrugSig,

                                qeScriptMsgAttributes.patientNum,
                                qeScriptMsgAttributes.tpAddrSeq,
                                qeScriptMsgAttributes.doctorNum,

                                qRxFillAux.fillDaysSupply.as("daysSupply"),
                                qeScriptMsgAttributes.numRefills,
                                qeScriptMsgAttributes.rxStatusCodeNum,

                                qeScriptMsgAttributes.ediTransactionCode,

                                prescriberAddress,
                                hiPrescriberAddress,
                                qPayorPlans.ppTypeCode,

                                qEcsResponses.ecsResponseStatusType,
                                qeScriptMsgAttributes.writtenDate,
                                qRxFillAux.fillStatusNum,
                                qRxFillAux.fillPrecheckDatetime,
                                qeScriptMsgAttributes.originalNumRefills,
                                qPayorPlans.ppNum.as("payorPlanPpNum"),
                                qPayors.payorBillTypeNum,

                                qPatientGeneral.patientIdCode,
                                qPatientGeneral.patientId,
                                qPatientGeneral.additionalPatientIdTypeCode,
                                qPatientGeneral.additionalPatientId,

                                qProducts.prodGenericRefNum.coalesce(ZERO_STRING).as("prodGenericRefNum"),
                                qRxFillAux.conversionLink,
                                qProducts.prodNumber,

                                qRxFillDur.durResolveDate,
                                qRxFillDur.eScriptMsgAttributeSeq.as("rfdScriptId"),
                                qOrderHeader.orderNum
                        )
                )
                .from(qeScriptMsgAttributes).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .join(qOrderLine).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderLine.eScriptMsgAttributeSeq.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq))
                .join(qOrderBillship).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderBillship.orderNum.eq(qOrderLine.orderNum))
                .leftJoin(qPatientAddress).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientAddress.patientAddrSeq.eq(qOrderBillship.patientShipAddressSeq))
                .leftJoin(qCityStateZip).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qCityStateZip.cszZipNum.eq(qPatientAddress.cszZipNum))
                .leftJoin(qStates).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qStates.stateNum.eq(qCityStateZip.stateNum))

                .leftJoin(qProducts).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qProducts.prodId.eq(qeScriptMsgAttributes.dispensedProductId))
                .leftJoin(qStateCsProducts).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qStateCsProducts.prodId.eq(qProducts.prodId).and(qStateCsProducts.stateNum.eq(qStates.stateNum)))

                .leftJoin(qRxFillAux).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillAux.eScriptMsgAttributeSeq.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq))

                .leftJoin(qPayorPlans).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPayorPlans.ppNum.eq(qRxFillAux.ppNum))
                .leftJoin(qPayors).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPayors.payorNum.eq(qPayorPlans.payorNum))

                .leftJoin(qEcsResponses).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qEcsResponses.ecsRespSeqNum.eq(qRxFillAux.fillEcsStatus))

                .leftJoin(qPatientGeneral).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientGeneral.patientNum.eq(qeScriptMsgAttributes.patientNum))

                .leftJoin(qRxFillDur).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillDur.eScriptMsgAttributeSeq.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq))
                .leftJoin(qOrderHeader).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderHeader.orderNum.eq(qOrderLine.orderNum))
                .where(qeScriptMsgAttributes.eScriptMsgAttributeSeq.eq(scriptId))
                .fetch();
    }

    /**
     * Check if the string is null/empty.
     *
     * @param str the string
     * @return <code>true</code> if the string is null/empty; <code>false</code> otherwise.
     */
    private static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
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
}

