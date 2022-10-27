package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.dto.AnsValidateHippaAddressResult;
import com.humana.pharmacy.dto.AnsValidateHippaFamilyDTO;
import com.humana.pharmacy.dto.AnsValidateHippaOrderDTO;
import com.humana.pharmacy.dto.AnsValidateHippaPatientDTO;
import com.humana.pharmacy.service.AnsValidateHippaAddressService;
import com.humana.pharmacy.common.Helper;
import com.humana.pharmacy.common.cache.model.CShippingMethod;
import com.humana.pharmacy.common.entity.QCityStateZip;
import com.humana.pharmacy.common.entity.QEScriptMsgAttributes;
import com.humana.pharmacy.common.entity.QOrderBillship;
import com.humana.pharmacy.common.entity.QOrderHeader;
import com.humana.pharmacy.common.entity.QOrderLines;
import com.humana.pharmacy.common.entity.QPatientAddress;
import com.humana.pharmacy.common.entity.QPatientAddressTypeAssignments;
import com.humana.pharmacy.common.entity.QPatientFamily;
import com.humana.pharmacy.common.entity.QPatientGeneral;
import com.humana.pharmacy.common.entity.QPatientPlans;
import com.humana.pharmacy.common.entity.QRxFillAux;
import com.humana.pharmacy.common.entity.QStates;
import com.humana.pharmacy.common.service.FunctionsService;
import com.humana.pharmacy.common.service.impl.BaseServiceImpl;
import com.humana.pharmacy.constants.AnsValidateHippaAddressError;
import com.humana.pharmacy.dto.AnsValidateHippaScriptDTO;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.types.Projections;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implementation of AnsValidateHippaAddressService using QueryDSL. It extends BaseServiceImpl.
 */
public class AnsValidateHippaAddressServiceImpl extends BaseServiceImpl implements AnsValidateHippaAddressService {

    /**
     * The join flag ' WITH (NOLOCK) '.
     */
    private static final String JOIN_FLAG_NOLOCK = " WITH (NOLOCK) ";

    /**
     * The date format 'yyyMMdd'.
     */
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyMMdd");

    /**
     * The string 'Y'.
     */
    private static final String VALUE_Y = "Y";

    /**
     * The string 'N'.
     */
    private static final String VALUE_N = "N";

    /**
     * The order status numbers '2' and '9'.
     */
    private static final List<Byte> ORDER_STATUS_NUMS = Arrays.asList((byte) 1, (byte) 2, (byte) 9);

    /**
     * The order line status numbers '2' and '9'.
     */
    private static final List<Byte> ORDER_LINE_STATUS_NUMS = Arrays.asList((byte) 1, (byte) 3);

    /**
     * The address type num '11'.
     */
    private static final int ADDRESS_TYPE_NUM_11 = 11;

    /**
     * The address error '13'.
     */
    private static final int ADDRESS_ERROR_13 = 13;

    /**
     * The address error '15'.
     */
    private static final int ADDRESS_ERROR_15 = 15;

    /**
     * The address error '16'.
     */
    private static final int ADDRESS_ERROR_16 = 16;

    /**
     * Reference to FunctionsService.
     */
    private FunctionsService functionsService;

    /**
     * Constructor of AnsValidateHippaAddressServiceImpl.
     *
     * @param workflowDataSource DataSource for epostrx_workflow database. Required
     * @param epostrxDataSource  DataSource for epostrx database. Required
     * @throws RuntimeException if epostrxDataSource or workflowDataSource is null
     */
    public AnsValidateHippaAddressServiceImpl(DataSource epostrxDataSource, DataSource workflowDataSource) {
        super(epostrxDataSource, workflowDataSource);
    }

