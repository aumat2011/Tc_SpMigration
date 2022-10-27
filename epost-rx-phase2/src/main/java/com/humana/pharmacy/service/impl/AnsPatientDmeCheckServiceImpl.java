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
     * The PP_PLAN_ID Kodda kullanılabilir
     */
    private static final Integer PP_PLAN_ID = 10073;

    /**
     * The JURISDICTION_CBZ Kodda kullanılabilir
     */
    private static final String JURISDICTION_CBZ = "CBZ";

    /**
     * The NDC_NUMBER Kodda kullanılabilir
     */
    private static final String NDC_NUMBER = "08484070120";

    /**
     * The EDITXNCODE_NEWRX Kodda kullanılabilir
     */
    private static final String EDITXNCODE_NEWRX = "NEWRX";

    /**
     * The EDITXNCODE_REFILLRX Kodda kullanılabilir  F_IsPatientDMEDocsValid
     */
    private static final String EDITXNCODE_REFILLRX = "REFILLRX";

    /**
     * The F_ISPATIENTDMEDOCVALID_PARAM1 Kodda kullanılabilir
     */
    private static final String F_ISPATIENTDMEDOCVALID_PARAM1 = "DTL";

    /**
     * The F_ISPATIENTDMEDOCVALID_PARAM2 Kodda kullanılabilir
     */
    private static final String F_ISPATIENTDMEDOCVALID_PARAM2 = "PPN";

    /**
     * The F_ISPATIENTDMEDOCVALID_PARAM3 Kodda kullanılabilir
     */
    private static final String F_ISPATIENTDMEDOCVALID_PARAM3 = "SFM";

    /**
     * The F_ISPATIENTDMEDOCVALID_PARAM4 Kodda kullanılabilir
     */
    private static final String F_ISPATIENTDMEDOCVALID_PARAM4 = "AOB";

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
