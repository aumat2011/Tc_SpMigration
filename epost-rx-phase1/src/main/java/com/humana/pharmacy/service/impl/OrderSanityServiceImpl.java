package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.dto.OrderSanityResult;
import com.humana.pharmacy.service.OrderSanityService;
import com.humana.pharmacy.common.Helper;
import com.humana.pharmacy.common.entity.QBenefitResponseCodes;
import com.humana.pharmacy.common.entity.QEScriptMsgAttributes;
import com.humana.pharmacy.common.entity.QEcsResponses;
import com.humana.pharmacy.common.entity.QGeneralLedger;
import com.humana.pharmacy.common.entity.QOrderBillship;
import com.humana.pharmacy.common.entity.QOrderHeader;
import com.humana.pharmacy.common.entity.QOrderLines;
import com.humana.pharmacy.common.entity.QPatientAddress;
import com.humana.pharmacy.common.entity.QPatientAddressTypeAssignments;
import com.humana.pharmacy.common.entity.QPatientGeneral;
import com.humana.pharmacy.common.entity.QPatientPlans;
import com.humana.pharmacy.common.entity.QPaymentCardReply;
import com.humana.pharmacy.common.entity.QPayorPlans;
import com.humana.pharmacy.common.entity.QPayors;
import com.humana.pharmacy.common.entity.QRules;
import com.humana.pharmacy.common.entity.QRxFillAux;
import com.humana.pharmacy.common.entity.QThirdPartyClaimRequests;
import com.humana.pharmacy.common.entity.QThirdPartyClaimResponses;
import com.humana.pharmacy.common.model.EScriptMsgAttributes;
import com.humana.pharmacy.common.model.OrderBillship;
import com.humana.pharmacy.common.model.OrderHeader;
import com.humana.pharmacy.common.model.OrderLines;
import com.humana.pharmacy.common.model.PatientGeneral;
import com.humana.pharmacy.common.model.PatientPlans;
import com.humana.pharmacy.common.model.PayorPlans;
import com.humana.pharmacy.common.model.Payors;
import com.humana.pharmacy.common.service.impl.BaseServiceImpl;
import com.humana.pharmacy.constants.OrderSanityError;
import com.humana.pharmacy.dto.OrderDTO;
import com.humana.pharmacy.dto.OrderEscriptDTO;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLQuery;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

/**
 * Implementation of OrderSanityService using QueryDSL. It extends BaseServiceImpl.
 */
public class OrderSanityServiceImpl extends BaseServiceImpl implements OrderSanityService {
    /**
     * The status '1'.
     */
    private static final byte ORDER_LINE_STATUS_NUM_1 = 1;
    /**
     * The status '3'.
     */
    private static final byte ORDER_LINE_STATUS_NUM_3 = 3;
    /**
     * The type '2'.
     */
    private static final int PAYOR_BILL_TYPE_NUM_2 = 2;
    /**
     * The type 'KIOSK PHARMACY'.
     */
    private static final String TPT_KIOSK = "KIOSK PHARMACY";
    /**
     * The number 18.
     */
    private static final int EIGHTEEN_YEARS = 18;
    /**
     * The keyword 'SAME_ORDER_PROCESS_MULTIPLE_PATIENT'.
     */
    private static final String STATUS_KEYWORD_MULTIPLE_PATIENT = "SAME_ORDER_PROCESS_MULTIPLE_PATIENT";
    /**
     * The status 'A'.
     */
    private static final String STATUS_A = "A";
    /**
     * The status 'A'.
     */
    private static final String PP_STATUS_A = "A";
    /**
     * The type 'A'.
     */
    private static final String PP_TYPE_A = "A";
    /**
     * The status 'A'.
     */
    private static final String PATIENT_STATUS_A = "A";
    /**
     * The join flag ' WITH (NOLOCK) '.
     */
    private static final String JOIN_FLAG = " WITH (NOLOCK) ";

