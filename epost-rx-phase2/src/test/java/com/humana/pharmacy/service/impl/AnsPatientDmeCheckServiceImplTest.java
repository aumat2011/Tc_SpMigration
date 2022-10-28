package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.TestsHelper;
import com.humana.pharmacy.dto.AnsDefaultRxPlanParamsDTO;
import com.humana.pharmacy.dto.AnsDefaultRxPlanParamsResult;
import com.humana.pharmacy.dto.AnsPatientDmeCheckDTO;
import com.humana.pharmacy.service.AnsPatientDmeCheckService;
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


import static org.junit.jupiter.api.Assertions.*;


/**
 Unit Tests for AnsPatientDmeCheckService
 */

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AnsPatientDmeCheckServiceImplTest {

    /**
     * epostrx instance
     */
    @Mock
    private DataSource epostrxDS;

    /**
     * epostrx_workflow instance
     */
    @Mock
    private DataSource epostrx_workflowDS;

    /**
     * SQLQuery instance.
     */
    @Mock
    private SQLQuery query;

    @Mock
    private Long patientNum = 1L;

    /**
     * Service instance
     */
    private AnsPatientDmeCheckServiceImpl ansPatientDmeCheckService;

    /**
     * Parameter script id.
     */
    private BigInteger scriptId = BigInteger.valueOf(1);

    /**
     * The AnsDefaultRxPlanParamsDTO list.
     */
    private List<AnsPatientDmeCheckDTO> ansPatientDmeCheckDTO; // Fucntion which collects AnsPatientDmeCheckDTO

    private Boolean controlEligibleMock; //Function

    @Mock
    private SQLQueryFactory epostrxQF;

    @Mock
    private SQLQueryFactory workflowQF;

    @BeforeEach
    void setUp() {
        ansPatientDmeCheckService = new AnsPatientDmeCheckServiceImpl(epostrxDS, epostrx_workflowDS);

        TestsHelper.setFieldValue(ansPatientDmeCheckService, "epostrxQF", epostrxQF);
        TestsHelper.setFieldValue(ansPatientDmeCheckService, "workflowQF", workflowQF);

        ansPatientDmeCheckDTO = getAnsPatientDmeCheckDTOs(scriptId);
        controlEligibleMock = controlEligible(patientNum);
    }

    private void setUpMock() {
        when(epostrxQF.select(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.from(any(Expression.class), any(Expression.class))).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);
        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);
    }

    /**
     * Throws an exception if epostrxDS is null
     */
    @Test
    public void epostrxDSNullCheck() {
        assertThrows(RuntimeException.class, () -> new AnsPatientDmeCheckServiceImpl(null, epostrx_workflowDS));
    }

    /**
     * Throws an exception if epostrx_workflowDS is null
     */
    @Test
    public void epostrx_workflowDSNullCheck()
    {
        assertThrows(RuntimeException.class, () -> new AnsPatientDmeCheckServiceImpl(epostrxDS, null));
    }

    /**
     * Tests if the given parameters' values are null
     */
    @Test
    public void ParamsNullCheck()
    {

    }

    /**
     * Tests if the given parameters' values don't match with the entity values
     */
    @Test
    public void NotFoundParamsInEntityCheck()
    {

    }


}
