package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.PatientAddress;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QPatientAddress is a Querydsl query type for PatientAddress
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QPatientAddress extends com.querydsl.sql.RelationalPathBase<PatientAddress> {

    private static final long serialVersionUID = -43483127;

    public static final QPatientAddress patientAddress1 = new QPatientAddress("patient_address");

    public final StringPath active = createString("active");

    public final StringPath addressDesc = createString("addressDesc");

    public final NumberPath<Integer> addressTypeNum = createNumber("addressTypeNum", Integer.class);

    public final StringPath conversionLink = createString("conversionLink");

    public final DateTimePath<java.sql.Timestamp> created = createDateTime("created", java.sql.Timestamp.class);

    public final NumberPath<Long> createdUserNum = createNumber("createdUserNum", Long.class);

    public final NumberPath<Long> cszZipNum = createNumber("cszZipNum", Long.class);

    public final DateTimePath<java.sql.Timestamp> deactivated = createDateTime("deactivated", java.sql.Timestamp.class);

    public final StringPath defaultBillAddress = createString("defaultBillAddress");

    public final StringPath defaultShipAddress = createString("defaultShipAddress");

    public final DateTimePath<java.sql.Timestamp> endDate = createDateTime("endDate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> lastVerification = createDateTime("lastVerification", java.sql.Timestamp.class);

    public final NumberPath<Integer> originationNum = createNumber("originationNum", Integer.class);

    public final NumberPath<Byte> overrideReasonId = createNumber("overrideReasonId", Byte.class);

    public final StringPath patientAddress = createString("patientAddress");

    public final StringPath patientAddress2 = createString("patientAddress2");

    public final StringPath patientAddress3 = createString("patientAddress3");

    public final NumberPath<Long> patientAddrSeq = createNumber("patientAddrSeq", Long.class);

    public final NumberPath<java.math.BigInteger> patientNum = createNumber("patientNum", java.math.BigInteger.class);

    public final NumberPath<Long> patientPhoneSeq = createNumber("patientPhoneSeq", Long.class);

    public final NumberPath<Short> patientZipFour = createNumber("patientZipFour", Short.class);

    public final SimplePath<byte[]> rowversion = createSimple("rowversion", byte[].class);

    public final DateTimePath<java.sql.Timestamp> startDate = createDateTime("startDate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> updated = createDateTime("updated", java.sql.Timestamp.class);

    public final NumberPath<Long> updatedUserNum = createNumber("updatedUserNum", Long.class);

    public final BooleanPath verified = createBoolean("verified");

    public final DateTimePath<java.sql.Timestamp> verifiedDate = createDateTime("verifiedDate", java.sql.Timestamp.class);

    public final StringPath verifiedUser = createString("verifiedUser");

    public QPatientAddress(String variable) {
        super(PatientAddress.class, forVariable(variable), "dbo", "patient_address");
        addMetadata();
    }

    public QPatientAddress(String variable, String schema, String table) {
        super(PatientAddress.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QPatientAddress(String variable, String schema) {
        super(PatientAddress.class, forVariable(variable), schema, "patient_address");
        addMetadata();
    }

    public QPatientAddress(Path<? extends PatientAddress> path) {
        super(path.getType(), path.getMetadata(), "dbo", "patient_address");
        addMetadata();
    }

    public QPatientAddress(PathMetadata metadata) {
        super(PatientAddress.class, metadata, "dbo", "patient_address");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(active, ColumnMetadata.named("active").withIndex(13).ofType(Types.CHAR).withSize(1));
        addMetadata(addressDesc, ColumnMetadata.named("address_desc").withIndex(15).ofType(Types.VARCHAR).withSize(250));
        addMetadata(addressTypeNum, ColumnMetadata.named("address_type_num").withIndex(3).ofType(Types.NUMERIC).withSize(5).notNull());
        addMetadata(conversionLink, ColumnMetadata.named("conversion_link").withIndex(14).ofType(Types.VARCHAR).withSize(60));
        addMetadata(created, ColumnMetadata.named("created").withIndex(19).ofType(Types.TIMESTAMP).withSize(27).withDigits(7).notNull());
        addMetadata(createdUserNum, ColumnMetadata.named("created_user_num").withIndex(20).ofType(Types.NUMERIC).withSize(16).notNull());
        addMetadata(cszZipNum, ColumnMetadata.named("csz_zip_num").withIndex(2).ofType(Types.NUMERIC).withSize(16).notNull());
        addMetadata(deactivated, ColumnMetadata.named("deactivated").withIndex(28).ofType(Types.TIMESTAMP).withSize(27).withDigits(7));
        addMetadata(defaultBillAddress, ColumnMetadata.named("default_bill_address").withIndex(10).ofType(Types.CHAR).withSize(1));
        addMetadata(defaultShipAddress, ColumnMetadata.named("default_ship_address").withIndex(9).ofType(Types.CHAR).withSize(1));
        addMetadata(endDate, ColumnMetadata.named("end_date").withIndex(12).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(lastVerification, ColumnMetadata.named("last_verification").withIndex(25).ofType(Types.TIMESTAMP).withSize(27).withDigits(7));
        addMetadata(originationNum, ColumnMetadata.named("origination_num").withIndex(26).ofType(Types.NUMERIC).withSize(5).notNull());
        addMetadata(overrideReasonId, ColumnMetadata.named("override_reason_id").withIndex(24).ofType(Types.TINYINT).withSize(3).notNull());
        addMetadata(patientAddress, ColumnMetadata.named("patient_address").withIndex(5).ofType(Types.VARCHAR).withSize(60).notNull());
        addMetadata(patientAddress2, ColumnMetadata.named("patient_address2").withIndex(6).ofType(Types.VARCHAR).withSize(60));
        addMetadata(patientAddress3, ColumnMetadata.named("patient_address3").withIndex(8).ofType(Types.VARCHAR).withSize(60));
        addMetadata(patientAddrSeq, ColumnMetadata.named("patient_addr_seq").withIndex(1).ofType(Types.NUMERIC).withSize(16).notNull());
        addMetadata(patientNum, ColumnMetadata.named("patient_num").withIndex(4).ofType(Types.NUMERIC).withSize(20).notNull());
        addMetadata(patientPhoneSeq, ColumnMetadata.named("patient_phone_seq").withIndex(18).ofType(Types.NUMERIC).withSize(16));
        addMetadata(patientZipFour, ColumnMetadata.named("patient_zip_four").withIndex(7).ofType(Types.NUMERIC).withSize(4));
        addMetadata(rowversion, ColumnMetadata.named("rowversion").withIndex(27).ofType(Types.BINARY).withSize(8).notNull());
        addMetadata(startDate, ColumnMetadata.named("start_date").withIndex(11).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(updated, ColumnMetadata.named("updated").withIndex(21).ofType(Types.TIMESTAMP).withSize(27).withDigits(7).notNull());
        addMetadata(updatedUserNum, ColumnMetadata.named("updated_user_num").withIndex(22).ofType(Types.NUMERIC).withSize(16).notNull());
        addMetadata(verified, ColumnMetadata.named("verified").withIndex(23).ofType(Types.BIT).withSize(1).notNull());
        addMetadata(verifiedDate, ColumnMetadata.named("verified_date").withIndex(16).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(verifiedUser, ColumnMetadata.named("verified_user").withIndex(17).ofType(Types.VARCHAR).withSize(60));
    }

}

