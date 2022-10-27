package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.TestsHelper;
import com.humana.pharmacy.dto.OrderSanityResult;
import com.humana.pharmacy.common.cache.model.CWorkflowQueue;
import com.humana.pharmacy.common.entity.QRules;
import com.humana.pharmacy.common.model.EScriptMsgAttributes;
import com.humana.pharmacy.common.model.OrderBillship;
import com.humana.pharmacy.common.model.OrderHeader;
import com.humana.pharmacy.common.model.OrderLines;
import com.humana.pharmacy.common.model.PatientGeneral;
import com.humana.pharmacy.common.model.PatientPlans;
import com.humana.pharmacy.common.model.PayorPlans;
import com.humana.pharmacy.common.model.Payors;
import com.humana.pharmacy.constants.OrderSanityError;
import com.humana.pharmacy.dto.OrderDTO;
import com.humana.pharmacy.dto.OrderEscriptDTO;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit test cases for OrderSanityServiceImpl;
 */
@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class OrderSanityServiceImplTest {
    /**
     * The number 60 * 60 * 1000.
     */
    private static final long ONE_HOUR_IN_MILLIS = 60 * 60 * 1000;
    /**
     * The "epostrx" DataSource instance.
     */
    @Mock
    private DataSource epostrxDataSource;

    /**
     * The "epostrx_workflow" DataSource instance.
     */
    @Mock
    private DataSource workflowDataSource;

    /**
     * The SQLQuery instance.
     */
    @Mock
    private SQLQuery query;

    /**
     * The Workflow SQLQuery instance.
     */
    @Mock
    private SQLQuery workflowQuery;

    /**
     * The OrderSanityServiceImpl instance.
     */
    private OrderSanityServiceImpl orderSanityService;

    /**
     * The order number.
     */
    private final BigInteger orderNum = BigInteger.valueOf(1);

    /**
     * The queue to be used.
     */
    private String queueToBeUsed = "1";

    /**
     * The order.
     */
    private OrderDTO order;
    /**
     * The escripts.
     */
    private List<OrderEscriptDTO> escripts;

    /**
     * The code value status flag.
     */
    private String codeValueStatus = "status1";

    /**
     * The trading partner type.
     */
    private String tradingPartnerType = "tradingPartnerType1";

    /**
     * The 'epostrx' query factory.
     */
    @Mock
    private SQLQueryFactory epostrxQueryFactory;
    /**
     * The 'epostrx orkflow' query factory.
     */
    @Mock
    private SQLQueryFactory workflowQueryFactory;

    @BeforeEach
    public void setUp() {
        orderSanityService = new OrderSanityServiceImpl(epostrxDataSource, workflowDataSource);

        TestsHelper.setFieldValue(orderSanityService, "epostrxQueryFactory", epostrxQueryFactory);
        TestsHelper.setFieldValue(orderSanityService, "workflowQueryFactory", workflowQueryFactory);

        order = getOrder();
    }

    private void setUpMock(boolean workflowMock) {
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.with(any(Path.class), any(SubQueryExpression.class))).thenReturn(query);
        when(query.select(any(Expression.class))).thenReturn(query);
        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.query()).thenReturn(query);
        if (workflowMock) {
            when(workflowQueryFactory.select(any(Expression.class))).thenReturn(workflowQuery);
            when(workflowQuery.from(any(Expression.class))).thenReturn(workflowQuery);
            when(workflowQuery.join(any(EntityPath.class))).thenReturn(workflowQuery);
            when(workflowQuery.on(any(Predicate.class))).thenReturn(workflowQuery);
            when(workflowQuery.where(any(Predicate.class))).thenReturn(workflowQuery);
            when(workflowQuery.fetch()).thenReturn(getQueues());
        }
    }

    @Test
    public void test_ctor_epostrxDataSourceNull() {
        assertThrows(RuntimeException.class, () -> new OrderSanityServiceImpl(null, workflowDataSource));
    }

    @Test
    public void test_ctor_workflowDataSourceNull() {
        assertThrows(RuntimeException.class, () -> new OrderSanityServiceImpl(epostrxDataSource, null));
    }

    @Test
    public void test_checkOrderSanity_orderNumNull() {
        assertThrows(RuntimeException.class, () -> orderSanityService.checkOrderSanity(null, queueToBeUsed));
    }

    @Test
    public void test_checkOrderSanity_orderNotFound() {
        order = null;

        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.with(any(Path.class), any(SubQueryExpression.class))).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchFirst()).thenReturn(null);

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.query()).thenReturn(query);
        assertThrows(RuntimeException.class, () -> orderSanityService.checkOrderSanity(BigInteger.valueOf(-1), queueToBeUsed));
    }

    @Test
    public void test_checkOrderSanity_multiplePatients_patientNums_eq_1() {
        escripts = getEscripts();
        codeValueStatus = "A";
        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(!res.getErrors().contains(OrderSanityError.MULTIPLE_PATIENTS));
        assertEquals("DR. CALL TECH FAX", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_multiplePatients_patientNums_gt_1() {
        escripts = getEscripts(2);
        escripts.get(1).getPatient().setPatientNum(BigInteger.valueOf(2));
        codeValueStatus = "A";
        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.MULTIPLE_PATIENTS));
        assertEquals("HIPAA VERIFICATION QUEUE", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_expiredRxs() {
        escripts = getEscripts();
        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.EXPIRED_RX));
        assertEquals("DR. CALL TECH FAX", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_insufficientRxs() {
        escripts = getEscripts();
        codeValueStatus = "A";
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).getPatient().setPatientNum(null);
        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.INSUFFICIENT_RX));
        assertEquals("DR. CALL TECH FAX", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_notPaidEcs1() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(new Timestamp(System.currentTimeMillis() + ONE_HOUR_IN_MILLIS));
        escripts.get(0).getEscript().setNumRefills(null);
        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.NOT_PAID_RX));
        assertEquals("FAILED CLAIMS TASK APPROVAL", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_notPaidEcs2() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(new Timestamp(System.currentTimeMillis() + ONE_HOUR_IN_MILLIS));
        escripts.get(0).getEscript().setNumRefills(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 3);
        escripts.get(0).getPayorPlan().setPpTypeCode("C");
        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.NOT_PAID_RX));
        assertEquals("FAILED CLAIMS TASK APPROVAL", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_orderNotOpen() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxNumber(null);
        escripts.get(0).getEscript().setWrittenQuantity(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) -1);

        OrderHeader orderHeader = getOrderHeader();
        order.setHeader(orderHeader);
        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.ORDER_NOT_OPEN));
    }

    @Test
    public void test_checkOrderSanity_wrongPatient1() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).getEscript().setDispensedQuantity(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 1);
        escripts.get(0).getPayor().setPayorBillTypeNum(-1);

        order.getBillship().setFamilyId("fxx");

        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.WRONG_PATIENT_ON_ORDER));
    }

    @Test
    public void test_checkOrderSanity_wrongPatient2() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).setTotalDispensedQuantity(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 1);
        escripts.get(0).getPayor().setPayorBillTypeNum(-1);

        order.getBillship().setFamilyId("fxx");
        order.getHeader().setOrderStatusNum((byte) 1);
        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.WRONG_PATIENT_ON_ORDER));
    }

    @Test
    public void test_checkOrderSanity_hippaBadAddress1() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).getEscript().setWrittenQuantity(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 1);
        escripts.get(0).getPayor().setPayorBillTypeNum(-1);

        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.HIPAA_BAD_ADDRESS));
        assertEquals("HIPAA VERIFICATION QUEUE", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_hippaBadAddress2() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).getEscript().setNumRefills(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 1);
        escripts.get(0).getPayor().setPayorBillTypeNum(-1);

        order.getHeader().setOrderStatusNum((byte) 1);
        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.HIPAA_BAD_ADDRESS));
        assertEquals("HIPAA VERIFICATION QUEUE", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_addressError_noShipMember1() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).getEscript().setNumRefills(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 1);
        escripts.get(0).getPayor().setPayorBillTypeNum(-1);
        escripts.get(0).getPatient().setGeneralStatusCode(null);

        order.setShipPatientNum(BigInteger.valueOf(1));
        order.getHeader().setOrderStatusNum((byte) -1);

        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.NO_PROPER_SHIP_MEMBER));
        assertEquals("FAILED CLAIMS", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_addressError_noShipMember2() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).getEscript().setNumRefills(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 1);
        escripts.get(0).getPayor().setPayorBillTypeNum(-1);
        escripts.get(0).getPatient().setGeneralStatusCode(null);

        order.setShipPatientNum(BigInteger.valueOf(1));
        // orderStatusNum = null
        order.getHeader().setOrderStatusNum(null);

        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.NO_PROPER_SHIP_MEMBER));
        assertEquals("FAILED CLAIMS", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_addressError_noShipMember3() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).getEscript().setNumRefills(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 1);
        escripts.get(0).getPayor().setPayorBillTypeNum(-1);
        escripts.get(0).getPatient().setGeneralStatusCode("A");
        escripts.get(0).getPatientPlan().setCoverageTypeId((byte) 4);

        order.setShipPatientNum(BigInteger.valueOf(1));
        order.getHeader().setOrderStatusNum(null);

        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.NO_PROPER_SHIP_MEMBER));
        assertEquals("FAILED CLAIMS", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_addressError_noShipMember4() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).getEscript().setNumRefills(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 1);
        escripts.get(0).getPayor().setPayorBillTypeNum(-1);
        escripts.get(0).getPatient().setGeneralStatusCode("A");
        escripts.get(0).getPatientPlan().setCpDeactivationDate(new Timestamp(System.currentTimeMillis() - 60 * 1000));

        order.setShipPatientNum(BigInteger.valueOf(1));
        order.getHeader().setOrderStatusNum(null);

        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.NO_PROPER_SHIP_MEMBER));
        assertEquals("FAILED CLAIMS", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_addressError_noShipMember5() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).getEscript().setNumRefills(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 1);
        escripts.get(0).getPayor().setPayorBillTypeNum(-1);
        escripts.get(0).getPatient().setGeneralStatusCode("A");
        escripts.get(0).getPatientPlan().setCpDeactivationDate(new Timestamp(System.currentTimeMillis() + ONE_HOUR_IN_MILLIS));
        escripts.get(0).getPatientPlan().setCpExpirationDate(new Timestamp(System.currentTimeMillis() - 60 * 1000));

        order.setShipPatientNum(BigInteger.valueOf(1));
        order.getHeader().setOrderStatusNum(null);

        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.NO_PROPER_SHIP_MEMBER));
        assertEquals("FAILED CLAIMS", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_addressError_noShipMember6() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).getEscript().setNumRefills(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 1);
        escripts.get(0).getPayor().setPayorBillTypeNum(-1);
        escripts.get(0).getPatient().setGeneralStatusCode("A");
        escripts.get(0).getPatientPlan().setCpDeactivationDate(new Timestamp(System.currentTimeMillis() + ONE_HOUR_IN_MILLIS));
        escripts.get(0).getPatientPlan().setCpExpirationDate(new Timestamp(System.currentTimeMillis() + ONE_HOUR_IN_MILLIS));
        escripts.get(0).getPatientPlan().setRelationshipNum(null);

        order.setShipPatientNum(BigInteger.valueOf(1));
        order.getHeader().setOrderStatusNum(null);

        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.NO_PROPER_SHIP_MEMBER));
        assertEquals("FAILED CLAIMS", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_addressError_noBillAddress1() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).getEscript().setNumRefills(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 1);
        escripts.get(0).getPayor().setPayorBillTypeNum(-1);
        escripts.get(0).getPatient().setGeneralStatusCode(null);

        order.setShipPatientNum(BigInteger.valueOf(1));
        order.getBillship().setFamilyId(null);
        order.setBillAddress(null);

        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.NO_BILL_ADDRESS));
        assertEquals("HIPAA VERIFICATION QUEUE", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_addressError_noBillAddress2() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).getEscript().setNumRefills(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 1);
        escripts.get(0).getPayor().setPayorBillTypeNum(-1);
        escripts.get(0).getPatient().setGeneralStatusCode("A");

        order.setShipPatientNum(BigInteger.valueOf(1));
        order.setBillAddress(null);

        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.NO_BILL_ADDRESS));
        assertEquals("HIPAA VERIFICATION QUEUE", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_addressError_noBillAddress3() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).getEscript().setNumRefills(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 1);
        escripts.get(0).getPayor().setPayorBillTypeNum(-1);
        escripts.get(0).getPatient().setGeneralStatusCode("A");
        escripts.get(0).getPatientPlan().setCoverageTypeId(null);
        escripts.get(0).getPatientPlan().setCpDeactivationDate(null);
        escripts.get(0).getPatientPlan().setCpExpirationDate(null);

        order.setShipPatientNum(BigInteger.valueOf(1));
        order.setBillAddress(null);

        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.NO_BILL_ADDRESS));
        assertEquals("HIPAA VERIFICATION QUEUE", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_addressError_noShipAddress() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).getEscript().setNumRefills(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 1);
        escripts.get(0).getPayor().setPayorBillTypeNum(-1);
        escripts.get(0).getPatient().setGeneralStatusCode(null);

        order.setShipPatientNum(BigInteger.valueOf(1));
        order.getBillship().setFamilyId(null);
        order.setShipAddress(null);

        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts, Arrays.asList(codeValueStatus));
        setUpMock(true);

        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.NO_SHIP_ADDRESS));
        assertEquals("HIPAA VERIFICATION QUEUE", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_noAuthRecord1() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).getEscript().setNumRefills(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 1);
        escripts.get(0).getPayor().setPayorBillTypeNum(-1);
        escripts.get(0).getPatient().setGeneralStatusCode(null);

        order.setTradingPartnerType("KIOSK PHARMACY");
        order.setShipPatientNum(BigInteger.valueOf(1));
        order.setAuthRecordNum(null);

        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);

        queueToBeUsed = null;
        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.NO_AUTH_RECORD));
        assertEquals("CREDIT CARD EXCEPTIONS QUEUE", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_noAuthRecord2() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).getEscript().setNumRefills(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 1);
        escripts.get(0).getPayor().setPayorBillTypeNum(-1);
        escripts.get(0).getPatient().setGeneralStatusCode(null);

        order.setShipPatientNum(BigInteger.valueOf(1));
        order.setAuthRecordNum(null);
        order.getHeader().setOrderStatusNum((byte) 1);

        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);
        when(workflowQueryFactory.from(any(Expression.class))).thenReturn(workflowQuery);
        when(workflowQuery.from(new Expression[]{QRules.rules})).thenReturn(workflowQuery);
        when(workflowQuery.fetchCount()).thenReturn(1L);
        when(workflowQuery.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(workflowQuery);

        queueToBeUsed = "0";
        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.NO_AUTH_RECORD));
        assertEquals("FINANCE", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_noAuthRecord3() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).getEscript().setNumRefills(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 1);
        escripts.get(0).getPayor().setPayorBillTypeNum(-1);
        escripts.get(0).getPatient().setGeneralStatusCode(null);

        order.setShipPatientNum(BigInteger.valueOf(1));
        order.setAuthRecordNum(null);
        order.getBillship().setFamilyId(null);

        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);
        when(workflowQueryFactory.from(any(Expression.class))).thenReturn(workflowQuery);
        when(workflowQuery.from(new Expression[]{QRules.rules})).thenReturn(workflowQuery);
        when(workflowQuery.fetchCount()).thenReturn(1L);
        when(workflowQuery.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(workflowQuery);

        queueToBeUsed = "1";
        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.NO_AUTH_RECORD));
        assertEquals("FINANCE OTC", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_noAuthRecord4() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).getEscript().setNumRefills(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 1);
        escripts.get(0).getPayor().setPayorBillTypeNum(-1);
        escripts.get(0).getPatient().setGeneralStatusCode(null);

        order.setTradingPartnerType("KIOSK PHARMACY");
        order.setShipPatientNum(BigInteger.valueOf(1));
        order.setAuthRecordNum(null);

        when(query.fetchFirst()).thenReturn(order, tradingPartnerType, codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(true);
        when(workflowQueryFactory.from(any(Expression.class))).thenReturn(workflowQuery);
        when(workflowQuery.from(new Expression[]{QRules.rules})).thenReturn(workflowQuery);
        when(workflowQuery.fetchCount()).thenReturn(0L);
        when(workflowQuery.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(workflowQuery);

        queueToBeUsed = "1";
        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertTrue(res.getErrors().contains(OrderSanityError.NO_AUTH_RECORD));
        assertEquals("CREDIT CARD EXCEPTIONS QUEUE", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkOrderSanity_noError1() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).getEscript().setNumRefills(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 1);
        escripts.get(0).getPayor().setPayorBillTypeNum(-1);
        escripts.get(0).getPatient().setGeneralStatusCode(null);

        order.setTradingPartnerType("KIOSK PHARMACY");
        order.setShipPatientNum(BigInteger.valueOf(1));
        order.setAuthRecordNum(null);
        order.getHeader().setOrderStatusNum((byte) 1);
        order.getHeader().setOrderTotalAmount(null);

        when(query.fetchFirst()).thenReturn(order, "KIOSK PHARMACY", codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(false);

        queueToBeUsed = "1";
        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertNull(res);
    }

    @Test
    public void test_checkOrderSanity_noError2() {
        escripts = getEscripts();
        escripts.get(0).getEscript().setRxExpirationDate(null);
        escripts.get(0).getEscript().setNumRefills(null);
        escripts.get(0).getOrderLine().setOrderLineStatusNum((byte) 1);
        escripts.get(0).getPayor().setPayorBillTypeNum(-1);
        escripts.get(0).getPatient().setGeneralStatusCode(null);

        order.setTradingPartnerType("KIOSK PHARMACY");
        order.setShipPatientNum(BigInteger.valueOf(1));
        order.setAuthRecordNum(null);
        order.getHeader().setOrderStatusNum((byte) 1);
        order.getBillship().setPaymentCardSeqNum(null);

        when(query.fetchFirst()).thenReturn(order, "KIOSK PHARMACY", codeValueStatus, escripts);
        when(query.fetch()).thenReturn(escripts);
        setUpMock(false);

        queueToBeUsed = "1";
        OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
        assertNull(res);
    }

    private List<OrderEscriptDTO> getEscripts() {
        return getEscripts(1);
    }

    private List<OrderEscriptDTO> getEscripts(int items) {
        escripts = new ArrayList<>();

        for (int i = 0; i < items; i++) {
            OrderEscriptDTO escript = new OrderEscriptDTO();
            OrderLines orderLines = new OrderLines();
            orderLines.setOrderLineStatusNum((byte) 1);
            escript.setOrderLine(orderLines);

            PatientGeneral patientGeneral = new PatientGeneral();
            patientGeneral.setPatientNum(BigInteger.valueOf(1));
            patientGeneral.setFamilyId("f01");
            patientGeneral.setPatientDob(Timestamp.valueOf(LocalDateTime.now().minus(20, ChronoUnit.YEARS)));
            patientGeneral.setGeneralStatusCode("A");
            escript.setPatient(patientGeneral);

            EScriptMsgAttributes eScriptMsgAttributes = new EScriptMsgAttributes();
            eScriptMsgAttributes.setRxExpirationDate(new Timestamp(System.currentTimeMillis()));
            eScriptMsgAttributes.setRxNumber("rx1");

            eScriptMsgAttributes.setNumRefills(0L);
            eScriptMsgAttributes.setWrittenQuantity(10L);
            eScriptMsgAttributes.setDispensedQuantity(3L);

            escript.setTotalDispensedQuantity(10L);

            PayorPlans payorPlans = new PayorPlans();
            payorPlans.setGeneralStatusCode("A");
            payorPlans.setPpTypeCode("A");
            escript.setPayorPlan(payorPlans);

            Payors payors = new Payors();
            payors.setPayorBillTypeNum(2);
            escript.setPayor(payors);

            escript.setEscript(eScriptMsgAttributes);

            PatientPlans patientPlans = new PatientPlans();
            patientPlans.setPpNum(1L);
            patientPlans.setCoverageTypeId((byte) 1);
            patientPlans.setRelationshipNum(1);
            escript.setPatientPlan(patientPlans);

            escripts.add(escript);
        }

        return escripts;
    }

    private OrderDTO getOrder() {
        OrderDTO order = new OrderDTO();

        order.setHeader(getOrderHeader());
        order.setBillship(getOrderBillship());
        order.setAuthRecordNum(BigInteger.valueOf(1));

        order.setBillAddress(1L);
        order.setShipAddress(1L);

        return order;
    }

    private OrderBillship getOrderBillship() {
        OrderBillship orderBillship = new OrderBillship();
        orderBillship.setFamilyId("f01");
        orderBillship.setPaymentCardSeqNum(1001L);

        return orderBillship;
    }

    private OrderHeader getOrderHeader() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setOrderStatusNum((byte) 2);
        orderHeader.setOrderTotalAmount(101L);
        orderHeader.setTradingPartnerNum(1L);

        return orderHeader;
    }

    /**
     * Create CWorkflowQueue instances.
     *
     * @return the CWorkflowQueue instances.
     */
    private List<CWorkflowQueue> getQueues() {
        List<CWorkflowQueue> queues = new ArrayList<>();

        queues.add(new CWorkflowQueue(1, 1001L, "wq101"));

        return queues;
    }
}
