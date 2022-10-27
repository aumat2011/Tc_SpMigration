package com.humana.pharmacy.common.service.impl;

import com.humana.pharmacy.common.TestsHelper;
import com.humana.pharmacy.common.cache.InfinispanCache;
import com.humana.pharmacy.common.cache.model.COrderDefaults;
import com.humana.pharmacy.common.cache.model.CRxDefaults;
import com.humana.pharmacy.common.cache.model.CShippingMethod;
import com.humana.pharmacy.common.cache.model.CWorkflowQueue;
import com.humana.pharmacy.common.dto.RsMultiLinkDTO;
import com.humana.pharmacy.common.dto.RxEScriptDTO;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit test cases for BaseServiceImpl.
 */
@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
public class BaseServiceImplTest {
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
     * The BaseServiceImpl instance.
     */
    private BaseServiceImpl baseService;

    /**
     * The queue numbers.
     */
    private final List<Integer> queueNums = new ArrayList<>(Arrays.asList(1, 2));

    /**
     * The queue names.
     */
    private final List<String> queueNames = new ArrayList<>(Arrays.asList("wq101", "wq201"));

    /**
     * The queue name.
     */
    private String queueName = "wq101";

    /**
     * The RX defaults.
     */
    private CRxDefaults rxDefaults;

    /**
     * The workflow queues.
     */
    private List<CWorkflowQueue> queues;

    /**
     * The keyword.
     */
    private String keyword = "keyword1";
    /**
     * The trading partner number.
     */
    private final Long tpNum = 1001L;

    /**
     * The Rx number.
     */
    private String rxNumber = "rxn1";

    /**
     * The script ID.
     */
    private BigInteger scriptId = BigInteger.valueOf(1);

    /**
     * The Rx status code number.
     */
    private Byte rxStatusCodeNum = (byte) 1;

    /**
     * The RxEScriptDTO list.
     */
    private List<RxEScriptDTO> rxEScriptDTOs;

    /**
     * The order defaults.
     */
    private COrderDefaults orderDefaults;

    /**
     * The shipping methods.
     */
    private List<CShippingMethod> shippingMethods;

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
        baseService = new MockServiceImpl(epostrxDataSource, workflowDataSource);

        TestsHelper.setFieldValue(baseService, "epostrxQueryFactory", epostrxQueryFactory);
        TestsHelper.setFieldValue(baseService, "workflowQueryFactory", workflowQueryFactory);

        rxDefaults = getRxDefaults();
        queues = getQueues();

        rxEScriptDTOs = getRxEScriptDTOs();

