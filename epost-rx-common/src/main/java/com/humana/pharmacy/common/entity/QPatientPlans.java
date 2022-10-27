package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.PatientPlans;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QPatientPlans is a Querydsl query type for PatientPlans
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QPatientPlans extends com.querydsl.sql.RelationalPathBase<PatientPlans> {

    private static final long serialVersionUID = 1372700607;

    public static final QPatientPlans patientPlans = new QPatientPlans("patient_plans");

    public final NumberPath<Byte> coverageTypeId = createNumber("coverageTypeId", Byte.class);

    public final DateTimePath<java.sql.Timestamp> cpActivationDate = createDateTime("cpActivationDate", java.sql.Timestamp.class);

    public final StringPath cpAltPlanId = createString("cpAltPlanId");

    public final NumberPath<Long> cpCopayAmount = createNumber("cpCopayAmount", Long.class);

    public final DateTimePath<java.sql.Timestamp> cpDeactivationDate = createDateTime("cpDeactivationDate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> cpEffectiveDate = createDateTime("cpEffectiveDate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> cpExpirationDate = createDateTime("cpExpirationDate", java.sql.Timestamp.class);

    public final StringPath cpFirstName = createString("cpFirstName");

    public final StringPath cpHomePlan = createString("cpHomePlan");

    public final StringPath cpHostPlan = createString("cpHostPlan");

    public final StringPath cpLastName = createString("cpLastName");

    public final StringPath cpMiddleName = createString("cpMiddleName");

    public final StringPath cpPersonCode = createString("cpPersonCode");

    public final StringPath cpPlanId = createString("cpPlanId");

    public final StringPath cpPlanIdExt = createString("cpPlanIdExt");

    public final StringPath externalLink = createString("externalLink");

    public final StringPath overrideGroupId = createString("overrideGroupId");

    public final NumberPath<java.math.BigInteger> patientNum = createNumber("patientNum", java.math.BigInteger.class);

    public final NumberPath<Long> ppNum = createNumber("ppNum", Long.class);

    public final NumberPath<Integer> relationshipNum = createNumber("relationshipNum", Integer.class);

    public final SimplePath<byte[]> rowversion = createSimple("rowversion", byte[].class);

    public final StringPath sigSourceCode = createString("sigSourceCode");

    public QPatientPlans(String variable) {
        super(PatientPlans.class, forVariable(variable), "dbo", "patient_plans");
        addMetadata();
    }

    public QPatientPlans(String variable, String schema, String table) {
        super(PatientPlans.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QPatientPlans(String variable, String schema) {
        super(PatientPlans.class, forVariable(variable), schema, "patient_plans");
        addMetadata();
    }

    public QPatientPlans(Path<? extends PatientPlans> path) {
        super(path.getType(), path.getMetadata(), "dbo", "patient_plans");
        addMetadata();
    }

    public QPatientPlans(PathMetadata metadata) {
        super(PatientPlans.class, metadata, "dbo", "patient_plans");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(coverageTypeId, ColumnMetadata.named("coverage_type_id").withIndex(4).ofType(Types.NUMERIC).withSize(2).notNull());
        addMetadata(cpActivationDate, ColumnMetadata.named("cp_activation_date").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(cpAltPlanId, ColumnMetadata.named("cp_alt_plan_id").withIndex(19).ofType(Types.VARCHAR).withSize(20));
        addMetadata(cpCopayAmount, ColumnMetadata.named("cp_copay_amount").withIndex(17).ofType(Types.NUMERIC).withSize(12));
        addMetadata(cpDeactivationDate, ColumnMetadata.named("cp_deactivation_date").withIndex(15).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(cpEffectiveDate, ColumnMetadata.named("cp_effective_date").withIndex(10).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(cpExpirationDate, ColumnMetadata.named("cp_expiration_date").withIndex(16).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(cpFirstName, ColumnMetadata.named("cp_first_name").withIndex(8).ofType(Types.VARCHAR).withSize(60));
        addMetadata(cpHomePlan, ColumnMetadata.named("cp_home_plan").withIndex(13).ofType(Types.VARCHAR).withSize(3));
        addMetadata(cpHostPlan, ColumnMetadata.named("cp_host_plan").withIndex(14).ofType(Types.VARCHAR).withSize(3));
        addMetadata(cpLastName, ColumnMetadata.named("cp_last_name").withIndex(11).ofType(Types.VARCHAR).withSize(60));
        addMetadata(cpMiddleName, ColumnMetadata.named("cp_middle_name").withIndex(9).ofType(Types.VARCHAR).withSize(60));
        addMetadata(cpPersonCode, ColumnMetadata.named("cp_person_code").withIndex(12).ofType(Types.CHAR).withSize(3));
        addMetadata(cpPlanId, ColumnMetadata.named("cp_plan_id").withIndex(3).ofType(Types.VARCHAR).withSize(20));
        addMetadata(cpPlanIdExt, ColumnMetadata.named("cp_plan_id_ext").withIndex(18).ofType(Types.VARCHAR).withSize(20));
        addMetadata(externalLink, ColumnMetadata.named("external_link").withIndex(20).ofType(Types.VARCHAR).withSize(100));
        addMetadata(overrideGroupId, ColumnMetadata.named("override_group_id").withIndex(21).ofType(Types.VARCHAR).withSize(60));
        addMetadata(patientNum, ColumnMetadata.named("patient_num").withIndex(2).ofType(Types.NUMERIC).withSize(20).notNull());
        addMetadata(ppNum, ColumnMetadata.named("pp_num").withIndex(1).ofType(Types.NUMERIC).withSize(16).notNull());
        addMetadata(relationshipNum, ColumnMetadata.named("relationship_num").withIndex(7).ofType(Types.NUMERIC).withSize(5));
        addMetadata(rowversion, ColumnMetadata.named("rowversion").withIndex(22).ofType(Types.BINARY).withSize(8).notNull());
        addMetadata(sigSourceCode, ColumnMetadata.named("sig_source_code").withIndex(5).ofType(Types.CHAR).withSize(1));
    }

}

