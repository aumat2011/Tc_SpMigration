package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.TestsHelper;
import com.humana.pharmacy.dto.AnsDefaultRxPlanParamsDTO;
import com.humana.pharmacy.dto.AnsDefaultRxPlanParamsResult;
import com.querydsl.core.JoinFlag;
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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit test cases for AnsDefaultRxPlanParamsServiceImpl;
 */
@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AnsDefaultRxPlanParamsServiceImplTest {
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
     * The AnsDefaultRxPlanParamsServiceImpl instance.
     */
    private AnsDefaultRxPlanParamsServiceImpl ansDefaultRxPlanParamsService;

    /**
     * The prod id.
     */
    private Long prodId = 1L;

    /**
     * The pp num.
     */
    private Long ppNum = 1L;

    /**
     * The script id.
     */
    private BigInteger scriptId = BigInteger.valueOf(1);

    /**
     * The AnsDefaultRxPlanParamsResult object.
     */
    private AnsDefaultRxPlanParamsResult ansDefaultRxPlanParamsResult;

    /**
     * The AnsDefaultRxPlanParamsDTO list.
     */
    private List<AnsDefaultRxPlanParamsDTO> ansDefaultRxPlanParamsDTOs;

    /**
     * The state num.
     */
    private Integer stateNum = 1;

    /**
     * The 'epostrx' query factory.
     */
    @Mock
    private SQLQueryFactory epostrxQueryFactory;
    /**
     * The 'epostrx_workflow' query factory.
     */
    @Mock
    private SQLQueryFactory workflowQueryFactory;

    @BeforeEach
    public void setUp() {
        ansDefaultRxPlanParamsService = new AnsDefaultRxPlanParamsServiceImpl(epostrxDataSource, workflowDataSource);

        TestsHelper.setFieldValue(ansDefaultRxPlanParamsService, "epostrxQueryFactory", epostrxQueryFactory);
        TestsHelper.setFieldValue(ansDefaultRxPlanParamsService, "workflowQueryFactory", workflowQueryFactory);

        ansDefaultRxPlanParamsResult = getAnsDefaultRxPlanParamsResult1();
        ansDefaultRxPlanParamsDTOs = getAnsDefaultRxPlanParamsDTOs();
    }

    private void setUpMock() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class), any(Expression.class))).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);

        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);
    }

    @Test
    public void test_ctor_epostrxDataSourceNull() {
        assertThrows(RuntimeException.class, () -> new AnsDefaultRxPlanParamsServiceImpl(null, workflowDataSource));
    }

    @Test
    public void test_ctor_workflowDataSourceNull() {
        assertThrows(RuntimeException.class, () -> new AnsDefaultRxPlanParamsServiceImpl(epostrxDataSource, null));
    }

    @Test
    public void test_getAnsDefaultRxPlanParams_ALLNull() {
        prodId = null;
        ppNum = null;
        scriptId = null;

        setUpMock();
        when(query.fetchFirst()).thenReturn(ansDefaultRxPlanParamsResult, ansDefaultRxPlanParamsResult, stateNum, ansDefaultRxPlanParamsResult);

        AnsDefaultRxPlanParamsResult res = ansDefaultRxPlanParamsService.getAnsDefaultRxPlanParams(prodId, ppNum, scriptId);

        assertNull(res.getNarcoticCode());
        assertNull(res.getNumRefillsAllowed());
        assertNull(res.getNumDaysFromOrigDate());
        assertNull(res.getNumDaysExpire());
        assertNull(res.getMaxDaysSupply());
        assertNull(res.getMinPctUtil());
        assertNull(res.getAcceptFax());
        assertNull(res.getPlanNumRefillsAllowed());
        assertNull(res.getPlanMinPctUtil());
        assertNull(res.getPlanMaxPctUtil());
        assertNull(res.getPlanMinQuantity());
        assertNull(res.getPlanMaxQuantity());
        assertNull(res.getPlanMinDaysSupply());
        assertNull(res.getPlanMaxDaysSupply());
        checkFutures(res);
    }

    @Test
    public void test_getAnsDefaultRxPlanParams_ALLNotFound() {
        prodId = 9999L;
        ppNum = 9999L;
        scriptId = BigInteger.valueOf(9999);

        setUpMock();

        AnsDefaultRxPlanParamsResult res = ansDefaultRxPlanParamsService.getAnsDefaultRxPlanParams(prodId, ppNum, scriptId);

        assertNull(res.getNarcoticCode());
        assertNull(res.getNumRefillsAllowed());
        assertNull(res.getNumDaysFromOrigDate());
        assertNull(res.getNumDaysExpire());
        assertNull(res.getMaxDaysSupply());
        assertNull(res.getMinPctUtil());
        assertNull(res.getAcceptFax());
        assertNull(res.getPlanNumRefillsAllowed());
        assertNull(res.getPlanMinPctUtil());
        assertNull(res.getPlanMaxPctUtil());
        assertNull(res.getPlanMinQuantity());
        assertNull(res.getPlanMaxQuantity());
        assertNull(res.getPlanMinDaysSupply());
        assertNull(res.getPlanMaxDaysSupply());
        checkFutures(res);
    }

    @Test
    public void test_getAnsDefaultRxPlanParams_prodIdNotNull() {
        ppNum = null;
        scriptId = null;

        setUpMock();
        when(query.fetchFirst()).thenReturn(ansDefaultRxPlanParamsResult, ansDefaultRxPlanParamsResult, stateNum, ansDefaultRxPlanParamsResult);

        AnsDefaultRxPlanParamsResult res = ansDefaultRxPlanParamsService.getAnsDefaultRxPlanParams(prodId, ppNum, scriptId);

        assertEquals("gprf1", res.getNarcoticCode());
        assertEquals(1, res.getNumRefillsAllowed());
        assertEquals(2, res.getNumDaysFromOrigDate());
        assertEquals(3, res.getNumDaysExpire());
        assertEquals(4, res.getMaxDaysSupply());
        assertEquals(5, res.getMinPctUtil());
        assertEquals("Y", res.getAcceptFax());
        assertNull(res.getPlanNumRefillsAllowed());
        assertNull(res.getPlanMinPctUtil());
        assertNull(res.getPlanMaxPctUtil());
        assertNull(res.getPlanMinQuantity());
        assertNull(res.getPlanMaxQuantity());
        assertNull(res.getPlanMinDaysSupply());
        assertNull(res.getPlanMaxDaysSupply());
        checkFutures(res);
    }

    @Test
    public void test_getAnsDefaultRxPlanParams_ppNumNotNull() {
        prodId = null;
        scriptId = null;

        setUpMock();
        when(query.fetchFirst()).thenReturn(ansDefaultRxPlanParamsResult, ansDefaultRxPlanParamsResult, stateNum, ansDefaultRxPlanParamsResult);

        AnsDefaultRxPlanParamsResult res = ansDefaultRxPlanParamsService.getAnsDefaultRxPlanParams(prodId, ppNum, scriptId);

        assertNull(res.getNarcoticCode());
        assertNull(res.getNumRefillsAllowed());
        assertNull(res.getNumDaysFromOrigDate());
        assertNull(res.getNumDaysExpire());
        assertNull(res.getMaxDaysSupply());
        assertNull(res.getMinPctUtil());
        assertNull(res.getAcceptFax());
        assertEquals((short) 6, res.getPlanNumRefillsAllowed());
        assertEquals(7, res.getPlanMinPctUtil());
        assertEquals(8, res.getPlanMaxPctUtil());
        assertEquals(9L, res.getPlanMinQuantity());
        assertEquals(10L, res.getPlanMaxQuantity());
        assertEquals((short) 11, res.getPlanMinDaysSupply());
        assertEquals((short) 12, res.getPlanMaxDaysSupply());
        checkFutures(res);
    }

    @Test
    public void test_getAnsDefaultRxPlanParams_1() {
        setUpMock();
        when(query.fetch()).thenReturn(ansDefaultRxPlanParamsDTOs);
        when(query.fetchFirst()).thenReturn(ansDefaultRxPlanParamsResult, ansDefaultRxPlanParamsResult, stateNum,
                ansDefaultRxPlanParamsResult);
        AnsDefaultRxPlanParamsResult res = ansDefaultRxPlanParamsService.getAnsDefaultRxPlanParams(prodId, ppNum, scriptId);

        assertEquals("gprf1", res.getNarcoticCode());
        assertEquals(1, res.getNumRefillsAllowed());
        assertEquals(2, res.getNumDaysFromOrigDate());
        assertEquals(3, res.getNumDaysExpire());
        assertEquals(4, res.getMaxDaysSupply());
        assertEquals(5, res.getMinPctUtil());
        assertEquals("Y", res.getAcceptFax());
        assertEquals((short) 6, res.getPlanNumRefillsAllowed());
        assertEquals(7, res.getPlanMinPctUtil());
        assertEquals(8, res.getPlanMaxPctUtil());
        assertEquals(9L, res.getPlanMinQuantity());
        assertEquals(10L, res.getPlanMaxQuantity());
        assertEquals((short) 11, res.getPlanMinDaysSupply());
        assertEquals((short) 12, res.getPlanMaxDaysSupply());
        checkFutures(res);
    }

    @Test
    public void test_getAnsDefaultRxPlanParams_2() {
        setUpMock();
        when(query.fetch()).thenReturn(ansDefaultRxPlanParamsDTOs);
        when(query.fetchFirst()).thenReturn(ansDefaultRxPlanParamsResult, ansDefaultRxPlanParamsResult, stateNum,
                getAnsDefaultRxPlanParamsResult2());

        AnsDefaultRxPlanParamsResult res = ansDefaultRxPlanParamsService.getAnsDefaultRxPlanParams(prodId, ppNum, scriptId);

        assertEquals("gprf2", res.getNarcoticCode());
        assertEquals(20, res.getNumRefillsAllowed());
        assertEquals(21, res.getNumDaysFromOrigDate());
        assertEquals(22, res.getNumDaysExpire());
        assertEquals(23, res.getMaxDaysSupply());
        assertEquals(24, res.getMinPctUtil());
        assertEquals("N", res.getAcceptFax());
        assertEquals((short) 6, res.getPlanNumRefillsAllowed());
        assertEquals(7, res.getPlanMinPctUtil());
        assertEquals(8, res.getPlanMaxPctUtil());
        assertEquals(9L, res.getPlanMinQuantity());
        assertEquals(10L, res.getPlanMaxQuantity());
        assertEquals((short) 11, res.getPlanMinDaysSupply());
        assertEquals((short) 12, res.getPlanMaxDaysSupply());
        checkFutures(res);
    }

    @Test
    public void test_getAnsDefaultRxPlanParams_3() {
        ansDefaultRxPlanParamsDTOs.remove(1);
        ansDefaultRxPlanParamsDTOs.get(0).setPatientAddrSeq(null);
        ansDefaultRxPlanParamsDTOs.get(0).setStateCsProductSeq(null);

        setUpMock();
        when(query.fetch()).thenReturn(ansDefaultRxPlanParamsDTOs);
        when(query.fetchFirst()).thenReturn(ansDefaultRxPlanParamsResult, ansDefaultRxPlanParamsResult, stateNum,
                getAnsDefaultRxPlanParamsResult2());

        AnsDefaultRxPlanParamsResult res = ansDefaultRxPlanParamsService.getAnsDefaultRxPlanParams(prodId, ppNum, scriptId);

        assertEquals("gprf2", res.getNarcoticCode());
        assertEquals(20, res.getNumRefillsAllowed());
        assertEquals(21, res.getNumDaysFromOrigDate());
        assertEquals(22, res.getNumDaysExpire());
        assertEquals(23, res.getMaxDaysSupply());
        assertEquals(24, res.getMinPctUtil());
        assertEquals("N", res.getAcceptFax());
        assertEquals((short) 6, res.getPlanNumRefillsAllowed());
        assertEquals(7, res.getPlanMinPctUtil());
        assertEquals(8, res.getPlanMaxPctUtil());
        assertEquals(9L, res.getPlanMinQuantity());
        assertEquals(10L, res.getPlanMaxQuantity());
        assertEquals((short) 11, res.getPlanMinDaysSupply());
        assertEquals((short) 12, res.getPlanMaxDaysSupply());
        checkFutures(res);
    }

    @Test
    public void test_getAnsDefaultRxPlanParams_4() {
        ansDefaultRxPlanParamsDTOs.remove(1);
        ansDefaultRxPlanParamsDTOs.get(0).setOrderInvoiceNumber(null);

        setUpMock();
        when(query.fetch()).thenReturn(ansDefaultRxPlanParamsDTOs);
        when(query.fetchFirst()).thenReturn(ansDefaultRxPlanParamsResult, ansDefaultRxPlanParamsResult, stateNum,
                getAnsDefaultRxPlanParamsResult2());

        AnsDefaultRxPlanParamsResult res = ansDefaultRxPlanParamsService.getAnsDefaultRxPlanParams(prodId, ppNum, scriptId);

        assertEquals("gprf2", res.getNarcoticCode());
        assertEquals(20, res.getNumRefillsAllowed());
        assertEquals(21, res.getNumDaysFromOrigDate());
        assertEquals(22, res.getNumDaysExpire());
        assertEquals(23, res.getMaxDaysSupply());
        assertEquals(24, res.getMinPctUtil());
        assertEquals("N", res.getAcceptFax());
        assertEquals((short) 6, res.getPlanNumRefillsAllowed());
        assertEquals(7, res.getPlanMinPctUtil());
        assertEquals(8, res.getPlanMaxPctUtil());
        assertEquals(9L, res.getPlanMinQuantity());
        assertEquals(10L, res.getPlanMaxQuantity());
        assertEquals((short) 11, res.getPlanMinDaysSupply());
        assertEquals((short) 12, res.getPlanMaxDaysSupply());
        checkFutures(res);
    }

    @Test
    public void test_getAnsDefaultRxPlanParams_5() {
        ansDefaultRxPlanParamsDTOs.remove(1);
        ansDefaultRxPlanParamsDTOs.get(0).setProdDea(null);
        ansDefaultRxPlanParamsDTOs.get(0).setOrderInvoiceNumber(null);

        setUpMock();
        when(query.fetch()).thenReturn(ansDefaultRxPlanParamsDTOs);
        when(query.fetchFirst()).thenReturn(ansDefaultRxPlanParamsResult, ansDefaultRxPlanParamsResult, stateNum,
                getAnsDefaultRxPlanParamsResult2());

        AnsDefaultRxPlanParamsResult res = ansDefaultRxPlanParamsService.getAnsDefaultRxPlanParams(prodId, ppNum, scriptId);

        assertEquals("gprf2", res.getNarcoticCode());
        assertEquals(20, res.getNumRefillsAllowed());
        assertEquals(21, res.getNumDaysFromOrigDate());
        assertEquals(22, res.getNumDaysExpire());
        assertEquals(23, res.getMaxDaysSupply());
        assertEquals(24, res.getMinPctUtil());
        assertEquals("N", res.getAcceptFax());
        assertEquals((short) 6, res.getPlanNumRefillsAllowed());
        assertEquals(7, res.getPlanMinPctUtil());
        assertEquals(8, res.getPlanMaxPctUtil());
        assertEquals(9L, res.getPlanMinQuantity());
        assertEquals(10L, res.getPlanMaxQuantity());
        assertEquals((short) 11, res.getPlanMinDaysSupply());
        assertEquals((short) 12, res.getPlanMaxDaysSupply());
        checkFutures(res);
    }

    @Test
    public void test_getAnsDefaultRxPlanParams_6() {
        ansDefaultRxPlanParamsDTOs.remove(1);
        ansDefaultRxPlanParamsDTOs.get(0).setProdDea(null);
        ansDefaultRxPlanParamsDTOs.get(0).setStateCsProductProdDea(null);
        ansDefaultRxPlanParamsDTOs.get(0).setDispensedProductId(null);

        setUpMock();
        when(query.fetch()).thenReturn(ansDefaultRxPlanParamsDTOs);
        when(query.fetchFirst()).thenReturn(ansDefaultRxPlanParamsResult, ansDefaultRxPlanParamsResult, stateNum,
                getAnsDefaultRxPlanParamsResult2());

        AnsDefaultRxPlanParamsResult res = ansDefaultRxPlanParamsService.getAnsDefaultRxPlanParams(prodId, ppNum, scriptId);

        assertEquals("gprf2", res.getNarcoticCode());
        assertEquals(20, res.getNumRefillsAllowed());
        assertEquals(21, res.getNumDaysFromOrigDate());
        assertEquals(22, res.getNumDaysExpire());
        assertEquals(23, res.getMaxDaysSupply());
        assertEquals(24, res.getMinPctUtil());
        assertEquals("N", res.getAcceptFax());
        assertEquals((short) 6, res.getPlanNumRefillsAllowed());
        assertEquals(7, res.getPlanMinPctUtil());
        assertEquals(8, res.getPlanMaxPctUtil());
        assertEquals(9L, res.getPlanMinQuantity());
        assertEquals(10L, res.getPlanMaxQuantity());
        assertEquals((short) 11, res.getPlanMinDaysSupply());
        assertEquals((short) 12, res.getPlanMaxDaysSupply());
        checkFutures(res);
    }

    @Test
    public void test_getAnsDefaultRxPlanParams_7() {
        ansDefaultRxPlanParamsDTOs.remove(1);
        ansDefaultRxPlanParamsDTOs.get(0).setProdDea(null);
        ansDefaultRxPlanParamsDTOs.get(0).setStateCsProductSeq(null);
        ansDefaultRxPlanParamsDTOs.get(0).setStateCsProductProdDea(null);
        ansDefaultRxPlanParamsDTOs.get(0).setDispensedProductId(null);

        setUpMock();
        when(query.fetch()).thenReturn(ansDefaultRxPlanParamsDTOs);
        when(query.fetchFirst()).thenReturn(ansDefaultRxPlanParamsResult, ansDefaultRxPlanParamsResult, stateNum,
                getAnsDefaultRxPlanParamsResult2());

        prodId = ansDefaultRxPlanParamsDTOs.get(0).getStateCsProductProdId();
        AnsDefaultRxPlanParamsResult res = ansDefaultRxPlanParamsService.getAnsDefaultRxPlanParams(prodId, ppNum, scriptId);

        assertEquals("gprf2", res.getNarcoticCode());
        assertEquals(20, res.getNumRefillsAllowed());
        assertEquals(21, res.getNumDaysFromOrigDate());
        assertEquals(22, res.getNumDaysExpire());
        assertEquals(23, res.getMaxDaysSupply());
        assertEquals(24, res.getMinPctUtil());
        assertEquals("N", res.getAcceptFax());
        assertEquals((short) 6, res.getPlanNumRefillsAllowed());
        assertEquals(7, res.getPlanMinPctUtil());
        assertEquals(8, res.getPlanMaxPctUtil());
        assertEquals(9L, res.getPlanMinQuantity());
        assertEquals(10L, res.getPlanMaxQuantity());
        assertEquals((short) 11, res.getPlanMinDaysSupply());
        assertEquals((short) 12, res.getPlanMaxDaysSupply());
        checkFutures(res);
    }

    @Test
    public void test_getAnsDefaultRxPlanParams_8() {
        ansDefaultRxPlanParamsDTOs.remove(1);
        ansDefaultRxPlanParamsDTOs.get(0).setProdDea(null);
        ansDefaultRxPlanParamsDTOs.get(0).setStateCsProductSeq(null);
        ansDefaultRxPlanParamsDTOs.get(0).setStateCsProductProdDea(null);
        ansDefaultRxPlanParamsDTOs.get(0).setDispensedProductId(null);
        ansDefaultRxPlanParamsDTOs.get(0).setOrderInvoiceNumber(null);

        setUpMock();
        when(query.fetch()).thenReturn(ansDefaultRxPlanParamsDTOs);
        when(query.fetchFirst()).thenReturn(ansDefaultRxPlanParamsResult, ansDefaultRxPlanParamsResult, stateNum,
                getAnsDefaultRxPlanParamsResult2());

        prodId = ansDefaultRxPlanParamsDTOs.get(0).getStateCsProductProdId();
        AnsDefaultRxPlanParamsResult res = ansDefaultRxPlanParamsService.getAnsDefaultRxPlanParams(prodId, ppNum, scriptId);

        assertEquals("gprf2", res.getNarcoticCode());
        assertEquals(20, res.getNumRefillsAllowed());
        assertEquals(21, res.getNumDaysFromOrigDate());
        assertEquals(22, res.getNumDaysExpire());
        assertEquals(23, res.getMaxDaysSupply());
        assertEquals(24, res.getMinPctUtil());
        assertEquals("N", res.getAcceptFax());
        assertEquals((short) 6, res.getPlanNumRefillsAllowed());
        assertEquals(7, res.getPlanMinPctUtil());
        assertEquals(8, res.getPlanMaxPctUtil());
        assertEquals(9L, res.getPlanMinQuantity());
        assertEquals(10L, res.getPlanMaxQuantity());
        assertEquals((short) 11, res.getPlanMinDaysSupply());
        assertEquals((short) 12, res.getPlanMaxDaysSupply());
        checkFutures(res);
    }

    @Test
    public void test_getAnsDefaultRxPlanParams_9() {
        ansDefaultRxPlanParamsDTOs.remove(1);
        ansDefaultRxPlanParamsDTOs.get(0).setProdDea(null);
        ansDefaultRxPlanParamsDTOs.get(0).setStateCsProductSeq(null);
        ansDefaultRxPlanParamsDTOs.get(0).setStateCsProductProdDea((short) 1);

        setUpMock();
        when(query.fetch()).thenReturn(ansDefaultRxPlanParamsDTOs);
        when(query.fetchFirst()).thenReturn(ansDefaultRxPlanParamsResult, ansDefaultRxPlanParamsResult, stateNum,
                getAnsDefaultRxPlanParamsResult2());

        prodId = 999L;
        AnsDefaultRxPlanParamsResult res = ansDefaultRxPlanParamsService.getAnsDefaultRxPlanParams(prodId, ppNum, scriptId);

        assertEquals("gprf2", res.getNarcoticCode());
        assertEquals(20, res.getNumRefillsAllowed());
        assertEquals(21, res.getNumDaysFromOrigDate());
        assertEquals(22, res.getNumDaysExpire());
        assertEquals(23, res.getMaxDaysSupply());
        assertEquals(24, res.getMinPctUtil());
        assertEquals("N", res.getAcceptFax());
        assertEquals((short) 6, res.getPlanNumRefillsAllowed());
        assertEquals(7, res.getPlanMinPctUtil());
        assertEquals(8, res.getPlanMaxPctUtil());
        assertEquals(9L, res.getPlanMinQuantity());
        assertEquals(10L, res.getPlanMaxQuantity());
        assertEquals((short) 11, res.getPlanMinDaysSupply());
        assertEquals((short) 12, res.getPlanMaxDaysSupply());
        checkFutures(res);
    }

    @Test
    public void test_getAnsDefaultRxPlanParams_10() {
        ansDefaultRxPlanParamsDTOs.remove(1);
        ansDefaultRxPlanParamsDTOs.get(0).setProdDea(null);
        ansDefaultRxPlanParamsDTOs.get(0).setStateCsProductSeq(null);
        ansDefaultRxPlanParamsDTOs.get(0).setStateCsProductProdDea((short) 0);

        setUpMock();
        when(query.fetch()).thenReturn(ansDefaultRxPlanParamsDTOs);
        when(query.fetchFirst()).thenReturn(ansDefaultRxPlanParamsResult, ansDefaultRxPlanParamsResult, stateNum,
                getAnsDefaultRxPlanParamsResult2());
        prodId = ansDefaultRxPlanParamsDTOs.get(0).getStateCsProductProdId();
        AnsDefaultRxPlanParamsResult res = ansDefaultRxPlanParamsService.getAnsDefaultRxPlanParams(prodId, ppNum, scriptId);

        assertEquals("gprf2", res.getNarcoticCode());
        assertEquals(20, res.getNumRefillsAllowed());
        assertEquals(21, res.getNumDaysFromOrigDate());
        assertEquals(22, res.getNumDaysExpire());
        assertEquals(23, res.getMaxDaysSupply());
        assertEquals(24, res.getMinPctUtil());
        assertEquals("N", res.getAcceptFax());
        assertEquals((short) 6, res.getPlanNumRefillsAllowed());
        assertEquals(7, res.getPlanMinPctUtil());
        assertEquals(8, res.getPlanMaxPctUtil());
        assertEquals(9L, res.getPlanMinQuantity());
        assertEquals(10L, res.getPlanMaxQuantity());
        assertEquals((short) 11, res.getPlanMinDaysSupply());
        assertEquals((short) 12, res.getPlanMaxDaysSupply());
        checkFutures(res);
    }

    @Test
    public void test_getAnsDefaultRxPlanParams_11() {
        ansDefaultRxPlanParamsDTOs.remove(1);
        ansDefaultRxPlanParamsDTOs.get(0).setDispensedProductId(null);
        ansDefaultRxPlanParamsDTOs.get(0).setProdDea(null);
        ansDefaultRxPlanParamsDTOs.get(0).setStateCsProductSeq(1L);
        ansDefaultRxPlanParamsDTOs.get(0).setStateCsProductProdDea((short) 0);

        setUpMock();
        when(query.fetch()).thenReturn(ansDefaultRxPlanParamsDTOs);
        when(query.fetchFirst()).thenReturn(ansDefaultRxPlanParamsResult, ansDefaultRxPlanParamsResult, stateNum,
                getAnsDefaultRxPlanParamsResult2());
        prodId = ansDefaultRxPlanParamsDTOs.get(0).getStateCsProductProdId();
        AnsDefaultRxPlanParamsResult res = ansDefaultRxPlanParamsService.getAnsDefaultRxPlanParams(prodId, ppNum, scriptId);

        assertEquals("gprf2", res.getNarcoticCode());
        assertEquals(20, res.getNumRefillsAllowed());
        assertEquals(21, res.getNumDaysFromOrigDate());
        assertEquals(22, res.getNumDaysExpire());
        assertEquals(23, res.getMaxDaysSupply());
        assertEquals(24, res.getMinPctUtil());
        assertEquals("N", res.getAcceptFax());
        assertEquals((short) 6, res.getPlanNumRefillsAllowed());
        assertEquals(7, res.getPlanMinPctUtil());
        assertEquals(8, res.getPlanMaxPctUtil());
        assertEquals(9L, res.getPlanMinQuantity());
        assertEquals(10L, res.getPlanMaxQuantity());
        assertEquals((short) 11, res.getPlanMinDaysSupply());
        assertEquals((short) 12, res.getPlanMaxDaysSupply());
        checkFutures(res);
    }

    /**
     * Check future fields of the result.
     *
     * @param res the result.
     */
    private void checkFutures(AnsDefaultRxPlanParamsResult res) {
        assertNull(res.getFuture1());
        assertNull(res.getFuture2());
        assertNull(res.getFuture3());
        assertNull(res.getFuture4());
        assertNull(res.getFuture5());
        assertNull(res.getFuture6());
        assertNull(res.getFuture7());
    }

    /**
     * Create an AnsDefaultRxPlanParamsResult object.
     *
     * @return the AnsDefaultRxPlanParamsResult object.
     */
    private static AnsDefaultRxPlanParamsResult getAnsDefaultRxPlanParamsResult1() {
        AnsDefaultRxPlanParamsResult result = new AnsDefaultRxPlanParamsResult();

        result.setNarcoticCode("gprf1");
        result.setNumRefillsAllowed(1);
        result.setNumDaysFromOrigDate(2);
        result.setNumDaysExpire(3);
        result.setMaxDaysSupply(4);
        result.setMinPctUtil(5);
        result.setAcceptFax("Y");
        result.setPlanNumRefillsAllowed((short) 6);
        result.setPlanMinPctUtil(7);
        result.setPlanMaxPctUtil(8);
        result.setPlanMinQuantity(9L);
        result.setPlanMaxQuantity(10L);
        result.setPlanMinDaysSupply((short) 11);
        result.setPlanMaxDaysSupply((short) 12);

        return result;
    }

    /**
     * Create an AnsDefaultRxPlanParamsResult object.
     *
     * @return the AnsDefaultRxPlanParamsResult object.
     */
    private static AnsDefaultRxPlanParamsResult getAnsDefaultRxPlanParamsResult2() {
        AnsDefaultRxPlanParamsResult result = new AnsDefaultRxPlanParamsResult();

        result.setNarcoticCode("gprf2");
        result.setNumRefillsAllowed(20);
        result.setNumDaysFromOrigDate(21);
        result.setNumDaysExpire(22);
        result.setMaxDaysSupply(23);
        result.setMinPctUtil(24);
        result.setAcceptFax("N");
        result.setPlanNumRefillsAllowed((short) 15);
        result.setPlanMinPctUtil(26);
        result.setPlanMaxPctUtil(27);
        result.setPlanMinQuantity(28L);
        result.setPlanMaxQuantity(29L);
        result.setPlanMinDaysSupply((short) 30);
        result.setPlanMaxDaysSupply((short) 31);

        return result;
    }

    /**
     * Get AnsDefaultRxPlanParamsDTO list.
     *
     * @return the AnsDefaultRxPlanParamsDTO list.
     */
    private List<AnsDefaultRxPlanParamsDTO> getAnsDefaultRxPlanParamsDTOs() {
        List<AnsDefaultRxPlanParamsDTO> list = new ArrayList<>();

        AnsDefaultRxPlanParamsDTO dto1 = new AnsDefaultRxPlanParamsDTO();
        dto1.setEScriptMsgAttributeSeq(BigInteger.valueOf(1));
        dto1.setDispensedProductId(2L);
        dto1.setOrderInvoiceNumber("OIN1");
        dto1.setPatientAddrSeq(3L);
        dto1.setStateCsProductSeq(4L);
        dto1.setStateCsProductProdId(2L);
        dto1.setProdDea((byte) 5);
        dto1.setProdId(2L);
        dto1.setStateCsProductProdDea((short) 6);
        list.add(dto1);

        AnsDefaultRxPlanParamsDTO dto2 = new AnsDefaultRxPlanParamsDTO();
        dto2.setEScriptMsgAttributeSeq(BigInteger.valueOf(21));
        dto2.setDispensedProductId(22L);
        dto2.setOrderInvoiceNumber("OIN2");
        dto2.setPatientAddrSeq(23L);
        dto2.setStateCsProductSeq(24L);
        dto1.setStateCsProductProdId(22L);
        dto2.setProdDea((byte) 25);
        dto2.setProdId(22L);
        dto2.setStateCsProductProdDea((short) 26);
        list.add(dto2);

        return list;
    }
}