        orderDefaults = getOrderDefaults();
        shippingMethods = getShippingMethods();
    }

    @AfterEach
    public void cleanUp() throws Exception {
        TestsHelper.clearCache();
    }

    @Test
    public void test_ctor_epostrxDataSourceNull() {
        assertThrows(RuntimeException.class, () -> new MockServiceImpl(null, workflowDataSource));
    }

    @Test
    public void test_ctor_workflowDataSourceNull() {
        assertThrows(RuntimeException.class, () -> new MockServiceImpl(epostrxDataSource, null));
    }

    @Test
    public void test_getRxDefaults_1() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.fetchFirst()).thenReturn(rxDefaults);

        CRxDefaults res = baseService.getRxDefaults();
        assertEquals(rxDefaults.getMaxPvDays(), res.getMaxPvDays());
        assertEquals(rxDefaults.getOtcAsRxPrecheck(), res.getOtcAsRxPrecheck());
        assertEquals(rxDefaults.getPrecheckAll(), res.getPrecheckAll());
        assertEquals(rxDefaults.getPwoPrecheck(), res.getPwoPrecheck());
        assertEquals(rxDefaults.getCheckNarcoticRefills(), res.getCheckNarcoticRefills());
    }

    @Test
    public void test_getRxDefaults_2() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.fetchFirst()).thenReturn(rxDefaults);

        CRxDefaults res1 = baseService.getRxDefaults();
        CRxDefaults res2 = baseService.getRxDefaults();
        assertEquals(res1.getMaxPvDays(), res2.getMaxPvDays());
        assertEquals(res1.getOtcAsRxPrecheck(), res2.getOtcAsRxPrecheck());
        assertEquals(res1.getPrecheckAll(), res2.getPrecheckAll());
        assertEquals(res1.getPwoPrecheck(), res2.getPwoPrecheck());
        assertEquals(res1.getCheckNarcoticRefills(), res2.getCheckNarcoticRefills());
    }

    @Test
    public void test_getRxDefaults_3() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.fetchFirst()).thenReturn(null);

        CRxDefaults res = baseService.getRxDefaults();
        assertNull(res.getMaxPvDays());
        assertNull(res.getOtcAsRxPrecheck());
        assertNull(res.getPrecheckAll());
        assertNull(res.getPwoPrecheck());
        assertNull(res.getCheckNarcoticRefills());
    }

    @Test
    public void test_getCodeValueStatus_keywordNull() {
        assertThrows(RuntimeException.class, () -> baseService.getCodeValueStatus(null));
    }

    @Test
    public void test_getCodeValueStatus_keywordEmpty() {
        keyword = " ";

        assertThrows(RuntimeException.class, () -> baseService.getCodeValueStatus(keyword));
    }

    @Test
    public void test_getCodeValueStatus_1() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchFirst()).thenReturn("status1");

        String res = baseService.getCodeValueStatus(keyword);
        assertEquals("status1", res);
    }

    @Test
    public void test_getCodeValueStatus_2() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchFirst()).thenReturn("status1");

        String res1 = baseService.getCodeValueStatus(keyword);
        String res2 = baseService.getCodeValueStatus(keyword);
        assertEquals(res1, res2);
    }

    @Test
    public void test_getCodeValueStatus_3() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchFirst()).thenReturn(null);

        String res = baseService.getCodeValueStatus(keyword);
        assertNull(res);
    }

    @Test
    public void test_getTradingPartnerType_tpNumNull() {
        assertThrows(RuntimeException.class, () -> baseService.getTradingPartnerType(null));
    }

    @Test
    public void test_getTradingPartnerType_1() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchFirst()).thenReturn("type1");

        String res = baseService.getTradingPartnerType(tpNum);
        assertEquals("type1", res);
    }

    @Test
    public void test_getTradingPartnerType_2() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchFirst()).thenReturn("type1");

        String res1 = baseService.getTradingPartnerType(tpNum);
        String res2 = baseService.getTradingPartnerType(tpNum);
        assertEquals(res1, res2);
    }

    @Test
    public void test_getTradingPartnerType_3() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchFirst()).thenReturn(null);

        String res = baseService.getTradingPartnerType(tpNum);
        assertNull(res);
    }

    @Test
    public void test_getWorkflowQueueNames_queueNumsNull() {
        assertThrows(RuntimeException.class, () -> baseService.getWorkflowQueueNames(null));
    }

    @Test
    public void test_getWorkflowQueueNames_queueNumsEmpty() {
        queueNums.clear();

        assertThrows(RuntimeException.class, () -> baseService.getWorkflowQueueNames(queueNums));
    }

    @Test
    public void test_getWorkflowQueueNames_1() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(workflowQuery);
        when(workflowQuery.from(any(Expression.class))).thenReturn(workflowQuery);
        when(workflowQuery.join(any(EntityPath.class))).thenReturn(workflowQuery);
        when(workflowQuery.on(any(Predicate.class))).thenReturn(workflowQuery);
        when(workflowQuery.where(any(Predicate.class))).thenReturn(workflowQuery);
        when(workflowQuery.fetch()).thenReturn(queues);

        Map<Integer, String> res = baseService.getWorkflowQueueNames(queueNums);
        assertEquals(2, res.size());
        assertEquals("wq101", res.get(1));
        assertEquals("wq201", res.get(2));
    }

    @Test
    public void test_getWorkflowQueueNames_2() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(workflowQuery);
        when(workflowQuery.from(any(Expression.class))).thenReturn(workflowQuery);
        when(workflowQuery.join(any(EntityPath.class))).thenReturn(workflowQuery);
        when(workflowQuery.on(any(Predicate.class))).thenReturn(workflowQuery);
        when(workflowQuery.where(any(Predicate.class))).thenReturn(workflowQuery);
        when(workflowQuery.fetch()).thenReturn(queues);

        Map<Integer, String> res1 = baseService.getWorkflowQueueNames(queueNums);
        Map<Integer, String> res2 = baseService.getWorkflowQueueNames(queueNums);
        assertEquals(res1.size(), res2.size());
        assertEquals("wq101", res2.get(1));
        assertEquals("wq201", res2.get(2));
    }

    @Test
    public void test_getWorkflowQueueNumbers_queueNamesNull() {
        assertThrows(RuntimeException.class, () -> baseService.getWorkflowQueueNumbers(null, tpNum));
    }

    @Test
    public void test_getWorkflowQueueNumbers_queueNamesEmpty() {
        queueNames.clear();

        assertThrows(
                RuntimeException.class, () -> baseService.getWorkflowQueueNumbers(queueNames, tpNum));
    }

    @Test
    public void test_getWorkflowQueueNumbers_1() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(workflowQuery);
        when(workflowQuery.from(any(Expression.class))).thenReturn(workflowQuery);
        when(workflowQuery.join(any(EntityPath.class))).thenReturn(workflowQuery);
        when(workflowQuery.on(any(Predicate.class))).thenReturn(workflowQuery);
        when(workflowQuery.where(any(Predicate.class))).thenReturn(workflowQuery);
        when(workflowQuery.fetch()).thenReturn(queues);

        Map<String, List<Integer>> res = baseService.getWorkflowQueueNumbers(queueNames, null);
        assertEquals(2, res.size());
        assertEquals(1, res.get("wq101").get(0));
        assertEquals(2, res.get("wq201").get(0));
    }

    @Test
    public void test_getWorkflowQueueNumbers_2() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        queues.get(1).setTradingPartnerNum(tpNum);
        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(workflowQuery);
        when(workflowQuery.from(any(Expression.class))).thenReturn(workflowQuery);
        when(workflowQuery.join(any(EntityPath.class))).thenReturn(workflowQuery);
        when(workflowQuery.on(any(Predicate.class))).thenReturn(workflowQuery);
        when(workflowQuery.where(any(Predicate.class))).thenReturn(workflowQuery);
        when(workflowQuery.fetch()).thenReturn(queues);

        Map<String, List<Integer>> res1 = baseService.getWorkflowQueueNumbers(queueNames, tpNum);
        Map<String, List<Integer>> res2 = baseService.getWorkflowQueueNumbers(queueNames, tpNum);
        assertEquals(res1.size(), res2.size());
    }

    @Test
    public void test_getWorkflowQueueNumbers_3() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(workflowQuery);
        when(workflowQuery.from(any(Expression.class))).thenReturn(workflowQuery);
        when(workflowQuery.join(any(EntityPath.class))).thenReturn(workflowQuery);
        when(workflowQuery.on(any(Predicate.class))).thenReturn(workflowQuery);
        when(workflowQuery.where(any(Predicate.class))).thenReturn(workflowQuery);
        when(workflowQuery.fetch()).thenReturn(queues);

        Map<String, List<Integer>> res = baseService.getWorkflowQueueNumbers(queueNames, null);
        assertEquals(2, res.size());
        queueNames.remove(1);
        res = baseService.getWorkflowQueueNumbers(queueNames, tpNum);
        assertEquals(1, res.size());
    }

    @Test
    public void test_getWorkflowQueueNumber_queueNameNull() {
        assertThrows(RuntimeException.class, () -> baseService.getWorkflowQueueNumber(null, tpNum));
    }

    @Test
    public void test_getWorkflowQueueNumber_queueNameEmpty() {
        queueName = " ";

        assertThrows(
                RuntimeException.class, () -> baseService.getWorkflowQueueNumber(queueName, tpNum));
    }

    @Test
    public void test_getWorkflowQueueNumber_1() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        queues.remove(1);
        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(workflowQuery);
        when(workflowQuery.from(any(Expression.class))).thenReturn(workflowQuery);
        when(workflowQuery.join(any(EntityPath.class))).thenReturn(workflowQuery);
        when(workflowQuery.on(any(Predicate.class))).thenReturn(workflowQuery);
        when(workflowQuery.where(any(Predicate.class))).thenReturn(workflowQuery);
        when(workflowQuery.fetch()).thenReturn(queues);

        Integer res = baseService.getWorkflowQueueNumber(queueName, null);
        assertEquals(1, res);
    }

    @Test
    public void test_getWorkflowQueueNumber_2() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        queues.remove(1);
        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(workflowQuery);
        when(workflowQuery.from(any(Expression.class))).thenReturn(workflowQuery);
        when(workflowQuery.join(any(EntityPath.class))).thenReturn(workflowQuery);
        when(workflowQuery.on(any(Predicate.class))).thenReturn(workflowQuery);
        when(workflowQuery.where(any(Predicate.class))).thenReturn(workflowQuery);
        when(workflowQuery.fetch()).thenReturn(queues);

        Integer res1 = baseService.getWorkflowQueueNumber(queueName, null);
        Integer res2 = baseService.getWorkflowQueueNumber(queueName, tpNum);
        assertEquals(res1, res2);
    }

    @Test
    public void test_getWorkflowQueueNumber_3() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        queues.clear();
        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(workflowQuery);
        when(workflowQuery.from(any(Expression.class))).thenReturn(workflowQuery);
        when(workflowQuery.join(any(EntityPath.class))).thenReturn(workflowQuery);
        when(workflowQuery.on(any(Predicate.class))).thenReturn(workflowQuery);
        when(workflowQuery.where(any(Predicate.class))).thenReturn(workflowQuery);
        when(workflowQuery.fetch()).thenReturn(queues);

        Integer res = baseService.getWorkflowQueueNumber(queueName, null);
        assertNull(res);
    }

    @Test
    public void test_getRxEScripts1_scriptIdNull() {
        scriptId = null;
        assertThrows(RuntimeException.class, () -> baseService.getRxEScripts(scriptId));
    }

    @Test
    public void test_getRxEScripts1() {
        setUpMock();

        List<RxEScriptDTO> res = baseService.getRxEScripts(scriptId);
        assertEquals(2, res.size());
    }

    @Test
    public void test_getRxEScripts2_rxNumberNull() {
        rxNumber = null;
        assertThrows(RuntimeException.class, () -> baseService.getRxEScripts(rxNumber));
    }

    @Test
    public void test_getRxEScripts2_rxNumberEmpty() {
        assertThrows(RuntimeException.class, () -> baseService.getRxEScripts(" "));
    }

    @Test
    public void test_getRxEScripts2() {
        setUpMock();

        List<RxEScriptDTO> res = baseService.getRxEScripts(rxNumber);
        assertEquals(2, res.size());
    }

    @Test
    public void test_getRxStatusCode_rxStatusCodeNumNull() {
        assertThrows(RuntimeException.class, () -> baseService.getRxStatusCode(null));
    }

    @Test
    public void test_getRxStatusCode_1() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchFirst()).thenReturn("rx_status1");

        String res = baseService.getRxStatusCode(rxStatusCodeNum);
        assertEquals("rx_status1", res);
    }

    @Test
    public void test_getRxStatusCode_2() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchFirst()).thenReturn("rx_status1");

        String res1 = baseService.getRxStatusCode(rxStatusCodeNum);
        String res2 = baseService.getRxStatusCode(rxStatusCodeNum);
        assertEquals(res1, res2);
    }

    @Test
    public void test_getRxStatusCode_3() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchFirst()).thenReturn(null);

        String res = baseService.getRxStatusCode(rxStatusCodeNum);
        assertNull(res);
    }

    @Test
    public void test_getOrderDefaults_1() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        COrderDefaults res = baseService.getOrderDefaults();
        assertEquals(orderDefaults.getFamilyMode(), res.getFamilyMode());
    }

    @Test
    public void test_getOrderDefaults_2() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        COrderDefaults res1 = baseService.getOrderDefaults();
        COrderDefaults res2 = baseService.getOrderDefaults();
        assertEquals(res1.getFamilyMode(), res2.getFamilyMode());
    }

    @Test
    public void test_getOrderDefaults_3() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);
        when(query.fetchFirst()).thenReturn(null);

        COrderDefaults res = baseService.getOrderDefaults();
        assertNull(res.getFamilyMode());
    }

    @Test
    public void test_getShippingMethods_1() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);
        when(query.fetch()).thenReturn(shippingMethods);

        List<CShippingMethod> res = baseService.getShippingMethods();
        assertEquals(shippingMethods.size(), res.size());
    }

    @Test
    public void test_getShippingMethods_2() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);
        when(query.fetch()).thenReturn(shippingMethods);

        List<CShippingMethod> res1 = baseService.getShippingMethods();
        List<CShippingMethod> res2 = baseService.getShippingMethods();
        assertEquals(res1.size(), res2.size());
    }

    @Test
    public void test_getRsRxLinkingParentChild_1() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);
        when(query.distinct()).thenReturn(query);
        when(query.join(any())).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.fetch()).thenReturn(getRsMultiLinkDTOs());

        List<RsMultiLinkDTO> res = baseService.getRsRxLinkingParentChild("8001", "Y");

        assertEquals(5, res.size());
    }

    @Test
    public void test_getRsRxLinkingParentChild_2() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);

        when(query.distinct()).thenReturn(query);
        when(query.join(any())).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.fetch()).thenReturn(getRsMultiLinkDTOs());

        List<RsMultiLinkDTO> res = baseService.getRsRxLinkingParentChild("8001", "All");

        assertEquals(5, res.size());
    }

    private void setUpMock() {
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
    }

    /**
     * Get RsMultiLinkDTO list.
     *
     * @return the RsMultiLinkDTO list
     */
    private List<RsMultiLinkDTO> getRsMultiLinkDTOs() {
        RsMultiLinkDTO r1 = new RsMultiLinkDTO();
        r1.setParentRxNumber("10001");
        r1.setChildRxNumber("9001");
        RsMultiLinkDTO r2 = new RsMultiLinkDTO();
        r2.setParentRxNumber("9001");
        r2.setChildRxNumber("8001");
        RsMultiLinkDTO r3 = new RsMultiLinkDTO();
        r3.setParentRxNumber("8001");
        r3.setChildRxNumber("7001");
        RsMultiLinkDTO r4 = new RsMultiLinkDTO();
        r4.setParentRxNumber("7001");
        r4.setChildRxNumber("6001");
        RsMultiLinkDTO r5 = new RsMultiLinkDTO();
        r5.setParentRxNumber("6001");
        r5.setChildRxNumber("5001");
        return Arrays.asList(r1, r2, r3, r4, r5);
    }

    /**
     * Get RxEScriptDTO list.
     *
     * @return the RxEScriptDTO list.
     */
    private List<RxEScriptDTO> getRxEScriptDTOs() {
        List<RxEScriptDTO> list = new ArrayList<>();

        RxEScriptDTO dto1 = new RxEScriptDTO();
        dto1.setEScriptMsgAttributeSeq(BigInteger.valueOf(1));
        dto1.setRxNumber("rxn1");
        dto1.setFillQtyDispensed(100L);
        dto1.setWrittenQuantity(101L);
        dto1.setFillStatusNum((byte) 2);
        list.add(dto1);
        RxEScriptDTO dto2 = new RxEScriptDTO();
        dto2.setEScriptMsgAttributeSeq(BigInteger.valueOf(1));
        dto2.setRxNumber("rxn1");
        dto2.setFillQtyDispensed(200L);
        dto2.setWrittenQuantity(201L);
        list.add(dto2);

        return list;
    }

    /**
     * Create CWorkflowQueue instances.
     *
     * @return the CWorkflowQueue instances.
     */
    private List<CWorkflowQueue> getQueues() {
        List<CWorkflowQueue> queues = new ArrayList<>();

        queues.add(new CWorkflowQueue(1, 1001L, "wq101"));
        queues.add(new CWorkflowQueue(2, 2001L, "wq201"));

        return queues;
    }

    /**
     * Create list of CShippingMethod.
     *
     * @return the CShippingMethod list
     */
    private static List<CShippingMethod> getShippingMethods() {
        List<CShippingMethod> list = new ArrayList<>();

        list.add(new CShippingMethod(1, "desc1"));
        list.add(new CShippingMethod(2, "desc2"));

        return list;
    }

    /**
     * Create an instance of COrderDefaults.
     *
     * @return the COrderDefaults instance
     */
    private static COrderDefaults getOrderDefaults() {
        return new COrderDefaults("familyMode1");
    }

    /**
     * Create an instance of CRxDefaults.
     *
     * @return the CRxDefaults instance
     */
    private static CRxDefaults getRxDefaults() {
        return new CRxDefaults(
                (short) 1, "otcAsRxPrecheck1", "precheckAll1", "pwoPrecheck1", "checkNarcoticRefills1");
    }
}
