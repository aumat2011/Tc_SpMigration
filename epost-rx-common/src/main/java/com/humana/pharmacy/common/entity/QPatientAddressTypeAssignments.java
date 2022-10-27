package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.PatientAddressTypeAssignments;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QPatientAddressTypeAssignments is a Querydsl query type for PatientAddressTypeAssignments
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QPatientAddressTypeAssignments extends com.querydsl.sql.RelationalPathBase<PatientAddressTypeAssignments> {

    private static final long serialVersionUID = -829192093;

    public static final QPatientAddressTypeAssignments patientAddressTypeAssignments = new QPatientAddressTypeAssignments("patient_address_type_assignments");

    public final NumberPath<Integer> addressTypeNum = createNumber("addressTypeNum", Integer.class);

    public final NumberPath<Long> patientAddrSeq = createNumber("patientAddrSeq", Long.class);

    public final NumberPath<java.math.BigInteger> patientNum = createNumber("patientNum", java.math.BigInteger.class);

    public final DateTimePath<java.sql.Timestamp> updated = createDateTime("updated", java.sql.Timestamp.class);

    public final NumberPath<Long> updatedUserNum = createNumber("updatedUserNum", Long.class);

    public QPatientAddressTypeAssignments(String variable) {
        super(PatientAddressTypeAssignments.class, forVariable(variable), "dbo", "patient_address_type_assignments");
        addMetadata();
    }

    public QPatientAddressTypeAssignments(String variable, String schema, String table) {
        super(PatientAddressTypeAssignments.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QPatientAddressTypeAssignments(String variable, String schema) {
        super(PatientAddressTypeAssignments.class, forVariable(variable), schema, "patient_address_type_assignments");
        addMetadata();
    }

    public QPatientAddressTypeAssignments(Path<? extends PatientAddressTypeAssignments> path) {
        super(path.getType(), path.getMetadata(), "dbo", "patient_address_type_assignments");
        addMetadata();
    }

    public QPatientAddressTypeAssignments(PathMetadata metadata) {
        super(PatientAddressTypeAssignments.class, metadata, "dbo", "patient_address_type_assignments");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(addressTypeNum, ColumnMetadata.named("address_type_num").withIndex(2).ofType(Types.NUMERIC).withSize(5).notNull());
        addMetadata(patientAddrSeq, ColumnMetadata.named("patient_addr_seq").withIndex(1).ofType(Types.NUMERIC).withSize(16).notNull());
        addMetadata(patientNum, ColumnMetadata.named("patient_num").withIndex(3).ofType(Types.NUMERIC).withSize(20).notNull());
        addMetadata(updated, ColumnMetadata.named("updated").withIndex(4).ofType(Types.TIMESTAMP).withSize(27).withDigits(7).notNull());
        addMetadata(updatedUserNum, ColumnMetadata.named("updated_user_num").withIndex(5).ofType(Types.NUMERIC).withSize(16).notNull());
    }

}

