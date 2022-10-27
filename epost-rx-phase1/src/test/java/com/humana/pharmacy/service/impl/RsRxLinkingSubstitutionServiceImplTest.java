package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.TestsHelper;
import com.humana.pharmacy.dto.RsRxLinkingSubstitutionDTO;
import com.humana.pharmacy.common.cache.model.CCodeValue;
import com.humana.pharmacy.common.dto.RsMultiLinkDTO;
import com.humana.pharmacy.common.dto.RsRxScriptDTO;
import com.humana.pharmacy.common.service.impl.FunctionsServiceImpl;
import com.querydsl.core.types.Expression;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLInsertClause;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Answers.RETURNS_SELF;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RsRxLinkingSubstitutionServiceImplTest {

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
    @Mock(answer = RETURNS_SELF)
    private SQLQuery query;

    /**
     * The Workflow SQLQuery instance.
     */
    @Mock(answer = RETURNS_SELF)
    private SQLQuery wfQuery;

    /**
     * The SQLInsertClause instance.
     */
    @Mock(answer = RETURNS_SELF)
    private SQLInsertClause insertClause;

    /**
     * The RsRxLinkingSubstitutionServiceImpl instance.
     */
    private RsRxLinkingSubstitutionServiceImpl service;

    /**
     * The FunctionsServiceImpl instance.
     */
    @Mock
    private FunctionsServiceImpl functionsService;

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
        service = new RsRxLinkingSubstitutionServiceImpl(epostrxDataSource, workflowDataSource);
        service.setFunctionsService(functionsService);
        TestsHelper.setFieldValue(service, "epostrxQueryFactory", epostrxQueryFactory);
        TestsHelper.setFieldValue(service, "workflowQueryFactory", workflowQueryFactory);
    }

    @Test
    public void test_ctor_epostrxDataSourceNull() {
        assertThrows(
                RuntimeException.class,
                () -> new RsRxLinkingSubstitutionServiceImpl(null, workflowDataSource));
    }

    @Test
    public void test_ctor_workflowDataSourceNull() {
        assertThrows(
                RuntimeException.class,
                () -> new RsRxLinkingSubstitutionServiceImpl(epostrxDataSource, null));
    }

    @Test
    public void test_checkAnsRxSanity_scriptIdNull() {
        assertThrows(RuntimeException.class, () -> service.getSubEScriptMsgAttributeSeq(null));
    }

    @Test
    public void test_checkAnsRxSanity_functionsServiceNull() {
        service = new RsRxLinkingSubstitutionServiceImpl(epostrxDataSource, workflowDataSource);
        assertThrows(
                RuntimeException.class, () -> service.getSubEScriptMsgAttributeSeq(new BigInteger("111")));
    }

    @Test
    public void getSubEScriptMsgAttributeSeq_newRx() {
        RsRxScriptDTO r1 = new RsRxScriptDTO();
        r1.setEScriptMsgAttributeSeq(new BigInteger("123"));
        RsRxScriptDTO r2 = new RsRxScriptDTO();
        r2.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        RsRxScriptDTO r3 = new RsRxScriptDTO();
        r3.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        r3.setEdiTransactionCode("NEWRX");
        RsRxScriptDTO r4 = new RsRxScriptDTO();
        r4.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        r4.setEdiTransactionCode("NEWRX");
        r4.setRxExpirationDate(new Timestamp(1000L));
        RsRxScriptDTO r5 = new RsRxScriptDTO();
        r5.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        r5.setEdiTransactionCode("NEWRX");
        r5.setRxExpirationDate(Timestamp.valueOf(LocalDateTime.now().plusDays(5L)));
        RsRxScriptDTO r6 = new RsRxScriptDTO();
        r6.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        r6.setEdiTransactionCode("NEWRX");
        r6.setRxExpirationDate(Timestamp.valueOf(LocalDateTime.now().plusDays(5L)));
        r6.setFillDaysSupply(60);
        RsRxScriptDTO r7 = new RsRxScriptDTO();
        r7.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        r7.setEdiTransactionCode("NEWRX");
        r7.setRxExpirationDate(Timestamp.valueOf(LocalDateTime.now().plusDays(5L)));
        r7.setFillDaysSupply(90);

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.fetch()).thenReturn(Arrays.asList(r1, r2, r3, r4, r5, r6, r7));

        assertEquals(
                new BigInteger("8001"), service.getSubEScriptMsgAttributeSeq(new BigInteger("8001")));
    }

    @Test
    public void getSubEScriptMsgAttributeSeq_reFillRx() {
        RsRxScriptDTO r1 = new RsRxScriptDTO();
        r1.setEScriptMsgAttributeSeq(new BigInteger("123"));
        RsRxScriptDTO r2 = new RsRxScriptDTO();
        r2.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        RsRxScriptDTO r3 = new RsRxScriptDTO();
        r3.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        r3.setEdiTransactionCode("REFILLRX");
        RsRxScriptDTO r4 = new RsRxScriptDTO();
        r4.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        r4.setEdiTransactionCode("REFILLRX");
        r4.setRxExpirationDate(new Timestamp(1000L));
        RsRxScriptDTO r5 = new RsRxScriptDTO();
        r5.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        r5.setEdiTransactionCode("REFILLRX");
        r5.setRxExpirationDate(Timestamp.valueOf(LocalDateTime.now().plusDays(5L)));
        RsRxScriptDTO r6 = new RsRxScriptDTO();
        r6.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        r6.setEdiTransactionCode("REFILLRX");
        r6.setRxExpirationDate(Timestamp.valueOf(LocalDateTime.now().plusDays(5L)));
        r6.setFillDaysSupply(60);
        RsRxScriptDTO r7 = new RsRxScriptDTO();
        r7.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        r7.setEdiTransactionCode("REFILLRX");
        r7.setRxExpirationDate(Timestamp.valueOf(LocalDateTime.now().plusDays(5L)));
        r7.setFillDaysSupply(90);
        RsRxScriptDTO r8 = new RsRxScriptDTO();
        r8.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        r8.setEdiTransactionCode("REFILLRX");
        r8.setRxExpirationDate(Timestamp.valueOf(LocalDateTime.now().plusDays(5L)));
        r8.setFillDaysSupply(90);

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.fetch()).thenReturn(Arrays.asList(r1, r2, r3, r4, r5, r6, r7, r8));
        when(functionsService.getRemainingRefills(any(), anyList())).thenReturn(0, 1);

        assertEquals(
                new BigInteger("8001"), service.getSubEScriptMsgAttributeSeq(new BigInteger("8001")));
    }

    @Test
    public void getSubEScriptMsgAttributeSeq_empty_query_result() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.fetch()).thenReturn(new ArrayList());
        assertEquals(
                new BigInteger("8001"), service.getSubEScriptMsgAttributeSeq(new BigInteger("8001")));
    }

    @Test
    public void getSubEScriptMsgAttributeSeq_same_individual() {
        RsRxScriptDTO r1 = new RsRxScriptDTO();
        r1.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        r1.setRxNumber("8001");
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.fetch()).thenReturn(Arrays.asList(r1), new ArrayList());
        RsMultiLinkDTO rml = new RsMultiLinkDTO();
        rml.setChildRxNumber("8001");
        rml.setParentRxNumber("7001");
        when(functionsService.getRsRxLinkingParentChild("8001", "Y")).thenReturn(Arrays.asList(rml));
        assertEquals(
                new BigInteger("8001"), service.getSubEScriptMsgAttributeSeq(new BigInteger("8001")));
    }

    @Disabled
    @Test
    public void getSubEScriptMsgAttributeSeq_isQuantityLowered_1() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class)))
                .thenReturn(query);
        when(epostrxQueryFactory.insert(any())).thenReturn(insertClause);

        RsRxScriptDTO r1 = new RsRxScriptDTO();
        r1.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        r1.setRxNumber("8001");

        Timestamp writtenDate = Timestamp.valueOf(LocalDateTime.now());
        RsRxLinkingSubstitutionDTO rs1 = new RsRxLinkingSubstitutionDTO();
        rs1.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        rs1.setRxNumber("8001");
        rs1.setIsQuantityLowered("Y");
        rs1.setWrittenDate(writtenDate);
        RsRxLinkingSubstitutionDTO rs2 = new RsRxLinkingSubstitutionDTO();
        rs2.setEScriptMsgAttributeSeq(new BigInteger("8002"));
        rs2.setRxNumber("8002");
        rs2.setWrittenDate(writtenDate);
        RsRxLinkingSubstitutionDTO rs3 = new RsRxLinkingSubstitutionDTO();
        rs3.setEScriptMsgAttributeSeq(new BigInteger("8003"));
        rs3.setRxNumber("8003");
        rs3.setFillStatusNum((byte) 4);
        rs3.setRxExpirationDate(new Timestamp(1000L));
        rs3.setWrittenDate(writtenDate);
        RsRxLinkingSubstitutionDTO rs4 = new RsRxLinkingSubstitutionDTO();
        rs4.setEScriptMsgAttributeSeq(new BigInteger("8004"));
        rs4.setRxNumber("8004");
        rs4.setFillStatusNum((byte) 5);
        rs4.setRxExpirationDate(Timestamp.valueOf(LocalDateTime.now().plusDays(3L)));
        rs4.setWrittenDate(writtenDate);
        RsRxLinkingSubstitutionDTO rs5 = new RsRxLinkingSubstitutionDTO();
        rs5.setEScriptMsgAttributeSeq(new BigInteger("8005"));
        rs5.setRxNumber("8005");
        rs5.setFillStatusNum((byte) 5);
        rs5.setRxExpirationDate(Timestamp.valueOf(LocalDateTime.now().plusDays(3L)));
        rs5.setFillDaysSupply(60);
        rs5.setWrittenDate(writtenDate);
        RsRxLinkingSubstitutionDTO rs6 = new RsRxLinkingSubstitutionDTO();
        rs6.setEScriptMsgAttributeSeq(new BigInteger("8006"));
        rs6.setRxNumber("8006");
        rs6.setFillStatusNum((byte) 5);
        rs6.setRxExpirationDate(Timestamp.valueOf(LocalDateTime.now().plusDays(3L)));
        rs6.setFillDaysSupply(90);
        rs6.setWrittenDate(writtenDate);
        RsRxLinkingSubstitutionDTO rs7 = new RsRxLinkingSubstitutionDTO();
        rs7.setEScriptMsgAttributeSeq(new BigInteger("8007"));
        rs7.setRxNumber("8007");
        rs7.setFillStatusNum((byte) 5);
        rs7.setRxExpirationDate(Timestamp.valueOf(LocalDateTime.now().plusDays(3L)));
        rs7.setFillDaysSupply(90);
        rs7.setRxStatusCodeNum((byte) 7);
        rs7.setWrittenDate(writtenDate);
        RsRxLinkingSubstitutionDTO rs8 = new RsRxLinkingSubstitutionDTO();
        rs8.setEScriptMsgAttributeSeq(new BigInteger("8008"));
        rs8.setRxNumber("8008");
        rs8.setFillStatusNum((byte) 5);
        rs8.setRxExpirationDate(Timestamp.valueOf(LocalDateTime.now().plusDays(3L)));
        rs8.setFillDaysSupply(90);
        rs8.setRxStatusCodeNum((byte) 1);
        rs8.setWrittenDate(writtenDate);

        CCodeValue ccValue = new CCodeValue();
        ccValue.setCodeValueKeyword("QTY_CHANGE_FLAG");
        ccValue.setCodeValueId(1);
        ccValue.setStatusFlag("A");
        CCodeValue ccValue2 = new CCodeValue();
        ccValue2.setCodeValueId(2);
        ccValue2.setCodeValueKeyword("TEST");
        ccValue2.setStatusFlag("A");
        CCodeValue ccValue3 = new CCodeValue();
        ccValue3.setCodeValueId(3);
        ccValue3.setCodeValueKeyword("QTY_CHANGE_FLAG");
        ccValue3.setStatusFlag("B");
        when(query.fetch())
                .thenReturn(
                        Arrays.asList(r1),
                        Arrays.asList(rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8),
                        Arrays.asList(ccValue2, ccValue3, ccValue),
                        new ArrayList());
        when(functionsService.getRemainingRefills(anyString(), anyList())).thenReturn(0, 1);
        RsMultiLinkDTO rml = new RsMultiLinkDTO();
        rml.setChildRxNumber("8001");
        rml.setParentRxNumber("7001");
        when(functionsService.getRsRxLinkingParentChild("8001", "Y")).thenReturn(Arrays.asList(rml));
        assertEquals(
                new BigInteger("8001"), service.getSubEScriptMsgAttributeSeq(new BigInteger("8001")));
        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        verify(insertClause)
                .values(any(), any(), any(), any(), any(), any(), any(), any(), ac.capture(), any());
        verify(insertClause).execute();
        assertEquals(
                "Rx number 8001 has been quantity lowered in current or immediate previous fill. So Rx linking substitution would not happen for this fill ",
                ac.getValue());
    }

    @Disabled
    @Test
    public void getSubEScriptMsgAttributeSeq_isQuantityLowered_2() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class)))
                .thenReturn(query);
        when(epostrxQueryFactory.insert(any())).thenReturn(insertClause);

        RsRxScriptDTO r1 = new RsRxScriptDTO();
        r1.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        r1.setRxNumber("8001");

        Timestamp writtenDate = Timestamp.valueOf(LocalDateTime.now().plusDays(3L));
        RsRxLinkingSubstitutionDTO rs0 = new RsRxLinkingSubstitutionDTO();
        rs0.setEScriptMsgAttributeSeq(new BigInteger("8000"));
        rs0.setRxNumber("8001");
        rs0.setIsQuantityLowered("Y");
        rs0.setWrittenDate(writtenDate);

        RsRxLinkingSubstitutionDTO rs1 = new RsRxLinkingSubstitutionDTO();
        rs1.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        rs1.setRxNumber("8001");
        rs1.setWrittenDate(writtenDate);
        rs1.setRxStatusCodeNum((byte) 5);
        rs1.setRxExpirationDate(writtenDate);
        rs1.setDispensedQuantity(0L);

        RsRxLinkingSubstitutionDTO rs2 = new RsRxLinkingSubstitutionDTO();
        rs2.setEScriptMsgAttributeSeq(new BigInteger("8002"));
        rs2.setRxNumber("8002");
        rs2.setFillStatusNum((byte) 4);
        rs2.setRxExpirationDate(writtenDate);
        rs2.setFillDaysSupply(90);
        rs2.setRxStatusCodeNum((byte) 1);
        rs2.setWrittenDate(writtenDate);

        CCodeValue ccValue = new CCodeValue();
        ccValue.setCodeValueKeyword("QTY_CHANGE_FLAG");
        ccValue.setStatusFlag("A");
        when(query.fetch())
                .thenReturn(
                        Arrays.asList(r1),
                        Arrays.asList(rs0, rs1, rs2),
                        Arrays.asList(ccValue),
                        new ArrayList());
        when(functionsService.getRemainingRefills(anyString(), anyList())).thenReturn(1);
        when(functionsService.getRxQtyRemaining(anyString(), anyList())).thenReturn(1L);
        RsMultiLinkDTO rml = new RsMultiLinkDTO();
        rml.setChildRxNumber("8001");
        rml.setParentRxNumber("7001");
        when(functionsService.getRsRxLinkingParentChild("8001", "Y")).thenReturn(Arrays.asList(rml));
        assertEquals(
                new BigInteger("8001"), service.getSubEScriptMsgAttributeSeq(new BigInteger("8001")));
        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        verify(insertClause)
                .values(any(), any(), any(), any(), any(), any(), any(), any(), ac.capture(), any());
        verify(insertClause).execute();
        assertEquals(
                "Rx number 8001 has been quantity lowered in current or immediate previous fill. So Rx linking substitution would not happen for this fill ",
                ac.getValue());
    }

    @Test
    public void getSubEScriptMsgAttributeSeq_isBeenSelected() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class)))
                .thenReturn(query);
        when(epostrxQueryFactory.insert(any())).thenReturn(insertClause);

        RsRxScriptDTO r1 = new RsRxScriptDTO();
        r1.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        r1.setRxNumber("8001");

        Timestamp writtenDate = Timestamp.valueOf(LocalDateTime.now().plusDays(3L));
        RsRxLinkingSubstitutionDTO rs0 = new RsRxLinkingSubstitutionDTO();
        rs0.setEScriptMsgAttributeSeq(new BigInteger("8000"));
        rs0.setRxNumber("8001");
        rs0.setIsQuantityLowered("Y");
        rs0.setWrittenDate(writtenDate);

        RsRxLinkingSubstitutionDTO rs1 = new RsRxLinkingSubstitutionDTO();
        rs1.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        rs1.setRxNumber("8001");
        rs1.setWrittenDate(writtenDate);
        rs1.setRxStatusCodeNum((byte) 5);
        rs1.setRxExpirationDate(writtenDate);
        rs1.setDispensedQuantity(0L);
        rs1.setAllowConsolidate("Y");
        rs1.setProdConsolidate("Y");

        RsRxLinkingSubstitutionDTO rs2 = new RsRxLinkingSubstitutionDTO();
        rs2.setEScriptMsgAttributeSeq(new BigInteger("8002"));
        rs2.setRxNumber("8002");
        rs2.setFillStatusNum((byte) 4);
        rs2.setRxExpirationDate(writtenDate);
        rs2.setFillDaysSupply(90);
        rs2.setRxStatusCodeNum((byte) 1);
        rs2.setWrittenDate(writtenDate);

        RsRxLinkingSubstitutionDTO rs3 = new RsRxLinkingSubstitutionDTO();
        rs3.setEScriptMsgAttributeSeq(new BigInteger("8003"));
        rs3.setRxNumber("8002");
        rs3.setOrderStatusNum((byte) 1);

        RsRxLinkingSubstitutionDTO rs4 = new RsRxLinkingSubstitutionDTO();
        rs4.setEScriptMsgAttributeSeq(new BigInteger("8004"));
        rs4.setRxNumber("8002");
        rs4.setOrderStatusNum((byte) 1);
        rs4.setOrderLineStatusNum((byte) 2);

        RsRxLinkingSubstitutionDTO rs5 = new RsRxLinkingSubstitutionDTO();
        rs5.setEScriptMsgAttributeSeq(new BigInteger("8005"));
        rs5.setRxNumber("8002");
        rs5.setOrderStatusNum((byte) 1);
        rs5.setOrderLineStatusNum((byte) 1);

        RsRxLinkingSubstitutionDTO rs6 = new RsRxLinkingSubstitutionDTO();
        rs6.setEScriptMsgAttributeSeq(new BigInteger("8006"));
        rs6.setRxNumber("8002");
        rs6.setOrderStatusNum((byte) 1);
        rs6.setOrderLineStatusNum((byte) 1);
        rs6.setOhOrderInvoiceNumber("");

        RsRxLinkingSubstitutionDTO rs7 = new RsRxLinkingSubstitutionDTO();
        rs7.setEScriptMsgAttributeSeq(new BigInteger("8007"));
        rs7.setRxNumber("8002");
        rs7.setOrderStatusNum((byte) 1);
        rs7.setOrderLineStatusNum((byte) 1);
        rs7.setOhOrderInvoiceNumber("7");

        CCodeValue ccValue = new CCodeValue();
        ccValue.setCodeValueKeyword("QTY_CHANGE_FLAG");
        ccValue.setStatusFlag("A");
        when(query.fetch())
                .thenReturn(
                        Arrays.asList(r1),
                        Arrays.asList(rs0, rs1, rs2, rs3, rs4, rs5, rs6, rs7),
                        Arrays.asList(ccValue),
                        new ArrayList());
        when(functionsService.getRemainingRefills(anyString(), anyList())).thenReturn(1);
        when(functionsService.getRxQtyRemaining(anyString(), anyList())).thenReturn(1L);
        RsMultiLinkDTO rml = new RsMultiLinkDTO();
        rml.setChildRxNumber("8001");
        rml.setParentRxNumber("7001");
        when(functionsService.getRsRxLinkingParentChild("8001", "Y")).thenReturn(Arrays.asList(rml));
        assertEquals(
                new BigInteger("8001"), service.getSubEScriptMsgAttributeSeq(new BigInteger("8001")));
        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        verify(insertClause)
                .values(any(), any(), any(), any(), any(), any(), any(), any(), ac.capture(), any());
        verify(insertClause).execute();
        assertEquals(
                "Rx number 8002 has already been selected to be filled on order 7 at location null",
                ac.getValue());
    }

    @Test
    public void getSubEScriptMsgAttributeSeq_isNotEligible() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class)))
                .thenReturn(query);
        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.fetch()).thenReturn(new ArrayList());
        when(epostrxQueryFactory.insert(any())).thenReturn(insertClause);

        RsRxScriptDTO r1 = new RsRxScriptDTO();
        r1.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        r1.setRxNumber("8001");

        Timestamp writtenDate = Timestamp.valueOf(LocalDateTime.now().plusDays(3L));
        RsRxLinkingSubstitutionDTO rs0 = new RsRxLinkingSubstitutionDTO();
        rs0.setEScriptMsgAttributeSeq(new BigInteger("8000"));
        rs0.setRxNumber("8001");
        rs0.setIsQuantityLowered("Y");
        rs0.setWrittenDate(writtenDate);

        RsRxLinkingSubstitutionDTO rs1 = new RsRxLinkingSubstitutionDTO();
        rs1.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        rs1.setRxNumber("8001");
        rs1.setWrittenDate(writtenDate);
        rs1.setRxExpirationDate(writtenDate);
        rs1.setDispensedQuantity(0L);

        RsRxLinkingSubstitutionDTO rs2 = new RsRxLinkingSubstitutionDTO();
        rs2.setEScriptMsgAttributeSeq(new BigInteger("8002"));
        rs2.setRxNumber("8002");
        rs2.setFillStatusNum((byte) 4);
        rs2.setRxExpirationDate(writtenDate);
        rs2.setFillDaysSupply(90);
        rs2.setRxStatusCodeNum((byte) 1);
        rs2.setWrittenDate(writtenDate);
        rs2.setOrderStatusNum((byte) 1);
        CCodeValue ccValue = new CCodeValue();
        ccValue.setCodeValueKeyword("QTY_CHANGE_FLAG");
        ccValue.setStatusFlag("A");
        when(query.fetch())
                .thenReturn(
                        Arrays.asList(r1),
                        Arrays.asList(rs0, rs1, rs2),
                        Arrays.asList(ccValue),
                        new ArrayList());
        when(functionsService.getRemainingRefills(anyString(), anyList())).thenReturn(1);
        when(functionsService.getRxQtyRemaining(anyString(), anyList())).thenReturn(1L);
        RsMultiLinkDTO rml = new RsMultiLinkDTO();
        rml.setChildRxNumber("8001");
        rml.setParentRxNumber("7001");
        when(functionsService.getRsRxLinkingParentChild("8001", "Y")).thenReturn(Arrays.asList(rml));
        assertEquals(
                new BigInteger("8001"), service.getSubEScriptMsgAttributeSeq(new BigInteger("8001")));
        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        verify(insertClause)
                .values(any(), any(), any(), any(), any(), any(), any(), any(), ac.capture(), any());
        verify(insertClause).execute();
        assertEquals(
                "Rx number 8002 is not eligible for Refill nullas it has an existing ERX entry in ERX ITEM Entry Queue or it is in the process of order creation",
                ac.getValue());
    }

    @Test
    public void getSubEScriptMsgAttributeSeq_isBeenSelectedByCurrentOrder() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class)))
                .thenReturn(query);
        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.fetch()).thenReturn(new ArrayList());
        when(epostrxQueryFactory.insert(any())).thenReturn(insertClause);

        RsRxScriptDTO r1 = new RsRxScriptDTO();
        r1.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        r1.setRxNumber("8001");

        Timestamp writtenDate = Timestamp.valueOf(LocalDateTime.now().plusDays(3L));
        RsRxLinkingSubstitutionDTO rs0 = new RsRxLinkingSubstitutionDTO();
        rs0.setEScriptMsgAttributeSeq(new BigInteger("8000"));
        rs0.setRxNumber("8001");

        RsRxLinkingSubstitutionDTO rs1 = new RsRxLinkingSubstitutionDTO();
        rs1.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        rs1.setRxNumber("8001");
        rs1.setWrittenDate(writtenDate);
        rs1.setRxExpirationDate(writtenDate);
        rs1.setDispensedQuantity(0L);
        rs1.setOrderNum(new BigInteger("1001"));

        RsRxLinkingSubstitutionDTO rsT1 = new RsRxLinkingSubstitutionDTO();
        rsT1.setEScriptMsgAttributeSeq(new BigInteger("8011"));
        rsT1.setRxNumber("8002");
        RsRxLinkingSubstitutionDTO rsT2 = new RsRxLinkingSubstitutionDTO();
        rsT2.setEScriptMsgAttributeSeq(new BigInteger("8012"));
        rsT2.setRxNumber("8002");
        rsT2.setOrderStatusNum((byte) 98);
        RsRxLinkingSubstitutionDTO rsT3 = new RsRxLinkingSubstitutionDTO();
        rsT3.setEScriptMsgAttributeSeq(new BigInteger("8013"));
        rsT3.setRxNumber("8002");
        rsT3.setOrderStatusNum((byte) 98);
        rsT3.setOrderNum(new BigInteger("1002"));
        RsRxLinkingSubstitutionDTO rsT4 = new RsRxLinkingSubstitutionDTO();
        rsT4.setEScriptMsgAttributeSeq(new BigInteger("8014"));
        rsT4.setRxNumber("8002");
        rsT4.setOrderStatusNum((byte) 98);
        rsT4.setOrderNum(new BigInteger("1001"));
        RsRxLinkingSubstitutionDTO rsT5 = new RsRxLinkingSubstitutionDTO();
        rsT5.setEScriptMsgAttributeSeq(new BigInteger("8015"));
        rsT5.setRxNumber("8002");
        rsT5.setOrderStatusNum((byte) 98);
        rsT5.setOrderNum(new BigInteger("1001"));
        rsT5.setOrderLineStatusNum((byte) 2);

        RsRxLinkingSubstitutionDTO rs2 = new RsRxLinkingSubstitutionDTO();
        rs2.setEScriptMsgAttributeSeq(new BigInteger("8002"));
        rs2.setRxNumber("8002");
        rs2.setFillStatusNum((byte) 4);
        rs2.setRxExpirationDate(writtenDate);
        rs2.setFillDaysSupply(90);
        rs2.setRxStatusCodeNum((byte) 1);
        rs2.setWrittenDate(writtenDate);
        rs2.setOrderNum(new BigInteger("1001"));
        rs2.setOrderStatusNum((byte) 98);
        rs2.setOrderLineStatusNum((byte) 1);
        CCodeValue ccValue = new CCodeValue();
        ccValue.setCodeValueKeyword("QTY_CHANGE_FLAG");
        ccValue.setStatusFlag("A");
        when(query.fetch())
                .thenReturn(
                        Arrays.asList(r1),
                        Arrays.asList(rs0, rs1, rsT1, rsT2, rsT3, rsT4, rsT5, rs2),
                        Arrays.asList(ccValue),
                        new ArrayList());
        when(functionsService.getRemainingRefills(anyString(), anyList())).thenReturn(1);
        RsMultiLinkDTO rml = new RsMultiLinkDTO();
        rml.setChildRxNumber("8001");
        rml.setParentRxNumber("7001");
        when(functionsService.getRsRxLinkingParentChild("8001", "Y")).thenReturn(Arrays.asList(rml));
        assertEquals(
                new BigInteger("8001"), service.getSubEScriptMsgAttributeSeq(new BigInteger("8001")));
        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        verify(insertClause)
                .values(any(), any(), any(), any(), any(), any(), any(), any(), ac.capture(), any());
        verify(insertClause).execute();
        assertEquals(
                "Rx number 8002 has already been selected to be filled on the current order:1001",
                ac.getValue());
    }

    @Test
    public void getSubEScriptMsgAttributeSeq_patientPlanNotFound() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class)))
                .thenReturn(query);
        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.fetch()).thenReturn(new ArrayList());
        when(epostrxQueryFactory.insert(any())).thenReturn(insertClause);

        RsRxScriptDTO r1 = new RsRxScriptDTO();
        r1.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        r1.setRxNumber("8001");

        Timestamp writtenDate = Timestamp.valueOf(LocalDateTime.now().plusDays(3L));

        RsRxLinkingSubstitutionDTO rs1 = new RsRxLinkingSubstitutionDTO();
        rs1.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        rs1.setRxNumber("8001");
        rs1.setWrittenDate(writtenDate);
        rs1.setRxExpirationDate(writtenDate);
        rs1.setDispensedQuantity(0L);
        rs1.setOrderNum(new BigInteger("1001"));

        RsRxLinkingSubstitutionDTO rsT1 = new RsRxLinkingSubstitutionDTO();
        rsT1.setEScriptMsgAttributeSeq(new BigInteger("8002"));
        rsT1.setRxNumber("8002");

        RsRxLinkingSubstitutionDTO rs2 = new RsRxLinkingSubstitutionDTO();
        rs2.setEScriptMsgAttributeSeq(new BigInteger("8002"));
        rs2.setRxNumber("8002");
        rs2.setFillStatusNum((byte) 4);
        rs2.setRxExpirationDate(writtenDate);
        rs2.setFillDaysSupply(90);
        rs2.setRxStatusCodeNum((byte) 1);
        rs2.setWrittenDate(writtenDate);
        rs2.setPpNum(1001L);

        when(query.fetch())
                .thenReturn(
                        Arrays.asList(r1),
                        Arrays.asList(rs1, rsT1, rs2),
                        new ArrayList(),
                        new ArrayList());
        when(functionsService.getRemainingRefills(anyString(), anyList())).thenReturn(1);
        RsMultiLinkDTO rml = new RsMultiLinkDTO();
        rml.setChildRxNumber("8001");
        rml.setParentRxNumber("7001");
        when(functionsService.getRsRxLinkingParentChild("8001", "Y")).thenReturn(Arrays.asList(rml));
        assertEquals(
                new BigInteger("8001"), service.getSubEScriptMsgAttributeSeq(new BigInteger("8001")));
        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        verify(insertClause)
                .values(any(), any(), any(), any(), any(), any(), any(), any(), ac.capture(), any());
        verify(insertClause).execute();
        assertEquals(
                "Patient Plan Information not found on profile",
                ac.getValue());
    }

    @Test
    public void getSubEScriptMsgAttributeSeq_patientPlanInactive() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class)))
                .thenReturn(query);
        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.fetch()).thenReturn(new ArrayList());
        when(epostrxQueryFactory.insert(any())).thenReturn(insertClause);

        RsRxScriptDTO r1 = new RsRxScriptDTO();
        r1.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        r1.setRxNumber("8001");

        Timestamp writtenDate = Timestamp.valueOf(LocalDateTime.now().plusDays(3L));

        RsRxLinkingSubstitutionDTO rs1 = new RsRxLinkingSubstitutionDTO();
        rs1.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        rs1.setRxNumber("8001");
        rs1.setWrittenDate(writtenDate);
        rs1.setRxExpirationDate(writtenDate);
        rs1.setDispensedQuantity(0L);
        rs1.setOrderNum(new BigInteger("1001"));

        RsRxLinkingSubstitutionDTO rsT1 = new RsRxLinkingSubstitutionDTO();
        rsT1.setEScriptMsgAttributeSeq(new BigInteger("8002"));
        rsT1.setRxNumber("8002");
        rsT1.setPpPlanName("patientPlanInactive");
        RsRxLinkingSubstitutionDTO rsT2 = new RsRxLinkingSubstitutionDTO();
        rsT2.setEScriptMsgAttributeSeq(new BigInteger("8002"));
        rsT2.setRxNumber("8002");
        rsT2.setPpNum(1002L);
        RsRxLinkingSubstitutionDTO rsT3 = new RsRxLinkingSubstitutionDTO();
        rsT3.setEScriptMsgAttributeSeq(new BigInteger("8002"));
        rsT3.setRxNumber("8002");
        rsT3.setPpNum(1002L);
        rsT3.setPayorPpNum(1002L);
        RsRxLinkingSubstitutionDTO rsT4 = new RsRxLinkingSubstitutionDTO();
        rsT4.setEScriptMsgAttributeSeq(new BigInteger("8002"));
        rsT4.setRxNumber("8002");
        rsT4.setPpNum(1002L);
        rsT4.setPayorPpNum(1002L);
        rsT4.setCoverageTypeId((byte) 3);

        RsRxLinkingSubstitutionDTO rs2 = new RsRxLinkingSubstitutionDTO();
        rs2.setEScriptMsgAttributeSeq(new BigInteger("8002"));
        rs2.setRxNumber("8002");
        rs2.setFillStatusNum((byte) 4);
        rs2.setRxExpirationDate(writtenDate);
        rs2.setFillDaysSupply(90);
        rs2.setRxStatusCodeNum((byte) 1);
        rs2.setWrittenDate(writtenDate);
        rs2.setPpNum(1001L);
        rs2.setPayorPpNum(1001L);
        rs2.setCoverageTypeId((byte) 4);

        when(query.fetch())
                .thenReturn(
                        Arrays.asList(r1),
                        Arrays.asList(rs1, rsT1, rsT2, rsT3, rsT4, rs2),
                        new ArrayList(),
                        new ArrayList());
        when(functionsService.getRemainingRefills(anyString(), anyList())).thenReturn(1);
        RsMultiLinkDTO rml = new RsMultiLinkDTO();
        rml.setChildRxNumber("8001");
        rml.setParentRxNumber("7001");
        when(functionsService.getRsRxLinkingParentChild("8001", "Y")).thenReturn(Arrays.asList(rml));
        assertEquals(
                new BigInteger("8001"), service.getSubEScriptMsgAttributeSeq(new BigInteger("8001")));
        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        verify(insertClause)
                .values(any(), any(), any(), any(), any(), any(), any(), any(), ac.capture(), any());
        verify(insertClause).execute();
        assertEquals(
                "Patient Plan:patientPlanInactive is INACTIVE",
                ac.getValue());
    }

    @Test
    public void getSubEScriptMsgAttributeSeq_noError() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.fetch()).thenReturn(new ArrayList());

        RsRxScriptDTO r1 = new RsRxScriptDTO();
        r1.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        r1.setRxNumber("8001");

        Timestamp writtenDate = Timestamp.valueOf(LocalDateTime.now().plusDays(3L));

        RsRxLinkingSubstitutionDTO rs1 = new RsRxLinkingSubstitutionDTO();
        rs1.setEScriptMsgAttributeSeq(new BigInteger("8001"));
        rs1.setRxNumber("8001");
        rs1.setWrittenDate(writtenDate);
        rs1.setRxExpirationDate(writtenDate);
        rs1.setDispensedQuantity(0L);
        rs1.setOrderNum(new BigInteger("1001"));

        RsRxLinkingSubstitutionDTO rs2 = new RsRxLinkingSubstitutionDTO();
        rs2.setEScriptMsgAttributeSeq(new BigInteger("8002"));
        rs2.setRxNumber("8002");
        rs2.setFillStatusNum((byte) 4);
        rs2.setRxExpirationDate(writtenDate);
        rs2.setFillDaysSupply(90);
        rs2.setRxStatusCodeNum((byte) 1);
        rs2.setWrittenDate(writtenDate);
        rs2.setPpNum(1001L);
        rs2.setPayorPpNum(1001L);
        rs2.setCoverageTypeId((byte) 3);

        when(query.fetch())
                .thenReturn(
                        Arrays.asList(r1),
                        Arrays.asList(rs1, rs2),
                        new ArrayList(),
                        new ArrayList());
        when(functionsService.getRemainingRefills(anyString(), anyList())).thenReturn(1);
        RsMultiLinkDTO rml = new RsMultiLinkDTO();
        rml.setChildRxNumber("8001");
        rml.setParentRxNumber("7001");
        when(functionsService.getRsRxLinkingParentChild("8001", "Y")).thenReturn(Arrays.asList(rml));
        assertEquals(
                new BigInteger("8002"), service.getSubEScriptMsgAttributeSeq(new BigInteger("8001")));
        verify(insertClause, times(0)).execute();
    }
}
