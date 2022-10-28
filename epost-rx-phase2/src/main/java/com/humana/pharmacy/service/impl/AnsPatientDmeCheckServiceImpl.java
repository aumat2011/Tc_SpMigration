package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.common.Helper;
import com.humana.pharmacy.common.entity.*;
import com.humana.pharmacy.common.service.impl.BaseServiceImpl;
import com.humana.pharmacy.dto.*;
import com.humana.pharmacy.service.AnsPatientDmeCheckService;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.sql.SQLQuery;

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


        List<AnsPatientDmeCheckDTO> ansPatientDmeCheckDTOS = getAnsPatientDmeCheckDTOs(scriptId);
        QCityStateZip qCityStateZip = QCityStateZip.cityStateZip;
        QRegion qRegion = QRegion.region;

        if(ansPatientDmeCheckDTOS.isEmpty()){
            return null;
        }

        AnsPatientDmeCheckDTO currentDTO = ansPatientDmeCheckDTOS.get(0);

        Long idSeq2 = getRegionName(currentDTO.getPatientNum(),currentDTO);

        if (idSeq2 > 2){
            String Jurisdiction =
                    epostrxQueryFactory.select(
                            Projections.bean(AnsPatientDmeCheckDTO.class,
                                    qRegion.regionName.coalesce(" ").as("regionName")))
                            .from(qCityStateZip).addJoinFlag(JOIN_FLAG_NOLOCK,JoinFlag.Position.BEFORE_CONDITION)
                            .join(qRegion).addJoinFlag(JOIN_FLAG_NOLOCK,JoinFlag.Position.BEFORE_CONDITION)
                            .on(qCityStateZip.regionNum.eq(qRegion.regionNum))
                            .where(qCityStateZip.cszZipNum.eq(idSeq2)).fetchFirst();
            currentDTO.setIdSeq2(idSeq2);
        }

        // if



    }

    private Long getRegionName(Long patientNum,AnsPatientDmeCheckDTO ansPatientDmeCheckDTO) {
        Long idSeq2Res;
        QPatientAddress qPatientAddress = QPatientAddress.patientAddress1;
        QCityStateZip qCityStateZip = QCityStateZip.cityStateZip;
        QRegion qRegion = QRegion.region;
        QPatientAddressTypeAssignments qPatientAddressTypeAssignments = QPatientAddressTypeAssignments.patientAddressTypeAssignments;

        Long patientAddrSeqResult =
                epostrxQueryFactory.select(qPatientAddressTypeAssignments.patientAddrSeq)
                        .from(qPatientAddressTypeAssignments)
                        .where(qPatientAddressTypeAssignments.patientNum.eq(BigInteger.valueOf(patientNum))
                                .and((qPatientAddressTypeAssignments.addressTypeNum.eq(1))))
                        .fetchFirst();



        String jurisdiction =
                epostrxQueryFactory.select(qRegion.regionName).from(qPatientAddress).addJoinFlag(JOIN_FLAG_NOLOCK,JoinFlag.Position.BEFORE_CONDITION)
                        .join(qCityStateZip).addJoinFlag(JOIN_FLAG_NOLOCK,JoinFlag.Position.BEFORE_CONDITION).join(qRegion)
                        .where(qPatientAddress.patientNum.eq(BigInteger.valueOf(patientNum))
                                .and(qPatientAddress.patientAddrSeq.eq(patientAddrSeqResult))
                                .and(qPatientAddress.cszZipNum.eq(qCityStateZip.cszZipNum))
                                .and(qCityStateZip.regionNum.eq(qRegion.regionNum)))
                        .fetchFirst();

        if(jurisdiction.isEmpty()){
            idSeq2Res =
                    epostrxQueryFactory.select(qPatientAddress.cszZipNum)
                            .from(qPatientAddress).addJoinFlag(JOIN_FLAG_NOLOCK,JoinFlag.Position.BEFORE_CONDITION)
                            .join(qPatientAddressTypeAssignments).addJoinFlag(JOIN_FLAG_NOLOCK,JoinFlag.Position.BEFORE_CONDITION)
                            .on(qPatientAddress.patientAddrSeq.eq(qPatientAddressTypeAssignments.patientAddrSeq))
                            .where(qPatientAddressTypeAssignments.addressTypeNum.eq(10).and(qPatientAddress.patientNum.eq(BigInteger.valueOf(patientNum))))
                            .fetchFirst();
            ansPatientDmeCheckDTO.setJurisdiction(null); //referance type
            return idSeq2Res;
        }
        else {
            ansPatientDmeCheckDTO.setJurisdiction(jurisdiction); //referance type
            idSeq2Res = null;
            return idSeq2Res;
        }

    }

