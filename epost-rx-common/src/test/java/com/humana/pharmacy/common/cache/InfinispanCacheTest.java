package com.humana.pharmacy.common.cache;

import com.humana.pharmacy.common.TestsHelper;
import com.humana.pharmacy.common.cache.model.CCodeValue;
import com.humana.pharmacy.common.cache.model.CRule;
import com.humana.pharmacy.common.cache.model.CRxDefaults;
import com.humana.pharmacy.common.cache.model.CWorkflowQueue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.humana.pharmacy.common.constants.CacheName.CODE_VALUES;
import static com.humana.pharmacy.common.constants.CacheName.CODE_VALUE_STATUS;
import static com.humana.pharmacy.common.constants.CacheName.RULES;
import static com.humana.pharmacy.common.constants.CacheName.RX_DEFAULTS;
import static com.humana.pharmacy.common.constants.CacheName.TRADING_PARTNER_TYPES;
import static com.humana.pharmacy.common.constants.CacheName.WORKFLOW_QUEUES;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit test cases for InfinispanCache;
 */
public class InfinispanCacheTest {
    /**
     * The queue numbers.
     */
    private final List<Integer> queueNums = new ArrayList<>(Arrays.asList(1, 2));
    /**
     * The queue names.
     */
    private final List<String> queueNames = new ArrayList<>(Arrays.asList("wq101", "wq201"));
    /**
     * The trading partner number.
     */
    private Long tpNum = 1001L;

    @AfterEach
    public void cleanUp() throws Exception {
        TestsHelper.clearCache();
    }

