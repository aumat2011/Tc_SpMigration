package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.PatientFamily;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QPatientFamily is a Querydsl query type for PatientFamily
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QPatientFamily extends com.querydsl.sql.RelationalPathBase<PatientFamily> {

    private static final long serialVersionUID = -692051793;

    public static final QPatientFamily patientFamily = new QPatientFamily("patient_family");

    public final StringPath conversionLink = createString("conversionLink");

    public final StringPath creditAccountStatus = createString("creditAccountStatus");

    public final NumberPath<Long> creditLimit = createNumber("creditLimit", Long.class);

    public final StringPath familyId = createString("familyId");

    public final StringPath headOfHousehold = createString("headOfHousehold");

    public final NumberPath<Long> patientFamilySeq = createNumber("patientFamilySeq", Long.class);

    public final NumberPath<java.math.BigInteger> patientNum = createNumber("patientNum", java.math.BigInteger.class);

    public final StringPath webAccess = createString("webAccess");

    public QPatientFamily(String variable) {
        super(PatientFamily.class, forVariable(variable), "dbo", "patient_family");
        addMetadata();
    }

    public QPatientFamily(String variable, String schema, String table) {
        super(PatientFamily.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QPatientFamily(String variable, String schema) {
        super(PatientFamily.class, forVariable(variable), schema, "patient_family");
        addMetadata();
    }

    public QPatientFamily(Path<? extends PatientFamily> path) {
        super(path.getType(), path.getMetadata(), "dbo", "patient_family");
        addMetadata();
    }

    public QPatientFamily(PathMetadata metadata) {
        super(PatientFamily.class, metadata, "dbo", "patient_family");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(conversionLink, ColumnMetadata.named("conversion_link").withIndex(7).ofType(Types.VARCHAR).withSize(60));
        addMetadata(creditAccountStatus, ColumnMetadata.named("credit_account_status").withIndex(8).ofType(Types.VARCHAR).withSize(10));
        addMetadata(creditLimit, ColumnMetadata.named("credit_limit").withIndex(6).ofType(Types.NUMERIC).withSize(12));
        addMetadata(familyId, ColumnMetadata.named("family_id").withIndex(3).ofType(Types.VARCHAR).withSize(60));
        addMetadata(headOfHousehold, ColumnMetadata.named("head_of_household").withIndex(4).ofType(Types.CHAR).withSize(1));
        addMetadata(patientFamilySeq, ColumnMetadata.named("patient_family_seq").withIndex(1).ofType(Types.NUMERIC).withSize(16).notNull());
        addMetadata(patientNum, ColumnMetadata.named("patient_num").withIndex(2).ofType(Types.NUMERIC).withSize(20));
        addMetadata(webAccess, ColumnMetadata.named("web_access").withIndex(5).ofType(Types.CHAR).withSize(1));
    }

}

