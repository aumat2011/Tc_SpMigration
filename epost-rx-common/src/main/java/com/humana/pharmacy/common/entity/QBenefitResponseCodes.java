package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.BenefitResponseCodes;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QBenefitResponseCodes is a Querydsl query type for BenefitResponseCodes
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QBenefitResponseCodes extends com.querydsl.sql.RelationalPathBase<BenefitResponseCodes> {

    private static final long serialVersionUID = 971186280;

    public static final QBenefitResponseCodes benefitResponseCodes = new QBenefitResponseCodes("benefit_response_codes");

    public final StringPath approvalCode = createString("approvalCode");

    public final StringPath displayMessage = createString("displayMessage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath internalDescription = createString("internalDescription");

    public final StringPath internalResponseCode = createString("internalResponseCode");

    public final StringPath transactionRespCode = createString("transactionRespCode");

    public QBenefitResponseCodes(String variable) {
        super(BenefitResponseCodes.class, forVariable(variable), "dbo", "benefit_response_codes");
        addMetadata();
    }

    public QBenefitResponseCodes(String variable, String schema, String table) {
        super(BenefitResponseCodes.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBenefitResponseCodes(String variable, String schema) {
        super(BenefitResponseCodes.class, forVariable(variable), schema, "benefit_response_codes");
        addMetadata();
    }

    public QBenefitResponseCodes(Path<? extends BenefitResponseCodes> path) {
        super(path.getType(), path.getMetadata(), "dbo", "benefit_response_codes");
        addMetadata();
    }

    public QBenefitResponseCodes(PathMetadata metadata) {
        super(BenefitResponseCodes.class, metadata, "dbo", "benefit_response_codes");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(approvalCode, ColumnMetadata.named("approval_code").withIndex(2).ofType(Types.CHAR).withSize(2));
        addMetadata(displayMessage, ColumnMetadata.named("display_message").withIndex(3).ofType(Types.VARCHAR).withSize(20));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(internalDescription, ColumnMetadata.named("internal_description").withIndex(6).ofType(Types.VARCHAR).withSize(100));
        addMetadata(internalResponseCode, ColumnMetadata.named("internal_response_code").withIndex(4).ofType(Types.VARCHAR).withSize(10));
        addMetadata(transactionRespCode, ColumnMetadata.named("transaction_resp_code").withIndex(5).ofType(Types.VARCHAR).withSize(10));
    }

}

