package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.PatientGeneral;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QPatientGeneral is a Querydsl query type for PatientGeneral
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QPatientGeneral extends com.querydsl.sql.RelationalPathBase<PatientGeneral> {

    private static final long serialVersionUID = 1024060669;

    public static final QPatientGeneral patientGeneral = new QPatientGeneral("patient_general");

    public final StringPath additionalPatientId = createString("additionalPatientId");

    public final StringPath additionalPatientIdTypeCode = createString("additionalPatientIdTypeCode");

    public final StringPath assessmentEligible = createString("assessmentEligible");

    public final NumberPath<Integer> assessmentWorkflow = createNumber("assessmentWorkflow", Integer.class);

    public final NumberPath<Integer> autoContactTypeId = createNumber("autoContactTypeId", Integer.class);

    public final NumberPath<Integer> careCategoryNum = createNumber("careCategoryNum", Integer.class);

    public final NumberPath<Long> careCoordinatorNum = createNumber("careCoordinatorNum", Long.class);

    public final NumberPath<Integer> careKitSeq = createNumber("careKitSeq", Integer.class);

    public final StringPath cmrStatusCode = createString("cmrStatusCode");

    public final DateTimePath<java.sql.Timestamp> consentAcceptedDate = createDateTime("consentAcceptedDate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> consentEnddatetime = createDateTime("consentEnddatetime", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> consentStartdatetime = createDateTime("consentStartdatetime", java.sql.Timestamp.class);

    public final StringPath consentStatus = createString("consentStatus");

    public final StringPath conversionLink = createString("conversionLink");

    public final DateTimePath<java.sql.Timestamp> dateAdded = createDateTime("dateAdded", java.sql.Timestamp.class);

    public final StringPath externalLink = createString("externalLink");

    public final NumberPath<Long> facilityStationSeq = createNumber("facilityStationSeq", Long.class);

    public final StringPath familyId = createString("familyId");

    public final StringPath genderCode = createString("genderCode");

    public final StringPath generalStatusCode = createString("generalStatusCode");

    public final NumberPath<Short> hippaCounter = createNumber("hippaCounter", Short.class);

    public final StringPath impairmentIndicator = createString("impairmentIndicator");

    public final StringPath isMdp = createString("isMdp");

    public final StringPath isMedSync = createString("isMedSync");

    public final NumberPath<Integer> languageId = createNumber("languageId", Integer.class);

    public final DateTimePath<java.sql.Timestamp> lastAobDatetime = createDateTime("lastAobDatetime", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> marketing = createDateTime("marketing", java.sql.Timestamp.class);

    public final NumberPath<Long> mdpUpdatedBy = createNumber("mdpUpdatedBy", Long.class);

    public final DateTimePath<java.sql.Timestamp> mdpUpdatedDate = createDateTime("mdpUpdatedDate", java.sql.Timestamp.class);

    public final NumberPath<Integer> medSyncUpdatedBy = createNumber("medSyncUpdatedBy", Integer.class);

    public final DateTimePath<java.sql.Timestamp> medSyncUpdatedDate = createDateTime("medSyncUpdatedDate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> nextAssessmentDate = createDateTime("nextAssessmentDate", java.sql.Timestamp.class);

    public final StringPath patientAlias = createString("patientAlias");

    public final DateTimePath<java.sql.Timestamp> patientAobEnddate = createDateTime("patientAobEnddate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> patientAobStartdate = createDateTime("patientAobStartdate", java.sql.Timestamp.class);

    public final NumberPath<Long> patientAttendingPhysician = createNumber("patientAttendingPhysician", Long.class);

    public final NumberPath<Long> patientCreditLimit = createNumber("patientCreditLimit", Long.class);

    public final DateTimePath<java.sql.Timestamp> patientDob = createDateTime("patientDob", java.sql.Timestamp.class);

    public final StringPath patientEmailAddress = createString("patientEmailAddress");

    public final StringPath patientFirstName = createString("patientFirstName");

    public final StringPath patientId = createString("patientId");

    public final StringPath patientIdCode = createString("patientIdCode");

    public final StringPath patientLastName = createString("patientLastName");

    public final StringPath patientMaidenName = createString("patientMaidenName");

    public final StringPath patientMembershipNum = createString("patientMembershipNum");

    public final StringPath patientMiddleName = createString("patientMiddleName");

    public final NumberPath<java.math.BigInteger> patientNum = createNumber("patientNum", java.math.BigInteger.class);

    public final NumberPath<Integer> patientOriginationSeq = createNumber("patientOriginationSeq", Integer.class);

    public final NumberPath<Long> patientPcPhysician = createNumber("patientPcPhysician", Long.class);

    public final NumberPath<Integer> patientPrefHourSeq = createNumber("patientPrefHourSeq", Integer.class);

    public final StringPath patientPrivacy = createString("patientPrivacy");

    public final DateTimePath<java.sql.Timestamp> patientPrivacyEnddate = createDateTime("patientPrivacyEnddate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> patientPrivacySentdate = createDateTime("patientPrivacySentdate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> patientPrivacyStartdate = createDateTime("patientPrivacyStartdate", java.sql.Timestamp.class);

    public final NumberPath<Integer> secondaryLanguageID = createNumber("secondaryLanguageID", Integer.class);

    public final StringPath sourceHost = createString("sourceHost");

    public final StringPath sourceUser = createString("sourceUser");

    public final NumberPath<Integer> titleId = createNumber("titleId", Integer.class);

    public final NumberPath<java.math.BigInteger> tradingPartnerNum = createNumber("tradingPartnerNum", java.math.BigInteger.class);

    public final StringPath verified = createString("verified");

    public final StringPath vip = createString("vip");

    public QPatientGeneral(String variable) {
        super(PatientGeneral.class, forVariable(variable), "dbo", "patient_general");
        addMetadata();
    }

    public QPatientGeneral(String variable, String schema, String table) {
        super(PatientGeneral.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QPatientGeneral(String variable, String schema) {
        super(PatientGeneral.class, forVariable(variable), schema, "patient_general");
        addMetadata();
    }

    public QPatientGeneral(Path<? extends PatientGeneral> path) {
        super(path.getType(), path.getMetadata(), "dbo", "patient_general");
        addMetadata();
    }

    public QPatientGeneral(PathMetadata metadata) {
        super(PatientGeneral.class, metadata, "dbo", "patient_general");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(additionalPatientId, ColumnMetadata.named("additional_patient_id").withIndex(55).ofType(Types.VARCHAR).withSize(60));
        addMetadata(additionalPatientIdTypeCode, ColumnMetadata.named("additional_patient_id_type_code").withIndex(54).ofType(Types.VARCHAR).withSize(3));
        addMetadata(assessmentEligible, ColumnMetadata.named("assessment_eligible").withIndex(36).ofType(Types.CHAR).withSize(1));
        addMetadata(assessmentWorkflow, ColumnMetadata.named("assessment_workflow").withIndex(35).ofType(Types.NUMERIC).withSize(7));
        addMetadata(autoContactTypeId, ColumnMetadata.named("auto_contact_type_id").withIndex(40).ofType(Types.NUMERIC).withSize(5));
        addMetadata(careCategoryNum, ColumnMetadata.named("care_category_num").withIndex(19).ofType(Types.NUMERIC).withSize(5));
        addMetadata(careCoordinatorNum, ColumnMetadata.named("care_coordinator_num").withIndex(25).ofType(Types.NUMERIC).withSize(16));
        addMetadata(careKitSeq, ColumnMetadata.named("care_kit_seq").withIndex(26).ofType(Types.NUMERIC).withSize(7));
        addMetadata(cmrStatusCode, ColumnMetadata.named("cmr_status_code").withIndex(50).ofType(Types.VARCHAR).withSize(4));
        addMetadata(consentAcceptedDate, ColumnMetadata.named("consent_accepted_date").withIndex(53).ofType(Types.TIMESTAMP).withSize(27).withDigits(7));
        addMetadata(consentEnddatetime, ColumnMetadata.named("consent_enddatetime").withIndex(46).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(consentStartdatetime, ColumnMetadata.named("consent_startdatetime").withIndex(45).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(consentStatus, ColumnMetadata.named("consent_status").withIndex(44).ofType(Types.VARCHAR).withSize(60));
        addMetadata(conversionLink, ColumnMetadata.named("conversion_link").withIndex(23).ofType(Types.VARCHAR).withSize(60));
        addMetadata(dateAdded, ColumnMetadata.named("date_added").withIndex(31).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(externalLink, ColumnMetadata.named("external_link").withIndex(24).ofType(Types.VARCHAR).withSize(60));
        addMetadata(facilityStationSeq, ColumnMetadata.named("facility_station_seq").withIndex(33).ofType(Types.NUMERIC).withSize(16));
        addMetadata(familyId, ColumnMetadata.named("family_id").withIndex(28).ofType(Types.VARCHAR).withSize(60));
        addMetadata(genderCode, ColumnMetadata.named("gender_code").withIndex(2).ofType(Types.CHAR).withSize(1));
        addMetadata(generalStatusCode, ColumnMetadata.named("general_status_code").withIndex(4).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(hippaCounter, ColumnMetadata.named("hippa_counter").withIndex(29).ofType(Types.NUMERIC).withSize(3));
        addMetadata(impairmentIndicator, ColumnMetadata.named("IMPAIRMENT_INDICATOR").withIndex(51).ofType(Types.VARCHAR).withSize(5));
        addMetadata(isMdp, ColumnMetadata.named("is_mdp").withIndex(59).ofType(Types.CHAR).withSize(1));
        addMetadata(isMedSync, ColumnMetadata.named("is_med_sync").withIndex(56).ofType(Types.CHAR).withSize(1));
        addMetadata(languageId, ColumnMetadata.named("language_id").withIndex(5).ofType(Types.NUMERIC).withSize(5).notNull());
        addMetadata(lastAobDatetime, ColumnMetadata.named("last_aob_datetime").withIndex(39).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(marketing, ColumnMetadata.named("marketing").withIndex(41).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(mdpUpdatedBy, ColumnMetadata.named("mdp_updated_by").withIndex(60).ofType(Types.NUMERIC).withSize(16));
        addMetadata(mdpUpdatedDate, ColumnMetadata.named("mdp_updated_date").withIndex(61).ofType(Types.TIMESTAMP).withSize(27).withDigits(7));
        addMetadata(medSyncUpdatedBy, ColumnMetadata.named("med_sync_updated_by").withIndex(57).ofType(Types.NUMERIC).withSize(5));
        addMetadata(medSyncUpdatedDate, ColumnMetadata.named("med_sync_updated_date").withIndex(58).ofType(Types.TIMESTAMP).withSize(27).withDigits(7));
        addMetadata(nextAssessmentDate, ColumnMetadata.named("next_assessment_date").withIndex(34).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(patientAlias, ColumnMetadata.named("patient_alias").withIndex(21).ofType(Types.VARCHAR).withSize(80));
        addMetadata(patientAobEnddate, ColumnMetadata.named("patient_aob_enddate").withIndex(38).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(patientAobStartdate, ColumnMetadata.named("patient_aob_startdate").withIndex(37).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(patientAttendingPhysician, ColumnMetadata.named("patient_attending_physician").withIndex(20).ofType(Types.NUMERIC).withSize(16));
        addMetadata(patientCreditLimit, ColumnMetadata.named("patient_credit_limit").withIndex(14).ofType(Types.NUMERIC).withSize(10));
        addMetadata(patientDob, ColumnMetadata.named("patient_dob").withIndex(10).ofType(Types.TIMESTAMP).withSize(23).withDigits(3).notNull());
        addMetadata(patientEmailAddress, ColumnMetadata.named("patient_email_address").withIndex(11).ofType(Types.VARCHAR).withSize(60));
        addMetadata(patientFirstName, ColumnMetadata.named("patient_first_name").withIndex(7).ofType(Types.VARCHAR).withSize(60).notNull());
        addMetadata(patientId, ColumnMetadata.named("patient_id").withIndex(12).ofType(Types.VARCHAR).withSize(60));
        addMetadata(patientIdCode, ColumnMetadata.named("patient_id_code").withIndex(3).ofType(Types.VARCHAR).withSize(3));
        addMetadata(patientLastName, ColumnMetadata.named("patient_last_name").withIndex(9).ofType(Types.VARCHAR).withSize(60).notNull());
        addMetadata(patientMaidenName, ColumnMetadata.named("patient_maiden_name").withIndex(22).ofType(Types.VARCHAR).withSize(60));
        addMetadata(patientMembershipNum, ColumnMetadata.named("patient_membership_num").withIndex(13).ofType(Types.VARCHAR).withSize(60));
        addMetadata(patientMiddleName, ColumnMetadata.named("patient_middle_name").withIndex(8).ofType(Types.VARCHAR).withSize(60));
        addMetadata(patientNum, ColumnMetadata.named("patient_num").withIndex(1).ofType(Types.NUMERIC).withSize(20).notNull());
        addMetadata(patientOriginationSeq, ColumnMetadata.named("patient_origination_seq").withIndex(49).ofType(Types.NUMERIC).withSize(5));
        addMetadata(patientPcPhysician, ColumnMetadata.named("patient_pc_physician").withIndex(18).ofType(Types.NUMERIC).withSize(16));
        addMetadata(patientPrefHourSeq, ColumnMetadata.named("patient_pref_hour_seq").withIndex(42).ofType(Types.NUMERIC).withSize(7));
        addMetadata(patientPrivacy, ColumnMetadata.named("patient_privacy").withIndex(15).ofType(Types.CHAR).withSize(1));
        addMetadata(patientPrivacyEnddate, ColumnMetadata.named("patient_privacy_enddate").withIndex(17).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(patientPrivacySentdate, ColumnMetadata.named("patient_privacy_sentdate").withIndex(43).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(patientPrivacyStartdate, ColumnMetadata.named("patient_privacy_startdate").withIndex(16).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(secondaryLanguageID, ColumnMetadata.named("Secondary_Language_ID").withIndex(52).ofType(Types.INTEGER).withSize(10));
        addMetadata(sourceHost, ColumnMetadata.named("source_host").withIndex(47).ofType(Types.VARCHAR).withSize(60));
        addMetadata(sourceUser, ColumnMetadata.named("source_user").withIndex(48).ofType(Types.VARCHAR).withSize(60));
        addMetadata(titleId, ColumnMetadata.named("title_id").withIndex(6).ofType(Types.NUMERIC).withSize(5).notNull());
        addMetadata(tradingPartnerNum, ColumnMetadata.named("trading_partner_num").withIndex(27).ofType(Types.NUMERIC).withSize(25));
        addMetadata(verified, ColumnMetadata.named("verified").withIndex(32).ofType(Types.CHAR).withSize(1));
        addMetadata(vip, ColumnMetadata.named("vip").withIndex(30).ofType(Types.CHAR).withSize(1));
    }

}

