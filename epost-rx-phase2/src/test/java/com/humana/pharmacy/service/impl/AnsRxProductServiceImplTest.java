package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.TestsHelper;
import com.humana.pharmacy.common.cache.model.CShippingMethod;
import com.humana.pharmacy.common.cache.model.CWorkflowQueue;
import com.humana.pharmacy.dto.AnsRxProductDTO;
import com.humana.pharmacy.dto.AnsRxProductResult;
import com.querydsl.core.types.Expression;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit test cases for AnsRxProductServiceImpl;
 */
@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AnsRxProductServiceImplTest {
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
    @Mock(answer = Answers.RETURNS_SELF)
    private SQLQuery query;

    /**
     * The AnsRxProductServiceImpl instance.
     */
    private AnsRxProductServiceImpl ansRxProductService;
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
        ansRxProductService = new AnsRxProductServiceImpl(epostrxDataSource, workflowDataSource);
        TestsHelper.setFieldValue(ansRxProductService, "epostrxQueryFactory", epostrxQueryFactory);
        TestsHelper.setFieldValue(ansRxProductService, "workflowQueryFactory", workflowQueryFactory);
    }

    @Test
    public void test_ctor_epostrxDataSourceNull() {
        assertThrows(
                RuntimeException.class, () -> new AnsRxProductServiceImpl(null, workflowDataSource));
    }

    @Test
    public void test_ctor_workflowDataSourceNull() {
        assertThrows(
                RuntimeException.class, () -> new AnsRxProductServiceImpl(epostrxDataSource, null));
    }

    @Test
    public void test_checkAnsRxProduct_scriptIdNull() {
        assertThrows(RuntimeException.class, () -> ansRxProductService.checkAnsRxProduct(null));
    }

    @Test
    public void test_checkAnsRxProduct_scriptIdNotFound() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.fetch()).thenReturn(new ArrayList());
        AnsRxProductResult result = ansRxProductService.checkAnsRxProduct(new BigInteger("1"));
        assertNull(result);
    }

    @Test
    public void test_checkAnsRxProduct_ITEM_CANNOT_BE_SHIPPED_TO_POBOX_coldpack() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.query()).thenReturn(query);
        List<AnsRxProductDTO> dtoList = getAnsRxProductDTO();
        dtoList.get(0).setTargetValue("val");
        when(query.fetch()).thenReturn(dtoList, getCShippingMethods());
        when(query.fetchCount()).thenReturn(0L);
        AnsRxProductResult result = ansRxProductService.checkAnsRxProduct(new BigInteger("1"));
        assertEquals("(1) ITEM CANNOT BE SHIPPED TO P.O. BOX\n", result.toErrorMessage());
    }

    @Test
    public void test_checkAnsRxProduct_ITEM_CANNOT_BE_SHIPPED_TO_POBOX_prodDea() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.query()).thenReturn(query);
        List<AnsRxProductDTO> dtoList = getAnsRxProductDTO();
        dtoList.get(0).setProdDea((byte) 2);
        when(query.fetch()).thenReturn(dtoList, getCShippingMethods());
        when(query.fetchCount()).thenReturn(0L);
        AnsRxProductResult result = ansRxProductService.checkAnsRxProduct(new BigInteger("1"));
        assertEquals("(1) ITEM CANNOT BE SHIPPED TO P.O. BOX\n", result.toErrorMessage());
    }

    @Test
    public void test_checkAnsRxProduct_ITEM_CANNOT_BE_SHIPPED_TO_POBOX_prodDeaState() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.query()).thenReturn(query);
        List<AnsRxProductDTO> dtoList = getAnsRxProductDTO();
        dtoList.get(0).setStateCsProductsProdDea((short) 2);
        when(query.fetch()).thenReturn(dtoList, getCShippingMethods());
        when(query.fetchCount()).thenReturn(0L);
        AnsRxProductResult result = ansRxProductService.checkAnsRxProduct(new BigInteger("1"));
        assertEquals("(1) ITEM CANNOT BE SHIPPED TO P.O. BOX\n", result.toErrorMessage());
    }

    @Test
    public void test_checkAnsRxProduct_ITEM_CANNOT_BE_SHIPPED_TO_POBOX_activePobox() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.query()).thenReturn(query);
        List<AnsRxProductDTO> dtoList = getAnsRxProductDTO();
        when(query.fetch()).thenReturn(dtoList, getCShippingMethods());
        when(query.fetchCount()).thenReturn(1L);
        AnsRxProductResult result = ansRxProductService.checkAnsRxProduct(new BigInteger("1"));
        assertEquals("(1) ITEM CANNOT BE SHIPPED TO P.O. BOX\n", result.toErrorMessage());
    }

    @Test
    public void test_checkAnsRxProduct_ITEM_CANNOT_BE_SHIPPED_TO_POBOX_uspShippingMethod() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.query()).thenReturn(query);
        List<AnsRxProductDTO> dtoList = getAnsRxProductDTO();
        dtoList.get(0).setShipMethodId(1);
        when(query.fetch()).thenReturn(dtoList, getCShippingMethods());
        when(query.fetchCount()).thenReturn(0L);
        AnsRxProductResult result = ansRxProductService.checkAnsRxProduct(new BigInteger("1"));
        assertEquals("(1) ITEM CANNOT BE SHIPPED TO P.O. BOX\n", result.toErrorMessage());
    }

    @Test
    public void test_checkAnsRxProduct_ITEM_CANNOT_BE_SHIPPED_TO_POBOX_noMatchPatientAddress() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.query()).thenReturn(query);
        List<AnsRxProductDTO> dtoList = getAnsRxProductDTO();
        dtoList.get(0).setPatientAddress("other");
        when(query.fetch()).thenReturn(dtoList, getCShippingMethods());
        when(query.fetchCount()).thenReturn(1L);
        AnsRxProductResult result = ansRxProductService.checkAnsRxProduct(new BigInteger("1"));
        assertNull(result);
    }

    @Test
    public void test_checkAnsRxProduct_STATE_REQ_NY_TRIPLICATE_SERIAL_NUM_ON_FILE_prodDea() {
        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.query()).thenReturn(query);
        List<AnsRxProductDTO> dtoList = getAnsRxProductDTO();
        dtoList.get(0).setStateCode("NY");
        dtoList.get(0).setProdDea((byte) 3);
        when(query.fetch()).thenReturn(dtoList, getCShippingMethods(), getCWorkflowQueue());
        when(query.fetchCount()).thenReturn(0L);
        AnsRxProductResult result = ansRxProductService.checkAnsRxProduct(new BigInteger("1"));
        assertEquals(
                "(1) State Req(NY): requires rx triplicate serial# on-file for CS.\n",
                result.toErrorMessage());
        assertEquals("DR. CALL TECH FAX", result.getOverrideQname());
        assertEquals("10", result.getOverrideQueue());
    }

    @Test
    public void test_checkAnsRxProduct_STATE_REQ_NY_TRIPLICATE_SERIAL_NUM_ON_FILE_prodDeaState() {
        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.query()).thenReturn(query);
        List<AnsRxProductDTO> dtoList = getAnsRxProductDTO();
        dtoList.get(0).setStateCode("NY");
        dtoList.get(0).setStateCsProductsProdDea((short) 3);
        when(query.fetch()).thenReturn(dtoList, getCShippingMethods(), getCWorkflowQueue());
        when(query.fetchCount()).thenReturn(0L);
        AnsRxProductResult result = ansRxProductService.checkAnsRxProduct(new BigInteger("1"));
        assertEquals(
                "(1) State Req(NY): requires rx triplicate serial# on-file for CS.\n",
                result.toErrorMessage());
        assertEquals("DR. CALL TECH FAX", result.getOverrideQname());
        assertEquals("10", result.getOverrideQueue());
    }

    @Test
    public void
    test_checkAnsRxProduct_STATE_REQ_NY_TRIPLICATE_SERIAL_NUM_ON_FILE_hasTriplicateSerialNum() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.query()).thenReturn(query);
        List<AnsRxProductDTO> dtoList = getAnsRxProductDTO();
        dtoList.get(0).setStateCode("NY");
        dtoList.get(0).setStateCsProductsProdDea((short) 3);
        dtoList.get(0).setTriplicateSerialNum("001");
        when(query.fetch()).thenReturn(dtoList, getCShippingMethods());
        when(query.fetchCount()).thenReturn(0L);
        AnsRxProductResult result = ansRxProductService.checkAnsRxProduct(new BigInteger("1"));
        assertNull(result);
    }

    @Test
    public void test_checkAnsRxProduct_STATE_REQ_NY_TRIPLICATE_SERIAL_NUM_ON_FILE_noMatch() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.query()).thenReturn(query);
        List<AnsRxProductDTO> dtoList = getAnsRxProductDTO();
        dtoList.get(0).setStateCode("NY");
        when(query.fetch()).thenReturn(dtoList, getCShippingMethods());
        when(query.fetchCount()).thenReturn(0L);
        AnsRxProductResult result = ansRxProductService.checkAnsRxProduct(new BigInteger("1"));
        assertNull(result);
    }

    @Test
    public void test_checkAnsRxProduct_STATE_REQ_TX_TRIPLICATE_SERIAL_NUM_ON_FILE_prodDea() {
        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.query()).thenReturn(query);
        List<AnsRxProductDTO> dtoList = getAnsRxProductDTO();
        dtoList.get(0).setStateCode("TX");
        dtoList.get(0).setProdDea((byte) 2);
        dtoList.get(0).setPatientAddress("");
        when(query.fetch()).thenReturn(dtoList, getCShippingMethods(), getCWorkflowQueue());
        when(query.fetchCount()).thenReturn(0L);
        AnsRxProductResult result = ansRxProductService.checkAnsRxProduct(new BigInteger("1"));
        assertEquals(
                "(1) State Req(TX): requires rx triplicate serial# on-file for CS.\n",
                result.toErrorMessage());
        assertEquals("DR. CALL TECH FAX", result.getOverrideQname());
        assertEquals("10", result.getOverrideQueue());
    }

    @Test
    public void test_checkAnsRxProduct_STATE_REQ_TX_TRIPLICATE_SERIAL_NUM_ON_FILE_prodDeaState() {
        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.query()).thenReturn(query);
        List<AnsRxProductDTO> dtoList = getAnsRxProductDTO();
        dtoList.get(0).setStateCode("TX");
        dtoList.get(0).setPatientAddress("");
        dtoList.get(0).setStateCsProductsProdDea((short) 2);
        when(query.fetch()).thenReturn(dtoList, getCShippingMethods(), getCWorkflowQueue());
        when(query.fetchCount()).thenReturn(0L);
        AnsRxProductResult result = ansRxProductService.checkAnsRxProduct(new BigInteger("1"));
        assertEquals(
                "(1) State Req(TX): requires rx triplicate serial# on-file for CS.\n",
                result.toErrorMessage());
        assertEquals("DR. CALL TECH FAX", result.getOverrideQname());
        assertEquals("10", result.getOverrideQueue());
    }

    @Test
    public void
    test_checkAnsRxProduct_STATE_REQ_TX_TRIPLICATE_SERIAL_NUM_ON_FILE_hasTriplicateSerialNum() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.query()).thenReturn(query);
        List<AnsRxProductDTO> dtoList = getAnsRxProductDTO();
        dtoList.get(0).setStateCode("TX");
        dtoList.get(0).setStateCsProductsProdDea((short) 2);
        dtoList.get(0).setPatientAddress("");
        dtoList.get(0).setTriplicateSerialNum("001");
        when(query.fetch()).thenReturn(dtoList, getCShippingMethods());
        when(query.fetchCount()).thenReturn(0L);
        AnsRxProductResult result = ansRxProductService.checkAnsRxProduct(new BigInteger("1"));
        assertNull(result);
    }

    @Test
    public void test_checkAnsRxProduct_STATE_REQ_TX_TRIPLICATE_SERIAL_NUM_ON_FILE_noMatch() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.query()).thenReturn(query);
        List<AnsRxProductDTO> dtoList = getAnsRxProductDTO();
        dtoList.get(0).setStateCode("TX");
        when(query.fetch()).thenReturn(dtoList, getCShippingMethods());
        when(query.fetchCount()).thenReturn(0L);
        AnsRxProductResult result = ansRxProductService.checkAnsRxProduct(new BigInteger("1"));
        assertNull(result);
    }

    private List<AnsRxProductDTO> getAnsRxProductDTO() {
        AnsRxProductDTO dto = new AnsRxProductDTO();
        dto.setPatientAddress("POBOX");
        dto.setDispensedProductId(1L);
        dto.setStateCode("XX");
        dto.setPatientAddress2("address2");
        dto.setProdGenericRefNum("001");
        dto.setTradingPartnerNum(1L);
        return Arrays.asList(dto);
    }

    private List<CShippingMethod> getCShippingMethods() {
        CShippingMethod cShippingMethod = new CShippingMethod();
        cShippingMethod.setShipMethodId(1);
        cShippingMethod.setShipMethodDesc("UPS");
        return Arrays.asList(cShippingMethod);
    }

    private List<CWorkflowQueue> getCWorkflowQueue() {
        CWorkflowQueue cWorkflowQueue = new CWorkflowQueue();
        cWorkflowQueue.setWorkflowQueueName("DR. CALL TECH FAX");
        cWorkflowQueue.setWorkflowQueueNum(10);
        return Arrays.asList(cWorkflowQueue);
    }
}
