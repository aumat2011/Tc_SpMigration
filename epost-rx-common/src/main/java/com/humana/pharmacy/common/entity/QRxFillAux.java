package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.RxFillAux;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QRxFillAux is a Querydsl query type for RxFillAux
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QRxFillAux extends com.querydsl.sql.RelationalPathBase<RxFillAux> {

    private static final long serialVersionUID = 60977665;

    public static final QRxFillAux rxFillAux = new QRxFillAux("rx_fill_aux");

    public final StringPath allowConsolidate = createString("allowConsolidate");

    public final StringPath attestCollected = createString("attestCollected");

    public final StringPath autoFillBin = createString("autoFillBin");

    public final NumberPath<Long> awpPrice = createNumber("awpPrice", Long.class);

    public final NumberPath<Long> awpUnitPrice = createNumber("awpUnitPrice", Long.class);

    public final NumberPath<Byte> batchEcs = createNumber("batchEcs", Byte.class);

    public final NumberPath<Integer> calculatedDaysSupply = createNumber("calculatedDaysSupply", Integer.class);

    public final NumberPath<Long> calculatedInvPrice = createNumber("calculatedInvPrice", Long.class);

    public final NumberPath<Long> calculatedInvWacPrice = createNumber("calculatedInvWacPrice", Long.class);

    public final NumberPath<Long> calculatedPlanPrice = createNumber("calculatedPlanPrice", Long.class);

    public final StringPath clinicId = createString("clinicId");

    public final NumberPath<Byte> cobCheck = createNumber("cobCheck", Byte.class);

    public final NumberPath<Long> compProdSeq = createNumber("compProdSeq", Long.class);

    public final DateTimePath<java.sql.Timestamp> consentDatetime = createDateTime("consentDatetime", java.sql.Timestamp.class);

    public final StringPath consentStatus = createString("consentStatus");

    public final NumberPath<Long> consentUsernum = createNumber("consentUsernum", Long.class);

    public final StringPath consolidated = createString("consolidated");

    public final StringPath conversionLink = createString("conversionLink");

    public final NumberPath<Byte> dailyTests = createNumber("dailyTests", Byte.class);

    public final DateTimePath<java.sql.Timestamp> dateProcessed = createDateTime("dateProcessed", java.sql.Timestamp.class);

    public final StringPath deFirstName = createString("deFirstName");

    public final StringPath deLastName = createString("deLastName");

    public final StringPath dePhoneAreaCode = createString("dePhoneAreaCode");

    public final StringPath dePhoneNumber = createString("dePhoneNumber");

    public final StringPath directEntryValue = createString("directEntryValue");

    public final StringPath drugDbcode = createString("drugDbcode");

    public final StringPath drugDbcodeQualifier = createString("drugDbcodeQualifier");

    public final NumberPath<Long> drugdbWacUnit = createNumber("drugdbWacUnit", Long.class);

    public final NumberPath<Byte> ecsBusy = createNumber("ecsBusy", Byte.class);

    public final NumberPath<java.math.BigDecimal> ecsclarMetricQuantity = createNumber("ecsclarMetricQuantity", java.math.BigDecimal.class);

    public final StringPath erxByPassed = createString("erxByPassed");

    public final DateTimePath<java.sql.Timestamp> erxReviewDatetime = createDateTime("erxReviewDatetime", java.sql.Timestamp.class);

    public final NumberPath<Long> erxReviewUsernum = createNumber("erxReviewUsernum", Long.class);

    public final NumberPath<java.math.BigInteger> eScriptMsgAttributeSeq = createNumber("eScriptMsgAttributeSeq", java.math.BigInteger.class);

    public final StringPath externalLink = createString("externalLink");

    public final NumberPath<Byte> fillAutoStatus = createNumber("fillAutoStatus", Byte.class);

    public final NumberPath<java.math.BigInteger> fillCob3Status = createNumber("fillCob3Status", java.math.BigInteger.class);

    public final NumberPath<java.math.BigInteger> fillCobStatus = createNumber("fillCobStatus", java.math.BigInteger.class);

    public final NumberPath<Integer> fillDaysSupply = createNumber("fillDaysSupply", Integer.class);

    public final DateTimePath<java.sql.Timestamp> fillDiscardDate = createDateTime("fillDiscardDate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> fillDispenseDate = createDateTime("fillDispenseDate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> fillEcsOverrideDate = createDateTime("fillEcsOverrideDate", java.sql.Timestamp.class);

    public final NumberPath<java.math.BigInteger> fillEcsStatus = createNumber("fillEcsStatus", java.math.BigInteger.class);

    public final DateTimePath<java.sql.Timestamp> fillExpirationDate = createDateTime("fillExpirationDate", java.sql.Timestamp.class);

    public final NumberPath<Short> fillInvStatus = createNumber("fillInvStatus", Short.class);

    public final DateTimePath<java.sql.Timestamp> fillPostcheckDatetime = createDateTime("fillPostcheckDatetime", java.sql.Timestamp.class);

    public final StringPath fillPostcheckRphInitials = createString("fillPostcheckRphInitials");

    public final DateTimePath<java.sql.Timestamp> fillPrecheckDatetime = createDateTime("fillPrecheckDatetime", java.sql.Timestamp.class);

    public final StringPath fillPrecheckRphInitials = createString("fillPrecheckRphInitials");

    public final NumberPath<Long> fillQtyDispensed = createNumber("fillQtyDispensed", Long.class);

    public final StringPath fillRxRphInitials = createString("fillRxRphInitials");

    public final DateTimePath<java.sql.Timestamp> fillShipDate = createDateTime("fillShipDate", java.sql.Timestamp.class);

    public final NumberPath<Byte> fillStatusNum = createNumber("fillStatusNum", Byte.class);

    public final StringPath followUpPrescriberFirstName = createString("followUpPrescriberFirstName");

    public final StringPath followUpPrescriberLastName = createString("followUpPrescriberLastName");

    public final StringPath followUpPrescriberMiddleName = createString("followUpPrescriberMiddleName");

    public final NumberPath<Integer> ftfMasterDays = createNumber("ftfMasterDays", Integer.class);

    public final NumberPath<Integer> ftfMasterQty = createNumber("ftfMasterQty", Integer.class);

    public final NumberPath<Long> ingredientPaidAmt = createNumber("ingredientPaidAmt", Long.class);

    public final NumberPath<Integer> initialFillDays = createNumber("initialFillDays", Integer.class);

    public final NumberPath<Integer> initialFillQty = createNumber("initialFillQty", Integer.class);

    public final NumberPath<Integer> itemsDispensed = createNumber("itemsDispensed", Integer.class);

    public final StringPath messageId = createString("messageId");

    public final NumberPath<Long> metricQuantity = createNumber("metricQuantity", Long.class);

    public final StringPath mybInfo = createString("mybInfo");

    public final NumberPath<Integer> numberMetricItems = createNumber("numberMetricItems", Integer.class);

    public final StringPath originalRxNumber = createString("originalRxNumber");

    public final NumberPath<Long> ortfQty = createNumber("ortfQty", Long.class);

    public final NumberPath<Long> patientCopay = createNumber("patientCopay", Long.class);

    public final NumberPath<Byte> patientLocationId = createNumber("patientLocationId", Byte.class);

    public final NumberPath<Long> payorPaidAmt = createNumber("payorPaidAmt", Long.class);

    public final NumberPath<Long> pcDiagnosisNum = createNumber("pcDiagnosisNum", Long.class);

    public final StringPath postEditRx = createString("postEditRx");

    public final NumberPath<Long> ppNum = createNumber("ppNum", Long.class);

    public final StringPath prescriberOrderNumber = createString("prescriberOrderNumber");

    public final StringPath prescribingReason = createString("prescribingReason");

    public final StringPath prohibitRenewalRequest = createString("prohibitRenewalRequest");

    public final DateTimePath<java.sql.Timestamp> refillByDate = createDateTime("refillByDate", java.sql.Timestamp.class);

    public final StringPath relatesToMessageid = createString("relatesToMessageid");

    public final NumberPath<Integer> relationshipNum = createNumber("relationshipNum", Integer.class);

    public final DateTimePath<java.sql.Timestamp> retailSoldDatetime = createDateTime("retailSoldDatetime", java.sql.Timestamp.class);

    public final StringPath rewriteDueTo = createString("rewriteDueTo");

    public final SimplePath<byte[]> rowversion = createSimple("rowversion", byte[].class);

    public final StringPath rxFillIndicator = createString("rxFillIndicator");

    public final NumberPath<java.math.BigInteger> rxLink = createNumber("rxLink", java.math.BigInteger.class);

    public final StringPath rxNumber = createString("rxNumber");

    public final NumberPath<Byte> rxoverride = createNumber("rxoverride", Byte.class);

    public final NumberPath<Long> rxRefillNum = createNumber("rxRefillNum", Long.class);

    public final NumberPath<Long> rxUnitPrice = createNumber("rxUnitPrice", Long.class);

    public final NumberPath<Long> rxWacUnitPrice = createNumber("rxWacUnitPrice", Long.class);

    public final NumberPath<Byte> sanityok = createNumber("sanityok", Byte.class);

    public final NumberPath<Byte> sanityok2 = createNumber("sanityok2", Byte.class);

    public final NumberPath<Byte> sanityok3 = createNumber("sanityok3", Byte.class);

    public final NumberPath<Long> secondaryPpNum = createNumber("secondaryPpNum", Long.class);

    public final StringPath sendFax = createString("sendFax");

    public final StringPath sourceHost = createString("sourceHost");

    public final StringPath sourceUser = createString("sourceUser");

    public final NumberPath<Long> tertiaryPpNum = createNumber("tertiaryPpNum", Long.class);

    public final NumberPath<java.math.BigInteger> tpGlCredited = createNumber("tpGlCredited", java.math.BigInteger.class);

    public final NumberPath<java.math.BigInteger> tpGlPosted = createNumber("tpGlPosted", java.math.BigInteger.class);

    public final DateTimePath<java.sql.Timestamp> translatedDatetime = createDateTime("translatedDatetime", java.sql.Timestamp.class);

    public final StringPath translatedImagePath = createString("translatedImagePath");

    public final StringPath translatedInitials = createString("translatedInitials");

    public final NumberPath<Integer> translatedLanguageId = createNumber("translatedLanguageId", Integer.class);

    public final StringPath translatedSig = createString("translatedSig");

    public final StringPath translatedSigParaphraseId = createString("translatedSigParaphraseId");

    public final StringPath translatedSigPharaphrase = createString("translatedSigPharaphrase");

    public final StringPath triplicateSerialNum = createString("triplicateSerialNum");

    public final NumberPath<Byte> validated = createNumber("validated", Byte.class);

    public final NumberPath<Long> voucherAmount = createNumber("voucherAmount", Long.class);

    public final DateTimePath<java.sql.Timestamp> voucherDatetime = createDateTime("voucherDatetime", java.sql.Timestamp.class);

    public final StringPath voucherNumber = createString("voucherNumber");

    public final NumberPath<Long> voucherOrigPatcopay = createNumber("voucherOrigPatcopay", Long.class);

    public final NumberPath<Long> voucherPatientAmount = createNumber("voucherPatientAmount", Long.class);

    public final DateTimePath<java.sql.Timestamp> workflowComplete = createDateTime("workflowComplete", java.sql.Timestamp.class);

    public QRxFillAux(String variable) {
        super(RxFillAux.class, forVariable(variable), "dbo", "rx_fill_aux");
        addMetadata();
    }

    public QRxFillAux(String variable, String schema, String table) {
        super(RxFillAux.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QRxFillAux(String variable, String schema) {
        super(RxFillAux.class, forVariable(variable), schema, "rx_fill_aux");
        addMetadata();
    }

    public QRxFillAux(Path<? extends RxFillAux> path) {
        super(path.getType(), path.getMetadata(), "dbo", "rx_fill_aux");
        addMetadata();
    }

    public QRxFillAux(PathMetadata metadata) {
        super(RxFillAux.class, metadata, "dbo", "rx_fill_aux");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(allowConsolidate, ColumnMetadata.named("allow_consolidate").withIndex(79).ofType(Types.CHAR).withSize(1));
        addMetadata(attestCollected, ColumnMetadata.named("attest_collected").withIndex(72).ofType(Types.CHAR).withSize(1));
        addMetadata(autoFillBin, ColumnMetadata.named("auto_fill_bin").withIndex(26).ofType(Types.VARCHAR).withSize(60));
        addMetadata(awpPrice, ColumnMetadata.named("awp_price").withIndex(48).ofType(Types.NUMERIC).withSize(12));
        addMetadata(awpUnitPrice, ColumnMetadata.named("awp_unit_price").withIndex(49).ofType(Types.NUMERIC).withSize(12));
        addMetadata(batchEcs, ColumnMetadata.named("batch_ecs").withIndex(46).ofType(Types.NUMERIC).withSize(1));
        addMetadata(calculatedDaysSupply, ColumnMetadata.named("calculated_days_supply").withIndex(12).ofType(Types.NUMERIC).withSize(5));
        addMetadata(calculatedInvPrice, ColumnMetadata.named("calculated_inv_price").withIndex(47).ofType(Types.NUMERIC).withSize(12));
        addMetadata(calculatedInvWacPrice, ColumnMetadata.named("calculated_inv_wac_price").withIndex(63).ofType(Types.NUMERIC).withSize(12));
        addMetadata(calculatedPlanPrice, ColumnMetadata.named("calculated_plan_price").withIndex(31).ofType(Types.NUMERIC).withSize(12));
        addMetadata(clinicId, ColumnMetadata.named("clinic_id").withIndex(33).ofType(Types.VARCHAR).withSize(60));
        addMetadata(cobCheck, ColumnMetadata.named("cob_check").withIndex(50).ofType(Types.NUMERIC).withSize(1));
        addMetadata(compProdSeq, ColumnMetadata.named("comp_prod_seq").withIndex(52).ofType(Types.NUMERIC).withSize(16));
        addMetadata(consentDatetime, ColumnMetadata.named("consent_datetime").withIndex(81).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(consentStatus, ColumnMetadata.named("consent_status").withIndex(80).ofType(Types.VARCHAR).withSize(60));
        addMetadata(consentUsernum, ColumnMetadata.named("consent_usernum").withIndex(82).ofType(Types.NUMERIC).withSize(16));
        addMetadata(consolidated, ColumnMetadata.named("consolidated").withIndex(74).ofType(Types.CHAR).withSize(1));
        addMetadata(conversionLink, ColumnMetadata.named("conversion_link").withIndex(27).ofType(Types.VARCHAR).withSize(60));
        addMetadata(dailyTests, ColumnMetadata.named("daily_tests").withIndex(73).ofType(Types.NUMERIC).withSize(2));
        addMetadata(dateProcessed, ColumnMetadata.named("date_processed").withIndex(22).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(deFirstName, ColumnMetadata.named("de_first_name").withIndex(85).ofType(Types.VARCHAR).withSize(60));
        addMetadata(deLastName, ColumnMetadata.named("de_last_name").withIndex(86).ofType(Types.VARCHAR).withSize(60));
        addMetadata(dePhoneAreaCode, ColumnMetadata.named("de_phone_area_code").withIndex(87).ofType(Types.VARCHAR).withSize(3));
        addMetadata(dePhoneNumber, ColumnMetadata.named("de_phone_number").withIndex(88).ofType(Types.VARCHAR).withSize(7));
        addMetadata(directEntryValue, ColumnMetadata.named("direct_entry_value").withIndex(106).ofType(Types.VARCHAR).withSize(1));
        addMetadata(drugDbcode, ColumnMetadata.named("drug_dbcode").withIndex(95).ofType(Types.VARCHAR).withSize(35));
        addMetadata(drugDbcodeQualifier, ColumnMetadata.named("drug_dbcode_qualifier").withIndex(96).ofType(Types.VARCHAR).withSize(3));
        addMetadata(drugdbWacUnit, ColumnMetadata.named("drugdb_wac_unit").withIndex(75).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ecsBusy, ColumnMetadata.named("ecs_busy").withIndex(35).ofType(Types.NUMERIC).withSize(1));
        addMetadata(ecsclarMetricQuantity, ColumnMetadata.named("ecsclar_metric_quantity").withIndex(68).ofType(Types.NUMERIC).withSize(8).withDigits(3));
        addMetadata(erxByPassed, ColumnMetadata.named("erx_by_passed").withIndex(98).ofType(Types.CHAR).withSize(1));
        addMetadata(erxReviewDatetime, ColumnMetadata.named("erx_review_datetime").withIndex(91).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(erxReviewUsernum, ColumnMetadata.named("erx_review_usernum").withIndex(90).ofType(Types.NUMERIC).withSize(16));
        addMetadata(eScriptMsgAttributeSeq, ColumnMetadata.named("e_script_msg_attribute_seq").withIndex(1).ofType(Types.NUMERIC).withSize(35).notNull());
        addMetadata(externalLink, ColumnMetadata.named("external_link").withIndex(32).ofType(Types.VARCHAR).withSize(60));
        addMetadata(fillAutoStatus, ColumnMetadata.named("fill_auto_status").withIndex(38).ofType(Types.NUMERIC).withSize(1));
        addMetadata(fillCob3Status, ColumnMetadata.named("fill_cob3_status").withIndex(51).ofType(Types.NUMERIC).withSize(30));
        addMetadata(fillCobStatus, ColumnMetadata.named("fill_cob_status").withIndex(37).ofType(Types.NUMERIC).withSize(30));
        addMetadata(fillDaysSupply, ColumnMetadata.named("fill_days_supply").withIndex(10).ofType(Types.NUMERIC).withSize(5));
        addMetadata(fillDiscardDate, ColumnMetadata.named("fill_discard_date").withIndex(39).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(fillDispenseDate, ColumnMetadata.named("fill_dispense_date").withIndex(8).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(fillEcsOverrideDate, ColumnMetadata.named("fill_ecs_override_date").withIndex(13).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(fillEcsStatus, ColumnMetadata.named("fill_ecs_status").withIndex(11).ofType(Types.NUMERIC).withSize(30));
        addMetadata(fillExpirationDate, ColumnMetadata.named("fill_expiration_date").withIndex(30).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(fillInvStatus, ColumnMetadata.named("fill_inv_status").withIndex(25).ofType(Types.NUMERIC).withSize(3));
        addMetadata(fillPostcheckDatetime, ColumnMetadata.named("fill_postcheck_datetime").withIndex(20).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(fillPostcheckRphInitials, ColumnMetadata.named("fill_postcheck_rph_initials").withIndex(17).ofType(Types.VARCHAR).withSize(15));
        addMetadata(fillPrecheckDatetime, ColumnMetadata.named("fill_precheck_datetime").withIndex(19).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(fillPrecheckRphInitials, ColumnMetadata.named("fill_precheck_rph_initials").withIndex(15).ofType(Types.VARCHAR).withSize(15));
        addMetadata(fillQtyDispensed, ColumnMetadata.named("fill_qty_dispensed").withIndex(9).ofType(Types.NUMERIC).withSize(15));
        addMetadata(fillRxRphInitials, ColumnMetadata.named("fill_rx_rph_initials").withIndex(18).ofType(Types.VARCHAR).withSize(15));
        addMetadata(fillShipDate, ColumnMetadata.named("fill_ship_date").withIndex(42).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(fillStatusNum, ColumnMetadata.named("fill_status_num").withIndex(5).ofType(Types.NUMERIC).withSize(2).notNull());
        addMetadata(followUpPrescriberFirstName, ColumnMetadata.named("FollowUpPrescriber_First_Name").withIndex(114).ofType(Types.VARCHAR).withSize(35));
        addMetadata(followUpPrescriberLastName, ColumnMetadata.named("FollowUpPrescriber_Last_Name").withIndex(113).ofType(Types.VARCHAR).withSize(35));
        addMetadata(followUpPrescriberMiddleName, ColumnMetadata.named("FollowUpPrescriber_Middle_Name").withIndex(115).ofType(Types.VARCHAR).withSize(35));
        addMetadata(ftfMasterDays, ColumnMetadata.named("ftf_master_days").withIndex(76).ofType(Types.NUMERIC).withSize(5));
        addMetadata(ftfMasterQty, ColumnMetadata.named("ftf_master_qty").withIndex(77).ofType(Types.NUMERIC).withSize(5));
        addMetadata(ingredientPaidAmt, ColumnMetadata.named("ingredient_paid_amt").withIndex(62).ofType(Types.NUMERIC).withSize(12));
        addMetadata(initialFillDays, ColumnMetadata.named("initial_fill_days").withIndex(71).ofType(Types.NUMERIC).withSize(5));
        addMetadata(initialFillQty, ColumnMetadata.named("initial_fill_qty").withIndex(70).ofType(Types.NUMERIC).withSize(5));
        addMetadata(itemsDispensed, ColumnMetadata.named("items_dispensed").withIndex(53).ofType(Types.NUMERIC).withSize(5));
        addMetadata(messageId, ColumnMetadata.named("message_id").withIndex(97).ofType(Types.VARCHAR).withSize(35));
        addMetadata(metricQuantity, ColumnMetadata.named("metric_quantity").withIndex(61).ofType(Types.NUMERIC).withSize(12));
        addMetadata(mybInfo, ColumnMetadata.named("myb_info").withIndex(78).ofType(Types.VARCHAR).withSize(60));
        addMetadata(numberMetricItems, ColumnMetadata.named("number_metric_items").withIndex(59).ofType(Types.NUMERIC).withSize(5));
        addMetadata(originalRxNumber, ColumnMetadata.named("original_rx_number").withIndex(104).ofType(Types.VARCHAR).withSize(25));
        addMetadata(ortfQty, ColumnMetadata.named("ortf_qty").withIndex(66).ofType(Types.NUMERIC).withSize(12));
        addMetadata(patientCopay, ColumnMetadata.named("patient_copay").withIndex(16).ofType(Types.NUMERIC).withSize(12));
        addMetadata(patientLocationId, ColumnMetadata.named("patient_location_id").withIndex(3).ofType(Types.NUMERIC).withSize(2));
        addMetadata(payorPaidAmt, ColumnMetadata.named("payor_paid_amt").withIndex(54).ofType(Types.NUMERIC).withSize(12));
        addMetadata(pcDiagnosisNum, ColumnMetadata.named("pc_diagnosis_num").withIndex(40).ofType(Types.NUMERIC).withSize(16));
        addMetadata(postEditRx, ColumnMetadata.named("post_edit_rx").withIndex(21).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(ppNum, ColumnMetadata.named("pp_num").withIndex(2).ofType(Types.NUMERIC).withSize(16));
        addMetadata(prescriberOrderNumber, ColumnMetadata.named("prescriber_order_number").withIndex(94).ofType(Types.VARCHAR).withSize(35));
        addMetadata(prescribingReason, ColumnMetadata.named("prescribing_reason").withIndex(28).ofType(Types.VARCHAR).withSize(60));
        addMetadata(prohibitRenewalRequest, ColumnMetadata.named("ProhibitRenewalRequest").withIndex(112).ofType(Types.CHAR).withSize(1));
        addMetadata(refillByDate, ColumnMetadata.named("refill_by_date").withIndex(29).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(relatesToMessageid, ColumnMetadata.named("relates_to_messageid").withIndex(93).ofType(Types.VARCHAR).withSize(35));
        addMetadata(relationshipNum, ColumnMetadata.named("relationship_num").withIndex(7).ofType(Types.NUMERIC).withSize(5).notNull());
        addMetadata(retailSoldDatetime, ColumnMetadata.named("retail_sold_datetime").withIndex(23).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(rewriteDueTo, ColumnMetadata.named("rewrite_due_to").withIndex(105).ofType(Types.VARCHAR).withSize(10));
        addMetadata(rowversion, ColumnMetadata.named("rowversion").withIndex(89).ofType(Types.BINARY).withSize(8).notNull());
        addMetadata(rxFillIndicator, ColumnMetadata.named("RxFillIndicator").withIndex(111).ofType(Types.VARCHAR).withSize(100));
        addMetadata(rxLink, ColumnMetadata.named("rx_link").withIndex(45).ofType(Types.NUMERIC).withSize(35));
        addMetadata(rxNumber, ColumnMetadata.named("rx_number").withIndex(55).ofType(Types.VARCHAR).withSize(60));
        addMetadata(rxoverride, ColumnMetadata.named("rxoverride").withIndex(69).ofType(Types.NUMERIC).withSize(1));
        addMetadata(rxRefillNum, ColumnMetadata.named("rx_refill_num").withIndex(6).ofType(Types.NUMERIC).withSize(10).notNull());
        addMetadata(rxUnitPrice, ColumnMetadata.named("rx_unit_price").withIndex(36).ofType(Types.NUMERIC).withSize(12));
        addMetadata(rxWacUnitPrice, ColumnMetadata.named("rx_wac_unit_price").withIndex(64).ofType(Types.NUMERIC).withSize(12));
        addMetadata(sanityok, ColumnMetadata.named("sanityok").withIndex(41).ofType(Types.NUMERIC).withSize(1));
        addMetadata(sanityok2, ColumnMetadata.named("sanityok2").withIndex(60).ofType(Types.NUMERIC).withSize(1));
        addMetadata(sanityok3, ColumnMetadata.named("sanityok3").withIndex(67).ofType(Types.NUMERIC).withSize(1));
        addMetadata(secondaryPpNum, ColumnMetadata.named("secondary_pp_num").withIndex(4).ofType(Types.NUMERIC).withSize(16));
        addMetadata(sendFax, ColumnMetadata.named("send_fax").withIndex(44).ofType(Types.CHAR).withSize(1));
        addMetadata(sourceHost, ColumnMetadata.named("source_host").withIndex(83).ofType(Types.VARCHAR).withSize(60));
        addMetadata(sourceUser, ColumnMetadata.named("source_user").withIndex(84).ofType(Types.VARCHAR).withSize(60));
        addMetadata(tertiaryPpNum, ColumnMetadata.named("tertiary_pp_num").withIndex(92).ofType(Types.NUMERIC).withSize(16));
        addMetadata(tpGlCredited, ColumnMetadata.named("tp_gl_credited").withIndex(24).ofType(Types.NUMERIC).withSize(30));
        addMetadata(tpGlPosted, ColumnMetadata.named("tp_gl_posted").withIndex(14).ofType(Types.NUMERIC).withSize(30));
        addMetadata(translatedDatetime, ColumnMetadata.named("translated_datetime").withIndex(57).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(translatedImagePath, ColumnMetadata.named("translated_image_path").withIndex(107).ofType(Types.VARCHAR).withSize(1000));
        addMetadata(translatedInitials, ColumnMetadata.named("translated_initials").withIndex(58).ofType(Types.VARCHAR).withSize(10));
        addMetadata(translatedLanguageId, ColumnMetadata.named("translated_language_id").withIndex(109).ofType(Types.NUMERIC).withSize(5));
        addMetadata(translatedSig, ColumnMetadata.named("translated_sig").withIndex(56).ofType(Types.NVARCHAR).withSize(1000));
        addMetadata(translatedSigParaphraseId, ColumnMetadata.named("translated_sig_paraphrase_id").withIndex(108).ofType(Types.VARCHAR).withSize(255));
        addMetadata(translatedSigPharaphrase, ColumnMetadata.named("translated_sig_pharaphrase").withIndex(110).ofType(Types.VARCHAR).withSize(1000));
        addMetadata(triplicateSerialNum, ColumnMetadata.named("triplicate_serial_num").withIndex(34).ofType(Types.VARCHAR).withSize(60));
        addMetadata(validated, ColumnMetadata.named("validated").withIndex(65).ofType(Types.NUMERIC).withSize(1));
        addMetadata(voucherAmount, ColumnMetadata.named("voucher_amount").withIndex(99).ofType(Types.NUMERIC).withSize(12));
        addMetadata(voucherDatetime, ColumnMetadata.named("voucher_datetime").withIndex(102).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(voucherNumber, ColumnMetadata.named("voucher_number").withIndex(101).ofType(Types.VARCHAR).withSize(60));
        addMetadata(voucherOrigPatcopay, ColumnMetadata.named("voucher_orig_patcopay").withIndex(103).ofType(Types.NUMERIC).withSize(12));
        addMetadata(voucherPatientAmount, ColumnMetadata.named("voucher_patient_amount").withIndex(100).ofType(Types.NUMERIC).withSize(12));
        addMetadata(workflowComplete, ColumnMetadata.named("workflow_complete").withIndex(43).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
    }

}

