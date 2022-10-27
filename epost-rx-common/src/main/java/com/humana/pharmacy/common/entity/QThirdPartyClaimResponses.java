package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.ThirdPartyClaimResponses;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QThirdPartyClaimResponses is a Querydsl query type for ThirdPartyClaimResponses
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QThirdPartyClaimResponses extends com.querydsl.sql.RelationalPathBase<ThirdPartyClaimResponses> {

    private static final long serialVersionUID = -1705173649;

    public static final QThirdPartyClaimResponses thirdPartyClaimResponses = new QThirdPartyClaimResponses("third_party_claim_responses");

    public final StringPath authorizationId = createString("authorizationId");

    public final DateTimePath<java.sql.Timestamp> captureDate = createDateTime("captureDate", java.sql.Timestamp.class);

    public final StringPath errorCode = createString("errorCode");

    public final StringPath errorDescription = createString("errorDescription");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> redeemedAmount = createNumber("redeemedAmount", Long.class);

    public final NumberPath<Short> redeemedQuantity = createNumber("redeemedQuantity", Short.class);

    public final NumberPath<Long> responseCode = createNumber("responseCode", Long.class);

    public final StringPath stanId = createString("stanId");

    public final StringPath transactionId = createString("transactionId");

    public QThirdPartyClaimResponses(String variable) {
        super(ThirdPartyClaimResponses.class, forVariable(variable), "dbo", "third_party_claim_responses");
        addMetadata();
    }

    public QThirdPartyClaimResponses(String variable, String schema, String table) {
        super(ThirdPartyClaimResponses.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QThirdPartyClaimResponses(String variable, String schema) {
        super(ThirdPartyClaimResponses.class, forVariable(variable), schema, "third_party_claim_responses");
        addMetadata();
    }

    public QThirdPartyClaimResponses(Path<? extends ThirdPartyClaimResponses> path) {
        super(path.getType(), path.getMetadata(), "dbo", "third_party_claim_responses");
        addMetadata();
    }

    public QThirdPartyClaimResponses(PathMetadata metadata) {
        super(ThirdPartyClaimResponses.class, metadata, "dbo", "third_party_claim_responses");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(authorizationId, ColumnMetadata.named("Authorization_id").withIndex(9).ofType(Types.VARCHAR).withSize(20));
        addMetadata(captureDate, ColumnMetadata.named("capture_date").withIndex(4).ofType(Types.TIMESTAMP).withSize(27).withDigits(7));
        addMetadata(errorCode, ColumnMetadata.named("error_code").withIndex(7).ofType(Types.VARCHAR).withSize(10));
        addMetadata(errorDescription, ColumnMetadata.named("error_description").withIndex(8).ofType(Types.VARCHAR).withSize(60));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(redeemedAmount, ColumnMetadata.named("redeemed_amount").withIndex(6).ofType(Types.NUMERIC).withSize(12));
        addMetadata(redeemedQuantity, ColumnMetadata.named("redeemed_quantity").withIndex(5).ofType(Types.NUMERIC).withSize(3));
        addMetadata(responseCode, ColumnMetadata.named("response_code").withIndex(3).ofType(Types.BIGINT).withSize(19));
        addMetadata(stanId, ColumnMetadata.named("Stan_id").withIndex(10).ofType(Types.VARCHAR).withSize(12));
        addMetadata(transactionId, ColumnMetadata.named("transaction_id").withIndex(2).ofType(Types.VARCHAR).withSize(60));
    }

}