    /**
     * Constructor of OrderSanityCheckServiceImpl.
     *
     * @param epostrxDataSource  DataSource for epostrx database. Required
     * @param workflowDataSource DataSource for epostrx_workflow database. Required
     * @throws RuntimeException if epostrxDataSource or workflowDataSource is null
     */
    public OrderSanityServiceImpl(DataSource epostrxDataSource, DataSource workflowDataSource) {
        super(epostrxDataSource, workflowDataSource);
    }

    /**
     * Check order sanity.
     *
     * @param orderNum      The order num. Required
     * @param queueToBeUsed The queue to be used. Optional
     * @return Order sanity check result. May be null if no error found
     * @throws RuntimeException if the orderNum is null or any other error occurs
     */
    public OrderSanityResult checkOrderSanity(BigInteger orderNum, String queueToBeUsed) {
        Helper.checkNull(orderNum, "orderNum");

        // Get order
        CompletableFuture<OrderDTO> orderFuture = CompletableFuture.supplyAsync(() -> getOrder(orderNum));

        // Get escripts
        CompletableFuture<List<OrderEscriptDTO>> escriptsFuture = CompletableFuture.supplyAsync(() -> getEscripts(orderNum));

        OrderDTO order;
        List<OrderEscriptDTO> escripts;
        try {
            order = orderFuture.join();
            escripts = escriptsFuture.join();
        } catch (CompletionException e) {
            throw new RuntimeException(e);
        }

        Long tpNum = order.getHeader().getTradingPartnerNum();

        OrderSanityResult result = new OrderSanityResult();
        result.setErrors(new ArrayList<>());

        if (checkMultiplePatients(escripts) != null) {
            // refer to P_ordersanitycheck 620 ~ 632
            result.getErrors().add(OrderSanityError.MULTIPLE_PATIENTS);
            result.setOverrideQname("HIPAA VERIFICATION QUEUE");
            result.setOverrideQueue(getWorkflowQueueNumber("HIPAA VERIFICATION QUEUE", tpNum));
            return result;
        }

        List<String> expiredRxs = checkExpiredRxs(escripts);
        if (!expiredRxs.isEmpty()) {
            // refer to P_ordersanitycheck 656 ~ 666
            result.getErrors().add(OrderSanityError.EXPIRED_RX);
            result.setExpiredRxs(expiredRxs);
            result.setOverrideQname("DR. CALL TECH FAX");
            result.setOverrideQueue(getWorkflowQueueNumber("DR. CALL TECH FAX", tpNum));
            return result;
        }

        List<String> insufficientRxs = checkInsufficientRxs(escripts);
        if (!insufficientRxs.isEmpty()) {
            // refer to P_ordersanitycheck 700 ~ 711
            result.getErrors().add(OrderSanityError.INSUFFICIENT_RX);
            result.setInsufficientRxs(insufficientRxs);
            result.setOverrideQname("DR. CALL TECH FAX");
            result.setOverrideQueue(getWorkflowQueueNumber("DR. CALL TECH FAX", tpNum));
            return result;
        }

        if (checkNotPaidEcs(order, escripts) != null) {
            // refer to P_ordersanitycheck 755 ~ 766
            result.getErrors().add(OrderSanityError.NOT_PAID_RX);
            result.setOverrideQname("FAILED CLAIMS TASK APPROVAL");
            result.setOverrideQueue(getWorkflowQueueNumber("FAILED CLAIMS TASK APPROVAL", tpNum));
            return result;
        }


        if (order.getHeader().getOrderStatusNum() != null
                && order.getHeader().getOrderStatusNum() != 1) {
            // refer to P_ordersanitycheck 771 ~ 777
            result.getErrors().add(OrderSanityError.ORDER_NOT_OPEN);
        }

        if (checkWrongPatient(order, escripts) != null) {
            // refer to P_ordersanitycheck 780 ~ 787
            result.getErrors().add(OrderSanityError.WRONG_PATIENT_ON_ORDER);
        }

        if (checkHippaBadAddress(order, escripts) != null) {
            // refer to P_ordersanitycheck 789 ~ 802
            result.getErrors().add(OrderSanityError.HIPAA_BAD_ADDRESS);
            result.setOverrideQname("HIPAA VERIFICATION QUEUE");
            result.setOverrideQueue(getWorkflowQueueNumber("HIPAA VERIFICATION QUEUE", tpNum));
        }

        OrderSanityError addressError = checkAddressError(order, escripts);
        if (addressError != null) {
            // refer to P_ordersanitycheck 806 ~ 839
            result.getErrors().add(addressError);

            if (addressError == OrderSanityError.NO_BILL_ADDRESS || addressError == OrderSanityError.NO_SHIP_ADDRESS) {
                result.setOverrideQname("HIPAA VERIFICATION QUEUE");
                result.setOverrideQueue(getWorkflowQueueNumber("HIPAA VERIFICATION QUEUE", tpNum));
            } else if (addressError == OrderSanityError.NO_PROPER_SHIP_MEMBER) {
                result.setOverrideQname("FAILED CLAIMS");
                result.setOverrideQueue(getWorkflowQueueNumber("FAILED CLAIMS", tpNum));
            }
        }

        if (checkAuthRecord(order) != null) {
            // refer to P_ordersanitycheck 844 ~ 861
            result.getErrors().add(OrderSanityError.NO_AUTH_RECORD);
            result.setOverrideQname("CREDIT CARD EXCEPTIONS QUEUE");

            if (queueToBeUsed != null) {
                QRules qRules = QRules.rules;
                long queueCount = workflowQueryFactory.from(qRules).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                        .where(qRules.ruleName.eq("FINANCE QUEUE").and(qRules.ruleEnabled.eq("Y")))
                        .fetchCount();
                if (queueCount > 0) {
                    result.setOverrideQname("0".equals(queueToBeUsed) ? "FINANCE" : "FINANCE OTC");
                }
            }
            result.setOverrideQueue(getWorkflowQueueNumber(result.getOverrideQname(), tpNum));
        }
        return result.getErrors().isEmpty() ? null : result;
    }

