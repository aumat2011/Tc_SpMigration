package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.TestsHelper;
import com.humana.pharmacy.common.cache.model.CRxDefaults;
import com.humana.pharmacy.common.dto.AnsRxNarcCodeEScriptDTO;
import com.humana.pharmacy.common.service.impl.FunctionsServiceImpl;
import com.humana.pharmacy.dto.AnsRxNarcCodeResult;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit test cases for AnsRxNarcCodeServiceImpl;
 */
@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AnsRxNarcCodeServiceImplTest {
    /**
     * The number '32 * 24 * 60 * 60 * 1000'.
     */
    private static final long GREATER_THAN_ONE_MONTH = 32L * 24 * 60 * 60 * 1000;

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
     * The AnsRxNarcCodeServiceImpl instance.
     */
    private AnsRxNarcCodeServiceImpl ansRxNarcCodeService;

    /**
     * The script ID.
     */
    private BigInteger scriptId = BigInteger.valueOf(1);

    /**
     * The Rx number.
     */
    private final String rxNumber = "rxn1";

    /**
     * The tuple data.
     */
    private final Object[] tupleData = new Object[]{scriptId, 1, (byte) 2, "NEWRX"};

    /**
     * The tuple.
     */
    @Mock
    private Tuple tuple;

    /**
     * The AnsRxNarcCodeEScriptDTO list.
     */
    private List<AnsRxNarcCodeEScriptDTO> ansRxNarcCodeEScriptDTO;

    /**
     * The RX defaults.
     */
    private CRxDefaults rxDefaults;

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
        functionsService = new FunctionsServiceImpl(epostrxDataSource, workflowDataSource);
        ansRxNarcCodeService = new AnsRxNarcCodeServiceImpl(epostrxDataSource, workflowDataSource);
        ansRxNarcCodeService.setFunctionsService(functionsService);

        TestsHelper.setFieldValue(ansRxNarcCodeService, "epostrxQueryFactory", epostrxQueryFactory);
        TestsHelper.setFieldValue(ansRxNarcCodeService, "workflowQueryFactory", workflowQueryFactory);

        rxDefaults = getRxDefaults();

        ansRxNarcCodeEScriptDTO = getAnsRxNarcCodeEScriptDTOs();
    }

    private void setUpMock() {
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);

        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
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
    public void test_checkAnsRxNarcCode_scriptIdNull() {
        assertThrows(RuntimeException.class, () -> ansRxNarcCodeService.checkAnsRxNarcCode(null, null));
    }

    @Test
    public void test_checkAnsRxNarcCode_functionsServiceNull() {
        ansRxNarcCodeService = new AnsRxNarcCodeServiceImpl(epostrxDataSource, workflowDataSource);
        assertThrows(RuntimeException.class, () -> ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null));
    }

    @Test
    public void test_checkAnsRxNarcCode_scriptIdNotFound() {
        setUpMock();

        scriptId = BigInteger.valueOf(9999);
        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxNarcCode_StateGpiClassLimitNumberRefills1() {
        ansRxNarcCodeEScriptDTO.get(0).setNumRefills(1L);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumRefillsGpi(0);

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("LA STATE\\GPI CLASS LIMITS NUMBER OF REFILLS TO: 0 RX HAS: 1"));
    }

    @Test
    public void test_checkAnsRxNarcCode_StateGpiClassLimitNumberRefills2() {
        ansRxNarcCodeEScriptDTO.get(0).setNumRefills(1L);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumRefillsGpi(0);
        ansRxNarcCodeEScriptDTO.get(0).setEdiTransactionCode("REFILLRX");

        tupleData[3] = "REFILLRX";

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("LA STATE\\GPI CLASS LIMITS NUMBER OF REFILLS TO: 0 RX HAS: 1"));
    }

    @Test
    public void test_checkAnsRxNarcCode_StateGpiClassLimitNumberRefills3() {
        ansRxNarcCodeEScriptDTO.get(0).setScpProdDea((short) 0);
        ansRxNarcCodeEScriptDTO.get(0).setNumRefills(1L);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumRefillsGpi(0);
        ansRxNarcCodeEScriptDTO.get(0).setEdiTransactionCode("REFILLRX");

        tupleData[3] = "REFILLRX";

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("LA STATE\\GPI CLASS LIMITS NUMBER OF REFILLS TO: 0 RX HAS: 1"));
    }

    @Test
    public void test_checkAnsRxNarcCode_StateGpiClassLimitDaysOriginal1() {
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysOriginalGpi(0);
        ansRxNarcCodeEScriptDTO.get(0).setNarcoticCode("" + ansRxNarcCodeEScriptDTO.get(0).getScpProdDea());
        ansRxNarcCodeEScriptDTO.get(0).setWrittenDate(new Timestamp(System.currentTimeMillis() - GREATER_THAN_ONE_MONTH));

        tupleData[2] = (byte) 5;

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("LA STATE\\GPI CLASS LIMITS DAYS FROM ORIGINAL: 0 RX DAYS: 32"));
    }

    @Test
    public void test_checkAnsRxNarcCode_StateGpiClassLimitDaysSupply1() {
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysOriginalGpi(1);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysSupplyGpi(0);
        ansRxNarcCodeEScriptDTO.get(0).setNarcoticCode("" + ansRxNarcCodeEScriptDTO.get(0).getScpProdDea());
        ansRxNarcCodeEScriptDTO.get(0).setWrittenDate(new Timestamp(System.currentTimeMillis() - GREATER_THAN_ONE_MONTH));

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("LA STATE\\GPI CLASS LIMITS DAYS SUPPLY AS: 0 RX DAYS SUPPLY: 1"));
    }

    @Test
    public void test_checkAnsRxNarcCode_StateGpiClassLimitDaysSupply2() {
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysOriginalGpi(1);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysSupplyGpi(0);
        ansRxNarcCodeEScriptDTO.get(0).setNarcoticCode("" + ansRxNarcCodeEScriptDTO.get(0).getScpProdDea());
        ansRxNarcCodeEScriptDTO.get(0).setWrittenDate(new Timestamp(System.currentTimeMillis() - GREATER_THAN_ONE_MONTH));

        tupleData[1] = 5;
        tupleData[2] = (byte) -9;
        tupleData[3] = "REFILLRX";

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("LA STATE\\GPI CLASS LIMITS DAYS SUPPLY AS: 0 RX DAYS SUPPLY: 5"));
    }

    @Test
    public void test_checkAnsRxNarcCode_StateGpiClassProhibitsElectronic() {
        ansRxNarcCodeEScriptDTO.get(0).setNarcoticCode("2");
        ansRxNarcCodeEScriptDTO.get(0).setScpProdDea((short) 2);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysOriginalGpi(1);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysSupplyGpi(9999);
        ansRxNarcCodeEScriptDTO.get(0).setOriginationNum(4);

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains(
                "STATE: LA GPI CLASS PROHIBITS ELECTRONIC TRANSMISSION (FAX/ERX) OF CONTROLLED SUBSTANCE RX."));
    }

    @Test
    public void test_checkAnsRxNarcCode_StateLaDoctorRule() {
        ansRxNarcCodeEScriptDTO.get(0).setNarcoticCode("2");
        ansRxNarcCodeEScriptDTO.get(0).setProdGenericRefNum(null);
        ansRxNarcCodeEScriptDTO.get(0).setNumRefills(1L);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumRefills(1);
        ansRxNarcCodeEScriptDTO.get(0).setProdDea((byte) 2);
        ansRxNarcCodeEScriptDTO.get(0).setScpProdDea((short) 2);
        ansRxNarcCodeEScriptDTO.get(0).setStateCode("LA");
        ansRxNarcCodeEScriptDTO.get(0).setTpaStateCode("XX");

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains(
                "LA in-state doctor rule. CII & CIII opioid prescriptions must be written by a LA licensed physician"));
    }

    @Test
    public void test_checkAnsRxNarcCode_StateLawLimitNumberRefills1() {
        ansRxNarcCodeEScriptDTO.get(0).setProdGenericRefNum(null);
        ansRxNarcCodeEScriptDTO.get(0).setNumRefills(1L);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumRefills(0);

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("LA STATE LAW LIMITS NUMBER OF REFILLS TO: 0 RX HAS: 1"));
    }

    @Test
    public void test_checkAnsRxNarcCode_StateLawLimitNumberRefills2() {
        ansRxNarcCodeEScriptDTO.get(0).setProdGenericRefNum(null);
        ansRxNarcCodeEScriptDTO.get(0).setNumRefills(1L);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumRefills(0);
        ansRxNarcCodeEScriptDTO.get(0).setEdiTransactionCode("REFILLRX");

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("LA STATE LAW LIMITS NUMBER OF REFILLS TO: 0 RX HAS: 1"));
    }

    @Test
    public void test_checkAnsRxNarcCode_StateLawLimitNumberRefills3() {
        ansRxNarcCodeEScriptDTO.get(0).setProdGenericRefNum(null);
        ansRxNarcCodeEScriptDTO.get(0).setNumRefills(1L);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumRefills(0);
        ansRxNarcCodeEScriptDTO.get(0).setEdiTransactionCode("REFILLRX");

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("LA STATE LAW LIMITS NUMBER OF REFILLS TO: 0 RX HAS: 1"));
    }

    @Test
    public void test_checkAnsRxNarcCode_StateLawLimitDaysOriginal() {
        ansRxNarcCodeEScriptDTO.get(0).setProdGenericRefNum(null);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysOriginal(0);
        ansRxNarcCodeEScriptDTO.get(0).setNarcoticCode("" + ansRxNarcCodeEScriptDTO.get(0).getScpProdDea());
        ansRxNarcCodeEScriptDTO.get(0).setWrittenDate(new Timestamp(System.currentTimeMillis() - GREATER_THAN_ONE_MONTH));

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("LA STATE LAW LIMITS DAYS FROM ORIGINAL: 0 RX DAYS: 32"));
    }

    @Test
    public void test_checkAnsRxNarcCode_StateLawLimitDaysSupply() {
        ansRxNarcCodeEScriptDTO.get(0).setProdGenericRefNum(null);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysOriginal(1);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysSupply(0);
        ansRxNarcCodeEScriptDTO.get(0).setEdiTransactionCode("REFILLRX");
        ansRxNarcCodeEScriptDTO.get(0).setNarcoticCode("" + ansRxNarcCodeEScriptDTO.get(0).getScpProdDea());
        ansRxNarcCodeEScriptDTO.get(0).setWrittenDate(new Timestamp(System.currentTimeMillis() - GREATER_THAN_ONE_MONTH));

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("LA STATE LAW LIMITS DAYS SUPPLY AS: 0 RX DAYS SUPPLY: 1"));
    }

    @Test
    public void test_checkAnsRxNarcCode_StateLawProhibitsElectronic() {
        ansRxNarcCodeEScriptDTO.get(0).setNarcoticCode("2");
        ansRxNarcCodeEScriptDTO.get(0).setProdGenericRefNum(null);
        ansRxNarcCodeEScriptDTO.get(0).setScpProdDea((short) 2);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysOriginal(1);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysSupply(9999);
        ansRxNarcCodeEScriptDTO.get(0).setOriginationNum(4);

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains(
                "EPOSTRX NARCOTIC CODE PARAMETERS FOR STATE: LA PROHIBITS ELECTRONIC TRANSMISSION (FAX/ERX) OF CONTROLLED SUBSTANCE RX."));
    }

    @Test
    public void test_checkAnsRxNarcCode_NoError1() {
        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxNarcCode_NoError2() {
        ansRxNarcCodeEScriptDTO.get(0).setNumRefills(0L);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumRefillsGpi(1);

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxNarcCode_NoError3() {
        ansRxNarcCodeEScriptDTO.get(0).setScpProdDea((short) 2);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysOriginalGpi(1);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysSupplyGpi(9999);
        ansRxNarcCodeEScriptDTO.get(0).setOriginationNum(5);

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxNarcCode_NoError4() {
        ansRxNarcCodeEScriptDTO.get(0).setScpProdDea((short) 2);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysOriginalGpi(1);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysSupplyGpi(9999);
        ansRxNarcCodeEScriptDTO.get(0).setOriginationNum(4);
        ansRxNarcCodeEScriptDTO.get(0).setNarcAcceptFaxGpi("X");

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxNarcCode_NoError5() {
        ansRxNarcCodeEScriptDTO.get(0).setProdGenericRefNum(null);
        ansRxNarcCodeEScriptDTO.get(0).setScpProdDea((short) 2);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysOriginal(1);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysSupply(9999);
        ansRxNarcCodeEScriptDTO.get(0).setOriginationNum(5);

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxNarcCode_NoError6() {
        ansRxNarcCodeEScriptDTO.get(0).setEdiTransactionCode("X");
        ansRxNarcCodeEScriptDTO.get(0).setScpProdDea((short) 2);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysOriginal(1);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysSupply(9999);
        ansRxNarcCodeEScriptDTO.get(0).setOriginationNum(5);

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxNarcCode_NoError7() {
        rxDefaults.setCheckNarcoticRefills("X");
        ansRxNarcCodeEScriptDTO.get(0).setScpProdDea((short) 2);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysOriginal(1);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysSupply(9999);
        ansRxNarcCodeEScriptDTO.get(0).setOriginationNum(5);
        ansRxNarcCodeEScriptDTO.get(0).setEdiTransactionCode("REFILLRX");

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxNarcCode_NoError8() {
        ansRxNarcCodeEScriptDTO.get(0).setProdGenericRefNum(null);
        ansRxNarcCodeEScriptDTO.get(0).setEdiTransactionCode("X");
        ansRxNarcCodeEScriptDTO.get(0).setScpProdDea((short) 2);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysOriginal(1);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysSupply(9999);
        ansRxNarcCodeEScriptDTO.get(0).setOriginationNum(5);

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxNarcCode_NoError9() {
        rxDefaults.setCheckNarcoticRefills("X");
        ansRxNarcCodeEScriptDTO.get(0).setProdGenericRefNum(null);
        ansRxNarcCodeEScriptDTO.get(0).setScpProdDea((short) 2);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysOriginal(1);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysSupply(9999);
        ansRxNarcCodeEScriptDTO.get(0).setOriginationNum(5);
        ansRxNarcCodeEScriptDTO.get(0).setEdiTransactionCode("REFILLRX");

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxNarcCode_NoError10() {
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysOriginalGpi(0);
        ansRxNarcCodeEScriptDTO.get(0).setNarcoticCode("" + ansRxNarcCodeEScriptDTO.get(0).getScpProdDea());
        ansRxNarcCodeEScriptDTO.get(0).setWrittenDate(new Timestamp(System.currentTimeMillis()));

        tupleData[2] = (byte) 5;

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxNarcCode_NoError11() {
        ansRxNarcCodeEScriptDTO.get(0).setNarcoticCode("2");
        ansRxNarcCodeEScriptDTO.get(0).setProdGenericRefNum(null);
        ansRxNarcCodeEScriptDTO.get(0).setNumRefills(1L);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumRefills(1);
        ansRxNarcCodeEScriptDTO.get(0).setProdDea((byte) 2);
        ansRxNarcCodeEScriptDTO.get(0).setScpProdDea((short) 2);
        ansRxNarcCodeEScriptDTO.get(0).setStateCode("LA");
        ansRxNarcCodeEScriptDTO.get(0).setTpaStateCode("LA");

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxNarcCode_NoError12() {
        ansRxNarcCodeEScriptDTO.get(0).setNarcoticCode(null);
        ansRxNarcCodeEScriptDTO.get(0).setProdGenericRefNum(null);
        ansRxNarcCodeEScriptDTO.get(0).setNumRefills(1L);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumRefillsGpi(1);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumRefills(1);
        ansRxNarcCodeEScriptDTO.get(0).setProdDea((byte) 2);
        ansRxNarcCodeEScriptDTO.get(0).setScpProdDea((short) 2);
        ansRxNarcCodeEScriptDTO.get(0).setStateCode("LA");
        ansRxNarcCodeEScriptDTO.get(0).setTpaStateCode("LA");

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxNarcCode_UpdateRxExpirationDate1() {
        ansRxNarcCodeEScriptDTO.get(0).setNarcoticCode("2");
        ansRxNarcCodeEScriptDTO.get(0).setScpProdDea((short) 2);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysOriginal(1);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysSupply(9999);
        ansRxNarcCodeEScriptDTO.get(0).setOriginationNum(5);
        ansRxNarcCodeEScriptDTO.get(0).setEdiTransactionCode("REFILLRX");
        ansRxNarcCodeEScriptDTO.get(0).setWrittenDate(new Timestamp(System.currentTimeMillis()));
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysExpire(100);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysExpireGpi(100);
        ansRxNarcCodeEScriptDTO.get(0).setRxExpirationDate(new Timestamp(System.currentTimeMillis() + GREATER_THAN_ONE_MONTH));

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxNarcCode_UpdateRxExpirationDate2() {
        ansRxNarcCodeEScriptDTO.get(0).setNarcoticCode("2");
        ansRxNarcCodeEScriptDTO.get(0).setScpProdDea((short) 2);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysOriginal(1);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysSupply(9999);
        ansRxNarcCodeEScriptDTO.get(0).setOriginationNum(5);
        ansRxNarcCodeEScriptDTO.get(0).setEdiTransactionCode("REFILLRX");
        ansRxNarcCodeEScriptDTO.get(0).setWrittenDate(new Timestamp(System.currentTimeMillis()));
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysExpire(100);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysExpireGpi(null);
        ansRxNarcCodeEScriptDTO.get(0).setRxExpirationDate(new Timestamp(System.currentTimeMillis() + GREATER_THAN_ONE_MONTH));

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxNarcCode_UpdateRxExpirationDate3() {
        ansRxNarcCodeEScriptDTO.get(0).setNarcoticCode("2");
        ansRxNarcCodeEScriptDTO.get(0).setProdGenericRefNum(null);
        ansRxNarcCodeEScriptDTO.get(0).setScpProdDea((short) 2);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysOriginal(1);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysSupply(9999);
        ansRxNarcCodeEScriptDTO.get(0).setOriginationNum(5);
        ansRxNarcCodeEScriptDTO.get(0).setEdiTransactionCode("REFILLRX");
        ansRxNarcCodeEScriptDTO.get(0).setWrittenDate(new Timestamp(System.currentTimeMillis()));
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysExpire(100);
        ansRxNarcCodeEScriptDTO.get(0).setRxExpirationDate(new Timestamp(System.currentTimeMillis() + GREATER_THAN_ONE_MONTH));

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxNarcCode_UpdateRxExpirationDate4() {
        ansRxNarcCodeEScriptDTO.get(0).setNarcoticCode("2");
        ansRxNarcCodeEScriptDTO.get(0).setProdGenericRefNum(null);
        ansRxNarcCodeEScriptDTO.get(0).setScpProdDea((short) 2);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysOriginal(1);
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysSupply(9999);
        ansRxNarcCodeEScriptDTO.get(0).setOriginationNum(5);
        ansRxNarcCodeEScriptDTO.get(0).setEdiTransactionCode("REFILLRX");
        ansRxNarcCodeEScriptDTO.get(0).setWrittenDate(new Timestamp(System.currentTimeMillis()));
        ansRxNarcCodeEScriptDTO.get(0).setNarcNumDaysExpire(100);
        ansRxNarcCodeEScriptDTO.get(0).setNarcoticCode("2");
        ansRxNarcCodeEScriptDTO.get(0).setRxExpirationDate(new Timestamp(System.currentTimeMillis() + GREATER_THAN_ONE_MONTH));

        setUpMock();

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxNarcCodeEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(rxDefaults);

        AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_setFunctionsService_functionsServiceNull() {
        assertThrows(RuntimeException.class, () -> ansRxNarcCodeService.setFunctionsService(null));
    }

    /**
     * Build the tuple.
     *
     * @param values the values.
     */
    private void buildTuple(Object[] values) {
        when(tuple.get(0, BigInteger.class)).thenReturn((BigInteger) values[0]);
        when(tuple.get(1, Integer.class)).thenReturn((Integer) values[1]);
        when(tuple.get(2, Byte.class)).thenReturn((Byte) values[2]);
        when(tuple.get(3, String.class)).thenReturn((String) values[3]);
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
        dto1.setRxNumber(rxNumber);
        dto1.setProdGenericRefNum("PGRN1");
        dto1.setOrderNum(BigInteger.valueOf(1));
        dto1.setNarcoticCode("1");
        dto1.setStateNum(1);
        dto1.setOrderInvoiceNumber("OIN1");
        dto1.setStateCsProductSeq(1L);
        dto1.setScpProdDea((short) 1);
        dto1.setStateName("LA");
        dto1.setGpiClassId("PGRN1");
        dto1.setEdiTransactionCode("NEWRX");
        list.add(dto1);

        AnsRxNarcCodeEScriptDTO dto2 = new AnsRxNarcCodeEScriptDTO();
        dto2.setEScriptMsgAttributeSeq(BigInteger.valueOf(2));
        dto2.setRxNumber("rxn1");
        dto2.setProdGenericRefNum("PGRN2");
        dto2.setOrderNum(BigInteger.valueOf(2));
        dto2.setNarcoticCode("2");
        dto2.setStateNum(2);
        dto2.setOrderInvoiceNumber("OIN2");
        dto2.setStateCsProductSeq(2L);
        dto2.setScpProdDea((short) 2);
        dto2.setGpiClassId("PGRN2");
        dto2.setEdiTransactionCode("REFILLRX");
        list.add(dto2);

        AnsRxNarcCodeEScriptDTO dto3 = new AnsRxNarcCodeEScriptDTO();
        dto3.setEScriptMsgAttributeSeq(BigInteger.valueOf(3));
        dto3.setRxNumber("rxn3");
        dto3.setProdGenericRefNum("PGRN3");
        dto3.setOrderNum(BigInteger.valueOf(3));
        dto3.setNarcoticCode("3");
        dto3.setStateNum(3);
        dto3.setOrderInvoiceNumber("OIN3");
        dto3.setStateCsProductSeq(3L);
        dto3.setScpProdDea((short) 3);
        dto3.setGpiClassId("PGRN3");
        list.add(dto3);

        return list;
    }

    /**
     * Create an instance of CRxDefaults.
     *
     * @return the CRxDefaults instance
     */
    private static CRxDefaults getRxDefaults() {
        return new CRxDefaults((short) 1, "otcAsRxPrecheck1",
                "precheckAll1", "pwoPrecheck1", "Y");
    }
}
