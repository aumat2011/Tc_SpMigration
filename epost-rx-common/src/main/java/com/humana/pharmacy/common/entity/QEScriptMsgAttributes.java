package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.EScriptMsgAttributes;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QEScriptMsgAttributes is a Querydsl query type for EScriptMsgAttributes
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QEScriptMsgAttributes extends com.querydsl.sql.RelationalPathBase<EScriptMsgAttributes> {

    private static final long serialVersionUID = 1377093474;

    public static final QEScriptMsgAttributes eScriptMsgAttributes = new QEScriptMsgAttributes("e_script_msg_attributes");

    public final NumberPath<Long> allocated = createNumber("allocated", Long.class);

    public final StringPath autoRefill = createString("autoRefill");

    public final StringPath cloneRx = createString("cloneRx");

    public final StringPath conversionLink = createString("conversionLink");

    public final StringPath copayConsentFlag = createString("copayConsentFlag");

    public final NumberPath<Integer> couponNum = createNumber("couponNum", Integer.class);

    public final StringPath cycleFillRunNumber = createString("cycleFillRunNumber");

    public final DateTimePath<java.sql.Timestamp> dateFirstFilled = createDateTime("dateFirstFilled", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> dateLastFilled = createDateTime("dateLastFilled", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> dispensedDate = createDateTime("dispensedDate", java.sql.Timestamp.class);

    public final StringPath dispensedDrugDaw = createString("dispensedDrugDaw");

    public final StringPath dispensedDrugDawDesc = createString("dispensedDrugDawDesc");

    public final StringPath dispensedDrugForm = createString("dispensedDrugForm");

    public final StringPath dispensedDrugFormDesc = createString("dispensedDrugFormDesc");

    public final StringPath dispensedDrugItemAgency = createString("dispensedDrugItemAgency");

    public final StringPath dispensedDrugSig = createString("dispensedDrugSig");

    public final StringPath dispensedDrugStrength = createString("dispensedDrugStrength");

    public final StringPath dispensedProduct = createString("dispensedProduct");

    public final NumberPath<Long> dispensedProductId = createNumber("dispensedProductId", Long.class);

    public final StringPath dispensedProductNumber = createString("dispensedProductNumber");

    public final NumberPath<Long> dispensedQuantity = createNumber("dispensedQuantity", Long.class);

    public final StringPath doctorAddress = createString("doctorAddress");

    public final StringPath doctorCity = createString("doctorCity");

    public final StringPath doctorContactNumber = createString("doctorContactNumber");

    public final StringPath doctorContactType = createString("doctorContactType");

    public final StringPath doctorContactTypeDesc = createString("doctorContactTypeDesc");

    public final StringPath doctorFirstName = createString("doctorFirstName");

    public final StringPath doctorId = createString("doctorId");

    public final StringPath doctorIdType = createString("doctorIdType");

    public final StringPath doctorIdTypeDesc = createString("doctorIdTypeDesc");

    public final StringPath doctorLastName = createString("doctorLastName");

    public final StringPath doctorMiddleName = createString("doctorMiddleName");

    public final NumberPath<Long> doctorNum = createNumber("doctorNum", Long.class);

    public final StringPath doctorPracticeName = createString("doctorPracticeName");

    public final StringPath doctorState = createString("doctorState");

    public final StringPath doctorZip = createString("doctorZip");

    public final NumberPath<java.math.BigInteger> ediMessageId = createNumber("ediMessageId", java.math.BigInteger.class);

    public final StringPath ediTransactionCode = createString("ediTransactionCode");

    public final StringPath erxId = createString("erxId");

    public final StringPath erxMsgid = createString("erxMsgid");

    public final StringPath erxPo = createString("erxPo");

    public final NumberPath<java.math.BigInteger> eScriptMsgAttributeSeq = createNumber("eScriptMsgAttributeSeq", java.math.BigInteger.class);

    public final StringPath freeText = createString("freeText");

    public final StringPath imageAvailable = createString("imageAvailable");

    public final NumberPath<java.math.BigInteger> imageTriageSeq = createNumber("imageTriageSeq", java.math.BigInteger.class);

    public final StringPath isMdpQueue = createString("isMdpQueue");

    public final StringPath isMedsyncQueue = createString("isMedsyncQueue");

    public final DateTimePath<java.sql.Timestamp> lastRefillDate = createDateTime("lastRefillDate", java.sql.Timestamp.class);

    public final NumberPath<Long> numRefills = createNumber("numRefills", Long.class);

    public final StringPath orderInvoiceNumber = createString("orderInvoiceNumber");

    public final NumberPath<Long> originalNumRefills = createNumber("originalNumRefills", Long.class);

    public final NumberPath<Integer> originationNum = createNumber("originationNum", Integer.class);

    public final NumberPath<Long> otcPpnum = createNumber("otcPpnum", Long.class);

    public final StringPath outreachMedication = createString("outreachMedication");

    public final StringPath patientAddress = createString("patientAddress");

    public final StringPath patientAddress2 = createString("patientAddress2");

    public final StringPath patientCardholderId = createString("patientCardholderId");

    public final StringPath patientCardholderName = createString("patientCardholderName");

    public final NumberPath<java.math.BigInteger> patientCareOrderNum = createNumber("patientCareOrderNum", java.math.BigInteger.class);

    public final StringPath patientCity = createString("patientCity");

    public final StringPath patientContactNumber = createString("patientContactNumber");

    public final StringPath patientContactType = createString("patientContactType");

    public final StringPath patientContactTypeDesc = createString("patientContactTypeDesc");

    public final DateTimePath<java.sql.Timestamp> patientDob = createDateTime("patientDob", java.sql.Timestamp.class);

    public final StringPath patientFirstName = createString("patientFirstName");

    public final StringPath patientGender = createString("patientGender");

    public final StringPath patientGroupName = createString("patientGroupName");

    public final StringPath patientGroupNumber = createString("patientGroupNumber");

    public final StringPath patientId = createString("patientId");

    public final StringPath patientIdType = createString("patientIdType");

    public final StringPath patientIdTypeDesc = createString("patientIdTypeDesc");

    public final StringPath patientLastName = createString("patientLastName");

    public final StringPath patientMiddleName = createString("patientMiddleName");

    public final NumberPath<java.math.BigInteger> patientNum = createNumber("patientNum", java.math.BigInteger.class);

    public final NumberPath<Long> patientPetSeq = createNumber("patientPetSeq", Long.class);

    public final StringPath patientState = createString("patientState");

    public final StringPath patientZip = createString("patientZip");

    public final NumberPath<java.math.BigInteger> patPayScheduleSeq = createNumber("patPayScheduleSeq", java.math.BigInteger.class);

    public final StringPath pharmacistFirstName = createString("pharmacistFirstName");

    public final StringPath pharmacistLastName = createString("pharmacistLastName");

    public final StringPath pharmacistMiddleName = createString("pharmacistMiddleName");

    public final StringPath pharmacyAddress = createString("pharmacyAddress");

    public final StringPath pharmacyCity = createString("pharmacyCity");

    public final StringPath pharmacyContactNumber = createString("pharmacyContactNumber");

    public final StringPath pharmacyContactType = createString("pharmacyContactType");

    public final StringPath pharmacyContactTypeDesc = createString("pharmacyContactTypeDesc");

    public final StringPath pharmacyId = createString("pharmacyId");

    public final StringPath pharmacyIdType = createString("pharmacyIdType");

    public final StringPath pharmacyIdTypeDesc = createString("pharmacyIdTypeDesc");

    public final StringPath pharmacyName = createString("pharmacyName");

    public final StringPath pharmacyState = createString("pharmacyState");

    public final StringPath pharmacyZip = createString("pharmacyZip");

    public final StringPath previousRxNumber = createString("previousRxNumber");

    public final DateTimePath<java.sql.Timestamp> pwoExpDate = createDateTime("pwoExpDate", java.sql.Timestamp.class);

    public final StringPath pwoItem = createString("pwoItem");

    public final StringPath refillStatusResponse = createString("refillStatusResponse");

    public final StringPath refillStatusResponseDesc = createString("refillStatusResponseDesc");

    public final SimplePath<byte[]> rowversion = createSimple("rowversion", byte[].class);

    public final DateTimePath<java.sql.Timestamp> rxExpirationDate = createDateTime("rxExpirationDate", java.sql.Timestamp.class);

    public final StringPath rxNumber = createString("rxNumber");

    public final NumberPath<java.math.BigInteger> rxQtyLeft = createNumber("rxQtyLeft", java.math.BigInteger.class);

    public final NumberPath<Integer> rxRefillsLeft = createNumber("rxRefillsLeft", Integer.class);

    public final NumberPath<Byte> rxStatusCodeNum = createNumber("rxStatusCodeNum", Byte.class);

    public final StringPath scriptLegible = createString("scriptLegible");

    public final DateTimePath<java.sql.Timestamp> shipperPickupDatetime = createDateTime("shipperPickupDatetime", java.sql.Timestamp.class);

    public final StringPath sourceHost = createString("sourceHost");

    public final StringPath sourceUser = createString("sourceUser");

    public final NumberPath<Long> sysUserNum = createNumber("sysUserNum", Long.class);

    public final NumberPath<Long> sysUserTaskId = createNumber("sysUserTaskId", Long.class);

    public final NumberPath<Long> tpAddrSeq = createNumber("tpAddrSeq", Long.class);

    public final NumberPath<Long> tradingPartnerNum = createNumber("tradingPartnerNum", Long.class);

    public final DateTimePath<java.sql.Timestamp> writtenDate = createDateTime("writtenDate", java.sql.Timestamp.class);

    public final StringPath writtenDrugDaw = createString("writtenDrugDaw");

    public final StringPath writtenDrugDawDesc = createString("writtenDrugDawDesc");

    public final StringPath writtenDrugForm = createString("writtenDrugForm");

    public final StringPath writtenDrugFormDesc = createString("writtenDrugFormDesc");

    public final StringPath writtenDrugItemAgency = createString("writtenDrugItemAgency");

    public final StringPath writtenDrugSig = createString("writtenDrugSig");

    public final StringPath writtenDrugSigCode = createString("writtenDrugSigCode");

    public final NumberPath<Long> writtenDrugSigFreq = createNumber("writtenDrugSigFreq", Long.class);

    public final StringPath writtenDrugStrength = createString("writtenDrugStrength");

    public final StringPath writtenProduct = createString("writtenProduct");

    public final NumberPath<Long> writtenProductId = createNumber("writtenProductId", Long.class);

    public final StringPath writtenProductNumber = createString("writtenProductNumber");

    public final NumberPath<Long> writtenQuantity = createNumber("writtenQuantity", Long.class);

    public QEScriptMsgAttributes(String variable) {
        super(EScriptMsgAttributes.class, forVariable(variable), "dbo", "e_script_msg_attributes");
        addMetadata();
    }

    public QEScriptMsgAttributes(String variable, String schema, String table) {
        super(EScriptMsgAttributes.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QEScriptMsgAttributes(String variable, String schema) {
        super(EScriptMsgAttributes.class, forVariable(variable), schema, "e_script_msg_attributes");
        addMetadata();
    }

    public QEScriptMsgAttributes(Path<? extends EScriptMsgAttributes> path) {
        super(path.getType(), path.getMetadata(), "dbo", "e_script_msg_attributes");
        addMetadata();
    }

    public QEScriptMsgAttributes(PathMetadata metadata) {
        super(EScriptMsgAttributes.class, metadata, "dbo", "e_script_msg_attributes");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(allocated, ColumnMetadata.named("allocated").withIndex(104).ofType(Types.NUMERIC).withSize(12));
        addMetadata(autoRefill, ColumnMetadata.named("auto_refill").withIndex(100).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(cloneRx, ColumnMetadata.named("clone_rx").withIndex(99).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(conversionLink, ColumnMetadata.named("conversion_link").withIndex(103).ofType(Types.VARCHAR).withSize(60));
        addMetadata(copayConsentFlag, ColumnMetadata.named("copay_consent_flag").withIndex(123).ofType(Types.CHAR).withSize(1));
        addMetadata(couponNum, ColumnMetadata.named("coupon_num").withIndex(4).ofType(Types.NUMERIC).withSize(5));
        addMetadata(cycleFillRunNumber, ColumnMetadata.named("cycle_fill_run_number").withIndex(102).ofType(Types.VARCHAR).withSize(60));
        addMetadata(dateFirstFilled, ColumnMetadata.named("DateFirstFilled").withIndex(122).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(dateLastFilled, ColumnMetadata.named("DateLastFilled").withIndex(121).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(dispensedDate, ColumnMetadata.named("dispensed_date").withIndex(29).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(dispensedDrugDaw, ColumnMetadata.named("dispensed_drug_daw").withIndex(41).ofType(Types.CHAR).withSize(3));
        addMetadata(dispensedDrugDawDesc, ColumnMetadata.named("dispensed_drug_daw_desc").withIndex(44).ofType(Types.VARCHAR).withSize(60));
        addMetadata(dispensedDrugForm, ColumnMetadata.named("dispensed_drug_form").withIndex(32).ofType(Types.CHAR).withSize(3));
        addMetadata(dispensedDrugFormDesc, ColumnMetadata.named("dispensed_drug_form_desc").withIndex(33).ofType(Types.VARCHAR).withSize(60));
        addMetadata(dispensedDrugItemAgency, ColumnMetadata.named("dispensed_drug_item_agency").withIndex(38).ofType(Types.VARCHAR).withSize(3));
        addMetadata(dispensedDrugSig, ColumnMetadata.named("dispensed_drug_sig").withIndex(37).ofType(Types.VARCHAR).withSize(255));
        addMetadata(dispensedDrugStrength, ColumnMetadata.named("dispensed_drug_strength").withIndex(36).ofType(Types.VARCHAR).withSize(70));
        addMetadata(dispensedProduct, ColumnMetadata.named("dispensed_product").withIndex(56).ofType(Types.VARCHAR).withSize(60));
        addMetadata(dispensedProductId, ColumnMetadata.named("dispensed_product_id").withIndex(22).ofType(Types.NUMERIC).withSize(16));
        addMetadata(dispensedProductNumber, ColumnMetadata.named("dispensed_product_number").withIndex(66).ofType(Types.VARCHAR).withSize(35));
        addMetadata(dispensedQuantity, ColumnMetadata.named("dispensed_quantity").withIndex(58).ofType(Types.NUMERIC).withSize(15));
        addMetadata(doctorAddress, ColumnMetadata.named("doctor_address").withIndex(34).ofType(Types.VARCHAR).withSize(60));
        addMetadata(doctorCity, ColumnMetadata.named("doctor_city").withIndex(39).ofType(Types.VARCHAR).withSize(35));
        addMetadata(doctorContactNumber, ColumnMetadata.named("doctor_contact_number").withIndex(21).ofType(Types.VARCHAR).withSize(80));
        addMetadata(doctorContactType, ColumnMetadata.named("doctor_contact_type").withIndex(27).ofType(Types.CHAR).withSize(3));
        addMetadata(doctorContactTypeDesc, ColumnMetadata.named("doctor_contact_type_desc").withIndex(23).ofType(Types.VARCHAR).withSize(35));
        addMetadata(doctorFirstName, ColumnMetadata.named("doctor_first_name").withIndex(13).ofType(Types.VARCHAR).withSize(60));
        addMetadata(doctorId, ColumnMetadata.named("doctor_id").withIndex(50).ofType(Types.VARCHAR).withSize(35));
        addMetadata(doctorIdType, ColumnMetadata.named("doctor_id_type").withIndex(54).ofType(Types.CHAR).withSize(3));
        addMetadata(doctorIdTypeDesc, ColumnMetadata.named("doctor_id_type_desc").withIndex(43).ofType(Types.VARCHAR).withSize(35));
        addMetadata(doctorLastName, ColumnMetadata.named("doctor_last_name").withIndex(14).ofType(Types.VARCHAR).withSize(60));
        addMetadata(doctorMiddleName, ColumnMetadata.named("doctor_middle_name").withIndex(18).ofType(Types.VARCHAR).withSize(60));
        addMetadata(doctorNum, ColumnMetadata.named("doctor_num").withIndex(5).ofType(Types.NUMERIC).withSize(16));
        addMetadata(doctorPracticeName, ColumnMetadata.named("doctor_practice_name").withIndex(30).ofType(Types.VARCHAR).withSize(60));
        addMetadata(doctorState, ColumnMetadata.named("doctor_state").withIndex(40).ofType(Types.VARCHAR).withSize(9));
        addMetadata(doctorZip, ColumnMetadata.named("doctor_zip").withIndex(46).ofType(Types.VARCHAR).withSize(11));
        addMetadata(ediMessageId, ColumnMetadata.named("edi_message_id").withIndex(70).ofType(Types.NUMERIC).withSize(35).notNull());
        addMetadata(ediTransactionCode, ColumnMetadata.named("edi_transaction_code").withIndex(71).ofType(Types.VARCHAR).withSize(10).notNull());
        addMetadata(erxId, ColumnMetadata.named("erx_id").withIndex(111).ofType(Types.VARCHAR).withSize(60));
        addMetadata(erxMsgid, ColumnMetadata.named("erx_msgid").withIndex(119).ofType(Types.VARCHAR).withSize(60));
        addMetadata(erxPo, ColumnMetadata.named("erx_po").withIndex(114).ofType(Types.VARCHAR).withSize(60));
        addMetadata(eScriptMsgAttributeSeq, ColumnMetadata.named("e_script_msg_attribute_seq").withIndex(1).ofType(Types.NUMERIC).withSize(35).notNull());
        addMetadata(freeText, ColumnMetadata.named("free_text").withIndex(2).ofType(Types.VARCHAR).withSize(250));
        addMetadata(imageAvailable, ColumnMetadata.named("image_available").withIndex(95).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(imageTriageSeq, ColumnMetadata.named("image_triage_seq").withIndex(113).ofType(Types.NUMERIC).withSize(30));
        addMetadata(isMdpQueue, ColumnMetadata.named("is_mdp_queue").withIndex(125).ofType(Types.CHAR).withSize(1));
        addMetadata(isMedsyncQueue, ColumnMetadata.named("is_medsync_queue").withIndex(124).ofType(Types.CHAR).withSize(1));
        addMetadata(lastRefillDate, ColumnMetadata.named("last_refill_date").withIndex(49).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(numRefills, ColumnMetadata.named("num_refills").withIndex(35).ofType(Types.NUMERIC).withSize(15));
        addMetadata(orderInvoiceNumber, ColumnMetadata.named("order_invoice_number").withIndex(94).ofType(Types.VARCHAR).withSize(60));
        addMetadata(originalNumRefills, ColumnMetadata.named("original_num_refills").withIndex(42).ofType(Types.NUMERIC).withSize(15));
        addMetadata(originationNum, ColumnMetadata.named("origination_num").withIndex(7).ofType(Types.NUMERIC).withSize(5));
        addMetadata(otcPpnum, ColumnMetadata.named("otc_ppnum").withIndex(110).ofType(Types.NUMERIC).withSize(16));
        addMetadata(outreachMedication, ColumnMetadata.named("outreach_medication").withIndex(120).ofType(Types.VARCHAR).withSize(120));
        addMetadata(patientAddress, ColumnMetadata.named("patient_address").withIndex(62).ofType(Types.VARCHAR).withSize(60));
        addMetadata(patientAddress2, ColumnMetadata.named("patient_address2").withIndex(118).ofType(Types.VARCHAR).withSize(35));
        addMetadata(patientCardholderId, ColumnMetadata.named("patient_cardholder_id").withIndex(74).ofType(Types.VARCHAR).withSize(60));
        addMetadata(patientCardholderName, ColumnMetadata.named("patient_cardholder_name").withIndex(75).ofType(Types.VARCHAR).withSize(60));
        addMetadata(patientCareOrderNum, ColumnMetadata.named("patient_care_order_num").withIndex(3).ofType(Types.NUMERIC).withSize(20));
        addMetadata(patientCity, ColumnMetadata.named("patient_city").withIndex(63).ofType(Types.VARCHAR).withSize(35));
        addMetadata(patientContactNumber, ColumnMetadata.named("patient_contact_number").withIndex(55).ofType(Types.VARCHAR).withSize(80));
        addMetadata(patientContactType, ColumnMetadata.named("patient_contact_type").withIndex(57).ofType(Types.CHAR).withSize(3));
        addMetadata(patientContactTypeDesc, ColumnMetadata.named("patient_contact_type_desc").withIndex(52).ofType(Types.VARCHAR).withSize(35));
        addMetadata(patientDob, ColumnMetadata.named("patient_dob").withIndex(47).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(patientFirstName, ColumnMetadata.named("patient_first_name").withIndex(16).ofType(Types.VARCHAR).withSize(60));
        addMetadata(patientGender, ColumnMetadata.named("patient_gender").withIndex(61).ofType(Types.CHAR).withSize(1));
        addMetadata(patientGroupName, ColumnMetadata.named("patient_group_name").withIndex(79).ofType(Types.VARCHAR).withSize(60));
        addMetadata(patientGroupNumber, ColumnMetadata.named("patient_group_number").withIndex(78).ofType(Types.VARCHAR).withSize(35));
        addMetadata(patientId, ColumnMetadata.named("patient_id").withIndex(51).ofType(Types.VARCHAR).withSize(35));
        addMetadata(patientIdType, ColumnMetadata.named("patient_id_type").withIndex(60).ofType(Types.CHAR).withSize(3));
        addMetadata(patientIdTypeDesc, ColumnMetadata.named("patient_id_type_desc").withIndex(59).ofType(Types.VARCHAR).withSize(35));
        addMetadata(patientLastName, ColumnMetadata.named("patient_last_name").withIndex(17).ofType(Types.VARCHAR).withSize(60));
        addMetadata(patientMiddleName, ColumnMetadata.named("patient_middle_name").withIndex(53).ofType(Types.VARCHAR).withSize(60));
        addMetadata(patientNum, ColumnMetadata.named("patient_num").withIndex(6).ofType(Types.NUMERIC).withSize(20));
        addMetadata(patientPetSeq, ColumnMetadata.named("patient_pet_seq").withIndex(101).ofType(Types.NUMERIC).withSize(16));
        addMetadata(patientState, ColumnMetadata.named("patient_state").withIndex(64).ofType(Types.VARCHAR).withSize(9));
        addMetadata(patientZip, ColumnMetadata.named("patient_zip").withIndex(65).ofType(Types.VARCHAR).withSize(11));
        addMetadata(patPayScheduleSeq, ColumnMetadata.named("pat_pay_schedule_seq").withIndex(108).ofType(Types.NUMERIC).withSize(30));
        addMetadata(pharmacistFirstName, ColumnMetadata.named("pharmacist_first_name").withIndex(73).ofType(Types.VARCHAR).withSize(60));
        addMetadata(pharmacistLastName, ColumnMetadata.named("pharmacist_last_name").withIndex(77).ofType(Types.VARCHAR).withSize(60));
        addMetadata(pharmacistMiddleName, ColumnMetadata.named("pharmacist_middle_name").withIndex(76).ofType(Types.VARCHAR).withSize(60));
        addMetadata(pharmacyAddress, ColumnMetadata.named("pharmacy_address").withIndex(83).ofType(Types.VARCHAR).withSize(60));
        addMetadata(pharmacyCity, ColumnMetadata.named("pharmacy_city").withIndex(84).ofType(Types.VARCHAR).withSize(60));
        addMetadata(pharmacyContactNumber, ColumnMetadata.named("pharmacy_contact_number").withIndex(80).ofType(Types.VARCHAR).withSize(80));
        addMetadata(pharmacyContactType, ColumnMetadata.named("pharmacy_contact_type").withIndex(81).ofType(Types.CHAR).withSize(3));
        addMetadata(pharmacyContactTypeDesc, ColumnMetadata.named("pharmacy_contact_type_desc").withIndex(82).ofType(Types.VARCHAR).withSize(60));
        addMetadata(pharmacyId, ColumnMetadata.named("pharmacy_id").withIndex(87).ofType(Types.VARCHAR).withSize(60));
        addMetadata(pharmacyIdType, ColumnMetadata.named("pharmacy_id_type").withIndex(88).ofType(Types.CHAR).withSize(3));
        addMetadata(pharmacyIdTypeDesc, ColumnMetadata.named("pharmacy_id_type_desc").withIndex(89).ofType(Types.VARCHAR).withSize(60));
        addMetadata(pharmacyName, ColumnMetadata.named("pharmacy_name").withIndex(72).ofType(Types.VARCHAR).withSize(60));
        addMetadata(pharmacyState, ColumnMetadata.named("pharmacy_state").withIndex(85).ofType(Types.VARCHAR).withSize(9));
        addMetadata(pharmacyZip, ColumnMetadata.named("pharmacy_zip").withIndex(86).ofType(Types.VARCHAR).withSize(11));
        addMetadata(previousRxNumber, ColumnMetadata.named("previous_rx_number").withIndex(97).ofType(Types.VARCHAR).withSize(60));
        addMetadata(pwoExpDate, ColumnMetadata.named("pwo_exp_date").withIndex(107).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(pwoItem, ColumnMetadata.named("pwo_item").withIndex(106).ofType(Types.CHAR).withSize(1));
        addMetadata(refillStatusResponse, ColumnMetadata.named("refill_status_response").withIndex(68).ofType(Types.CHAR).withSize(1));
        addMetadata(refillStatusResponseDesc, ColumnMetadata.named("refill_status_response_desc").withIndex(69).ofType(Types.VARCHAR).withSize(35));
        addMetadata(rowversion, ColumnMetadata.named("rowversion").withIndex(117).ofType(Types.BINARY).withSize(8).notNull());
        addMetadata(rxExpirationDate, ColumnMetadata.named("rx_expiration_date").withIndex(91).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(rxNumber, ColumnMetadata.named("rx_number").withIndex(90).ofType(Types.VARCHAR).withSize(60));
        addMetadata(rxQtyLeft, ColumnMetadata.named("rx_qty_left").withIndex(92).ofType(Types.NUMERIC).withSize(25));
        addMetadata(rxRefillsLeft, ColumnMetadata.named("rx_refills_left").withIndex(93).ofType(Types.NUMERIC).withSize(7));
        addMetadata(rxStatusCodeNum, ColumnMetadata.named("rx_status_code_num").withIndex(8).ofType(Types.NUMERIC).withSize(2));
        addMetadata(scriptLegible, ColumnMetadata.named("script_legible").withIndex(96).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(shipperPickupDatetime, ColumnMetadata.named("shipper_pickup_datetime").withIndex(105).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(sourceHost, ColumnMetadata.named("source_host").withIndex(115).ofType(Types.VARCHAR).withSize(60));
        addMetadata(sourceUser, ColumnMetadata.named("source_user").withIndex(116).ofType(Types.VARCHAR).withSize(60));
        addMetadata(sysUserNum, ColumnMetadata.named("sys_user_num").withIndex(9).ofType(Types.NUMERIC).withSize(16).notNull());
        addMetadata(sysUserTaskId, ColumnMetadata.named("sys_user_task_id").withIndex(109).ofType(Types.NUMERIC).withSize(16));
        addMetadata(tpAddrSeq, ColumnMetadata.named("tp_addr_seq").withIndex(112).ofType(Types.NUMERIC).withSize(16));
        addMetadata(tradingPartnerNum, ColumnMetadata.named("trading_partner_num").withIndex(98).ofType(Types.NUMERIC).withSize(16));
        addMetadata(writtenDate, ColumnMetadata.named("written_date").withIndex(45).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(writtenDrugDaw, ColumnMetadata.named("written_drug_daw").withIndex(48).ofType(Types.CHAR).withSize(3));
        addMetadata(writtenDrugDawDesc, ColumnMetadata.named("written_drug_daw_desc").withIndex(67).ofType(Types.VARCHAR).withSize(60));
        addMetadata(writtenDrugForm, ColumnMetadata.named("written_drug_form").withIndex(20).ofType(Types.CHAR).withSize(3));
        addMetadata(writtenDrugFormDesc, ColumnMetadata.named("written_drug_form_desc").withIndex(15).ofType(Types.VARCHAR).withSize(60));
        addMetadata(writtenDrugItemAgency, ColumnMetadata.named("written_drug_item_agency").withIndex(26).ofType(Types.VARCHAR).withSize(3));
        addMetadata(writtenDrugSig, ColumnMetadata.named("written_drug_sig").withIndex(28).ofType(Types.VARCHAR).withSize(255));
        addMetadata(writtenDrugSigCode, ColumnMetadata.named("written_drug_sig_code").withIndex(24).ofType(Types.VARCHAR).withSize(35));
        addMetadata(writtenDrugSigFreq, ColumnMetadata.named("written_drug_sig_freq").withIndex(25).ofType(Types.NUMERIC).withSize(12));
        addMetadata(writtenDrugStrength, ColumnMetadata.named("written_drug_strength").withIndex(31).ofType(Types.VARCHAR).withSize(70));
        addMetadata(writtenProduct, ColumnMetadata.named("written_product").withIndex(10).ofType(Types.VARCHAR).withSize(120));
        addMetadata(writtenProductId, ColumnMetadata.named("written_product_id").withIndex(11).ofType(Types.NUMERIC).withSize(16));
        addMetadata(writtenProductNumber, ColumnMetadata.named("written_product_number").withIndex(19).ofType(Types.VARCHAR).withSize(35));
        addMetadata(writtenQuantity, ColumnMetadata.named("written_quantity").withIndex(12).ofType(Types.NUMERIC).withSize(15));
    }

}