    /**
     * Checks if single order has multiple patients attached.
     *
     * @param escripts The order escripts
     * @return Sanity check error. May be null if no error found
     */
    private OrderSanityError checkMultiplePatients(List<OrderEscriptDTO> escripts) {
        // Refer to P_ordersanitycheck line 190 ~ 206
        if (STATUS_A.equals(getCodeValueStatus(STATUS_KEYWORD_MULTIPLE_PATIENT))) {
            Set<BigInteger> patientNums = escripts.stream()
                    .filter(e -> e.getOrderLine().getOrderLineStatusNum() == ORDER_LINE_STATUS_NUM_1)
                    .filter(e -> e.getPatient().getPatientNum() != null)
                    .map(e -> e.getPatient().getPatientNum())
                    .collect(Collectors.toSet());

            if (patientNums.size() > 1) {
                return OrderSanityError.MULTIPLE_PATIENTS;
            }
        }
        return null;
    }

    /**
     * Check expired Rxs.
     *
     * @param escripts The order escripts
     * @return List of Rx numbers which are expired
     */
    private List<String> checkExpiredRxs(List<OrderEscriptDTO> escripts) {
        // Refer to P_ordersanitycheck line 640 ~ 654
        Date current = new Date();
        Set<String> expiredRxNumbers = escripts.stream()
                .filter(e -> e.getOrderLine().getOrderLineStatusNum() == ORDER_LINE_STATUS_NUM_1)
                .filter(e -> e.getEscript().getRxExpirationDate() != null && e.getEscript().getRxExpirationDate().before(current))
                .filter(e -> e.getEscript().getRxNumber() != null)
                .map(e -> e.getEscript().getRxNumber())
                .collect(Collectors.toSet());
        return new ArrayList<>(expiredRxNumbers);
    }

