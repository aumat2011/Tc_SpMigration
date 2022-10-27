package com.humana.pharmacy.common.service.impl;

import com.humana.pharmacy.common.TestsHelper;
import com.humana.pharmacy.common.cache.model.CRxDefaults;
import com.humana.pharmacy.common.dto.AnsRxNarcCodeEScriptDTO;
import com.humana.pharmacy.common.dto.RsMultiLinkDTO;
import com.humana.pharmacy.common.dto.RsRxScriptDTO;
import com.humana.pharmacy.common.dto.RxEScriptDTO;
import com.humana.pharmacy.common.entity.QPatientPlans;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.Union;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit test cases for FunctionsServiceImpl.
 */
@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FunctionsServiceImplTest {
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
     * The Union instance.
     */
    @Mock
    private Union union;

    /**
     * The FunctionsServiceImpl instance.
     */
    private FunctionsServiceImpl functionsService;

    /**
     * The escript ids.
     */
    private final List<BigInteger> escriptIds =
            new ArrayList<>(Arrays.asList(BigInteger.valueOf(1), BigInteger.valueOf(2)));

    /**
     * The RX defaults.
     */
    private CRxDefaults rxDefaults;

    /**
     * The Rx number.
     */
    private String rxNumber = "rxn1";

    /**
     * The script ID.
     */
    private BigInteger scriptId = BigInteger.valueOf(1);

    /**
     * The RxEScriptDTO list.
     */
    private List<RxEScriptDTO> rxEScriptDTOs;

    /**
     * The AnsRxNarcCodeEScriptDTO list.
     */
    private List<AnsRxNarcCodeEScriptDTO> ansRxNarcCodeEScriptDTO;

    /**
     * The order narcotic code.
     */
    private AnsRxNarcCodeEScriptDTO orderNarcCode;

    /**
     * The patient num.
     */
    private BigInteger patientNum = BigInteger.valueOf(1);

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
        functionsService = new FunctionsServiceImpl(epostrxDataSource, workflowDataSource);

        TestsHelper.setFieldValue(functionsService, "epostrxQueryFactory", epostrxQueryFactory);
        TestsHelper.setFieldValue(functionsService, "workflowQueryFactory", workflowQueryFactory);

        rxDefaults = getRxDefaults();

        rxEScriptDTOs = getRxEScriptDTOs();
        ansRxNarcCodeEScriptDTO = getAnsRxNarcCodeEScriptDTOs();
        orderNarcCode = getAnsRxNarcCodeEScriptDTOs().get(0);
    }

    private void setUpMock() {
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
    }

    @Test
    public void test_ctor_epostrxDataSourceNull() {
        assertThrows(RuntimeException.class, () -> new FunctionsServiceImpl(null, workflowDataSource));
    }

    @Test
    public void test_ctor_workflowDataSourceNull() {
        assertThrows(RuntimeException.class, () -> new FunctionsServiceImpl(epostrxDataSource, null));
    }

    @Test
    public void test_getPrecheckRequired_escriptIdsNull() {
        assertThrows(RuntimeException.class, () -> functionsService.getPrecheckRequired(null));
    }

    @Test
    public void test_getPrecheckRequired_escriptIdsEmpty() {
        escriptIds.clear();

        assertThrows(RuntimeException.class, () -> functionsService.getPrecheckRequired(escriptIds));
    }

    @Test
    public void test_getPrecheckRequired_1() {
        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetchFirst()).thenReturn(rxDefaults);

        List<BigInteger> res = functionsService.getPrecheckRequired(escriptIds);
        assertEquals(0, res.size());
    }

    @Test
    public void test_getPrecheckRequired_2() {
        rxDefaults.setPrecheckAll("Y");
        rxDefaults.setPwoPrecheck("Y");
        rxDefaults.setOtcAsRxPrecheck("Y");

        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetchFirst()).thenReturn(rxDefaults);
        when(query.fetch()).thenReturn(new ArrayList(Arrays.asList(BigInteger.valueOf(1))));

        List<BigInteger> res = functionsService.getPrecheckRequired(escriptIds);
        assertEquals(1, res.size());
    }

    @Test
    public void test_getFirstTimeFill_escriptIdsNull() {
        assertThrows(RuntimeException.class, () -> functionsService.getFirstTimeFill(null));
    }

    @Test
    public void test_getFirstTimeFill_escriptIdsEmpty() {
        escriptIds.clear();

        assertThrows(RuntimeException.class, () -> functionsService.getFirstTimeFill(escriptIds));
    }

    @Test
    public void test_getFirstTimeFill_1() {
        setUpMock();
        when(query.join(any(EntityPath.class))).thenReturn(query);

        List<BigInteger> res = functionsService.getFirstTimeFill(escriptIds);
        assertEquals(0, res.size());
    }

    @Test
    public void test_getFirstTimeFill_2() {
        setUpMock();
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(new ArrayList(Arrays.asList(BigInteger.valueOf(1))));

        List<BigInteger> res = functionsService.getFirstTimeFill(escriptIds);
        assertEquals(1, res.size());
    }

    @Test
    public void test_getExceedMaxPVDays_escriptIdsNull() {
        assertThrows(RuntimeException.class, () -> functionsService.getExceedMaxPVDays(null));
    }

    @Test
    public void test_getExceedMaxPVDays_escriptIdsEmpty() {
        escriptIds.clear();

        assertThrows(RuntimeException.class, () -> functionsService.getExceedMaxPVDays(escriptIds));
    }

    @Test
    public void test_getExceedMaxPVDays_1() {
        setUpMock();
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.fetchFirst()).thenReturn(rxDefaults);

        List<BigInteger> res = functionsService.getExceedMaxPVDays(escriptIds);
        assertEquals(0, res.size());
    }

    @Test
    public void test_getExceedMaxPVDays_2() {
        rxDefaults.setMaxPvDays(null);

        setUpMock();
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.fetchFirst()).thenReturn(rxDefaults);
        when(query.fetch()).thenReturn(new ArrayList(Arrays.asList(BigInteger.valueOf(1))));

        List<BigInteger> res = functionsService.getExceedMaxPVDays(escriptIds);
        assertEquals(1, res.size());
    }

    @Test
    public void test_getPrimaryEcsPaid_escriptIdsNull() {
        assertThrows(RuntimeException.class, () -> functionsService.getPrimaryEcsPaid(null));
    }

    @Test
    public void test_getPrimaryEcsPaid_escriptIdsEmpty() {
        escriptIds.clear();

        assertThrows(RuntimeException.class, () -> functionsService.getPrimaryEcsPaid(escriptIds));
    }

    @Test
    public void test_getPrimaryEcsPaid_1() {
        when(epostrxQueryFactory.query()).thenReturn(query);
        when(query.union(any(List.class))).thenReturn(union);
        when(union.fetch()).thenReturn(new ArrayList());

        List<BigInteger> res = functionsService.getPrimaryEcsPaid(escriptIds);
        assertEquals(0, res.size());
    }

    @Test
    public void test_getPrimaryEcsPaid_2() {
        when(epostrxQueryFactory.query()).thenReturn(query);
        when(query.union(any(List.class))).thenReturn(union);
        when(union.fetch()).thenReturn(new ArrayList(Arrays.asList(BigInteger.valueOf(1))));

        List<BigInteger> res = functionsService.getPrimaryEcsPaid(escriptIds);
        assertEquals(1, res.size());
    }

    @Test
    public void test_getRemainingRefills_rxNumberNull() {
        assertThrows(
                RuntimeException.class, () -> functionsService.getRemainingRefills(null, rxEScriptDTOs));
    }

    @Test
    public void test_getRemainingRefills_rxNumberEmpty() {
        assertThrows(
                RuntimeException.class, () -> functionsService.getRemainingRefills(" ", rxEScriptDTOs));
    }

    @Test
    public void test_getRemainingRefills_No1() {
        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        rxNumber = "NONE";
        Integer res = functionsService.getRemainingRefills(rxNumber, null);
        assertNull(res);
    }

    @Test
    public void test_getRemainingRefills_No2() {
        rxEScriptDTOs.clear();

        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        rxNumber = "NONE";
        Integer res = functionsService.getRemainingRefills(rxNumber, null);
        assertNull(res);
    }

    @Test
    public void test_getRemainingRefills_No3() {
        rxEScriptDTOs.clear();

        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        rxNumber = "NONE";
        Integer res = functionsService.getRemainingRefills(rxNumber, new ArrayList<>());
        assertNull(res);
    }

    @Test
    public void test_getRemainingRefills_ControlledFillsExceeded_True() {
        rxEScriptDTOs.get(0).setProdDea((byte) 1);
        rxEScriptDTOs.get(0).setOriginalNumRefills(0L);

        Integer res = functionsService.getRemainingRefills(rxNumber, rxEScriptDTOs);
        assertEquals(0, res);
    }

    @Test
    public void test_getRemainingRefills_ControlledFillsExceeded_False1() {
        rxEScriptDTOs.get(0).setProdDea(null);
        rxEScriptDTOs.get(0).setOriginalNumRefills(0L);

        Integer res = functionsService.getRemainingRefills(rxNumber, rxEScriptDTOs);
        assertEquals(3, res);
    }

    @Test
    public void test_getRemainingRefills_ControlledFillsExceeded_False2() {
        rxEScriptDTOs.get(0).setProdDea((byte) -1);
        rxEScriptDTOs.get(0).setOriginalNumRefills(0L);

        Integer res = functionsService.getRemainingRefills(rxNumber, rxEScriptDTOs);
        assertEquals(3, res);
    }

    @Test
    public void test_getRemainingRefills_ControlledFillsExceeded_False3() {
        rxEScriptDTOs.get(0).setProdDea((byte) 1);
        rxEScriptDTOs.get(0).setOriginalNumRefills(null);

        Integer res = functionsService.getRemainingRefills(rxNumber, rxEScriptDTOs);
        assertEquals(3, res);
    }

    @Test
    public void test_getRemainingRefills_ControlledFillsExceeded_False4() {
        rxEScriptDTOs.get(0).setProdDea((byte) 1);
        rxEScriptDTOs.get(0).setOriginalNumRefills(999L);

        Integer res = functionsService.getRemainingRefills(rxNumber, rxEScriptDTOs);
        assertEquals(3, res);
    }

    @Test
    public void test_getRemainingRefills_1() {
        rxEScriptDTOs.get(0).setFillStatusNum(null);

        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        Integer res = functionsService.getRemainingRefills(rxNumber, rxEScriptDTOs);
        assertEquals(1, res);
    }

    @Test
    public void test_getRemainingRefills_2() {
        rxEScriptDTOs.get(0).setFillStatusNum(null);

        List<RxEScriptDTO> list = getRxEScriptDTOs();
        list.get(0).setFillQtyDispensed(0l);
        list.get(1).setFillQtyDispensed(0l);

        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(list);

        Integer res = functionsService.getRemainingRefills(rxNumber, list);
        assertNull(res);
    }

    @Test
    public void test_getRemainingRefills_3() {
        rxEScriptDTOs.get(0).setDispensedQuantity(2L);
        rxEScriptDTOs.get(0).setFillStatusNum(null);

        List<RxEScriptDTO> list = getRxEScriptDTOs();
        list.get(0).setFillQtyDispensed(0L);
        list.get(1).setFillQtyDispensed(0L);

        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(list);

        Integer res = functionsService.getRemainingRefills(rxNumber, list);
        assertNull(res);
    }

    @Test
    public void test_getRemainingRefills_4() {
        rxEScriptDTOs.get(0).setDispensedQuantity(-1L);
        rxEScriptDTOs.get(0).setWrittenQuantity(-1L);
        rxEScriptDTOs.get(0).setFillStatusNum(null);

        List<RxEScriptDTO> list = getRxEScriptDTOs();
        list.get(0).setFillQtyDispensed(0L);
        list.get(1).setFillQtyDispensed(0L);

        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(list);

        Integer res = functionsService.getRemainingRefills(rxNumber, list);
        assertNull(res);
    }

    @Test
    public void test_getRemainingRefills_5() {
        rxEScriptDTOs.get(0).setFillQtyDispensed(0L);
        rxEScriptDTOs.get(0).setDispensedQuantity(2L);
        rxEScriptDTOs.get(0).setOrtfQty(null);

        Integer res = functionsService.getRemainingRefills(rxNumber, rxEScriptDTOs);
        assertEquals(150, res);
    }

    @Test
    public void test_getRxTotalQty_scriptIdNull() {
        assertThrows(RuntimeException.class, () -> functionsService.getRxTotalQty(null, rxEScriptDTOs));
    }

    @Test
    public void test_getRxTotalQty_No() {
        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        scriptId = BigInteger.valueOf(-1);
        long res = functionsService.getRxTotalQty(scriptId, null);
        assertEquals(0, res);
    }

    @Test
    public void test_getRxTotalQty_1() {
        long res = functionsService.getRxTotalQty(scriptId, rxEScriptDTOs);
        assertEquals(301, res);
    }

    @Test
    public void test_getRxTotalQty_2() {
        rxEScriptDTOs.get(0).setWrittenQuantity(null);

        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        long res = functionsService.getRxTotalQty(scriptId, null);
        assertEquals(0, res);
    }

    @Test
    public void test_getRxTotalQty_3() {
        rxEScriptDTOs.get(0).setNumRefills(1L);

        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        long res = functionsService.getRxTotalQty(scriptId, new ArrayList<>());
        assertEquals(602, res);
    }

    @Test
    public void test_getRxTotalQty_4() {
        rxEScriptDTOs.get(1).setEScriptMsgAttributeSeq(scriptId);

        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        long res = functionsService.getRxTotalQty(scriptId, null);
        assertEquals(301, res);
    }

    @Test
    public void test_getRxTotalQtyFilled_rxNumberNull() {
        assertThrows(
                RuntimeException.class, () -> functionsService.getRxTotalQtyFilled(null, rxEScriptDTOs));
    }

    @Test
    public void test_getRxTotalQtyFilled_rxNumberEmpty() {
        assertThrows(
                RuntimeException.class, () -> functionsService.getRxTotalQtyFilled(" ", rxEScriptDTOs));
    }

    @Test
    public void test_getRxTotalQtyFilled_No() {
        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        rxNumber = "NONE";
        long res = functionsService.getRxTotalQtyFilled(rxNumber, null);
        assertEquals(0, res);
    }

    @Test
    public void test_getRxTotalQtyFilled_1() {
        rxEScriptDTOs.get(0).setOrtfQty(null);

        long res = functionsService.getRxTotalQtyFilled(rxNumber, rxEScriptDTOs);
        assertEquals(100, res);
    }

    @Test
    public void test_getRxTotalQtyFilled_2() {
        rxEScriptDTOs.get(0).setOrtfQty(null);

        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        long res = functionsService.getRxTotalQtyFilled(rxNumber, null);
        assertEquals(100, res);
    }

    @Test
    public void test_getRxTotalQtyFilled_3() {
        rxEScriptDTOs.get(0).setOrtfQty(null);

        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        long res = functionsService.getRxTotalQtyFilled(rxNumber, new ArrayList<>());
        assertEquals(100, res);
    }

    @Test
    public void test_getRxTotalQtyFilled_4() {
        rxEScriptDTOs.get(0).setOrtfQty(null);

        rxEScriptDTOs.get(1).setRxNumber(rxNumber);
        rxEScriptDTOs.get(1).setOrtfQty(0L);
        rxEScriptDTOs.get(1).setFillStatusNum((byte) 2);

        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        long res = functionsService.getRxTotalQtyFilled(rxNumber, null);
        assertEquals(300, res);
    }

    @Test
    public void test_getRxTotalQtyFilled_5() {
        rxEScriptDTOs.get(0).setFillQtyDispensed(null);
        rxEScriptDTOs.get(1).setRxNumber(rxNumber);
        rxEScriptDTOs.get(1).setOrtfQty(1L);

        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        long res = functionsService.getRxTotalQtyFilled(rxNumber, null);
        assertEquals(0, res);
    }

    @Test
    public void test_getRxTotalORTFQtyFilled_rxNumberNull() {
        assertThrows(
                RuntimeException.class,
                () -> functionsService.getRxTotalORTFQtyFilled(null, rxEScriptDTOs));
    }

    @Test
    public void test_getRxTotalORTFQtyFilled_rxNumberEmpty() {
        assertThrows(
                RuntimeException.class, () -> functionsService.getRxTotalORTFQtyFilled(" ", rxEScriptDTOs));
    }

    @Test
    public void test_getRxTotalORTFQtyFilled_No() {
        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        rxNumber = "NONE";
        long res = functionsService.getRxTotalORTFQtyFilled(rxNumber, null);
        assertEquals(0, res);
    }

    @Test
    public void test_getRxTotalORTFQtyFilled_1() {
        long res = functionsService.getRxTotalORTFQtyFilled(rxNumber, rxEScriptDTOs);
        assertEquals(1, res);
    }

    @Test
    public void test_getRxTotalORTFQtyFilled_2() {
        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        long res = functionsService.getRxTotalORTFQtyFilled(rxNumber, null);
        assertEquals(1, res);
    }

    @Test
    public void test_getRxTotalORTFQtyFilled_3() {
        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        long res = functionsService.getRxTotalORTFQtyFilled(rxNumber, new ArrayList<>());
        assertEquals(1, res);
    }

    @Test
    public void test_getRxTotalORTFQtyFilled_4() {
        rxEScriptDTOs.get(1).setRxNumber(rxNumber);
        rxEScriptDTOs.get(1).setFillStatusNum((byte) 2);

        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        long res = functionsService.getRxTotalORTFQtyFilled(rxNumber, null);
        assertEquals(3, res);
    }

    @Test
    public void test_getRxTotalORTFQtyFilled_5() {
        rxEScriptDTOs.get(0).setOrtfQty(null);
        rxEScriptDTOs.get(1).setRxNumber(rxNumber);
        rxEScriptDTOs.get(1).setOrtfQty(0L);

        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        long res = functionsService.getRxTotalORTFQtyFilled(rxNumber, null);
        assertEquals(0, res);
    }

    @Test
    public void test_getRxTotalFills_scriptIdNull() {
        assertThrows(
                RuntimeException.class, () -> functionsService.getRxTotalFills(null, rxEScriptDTOs));
    }

    @Test
    public void test_getRxTotalFills_No() {
        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        scriptId = BigInteger.valueOf(-1);
        long res = functionsService.getRxTotalFills(scriptId, null);
        assertEquals(1, res);
    }

    @Test
    public void test_getRxTotalFills_1() {
        long res = functionsService.getRxTotalFills(scriptId, rxEScriptDTOs);
        assertEquals(1, res);
    }

    @Test
    public void test_getRxTotalFills_2() {
        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        long res = functionsService.getRxTotalFills(scriptId, null);
        assertEquals(1, res);
    }

    @Test
    public void test_getRxTotalFills_3() {
        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        long res = functionsService.getRxTotalFills(scriptId, new ArrayList<>());
        assertEquals(1, res);
    }

    @Test
    public void test_getRxTotalFills_4() {
        rxEScriptDTOs.get(1).setEScriptMsgAttributeSeq(scriptId);
        rxEScriptDTOs.get(1).setFillStatusNum((byte) 2);

        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        long res = functionsService.getRxTotalFills(scriptId, null);
        assertEquals(1, res);
    }

    @Test
    public void test_getClaimPaidNonCashNonSelfAdjudicating_scriptIdNull() {
        assertThrows(
                RuntimeException.class,
                () -> functionsService.getClaimPaidNonCashNonSelfAdjudicating(null));
    }

    @Test
    public void test_getClaimPaidNonCashNonSelfAdjudicating_Paid() {
        setUpMock();
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.fetchCount()).thenReturn(1L);

        Integer res = functionsService.getClaimPaidNonCashNonSelfAdjudicating(scriptId);
        assertEquals(1, res);
    }

    @Test
    public void test_getClaimPaidNonCashNonSelfAdjudicating_NotPaid() {
        setUpMock();
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.fetchCount()).thenReturn(0L);

        Integer res = functionsService.getClaimPaidNonCashNonSelfAdjudicating(scriptId);
        assertEquals(0, res);
    }

    @Test
    public void test_getGPIClass_escriptsNull() {
        String res = functionsService.getGPIClass(null);

        assertEquals("", res);
    }

    @Test
    public void test_getGPIClass_escriptsEmpty() {
        ansRxNarcCodeEScriptDTO.clear();
        String res = functionsService.getGPIClass(ansRxNarcCodeEScriptDTO);

        assertEquals("", res);
    }

    @Test
    public void test_getGPIClass_1() {
        String res = functionsService.getGPIClass(ansRxNarcCodeEScriptDTO);

        assertEquals("PGRN1", res);
    }

    @Test
    public void test_getGPIClass_2() {
        ansRxNarcCodeEScriptDTO.get(0).setProdGenericRefNum(null);

        String res = functionsService.getGPIClass(ansRxNarcCodeEScriptDTO);

        assertEquals("PGRN212345", res);
    }

    @Test
    public void test_getGPIClass_3() {
        ansRxNarcCodeEScriptDTO.get(0).setProdGenericRefNum(null);
        ansRxNarcCodeEScriptDTO.get(1).setProdGenericRefNum(null);

        String res = functionsService.getGPIClass(ansRxNarcCodeEScriptDTO);

        assertEquals("", res);
    }

    @Test
    public void test_getOrderNarcCode_escriptsNull() {
        AnsRxNarcCodeEScriptDTO res = functionsService.getOrderNarcCode(null);

        assertNull(res);
    }

    @Test
    public void test_getOrderNarcCode_escriptsEmpty() {
        ansRxNarcCodeEScriptDTO.clear();
        AnsRxNarcCodeEScriptDTO res = functionsService.getOrderNarcCode(ansRxNarcCodeEScriptDTO);

        assertNull(res);
    }

    @Test
    public void test_getOrderNarcCode_1() {
        AnsRxNarcCodeEScriptDTO res = functionsService.getOrderNarcCode(ansRxNarcCodeEScriptDTO);

        assertEquals(BigInteger.valueOf(1), res.getEScriptMsgAttributeSeq());
    }

    @Test
    public void test_getOrderNarcCode_2() {
        ansRxNarcCodeEScriptDTO.get(0).setOrderNum(null);

        AnsRxNarcCodeEScriptDTO res = functionsService.getOrderNarcCode(ansRxNarcCodeEScriptDTO);

        assertEquals(BigInteger.valueOf(2), res.getEScriptMsgAttributeSeq());
    }

    @Test
    public void test_getOrderNarcCode_3() {
        ansRxNarcCodeEScriptDTO.get(0).setNarcoticCode(null);

        AnsRxNarcCodeEScriptDTO res = functionsService.getOrderNarcCode(ansRxNarcCodeEScriptDTO);

        assertEquals(BigInteger.valueOf(2), res.getEScriptMsgAttributeSeq());
    }

    @Test
    public void test_getOrderNarcCode_4() {
        ansRxNarcCodeEScriptDTO.get(0).setStateNum(null);

        AnsRxNarcCodeEScriptDTO res = functionsService.getOrderNarcCode(ansRxNarcCodeEScriptDTO);

        assertEquals(BigInteger.valueOf(2), res.getEScriptMsgAttributeSeq());
    }

    @Test
    public void test_getOrderNarcCode_5() {
        ansRxNarcCodeEScriptDTO.get(0).setStateNum(null);
        ansRxNarcCodeEScriptDTO.get(1).setStateNum(null);

        AnsRxNarcCodeEScriptDTO res = functionsService.getOrderNarcCode(ansRxNarcCodeEScriptDTO);

        assertNull(res);
    }

    @Test
    public void test_getDeaProduct_orderNarcCodeNull() {
        Byte res = functionsService.getDeaProduct(null, ansRxNarcCodeEScriptDTO);

        assertEquals((byte) 0, res);
    }

    @Test
    public void test_getDeaProduct_escriptsNull() {
        Byte res = functionsService.getDeaProduct(orderNarcCode, null);

        assertEquals((byte) 0, res);
    }

    @Test
    public void test_getDeaProduct_escriptsEmpty() {
        ansRxNarcCodeEScriptDTO.clear();
        Byte res = functionsService.getDeaProduct(orderNarcCode, ansRxNarcCodeEScriptDTO);

        assertEquals((byte) 0, res);
    }

    @Test
    public void test_getDeaProduct_1() {
        Byte res = functionsService.getDeaProduct(orderNarcCode, ansRxNarcCodeEScriptDTO);

        assertEquals((byte) 1, res);
    }

    @Test
    public void test_getDeaProduct_2() {
        ansRxNarcCodeEScriptDTO.get(1).setOrderInvoiceNumber(orderNarcCode.getOrderInvoiceNumber());
        ansRxNarcCodeEScriptDTO.get(1).setStateNum(orderNarcCode.getStateNum());

        ansRxNarcCodeEScriptDTO.get(0).setOrderInvoiceNumber(null);

        Byte res = functionsService.getDeaProduct(orderNarcCode, ansRxNarcCodeEScriptDTO);

        assertEquals((byte) 2, res);
    }

    @Test
    public void test_getDeaProduct_3() {
        ansRxNarcCodeEScriptDTO.get(1).setOrderInvoiceNumber(orderNarcCode.getOrderInvoiceNumber());
        ansRxNarcCodeEScriptDTO.get(1).setStateNum(orderNarcCode.getStateNum());

        ansRxNarcCodeEScriptDTO.get(0).setStateCsProductSeq(null);

        Byte res = functionsService.getDeaProduct(orderNarcCode, ansRxNarcCodeEScriptDTO);

        assertEquals((byte) 2, res);
    }

    @Test
    public void test_getDeaProduct_4() {
        ansRxNarcCodeEScriptDTO.get(1).setOrderInvoiceNumber(orderNarcCode.getOrderInvoiceNumber());
        ansRxNarcCodeEScriptDTO.get(1).setStateNum(orderNarcCode.getStateNum());

        ansRxNarcCodeEScriptDTO.get(0).setStateNum(999);

        Byte res = functionsService.getDeaProduct(orderNarcCode, ansRxNarcCodeEScriptDTO);

        assertEquals((byte) 2, res);
    }

    @Test
    public void test_getDeaProduct_5() {
        ansRxNarcCodeEScriptDTO.get(0).setStateNum(999);
        ansRxNarcCodeEScriptDTO.get(1).setStateNum(9999);

        Byte res = functionsService.getDeaProduct(orderNarcCode, ansRxNarcCodeEScriptDTO);

        assertEquals((byte) 0, res);
    }

    @Test
    public void test_getNumericsFromString_1() {
        String res = functionsService.getNumericsFromString(null);

        assertNull(res);
    }

    @Test
    public void test_getNumericsFromString_2() {
        String res = functionsService.getNumericsFromString(" ");

        assertEquals("", res);
    }

    @Test
    public void test_getNumericsFromString_3() {
        String res = functionsService.getNumericsFromString("a2B5_3 ");

        assertEquals("253", res);
    }

    @Test
    public void test_isOnlyActiveCoverageCash_1() {
        Boolean res = functionsService.isOnlyActiveCoverageCash(null);

        assertFalse(res);
    }

    @Test
    public void test_isOnlyActiveCoverageCash_2() {
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);

        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class)))
                .thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);

        Boolean res = functionsService.isOnlyActiveCoverageCash(patientNum);

        assertFalse(res);
    }

    @Test
    public void test_isOnlyActiveCoverageCash_3() {
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);

        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class)))
                .thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(tuple1.get(QPatientPlans.patientPlans.ppNum)).thenReturn(1L);
        when(tuple1.get(QPatientPlans.patientPlans.coverageTypeId)).thenReturn((byte) 1);
        when(query.fetch()).thenReturn(Arrays.asList(tuple1));

        Boolean res = functionsService.isOnlyActiveCoverageCash(patientNum);

        assertFalse(res);
    }

    @Test
    public void test_isOnlyActiveCoverageCash_4() {
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);

        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class)))
                .thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(tuple2.get(QPatientPlans.patientPlans.ppNum)).thenReturn(2L);
        when(tuple2.get(QPatientPlans.patientPlans.coverageTypeId)).thenReturn((byte) 1);
        when(query.fetch()).thenReturn(Arrays.asList(tuple2));

        Boolean res = functionsService.isOnlyActiveCoverageCash(patientNum);

        assertTrue(res);
    }

    @Test
    public void test_isOnlyActiveCoverageCash_5() {
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);

        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class)))
                .thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(tuple1.get(QPatientPlans.patientPlans.ppNum)).thenReturn(2L);
        when(tuple1.get(QPatientPlans.patientPlans.coverageTypeId)).thenReturn((byte) 1);
        when(tuple2.get(QPatientPlans.patientPlans.ppNum)).thenReturn(1L);
        when(tuple2.get(QPatientPlans.patientPlans.coverageTypeId)).thenReturn((byte) 2);
        when(query.fetch()).thenReturn(Arrays.asList(tuple1, tuple2));

        Boolean res = functionsService.isOnlyActiveCoverageCash(patientNum);

        assertTrue(res);
    }

    @Test
    public void test_getRsRxLinkingParentChild_1() {
        setUpMock();
        when(query.distinct()).thenReturn(query);
        when(query.join(any())).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.fetch()).thenReturn(getRsMultiLinkDTOs());

        List<RsMultiLinkDTO> res = functionsService.getRsRxLinkingParentChild("8001", "Y");

        assertEquals(5, res.size());
    }

    @Test
    public void test_getRsRxLinkingParentChild_2() {
        setUpMock();
        when(query.distinct()).thenReturn(query);
        when(query.join(any())).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.fetch()).thenReturn(getRsMultiLinkDTOs());

        List<RsMultiLinkDTO> res = functionsService.getRsRxLinkingParentChild("8001", "ALL");

        assertEquals(5, res.size());
    }

    @Test
    public void test_getRxQtyRemaining_1() {
        List<RsRxScriptDTO> dtoList = getRsRxScriptDTO();
        Long res = functionsService.getRxQtyRemaining("rxn1", dtoList);
        assertEquals(200L, res);
    }

    @Test
    public void test_getRxQtyRemaining_2() {
        List<RsRxScriptDTO> dtoList = getRsRxScriptDTO();
        dtoList.get(1).setRxQtyLeft(null);
        dtoList.get(0).setOrtfQty(-100L);
        dtoList.get(0).setFillQtyDispensed(null);

        Long res = functionsService.getRxQtyRemaining("rxn1", dtoList);

        assertEquals(0L, res);
    }

    @Test
    public void test_getRxQtyRemaining_3() {
        List<RsRxScriptDTO> dtoList = getRsRxScriptDTO();
        dtoList.get(1).setRxQtyLeft(new BigInteger("300"));
        dtoList.get(0).setOrtfQty(-100L);
        dtoList.get(0).setFillQtyDispensed(-1L);
        Long res = functionsService.getRxQtyRemaining("rxn1", dtoList);

        assertEquals(201L, res);
    }

    @Test
    public void test_getRxQtyRemaining_4() {
        List<RsRxScriptDTO> dtoList = getRsRxScriptDTO();
        dtoList.get(1).setRxQtyLeft(new BigInteger("300"));
        dtoList.get(0).setOrtfQty(0L);
        dtoList.get(0).setFillQtyDispensed(null);
        Long res = functionsService.getRxQtyRemaining("rxn1", dtoList);

        assertEquals(201L, res);
    }

    @Test
    public void test_getRxQtyRemaining_5() {
        List<RsRxScriptDTO> dtoList = getRsRxScriptDTO();
        dtoList.get(0).setFillStatusNum((byte) 3);
        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(dtoList);
        Long res = functionsService.getRxQtyRemaining("rxn1", null);

        assertEquals(201L, res);
    }

    @Test
    public void test_getRxQtyRemaining_6() {
        setUpMock();
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(new ArrayList());
        Long res = functionsService.getRxQtyRemaining("rxn1", null);
        assertNull(res);
    }

    /**
     * Get RsRxScriptDTO list.
     *
     * @return the RsRxScriptDTO list.
     */
    private List<RsRxScriptDTO> getRsRxScriptDTO() {
        List<RsRxScriptDTO> list = new ArrayList<>();

        RsRxScriptDTO dto1 = new RsRxScriptDTO();
        dto1.setEScriptMsgAttributeSeq(BigInteger.valueOf(1));
        dto1.setRxNumber("rxn1");
        dto1.setFillQtyDispensed(100L);
        dto1.setWrittenQuantity(301L);
        dto1.setRxQtyLeft(new BigInteger("41"));
        dto1.setFillStatusNum((byte) 2);
        dto1.setOrtfQty(100L);
        list.add(dto1);
        RsRxScriptDTO dto2 = new RsRxScriptDTO();
        dto2.setEScriptMsgAttributeSeq(BigInteger.valueOf(2));
        dto2.setRxNumber("rxn2");
        dto2.setRxQtyLeft(new BigInteger("42"));
        dto2.setFillQtyDispensed(200L);
        dto2.setWrittenQuantity(201L);
        dto2.setOrtfQty(200L);
        list.add(dto2);

        return list;
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
        dto1.setWrittenQuantity(301L);
        dto1.setFillStatusNum((byte) 2);
        dto1.setOrtfQty(100L);
        list.add(dto1);
        RxEScriptDTO dto2 = new RxEScriptDTO();
        dto2.setEScriptMsgAttributeSeq(BigInteger.valueOf(2));
        dto2.setRxNumber("rxn2");
        dto2.setFillQtyDispensed(200L);
        dto2.setWrittenQuantity(201L);
        dto2.setOrtfQty(200L);
        list.add(dto2);

        return list;
    }

    /**
     * Get AnsRxNarcCodeEScriptDTO list.
     *
     * @return the AnsRxNarcCodeEScriptDTO list.
     */
    private List<AnsRxNarcCodeEScriptDTO> getAnsRxNarcCodeEScriptDTOs() {
        List<AnsRxNarcCodeEScriptDTO> list = new ArrayList<>();

        AnsRxNarcCodeEScriptDTO dto1 = new AnsRxNarcCodeEScriptDTO();
        dto1.setEScriptMsgAttributeSeq(BigInteger.valueOf(1));
        dto1.setRxNumber("rxn1");
        dto1.setProdGenericRefNum("PGRN1");
        dto1.setOrderNum(BigInteger.valueOf(1));
        dto1.setNarcoticCode("NC1");
        dto1.setStateNum(1);
        dto1.setOrderInvoiceNumber("OIN1");
        dto1.setStateCsProductSeq(1L);
        dto1.setScpProdDea((short) 1);
        list.add(dto1);

        AnsRxNarcCodeEScriptDTO dto2 = new AnsRxNarcCodeEScriptDTO();
        dto2.setEScriptMsgAttributeSeq(BigInteger.valueOf(2));
        dto2.setRxNumber("rxn2");
        dto2.setProdGenericRefNum("PGRN2123456789");
        dto2.setOrderNum(BigInteger.valueOf(2));
        dto2.setNarcoticCode("NC2");
        dto2.setStateNum(2);
        dto2.setOrderInvoiceNumber("OIN2");
        dto2.setStateCsProductSeq(2L);
        dto2.setScpProdDea((short) 2);
        list.add(dto2);

        return list;
    }

    private static CRxDefaults getRxDefaults() {
        return new CRxDefaults(
                (short) 1, "otcAsRxPrecheck1", "precheckAll1", "pwoPrecheck1", "checkNarcoticRefills1");
    }
}
