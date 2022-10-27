package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.TestsHelper;
import com.humana.pharmacy.dto.AnsRxSanityResult;
import com.humana.pharmacy.common.cache.model.CWorkflowQueue;
import com.humana.pharmacy.common.service.impl.FunctionsServiceImpl;
import com.humana.pharmacy.dto.AnsRxEScriptDTO;
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
import java.sql.Timestamp;
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
 * Unit test cases for RxSanityServiceImpl;
 */
@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
public class AnsRxSanityServiceImplTest {
    /**
     * The number '400 * 24 * 60 * 60 * 1000'.
     */
    private static final long GREATER_THAN_ONE_YEAR = 400L * 24 * 60 * 60 * 1000;

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
     * The FunctionsServiceImpl instance.
     */
    private FunctionsServiceImpl functionsService;

    /**
     * The AnsRxSanityServiceImpl instance.
     */
    private AnsRxSanityServiceImpl ansRxSanityService;

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
    private final String[] tupleData = new String[]{"PGRN1", "type1", "num1", "PGRN1", "type2", "num2", "dispensedDrugDaw1"};

    /**
     * The tuple.
     */
    @Mock
    private Tuple tuple;

    /**
     * The AnsRxEScriptDTO list.
     */
    private List<AnsRxEScriptDTO> ansRxEScriptDTO;

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
        ansRxSanityService = new AnsRxSanityServiceImpl(epostrxDataSource, workflowDataSource);
        ansRxSanityService.setFunctionsService(functionsService);

        TestsHelper.setFieldValue(ansRxSanityService, "epostrxQueryFactory", epostrxQueryFactory);
        TestsHelper.setFieldValue(ansRxSanityService, "workflowQueryFactory", workflowQueryFactory);