    /**
     * Check Rxs has insufficient quantity to fill.
     *
     * @param escripts The order escripts
     * @return List of Rx numbers which has insufficient quantity to fill.
     */
    private List<String> checkInsufficientRxs(List<OrderEscriptDTO> escripts) {
        // Refer to P_ordersanitycheck line 678 ~ 698
        Set<String> insufficientRxNumbers = escripts.stream()
                .filter(e -> e.getOrderLine().getOrderLineStatusNum() == ORDER_LINE_STATUS_NUM_1)
                .filter(e -> {
                    EScriptMsgAttributes escript = e.getEscript();
                    // If any of the number used here is null, return false
                    if (escript.getNumRefills() == null
                            || escript.getWrittenQuantity() == null
                            || escript.getDispensedQuantity() == null
                            || e.getTotalDispensedQuantity() == null) {
                        return false;
                    }
                    return (escript.getNumRefills() + 1) * escript.getWrittenQuantity() < e.getTotalDispensedQuantity() + escript.getDispensedQuantity();
                })
                .filter(e -> e.getEscript().getRxNumber() != null)
                .map(e -> e.getEscript().getRxNumber())
                .collect(Collectors.toSet());
        return new ArrayList<>(insufficientRxNumbers);
    }

    /**
     * Checks if order contains RXs without a paid ecs status.
     *
     * @param order    The order to check
     * @param escripts The order escripts
     * @return Sanity check error. May be null if no error found
     */
    private OrderSanityError checkNotPaidEcs(OrderDTO order, List<OrderEscriptDTO> escripts) {
        // Refer to P_ordersanitycheck line 722 ~ 752
        // skip reships
        if (order.getHeader().getReplaceParentOrderNum() == null) {
            List<OrderEscriptDTO> notPaid = escripts.stream()
                    .filter(e -> e.getOrderLine().getOrderLineStatusNum() == ORDER_LINE_STATUS_NUM_1
                            || e.getOrderLine().getOrderLineStatusNum() == ORDER_LINE_STATUS_NUM_3)
                    .filter(e -> PP_STATUS_A.equals(e.getPayorPlan().getGeneralStatusCode()))
                    .filter(e -> PP_TYPE_A.equals(e.getPayorPlan().getPpTypeCode())
                            || "C".equals(e.getPayorPlan().getPpTypeCode()))
                    .filter(e -> e.getPayor().getPayorBillTypeNum() == PAYOR_BILL_TYPE_NUM_2)
                    .filter(e -> e.getPaidClaimId() == null && e.getPaidEcsNum() == null)
                    .collect(Collectors.toList());

            if (!notPaid.isEmpty()) {
                return OrderSanityError.NOT_PAID_RX;
            }
        }
        return null;
    }

    /**
     * Checks if order contains wrong patient member not in same family. Make sure that all members receiving items on this shipment are part of the same family.
     *
     * @param order    The order to check
     * @param escripts The order escripts
     * @return Sanity check error. May be null if no error found
     */
    private OrderSanityError checkWrongPatient(OrderDTO order, List<OrderEscriptDTO> escripts) {
        // Refer to P_ordersanitycheck line 224 ~ 241
        if (order.getBillship().getFamilyId() == null) {
            return null;
        }

        if ((order.getHeader().getOrderStatusNum() == null)
                || (order.getHeader().getOrderStatusNum() != 1 && order.getHeader().getOrderStatusNum() != 2)) {
            return null;
        }

        if (escripts.stream()
                .filter(e -> e.getOrderLine().getOrderLineStatusNum() == ORDER_LINE_STATUS_NUM_1)
                .anyMatch(e -> !order.getBillship().getFamilyId().equals(e.getPatient().getFamilyId()))) {
            return OrderSanityError.WRONG_PATIENT_ON_ORDER;
        }
        return null;
    }

