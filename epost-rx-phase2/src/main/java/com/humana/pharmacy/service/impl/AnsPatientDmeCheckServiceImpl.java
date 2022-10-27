package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.common.Helper;
import com.humana.pharmacy.common.service.impl.BaseServiceImpl;
import com.humana.pharmacy.dto.AnsDefaultRxPlanParamsDTO;
import com.humana.pharmacy.dto.AnsDefaultRxPlanParamsResult;
import com.humana.pharmacy.common.entity.QCityStateZip;
import com.humana.pharmacy.common.entity.QEScriptMsgAttributes;
import com.humana.pharmacy.common.entity.QOrderBillship;
import com.humana.pharmacy.common.entity.QOrderHeader;
import com.humana.pharmacy.common.entity.QOrderLines;
import com.humana.pharmacy.common.entity.QPatientAddress;
import com.humana.pharmacy.common.entity.QPatientAddressTypeAssignments;
import com.humana.pharmacy.common.entity.QPayorPlans;
import com.humana.pharmacy.common.entity.QProducts;
import com.humana.pharmacy.common.entity.QRxParams;
import com.humana.pharmacy.common.entity.QStateCsProducts;
import com.humana.pharmacy.dto.AnsPatientDmeCheckResult;
import com.humana.pharmacy.dto.AnsRxProductResult;
import com.humana.pharmacy.service.AnsPatientDmeCheckService;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Implementation of AnsPatientDmeCheckService using QueryDSL. It extends BaseServiceImpl.
 */
public class AnsPatientDmeCheckServiceImpl extends BaseServiceImpl implements AnsPatientDmeCheckService {

    /**
     * Constructor of AnsPatientDmeCheckServiceImpl.
     *
     * @param epostrxDataSource  DataSource for epostrx database. Required
     * @param workflowDataSource DataSource for epostrx_workflow database. Required
     * @throws RuntimeException if epostrxDataSource or workflowDataSource is null
     */
    public AnsPatientDmeCheckServiceImpl(DataSource epostrxDataSource, DataSource workflowDataSource) {
        super(epostrxDataSource, workflowDataSource);
    }

    public AnsPatientDmeCheckResult checkPatientDme(BigInteger scriptId) {
        Helper.checkNull(scriptId, "scriptId");

        return null;
    }



}