        ansRxEScriptDTO = getAnsRxEScriptDTOs();
    }

    private void setUpMock(boolean workflowMock) {
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);

        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
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
        assertThrows(RuntimeException.class, () -> new RxSanityServiceImpl(null, workflowDataSource));
    }

    @Test
    public void test_ctor_workflowDataSourceNull() {
        assertThrows(RuntimeException.class, () -> new RxSanityServiceImpl(epostrxDataSource, null));
    }

    @Test
    public void test_checkAnsRxSanity_scriptIdNull() {
        assertThrows(RuntimeException.class, () -> ansRxSanityService.checkAnsRxSanity(null, null));
    }

    @Test
    public void test_checkAnsRxSanity_functionsServiceNull() {
        ansRxSanityService = new AnsRxSanityServiceImpl(epostrxDataSource, workflowDataSource);
        assertThrows(RuntimeException.class, () -> ansRxSanityService.checkAnsRxSanity(scriptId, null));
    }

    @Test
    public void test_checkAnsRxSanity_scriptIdNotFound() {
        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(new ArrayList<>(), new ArrayList<>(), Arrays.asList(tuple));

        scriptId = BigInteger.valueOf(9999);
        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, "0");
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("Rx SIG or GPI does not match the prechecked Rx."));
        assertTrue(res.toErrorMessage().contains(
                "RX NOT PAID!! CURRENT STATUS = (?Unknown; [A = Accepted Reversal, R = Rejected, D = Duplicate Paid (need to verify the claim is Paid)"));
    }

    @Test
    public void test_checkAnsRxSanity_SigOrGpiNotMatch1() {
        ansRxEScriptDTO.get(0).setRxNumber(null);
        ansRxEScriptDTO.get(1).setRxNumber(null);

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("Rx SIG or GPI does not match the prechecked Rx."));
    }

    @Test
    public void test_checkAnsRxSanity_SigOrGpiNotMatch2() {
        ansRxEScriptDTO.get(0).setFillPrecheckDatetime(null);
        ansRxEScriptDTO.get(1).setFillPrecheckDatetime(null);

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("Rx SIG or GPI does not match the prechecked Rx."));
    }

    @Test
    public void test_checkAnsRxSanity_SigOrGpiNotMatch3() {
        ansRxEScriptDTO.get(0).setRxNumber("XX");
        ansRxEScriptDTO.get(0).setDispensedDrugSig(null);
        ansRxEScriptDTO.get(1).setDispensedDrugSig(null);

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, "XX");
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("Rx SIG or GPI does not match the prechecked Rx."));
    }

    @Test
    public void test_checkAnsRxSanity_ControlledFillsExceeded1() {
        ansRxEScriptDTO.get(0).setProdDea((byte) 0);
        ansRxEScriptDTO.get(0).setOriginalNumRefills(0L);
        ansRxEScriptDTO.get(1).setEScriptMsgAttributeSeq(ansRxEScriptDTO.get(0).getEScriptMsgAttributeSeq());
        ansRxEScriptDTO.get(1).setFillStatusNum((byte) 9);
        ansRxEScriptDTO.get(1).setProdDea((byte) 1);
        ansRxEScriptDTO.get(1).setOriginalNumRefills(0L);

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("Controlled substance fill count exceeds # of refills written."));
    }

    @Test
    public void test_checkAnsRxSanity_ControlledFillsExceeded2() {
        ansRxEScriptDTO.get(0).setStateProdDea((short) 1);
        ansRxEScriptDTO.get(0).setOriginalNumRefills(0L);
        ansRxEScriptDTO.get(1).setEScriptMsgAttributeSeq(ansRxEScriptDTO.get(0).getEScriptMsgAttributeSeq());
        ansRxEScriptDTO.get(1).setFillStatusNum((byte) 9);
        ansRxEScriptDTO.get(1).setProdDea((byte) 1);
        ansRxEScriptDTO.get(1).setOriginalNumRefills(null);

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("Controlled substance fill count exceeds # of refills written."));
    }

    @Test
    public void test_checkAnsRxSanity_ControlledFillsExceeded3() {
        ansRxEScriptDTO.get(0).setProdDea((byte) 1);
        ansRxEScriptDTO.get(0).setOriginalNumRefills(100L);
        ansRxEScriptDTO.get(0).setStateProdDea((short) 0);
        ansRxEScriptDTO.get(1).setEScriptMsgAttributeSeq(ansRxEScriptDTO.get(0).getEScriptMsgAttributeSeq());
        ansRxEScriptDTO.get(1).setFillStatusNum((byte) 9);
        ansRxEScriptDTO.get(1).setStateProdDea((short) 1);
        ansRxEScriptDTO.get(1).setOriginalNumRefills(0L);

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("Controlled substance fill count exceeds # of refills written."));
    }

    @Test
    public void test_checkAnsRxSanity_ControlledReillsMoreThanFive() {
        ansRxEScriptDTO.get(0).setProdDea((byte) 1);
        ansRxEScriptDTO.get(0).setNumRefills(6L);

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("Controlled substance has been refilled [6] times. Should be less than 6."));
    }

    @Test
    public void test_checkAnsRxSanity_RxNotActive() {
        ansRxEScriptDTO.get(0).setRxStatusCodeNum((byte) 8);

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn("rx_status1");

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("Rx is not active, current status is [rx_status1]."));
    }

    @Test
    public void test_checkAnsRxSanity_PrescriberEpxEligible() {
        ansRxEScriptDTO.get(0).setEdiTransactionCode("REFILLRX");
        ansRxEScriptDTO.get(0).setPrescriberAddress("1");

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains(
                "Prescriber is ERX eligible;no prescriber address attached to script.  Please edit the prescription and select a prescriber address."));
    }

    @Test
    public void test_checkAnsRxSanity_RxNotPaid1() {
        ansRxEScriptDTO.remove(2);
        ansRxEScriptDTO.remove(1);
        ansRxEScriptDTO.get(0).setPpTypeCode("A");

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains(
                "RX NOT PAID!! CURRENT STATUS = (?Unknown; [A = Accepted Reversal, R = Rejected, D = Duplicate Paid (need to verify the claim is Paid)"));
    }

    @Test
    public void test_checkAnsRxSanity_RxNotPaid2() {
        ansRxEScriptDTO.get(0).setRxStatusCodeNum((byte) 8);

        ansRxEScriptDTO.remove(2);
        ansRxEScriptDTO.remove(1);
        ansRxEScriptDTO.get(0).setPpTypeCode("A");
        ansRxEScriptDTO.get(0).setEcsResponseStatusType("A");

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn("rx_status1");

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains(
                "RX NOT PAID!! CURRENT STATUS = (A; [A = Accepted Reversal, R = Rejected, D = Duplicate Paid (need to verify the claim is Paid)"));
        assertTrue(res.toErrorMessage().contains("Rx is not active, current status is [rx_status1]."));
    }

    @Test
    public void test_checkAnsRxSanity_RxNotPaid3() {
        ansRxEScriptDTO.remove(2);
        ansRxEScriptDTO.remove(1);
        ansRxEScriptDTO.get(0).setPpTypeCode("A");
        ansRxEScriptDTO.get(0).setEcsResponseStatusType("P");

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains(
                "RX NOT PAID!! CURRENT STATUS = (?Unknown; [A = Accepted Reversal, R = Rejected, D = Duplicate Paid (need to verify the claim is Paid)"));
    }

    @Test
    public void test_checkAnsRxSanity_WrittenPastDate() {
        ansRxEScriptDTO.remove(2);
        ansRxEScriptDTO.remove(1);
        ansRxEScriptDTO.get(0).setWrittenDate(new Timestamp(System.currentTimeMillis() - GREATER_THAN_ONE_YEAR));

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("FILLING > 1-YEAR PAST WRITTEN DATE"));
    }

    @Test
    public void test_checkAnsRxSanity_WrittenFutureDate() {
        ansRxEScriptDTO.remove(2);
        ansRxEScriptDTO.remove(1);
        ansRxEScriptDTO.get(0).setWrittenDate(new Timestamp(System.currentTimeMillis() + GREATER_THAN_ONE_YEAR));

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("DATE WRITTEN IS A FUTURE DATE"));
    }

    @Test
    public void test_checkAnsRxSanity_RequiresSsnOnFile1() {
        ansRxEScriptDTO.remove(2);
        ansRxEScriptDTO.remove(1);
        ansRxEScriptDTO.get(0).setShipState("KY");
        ansRxEScriptDTO.get(0).setProdDea((byte) 2);
        ansRxEScriptDTO.get(0).setPatientIdCode(null);

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("State Req(KY): requires SSN on-file for Controls."));
    }

    @Test
    public void test_checkAnsRxSanity_RequiresSsnOnFile2() {
        ansRxEScriptDTO.remove(2);
        ansRxEScriptDTO.remove(1);
        ansRxEScriptDTO.get(0).setShipState("KY");
        ansRxEScriptDTO.get(0).setProdDea((byte) 3);
        ansRxEScriptDTO.get(0).setPatientIdCode("");

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("State Req(KY): requires SSN on-file for Controls."));
    }

    @Test
    public void test_checkAnsRxSanity_RequiresSsnOnFile3() {
        ansRxEScriptDTO.remove(2);
        ansRxEScriptDTO.remove(1);
        ansRxEScriptDTO.get(0).setShipState("KY");
        ansRxEScriptDTO.get(0).setStateProdDea((short) 3);
        ansRxEScriptDTO.get(0).setPatientIdCode("02");
        ansRxEScriptDTO.get(0).setAdditionalPatientIdTypeCode("01");

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("State Req(KY): requires SSN on-file for Controls."));
    }

    @Test
    public void test_checkAnsRxSanity_RequiresSsnOnFile4() {
        ansRxEScriptDTO.remove(2);
        ansRxEScriptDTO.remove(1);
        ansRxEScriptDTO.get(0).setShipState("KY");
        ansRxEScriptDTO.get(0).setStateProdDea((short) 3);
        ansRxEScriptDTO.get(0).setPatientIdCode("02");
        ansRxEScriptDTO.get(0).setAdditionalPatientIdTypeCode("02");
        ansRxEScriptDTO.get(0).setAdditionalPatientId("9xxx");

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("State Req(KY): requires SSN on-file for Controls."));
    }

    @Test
    public void test_checkAnsRxSanity_RequiresSsnOnFile5() {
        ansRxEScriptDTO.remove(2);
        ansRxEScriptDTO.remove(1);
        ansRxEScriptDTO.get(0).setShipState("KY");
        ansRxEScriptDTO.get(0).setStateProdDea((short) 3);
        ansRxEScriptDTO.get(0).setPatientIdCode("01");
        ansRxEScriptDTO.get(0).setPatientId(null);

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("State Req(KY): requires SSN on-file for Controls."));
    }

    @Test
    public void test_checkAnsRxSanity_RequiresSsnOnFile6() {
        ansRxEScriptDTO.remove(2);
        ansRxEScriptDTO.remove(1);
        ansRxEScriptDTO.get(0).setShipState("KY");
        ansRxEScriptDTO.get(0).setStateProdDea((short) 3);
        ansRxEScriptDTO.get(0).setPatientIdCode("01");
        ansRxEScriptDTO.get(0).setPatientId("");

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("State Req(KY): requires SSN on-file for Controls."));
    }

    @Test
    public void test_checkAnsRxSanity_RequiresSsnOnFile7() {
        ansRxEScriptDTO.remove(2);
        ansRxEScriptDTO.remove(1);
        ansRxEScriptDTO.get(0).setShipState("KY");
        ansRxEScriptDTO.get(0).setStateProdDea((short) 3);
        ansRxEScriptDTO.get(0).setPatientIdCode("01");
        ansRxEScriptDTO.get(0).setPatientId("9xxxx");

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("State Req(KY): requires SSN on-file for Controls."));
    }

    @Test
    public void test_checkAnsRxSanity_RequiresSsnOnFile8() {
        ansRxEScriptDTO.remove(2);
        ansRxEScriptDTO.remove(1);
        ansRxEScriptDTO.get(0).setShipState("KY");
        ansRxEScriptDTO.get(0).setStateProdDea((short) 3);
        ansRxEScriptDTO.get(0).setPatientIdCode("02");
        ansRxEScriptDTO.get(0).setAdditionalPatientIdTypeCode("01");
        ansRxEScriptDTO.get(0).setAdditionalPatientId("9xxx");

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("State Req(KY): requires SSN on-file for Controls."));
    }

    @Test
    public void test_checkAnsRxSanity_FlEpilepticSubstition1() {
        scriptId = ansRxEScriptDTO.get(2).getEScriptMsgAttributeSeq();
        ansRxEScriptDTO.get(0).setShipState("FL");
        ansRxEScriptDTO.get(0).setProdGenericRefNum("72xxx");
        ansRxEScriptDTO.get(0).setOrderLineStatusNum((byte) 0);
        ansRxEScriptDTO.get(1).setShipState("FL");
        ansRxEScriptDTO.get(1).setProdGenericRefNum("72xxx");
        ansRxEScriptDTO.get(1).setFillStatusNum((byte) 2);
        ansRxEScriptDTO.get(2).setShipState("FL");
        ansRxEScriptDTO.get(2).setProdGenericRefNum("72xxx");
        ansRxEScriptDTO.get(2).setOrderLineStatusNum((byte) 1);
        ansRxEScriptDTO.get(2).setRxNumber(rxNumber);

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, rxNumber);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("State Req(FL): Epileptic Substition, requires patient/doctor contact."));
    }

    @Test
    public void test_checkAnsRxSanity_FlEpilepticSubstition2() {
        scriptId = ansRxEScriptDTO.get(2).getEScriptMsgAttributeSeq();
        ansRxEScriptDTO.get(0).setShipState("FL");
        ansRxEScriptDTO.get(0).setProdGenericRefNum("72xxx");
        ansRxEScriptDTO.get(0).setOrderLineStatusNum((byte) 0);
        ansRxEScriptDTO.get(0).setEScriptMsgAttributeSeq(BigInteger.valueOf(999));
        ansRxEScriptDTO.get(1).setShipState("FL");
        ansRxEScriptDTO.get(1).setProdGenericRefNum("72xxxxxxxx");
        ansRxEScriptDTO.get(1).setFillStatusNum((byte) 2);
        ansRxEScriptDTO.get(2).setShipState("FL");
        ansRxEScriptDTO.get(2).setProdGenericRefNum("72yyyyyyyy");
        ansRxEScriptDTO.get(2).setOrderLineStatusNum((byte) 1);
        ansRxEScriptDTO.get(2).setRxNumber(rxNumber);

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, rxNumber);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("State Req(FL): Epileptic Substition, requires patient/doctor contact."));
    }

    @Test
    public void test_checkAnsRxSanity_FlEpilepticSubstition3() {
        scriptId = ansRxEScriptDTO.get(2).getEScriptMsgAttributeSeq();
        ansRxEScriptDTO.get(0).setShipState("FL");
        ansRxEScriptDTO.get(0).setProdGenericRefNum("72xxx");
        ansRxEScriptDTO.get(0).setOrderLineStatusNum((byte) 0);
        ansRxEScriptDTO.get(0).setRxNumber(null);
        ansRxEScriptDTO.get(1).setShipState("FL");
        ansRxEScriptDTO.get(1).setProdGenericRefNum("72xxx");
        ansRxEScriptDTO.get(1).setProdNumber("prodNumxxxxxx");
        ansRxEScriptDTO.get(1).setFillStatusNum((byte) 2);
        ansRxEScriptDTO.get(2).setShipState("FL");
        ansRxEScriptDTO.get(2).setProdGenericRefNum("72xxx");
        ansRxEScriptDTO.get(2).setProdNumber("prodNumyyyyyy");
        ansRxEScriptDTO.get(2).setOrderLineStatusNum((byte) 1);
        ansRxEScriptDTO.get(2).setRxNumber(rxNumber);

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, rxNumber);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("State Req(FL): Epileptic Substition, requires patient/doctor contact."));
    }

    @Test
    public void test_checkAnsRxSanity_ControlledRefillAttempt() {
        ansRxEScriptDTO.get(0).setEdiTransactionCode("NEWRX-2");
        ansRxEScriptDTO.get(0).setProdDea((byte) 2);

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("Controlled Substance Refill Attempt"));
    }

    @Test
    public void test_checkAnsRxSanity_GpiNotEquivilent() {
        tupleData[3] = "PGRN2";

        setUpMock(true);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("DISPENSE GPI: [PGRN1]; WRITTEN GPI: [PGRN2] NOT EQUIVILENT."));
        assertEquals("DR. CALL RPH", res.getOverrideQname());
        assertEquals(1, res.getOverrideQueue());
    }

    @Test
    public void test_checkAnsRxSanity_AlertWoSpecialTeeCodes() {
        tupleData[0] = "39400010100320";
        tupleData[1] = "X";
        tupleData[3] = "39400010100320";
        tupleData[6] = "0";

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("ALERT QUALITY - subbing issue - GPI w/o Special Tee Codes"));
    }

    @Test
    public void test_checkAnsRxSanity_AlertWSpecialTeeCodes1() {
        tupleData[0] = "34000003100330";
        tupleData[1] = "T";
        tupleData[3] = "34000003100330";
        tupleData[4] = "T";
        tupleData[6] = "0";

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("ALERT QUALITY - subbing issue - GPI w/ Special Tee Codes"));
    }

    @Test
    public void test_checkAnsRxSanity_AlertWSpecialTeeCodes2() {
        tupleData[0] = "34000003100330";
        tupleData[1] = "T";
        tupleData[3] = "34000003100330";
        tupleData[4] = "G";
        tupleData[6] = "0";

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("ALERT QUALITY - subbing issue - GPI w/ Special Tee Codes"));
    }

    @Test
    public void test_checkAnsRxSanity_AlertDawDrug() {
        tupleData[0] = "83200030200315";
        tupleData[1] = "G";
        tupleData[3] = "83200030200315";
        tupleData[4] = "T";
        tupleData[6] = "1";

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("ALERT QUALITY - subbing issue - DAW 1,2 Drug"));
    }

    @Test
    public void test_checkAnsRxSanity_AlertDaw8Drug() {
        tupleData[1] = "G";
        tupleData[2] = "100525804900315";
        tupleData[4] = "T";
        tupleData[5] = "00525804900315";
        tupleData[6] = "8";

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("ALERT QUALITY - subbing issue - GPI with DAW 8 Drug"));
    }

    @Test
    public void test_checkAnsRxSanity_UnresolvedDur() {
        ansRxEScriptDTO.get(0).setDurResolveDate(null);
        ansRxEScriptDTO.get(0).setOrderNum(new BigInteger("1"));
        ansRxEScriptDTO.get(0).setRfdScriptId(new BigInteger("1"));

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("Unresolved DUR found during RX Sanity Check 1.28."));
    }

    @Test
    public void test_checkAnsRxSanity_UnresolvedDur2() {
        ansRxEScriptDTO.get(0).setDurResolveDate(null);
        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);

        assertFalse(res.toErrorMessage().contains("Unresolved DUR found during RX Sanity Check 1.28."));
    }

    @Test
    public void test_checkAnsRxSanity_UnresolvedDur3() {
        ansRxEScriptDTO.get(0).setDurResolveDate(null);
        ansRxEScriptDTO.get(0).setOrderNum(new BigInteger("1"));

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);

        assertFalse(res.toErrorMessage().contains("Unresolved DUR found during RX Sanity Check 1.28."));
    }

    @Test
    public void test_checkAnsRxSanity_HiCompliance1() {
        ansRxEScriptDTO.get(0).setShipState("HI");
        ansRxEScriptDTO.get(0).setProdDea((byte) 2);

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("State Req (HI): Federal and State Specific Control shipping to HI must have HI prescriber address."));
    }

    @Test
    public void test_checkAnsRxSanity_HiCompliance2() {
        ansRxEScriptDTO.get(0).setShipState("HI");
        ansRxEScriptDTO.get(0).setStateProdDea((short) 1);
        ansRxEScriptDTO.get(0).setTpAddrSeq(1L);
        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains("State Req (HI): Federal and State Specific Control shipping to HI must have HI prescriber address."));
    }

    @Test
    public void test_checkAnsRxSanity_HiCompliance3() {
        ansRxEScriptDTO.get(0).setShipState("HI");
        ansRxEScriptDTO.get(0).setStateProdDea((short) 1);
        ansRxEScriptDTO.get(0).setTpAddrSeq(1L);
        ansRxEScriptDTO.get(0).setHiPrescriberAddress("1");
        ansRxEScriptDTO.get(0).setPatientIdCode(null);

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains(
                "State Req (HI): requires Driver''s License, State ID, or Passport number on-file for Federal and State Specific Controls."));
    }

    @Test
    public void test_checkAnsRxSanity_HiCompliance4() {
        ansRxEScriptDTO.get(0).setShipState("HI");
        ansRxEScriptDTO.get(0).setStateProdDea((short) 1);
        ansRxEScriptDTO.get(0).setTpAddrSeq(1L);
        ansRxEScriptDTO.get(0).setHiPrescriberAddress("1");
        ansRxEScriptDTO.get(0).setPatientIdCode("03");
        ansRxEScriptDTO.get(0).setAdditionalPatientIdTypeCode("03");

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains(
                "State Req (HI): requires Driver''s License, State ID, or Passport number on-file for Federal and State Specific Controls."));
    }

    @Test
    public void test_checkAnsRxSanity_HiCompliance5() {
        ansRxEScriptDTO.get(0).setShipState("HI");
        ansRxEScriptDTO.get(0).setStateProdDea((short) 1);
        ansRxEScriptDTO.get(0).setTpAddrSeq(1L);
        ansRxEScriptDTO.get(0).setHiPrescriberAddress("1");
        ansRxEScriptDTO.get(0).setPatientIdCode("03");
        ansRxEScriptDTO.get(0).setAdditionalPatientIdTypeCode("05");

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains(
                "State Req (HI): requires Driver''s License, State ID, or Passport number on-file for Federal and State Specific Controls."));
    }

    @Test
    public void test_checkAnsRxSanity_HiCompliance6() {
        ansRxEScriptDTO.get(0).setShipState("HI");
        ansRxEScriptDTO.get(0).setStateProdDea((short) 1);
        ansRxEScriptDTO.get(0).setTpAddrSeq(1L);
        ansRxEScriptDTO.get(0).setHiPrescriberAddress("1");
        ansRxEScriptDTO.get(0).setPatientIdCode("02");
        ansRxEScriptDTO.get(0).setPatientId(null);

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertTrue(res.getErrors().size() > 0);

        assertTrue(res.toErrorMessage().contains(
                "State Req (HI): requires Driver''s License, State ID, or Passport number on-file for Federal and State Specific Controls."));
    }

    @Test
    public void test_checkAnsRxSanity_NoError1() {
        ansRxEScriptDTO.get(1).setDispensedDrugSig(ansRxEScriptDTO.get(0).getDispensedDrugSig());
        ansRxEScriptDTO.get(1).setProdGenericRefNum(ansRxEScriptDTO.get(0).getProdGenericRefNum());

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO.subList(0, 2), Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxSanity_NoError2() {
        ansRxEScriptDTO.get(0).setProdDea((byte) 1);
        ansRxEScriptDTO.get(0).setOriginalNumRefills(null);
        ansRxEScriptDTO.get(0).setStateProdDea((short) 1);
        ansRxEScriptDTO.get(0).setEdiTransactionCode("NEWRX");
        ansRxEScriptDTO.get(1).setEScriptMsgAttributeSeq(ansRxEScriptDTO.get(0).getEScriptMsgAttributeSeq());
        ansRxEScriptDTO.get(1).setFillStatusNum((byte) 9);
        ansRxEScriptDTO.get(1).setStateProdDea((short) 1);
        ansRxEScriptDTO.get(1).setOriginalNumRefills(100L);

        ansRxEScriptDTO.get(0).setShipState("FL");
        ansRxEScriptDTO.get(0).setProdGenericRefNum("72xxx");
        ansRxEScriptDTO.get(1).setProdGenericRefNum("73xxx");

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO.subList(0, 2), Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxSanity_NoError3() {
        ansRxEScriptDTO.get(0).setProdDea((byte) 1);
        ansRxEScriptDTO.get(0).setNumRefills(5L);

        ansRxEScriptDTO.get(0).setEdiTransactionCode("REFILLRX");
        ansRxEScriptDTO.get(0).setTpAddrSeq(1L);

        ansRxEScriptDTO.get(0).setShipState("FL");
        ansRxEScriptDTO.get(0).setProdGenericRefNum("73xxx");

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO.subList(0, 1), Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxSanity_NoError4() {
        ansRxEScriptDTO.get(0).setRxStatusCodeNum((byte) 8);
        ansRxEScriptDTO.get(1).setRxStatusCodeNum((byte) 7);

        ansRxEScriptDTO.get(0).setEdiTransactionCode("REFILLRX");

        ansRxEScriptDTO.get(0).setShipState("FL");
        ansRxEScriptDTO.get(0).setProdGenericRefNum(null);

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO.subList(0, 1), Arrays.asList(tuple));
        when(query.fetchFirst()).thenReturn(null);

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxSanity_NoError5() {
        ansRxEScriptDTO.get(0).setEdiTransactionCode("REFILLRX");

        ansRxEScriptDTO.get(0).setShipState("KY");
        ansRxEScriptDTO.get(0).setProdDea((byte) 9);

        tupleData[0] = "39400010100320";
        tupleData[1] = "X";
        tupleData[3] = "39400010100320";
        tupleData[4] = "X";
        tupleData[6] = "2";

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO.subList(0, 1), Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxSanity_NoError6() {
        ansRxEScriptDTO.remove(2);
        ansRxEScriptDTO.remove(1);
        ansRxEScriptDTO.get(0).setPpTypeCode("A");
        ansRxEScriptDTO.get(0).setEcsResponseStatusType("P");

        ansRxEScriptDTO.get(0).setShipState("KY");
        ansRxEScriptDTO.get(0).setProdDea((byte) 9);
        ansRxEScriptDTO.get(0).setStateProdDea((short) 9);

        tupleData[0] = "34000003100330";
        tupleData[1] = "X";
        tupleData[3] = "34000003100330";
        tupleData[4] = "X";
        tupleData[6] = "2";

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO.subList(0, 1), Arrays.asList(tuple));
        when(query.fetchCount()).thenReturn(1L);

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxSanity_NoError7() {
        ansRxEScriptDTO.remove(2);
        ansRxEScriptDTO.remove(1);
        ansRxEScriptDTO.get(0).setWrittenDate(new Timestamp(System.currentTimeMillis() - 24L * 60 * 60 * 1000));

        ansRxEScriptDTO.get(0).setShipState("KY");
        ansRxEScriptDTO.get(0).setStateProdDea((short) 3);
        ansRxEScriptDTO.get(0).setPatientIdCode("01");
        ansRxEScriptDTO.get(0).setPatientId("8xxx");

        tupleData[0] = "28100010100310";
        tupleData[1] = "X";
        tupleData[3] = "28100010100310";
        tupleData[4] = "T";
        tupleData[6] = "3";

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO.subList(0, 1), Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxSanity_NoError8() {
        ansRxEScriptDTO.remove(2);
        ansRxEScriptDTO.remove(1);
        ansRxEScriptDTO.get(0).setShipState("KY");
        ansRxEScriptDTO.get(0).setStateProdDea((short) 3);
        ansRxEScriptDTO.get(0).setPatientIdCode("02");
        ansRxEScriptDTO.get(0).setAdditionalPatientIdTypeCode("01");
        ansRxEScriptDTO.get(0).setAdditionalPatientId("8xxx");

        tupleData[0] = "28100010100310";
        tupleData[1] = "X";
        tupleData[3] = "28100010100310";
        tupleData[4] = "T";
        tupleData[6] = "2";

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxSanity_NoError9() {
        scriptId = ansRxEScriptDTO.get(2).getEScriptMsgAttributeSeq();
        ansRxEScriptDTO.get(0).setShipState("FL");
        ansRxEScriptDTO.get(0).setProdGenericRefNum("72xxx");
        ansRxEScriptDTO.get(0).setOrderLineStatusNum((byte) 0);
        ansRxEScriptDTO.get(0).setEScriptMsgAttributeSeq(BigInteger.valueOf(999));
        ansRxEScriptDTO.get(1).setShipState("FL");
        ansRxEScriptDTO.get(1).setProdGenericRefNum("72xxx");
        ansRxEScriptDTO.get(1).setFillStatusNum((byte) 2);
        ansRxEScriptDTO.get(2).setShipState("FL");
        ansRxEScriptDTO.get(2).setProdGenericRefNum("72xxx");
        ansRxEScriptDTO.get(2).setOrderLineStatusNum((byte) 1);
        ansRxEScriptDTO.get(2).setProdNumber(ansRxEScriptDTO.get(1).getProdNumber());
        ansRxEScriptDTO.get(2).setRxNumber(rxNumber);

        tupleData[1] = "G";
        tupleData[2] = "100525804900315";
        tupleData[4] = "T";
        tupleData[5] = "00525804900315";
        tupleData[6] = "9";

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, rxNumber);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxSanity_NoError10() {
        scriptId = ansRxEScriptDTO.get(2).getEScriptMsgAttributeSeq();
        ansRxEScriptDTO.get(0).setShipState("FL");
        ansRxEScriptDTO.get(0).setProdGenericRefNum("72xxx");
        ansRxEScriptDTO.get(0).setOrderLineStatusNum((byte) 0);
        ansRxEScriptDTO.get(0).setFillStatusNum((byte) 1);
        ansRxEScriptDTO.get(0).setRxNumber("XX");
        ansRxEScriptDTO.get(1).setShipState("FL");
        ansRxEScriptDTO.get(1).setProdGenericRefNum("72xxx");
        ansRxEScriptDTO.get(1).setFillStatusNum((byte) 2);
        ansRxEScriptDTO.get(1).setConversionLink("link1");
        ansRxEScriptDTO.get(2).setShipState("FL");
        ansRxEScriptDTO.get(2).setProdGenericRefNum("72xxx");
        ansRxEScriptDTO.get(2).setOrderLineStatusNum((byte) 1);
        ansRxEScriptDTO.get(2).setProdNumber(ansRxEScriptDTO.get(1).getProdNumber());
        ansRxEScriptDTO.get(2).setRxNumber(rxNumber);

        tupleData[1] = "G";
        tupleData[2] = "100525804900315";
        tupleData[4] = "T";
        tupleData[5] = "100525804900315";
        tupleData[6] = "8";

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO, Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, rxNumber);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxSanity_NoError11() {
        tupleData[1] = "G";
        tupleData[2] = "00525804900315";
        tupleData[4] = "T";
        tupleData[5] = "00525804900315";
        tupleData[6] = "8";

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO.subList(0, 1), Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxSanity_NoError12() {
        ansRxEScriptDTO.get(0).setShipState("HI");
        ansRxEScriptDTO.get(0).setHiPrescriberAddress("1");
        ansRxEScriptDTO.get(0).setStateProdDea((short) 1);
        ansRxEScriptDTO.get(0).setTpAddrSeq(1L);
        ansRxEScriptDTO.get(0).setPatientIdCode("04");
        ansRxEScriptDTO.get(0).setPatientId("id");

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO.subList(0, 1), Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertNull(res);
    }

    @Test
    public void test_checkAnsRxSanity_NoError13() {
        ansRxEScriptDTO.get(0).setShipState("HI");
        ansRxEScriptDTO.get(0).setHiPrescriberAddress("1");
        ansRxEScriptDTO.get(0).setStateProdDea((short) 1);
        ansRxEScriptDTO.get(0).setTpAddrSeq(1L);
        ansRxEScriptDTO.get(0).setPatientIdCode("04");
        ansRxEScriptDTO.get(0).setPatientId("");
        ansRxEScriptDTO.get(0).setAdditionalPatientIdTypeCode("02");
        ansRxEScriptDTO.get(0).setAdditionalPatientId("id");

        setUpMock(false);

        when(epostrxQueryFactory.select(new Expression[]{any(Expression.class)})).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        buildTuple(tupleData);
        when(query.fetch()).thenReturn(ansRxEScriptDTO, ansRxEScriptDTO.subList(0, 1), Arrays.asList(tuple));

        AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, null);
        assertNull(res);
    }

    /**
     * Build the tuple.
     *
     * @param values the values.
     */
    private void buildTuple(String[] values) {
        for (int i = 0; i < values.length; i++) {
            when(tuple.get(i, String.class)).thenReturn(values[i]);
        }
    }

    /**
     * Get AnsRxEScriptDTO list.
     *
     * @return the AnsRxEScriptDTO list.
     */
    private List<AnsRxEScriptDTO> getAnsRxEScriptDTOs() {
        List<AnsRxEScriptDTO> list = new ArrayList<>();

        AnsRxEScriptDTO dto1 = new AnsRxEScriptDTO();
        dto1.setEScriptMsgAttributeSeq(BigInteger.valueOf(1));
        dto1.setRxNumber("rxn1");
        dto1.setFillPrecheckDatetime(new Timestamp(System.currentTimeMillis()));
        dto1.setDispensedDrugSig("sig1");
        dto1.setProdGenericRefNum("PGRN1");
        dto1.setOrderLineStatusNum((byte) 1);
        dto1.setFillStatusNum((byte) 2);
        dto1.setProdNumber("prodNum1");
        dto1.setDurResolveDate(new Timestamp(System.currentTimeMillis()));
        dto1.setPpTypeCode("B");
        list.add(dto1);

        AnsRxEScriptDTO dto2 = new AnsRxEScriptDTO();
        dto2.setEScriptMsgAttributeSeq(BigInteger.valueOf(2));
        dto2.setRxNumber("rxn1");
        dto2.setFillPrecheckDatetime(new Timestamp(System.currentTimeMillis() + 1000));
        dto2.setDispensedDrugSig("sig2");
        dto2.setProdGenericRefNum("PGRN2");
        dto2.setProdNumber("prodNum2");
        dto2.setDurResolveDate(new Timestamp(System.currentTimeMillis()));
        dto2.setPpTypeCode("B");
        list.add(dto2);

        AnsRxEScriptDTO dto3 = new AnsRxEScriptDTO();
        dto3.setEScriptMsgAttributeSeq(BigInteger.valueOf(3));
        dto3.setRxNumber("rxn3");
        dto3.setFillPrecheckDatetime(new Timestamp(System.currentTimeMillis() + 2000));
        dto3.setDispensedDrugSig("sig3");
        dto3.setProdGenericRefNum("PGRN3");
        dto3.setProdNumber("prodNum3");
        dto3.setDurResolveDate(new Timestamp(System.currentTimeMillis()));
        dto2.setPpTypeCode("B");
        list.add(dto3);

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

        return queues;
    }
}