    /**
     * Checks HIPAA ship-to violation. Make sure we are using the shipping address of a member receiving items in the shipment.
     *
     * @param order    The order to check
     * @param escripts The order escripts
     * @return Sanity check error. May be null if no error found
     */
    private OrderSanityError checkHippaBadAddress(OrderDTO order, List<OrderEscriptDTO> escripts) {
        // Refer to P_ordersanitycheck line 243 ~ 270
        if (order.getBillship().getFamilyId() == null || TPT_KIOSK.equals(order.getTradingPartnerType())) {
            return null;
        }

        if (order.getHeader().getOrderStatusNum() == null
                || (order.getHeader().getOrderStatusNum() != 1 && order.getHeader().getOrderStatusNum() != 2)) {
            return null;
        }

        if (escripts.stream()
                .filter(e -> e.getOrderLine().getOrderLineStatusNum() == ORDER_LINE_STATUS_NUM_1)
                .noneMatch(e -> e.getPatient() != null && order.getShipPatientNum() != null && order.getShipPatientNum().equals(e.getPatient().getPatientNum()))) {
            return OrderSanityError.HIPAA_BAD_ADDRESS;
        }
        return null;
    }

    /**
     * Find ranking adult/minor member in the shipment.
     *
     * @param order    The order to get ranking member
     * @param escripts The order escripts
     * @param adult    Whether to get adult member or minor member
     * @return The member or null if not found
     */
    private OrderEscriptDTO findRankingMember(OrderDTO order, List<OrderEscriptDTO> escripts, boolean adult) {
        // Refer to P_ordersanitycheck line 410 ~ 494
        Timestamp now = Timestamp.from(Instant.now());
        Timestamp eighteenYearsAgo = Timestamp.valueOf(LocalDateTime.now().minus(EIGHTEEN_YEARS, ChronoUnit.YEARS));

        // Filter valid active patient belong to family with adult/minor age
        List<OrderEscriptDTO> filtered = escripts.stream()
                .filter(e -> e.getPatientPlan().getPpNum() != null)
                .filter(e -> order.getBillship().getFamilyId().equals(e.getPatient().getFamilyId()))
                .filter(e -> PATIENT_STATUS_A.equals(e.getPatient().getGeneralStatusCode()))
                .filter(e -> e.getPatient().getPatientDob() != null && (adult ? e.getPatient().getPatientDob().before(eighteenYearsAgo) : e.getPatient().getPatientDob().after(eighteenYearsAgo)))
                .collect(Collectors.toList());

        // Group by ppNum
        Map<Long, List<OrderEscriptDTO>> ppNumGroups = filtered.stream().collect(Collectors.groupingBy(e -> e.getPatientPlan().getPpNum()));

        // Find the min relationship number
        Map<Long, Integer> minRelationshipNums = new HashMap<>();
        for (Long ppNum : ppNumGroups.keySet()) {
            List<OrderEscriptDTO> group = ppNumGroups.get(ppNum);
            Integer minRelationNum = group.stream()
                    .filter(e -> e.getPatientPlan().getRelationshipNum() != null)
                    .map(e -> e.getPatientPlan().getRelationshipNum()).min(Integer::compare).orElse(-1);
            minRelationshipNums.put(ppNum, minRelationNum);
        }

        // Filter again
        filtered = filtered.stream()
                .filter(e -> e.getPatientPlan().getCoverageTypeId() == null || e.getPatientPlan().getCoverageTypeId() != 4)
                .filter(e -> e.getPatientPlan().getCpDeactivationDate() == null || e.getPatientPlan().getCpDeactivationDate().after(now))
                .filter(e -> e.getPatientPlan().getCpExpirationDate() == null || e.getPatientPlan().getCpExpirationDate().after(now))
                .filter(e -> e.getPatientPlan().getRelationshipNum() != null && e.getPatientPlan().getRelationshipNum().equals(minRelationshipNums.get(e.getPatientPlan().getPpNum())))
                .collect(Collectors.toList());

        // Find the min patient number
        return filtered.stream().min(Comparator.comparing(e -> e.getPatient().getPatientNum())).orElse(null);
    }

