package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.TestsHelper;
import com.humana.pharmacy.dto.RxSanityResult;
import com.humana.pharmacy.common.dto.RxEScriptDTO;
import com.humana.pharmacy.common.service.impl.FunctionsServiceImpl;
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
 * Unit test cases for RxSanityServiceImpl;
 */
@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RxSanityServiceImplTest {
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
     * The FunctionsServiceImpl instance.
     */
    private FunctionsServiceImpl functionsService;

    /**
     * The RxSanityServiceImpl instance.
     */
    private RxSanityServiceImpl rxSanityService;

    /**
     * The script ID.
     */
    private BigInteger scriptId = BigInteger.valueOf(1);

    /**
     * The shipping state.
     */
    private String shippingState = "OK";

    /**
     * The Rx number.
     */
    private String rxNumber = "rxn1";

    /**
     * The null value for QOKHydrocodoneSanityProducts.
     */
    private final String okNullGpi = "XX";
    /**
     * The tuple.
     */
    @Mock
    private Tuple tuple;

    /**
     * The RxEScriptDTO list.
     */
    private List<RxEScriptDTO> rxEScriptDTOs;

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
        rxSanityService = new RxSanityServiceImpl(epostrxDataSource, workflowDataSource);
        rxSanityService.setFunctionsService(functionsService);

        TestsHelper.setFieldValue(rxSanityService, "epostrxQueryFactory", epostrxQueryFactory);
        TestsHelper.setFieldValue(rxSanityService, "workflowQueryFactory", workflowQueryFactory);

        rxEScriptDTOs = getRxEScriptDTOs();
    }

    private void setUpMock() {
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);

        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);

        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class))).thenReturn(query);
    }

    @Test
    public void test_ctor_epostrxDataSourceNull() {
        assertThrows(RuntimeException.class, () -> new RxSanityServiceImpl(null, workflowDataSource));
    }

    @Test
    public void test_ctor_workflowDataSourceNull() {
        assertThrows(RuntimeException.class, () -> new RxSanityServiceImpl(epostrxDataSource, null));
    }

    @Test
    public void test_checkRxSanity_scriptIdNull() {
        assertThrows(RuntimeException.class, () -> rxSanityService.checkRxSanity(null, null));
    }

    @Test
    public void test_checkRxSanity_functionsServiceNull() {
        rxSanityService = new RxSanityServiceImpl(epostrxDataSource, workflowDataSource);
        assertThrows(RuntimeException.class, () -> rxSanityService.checkRxSanity(null, null));
    }

    @Test
    public void test_checkRxSanity_NoError1() {
        setUpMock();
        when(query.fetchFirst()).thenReturn(null);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class), any(Expression.class))).thenReturn(query);

        scriptId = BigInteger.valueOf(999);
        RxSanityResult res = rxSanityService.checkRxSanity(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkRxSanity_NoError2() {
        setUpMock();

        rxNumber = null;
        when(tuple.get(0, String.class)).thenReturn(shippingState);
        when(tuple.get(1, String.class)).thenReturn(rxNumber);
        when(tuple.get(2, String.class)).thenReturn(okNullGpi);
        when(query.fetchFirst()).thenReturn(tuple);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class), any(Expression.class))).thenReturn(query);

        RxSanityResult res = rxSanityService.checkRxSanity(scriptId, rxNumber);
        assertNull(res);
    }

    @Test
    public void test_checkRxSanity_NoError3() {
        setUpMock();

        rxNumber = " ";
        when(tuple.get(0, String.class)).thenReturn(shippingState);
        when(tuple.get(1, String.class)).thenReturn(rxNumber);
        when(tuple.get(2, String.class)).thenReturn(okNullGpi);
        when(query.fetchFirst()).thenReturn(tuple);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class), any(Expression.class))).thenReturn(query);

        RxSanityResult res = rxSanityService.checkRxSanity(scriptId, rxNumber);
        assertNull(res);
    }

    @Test
    public void test_checkRxSanity_NoError4() {
        setUpMock();

        shippingState = "None OK";
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(tuple.get(0, String.class)).thenReturn(shippingState);
        when(tuple.get(1, String.class)).thenReturn(rxNumber);
        when(tuple.get(2, String.class)).thenReturn(okNullGpi);
        when(query.fetchFirst()).thenReturn(tuple);
        when(query.fetch()).thenReturn(rxEScriptDTOs);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class), any(Expression.class))).thenReturn(query);

        RxSanityResult res = rxSanityService.checkRxSanity(scriptId, rxNumber);
        assertNull(res);
    }

    @Test
    public void test_checkRxSanity_NoError5() {
        setUpMock();

        rxEScriptDTOs.get(0).setProdDea((byte) 1);
        rxEScriptDTOs.get(0).setOriginalNumRefills(0L);

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class), any(Expression.class))).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(tuple.get(0, String.class)).thenReturn(shippingState);
        when(tuple.get(1, String.class)).thenReturn(rxNumber);
        when(tuple.get(2, String.class)).thenReturn(okNullGpi);
        when(query.fetchFirst()).thenReturn(tuple);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        RxSanityResult res = rxSanityService.checkRxSanity(scriptId, rxNumber);
        assertNull(res);
    }

    @Test
    public void test_checkRxSanity_NoError6() {
        setUpMock();

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class), any(Expression.class))).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(tuple.get(0, String.class)).thenReturn(shippingState);
        when(tuple.get(1, String.class)).thenReturn(rxNumber);
        when(tuple.get(2, String.class)).thenReturn(okNullGpi);
        when(query.fetchFirst()).thenReturn(tuple, 0);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        RxSanityResult res = rxSanityService.checkRxSanity(scriptId, rxNumber);
        assertNull(res);
    }

    @Test
    public void test_checkRxSanity_NoError7() {
        setUpMock();

        rxEScriptDTOs.get(0).setWrittenQuantity(0L);
        rxEScriptDTOs.get(1).setWrittenQuantity(0L);

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class), any(Expression.class))).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(tuple.get(0, String.class)).thenReturn(shippingState);
        when(tuple.get(1, String.class)).thenReturn(rxNumber);
        when(tuple.get(2, String.class)).thenReturn(okNullGpi);
        when(query.fetchFirst()).thenReturn(tuple, 1);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        RxSanityResult res = rxSanityService.checkRxSanity(scriptId, rxNumber);
        assertNull(res);
    }

    @Test
    public void test_checkRxSanity() {
        setUpMock();

        rxEScriptDTOs.get(0).setFillQtyDispensed(1l);
        rxEScriptDTOs.get(1).setFillQtyDispensed(2l);
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class), any(Expression.class))).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(tuple.get(0, String.class)).thenReturn(shippingState);
        when(tuple.get(1, String.class)).thenReturn(rxNumber);
        when(tuple.get(2, String.class)).thenReturn("1");
        when(query.fetchFirst()).thenReturn(tuple, 1);
        when(query.fetch()).thenReturn(rxEScriptDTOs);

        RxSanityResult res = rxSanityService.checkRxSanity(scriptId, rxNumber);
        assertEquals(1, res.getErrors().size());
        assertEquals(" (1) Hydrocodone Product with Refills. Please Review.", res.toErrorMessage());
    }

    @Test
    public void test_ePostRx_04_01_002() {
        assertThrows(RuntimeException.class, () -> rxSanityService.checkRxSanity(null, null));
        assertThrows(RuntimeException.class, () -> rxSanityService.checkRxSanity(BigInteger.valueOf(-999), null));
    }

    @Test
    public void test_ePostRx_04_04_002() {
        setUpMock();

        List<RxEScriptDTO> list = new ArrayList<>();
        RxEScriptDTO dto = new RxEScriptDTO();
        dto.setEScriptMsgAttributeSeq(BigInteger.valueOf(4200));
        dto.setRxRefillsLeft(2);
        dto.setWrittenQuantity(30L);
        dto.setDispensedQuantity(10L);
        dto.setRxNumber("4200");
        dto.setOriginalNumRefills(3L);
        dto.setNumRefills(1L);
        dto.setFillStatusNum((byte) 2);
        dto.setFillQtyDispensed(10L);
        dto.setOrtfQty(null);
        dto.setProdDea((byte) 2);
        list.add(dto);

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class), any(Expression.class))).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(tuple.get(0, String.class)).thenReturn(shippingState);
        when(tuple.get(1, String.class)).thenReturn("4200");
        when(tuple.get(2, String.class)).thenReturn("1");
        when(query.fetchFirst()).thenReturn(tuple);
        when(query.fetch()).thenReturn(list);

        RxSanityResult res = rxSanityService.checkRxSanity(BigInteger.valueOf(4200), "4200");
        assertEquals(1, res.getErrors().size());
        assertEquals(" (1) Hydrocodone Product with Refills. Please Review.", res.toErrorMessage());

        dto = new RxEScriptDTO();
        dto.setEScriptMsgAttributeSeq(BigInteger.valueOf(4201));
        dto.setRxRefillsLeft(1);
        dto.setWrittenQuantity(30L);
        dto.setDispensedQuantity(15L);
        dto.setRxNumber("4200");
        dto.setOriginalNumRefills(3L);
        dto.setNumRefills(2L);
        dto.setFillStatusNum((byte) 2);
        dto.setFillQtyDispensed(5L);
        dto.setOrtfQty(null);
        dto.setProdDea((byte) 2);
        list.add(dto);

        res = rxSanityService.checkRxSanity(BigInteger.valueOf(4200), null);
        assertEquals(1, res.getErrors().size());
        assertEquals(" (1) Hydrocodone Product with Refills. Please Review.", res.toErrorMessage());

        dto = new RxEScriptDTO();
        dto.setEScriptMsgAttributeSeq(BigInteger.valueOf(4202));
        dto.setRxRefillsLeft(0);
        dto.setWrittenQuantity(30L);
        dto.setDispensedQuantity(20L);
        dto.setRxNumber("4200");
        dto.setOriginalNumRefills(3L);
        dto.setNumRefills(3L);
        dto.setFillStatusNum((byte) 2);
        dto.setFillQtyDispensed(5L);
        dto.setOrtfQty(null);
        dto.setProdDea((byte) 2);
        list.add(dto);

        res = rxSanityService.checkRxSanity(BigInteger.valueOf(4200), "4200");
        assertEquals(1, res.getErrors().size());
        assertEquals(" (1) Hydrocodone Product with Refills. Please Review.", res.toErrorMessage());

        dto = new RxEScriptDTO();
        dto.setEScriptMsgAttributeSeq(BigInteger.valueOf(4203));
        dto.setRxRefillsLeft(0);
        dto.setWrittenQuantity(30L);
        dto.setDispensedQuantity(20L);
        dto.setRxNumber("4200");
        dto.setOriginalNumRefills(3L);
        dto.setNumRefills(4L);
        dto.setFillStatusNum((byte) 2);
        dto.setFillQtyDispensed(5L);
        dto.setOrtfQty(null);
        dto.setProdDea((byte) 2);
        list.add(dto);

        res = rxSanityService.checkRxSanity(BigInteger.valueOf(4200), "4200");
        assertNull(res);
    }

    @Test
    public void test_ePostRx_04_04_003() {
        setUpMock();

        List<RxEScriptDTO> list = new ArrayList<>();
        RxEScriptDTO dto = new RxEScriptDTO();
        dto.setEScriptMsgAttributeSeq(BigInteger.valueOf(4200));
        dto.setRxRefillsLeft(2);
        dto.setWrittenQuantity(30L);
        dto.setDispensedQuantity(10L);
        dto.setRxNumber("4200");
        dto.setOriginalNumRefills(3L);
        dto.setNumRefills(1L);
        dto.setFillStatusNum((byte) 2);
        dto.setFillQtyDispensed(10L);
        dto.setOrtfQty(null);
        dto.setProdDea((byte) 0);
        list.add(dto);

        dto = new RxEScriptDTO();
        dto.setEScriptMsgAttributeSeq(BigInteger.valueOf(4201));
        dto.setRxRefillsLeft(1);
        dto.setWrittenQuantity(30L);
        dto.setDispensedQuantity(15L);
        dto.setRxNumber("4200");
        dto.setOriginalNumRefills(3L);
        dto.setNumRefills(2L);
        dto.setFillStatusNum((byte) 2);
        dto.setFillQtyDispensed(5L);
        dto.setOrtfQty(null);
        dto.setProdDea((byte) 0);
        list.add(dto);

        dto = new RxEScriptDTO();
        dto.setEScriptMsgAttributeSeq(BigInteger.valueOf(4202));
        dto.setRxRefillsLeft(0);
        dto.setWrittenQuantity(30L);
        dto.setDispensedQuantity(20L);
        dto.setRxNumber("4200");
        dto.setOriginalNumRefills(3L);
        dto.setNumRefills(3L);
        dto.setFillStatusNum((byte) 2);
        dto.setFillQtyDispensed(5L);
        dto.setOrtfQty(null);
        dto.setProdDea((byte) 0);
        list.add(dto);

        dto = new RxEScriptDTO();
        dto.setEScriptMsgAttributeSeq(BigInteger.valueOf(4203));
        dto.setRxRefillsLeft(0);
        dto.setWrittenQuantity(30L);
        dto.setDispensedQuantity(20L);
        dto.setRxNumber("4200");
        dto.setOriginalNumRefills(3L);
        dto.setNumRefills(4L);
        dto.setFillStatusNum((byte) 2);
        dto.setFillQtyDispensed(5L);
        dto.setOrtfQty(null);
        dto.setProdDea((byte) 0);
        list.add(dto);

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class), any(Expression.class))).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(tuple.get(0, String.class)).thenReturn(shippingState);
        when(tuple.get(1, String.class)).thenReturn("4200");
        when(tuple.get(2, String.class)).thenReturn("1");
        when(query.fetchFirst()).thenReturn(tuple);
        when(query.fetch()).thenReturn(list);

        RxSanityResult res = rxSanityService.checkRxSanity(BigInteger.valueOf(4200), "4200");
        assertEquals(1, res.getErrors().size());
        assertEquals(" (1) Hydrocodone Product with Refills. Please Review.", res.toErrorMessage());

        for (RxEScriptDTO item : list) {
            item.setProdDea((byte) 2);
        }

        res = rxSanityService.checkRxSanity(BigInteger.valueOf(4200), "4200");
        assertNull(res);
    }

    @Test
    public void test_ePostRx_04_04_004() {
        setUpMock();

        List<RxEScriptDTO> list = new ArrayList<>();
        RxEScriptDTO dto = new RxEScriptDTO();
        dto.setEScriptMsgAttributeSeq(BigInteger.valueOf(4200));
        dto.setRxRefillsLeft(2);
        dto.setWrittenQuantity(30L);
        dto.setDispensedQuantity(10L);
        dto.setRxNumber("4200");
        dto.setOriginalNumRefills(3L);
        dto.setNumRefills(1L);
        dto.setFillStatusNum((byte) 2);
        dto.setFillQtyDispensed(10L);
        dto.setOrtfQty(null);
        dto.setProdDea((byte) 2);
        list.add(dto);

        dto = new RxEScriptDTO();
        dto.setEScriptMsgAttributeSeq(BigInteger.valueOf(4201));
        dto.setRxRefillsLeft(1);
        dto.setWrittenQuantity(30L);
        dto.setDispensedQuantity(15L);
        dto.setRxNumber("4200");
        dto.setOriginalNumRefills(3L);
        dto.setNumRefills(2L);
        dto.setFillStatusNum((byte) 2);
        dto.setFillQtyDispensed(5L);
        dto.setOrtfQty(null);
        dto.setProdDea((byte) 2);
        list.add(dto);

        dto = new RxEScriptDTO();
        dto.setEScriptMsgAttributeSeq(BigInteger.valueOf(4202));
        dto.setRxRefillsLeft(0);
        dto.setWrittenQuantity(30L);
        dto.setDispensedQuantity(20L);
        dto.setRxNumber("4200");
        dto.setOriginalNumRefills(3L);
        dto.setNumRefills(3L);
        dto.setFillStatusNum((byte) 2);
        dto.setFillQtyDispensed(5L);
        dto.setOrtfQty(null);
        dto.setProdDea((byte) 2);
        list.add(dto);

        dto = new RxEScriptDTO();
        dto.setEScriptMsgAttributeSeq(BigInteger.valueOf(4203));
        dto.setRxRefillsLeft(0);
        dto.setWrittenQuantity(30L);
        dto.setDispensedQuantity(20L);
        dto.setRxNumber("4200");
        dto.setOriginalNumRefills(3L);
        dto.setNumRefills(4L);
        dto.setFillStatusNum((byte) 2);
        dto.setFillQtyDispensed(5L);
        dto.setOrtfQty(null);
        dto.setProdDea((byte) 2);
        list.add(dto);

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class), any(Expression.class))).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(tuple.get(0, String.class)).thenReturn(shippingState);
        when(tuple.get(1, String.class)).thenReturn("4200");
        when(tuple.get(2, String.class)).thenReturn("1");
        when(query.fetchFirst()).thenReturn(tuple);
        when(query.fetch()).thenReturn(list);

        RxSanityResult res = rxSanityService.checkRxSanity(BigInteger.valueOf(4200), "4200");
        assertNull(res);

        for (RxEScriptDTO item : list) {
            item.setFillStatusNum((byte) 0);
        }

        res = rxSanityService.checkRxSanity(BigInteger.valueOf(4200), "4200");
        assertEquals(1, res.getErrors().size());
        assertEquals(" (1) Hydrocodone Product with Refills. Please Review.", res.toErrorMessage());
    }

    @Test
    public void test_ePostRx_04_05_001() {
        setUpMock();

        List<RxEScriptDTO> list = new ArrayList<>();
        RxEScriptDTO dto = new RxEScriptDTO();
        dto.setEScriptMsgAttributeSeq(BigInteger.valueOf(4300));
        dto.setRxRefillsLeft(1);
        dto.setWrittenQuantity(30L);
        dto.setDispensedQuantity(10L);
        dto.setRxNumber("4300");
        dto.setOriginalNumRefills(2L);
        dto.setNumRefills(1L);
        dto.setFillStatusNum((byte) 2);
        dto.setFillQtyDispensed(10L);
        dto.setOrtfQty(null);
        dto.setProdDea((byte) 2);
        list.add(dto);

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class), any(Expression.class))).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(tuple.get(0, String.class)).thenReturn(shippingState);
        when(tuple.get(1, String.class)).thenReturn("4300");
        when(tuple.get(2, String.class)).thenReturn("1");
        when(query.fetchFirst()).thenReturn(tuple);
        when(query.fetch()).thenReturn(list);

        RxSanityResult res = rxSanityService.checkRxSanity(BigInteger.valueOf(4200), "4300");
        assertEquals(1, res.getErrors().size());
        assertEquals(" (1) Hydrocodone Product with Refills. Please Review.", res.toErrorMessage());

        dto = new RxEScriptDTO();
        dto.setEScriptMsgAttributeSeq(BigInteger.valueOf(4301));
        dto.setRxRefillsLeft(0);
        dto.setWrittenQuantity(30L);
        dto.setDispensedQuantity(40L);
        dto.setRxNumber("4300");
        dto.setOriginalNumRefills(2L);
        dto.setNumRefills(1L);
        dto.setFillStatusNum((byte) 2);
        dto.setFillQtyDispensed(20L);
        dto.setOrtfQty(0L);
        dto.setProdDea((byte) 2);
        list.add(dto);

        res = rxSanityService.checkRxSanity(BigInteger.valueOf(4200), "4300");
        assertNull(res);

        list.get(1).setDispensedQuantity(50L);
        list.get(1).setOrtfQty(1000L);

        res = rxSanityService.checkRxSanity(BigInteger.valueOf(4200), "4300");
        assertEquals(1, res.getErrors().size());
        assertEquals(" (1) Hydrocodone Product with Refills. Please Review.", res.toErrorMessage());
    }

    @Test
    public void test_ePostRx_04_07_001() {
        setUpMock();

        List<RxEScriptDTO> list = new ArrayList<>();
        RxEScriptDTO dto = new RxEScriptDTO();
        dto.setEScriptMsgAttributeSeq(BigInteger.valueOf(4400));
        dto.setRxRefillsLeft(1);
        dto.setWrittenQuantity(30L);
        dto.setDispensedQuantity(10L);
        dto.setRxNumber("4400");
        dto.setOriginalNumRefills(2L);
        dto.setNumRefills(1L);
        dto.setFillStatusNum((byte) 2);
        dto.setFillQtyDispensed(10L);
        dto.setOrtfQty(null);
        dto.setProdDea((byte) 2);
        list.add(dto);

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class), any(Expression.class))).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(tuple.get(0, String.class)).thenReturn(shippingState);
        when(tuple.get(1, String.class)).thenReturn("4400");
        when(tuple.get(2, String.class)).thenReturn("1");
        when(query.fetchFirst()).thenReturn(tuple);
        when(query.fetch()).thenReturn(list);

        RxSanityResult res = rxSanityService.checkRxSanity(BigInteger.valueOf(4200), "4400");
        assertEquals(1, res.getErrors().size());
        assertEquals(" (1) Hydrocodone Product with Refills. Please Review.", res.toErrorMessage());

        list.clear();
        dto = new RxEScriptDTO();
        dto.setEScriptMsgAttributeSeq(BigInteger.valueOf(4203));
        dto.setRxRefillsLeft(0);
        dto.setWrittenQuantity(30L);
        dto.setDispensedQuantity(20L);
        dto.setRxNumber("4200");
        dto.setOriginalNumRefills(3L);
        dto.setNumRefills(4L);
        dto.setFillStatusNum((byte) 2);
        dto.setFillQtyDispensed(5L);
        dto.setOrtfQty(null);
        dto.setProdDea((byte) 2);
        list.add(dto);

        res = rxSanityService.checkRxSanity(BigInteger.valueOf(4200), null);
        assertNull(res);

        list.clear();
        dto = new RxEScriptDTO();
        dto.setEScriptMsgAttributeSeq(BigInteger.valueOf(4400));
        dto.setRxRefillsLeft(1);
        dto.setWrittenQuantity(30L);
        dto.setDispensedQuantity(10L);
        dto.setRxNumber("4400");
        dto.setOriginalNumRefills(2L);
        dto.setNumRefills(1L);
        dto.setFillStatusNum((byte) 2);
        dto.setFillQtyDispensed(10L);
        dto.setOrtfQty(null);
        dto.setProdDea((byte) 2);
        list.add(dto);

        res = rxSanityService.checkRxSanity(BigInteger.valueOf(4200), "4400");
        assertEquals(1, res.getErrors().size());
        assertEquals(" (1) Hydrocodone Product with Refills. Please Review.", res.toErrorMessage());
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
        list.add(dto1);
        RxEScriptDTO dto2 = new RxEScriptDTO();
        dto2.setEScriptMsgAttributeSeq(BigInteger.valueOf(2));
        dto2.setRxNumber("rxn1");
        dto2.setFillQtyDispensed(200L);
        dto2.setWrittenQuantity(401L);
        list.add(dto2);

        return list;
    }
}