    /**
     * Check HIPPA shipping address by ordernum.
     * <p>Code from file: P_ansvalidatehippaadress.sql
     *
     * @param orderNum The orderNum. Required
     * @return Ans validate hippa address check result. Will return null if no errors found.
     * @throws RuntimeException if the orderNum is null, functionsService is null (not configured) or family mode but no family ID
     */
    public AnsValidateHippaAddressResult checkAnsValidateHippaAddress(BigInteger orderNum) {
        Helper.checkNull(orderNum, "orderNum");
        Helper.checkNull(functionsService, "functionsService");

        List<AnsValidateHippaOrderDTO> ansValidateHippaList = getAnsValidateHippa(orderNum);
        if (ansValidateHippaList.stream().anyMatch(dto -> VALUE_Y.equals(dto.getForceShipAddress()))) {
            return null;
        }

        AnsValidateHippaAddressResult result = new AnsValidateHippaAddressResult();
        if (checkCurrierIsNotUSPS(ansValidateHippaList)) {
            result.addError(AnsValidateHippaAddressError.CURRIER_IS_NOT_USPS);
            return result;
        }

        AnsValidateHippaAddressError addressError = checkAddress(ansValidateHippaList);
        if (addressError != null) {
            result.addError(addressError);
            return result;
        }

        //Line: 266-272
        if (ansValidateHippaList.isEmpty() ||
                ansValidateHippaList.get(0).getPatientShipAddressSeq() == null ||
                ansValidateHippaList.get(0).getPatientShipAddressSeq().equals(0L)) {
            result.addError(AnsValidateHippaAddressError.MANUAL_VERIFICATION_REQUIRED);
            return result;
        }
        AnsValidateHippaOrderDTO cur = ansValidateHippaList.get(0);

        //Line: 276-281
        if (!VALUE_Y.equals(cur.getActive())) {
            result.addError(AnsValidateHippaAddressError.ADDRESS_STATUS_IS_INACTIVE);
            return result;
        }
        String familyMode = getOrderDefaults().getFamilyMode();
        long memberNotInOrder = 0;
        long nonFamilyMember = 0;
        int addError = 0;
        if (VALUE_Y.equals(familyMode)) {
            if (cur.getFamilyId() != null) {
                List<AnsValidateHippaScriptDTO> scriptList = getScripts(orderNum);
                List<BigInteger> patientNumList = new ArrayList<>();
                patientNumList.addAll(scriptList.stream().map(d -> d.getPatientNum())
                        .filter(Objects::nonNull).distinct().collect(Collectors.toList()));
                BigInteger patientNum = null;
                //Line: 293-302
                nonFamilyMember = scriptList.stream().filter(dto -> ORDER_STATUS_NUMS.contains(dto.getOrderStatusNum()) &&
                        ORDER_LINE_STATUS_NUMS.contains(dto.getOrderLineStatusNum()) &&
                        dto.getFamilyId() != null &&
                        !Objects.equals(dto.getPfFamilyId(), dto.getFamilyId())).count();
                memberNotInOrder = scriptList.stream().filter(dto -> ORDER_STATUS_NUMS.contains(dto.getOrderStatusNum()) &&
                        ORDER_LINE_STATUS_NUMS.contains(dto.getOrderLineStatusNum()) &&
                        dto.getPatientNum() != null &&
                        Objects.equals(dto.getPatientNum(), dto.getPaPatientNum())).count();
                if (nonFamilyMember == 0 && memberNotInOrder > 0) {
                    LocalDateTime hipaa = LocalDateTime.now().minusYears(cur.getConsentAge());
                    BigInteger patientAdult = getRankingMember(scriptList, hipaa, false);
                    BigInteger patientMinor = getRankingMember(scriptList, hipaa, true);
                    BigInteger cardholderPatNum = getFamilyCardholder(cur.getFamilyId());
                    if (cardholderPatNum != null) {
                        patientNumList.add(cardholderPatNum);
                    }
                    if (patientAdult.compareTo(BigInteger.ZERO) > 0) {
                        patientNum = patientAdult;
                        patientNumList.add(patientAdult);
                    } else if (patientMinor.compareTo(BigInteger.ZERO) > 0) {
                        patientNum = patientMinor;
                        patientNumList.add(patientMinor);
                        if (cardholderPatNum == null) {
                            addError = ADDRESS_ERROR_13;
                        }
                    }
                    List<AnsValidateHippaPatientDTO> patientList = getPatient(patientNumList);
                    Long cardholderMatched = getCardHolderAddrMatch(patientList, patientNum, cardholderPatNum, cur.getConsentAge());
                    if (cardholderMatched == null) {
                        addError = ADDRESS_ERROR_15;
                        final BigInteger fP = patientNum;
                        //Line: 516-530
                        cardholderMatched = patientList.stream().filter(dto -> dto.getPatientNum().equals(fP) &&
                                        Objects.equals(dto.getAddressTypeNum(), ADDRESS_TYPE_NUM_11) && "A".equals(dto.getActive()))
                                .map(d -> d.getPatientAddrSeq())
                                .findFirst().orElse(null);
                    }
                    //Line: 628-655
                    if (cur.getPatientShipAddressSeq() != null
                            && Objects.equals(cur.getPatientShipAddressSeq(), cardholderMatched)) {
                        if (scriptList.stream().anyMatch(s -> {
                            if (s.getOrderLineStatusNum() == null || !s.getOrderLineStatusNum().equals((byte) 1)) {
                                return false;
                            }
                            List<AnsValidateHippaPatientDTO> holders = patientList.stream().filter(dto -> dto.getPatientNum().equals(cardholderPatNum) && VALUE_Y.equals(dto.getActive()) &&
                                    dto.getCszZipNum() != null).collect(Collectors.toList());
                            List<AnsValidateHippaPatientDTO> ps = patientList.stream().filter(dto -> dto.getPatientNum().equals(s.getPatientNum()) &&
                                    Objects.equals(dto.getAddressTypeNum(), ADDRESS_TYPE_NUM_11) && VALUE_Y.equals(dto.getActive()) &&
                                    dto.getCszZipNum() != null).collect(Collectors.toList());
                            //Line: 650-653
                            return !holders.stream().anyMatch(h -> h.getPatientAddrSeq() != null &&
                                    ps.stream().anyMatch(p -> p.getCszZipNum().equals(h.getCszZipNum()) &&
                                            functionsService.getNumericsFromString(p.getPatientAddress().concat(isNull(p.getPatientAddress2(), "")))
                                                    .equals(functionsService.getNumericsFromString(h.getPatientAddress().concat(isNull(h.getPatientAddress2(), ""))))));
                        })) {
                            addError = ADDRESS_ERROR_16;
                        }
                    }
                }
            } else {
                result.addError(AnsValidateHippaAddressError.NO_FAMILY_RECORD);
                return result;
            }
        }
        if (nonFamilyMember > 0) {
            result.addError(AnsValidateHippaAddressError.PATIENT_IS_NOT_VALID);
        }
        if (addError == ADDRESS_ERROR_13) {
            result.addError(AnsValidateHippaAddressError.NO_CARDHOLDER);
        } else if (addError == ADDRESS_ERROR_15) {
            result.addError(AnsValidateHippaAddressError.NO_MINOR_ADDRESS);
        } else if (addError == ADDRESS_ERROR_16) {
            result.addError(AnsValidateHippaAddressError.ADDRESS_NOT_COMMON);
        }
        return result.getErrors().size() > 0 ? result : null;
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
     * Get matched cardholder address seq.
     *
     * @param cardholderPatNum the cardholder patient num
     * @param patientNum       the current patient num
     * @param patients         the list of patients
     * @param curHiAge         the current Hippa age
     * @return the matched cardholder address seq
     */
    private Long getCardHolderAddrMatch(List<AnsValidateHippaPatientDTO> patients, BigInteger patientNum,
                                        BigInteger cardholderPatNum, Byte curHiAge) {
        //Line: 574-577
        if (patients.stream().noneMatch(dto -> Objects.equals(dto.getPatientNum(), patientNum)
                && dto.getFamilyId() != null)) {
            throw new RuntimeException("FAMILY MODE, BUT NO FAMILY ID");
        }
        Byte hiAge = curHiAge;
        LocalDate dob = patients.stream().filter(d -> d.getPatientNum().equals(patientNum)
                        && d.getPatientDob() != null)
                .map(d -> d.getPatientDob().toLocalDateTime().toLocalDate()).findFirst().orElse(LocalDate.now());
        int patientAge = Period.between(dob, LocalDate.now()).getYears();
        //Line: 548
        if (!Objects.equals(patientNum, cardholderPatNum)
                && patientAge < hiAge) {
            List<AnsValidateHippaPatientDTO> holders = patients.stream().filter(
                    dto -> VALUE_Y.equals(dto.getActive()) && dto.getCszZipNum() != null
                            && dto.getPatientNum().equals(cardholderPatNum)).collect(Collectors.toList());
            List<AnsValidateHippaPatientDTO> ps = patients.stream().filter(
                    dto -> dto.getPatientNum().equals(patientNum) &&
                            Objects.equals(dto.getAddressTypeNum(), ADDRESS_TYPE_NUM_11)
                            && VALUE_Y.equals(dto.getActive()) &&
                            dto.getCszZipNum() != null).collect(Collectors.toList());
            //Line: 554-570
            return holders.stream().filter(h -> h.getPatientAddrSeq() != null
                            && ps.stream().anyMatch(p -> p.getCszZipNum().equals(h.getCszZipNum()) &&
                            p.getPatientAddress().trim().equals(h.getPatientAddress().trim()) &&
                            Objects.equals(isNull(p.getPatientAddress2(), "NONE").trim()
                                    , isNull(h.getPatientAddress2(), "NONE").trim())))
                    .map(AnsValidateHippaPatientDTO::getPatientAddrSeq)
                    .findFirst().orElse(null);
        }
        return null;
    }

    /**
     * Get family cardholder patient num.
     *
     * @param familyId the family id
     * @return the family cardholder patient num
     */
    private BigInteger getFamilyCardholder(String familyId) {
        List<AnsValidateHippaFamilyDTO> familyList = getFamilies(familyId);
        long hohCount = familyList.stream().filter(dto -> VALUE_Y.equals(dto.getHeadOfHousehold()))
                .map(AnsValidateHippaFamilyDTO::getPatientNum).distinct().count();
        if (hohCount == 1) {
            return familyList.stream().filter(dto -> dto.getPatientNum() != null
                            && VALUE_Y.equals(dto.getHeadOfHousehold()))
                    .map(AnsValidateHippaFamilyDTO::getPatientNum).findFirst().orElse(null);
        } else {
            return familyList.stream().filter(dto -> dto.getPatientNum() != null
                            && (hohCount == 0 || VALUE_Y.equals(dto.getHeadOfHousehold()))
                            && dto.getPpNum() != null
                            && dto.getPpNum() != 2L
                            && Objects.equals(dto.getRelationshipNum(), getMinRelationshipNum(familyList)))
                    .map(AnsValidateHippaFamilyDTO::getPatientNum)
                    .sorted()
                    .findFirst()
                    .orElse(null);
        }
    }

    /**
     * Get min of relationship num.
     *
     * @param familyList the list of family dto.
     * @return the min relationship num.
     */
    private Integer getMinRelationshipNum(List<AnsValidateHippaFamilyDTO> familyList) {
        return familyList.stream()
                .filter(o -> Objects.equals(o.getCoverageTypeId(), getMinCoverageTypeId(familyList, o.getPatientNum())))
                .map(AnsValidateHippaFamilyDTO::getRelationshipNum)
                .min(Integer::compare)
                .orElse(-1);
    }

    /**
     * Get min of coverage type id.
     *
     * @param familyList the list of family dto.
     * @param patientNum the current patient num.
     * @return the coverage type id.
     */
    private Byte getMinCoverageTypeId(List<AnsValidateHippaFamilyDTO> familyList, BigInteger patientNum) {
        return familyList.stream()
                .filter(o -> Objects.equals(patientNum, o.getPatientNum())
                        && !Objects.equals((byte) 4, o.getCoverageTypeId())
                        && !Objects.equals(2L, o.getPpNum())
                        && functionsService.isOnlyActiveCoverageCash(o.getPatientNum()))
                .map(AnsValidateHippaFamilyDTO::getCoverageTypeId)
                .min(Byte::compare)
                .orElse((byte) -1);
    }

    /**
     * Check currier is not USPS.
     *
     * @param ansValidateHippaList the list of order dto
     * @return <code>true</code> if currier is not USPS; <code>false</code> otherwise
     */
    private Boolean checkCurrierIsNotUSPS(List<AnsValidateHippaOrderDTO> ansValidateHippaList) {
        List<CShippingMethod> shippingMethods = getShippingMethods();
        //Line: 210-220
        List<Integer> shipMethodIds = shippingMethods.stream()
                .filter(ship -> ship.getShipMethodDesc().startsWith("FEDEX") ||
                        ship.getShipMethodDesc().startsWith("UPS") ||
                        ship.getShipMethodDesc().matches("^BEST.*METHOD.*OVERNIGHT.*DEL") ||
                        ship.getShipMethodDesc().matches("^BEST.*METHOD.*OVERNIGHT.*NO.*CHARGE"))
                .map(CShippingMethod::getShipMethodId).collect(Collectors.toList());
        return ansValidateHippaList.stream().filter(dto -> shipMethodIds.contains(dto.getShipMethodId()))
                .anyMatch(dto -> isNull(dto.getPatientAddress(), "").matches("general.*delivery") ||
                        isNull(dto.getPatientAddress2(), "").matches("general.*delivery"));
    }

    /**
     * Check patient address.
     *
     * @param ansValidateHippaList The list of order dto.
     * @return The address error.
     */
    private AnsValidateHippaAddressError checkAddress(List<AnsValidateHippaOrderDTO> ansValidateHippaList) {
        if (ansValidateHippaList.isEmpty()) {
            return null;
        }
        AnsValidateHippaOrderDTO ansValidateHippa = ansValidateHippaList.get(0);
        //Line: 232
        if (ansValidateHippa.getVerified() != null
                && !ansValidateHippa.getVerified()) {
            //Line: 235
            if (ansValidateHippa.getOverrideReasonId() != null && ansValidateHippa.getOverrideReasonId().equals((byte) 0)) {
                return AnsValidateHippaAddressError.ADDRESS_IS_NOT_VERIFIED_AND_NOT_OVERRIDDEN;
                //Line: 241
            } else if (ansValidateHippa.getOverrideReasonId() != null &&
                    ansValidateHippa.getOverrideReasonId().equals((byte) 3) &&
                    !(ansValidateHippa.getLastVerification() != null
                            && Objects.equals(DATE_FORMAT.format(ansValidateHippa.getLastVerification()), DATE_FORMAT.format(System.currentTimeMillis())))) {
                return AnsValidateHippaAddressError.ADDRESS_IS_NOT_VERIFIED_AND_ADDRESS_SERVICE_IS_DOWN;
                //Line: 249
            } else if ((isNull(ansValidateHippa.getPatientAddress(), "").length() > 35)
                    || (isNull(ansValidateHippa.getPatientAddress2(), "").length() > 35)) {
                return AnsValidateHippaAddressError.ADDRESS_LINE_IS_OVERED;
            }
        }
        return null;
    }

    /**
     * Get list of orderDTO by order num.
     *
     * @param orderNum The order num
     * @return The list of order dto
     */
    private List<AnsValidateHippaOrderDTO> getAnsValidateHippa(BigInteger orderNum) {
        QOrderBillship qOrderBillship = QOrderBillship.orderBillship;
        QPatientAddress qPatientAddress = QPatientAddress.patientAddress1;
        QCityStateZip qCityStateZip = QCityStateZip.cityStateZip;
        QStates qStates = QStates.states;
        QPatientGeneral qPatientGeneral = QPatientGeneral.patientGeneral;
        QPatientFamily qPatientFamily = QPatientFamily.patientFamily;
        QPatientAddressTypeAssignments qPatientAddressTypeAssignments = QPatientAddressTypeAssignments.patientAddressTypeAssignments;
        return epostrxQueryFactory.select(Projections.bean(AnsValidateHippaOrderDTO.class,
                        qStates.consentAge.coalesce((byte) 18).as("consentAge"),
                        qOrderBillship.forceShipAddress.coalesce(VALUE_N).as("forceShipAddress"),
                        qPatientAddress.patientNum,
                        qOrderBillship.patientShipAddressSeq.coalesce(0L).as("patientShipAddressSeq"),
                        qOrderBillship.patientAddressSeq.coalesce(0L).as("patientAddressSeq"),
                        qOrderBillship.shipMethodId,
                        qPatientAddress.patientAddress,
                        qPatientAddress.patientAddress2,
                        qPatientAddress.active,
                        qPatientAddress.verified,
                        qPatientAddress.overrideReasonId,
                        qPatientAddress.lastVerification,
                        qPatientFamily.familyId))
                .from(qOrderBillship).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .leftJoin(qPatientAddress).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientAddress.patientAddrSeq.eq(qOrderBillship.patientShipAddressSeq))
                .leftJoin(qCityStateZip).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qCityStateZip.cszZipNum.eq(qPatientAddress.cszZipNum))
                .leftJoin(qStates).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qStates.stateNum.eq(qCityStateZip.stateNum))
                .leftJoin(qPatientGeneral).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientGeneral.patientNum.eq(qPatientAddress.patientNum))
                .leftJoin(qPatientFamily).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientFamily.patientNum.eq(qPatientAddress.patientNum))
                .leftJoin(qPatientAddressTypeAssignments).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientAddressTypeAssignments.patientAddrSeq.eq(qPatientAddress.patientAddrSeq))
                .where(qOrderBillship.orderNum.eq(orderNum))
                .fetch();
    }

    /**
     * Get list of scriptDTO by order num.
     *
     * @param orderNum The order num
     * @return The list of script dto
     */
    private List<AnsValidateHippaScriptDTO> getScripts(BigInteger orderNum) {
        QEScriptMsgAttributes qeScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        QOrderLines qOrderLines = QOrderLines.orderLines;
        QOrderHeader qOrderHeader = QOrderHeader.orderHeader;
        QOrderBillship qOrderBillship = QOrderBillship.orderBillship;
        QPatientFamily qPatientFamily = QPatientFamily.patientFamily;
        QPatientAddress qPatientAddress = QPatientAddress.patientAddress1;
        QRxFillAux qRxFillAux = QRxFillAux.rxFillAux;
        QPatientGeneral qPatientGeneral = QPatientGeneral.patientGeneral;
        QPatientPlans qPatientPlans = QPatientPlans.patientPlans;
        return epostrxQueryFactory.select(
                        Projections.bean(AnsValidateHippaScriptDTO.class,
                                qeScriptMsgAttributes.patientNum,
                                qPatientAddress.patientNum.as("paPatientNum"),
                                qOrderHeader.orderStatusNum,
                                qOrderBillship.familyId,
                                qPatientFamily.familyId.as("pfFamilyId"),
                                qOrderLines.orderLineStatusNum,
                                qPatientPlans.ppNum,
                                qeScriptMsgAttributes.otcPpnum,
                                qRxFillAux.ppNum.as("rfPpNum"),
                                qPatientGeneral.patientDob,
                                qPatientGeneral.generalStatusCode,
                                qPatientPlans.relationshipNum))
                .from(qeScriptMsgAttributes).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .leftJoin(qOrderLines).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderLines.eScriptMsgAttributeSeq.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq))
                .leftJoin(qOrderHeader).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderHeader.orderNum.eq(qOrderLines.orderNum))
                .leftJoin(qOrderBillship).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderBillship.orderNum.eq(qOrderLines.orderNum))
                .leftJoin(qPatientFamily).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientFamily.patientNum.eq(qeScriptMsgAttributes.patientNum))
                .leftJoin(qPatientAddress).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientAddress.patientAddrSeq.eq(qOrderBillship.patientShipAddressSeq))
                .leftJoin(qRxFillAux).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillAux.eScriptMsgAttributeSeq.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq))
                .leftJoin(qPatientGeneral).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientGeneral.patientNum.eq(qeScriptMsgAttributes.patientNum))
                .leftJoin(qPatientPlans).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientPlans.patientNum.eq(qPatientGeneral.patientNum))
                .where(qOrderLines.orderNum.eq(orderNum))
                .fetch();
    }

    /**
     * Get list of familyDTO by family id.
     *
     * @param familyId The family id
     * @return The list of family dto
     */
    private List<AnsValidateHippaFamilyDTO> getFamilies(String familyId) {
        QPatientFamily qPatientFamily = QPatientFamily.patientFamily;
        QPatientPlans qPatientPlans = QPatientPlans.patientPlans;
        QPatientAddress qPatientAddress = QPatientAddress.patientAddress1;
        return epostrxQueryFactory.select(
                        Projections.bean(AnsValidateHippaFamilyDTO.class,
                                qPatientFamily.patientNum,
                                qPatientFamily.headOfHousehold,
                                qPatientPlans.ppNum,
                                qPatientPlans.relationshipNum,
                                qPatientPlans.coverageTypeId,
                                qPatientAddress.patientAddrSeq))
                .from(qPatientFamily).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .leftJoin(qPatientPlans).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientPlans.patientNum.eq(qPatientFamily.patientNum))
                .leftJoin(qPatientAddress).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientAddress.patientNum.eq(qPatientFamily.patientNum))
                .where(qPatientFamily.familyId.eq(familyId))
                .fetch();
    }

    /**
     * Get list of patientDTO by list of patient num.
     *
     * @param patientNumList The list of patient num
     * @return The list of patient dto
     */
    private List<AnsValidateHippaPatientDTO> getPatient(List<BigInteger> patientNumList) {
        QPatientAddress qPatientAddress = QPatientAddress.patientAddress1;
        QPatientGeneral qPatientGeneral = QPatientGeneral.patientGeneral;
        QPatientAddressTypeAssignments qPatientAddressTypeAssignments = QPatientAddressTypeAssignments.patientAddressTypeAssignments;
        QCityStateZip qCityStateZip = QCityStateZip.cityStateZip;
        QStates qStates = QStates.states;
        QPatientFamily qPatientFamily = QPatientFamily.patientFamily;
        return epostrxQueryFactory.select(
                        Projections.bean(AnsValidateHippaPatientDTO.class,
                                qPatientAddress.patientNum,
                                qPatientFamily.familyId,
                                qStates.consentAge,
                                qPatientGeneral.patientDob,
                                qPatientAddress.active,
                                qPatientAddressTypeAssignments.addressTypeNum,
                                qPatientAddress.patientAddrSeq,
                                qPatientAddress.patientAddress,
                                qPatientAddress.patientAddress2,
                                qPatientAddress.cszZipNum))
                .from(qPatientAddress).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .leftJoin(qPatientGeneral).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientGeneral.patientNum.eq(qPatientAddress.patientNum))
                .leftJoin(qPatientAddressTypeAssignments).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientAddressTypeAssignments.patientAddrSeq.eq(qPatientAddress.patientAddrSeq))
                .leftJoin(qCityStateZip).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qCityStateZip.cszZipNum.eq(qPatientAddress.cszZipNum))
                .leftJoin(qStates).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qStates.stateNum.eq(qCityStateZip.stateNum))
                .leftJoin(qPatientFamily).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientFamily.patientNum.eq(qPatientAddress.patientNum))
                .where(qPatientAddress.patientNum.in(patientNumList))
                .fetch();
    }

    /**
     * Get ranking member.
     *
     * @param scriptList the script list
     * @param hipaa      the hipaa
     * @param isMinor    the adult/minor flag
     * @return the patient num
     */
    private BigInteger getRankingMember(List<AnsValidateHippaScriptDTO> scriptList, LocalDateTime hipaa,
                                        boolean isMinor) {
        //Line: 328-345 && 363-380
        return scriptList.stream().filter(dto ->
                        Objects.equals(dto.getOrderLineStatusNum(), (byte) 1) && "A".equals(dto.getGeneralStatusCode())
                                && ((isMinor && dto.getPatientDob().toLocalDateTime().isAfter(hipaa)) ||
                                ((!isMinor) && dto.getPatientDob().toLocalDateTime().isBefore(hipaa)))
                                && Objects.equals(dto.getPpNum(), isNull(dto.getOtcPpnum(), 2L) == 1L ? 2L : isNull(dto.getRfPpNum(), 2L))
                                && Objects.equals(dto.getRelationshipNum(), getMinRelationshipNum(scriptList, hipaa, dto.getPpNum(), isMinor)))
                .map(AnsValidateHippaScriptDTO::getPatientNum)
                .sorted()
                .findFirst()
                .orElse(BigInteger.ZERO);
    }

    /**
     * Get min of relationship num.
     *
     * @param scriptList the script list
     * @param hipaa      the hipaa
     * @param ppNum      the pp num
     * @param isMinor    the adult/minor flag
     * @return the min relationship num.
     */
    private Integer getMinRelationshipNum(List<AnsValidateHippaScriptDTO> scriptList, LocalDateTime hipaa,
                                          Long ppNum, boolean isMinor) {
        //Line: 347-359 && 382-394
        return scriptList.stream().filter(dto -> Objects.equals(dto.getOrderLineStatusNum(), (byte) 1) && "A".equals(dto.getGeneralStatusCode()) &&
                        ((isMinor && dto.getPatientDob().toLocalDateTime().isAfter(hipaa)) ||
                                ((!isMinor) && dto.getPatientDob().toLocalDateTime().isBefore(hipaa))) &&
                        dto.getPpNum().equals(ppNum))
                .map(AnsValidateHippaScriptDTO::getRelationshipNum)
                .filter(Objects::nonNull)
                .sorted()
                .findFirst()
                .orElse(null);
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