    @Test
    public void test_initialize_twice() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());
        assertDoesNotThrow(() -> InfinispanCache.initialize(TestsHelper.getCacheManager()));
    }

    @Test
    public void test_putAll_without_initialized() {
        assertDoesNotThrow(() -> InfinispanCache.putAll(WORKFLOW_QUEUES, new HashMap<>()));
        assertDoesNotThrow(() -> InfinispanCache.putAll(WORKFLOW_QUEUES, null));
    }

    @Test
    public void test_put_without_initialized() {
        assertDoesNotThrow(() -> InfinispanCache.put(WORKFLOW_QUEUES, "cw1", new CWorkflowQueue()));
        assertDoesNotThrow(() -> InfinispanCache.put(WORKFLOW_QUEUES, "cw1", null));
    }

    @Test
    public void test_get_without_initialized() {
        assertNull(InfinispanCache.get(WORKFLOW_QUEUES, "cw1"));
    }

    @Test
    public void test_remove_without_initialized() {
        assertDoesNotThrow(() -> InfinispanCache.remove(WORKFLOW_QUEUES, "cw1"));
    }

    @Test
    public void test_cache_function() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        // WORKFLOW_QUEUES
        InfinispanCache.put(WORKFLOW_QUEUES, 1, new CWorkflowQueue(1, null, "cw1"));
        CWorkflowQueue saved = InfinispanCache.get(WORKFLOW_QUEUES, 1);
        assertEquals("cw1", saved.getWorkflowQueueName());

        InfinispanCache.remove(WORKFLOW_QUEUES, 1);
        assertNull(InfinispanCache.get(WORKFLOW_QUEUES, 1));

        // RX_DEFAULTS
        InfinispanCache.put(RX_DEFAULTS, "df1", new CRxDefaults((short) 101, "Y", "Y", "Y", "Y"));
        CRxDefaults savedDefaults = InfinispanCache.get(RX_DEFAULTS, "df1");
        assertEquals((short) 101, savedDefaults.getMaxPvDays());

        InfinispanCache.remove(RX_DEFAULTS, "df1");
        assertNull(InfinispanCache.get(RX_DEFAULTS, "df1"));

        // CODE_VALUE_STATUS
        InfinispanCache.put(CODE_VALUE_STATUS, "cvs1", "cvs01");
        String savedCvs = InfinispanCache.get(CODE_VALUE_STATUS, "cvs1");
        assertEquals("cvs01", savedCvs);

        InfinispanCache.remove(CODE_VALUE_STATUS, "cvs1");
        assertNull(InfinispanCache.get(CODE_VALUE_STATUS, "cvs1"));

        // TRADING_PARTNER_TYPES
        InfinispanCache.put(TRADING_PARTNER_TYPES, 101L, "tpt1");
        String savedTpt = InfinispanCache.get(TRADING_PARTNER_TYPES, 101L);
        assertEquals("tpt1", savedTpt);

        InfinispanCache.remove(TRADING_PARTNER_TYPES, 101L);
        assertNull(InfinispanCache.get(TRADING_PARTNER_TYPES, 101L));
    }

    @Test
    public void test_getWorkflowQueues1_queueNumsIdsNull() {
        assertThrows(RuntimeException.class, () -> InfinispanCache.getWorkflowQueues(null));
    }

    @Test
    public void test_getWorkflowQueues1_queueNumsIdsEmpty() {
        queueNums.clear();

        assertThrows(RuntimeException.class, () -> InfinispanCache.getWorkflowQueues(queueNums));
    }

    @Test
    public void test_getWorkflowQueues1_1() throws Exception {
        TestsHelper.clearCache();

        List<CWorkflowQueue> res = InfinispanCache.getWorkflowQueues(queueNums);

        assertNull(res);
    }

    @Test
    public void test_getWorkflowQueues1_2() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        List<CWorkflowQueue> res = InfinispanCache.getWorkflowQueues(queueNums);

        assertEquals(0, res.size());
    }

    @Test
    public void test_getWorkflowQueues1_3() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        CWorkflowQueue queue1 = new CWorkflowQueue(1, 1001L, "wq101");
        CWorkflowQueue queue2 = new CWorkflowQueue(2, 2001L, "wq201");

        InfinispanCache.put(WORKFLOW_QUEUES, queue1.getWorkflowQueueNum(), queue1);
        InfinispanCache.put(WORKFLOW_QUEUES, queue2.getWorkflowQueueNum(), queue2);

        List<CWorkflowQueue> res = InfinispanCache.getWorkflowQueues(queueNums);

        assertEquals(2, res.size());

        queueNums.remove(0);
        res = InfinispanCache.getWorkflowQueues(queueNums);

        assertEquals(1, res.size());
        assertEquals(queueNums.get(0), res.get(0).getWorkflowQueueNum());
    }

    @Test
    public void test_getWorkflowQueues2_queueNamesNull() {
        assertThrows(RuntimeException.class, () -> InfinispanCache.getWorkflowQueues(null, tpNum));
    }

    @Test
    public void test_getWorkflowQueues2_queueNamesEmpty() {
        queueNames.clear();

        assertThrows(
                RuntimeException.class, () -> InfinispanCache.getWorkflowQueues(queueNames, tpNum));
    }

    @Test
    public void test_getWorkflowQueues2_1() throws Exception {
        TestsHelper.clearCache();

        List<CWorkflowQueue> res = InfinispanCache.getWorkflowQueues(queueNames, tpNum);

        assertNull(res);
    }

    @Test
    public void test_getWorkflowQueues2_2() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        List<CWorkflowQueue> res = InfinispanCache.getWorkflowQueues(queueNames, tpNum);

        assertEquals(0, res.size());
    }

    @Test
    public void test_getWorkflowQueues2_3() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        CWorkflowQueue queue1 = new CWorkflowQueue(1, 1001L, "wq101");
        CWorkflowQueue queue2 = new CWorkflowQueue(2, 2001L, "wq201");
        CWorkflowQueue queue3 = new CWorkflowQueue(3, 1001L, "wq101");

        InfinispanCache.put(WORKFLOW_QUEUES, queue1.getWorkflowQueueNum(), queue1);
        InfinispanCache.put(WORKFLOW_QUEUES, queue2.getWorkflowQueueNum(), queue2);
        InfinispanCache.put(WORKFLOW_QUEUES, queue3.getWorkflowQueueNum(), queue3);

        List<CWorkflowQueue> res = InfinispanCache.getWorkflowQueues(queueNames, null);

        assertEquals(3, res.size());

        res = InfinispanCache.getWorkflowQueues(queueNames, tpNum);

        assertEquals(2, res.size());

        queueNames.remove(0);
        tpNum = 2001L;
        res = InfinispanCache.getWorkflowQueues(queueNames, tpNum);

        assertEquals(1, res.size());
        assertEquals(queueNames.get(0), res.get(0).getWorkflowQueueName());
        assertEquals(tpNum, res.get(0).getTradingPartnerNum());
    }

    @Test
    public void test_getRules() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        CRule rule1 = new CRule(1L, "orderBasedRule1");
        CRule rule2 = new CRule(2L, "orderBasedRule2");
        CRule rule3 = new CRule(3L, "orderBasedRule1");

        InfinispanCache.put(RULES, rule1.getRuleId(), rule1);
        InfinispanCache.put(RULES, rule2.getRuleId(), rule2);
        InfinispanCache.put(RULES, rule3.getRuleId(), rule3);

        List<CRule> res = InfinispanCache.getRules("orderBasedRule2");

        assertEquals(1, res.size());
        assertEquals(2L, res.get(0).getRuleId());
        assertEquals("orderBasedRule2", res.get(0).getOrderBasedRule());

        res = InfinispanCache.getRules("orderBasedRule1");

        assertEquals(2, res.size());

        res = InfinispanCache.getRules("orderBasedRuleX");

        assertEquals(0, res.size());
    }

    @Test
    public void test_getCodeValues() {
        InfinispanCache.initialize(TestsHelper.getCacheManager());

        CCodeValue codeValue1 = new CCodeValue(1, "codeValueKeyword1");
        codeValue1.setFkCodeCatId("fkCodeCatId1");
        CCodeValue codeValue2 = new CCodeValue(2, "codeValueKeyword2");
        codeValue2.setFkCodeCatId("fkCodeCatId2");
        CCodeValue codeValue3 = new CCodeValue(3, "codeValueKeyword3");
        codeValue3.setFkCodeCatId("fkCodeCatId1");

        InfinispanCache.put(CODE_VALUES, codeValue1.getCodeValueId(), codeValue1);
        InfinispanCache.put(CODE_VALUES, codeValue2.getCodeValueId(), codeValue2);
        InfinispanCache.put(CODE_VALUES, codeValue3.getCodeValueId(), codeValue3);

        List<CCodeValue> res = InfinispanCache.getCodeValues("fkCodeCatId2");

        assertEquals(1, res.size());
        assertEquals(2, res.get(0).getCodeValueId());
        assertEquals("fkCodeCatId2", res.get(0).getFkCodeCatId());

        res = InfinispanCache.getCodeValues("fkCodeCatId1");

        assertEquals(2, res.size());

        res = InfinispanCache.getCodeValues("fkCodeCatIdX");

        assertEquals(0, res.size());
    }
}