    /**
     * Check address error.
     *
     * @param order    The order to check
     * @param escripts The order escripts
     * @return Sanity check error. May be null if no error found
     */
    private OrderSanityError checkAddressError(OrderDTO order, List<OrderEscriptDTO> escripts) {
        // Refer to P_ordersanitycheck line 272 ~ 572
        if (TPT_KIOSK.equals(order.getTradingPartnerType())) {
            return null;
        }

        Long billAddress;
        Long shipAddress;

        if (order.getBillship().getFamilyId() == null) {
            // not family mode, check bill and ship address
            billAddress = order.getBillAddress();
            shipAddress = order.getShipAddress();
        } else {
            // family mode, check that shipment is addressed to proper member within the shipment

            // Find adult ranking member
            OrderEscriptDTO rankingMember = findRankingMember(order, escripts, true);
            if (rankingMember == null) {
                // Find minor ranking member
                rankingMember = findRankingMember(order, escripts, false);
            }
            if (rankingMember == null) {
                return OrderSanityError.NO_PROPER_SHIP_MEMBER;
            }

            billAddress = rankingMember.getBillAddress();
            shipAddress = rankingMember.getShipAddress();
        }

        if (billAddress == null) {
            // No bill address
            return OrderSanityError.NO_BILL_ADDRESS;
        }

        if (shipAddress == null) {
            // No ship address
            return OrderSanityError.NO_SHIP_ADDRESS;
        }

        return null;
    }

    /**
     * Check if there is a valid AUTH record.
     *
     * @param order The order to check
     * @return Sanity check error. May be null if no error found
     */
    private OrderSanityError checkAuthRecord(OrderDTO order) {
        // Refer to P_ordersanitycheck line 574 ~ 614
        if (order.getBillship().getPaymentCardSeqNum() != null &&
                order.getBillship().getPaymentCardSeqNum() > 0 &&
                order.getHeader().getOrderTotalAmount() != null &&
                order.getHeader().getOrderTotalAmount() > 0 &&
                (order.getAuthRecordNum() == null ||
                        order.getAuthRecordNum().compareTo(BigInteger.valueOf(0)) <= 0)) {
            return OrderSanityError.NO_AUTH_RECORD;
        }
        return null;
    }

