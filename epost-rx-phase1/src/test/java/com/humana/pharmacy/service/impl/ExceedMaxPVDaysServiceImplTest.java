package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.TestsHelper;
import com.humana.pharmacy.dto.ExceedMaxPVDaysResult;
import com.humana.pharmacy.common.cache.model.CRxDefaults;
import com.humana.pharmacy.common.cache.model.CWorkflowQueue;
import com.humana.pharmacy.common.entity.QEScriptMsgAttributes;
import com.humana.pharmacy.common.entity.QOrderHeader;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.Tuple;
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

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit test cases for ExceedMaxPVDaysServiceImpl;
 */
@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
public class ExceedMaxPVDaysServiceImplTest {
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
     * The ExceedMaxPVDaysServiceImpl instance.
     */
    private ExceedMaxPVDaysServiceImpl exceedMaxPVDaysService;

    /**
     * The order number.
     */
    private final BigInteger orderNum = BigInteger.valueOf(1);

    /**
     * The rx defaults.
     */
    private CRxDefaults rxDefaults;

    /**
     * The tuple.
     */
    @Mock
    private Tuple tuple1;

    /**
     * The tuple.
     */
    @Mock
    private Tuple tuple2;

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
        exceedMaxPVDaysService = new ExceedMaxPVDaysServiceImpl(epostrxDataSource, workflowDataSource);

        TestsHelper.setFieldValue(exceedMaxPVDaysService, "epostrxQueryFactory", epostrxQueryFactory);
        TestsHelper.setFieldValue(exceedMaxPVDaysService, "workflowQueryFactory", workflowQueryFactory);

        rxDefaults = getRxDefaults();
    }

    private void setUpMock(boolean workflowMock) {
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.distinct()).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);

        when(query.fetchFirst()).thenReturn(rxDefaults);
        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class))).thenReturn(query);
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
        assertThrows(RuntimeException.class, () -> new ExceedMaxPVDaysServiceImpl(null, workflowDataSource));
    }

    @Test
    public void test_ctor_workflowDataSourceNull() {
        assertThrows(RuntimeException.class, () -> new ExceedMaxPVDaysServiceImpl(epostrxDataSource, null));
    }

    @Test
    public void test_checkExceedMaxPVDays_orderNumNull() {
        assertThrows(RuntimeException.class, () -> exceedMaxPVDaysService.checkExceedMaxPVDays(null));
    }

    @Test
    public void test_checkExceedMaxPVDays_1() {
        setUpMock(false);

        ExceedMaxPVDaysResult res = exceedMaxPVDaysService.checkExceedMaxPVDays(orderNum);
        assertNull(res);
    }

    @Test
    public void test_checkExceedMaxPVDays_2() {
        setUpMock(true);

        List<Tuple> tuples = getTuples();
        when(query.fetch()).thenReturn(tuples);

        ExceedMaxPVDaysResult res = exceedMaxPVDaysService.checkExceedMaxPVDays(orderNum);
        assertTrue(res.getRxNumbers().contains("rxn1"));
        assertTrue(res.getRxNumbers().contains("rxn2"));
        assertEquals((short) 1, res.getMaxPVDays());
        assertEquals("AZ CONSOLIDATE QUEUE", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkExceedMaxPVDays_3() {
        rxDefaults.setMaxPvDays(null);
        rxDefaults.setPrecheckAll("Y");
        rxDefaults.setPwoPrecheck("Y");
        rxDefaults.setOtcAsRxPrecheck("Y");

        setUpMock(true);

        List<Tuple> tuples = getTuples();
        when(query.fetch()).thenReturn(tuples);

        ExceedMaxPVDaysResult res = exceedMaxPVDaysService.checkExceedMaxPVDays(orderNum);
        assertTrue(res.getRxNumbers().contains("rxn1"));
        assertTrue(res.getRxNumbers().contains("rxn2"));
        assertEquals((short) 0, res.getMaxPVDays());
        assertEquals("AZ CONSOLIDATE QUEUE", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    /**
     * Create an instance of CRxDefaults.
     *
     * @return the CRxDefaults instance
     */
    private static CRxDefaults getRxDefaults() {
        return new CRxDefaults((short) 1, "otcAsRxPrecheck1",
                "precheckAll1", "pwoPrecheck1", "checkNarcoticRefills1");
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

    /**
     * Get the tuple list.
     *
     * @return The tuple list.
     */
    private List<Tuple> getTuples() {
        List<Tuple> tuples = new ArrayList<>();
        tuples.add(tuple1);
        tuples.add(tuple2);

        QEScriptMsgAttributes qEscript = QEScriptMsgAttributes.eScriptMsgAttributes;
        QOrderHeader qOrderHeader = QOrderHeader.orderHeader;

        when(tuple1.get(qEscript.rxNumber)).thenReturn("rxn1");
        when(tuple2.get(qEscript.rxNumber)).thenReturn("rxn2");
        when(tuple1.get(qOrderHeader.tradingPartnerNum)).thenReturn(1L);

        return tuples;
    }
}
