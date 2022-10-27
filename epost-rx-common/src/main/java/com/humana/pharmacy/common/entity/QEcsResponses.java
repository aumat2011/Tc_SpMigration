package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.EcsResponses;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QEcsResponses is a Querydsl query type for EcsResponses
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QEcsResponses extends com.querydsl.sql.RelationalPathBase<EcsResponses> {

    private static final long serialVersionUID = -471111273;

    public static final QEcsResponses ecsResponses = new QEcsResponses("ecs_responses");

    public final StringPath adjPaymentType = createString("adjPaymentType");

    public final NumberPath<Long> amtBrand = createNumber("amtBrand", Long.class);

    public final NumberPath<Long> amtCoinsurance = createNumber("amtCoinsurance", Long.class);

    public final NumberPath<Long> amtCoverageGap = createNumber("amtCoverageGap", Long.class);

    public final NumberPath<Long> amtNetwork = createNumber("amtNetwork", Long.class);

    public final NumberPath<Long> amtNonPreferred = createNumber("amtNonPreferred", Long.class);

    public final NumberPath<Long> amtPreferred = createNumber("amtPreferred", Long.class);

    public final NumberPath<Long> awpUnitPrice = createNumber("awpUnitPrice", Long.class);

    public final StringPath basisOfCalcCoinsurance = createString("basisOfCalcCoinsurance");

    public final StringPath batchEcs = createString("batchEcs");

    public final StringPath benefitId = createString("benefitId");

    public final StringPath bin = createString("bin");

    public final StringPath cardholderid = createString("cardholderid");

    public final StringPath cmsLowIncomeLevel = createString("cmsLowIncomeLevel");

    public final StringPath cobTxn = createString("cobTxn");

    public final NumberPath<Long> compProdSeq = createNumber("compProdSeq", Long.class);

    public final StringPath costBasis = createString("costBasis");

    public final NumberPath<Long> dispenseFeeContractAmt = createNumber("dispenseFeeContractAmt", Long.class);

    public final StringPath docDelivery = createString("docDelivery");

    public final StringPath doctorName = createString("doctorName");

    public final NumberPath<java.math.BigInteger> doctorNum = createNumber("doctorNum", java.math.BigInteger.class);

    public final NumberPath<Byte> ecsBasisReimbDetCode = createNumber("ecsBasisReimbDetCode", Byte.class);

    public final DateTimePath<java.sql.Timestamp> ecsDatetime = createDateTime("ecsDatetime", java.sql.Timestamp.class);

    public final StringPath ecsLog = createString("ecsLog");

    public final NumberPath<java.math.BigInteger> ecsReqSeqNum = createNumber("ecsReqSeqNum", java.math.BigInteger.class);

    public final NumberPath<Long> ecsRespAccumDedAmt = createNumber("ecsRespAccumDedAmt", Long.class);

    public final StringPath ecsRespAddlMsg = createString("ecsRespAddlMsg");

    public final NumberPath<Long> ecsRespAmtApplyPerDed = createNumber("ecsRespAmtApplyPerDed", Long.class);

    public final NumberPath<Long> ecsRespAmtApplyProdSel = createNumber("ecsRespAmtApplyProdSel", Long.class);

    public final NumberPath<Long> ecsRespAmtApplySalesTax = createNumber("ecsRespAmtApplySalesTax", Long.class);

    public final NumberPath<Long> ecsRespAmtCopay = createNumber("ecsRespAmtCopay", Long.class);

    public final NumberPath<Long> ecsRespAmtExcBeneMax = createNumber("ecsRespAmtExcBeneMax", Long.class);

    public final StringPath ecsRespAuthorizationNum = createString("ecsRespAuthorizationNum");

    public final DateTimePath<java.sql.Timestamp> ecsRespDate = createDateTime("ecsRespDate", java.sql.Timestamp.class);

    public final NumberPath<Long> ecsRespDispenseFeePaid = createNumber("ecsRespDispenseFeePaid", Long.class);

    public final NumberPath<Long> ecsRespDispenseFeeSubmitted = createNumber("ecsRespDispenseFeeSubmitted", Long.class);

    public final NumberPath<Byte> ecsRespDurOverflow = createNumber("ecsRespDurOverflow", Byte.class);

    public final NumberPath<Long> ecsRespFlatSalesTax = createNumber("ecsRespFlatSalesTax", Long.class);

    public final NumberPath<Long> ecsRespFlatSalesTaxSubmitted = createNumber("ecsRespFlatSalesTaxSubmitted", Long.class);

    public final NumberPath<Long> ecsRespGrossDueSubmitted = createNumber("ecsRespGrossDueSubmitted", Long.class);

    public final NumberPath<Long> ecsRespIncentiveFeePaid = createNumber("ecsRespIncentiveFeePaid", Long.class);

    public final NumberPath<Long> ecsRespIncentiveFeeSubmitted = createNumber("ecsRespIncentiveFeeSubmitted", Long.class);

    public final NumberPath<Long> ecsRespIngredCostPaid = createNumber("ecsRespIngredCostPaid", Long.class);

    public final NumberPath<Long> ecsRespIngredCostSubmitted = createNumber("ecsRespIngredCostSubmitted", Long.class);

    public final StringPath ecsRespMsg = createString("ecsRespMsg");

    public final StringPath ecsRespMsgAck = createString("ecsRespMsgAck");

    public final StringPath ecsResponseStatusType = createString("ecsResponseStatusType");

    public final NumberPath<Long> ecsRespOtherAmountPaid = createNumber("ecsRespOtherAmountPaid", Long.class);

    public final NumberPath<Long> ecsRespOtherAmountSubmitted = createNumber("ecsRespOtherAmountSubmitted", Long.class);

    public final NumberPath<Long> ecsRespPatPayAmt = createNumber("ecsRespPatPayAmt", Long.class);

    public final NumberPath<Long> ecsRespPerSalesTaxPaid = createNumber("ecsRespPerSalesTaxPaid", Long.class);

    public final NumberPath<Long> ecsRespPerSalesTaxSubmitted = createNumber("ecsRespPerSalesTaxSubmitted", Long.class);

    public final NumberPath<Long> ecsRespProfServiceFeePaid = createNumber("ecsRespProfServiceFeePaid", Long.class);

    public final NumberPath<Long> ecsRespProfServiceFeeSubmitted = createNumber("ecsRespProfServiceFeeSubmitted", Long.class);

    public final StringPath ecsRespReferenceNumber = createString("ecsRespReferenceNumber");

    public final StringPath ecsRespRejMsgAck = createString("ecsRespRejMsgAck");

    public final NumberPath<Long> ecsRespRemainBeneAmt = createNumber("ecsRespRemainBeneAmt", Long.class);

    public final NumberPath<Long> ecsRespRemainDedAmt = createNumber("ecsRespRemainDedAmt", Long.class);

    public final NumberPath<Long> ecsRespSalesTaxPaid = createNumber("ecsRespSalesTaxPaid", Long.class);

    public final NumberPath<Long> ecsRespSalesTaxSubmitted = createNumber("ecsRespSalesTaxSubmitted", Long.class);

    public final NumberPath<java.math.BigInteger> ecsRespSeqNum = createNumber("ecsRespSeqNum", java.math.BigInteger.class);

    public final NumberPath<Long> ecsRespTotalAmtPaid = createNumber("ecsRespTotalAmtPaid", Long.class);

    public final StringPath ecsTransactionCode = createString("ecsTransactionCode");

    public final StringPath ecsVersionCode = createString("ecsVersionCode");

    public final StringPath escRespHelpDeskPhone = createString("escRespHelpDeskPhone");

    public final NumberPath<java.math.BigInteger> eScriptMsgAttributeSeq = createNumber("eScriptMsgAttributeSeq", java.math.BigInteger.class);

    public final NumberPath<Long> estGenericSavings = createNumber("estGenericSavings", Long.class);

    public final NumberPath<Integer> fillDays = createNumber("fillDays", Integer.class);

    public final NumberPath<Integer> fillNumber = createNumber("fillNumber", Integer.class);

    public final NumberPath<Long> fillQuantity = createNumber("fillQuantity", Long.class);

    public final NumberPath<Long> fillUsualCustomaryPrice = createNumber("fillUsualCustomaryPrice", Long.class);

    public final NumberPath<Byte> forceAccept = createNumber("forceAccept", Byte.class);

    public final StringPath formularyId = createString("formularyId");

    public final StringPath groupId = createString("groupId");

    public final NumberPath<Long> healthSpendAmt = createNumber("healthSpendAmt", Long.class);

    public final NumberPath<Long> ingredCostContractAmt = createNumber("ingredCostContractAmt", Long.class);

    public final StringPath intCtrlNumber = createString("intCtrlNumber");

    public final StringPath location = createString("location");

    public final NumberPath<Byte> manualEcs = createNumber("manualEcs", Byte.class);

    public final StringPath medicaidAgencyNumber = createString("medicaidAgencyNumber");

    public final StringPath medicaidCardholderId = createString("medicaidCardholderId");

    public final StringPath medicaidIcn = createString("medicaidIcn");

    public final StringPath medicaidIdNumber = createString("medicaidIdNumber");

    public final StringPath medicareContractNumber = createString("medicareContractNumber");

    public final StringPath medicareCovCode = createString("medicareCovCode");

    public final StringPath medicareEffectDate = createString("medicareEffectDate");

    public final StringPath medicareTermDate = createString("medicareTermDate");

    public final NumberPath<Long> metricQuantity = createNumber("metricQuantity", Long.class);

    public final StringPath networkId = createString("networkId");

    public final NumberPath<Integer> otherCoverageType = createNumber("otherCoverageType", Integer.class);

    public final NumberPath<Long> otherPayorAmtRecognized = createNumber("otherPayorAmtRecognized", Long.class);

    public final DateTimePath<java.sql.Timestamp> paDatetime = createDateTime("paDatetime", java.sql.Timestamp.class);

    public final NumberPath<java.math.BigInteger> paidSeqNum = createNumber("paidSeqNum", java.math.BigInteger.class);

    public final StringPath paReplycode = createString("paReplycode");

    public final StringPath patientDob = createString("patientDob");

    public final StringPath patientName = createString("patientName");

    public final NumberPath<java.math.BigInteger> patientNum = createNumber("patientNum", java.math.BigInteger.class);

    public final NumberPath<Long> patSalesTax = createNumber("patSalesTax", Long.class);

    public final StringPath paUrl = createString("paUrl");

    public final NumberPath<Long> paUsernum = createNumber("paUsernum", Long.class);

    public final StringPath payorId = createString("payorId");

    public final StringPath pcn = createString("pcn");

    public final NumberPath<Long> percentSalesTaxBasisPaid = createNumber("percentSalesTaxBasisPaid", Long.class);

    public final NumberPath<Integer> percentSalesTaxBasisSubmitted = createNumber("percentSalesTaxBasisSubmitted", Integer.class);

    public final NumberPath<Long> percentSalesTaxRatePaid = createNumber("percentSalesTaxRatePaid", Long.class);

    public final NumberPath<Long> percentSalesTaxRateSubmitted = createNumber("percentSalesTaxRateSubmitted", Long.class);

    public final StringPath planId = createString("planId");

    public final NumberPath<Long> planSalesTax = createNumber("planSalesTax", Long.class);

    public final NumberPath<Long> ppNum = createNumber("ppNum", Long.class);

    public final StringPath processor = createString("processor");

    public final NumberPath<Long> processorFee = createNumber("processorFee", Long.class);

    public final NumberPath<java.math.BigInteger> prodId = createNumber("prodId", java.math.BigInteger.class);

    public final StringPath productName = createString("productName");

    public final StringPath productNumber = createString("productNumber");

    public final StringPath providerId = createString("providerId");

    public final StringPath providerIdCode = createString("providerIdCode");

    public final StringPath rebillReason = createString("rebillReason");

    public final StringPath rxNumber = createString("rxNumber");

    public final StringPath server = createString("server");

    public final NumberPath<Long> spendAmountRemain = createNumber("spendAmountRemain", Long.class);

    public final NumberPath<Long> supplierUnitPrice = createNumber("supplierUnitPrice", Long.class);

    public final NumberPath<java.math.BigInteger> sysUserNum = createNumber("sysUserNum", java.math.BigInteger.class);

    public final StringPath tradingPartnerNum = createString("tradingPartnerNum");

    public final StringPath txnRefNumber = createString("txnRefNumber");

    public final NumberPath<Long> wacUnitPrice = createNumber("wacUnitPrice", Long.class);

    public final StringPath webUrl = createString("webUrl");

    public QEcsResponses(String variable) {
        super(EcsResponses.class, forVariable(variable), "dbo", "ecs_responses");
        addMetadata();
    }

    public QEcsResponses(String variable, String schema, String table) {
        super(EcsResponses.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QEcsResponses(String variable, String schema) {
        super(EcsResponses.class, forVariable(variable), schema, "ecs_responses");
        addMetadata();
    }

    public QEcsResponses(Path<? extends EcsResponses> path) {
        super(path.getType(), path.getMetadata(), "dbo", "ecs_responses");
        addMetadata();
    }

    public QEcsResponses(PathMetadata metadata) {
        super(EcsResponses.class, metadata, "dbo", "ecs_responses");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(adjPaymentType, ColumnMetadata.named("adj_payment_type").withIndex(75).ofType(Types.VARCHAR).withSize(60));
        addMetadata(amtBrand, ColumnMetadata.named("amt_brand").withIndex(88).ofType(Types.NUMERIC).withSize(12));
        addMetadata(amtCoinsurance, ColumnMetadata.named("amt_coinsurance").withIndex(82).ofType(Types.NUMERIC).withSize(12));
        addMetadata(amtCoverageGap, ColumnMetadata.named("amt_coverage_gap").withIndex(91).ofType(Types.NUMERIC).withSize(12));
        addMetadata(amtNetwork, ColumnMetadata.named("amt_network").withIndex(87).ofType(Types.NUMERIC).withSize(12));
        addMetadata(amtNonPreferred, ColumnMetadata.named("amt_non_preferred").withIndex(90).ofType(Types.NUMERIC).withSize(12));
        addMetadata(amtPreferred, ColumnMetadata.named("amt_preferred").withIndex(89).ofType(Types.NUMERIC).withSize(12));
        addMetadata(awpUnitPrice, ColumnMetadata.named("awp_unit_price").withIndex(67).ofType(Types.NUMERIC).withSize(12));
        addMetadata(basisOfCalcCoinsurance, ColumnMetadata.named("basis_of_calc_coinsurance").withIndex(84).ofType(Types.VARCHAR).withSize(60));
        addMetadata(batchEcs, ColumnMetadata.named("batch_ecs").withIndex(45).ofType(Types.VARCHAR).withSize(60));
        addMetadata(benefitId, ColumnMetadata.named("benefit_id").withIndex(102).ofType(Types.VARCHAR).withSize(60));
        addMetadata(bin, ColumnMetadata.named("bin").withIndex(56).ofType(Types.VARCHAR).withSize(60));
        addMetadata(cardholderid, ColumnMetadata.named("cardholderid").withIndex(64).ofType(Types.VARCHAR).withSize(60));
        addMetadata(cmsLowIncomeLevel, ColumnMetadata.named("cms_low_income_level").withIndex(107).ofType(Types.VARCHAR).withSize(60));
        addMetadata(cobTxn, ColumnMetadata.named("cob_txn").withIndex(49).ofType(Types.VARCHAR).withSize(60));
        addMetadata(compProdSeq, ColumnMetadata.named("comp_prod_seq").withIndex(50).ofType(Types.NUMERIC).withSize(16));
        addMetadata(costBasis, ColumnMetadata.named("cost_basis").withIndex(60).ofType(Types.VARCHAR).withSize(35));
        addMetadata(dispenseFeeContractAmt, ColumnMetadata.named("dispense_fee_contract_amt").withIndex(93).ofType(Types.NUMERIC).withSize(12));
        addMetadata(docDelivery, ColumnMetadata.named("doc_delivery").withIndex(111).ofType(Types.CHAR).withSize(1));
        addMetadata(doctorName, ColumnMetadata.named("doctor_name").withIndex(53).ofType(Types.VARCHAR).withSize(120));
        addMetadata(doctorNum, ColumnMetadata.named("doctor_num").withIndex(120).ofType(Types.NUMERIC).withSize(30));
        addMetadata(ecsBasisReimbDetCode, ColumnMetadata.named("ecs_basis_reimb_det_code").withIndex(2).ofType(Types.NUMERIC).withSize(2));
        addMetadata(ecsDatetime, ColumnMetadata.named("ecs_datetime").withIndex(65).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(ecsLog, ColumnMetadata.named("ecs_log").withIndex(44).ofType(Types.VARCHAR).withSize(8000));
        addMetadata(ecsReqSeqNum, ColumnMetadata.named("ecs_req_seq_num").withIndex(3).ofType(Types.NUMERIC).withSize(30));
        addMetadata(ecsRespAccumDedAmt, ColumnMetadata.named("ecs_resp_accum_ded_amt").withIndex(19).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespAddlMsg, ColumnMetadata.named("ecs_resp_addl_msg").withIndex(18).ofType(Types.VARCHAR).withSize(2147483647));
        addMetadata(ecsRespAmtApplyPerDed, ColumnMetadata.named("ecs_resp_amt_apply_per_ded").withIndex(24).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespAmtApplyProdSel, ColumnMetadata.named("ecs_resp_amt_apply_prod_sel").withIndex(32).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespAmtApplySalesTax, ColumnMetadata.named("ecs_resp_amt_apply_sales_tax").withIndex(36).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespAmtCopay, ColumnMetadata.named("ecs_resp_amt_copay").withIndex(26).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespAmtExcBeneMax, ColumnMetadata.named("ecs_resp_amt_exc_bene_max").withIndex(34).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespAuthorizationNum, ColumnMetadata.named("ecs_resp_authorization_num").withIndex(16).ofType(Types.VARCHAR).withSize(20));
        addMetadata(ecsRespDate, ColumnMetadata.named("ecs_resp_date").withIndex(8).ofType(Types.TIMESTAMP).withSize(23).withDigits(3).notNull());
        addMetadata(ecsRespDispenseFeePaid, ColumnMetadata.named("ecs_resp_dispense_fee_paid").withIndex(28).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespDispenseFeeSubmitted, ColumnMetadata.named("ecs_resp_dispense_fee_submitted").withIndex(29).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespDurOverflow, ColumnMetadata.named("ecs_resp_dur_overflow").withIndex(37).ofType(Types.NUMERIC).withSize(1));
        addMetadata(ecsRespFlatSalesTax, ColumnMetadata.named("ecs_resp_flat_sales_tax").withIndex(72).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespFlatSalesTaxSubmitted, ColumnMetadata.named("ecs_resp_flat_sales_tax_submitted").withIndex(114).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespGrossDueSubmitted, ColumnMetadata.named("ecs_resp_gross_due_submitted").withIndex(39).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespIncentiveFeePaid, ColumnMetadata.named("ecs_resp_incentive_fee_paid").withIndex(35).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespIncentiveFeeSubmitted, ColumnMetadata.named("ecs_resp_incentive_fee_submitted").withIndex(20).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespIngredCostPaid, ColumnMetadata.named("ecs_resp_ingred_cost_paid").withIndex(10).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespIngredCostSubmitted, ColumnMetadata.named("ecs_resp_ingred_cost_submitted").withIndex(38).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespMsg, ColumnMetadata.named("ecs_resp_msg").withIndex(17).ofType(Types.VARCHAR).withSize(2000));
        addMetadata(ecsRespMsgAck, ColumnMetadata.named("ecs_resp_msg_ack").withIndex(40).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(ecsResponseStatusType, ColumnMetadata.named("ecs_response_status_type").withIndex(1).ofType(Types.CHAR).withSize(1));
        addMetadata(ecsRespOtherAmountPaid, ColumnMetadata.named("ecs_resp_other_amount_paid").withIndex(12).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespOtherAmountSubmitted, ColumnMetadata.named("ecs_resp_other_amount_submitted").withIndex(22).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespPatPayAmt, ColumnMetadata.named("ecs_resp_pat_pay_amt").withIndex(9).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespPerSalesTaxPaid, ColumnMetadata.named("ecs_resp_per_sales_tax_paid").withIndex(31).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespPerSalesTaxSubmitted, ColumnMetadata.named("ecs_resp_per_sales_tax_submitted").withIndex(33).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespProfServiceFeePaid, ColumnMetadata.named("ecs_resp_prof_service_fee_paid").withIndex(30).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespProfServiceFeeSubmitted, ColumnMetadata.named("ecs_resp_prof_service_fee_submitted").withIndex(25).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespReferenceNumber, ColumnMetadata.named("ecs_resp_reference_number").withIndex(11).ofType(Types.VARCHAR).withSize(60));
        addMetadata(ecsRespRejMsgAck, ColumnMetadata.named("ecs_resp_rej_msg_ack").withIndex(41).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(ecsRespRemainBeneAmt, ColumnMetadata.named("ecs_resp_remain_bene_amt").withIndex(23).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespRemainDedAmt, ColumnMetadata.named("ecs_resp_remain_ded_amt").withIndex(21).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespSalesTaxPaid, ColumnMetadata.named("ecs_resp_sales_tax_paid").withIndex(13).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespSalesTaxSubmitted, ColumnMetadata.named("ecs_resp_sales_tax_submitted").withIndex(15).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsRespSeqNum, ColumnMetadata.named("ecs_resp_seq_num").withIndex(6).ofType(Types.NUMERIC).withSize(30).notNull());
        addMetadata(ecsRespTotalAmtPaid, ColumnMetadata.named("ecs_resp_total_amt_paid").withIndex(14).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsTransactionCode, ColumnMetadata.named("ecs_transaction_code").withIndex(4).ofType(Types.VARCHAR).withSize(5));
        addMetadata(ecsVersionCode, ColumnMetadata.named("ecs_version_code").withIndex(71).ofType(Types.VARCHAR).withSize(10));
        addMetadata(escRespHelpDeskPhone, ColumnMetadata.named("esc_resp_help_desk_phone").withIndex(27).ofType(Types.VARCHAR).withSize(18));
        addMetadata(eScriptMsgAttributeSeq, ColumnMetadata.named("e_script_msg_attribute_seq").withIndex(7).ofType(Types.NUMERIC).withSize(35));
        addMetadata(estGenericSavings, ColumnMetadata.named("est_generic_savings").withIndex(83).ofType(Types.NUMERIC).withSize(12));
        addMetadata(fillDays, ColumnMetadata.named("fill_days").withIndex(112).ofType(Types.NUMERIC).withSize(7));
        addMetadata(fillNumber, ColumnMetadata.named("fill_number").withIndex(58).ofType(Types.NUMERIC).withSize(7));
        addMetadata(fillQuantity, ColumnMetadata.named("fill_quantity").withIndex(59).ofType(Types.NUMERIC).withSize(12));
        addMetadata(fillUsualCustomaryPrice, ColumnMetadata.named("fill_usual_customary_price").withIndex(42).ofType(Types.NUMERIC).withSize(12));
        addMetadata(forceAccept, ColumnMetadata.named("force_accept").withIndex(61).ofType(Types.NUMERIC).withSize(1));
        addMetadata(formularyId, ColumnMetadata.named("formulary_id").withIndex(101).ofType(Types.VARCHAR).withSize(60));
        addMetadata(groupId, ColumnMetadata.named("group_id").withIndex(99).ofType(Types.VARCHAR).withSize(60));
        addMetadata(healthSpendAmt, ColumnMetadata.named("health_spend_amt").withIndex(86).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ingredCostContractAmt, ColumnMetadata.named("ingred_cost_contract_amt").withIndex(92).ofType(Types.NUMERIC).withSize(12));
        addMetadata(intCtrlNumber, ColumnMetadata.named("int_ctrl_number").withIndex(76).ofType(Types.VARCHAR).withSize(60));
        addMetadata(location, ColumnMetadata.named("location").withIndex(123).ofType(Types.VARCHAR).withSize(500));
        addMetadata(manualEcs, ColumnMetadata.named("manual_ecs").withIndex(46).ofType(Types.NUMERIC).withSize(1));
        addMetadata(medicaidAgencyNumber, ColumnMetadata.named("medicaid_agency_number").withIndex(95).ofType(Types.VARCHAR).withSize(60));
        addMetadata(medicaidCardholderId, ColumnMetadata.named("medicaid_cardholder_id").withIndex(96).ofType(Types.VARCHAR).withSize(60));
        addMetadata(medicaidIcn, ColumnMetadata.named("medicaid_icn").withIndex(78).ofType(Types.VARCHAR).withSize(60));
        addMetadata(medicaidIdNumber, ColumnMetadata.named("medicaid_id_number").withIndex(94).ofType(Types.VARCHAR).withSize(60));
        addMetadata(medicareContractNumber, ColumnMetadata.named("medicare_contract_number").withIndex(106).ofType(Types.VARCHAR).withSize(60));
        addMetadata(medicareCovCode, ColumnMetadata.named("medicare_cov_code").withIndex(103).ofType(Types.VARCHAR).withSize(60));
        addMetadata(medicareEffectDate, ColumnMetadata.named("medicare_effect_date").withIndex(104).ofType(Types.VARCHAR).withSize(60));
        addMetadata(medicareTermDate, ColumnMetadata.named("medicare_term_date").withIndex(105).ofType(Types.VARCHAR).withSize(60));
        addMetadata(metricQuantity, ColumnMetadata.named("metric_quantity").withIndex(66).ofType(Types.NUMERIC).withSize(12));
        addMetadata(networkId, ColumnMetadata.named("network_id").withIndex(100).ofType(Types.VARCHAR).withSize(60));
        addMetadata(otherCoverageType, ColumnMetadata.named("other_coverage_type").withIndex(47).ofType(Types.NUMERIC).withSize(5));
        addMetadata(otherPayorAmtRecognized, ColumnMetadata.named("other_payor_amt_recognized").withIndex(113).ofType(Types.NUMERIC).withSize(12));
        addMetadata(paDatetime, ColumnMetadata.named("pa_datetime").withIndex(126).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(paidSeqNum, ColumnMetadata.named("paid_seq_num").withIndex(70).ofType(Types.NUMERIC).withSize(30));
        addMetadata(paReplycode, ColumnMetadata.named("pa_replycode").withIndex(124).ofType(Types.VARCHAR).withSize(35));
        addMetadata(patientDob, ColumnMetadata.named("patient_dob").withIndex(73).ofType(Types.VARCHAR).withSize(60));
        addMetadata(patientName, ColumnMetadata.named("patient_name").withIndex(51).ofType(Types.VARCHAR).withSize(120));
        addMetadata(patientNum, ColumnMetadata.named("patient_num").withIndex(119).ofType(Types.NUMERIC).withSize(30));
        addMetadata(patSalesTax, ColumnMetadata.named("pat_sales_tax").withIndex(80).ofType(Types.NUMERIC).withSize(12));
        addMetadata(paUrl, ColumnMetadata.named("pa_url").withIndex(125).ofType(Types.VARCHAR).withSize(3500));
        addMetadata(paUsernum, ColumnMetadata.named("pa_usernum").withIndex(122).ofType(Types.NUMERIC).withSize(16));
        addMetadata(payorId, ColumnMetadata.named("payor_id").withIndex(97).ofType(Types.VARCHAR).withSize(60));
        addMetadata(pcn, ColumnMetadata.named("pcn").withIndex(57).ofType(Types.VARCHAR).withSize(60));
        addMetadata(percentSalesTaxBasisPaid, ColumnMetadata.named("percent_sales_tax_basis_paid").withIndex(110).ofType(Types.NUMERIC).withSize(12));
        addMetadata(percentSalesTaxBasisSubmitted, ColumnMetadata.named("percent_sales_tax_basis_submitted").withIndex(117).ofType(Types.NUMERIC).withSize(5));
        addMetadata(percentSalesTaxRatePaid, ColumnMetadata.named("percent_sales_tax_rate_paid").withIndex(109).ofType(Types.NUMERIC).withSize(12));
        addMetadata(percentSalesTaxRateSubmitted, ColumnMetadata.named("percent_sales_tax_rate_submitted").withIndex(108).ofType(Types.NUMERIC).withSize(12));
        addMetadata(planId, ColumnMetadata.named("plan_id").withIndex(98).ofType(Types.VARCHAR).withSize(60));
        addMetadata(planSalesTax, ColumnMetadata.named("plan_sales_tax").withIndex(81).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ppNum, ColumnMetadata.named("pp_num").withIndex(5).ofType(Types.NUMERIC).withSize(16));
        addMetadata(processor, ColumnMetadata.named("processor").withIndex(115).ofType(Types.VARCHAR).withSize(60));
        addMetadata(processorFee, ColumnMetadata.named("processor_fee").withIndex(79).ofType(Types.NUMERIC).withSize(12));
        addMetadata(prodId, ColumnMetadata.named("prod_id").withIndex(118).ofType(Types.NUMERIC).withSize(30));
        addMetadata(productName, ColumnMetadata.named("product_name").withIndex(52).ofType(Types.VARCHAR).withSize(120));
        addMetadata(productNumber, ColumnMetadata.named("product_number").withIndex(55).ofType(Types.VARCHAR).withSize(60));
        addMetadata(providerId, ColumnMetadata.named("provider_id").withIndex(62).ofType(Types.VARCHAR).withSize(60));
        addMetadata(providerIdCode, ColumnMetadata.named("provider_id_code").withIndex(63).ofType(Types.VARCHAR).withSize(60));
        addMetadata(rebillReason, ColumnMetadata.named("rebill_reason").withIndex(121).ofType(Types.VARCHAR).withSize(500));
        addMetadata(rxNumber, ColumnMetadata.named("rx_number").withIndex(43).ofType(Types.VARCHAR).withSize(60));
        addMetadata(server, ColumnMetadata.named("server").withIndex(116).ofType(Types.VARCHAR).withSize(60));
        addMetadata(spendAmountRemain, ColumnMetadata.named("spend_amount_remain").withIndex(85).ofType(Types.NUMERIC).withSize(12));
        addMetadata(supplierUnitPrice, ColumnMetadata.named("supplier_unit_price").withIndex(68).ofType(Types.NUMERIC).withSize(12));
        addMetadata(sysUserNum, ColumnMetadata.named("sys_user_num").withIndex(48).ofType(Types.NUMERIC).withSize(30));
        addMetadata(tradingPartnerNum, ColumnMetadata.named("trading_partner_num").withIndex(54).ofType(Types.VARCHAR).withSize(60));
        addMetadata(txnRefNumber, ColumnMetadata.named("txn_ref_number").withIndex(74).ofType(Types.VARCHAR).withSize(60));
        addMetadata(wacUnitPrice, ColumnMetadata.named("wac_unit_price").withIndex(69).ofType(Types.NUMERIC).withSize(12));
        addMetadata(webUrl, ColumnMetadata.named("web_url").withIndex(77).ofType(Types.VARCHAR).withSize(500));
    }

}

