package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.dto.WorkflowQueueDTO;
import com.humana.pharmacy.common.cache.model.CRule;
import com.humana.pharmacy.common.cache.model.CWorkflowQueue;
import com.humana.pharmacy.common.entity.QCodeValue;
import com.humana.pharmacy.common.entity.QRules;
import com.humana.pharmacy.dto.OrderProductDTO;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
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
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit test cases for DuplicateGPIServiceImpl;
 */
@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class DuplicateGPIServiceImplTest {
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
     * The Rules SQLQuery instance.
     */
    @Mock
    private SQLQuery rulesQuery;

    /**
     * The CodeValues SQLQuery instance.
     */
    @Mock
    private SQLQuery codeValuesQuery;

    /**
     * The DuplicateGPIServiceImpl instance.
     */
    private DuplicateGPIServiceImpl duplicateGPIService;
    /**
     * The patient number.
     */
    private final BigInteger patientNum = BigInteger.valueOf(1);

    /**
     * The order invoice number.
     */
    private final String orderInvoiceNum = "OIN3";

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
        duplicateGPIService = new DuplicateGPIServiceImpl(epostrxDataSource, workflowDataSource);

        setValue(duplicateGPIService, "epostrxQueryFactory", epostrxQueryFactory);
        setValue(duplicateGPIService, "workflowQueryFactory", workflowQueryFactory);
    }

    private void setUpMock(boolean workflowMock) {
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);

        when(query.from(any(QCodeValue.class))).thenReturn(codeValuesQuery);
        when(codeValuesQuery.where(any(Predicate.class))).thenReturn(codeValuesQuery);

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        if (workflowMock) {
            when(workflowQueryFactory.select(any(Expression.class))).thenReturn(workflowQuery);
            when(workflowQuery.from(any(Expression.class))).thenReturn(workflowQuery);
            when(workflowQuery.join(any(EntityPath.class))).thenReturn(workflowQuery);
            when(workflowQuery.on(any(Predicate.class))).thenReturn(workflowQuery);
            when(workflowQuery.where(any(Predicate.class))).thenReturn(workflowQuery);

            when(workflowQuery.from(any(QRules.class))).thenReturn(rulesQuery);
            when(rulesQuery.where(any(Predicate.class))).thenReturn(rulesQuery);
        }
    }

    @Test
    public void test_ctor_epostrxDataSourceNull() {
        assertThrows(RuntimeException.class, () -> new DuplicateGPIServiceImpl(null, workflowDataSource));
    }

    @Test
    public void test_ctor_workflowDataSourceNull() {
        assertThrows(RuntimeException.class, () -> new DuplicateGPIServiceImpl(epostrxDataSource, null));
    }

    @Test
    public void test_checkDuplicateGPI_patientNumNull() {
        assertThrows(RuntimeException.class, () -> duplicateGPIService.checkDuplicateGPI(null, orderInvoiceNum));
    }

    @Test
    public void test_checkDuplicateGPI_orderInvoiceNumNull() {
        assertThrows(RuntimeException.class, () -> duplicateGPIService.checkDuplicateGPI(patientNum, null));
    }

    @Test
    public void test_checkDuplicateGPI_orderInvoiceNumEmpty() {
        assertThrows(RuntimeException.class, () -> duplicateGPIService.checkDuplicateGPI(patientNum, " "));
    }

    @Test
    public void test_checkDuplicateGPI_1() {
        setUpMock(true);

        List<OrderProductDTO> orderProductDTOs = new ArrayList<>();

        when(query.fetch()).thenReturn(orderProductDTOs);

        String res = duplicateGPIService.checkDuplicateGPI(patientNum, orderInvoiceNum);
        assertEquals("", res);
    }

    @Test
    public void test_checkDuplicateGPI_SameOrder1() {
        setUpMock(true);

        List<OrderProductDTO> orderProductDTOs = getOrderProductDTOs();

        when(query.fetch()).thenReturn(orderProductDTOs);

        String res = duplicateGPIService.checkDuplicateGPI(patientNum, orderInvoiceNum);
        assertEquals("Duplicate GPI 'p3' within the same Order. -!-Duplicate GPI 'p4' within the same Order. -!-", res);
    }

    @Test
    public void test_checkDuplicateGPI_SameOrder2() {
        setUpMock(true);

        List<OrderProductDTO> orderProductDTOs = getOrderProductDTOs();
        orderProductDTOs.get(3).setOrderInvoiceNumber("OIN4");

        when(query.fetch()).thenReturn(orderProductDTOs);

        String res = duplicateGPIService.checkDuplicateGPI(patientNum, orderInvoiceNum);
        assertEquals("", res);
    }

    @Test
    public void test_checkDuplicateGPI_OpenOrder1() {
        setUpMock(true);

        List<OrderProductDTO> orderProductDTOs = getOrderProductDTOs();
        orderProductDTOs.get(1).setProdGenericRefNum("PGRN3");
        orderProductDTOs.get(1).setOrderQueueNum(1);
        orderProductDTOs.get(0).setNonOrderQueueNum(3);
        List<CWorkflowQueue> workflowQueueList = new ArrayList<>();
        workflowQueueList.add(new CWorkflowQueue(1, null, "qn2"));
        workflowQueueList.add(new CWorkflowQueue(3, null, "qn3"));

        when(workflowQuery.fetch()).thenReturn(workflowQueueList);
        when(query.fetch()).thenReturn(orderProductDTOs);

        List<CRule> ruleList = new ArrayList<>();
        ruleList.add(new CRule(1l, "Y"));
        ruleList.add(new CRule(2l, "N"));
        when(rulesQuery.fetch()).thenReturn(ruleList);

        String res = duplicateGPIService.checkDuplicateGPI(patientNum, orderInvoiceNum);
        assertEquals("Duplicate GPI 'p3' within the same Order. -!-Duplicate GPI 'p4' within the same Order. -!-"
                + "Duplicate GPI 'p3' in this Order, 'p2' in Open Order OIN2 in qn2 Queue. -!-"
                + "Duplicate GPI 'p4' in this Order, 'p2' in Open Order OIN2 in qn2 Queue. -!-", res);
    }

    @Test
    public void test_checkDuplicateGPI_OpenOrder2() {
        setUpMock(true);

        List<OrderProductDTO> orderProductDTOs = getOrderProductDTOs();
        orderProductDTOs.get(1).setProdGenericRefNum("PGRN3");
        orderProductDTOs.get(1).setOrderQueueNum(1);
        orderProductDTOs.get(0).setNonOrderQueueNum(3);
        List<CWorkflowQueue> workflowQueueList = new ArrayList<>();
        workflowQueueList.add(new CWorkflowQueue(1, null, "qn2"));
        workflowQueueList.add(new CWorkflowQueue(3, null, "qn3"));

        when(workflowQuery.fetch()).thenReturn(workflowQueueList);
        when(query.fetch()).thenReturn(orderProductDTOs);

        List<CRule> ruleList = new ArrayList<>();
        ruleList.add(new CRule(1l, "Y"));
        ruleList.add(new CRule(2l, "N"));
        when(rulesQuery.fetch()).thenReturn(ruleList);

        String res = duplicateGPIService.checkDuplicateGPI(patientNum, orderInvoiceNum);
        assertEquals("Duplicate GPI 'p3' within the same Order. -!-Duplicate GPI 'p4' within the same Order. -!-"
                + "Duplicate GPI 'p3' in this Order, 'p2' in Open Order OIN2 in qn2 Queue. -!-"
                + "Duplicate GPI 'p4' in this Order, 'p2' in Open Order OIN2 in qn2 Queue. -!-", res);
    }

    @Test
    public void test_checkDuplicateGPI_OpenOrder3() {
        setUpMock(true);

        List<OrderProductDTO> orderProductDTOs = getOrderProductDTOs();
        orderProductDTOs.get(1).setProdGenericRefNum("PGRN3");
        orderProductDTOs.get(2).setOrderQueueNum(1);
        orderProductDTOs.get(3).setNonOrderQueueNum(3);

        when(query.fetch()).thenReturn(orderProductDTOs);

        String res = duplicateGPIService.checkDuplicateGPI(patientNum, orderInvoiceNum);

        assertEquals("Duplicate GPI 'p3' within the same Order. -!-Duplicate GPI 'p4' within the same Order. -!-", res);
    }

    @Test
    public void test_checkDuplicateGPI_OpenOrder4() {
        setUpMock(true);

        List<OrderProductDTO> orderProductDTOs = getOrderProductDTOs();
        orderProductDTOs.get(1).setProdGenericRefNum("PGRN3");

        when(query.fetch()).thenReturn(orderProductDTOs);

        String res = duplicateGPIService.checkDuplicateGPI(patientNum, orderInvoiceNum);
        assertEquals("Duplicate GPI 'p3' within the same Order. -!-Duplicate GPI 'p4' within the same Order. -!-", res);
    }

    @Test
    public void test_checkDuplicateGPI_WorkflowQueue() {
        setUpMock(true);

        List<OrderProductDTO> orderProductDTOs = getOrderProductDTOs();
        orderProductDTOs.get(1).setProdGenericRefNum("PGRN3");
        orderProductDTOs.get(1).setOrderQueueNum(1);
        orderProductDTOs.get(0).setNonOrderQueueNum(3);
        List<CWorkflowQueue> workflowQueueList = new ArrayList<>();
        workflowQueueList.add(new CWorkflowQueue(1, null, "DR. CALL TECH OUTBOUND"));
        workflowQueueList.add(new CWorkflowQueue(3, null, "qn3"));

        when(workflowQuery.fetch()).thenReturn(workflowQueueList);
        when(query.fetch()).thenReturn(orderProductDTOs);

        List<CRule> ruleList = new ArrayList<>();
        ruleList.add(new CRule(1l, "Y"));
        ruleList.add(new CRule(2l, "N"));
        when(rulesQuery.fetch()).thenReturn(ruleList);


        String res = duplicateGPIService.checkDuplicateGPI(patientNum, orderInvoiceNum);
        assertEquals("Duplicate GPI 'p3' within the same Order. -!-Duplicate GPI 'p4' within the same Order. -!-"
                        + "Duplicate GPI 'p3' in this Order, 'p2' in Order OIN2 with fax in DR. CALL TECH OUTBOUND Queue. -!-"
                        + "Duplicate GPI 'p4' in this Order, 'p2' in Order OIN2 with fax in DR. CALL TECH OUTBOUND Queue. -!-",
                res);
    }

    @Test
    public void test_checkDuplicateGPI_IncompletePending1() {
        setUpMock(true);

        List<OrderProductDTO> orderProductDTOs = getOrderProductDTOs();
        orderProductDTOs.get(1).setOrderStatusNum((byte) 98);
        orderProductDTOs.get(1).setProdGenericRefNum("PGRN3");
        orderProductDTOs.get(1).setOrderQueueNum(1);
        orderProductDTOs.get(0).setNonOrderQueueNum(3);
        List<CWorkflowQueue> workflowQueueList = new ArrayList<>();
        workflowQueueList.add(new CWorkflowQueue(1, null, "qn2"));
        workflowQueueList.add(new CWorkflowQueue(3, null, "qn3"));

        when(workflowQuery.fetch()).thenReturn(workflowQueueList);
        when(query.fetch()).thenReturn(orderProductDTOs);

        List<CRule> ruleList = new ArrayList<>();
        ruleList.add(new CRule(1l, "Y"));
        ruleList.add(new CRule(2l, "N"));
        when(rulesQuery.fetch()).thenReturn(ruleList);

        String res = duplicateGPIService.checkDuplicateGPI(patientNum, orderInvoiceNum);
        assertEquals("Duplicate GPI 'p3' within the same Order. -!-Duplicate GPI 'p4' within the same Order. -!-"
                + "Duplicate GPI 'p3' in this Order, 'p2' in Open Order OIN2 in qn2 Queue. -!-"
                + "Duplicate GPI 'p4' in this Order, 'p2' in Open Order OIN2 in qn2 Queue. -!-"
                + "Duplicate GPI 'p3' in this Order, 'p2' in Incomplete/Pending Order OIN2. -!-"
                + "Duplicate GPI 'p4' in this Order, 'p2' in Incomplete/Pending Order OIN2. -!-", res);
    }

    @Test
    public void test_checkDuplicateGPI_IncompletePending2() {
        setUpMock(true);

        List<OrderProductDTO> orderProductDTOs = getOrderProductDTOs();
        orderProductDTOs.get(1).setOrderStatusNum((byte) 98);
        orderProductDTOs.get(1).setProdGenericRefNum("PGRNX");

        when(query.fetch()).thenReturn(orderProductDTOs);

        String res = duplicateGPIService.checkDuplicateGPI(patientNum, orderInvoiceNum);
        assertEquals("Duplicate GPI 'p3' within the same Order. -!-Duplicate GPI 'p4' within the same Order. -!-", res);
    }

    /**
     * Get OrderProductDTO data.
     *
     * @return the OrderProductDTO data.
     */
    private List<OrderProductDTO> getOrderProductDTOs() {
        List<OrderProductDTO> orderProductDTOs = new ArrayList<>();

        orderProductDTOs.add(getOrderProductDTO(BigInteger.valueOf(1),
                (byte) 1, "OIN1", "PGRN1", "p1"));
        orderProductDTOs.add(getOrderProductDTO(BigInteger.valueOf(2), (byte) 1, "OIN2", "PGRN2", "p2"));
        orderProductDTOs.add(getOrderProductDTO(BigInteger.valueOf(3), (byte) 1, "OIN3", "PGRN3", "p3"));
        orderProductDTOs.add(getOrderProductDTO(BigInteger.valueOf(4), (byte) 1, "OIN3", "PGRN3", "p4"));
        orderProductDTOs.add(getOrderProductDTO(BigInteger.valueOf(1), (byte) 1, "OIN5", "PGRN1", "p5"));

        return orderProductDTOs;
    }

    /**
     * Create an instance of OrderProductDTO.
     *
     * @param orderNum           the order number
     * @param orderStatusNum     the order status number
     * @param orderInvoiceNumber the order invoice number
     * @param prodGenericRefNum  the product generic ref number
     * @param prodNameDesc       the product name description
     * @return the OrderProductDTO instance
     */
    private OrderProductDTO getOrderProductDTO(BigInteger orderNum,
                                               Byte orderStatusNum,
                                               String orderInvoiceNumber,
                                               String prodGenericRefNum,
                                               String prodNameDesc) {
        OrderProductDTO dto = new OrderProductDTO();
        dto.setOrderNum(orderNum);
        dto.setOrderStatusNum(orderStatusNum);
        dto.setOrderInvoiceNumber(orderInvoiceNumber);
        dto.setProdGenericRefNum(prodGenericRefNum);
        dto.setProdNameDesc(prodNameDesc);

        return dto;
    }

    /**
     * Get WorkflowQueueDTO data.
     *
     * @return the WorkflowQueueDTO data.
     */
    private List<WorkflowQueueDTO> getWorkflowQueueDTOs() {
        List<WorkflowQueueDTO> workflowQueueDTOs = new ArrayList<>();

        workflowQueueDTOs.add(getWorkflowQueueDTO(BigInteger.valueOf(1), 1, "Y", "qn1", ""));
        workflowQueueDTOs.add(getWorkflowQueueDTO(BigInteger.valueOf(2), 2, "Y", "qn2", ""));
        workflowQueueDTOs
                .add(getWorkflowQueueDTO(BigInteger.valueOf(3), 3, "N", "REFILL OUTREACH", "PGRN3"));

        return workflowQueueDTOs;
    }

    /**
     * Create an instance of WorkflowQueueDTO.
     *
     * @param orderNum          the order number
     * @param workflowQueueNum  the workflow queue number
     * @param orderBasedRule    the order based rule
     * @param workflowQueueName the workflow queue name
     * @param prodGenericRefNum the product generic ref number
     * @return the WorkflowQueueDTO instance
     */
    private WorkflowQueueDTO getWorkflowQueueDTO(BigInteger orderNum,
                                                 Integer workflowQueueNum,
                                                 String orderBasedRule,
                                                 String workflowQueueName,
                                                 String prodGenericRefNum) {
        WorkflowQueueDTO dto = new WorkflowQueueDTO();
        dto.setOrderNum(orderNum);
        dto.setWorkflowQueueNum(workflowQueueNum);
        dto.setOrderBasedRule(orderBasedRule);
        dto.setWorkflowQueueName(workflowQueueName);
        dto.setProdGenericRefNum(prodGenericRefNum);

        return dto;
    }

    /**
     * Sets field of the given object.
     *
     * @param obj   the given object
     * @param field the field name
     * @param value the field value
     */
    private void setValue(Object obj, String field, Object value) {
        try {
            // Create Field object
            Field privateField
                    = DuplicateGPIServiceImpl.class.getSuperclass().getDeclaredField(field);

            // Set the accessibility as true
            privateField.setAccessible(true);

            // Store the value of private field in variable
            privateField.set(obj, value);

            // Set the accessibility as true
            privateField.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
