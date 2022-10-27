package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.TestsHelper;
import com.humana.pharmacy.dto.RsRxLinkWorkflowMinUtilizationCheckDTO;
import com.humana.pharmacy.common.cache.model.CCodeValue;
import com.humana.pharmacy.common.dto.RsMultiLinkDTO;
import com.humana.pharmacy.dto.RsRxLinkWorkflowMinUtilizationCheckResult;
import com.humana.pharmacy.common.model.RxParams;
import com.humana.pharmacy.common.model.RxParamsGpis;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Answers.RETURNS_SELF;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RsRxLinkWorkflowMinUtilizationCheckServiceImplTest {
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
     * The RsRxLinkWorkflowMinUtilizationCheckServiceImpl instance.
     */
    private RsRxLinkWorkflowMinUtilizationCheckServiceImpl service;

    /**
     * The code values.
     */
    private List<CCodeValue> codeValues;

    /**
     * The RsRxLinkWorkflowMinUtilizationCheckDTO list.
     */
    private List<RsRxLinkWorkflowMinUtilizationCheckDTO> rsRxLinkWorkflowMinUtilizationCheckDTOs;

    /**
     * The RsRxLinkWorkflowMinUtilizationCheckDTO list.
     */
    private List<RsRxLinkWorkflowMinUtilizationCheckDTO> rsRxLinkWorkflowMinUtilizationCheckDTOs2;

    /**
     * The RsMultiLinkDTO list.
     */
    private List<RsMultiLinkDTO> rsMultiLinkDTOs;

    /**
     * The rx number.
     */
    private String rxNumber = "1002";

    /**
     * The RxParamsGpis list.
     */
    private List<RxParamsGpis> rxParamsGpisList;

    /**
     * The RxParams list.
     */
    private List<RxParams> rxParamsList;

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
        service = new RsRxLinkWorkflowMinUtilizationCheckServiceImpl(epostrxDataSource, workflowDataSource);
        TestsHelper.setFieldValue(service, "epostrxQueryFactory", epostrxQueryFactory);
        TestsHelper.setFieldValue(service, "workflowQueryFactory", workflowQueryFactory);

        codeValues = getCodeValues();
        rsRxLinkWorkflowMinUtilizationCheckDTOs = getRsRxLinkWorkflowMinUtilizationCheckDTOs();
        rsMultiLinkDTOs = getRsMultiLinkDTOs();
        rxParamsGpisList = getRxParamsGpisList();
        rxParamsList = getRxParamsList();
    }

    @Test
    public void test_ctor_epostrxDataSourceNull() {
        assertThrows(
                RuntimeException.class,
                () -> new RsRxLinkWorkflowMinUtilizationCheckServiceImpl(null, workflowDataSource));
    }

    @Test
    public void test_ctor_workflowDataSourceNull() {
        assertThrows(
                RuntimeException.class,
                () -> new RsRxLinkWorkflowMinUtilizationCheckServiceImpl(epostrxDataSource, null));
    }

    @Test
    public void test_checkRsRxLinkWorkflowMinUtilization_rxNumberNull() {
        rxNumber = null;

        assertThrows(RuntimeException.class, () ->
                service.checkRsRxLinkWorkflowMinUtilization(rxNumber));
    }

    @Test
    public void test_checkRsRxLinkWorkflowMinUtilization_rxNumberEmpty() {
        rxNumber = " ";

        assertThrows(RuntimeException.class, () ->
                service.checkRsRxLinkWorkflowMinUtilization(rxNumber));
    }

    @Test
    public void test_checkRsRxLinkWorkflowMinUtilization_RxMultiLinkNotExist() {
        rxNumber = "not exist";

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchCount()).thenReturn(0L);

        RsRxLinkWorkflowMinUtilizationCheckResult res = service.checkRsRxLinkWorkflowMinUtilization(rxNumber);

        assertNull(res);
    }

    @Test
    public void test_checkRsRxLinkWorkflowMinUtilization_NoError1() {
        rsMultiLinkDTOs.clear();

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchCount()).thenReturn(1L);
        when(query.fetch()).thenReturn(rsMultiLinkDTOs, codeValues);

        RsRxLinkWorkflowMinUtilizationCheckResult res = service.checkRsRxLinkWorkflowMinUtilization(rxNumber);

        assertNull(res.getError());
        assertEquals("RXSANITY_Keyword", res.getOverrideQueue());
        assertEquals("RXSANITY_Desc", res.getOverrideQname());
    }

    @Test
    public void test_checkRsRxLinkWorkflowMinUtilization_NoError2() {
        rsRxLinkWorkflowMinUtilizationCheckDTOs2 = Arrays.asList(rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0));

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchCount()).thenReturn(1L);
        when(query.fetch()).thenReturn(rsMultiLinkDTOs, rsRxLinkWorkflowMinUtilizationCheckDTOs,
                rsRxLinkWorkflowMinUtilizationCheckDTOs2, rxParamsGpisList, rxParamsList, codeValues);
        when(query.fetchFirst()).thenReturn(1, "LA", 2, "LA", 3);

        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.from(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.fetchCount()).thenReturn(0L);

        RsRxLinkWorkflowMinUtilizationCheckResult res = service.checkRsRxLinkWorkflowMinUtilization(rxNumber);

        assertNull(res.getError());
        assertEquals("RXSANITY_Keyword", res.getOverrideQueue());
        assertEquals("RXSANITY_Desc", res.getOverrideQname());
    }

    @Test
    public void test_checkRsRxLinkWorkflowMinUtilization_NoError3() {
        rsRxLinkWorkflowMinUtilizationCheckDTOs2 = Arrays.asList(rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0));

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchCount()).thenReturn(1L);
        when(query.fetch()).thenReturn(rsMultiLinkDTOs, rsRxLinkWorkflowMinUtilizationCheckDTOs,
                rsRxLinkWorkflowMinUtilizationCheckDTOs2, rxParamsGpisList, rxParamsList, codeValues);
        when(query.fetchFirst()).thenReturn(1, "LA", 2, "LA", 3);

        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.from(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.fetchCount()).thenReturn(0L);

        RsRxLinkWorkflowMinUtilizationCheckResult res = service.checkRsRxLinkWorkflowMinUtilization(rxNumber);

        assertNull(res.getError());
        assertEquals("RXSANITY_Keyword", res.getOverrideQueue());
        assertEquals("RXSANITY_Desc", res.getOverrideQname());
    }

    @Test
    public void test_checkRsRxLinkWorkflowMinUtilization_NoError4() {
        rsRxLinkWorkflowMinUtilizationCheckDTOs2 = Arrays.asList(rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0));

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchCount()).thenReturn(1L);
        when(query.fetch()).thenReturn(rsMultiLinkDTOs, rsRxLinkWorkflowMinUtilizationCheckDTOs,
                rsRxLinkWorkflowMinUtilizationCheckDTOs2, rxParamsGpisList, codeValues);
        when(query.fetchFirst()).thenReturn(3, "LA", 2, "LA", 3);

        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.from(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.fetchCount()).thenReturn(0L);

        RsRxLinkWorkflowMinUtilizationCheckResult res = service.checkRsRxLinkWorkflowMinUtilization(rxNumber);

        assertNull(res.getError());
        assertEquals("RXSANITY_Keyword", res.getOverrideQueue());
        assertEquals("RXSANITY_Desc", res.getOverrideQname());
    }

    @Test
    public void test_checkRsRxLinkWorkflowMinUtilization_NoError5() {
        rxParamsGpisList.clear();
        rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0).setStateCsProductSeq(1L);
        rsRxLinkWorkflowMinUtilizationCheckDTOs2 = Arrays.asList(rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0));

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchCount()).thenReturn(1L);
        when(query.fetch()).thenReturn(rsMultiLinkDTOs, rsRxLinkWorkflowMinUtilizationCheckDTOs,
                rsRxLinkWorkflowMinUtilizationCheckDTOs2, rxParamsGpisList, rxParamsList, codeValues);
        when(query.fetchFirst()).thenReturn(3, "LA", 3, "LA", 3);

        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.from(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.fetchCount()).thenReturn(0L);

        RsRxLinkWorkflowMinUtilizationCheckResult res = service.checkRsRxLinkWorkflowMinUtilization(rxNumber);

        assertNull(res.getError());
        assertEquals("RXSANITY_Keyword", res.getOverrideQueue());
        assertEquals("RXSANITY_Desc", res.getOverrideQname());
    }

    @Test
    public void test_checkRsRxLinkWorkflowMinUtilization_NoError6() {
        rxParamsGpisList.clear();
        rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0).setFillStatusNum((byte) 5);
        rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0).setStateCsProductSeq(1L);
        rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0).setEdiTransactionCode("REFILLRX");
        rsRxLinkWorkflowMinUtilizationCheckDTOs.get(1).setFillStatusNum((byte) 5);
        rsRxLinkWorkflowMinUtilizationCheckDTOs.get(1).setStateCsProductSeq(1L);
        rsRxLinkWorkflowMinUtilizationCheckDTOs.get(1).setEdiTransactionCode("REFILLRX");
        rsRxLinkWorkflowMinUtilizationCheckDTOs2 = Arrays.asList(rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0));

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchCount()).thenReturn(1L);
        when(query.fetch()).thenReturn(rsMultiLinkDTOs, rsRxLinkWorkflowMinUtilizationCheckDTOs,
                rsRxLinkWorkflowMinUtilizationCheckDTOs2, rxParamsGpisList, rsRxLinkWorkflowMinUtilizationCheckDTOs2,
                rxParamsGpisList, codeValues);
        when(query.fetchFirst()).thenReturn(3, "LA", 3, "LA", 3);

        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.from(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.fetchCount()).thenReturn(0L);

        RsRxLinkWorkflowMinUtilizationCheckResult res = service.checkRsRxLinkWorkflowMinUtilization(rxNumber);

        assertNull(res.getError());
        assertEquals("RXSANITY_Keyword", res.getOverrideQueue());
        assertEquals("RXSANITY_Desc", res.getOverrideQname());
    }

    @Test
    public void test_checkRsRxLinkWorkflowMinUtilization_NoError7() {
        rxParamsGpisList.clear();
        rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0).setFillStatusNum((byte) 5);
        rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0).setStateCsProductSeq(1L);
        rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0).setEdiTransactionCode("XX");
        rsRxLinkWorkflowMinUtilizationCheckDTOs.get(1).setFillStatusNum((byte) 2);
        rsRxLinkWorkflowMinUtilizationCheckDTOs.get(1).setStateCsProductSeq(1L);
        rsRxLinkWorkflowMinUtilizationCheckDTOs.get(1).setEdiTransactionCode("XX");
        rsRxLinkWorkflowMinUtilizationCheckDTOs2 = Arrays.asList(rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0));

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchCount()).thenReturn(1L);
        when(query.fetch()).thenReturn(rsMultiLinkDTOs, rsRxLinkWorkflowMinUtilizationCheckDTOs,
                codeValues);
        when(query.fetchFirst()).thenReturn(3, "LA", 3, "LA", 3);

        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.from(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.fetchCount()).thenReturn(0L);

        RsRxLinkWorkflowMinUtilizationCheckResult res = service.checkRsRxLinkWorkflowMinUtilization(rxNumber);

        assertNull(res.getError());
        assertEquals("RXSANITY_Keyword", res.getOverrideQueue());
        assertEquals("RXSANITY_Desc", res.getOverrideQname());
    }

    @Test
    public void test_checkRsRxLinkWorkflowMinUtilization_NoError8() {
        rxParamsGpisList.clear();
        rsRxLinkWorkflowMinUtilizationCheckDTOs2 = Arrays.asList(rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0));

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchCount()).thenReturn(1L);
        when(query.fetch()).thenReturn(rsMultiLinkDTOs, rsRxLinkWorkflowMinUtilizationCheckDTOs,
                rsRxLinkWorkflowMinUtilizationCheckDTOs2, rxParamsGpisList, rxParamsList, codeValues);
        when(query.fetchFirst()).thenReturn(0, 0, "LA", 3, "LA", 3);

        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.from(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.fetchCount()).thenReturn(0L);

        RsRxLinkWorkflowMinUtilizationCheckResult res = service.checkRsRxLinkWorkflowMinUtilization(rxNumber);

        assertNull(res.getError());
        assertEquals("RXSANITY_Keyword", res.getOverrideQueue());
        assertEquals("RXSANITY_Desc", res.getOverrideQname());
    }

    @Test
    public void test_checkRsRxLinkWorkflowMinUtilization_ItemInWorkflowError() {
        rsRxLinkWorkflowMinUtilizationCheckDTOs2 = Arrays.asList(rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0));

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchCount()).thenReturn(1L);
        when(query.fetch()).thenReturn(rsMultiLinkDTOs, rsRxLinkWorkflowMinUtilizationCheckDTOs,
                codeValues);
        when(query.fetchFirst()).thenReturn(3, "LA", 2, "LA", 3);

        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.from(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.fetchCount()).thenReturn(1L);

        RsRxLinkWorkflowMinUtilizationCheckResult res = service.checkRsRxLinkWorkflowMinUtilization(rxNumber);

        assertEquals("ITEM IN WORKFLOW", res.toErrorMessage());
        assertEquals("RXSANITY_Keyword", res.getOverrideQueue());
        assertEquals("RXSANITY_Desc", res.getOverrideQname());
    }

    @Test
    public void test_checkRsRxLinkWorkflowMinUtilization_StateGpiMinUtilError() {
        rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0).setFillStatusNum((byte) 9);
        rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0).setFillDaysSupply(1);
        rsRxLinkWorkflowMinUtilizationCheckDTOs2 = Arrays.asList(rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0));

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchCount()).thenReturn(1L);
        when(query.fetch()).thenReturn(rsMultiLinkDTOs, rsRxLinkWorkflowMinUtilizationCheckDTOs,
                rsRxLinkWorkflowMinUtilizationCheckDTOs2, rxParamsGpisList, codeValues);
        when(query.fetchFirst()).thenReturn(3, "LA", 2, "LA", 3);

        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.from(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.fetchCount()).thenReturn(0L);

        RsRxLinkWorkflowMinUtilizationCheckResult res = service.checkRsRxLinkWorkflowMinUtilization(rxNumber);

        assertTrue(res.toErrorMessage().contains("LA STATE\\GPI CLASS MIN UTILIZATION NOT MET. REFILL ELIGIBLE ON:"));
        assertEquals("RXSANITY_Keyword", res.getOverrideQueue());
        assertEquals("RXSANITY_Desc", res.getOverrideQname());
    }

    @Test
    public void test_checkRsRxLinkWorkflowMinUtilization_StateNarcMinUtilError1() {
        rxParamsGpisList.clear();
        rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0).setFillStatusNum((byte) 9);
        rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0).setFillDaysSupply(1);
        rsRxLinkWorkflowMinUtilizationCheckDTOs2 = Arrays.asList(rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0));

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchCount()).thenReturn(1L);
        when(query.fetch()).thenReturn(rsMultiLinkDTOs, rsRxLinkWorkflowMinUtilizationCheckDTOs,
                rsRxLinkWorkflowMinUtilizationCheckDTOs2, rxParamsGpisList, rxParamsList, codeValues);
        when(query.fetchFirst()).thenReturn(3, "LA", 2, "LA", 3);

        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.from(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.fetchCount()).thenReturn(0L);

        RsRxLinkWorkflowMinUtilizationCheckResult res = service.checkRsRxLinkWorkflowMinUtilization(rxNumber);

        assertTrue(res.toErrorMessage().contains("LA STATE NARC MIN UTILIZATION NOT MET. REFILL ELIGIBLE ON:"));
        assertEquals("RXSANITY_Keyword", res.getOverrideQueue());
        assertEquals("RXSANITY_Desc", res.getOverrideQname());
    }

    @Test
    public void test_checkRsRxLinkWorkflowMinUtilization_StateNarcMinUtilError2() {
        rxParamsGpisList.clear();
        rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0).setFillStatusNum((byte) 9);
        rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0).setFillDaysSupply(1);
        rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0).setFillDispenseDate(new Timestamp(System.currentTimeMillis()));
        rsRxLinkWorkflowMinUtilizationCheckDTOs2 = Arrays.asList(rsRxLinkWorkflowMinUtilizationCheckDTOs.get(0));

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.fetchCount()).thenReturn(1L);
        when(query.fetch()).thenReturn(rsMultiLinkDTOs, rsRxLinkWorkflowMinUtilizationCheckDTOs,
                rsRxLinkWorkflowMinUtilizationCheckDTOs2, rxParamsGpisList, rxParamsList, codeValues);
        when(query.fetchFirst()).thenReturn(0, 0, "LA", 3, "LA", 3);

        when(workflowQueryFactory.select(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.from(any(Expression.class))).thenReturn(wfQuery);
        when(wfQuery.fetchCount()).thenReturn(0L);

        RsRxLinkWorkflowMinUtilizationCheckResult res = service.checkRsRxLinkWorkflowMinUtilization(rxNumber);

        assertTrue(res.toErrorMessage().contains("DEFAULT SETTINGS: STATE NARC MIN UTILIZATION NOT MET. REFILL ELIGIBLE ON:"));
        assertEquals("RXSANITY_Keyword", res.getOverrideQueue());
        assertEquals("RXSANITY_Desc", res.getOverrideQname());
    }

    /**
     * Create a RsMultiLinkDTO list.
     *
     * @return the RsMultiLinkDTO list.
     */
    private List<RsMultiLinkDTO> getRsMultiLinkDTOs() {
        List<RsMultiLinkDTO> list = new ArrayList<>();

        RsMultiLinkDTO dto1 = new RsMultiLinkDTO();
        dto1.setRxLinkSeq(1L);
        dto1.setParentRxNumber("0");
        dto1.setChildRxNumber("1001");
        dto1.setActive("Y");
        list.add(dto1);

        RsMultiLinkDTO dto2 = new RsMultiLinkDTO();
        dto2.setRxLinkSeq(2L);
        dto2.setParentRxNumber("1001");
        dto2.setChildRxNumber("1002");
        dto2.setActive("Y");
        list.add(dto2);

        return list;
    }

    /**
     * Create a RsRxLinkWorkflowMinUtilizationCheckDTO list.
     *
     * @return the RsRxLinkWorkflowMinUtilizationCheckDTO list.
     */
    private List<RsRxLinkWorkflowMinUtilizationCheckDTO> getRsRxLinkWorkflowMinUtilizationCheckDTOs() {
        List<RsRxLinkWorkflowMinUtilizationCheckDTO> list = new ArrayList<>();

        RsRxLinkWorkflowMinUtilizationCheckDTO dto1 = new RsRxLinkWorkflowMinUtilizationCheckDTO();
        dto1.setEScriptMsgAttributeSeq(BigInteger.valueOf(1));
        dto1.setChildEScriptMsgAttributeSeq(BigInteger.valueOf(2));
        dto1.setProdGenericRefNum("pgrn1");
        dto1.setRxNumber("1001");
        dto1.setFillStatusNum((byte) 2);
        dto1.setEdiTransactionCode("NEWRX");
        dto1.setOrderInvoiceNumber("OIN1");
        dto1.setStateCsProductStateNum(3);
        list.add(dto1);

        RsRxLinkWorkflowMinUtilizationCheckDTO dto2 = new RsRxLinkWorkflowMinUtilizationCheckDTO();
        dto2.setEScriptMsgAttributeSeq(BigInteger.valueOf(2));
        dto2.setChildEScriptMsgAttributeSeq(BigInteger.valueOf(3));
        dto2.setProdGenericRefNum("pgrn2");
        dto2.setRxNumber("1002");
        dto2.setFillStatusNum((byte) 2);
        dto2.setEdiTransactionCode("NEWRX");
        dto2.setOrderInvoiceNumber("OIN2");
        dto2.setStateCsProductStateNum(3);
        list.add(dto2);

        RsRxLinkWorkflowMinUtilizationCheckDTO dto3 = new RsRxLinkWorkflowMinUtilizationCheckDTO();
        dto3.setEScriptMsgAttributeSeq(BigInteger.valueOf(3));
        dto3.setChildEScriptMsgAttributeSeq(BigInteger.valueOf(0));
        dto3.setProdGenericRefNum("pgrn3");
        dto3.setRxNumber("1003");
        dto3.setFillStatusNum((byte) 9);
        dto3.setEdiTransactionCode("REFILLRX");
        dto3.setOrderInvoiceNumber("OIN3");
        dto3.setStateCsProductStateNum(3);
        list.add(dto3);

        return list;
    }

    /**
     * Create a RxParamsGpis list.
     *
     * @return the RxParamsGpis list.
     */
    private static List<RxParamsGpis> getRxParamsGpisList() {
        List<RxParamsGpis> list = new ArrayList<>();

        RxParamsGpis rxParamsGpis0 = new RxParamsGpis();
        rxParamsGpis0.setStateNum(0);
        rxParamsGpis0.setMinPctUtilization(201);
        list.add(rxParamsGpis0);

        RxParamsGpis rxParamsGpis1 = new RxParamsGpis();
        rxParamsGpis1.setStateNum(3);
        list.add(rxParamsGpis1);

        RxParamsGpis rxParamsGpis2 = new RxParamsGpis();
        rxParamsGpis2.setStateNum(5);
        list.add(rxParamsGpis2);

        return list;
    }

    /**
     * Create a RxParams list.
     *
     * @return the RxParams list.
     */
    private static List<RxParams> getRxParamsList() {
        List<RxParams> list = new ArrayList<>();
        RxParams rxParams0 = new RxParams();
        rxParams0.setStateNum(0);
        rxParams0.setMinPctUtilization(201);
        list.add(rxParams0);

        RxParams rxParams1 = new RxParams();
        rxParams1.setStateNum(2);
        list.add(rxParams1);

        return list;
    }

    /**
     * Create a CCodeValue list.
     *
     * @return the CCodeValue list.
     */
    private List<CCodeValue> getCodeValues() {
        CCodeValue ccValue1 = new CCodeValue();
        ccValue1.setCodeValueId(1);
        ccValue1.setFkCodeCatId("RXSANITY");
        ccValue1.setCodeValueKeyword("RXSANITY_Keyword");
        ccValue1.setCodeValueDesc("RXSANITY_Desc");
        ccValue1.setStatusFlag("A");

        return Arrays.asList(ccValue1);
    }
}
