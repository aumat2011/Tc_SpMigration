package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.PatientAttributes;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QPatientAttributes is a Querydsl query type for PatientAttributes
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QPatientAttributes extends com.querydsl.sql.RelationalPathBase<PatientAttributes> {

    private static final long serialVersionUID = 738971938;

    public static final QPatientAttributes patientAttributes = new QPatientAttributes("patient_attributes");

    public final StringPath autoRefill = createString("autoRefill");

    public final StringPath billSecondaryCoverage = createString("billSecondaryCoverage");

    public final NumberPath<Long> copayConsentAmount = createNumber("copayConsentAmount", Long.class);

    public final StringPath copayConsentRequired = createString("copayConsentRequired");

    public final NumberPath<Long> copayDiffAmount = createNumber("copayDiffAmount", Long.class);

    public final StringPath copayDifferential = createString("copayDifferential");

    public final StringPath docDelivery = createString("docDelivery");

    public final StringPath facilityId = createString("facilityId");

    public final StringPath insulinPumpRequired = createString("insulinPumpRequired");

    public final StringPath other = createString("other");

    public final StringPath patientBloodGlucoseLevel = createString("patientBloodGlucoseLevel");

    public final StringPath patientBloodPressure = createString("patientBloodPressure");

    public final StringPath patientBloodType = createString("patientBloodType");

    public final StringPath patientCholesterolLevel = createString("patientCholesterolLevel");

    public final StringPath patientDisabled = createString("patientDisabled");

    public final StringPath patientEyeColor = createString("patientEyeColor");

    public final StringPath patientFamilyPlanning = createString("patientFamilyPlanning");

    public final StringPath patientGenIfAvail = createString("patientGenIfAvail");

    public final StringPath patientHairColor = createString("patientHairColor");

    public final StringPath patientHbA1CLevel = createString("patientHbA1CLevel");

    public final StringPath patientHearingImpaired = createString("patientHearingImpaired");

    public final NumberPath<Integer> patientHeight = createNumber("patientHeight", Integer.class);

    public final NumberPath<Byte> patientLocationId = createNumber("patientLocationId", Byte.class);

    public final StringPath patientMarried = createString("patientMarried");

    public final NumberPath<java.math.BigInteger> patientNum = createNumber("patientNum", java.math.BigInteger.class);

    public final StringPath patientPreauthApproval = createString("patientPreauthApproval");

    public final StringPath patientPregnant = createString("patientPregnant");

    public final StringPath patientRefillReminder = createString("patientRefillReminder");

    public final NumberPath<Byte> patientResidenceId = createNumber("patientResidenceId", Byte.class);

    public final StringPath patientRetired = createString("patientRetired");

    public final StringPath patientSafetyCaps = createString("patientSafetyCaps");

    public final StringPath patientSchool = createString("patientSchool");

    public final StringPath patientSchoolStatus = createString("patientSchoolStatus");

    public final NumberPath<Integer> patientServiceId = createNumber("patientServiceId", Integer.class);

    public final StringPath patientSmokes = createString("patientSmokes");

    public final StringPath patientStudent = createString("patientStudent");

    public final StringPath patientTwin = createString("patientTwin");

    public final StringPath patientVisuallyImpaired = createString("patientVisuallyImpaired");

    public final NumberPath<Integer> patientWeight = createNumber("patientWeight", Integer.class);

    public final NumberPath<Integer> paymentMethodId = createNumber("paymentMethodId", Integer.class);

    public final NumberPath<Long> pcTradingPartnerNum = createNumber("pcTradingPartnerNum", Long.class);

    public final NumberPath<Integer> pickupDeliveryMethodsSeq = createNumber("pickupDeliveryMethodsSeq", Integer.class);

    public final StringPath prodConsolidate = createString("prodConsolidate");

    public final NumberPath<Integer> raceId = createNumber("raceId", Integer.class);

    public final NumberPath<Integer> religionNum = createNumber("religionNum", Integer.class);

    public final NumberPath<Integer> shipMethodId = createNumber("shipMethodId", Integer.class);

    public final StringPath shippingAccountNumber = createString("shippingAccountNumber");

    public final NumberPath<Integer> speciesNum = createNumber("speciesNum", Integer.class);

    public final StringPath workflowAutoNotify = createString("workflowAutoNotify");

    public QPatientAttributes(String variable) {
        super(PatientAttributes.class, forVariable(variable), "dbo", "patient_attributes");
        addMetadata();
    }

    public QPatientAttributes(String variable, String schema, String table) {
        super(PatientAttributes.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QPatientAttributes(String variable, String schema) {
        super(PatientAttributes.class, forVariable(variable), schema, "patient_attributes");
        addMetadata();
    }

    public QPatientAttributes(Path<? extends PatientAttributes> path) {
        super(path.getType(), path.getMetadata(), "dbo", "patient_attributes");
        addMetadata();
    }

    public QPatientAttributes(PathMetadata metadata) {
        super(PatientAttributes.class, metadata, "dbo", "patient_attributes");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(autoRefill, ColumnMetadata.named("auto_refill").withIndex(46).ofType(Types.CHAR).withSize(1));
        addMetadata(billSecondaryCoverage, ColumnMetadata.named("bill_secondary_coverage").withIndex(31).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(copayConsentAmount, ColumnMetadata.named("copay_consent_amount").withIndex(48).ofType(Types.NUMERIC).withSize(12));
        addMetadata(copayConsentRequired, ColumnMetadata.named("copay_consent_required").withIndex(47).ofType(Types.CHAR).withSize(1));
        addMetadata(copayDiffAmount, ColumnMetadata.named("copay_diff_amount").withIndex(41).ofType(Types.NUMERIC).withSize(12));
        addMetadata(copayDifferential, ColumnMetadata.named("copay_differential").withIndex(40).ofType(Types.CHAR).withSize(1));
        addMetadata(docDelivery, ColumnMetadata.named("doc_delivery").withIndex(42).ofType(Types.CHAR).withSize(1));
        addMetadata(facilityId, ColumnMetadata.named("facility_id").withIndex(29).ofType(Types.VARCHAR).withSize(10));
        addMetadata(insulinPumpRequired, ColumnMetadata.named("insulin_pump_required").withIndex(49).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(other, ColumnMetadata.named("other").withIndex(37).ofType(Types.VARCHAR).withSize(256));
        addMetadata(patientBloodGlucoseLevel, ColumnMetadata.named("patient_blood_glucose_level").withIndex(13).ofType(Types.CHAR).withSize(6));
        addMetadata(patientBloodPressure, ColumnMetadata.named("patient_blood_pressure").withIndex(14).ofType(Types.CHAR).withSize(10));
        addMetadata(patientBloodType, ColumnMetadata.named("patient_blood_type").withIndex(34).ofType(Types.VARCHAR).withSize(60));
        addMetadata(patientCholesterolLevel, ColumnMetadata.named("patient_cholesterol_level").withIndex(15).ofType(Types.CHAR).withSize(4));
        addMetadata(patientDisabled, ColumnMetadata.named("patient_disabled").withIndex(20).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(patientEyeColor, ColumnMetadata.named("patient_eye_color").withIndex(33).ofType(Types.VARCHAR).withSize(60));
        addMetadata(patientFamilyPlanning, ColumnMetadata.named("patient_family_planning").withIndex(22).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(patientGenIfAvail, ColumnMetadata.named("patient_gen_if_avail").withIndex(17).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(patientHairColor, ColumnMetadata.named("patient_hair_color").withIndex(32).ofType(Types.VARCHAR).withSize(60));
        addMetadata(patientHbA1CLevel, ColumnMetadata.named("patient_HbA1C_level").withIndex(16).ofType(Types.CHAR).withSize(5));
        addMetadata(patientHearingImpaired, ColumnMetadata.named("patient_hearing_impaired").withIndex(45).ofType(Types.CHAR).withSize(1));
        addMetadata(patientHeight, ColumnMetadata.named("patient_height").withIndex(2).ofType(Types.NUMERIC).withSize(5));
        addMetadata(patientLocationId, ColumnMetadata.named("patient_location_id").withIndex(7).ofType(Types.NUMERIC).withSize(2));
        addMetadata(patientMarried, ColumnMetadata.named("patient_married").withIndex(19).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(patientNum, ColumnMetadata.named("patient_num").withIndex(1).ofType(Types.NUMERIC).withSize(20).notNull());
        addMetadata(patientPreauthApproval, ColumnMetadata.named("patient_preauth_approval").withIndex(35).ofType(Types.CHAR).withSize(1));
        addMetadata(patientPregnant, ColumnMetadata.named("patient_pregnant").withIndex(28).ofType(Types.CHAR).withSize(1));
        addMetadata(patientRefillReminder, ColumnMetadata.named("patient_refill_reminder").withIndex(27).ofType(Types.CHAR).withSize(1));
        addMetadata(patientResidenceId, ColumnMetadata.named("patient_residence_id").withIndex(39).ofType(Types.NUMERIC).withSize(2));
        addMetadata(patientRetired, ColumnMetadata.named("patient_retired").withIndex(26).ofType(Types.CHAR).withSize(1));
        addMetadata(patientSafetyCaps, ColumnMetadata.named("patient_safety_caps").withIndex(21).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(patientSchool, ColumnMetadata.named("patient_school").withIndex(24).ofType(Types.VARCHAR).withSize(30));
        addMetadata(patientSchoolStatus, ColumnMetadata.named("patient_school_status").withIndex(8).ofType(Types.VARCHAR).withSize(2));
        addMetadata(patientServiceId, ColumnMetadata.named("patient_service_id").withIndex(44).ofType(Types.NUMERIC).withSize(5));
        addMetadata(patientSmokes, ColumnMetadata.named("patient_smokes").withIndex(11).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(patientStudent, ColumnMetadata.named("patient_student").withIndex(23).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(patientTwin, ColumnMetadata.named("patient_twin").withIndex(18).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(patientVisuallyImpaired, ColumnMetadata.named("patient_visually_impaired").withIndex(25).ofType(Types.CHAR).withSize(1));
        addMetadata(patientWeight, ColumnMetadata.named("patient_weight").withIndex(10).ofType(Types.NUMERIC).withSize(5));
        addMetadata(paymentMethodId, ColumnMetadata.named("payment_method_id").withIndex(5).ofType(Types.NUMERIC).withSize(5));
        addMetadata(pcTradingPartnerNum, ColumnMetadata.named("pc_trading_partner_num").withIndex(38).ofType(Types.NUMERIC).withSize(16));
        addMetadata(pickupDeliveryMethodsSeq, ColumnMetadata.named("pickup_delivery_methods_seq").withIndex(6).ofType(Types.NUMERIC).withSize(5));
        addMetadata(prodConsolidate, ColumnMetadata.named("prod_consolidate").withIndex(43).ofType(Types.CHAR).withSize(1));
        addMetadata(raceId, ColumnMetadata.named("race_id").withIndex(9).ofType(Types.NUMERIC).withSize(5));
        addMetadata(religionNum, ColumnMetadata.named("religion_num").withIndex(3).ofType(Types.NUMERIC).withSize(5));
        addMetadata(shipMethodId, ColumnMetadata.named("ship_method_id").withIndex(4).ofType(Types.NUMERIC).withSize(5));
        addMetadata(shippingAccountNumber, ColumnMetadata.named("shipping_account_number").withIndex(36).ofType(Types.VARCHAR).withSize(60));
        addMetadata(speciesNum, ColumnMetadata.named("species_num").withIndex(12).ofType(Types.NUMERIC).withSize(5));
        addMetadata(workflowAutoNotify, ColumnMetadata.named("workflow_auto_notify").withIndex(30).ofType(Types.CHAR).withSize(1).notNull());
    }

}