    /**
     * Get active address of patient for given address type.
     * <p>
     * Refer to P_ordersanitycheck line 294 ~ 339
     *
     * @param patientNum     The patient number expression
     * @param addressTypeNum The address type number
     * @return Query for the address
     */
    private SQLQuery<Long> getActiveAddress(NumberExpression<BigInteger> patientNum, int addressTypeNum) {
        // Refer to P_ordersanitycheck line 294 ~ 339
        QPatientAddress qPatientAddress = new QPatientAddress("pa");
        QPatientAddressTypeAssignments qAddressType = new QPatientAddressTypeAssignments("pata");

        return SQLExpressions
                .select(qPatientAddress.patientAddrSeq)
                .limit(1)
                .from(qPatientAddress).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .join(qAddressType).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientAddress.patientAddrSeq.eq(qAddressType.patientAddrSeq))
                .where(qPatientAddress.patientNum.eq(patientNum)
                        .and(qAddressType.addressTypeNum.eq(addressTypeNum))
                        .and(qPatientAddress.active.eq("Y")));
    }

    /**
     * Get order data.
     *
     * @param orderNum The order number
     * @return Order data dto
     * @throws IllegalArgumentException if order can't be found
     */
    private OrderDTO getOrder(BigInteger orderNum) {
        QOrderHeader qOrderHeader = QOrderHeader.orderHeader;
        QOrderBillship qOrderBillship = QOrderBillship.orderBillship;
        QPatientAddress qPatientAddress = QPatientAddress.patientAddress1;
        QPaymentCardReply pcr = QPaymentCardReply.paymentCardReply;
        QGeneralLedger gl = QGeneralLedger.generalLedger;

        // Get auth record, Refer to P_ordersanitycheck line 574 ~ 614
        SQLQuery<BigInteger> authRecordNum = SQLExpressions
                .select(pcr.glPostNum.max())
                .from(pcr).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .join(gl).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(gl.glPostNum.eq(pcr.glPostNum))
                .where(pcr.pcReplyType.eq("A")
                        .and(pcr.pcReplyAuthCode.isNotNull())
                        .and(pcr.glPostNum.eq(qOrderHeader.orderGlPosted)));

        OrderDTO order = epostrxQueryFactory
                .select(Projections.bean(OrderDTO.class,
                        Projections.bean(OrderHeader.class,
                                qOrderHeader.orderNum,
                                qOrderHeader.orderStatusNum,
                                qOrderHeader.orderTotalAmount.coalesce(0L).as("orderTotalAmount"),
                                qOrderHeader.tradingPartnerNum,
                                qOrderHeader.replaceParentOrderNum).as("header"),
                        Projections.bean(OrderBillship.class,
                                qOrderBillship.familyId,
                                qOrderBillship.paymentCardSeqNum.coalesce(0L).as("paymentCardSeqNum")).as("billship"),
                        authRecordNum.as("authRecordNum"),
                        getActiveAddress(qOrderBillship.orderPaymentOwner, 10).as("billAddress"),
                        getActiveAddress(qPatientAddress.patientNum, 11).as("shipAddress"),
                        qPatientAddress.patientNum.as("shipPatientNum")
                ))
                .from(qOrderHeader).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .leftJoin(qOrderBillship).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderBillship.orderNum.eq(qOrderHeader.orderNum))
                .leftJoin(qPatientAddress).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderBillship.patientShipAddressSeq.eq(qPatientAddress.patientAddrSeq))
                .where(qOrderHeader.orderNum.eq(orderNum))
                .fetchFirst();

        if (order == null) {
            // raise illegal argument exception saying order is not found
            throw new IllegalArgumentException("Order not found");
        }

        if (order.getHeader() != null && order.getHeader().getTradingPartnerNum() != null) {
            order.setTradingPartnerType(getTradingPartnerType(order.getHeader().getTradingPartnerNum()));
        }

        return order;
    }

    /**
     * Get order's escripts data.
     *
     * @param orderNum The order number
     * @return Order's escripts data
     */
    private List<OrderEscriptDTO> getEscripts(BigInteger orderNum) {
        QOrderHeader qOrderHeader = QOrderHeader.orderHeader;
        QOrderLines qOrderLine = QOrderLines.orderLines;
        QEScriptMsgAttributes qEscript = QEScriptMsgAttributes.eScriptMsgAttributes;
        QRxFillAux qRxFillAux = QRxFillAux.rxFillAux;
        QPatientPlans qPatientPlans = QPatientPlans.patientPlans;
        QPatientGeneral qPatientGeneral = QPatientGeneral.patientGeneral;
        QPayors qPayors = QPayors.payors;
        QPayorPlans qPayorPlans = QPayorPlans.payorPlans;
        QEcsResponses qEcsResponse = QEcsResponses.ecsResponses;
        QBenefitResponseCodes qBenifitCode = QBenefitResponseCodes.benefitResponseCodes;
        QThirdPartyClaimRequests qClaimRequest = QThirdPartyClaimRequests.thirdPartyClaimRequests;
        QThirdPartyClaimResponses qClaimRepsonse = QThirdPartyClaimResponses.thirdPartyClaimResponses;
        QEScriptMsgAttributes esma = new QEScriptMsgAttributes("esma");
        QEScriptMsgAttributes esmao = new QEScriptMsgAttributes("esmao");

        // The total dispensed quantity
        // refer to P_ordersanitycheck line 677 ~ 686
        SQLQuery<Long> totalDispensed = SQLExpressions
                .select(esma.dispensedQuantity.sum().as("totalDispensedQuantity"))
                .from(esma).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .join(qRxFillAux).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(esma.eScriptMsgAttributeSeq.eq(qRxFillAux.eScriptMsgAttributeSeq))
                .join(esmao).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillAux.rxNumber.eq(esmao.rxNumber))
                .join(qOrderHeader).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderHeader.orderInvoiceNumber.eq(esmao.orderInvoiceNumber))
                .where(qOrderHeader.orderNum.eq(orderNum)
                        .and(qRxFillAux.fillStatusNum.in(2, 9))
                        .and(esma.rxNumber.eq((qEscript.rxNumber))))
                .groupBy(esma.rxNumber);

        // The paid claim and ECS
        // Refer to F_isPrimaryEcsPaid function
        SQLQuery<Long> paidClaim = SQLExpressions
                .select(qClaimRequest.id)
                .limit(1)
                .from(qClaimRequest).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .join(qClaimRepsonse).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qClaimRequest.transactionId.eq(qClaimRepsonse.transactionId))
                .join(qBenifitCode).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qClaimRepsonse.responseCode.eq(qBenifitCode.id))
                .where(qClaimRequest.transactionCode.eq("LS")
                        .and(qBenifitCode.approvalCode.eq("AA"))
                        .and(qClaimRequest.eScriptMsgAttributeSeq.castToNum(BigInteger.class).eq(qEscript.eScriptMsgAttributeSeq)));
        SQLQuery<BigInteger> paidEsc = SQLExpressions
                .select(qEcsResponse.ecsRespSeqNum)
                .limit(1)
                .from(qEcsResponse).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .where(qEcsResponse.ecsResponseStatusType.in("P", "C", "D")
                        .and(qEcsResponse.eScriptMsgAttributeSeq.eq(qEscript.eScriptMsgAttributeSeq))
                        .and(qEcsResponse.ecsRespSeqNum.eq(qRxFillAux.fillEcsStatus)));

        return epostrxQueryFactory.query()
                .select(Projections.bean(OrderEscriptDTO.class,
                        Projections.bean(OrderLines.class,
                                qOrderLine.orderLineStatusNum).as("orderLine"),
                        Projections.bean(EScriptMsgAttributes.class,
                                qEscript.rxNumber,
                                qEscript.rxExpirationDate,
                                qEscript.numRefills,
                                qEscript.writtenQuantity,
                                qEscript.dispensedQuantity).as("escript"),
                        Projections.bean(PayorPlans.class,
                                qPayorPlans.generalStatusCode,
                                qPayorPlans.ppTypeCode).as("payorPlan"),
                        Projections.bean(Payors.class,
                                qPayors.payorBillTypeNum).as("payor"),
                        Projections.bean(PatientGeneral.class,
                                qPatientGeneral.patientNum,
                                qPatientGeneral.familyId,
                                qPatientGeneral.generalStatusCode,
                                qPatientGeneral.patientDob).as("patient"),
                        Projections.bean(PatientPlans.class,
                                qPatientPlans.ppNum,
                                qPatientPlans.coverageTypeId,
                                qPatientPlans.cpDeactivationDate,
                                qPatientPlans.cpExpirationDate,
                                qPatientPlans.relationshipNum).as("patientPlan"),
                        paidClaim.as("paidClaimId"),
                        paidEsc.as("paidEcsNum"),
                        getActiveAddress(qEscript.patientNum, 10).as("billAddress"),
                        getActiveAddress(qEscript.patientNum, 11).as("shipAddress"),
                        totalDispensed.as("totalDispensedQuantity")
                ))
                .from(qEscript).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .join(qOrderLine).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderLine.eScriptMsgAttributeSeq.eq(qEscript.eScriptMsgAttributeSeq))
                .leftJoin(qRxFillAux).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillAux.eScriptMsgAttributeSeq.eq(qEscript.eScriptMsgAttributeSeq))
                .leftJoin(qPayorPlans).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPayorPlans.ppNum.eq(qRxFillAux.ppNum))
                .leftJoin(qPayors).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPayorPlans.payorNum.eq(qPayors.payorNum))
                .leftJoin(qPatientGeneral).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientGeneral.patientNum.eq(qEscript.patientNum))
                .leftJoin(qPatientPlans).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientPlans.patientNum.eq(qEscript.patientNum)
                        .and(qPatientPlans.ppNum.eq(Expressions.cases().when(qEscript.otcPpnum.coalesce(0L).eq(1L)).then(2L)
                                .otherwise(qRxFillAux.ppNum.coalesce(2L)))))
                .where(qOrderLine.orderNum.eq(orderNum))
                .fetch();
    }
}