//    private Boolean controlEligible(Long patientNum, String dmeCollection, String ppPlanId) {
//
//        // Line: 119-137
//        QPatientPlans qPatientPlans = QPatientPlans.patientPlans;
//        QPayorPlans qPayorPlans = QPayorPlans.payorPlans;
//
//
//        Long ppNum =
//                epostrxQueryFactory.select(qPayorPlans.ppNum)
//                        .from(qPatientPlans).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
//                        .join(qPayorPlans).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
//                        .on(qPayorPlans.ppNum.eq(qPatientPlans.ppNum))
//                        .where(qPatientPlans.patientNum.eq(BigInteger.valueOf(patientNum))
//                                .and(qPayorPlans.dmeCollection.eq(dmeCollection.coalesce(qPayorPlans.dmeCollection)))
//                                .and(qPayorPlans.ppPlanId.eq(ppPlanId.coalesce(qPayorPlans.ppPlanId)));
//
//        Integer patientPlanActiveStatus = getPatientPlanActiveStatus (patientNum,ppNum);
//
//        if (patientPlanActiveStatus == 0){
//            return true ;
//        }
//        else return false;
//    }

    private byte controlEligible(Long patientNum, Long ppNum) {
        Integer patientPlanActiveStatus = getPatientPlanActiveStatus(patientNum,ppNum);

        if (patientPlanActiveStatus == 0){
            return 1 ;
        }
        else return 0;
    }

    / /**
     * Collect AnsPatientDmeCheckDTO data.
     *
     * @param scriptId the script id
     * @return the list of AnsPatientDmeCheckDTO
     */
    private List<AnsPatientDmeCheckDTO> getAnsPatientDmeCheckDTOs(BigInteger scriptId) {
        QEScriptMsgAttributes qeScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        QRxFillAux qRxFillAux = QRxFillAux.rxFillAux;
        QPatientMedPartb qPatientMedPartb = QPatientMedPartb.patientMedPartb
        QPatientAddress qPatientAddress = QPatientAddress.patientAddress1;
        QCityStateZip qCityStateZip = QCityStateZip.cityStateZip;
        QRegions qRegions = QRegions.regions;
        QPatientAddressTypeAssignments qPatientAddressTypeAssignments = QPatientAddressTypeAssignments.patientAddressTypeAssignments;
        QPatientAddressTypeAssignments qPatientAddressTypeAssignments2 = new QPatientAddressTypeAssignments("qPatientAddressTypeAssignments2");
        QPayorPlans qPayorPlans = QPayorPlans.payorPlans;
        QPayorPlans qPayorPlans2 = new QPayorPlans("qPayorPlans2");
        QPayorPlans qPayorPlans3 = new QPayorPlans("qPayorPlans3");
        QPatientPlans qPatientPlans = QPatientPlans.patientPlans;

        return epostrxQueryFactory.select(
                        Projections.bean(AnsPatientDmeCheckDTO.class,
                                qeScriptMsgAttributes.patientNum,
                                qeScriptMsgAttributes.originationNum,
                                qeScriptMsgAttributes.ediTransactionCode,
                                qeScriptMsgAttributes.dispensedProductNumber,
                                qeScriptMsgAttributes.pwoItem.coalesce(FLAG_N).as("pwoItem"),
                                qeScriptMsgAttributes.pwoExpDate,
                                qRxFillAux.dailyTests.coalesce((byte) 0).as("dailyTests"),
                                qRxFillAux.attestCollected.coalesce(FLAG_N).as("attestCollected"),
                                qPatientMedPartb.overUtilizer.coalesce(FLAG_N).as("overUtilizer"),
                                qPatientMedPartb.insulinDependent.coalesce(FLAG_N).as("insulinDependent"),
                                qPayorPlans.dmeCollection.coalesce(FLAG_N).as("dmeCollection"),
                                qRegions.regionName.coalesce('').as("regionName"),
                                qPatientAddress.cszZipNum,
                                qPayorPlans2.ppNum,
                                qPayorPlans3.ppNum
                        )
                )
                // Line: 105 - 116
                .from(qeScriptMsgAttributes).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .leftJoin(qRxFillAux).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillAux.eScriptMsgAttributeSeq.eq(qeScriptMsgAttributes.eScriptMsgAttributeSeq))
                // Line: 145- 149
                .leftJoin(qPayorPlans).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPayorPlans.ppNum.eq(qRxFillAux.ppNum))
                // Line: 139 - 143
                .leftJoin(qPatientMedPartb).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientAddress.patientNum.eq(qeScriptMsgAttributes.patientNum))
                // Line: 156 - 184 and 189-196
                .leftJoin(qPatientAddress).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientAddress.patientNum.eq(qeScriptMsgAttributes.patientNum))
                .leftJoin(qCityStateZip).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qCityStateZip.cszZipNum.eq(qPatientAddress.cszZipNum))
                .leftJoin(qRegions).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRegions.regionNum.eq(qCityStateZip.regionNum))
                .leftJoin(qPatientAddressTypeAssignments).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientAddressTypeAssignments.patientNum.eq(qeScriptMsgAttributes.patientNum))
                .and(qPatientAddressTypeAssignments.addressTypeNum.eq(ADRESS_TYPE_NUM_1))
                .leftJoin(qPatientAddressTypeAssignments2).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientAddressTypeAssignments2.patientAddrSeq.eq(qPatientAddress.patientAddrSeq))
                .and(qPatientAddressTypeAssignments2.addressTypeNum.eq(ADRESS_TYPE_NUM_10))
                // Line: 119-137
                .leftJoin(qPatientPlans).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPatientPlans.patientNum.eq(qeScriptMsgAttributes.patientNum))
                .leftJoin(qPayorPlans2).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPayorPlans2.ppNum.eq(qPatientPlans.ppNum))
                .and(qPayorPlans2.dmeCollection.eq(DME_COLLECTION_Y))
                .leftJoin(qPayorPlans3).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qPayorPlans3.ppNum.eq(qPatientPlans.ppNum))
                .and(qPayorPlans3.ppPlanId.eq(PP_PLAN_ID_10073))
                .where(qeScriptMsgAttributes.eScriptMsgAttributeSeq.eq(scriptId))
                .fetch();
    }



}
