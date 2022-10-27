package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.ThirdPartyClaimRequests;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QThirdPartyClaimRequests is a Querydsl query type for ThirdPartyClaimRequests
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QThirdPartyClaimRequests extends com.querydsl.sql.RelationalPathBase<ThirdPartyClaimRequests> {

    private static final long serialVersionUID = 1277533959;

    public static final QThirdPartyClaimRequests thirdPartyClaimRequests = new QThirdPartyClaimRequests("third_party_claim_requests");

    public final NumberPath<java.math.BigInteger> benefitCardNum = createNumber("benefitCardNum", java.math.BigInteger.class);

    public final NumberPath<Long> eScriptMsgAttributeSeq = createNumber("eScriptMsgAttributeSeq", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath manual = createBoolean("manual");

    public final NumberPath<Integer> orderAmount = createNumber("orderAmount", Integer.class);

    public final NumberPath<java.math.BigInteger> orderInvoiceNumber = createNumber("orderInvoiceNumber", java.math.BigInteger.class);

    public final StringPath processor = createString("processor");

    public final NumberPath<Integer> redemptionReqAmount = createNumber("redemptionReqAmount", Integer.class);

    public final NumberPath<Short> redemptionReqQuantity = createNumber("redemptionReqQuantity", Short.class);

    public final DateTimePath<java.sql.Timestamp> requestDatetime = createDateTime("requestDatetime", java.sql.Timestamp.class);

    public final StringPath server = createString("server");

    public final StringPath sysUserNum = createString("sysUserNum");

    public final StringPath transactionCode = createString("transactionCode");

    public final StringPath transactionId = createString("transactionId");

    public QThirdPartyClaimRequests(String variable) {
        super(ThirdPartyClaimRequests.class, forVariable(variable), "dbo", "third_party_claim_requests");
        addMetadata();
    }

    public QThirdPartyClaimRequests(String variable, String schema, String table) {
        super(ThirdPartyClaimRequests.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QThirdPartyClaimRequests(String variable, String schema) {
        super(ThirdPartyClaimRequests.class, forVariable(variable), schema, "third_party_claim_requests");
        addMetadata();
    }

    public QThirdPartyClaimRequests(Path<? extends ThirdPartyClaimRequests> path) {
        super(path.getType(), path.getMetadata(), "dbo", "third_party_claim_requests");
        addMetadata();
    }

    public QThirdPartyClaimRequests(PathMetadata metadata) {
        super(ThirdPartyClaimRequests.class, metadata, "dbo", "third_party_claim_requests");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(benefitCardNum, ColumnMetadata.named("benefit_card_num").withIndex(6).ofType(Types.NUMERIC).withSize(19).notNull());
        addMetadata(eScriptMsgAttributeSeq, ColumnMetadata.named("e_script_msg_attribute_seq").withIndex(2).ofType(Types.NUMERIC).withSize(17));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(manual, ColumnMetadata.named("manual").withIndex(12).ofType(Types.BIT).withSize(1));
        addMetadata(orderAmount, ColumnMetadata.named("order_amount").withIndex(9).ofType(Types.NUMERIC).withSize(9));
        addMetadata(orderInvoiceNumber, ColumnMetadata.named("order_invoice_number").withIndex(8).ofType(Types.NUMERIC).withSize(30));
        addMetadata(processor, ColumnMetadata.named("processor").withIndex(13).ofType(Types.VARCHAR).withSize(60));
        addMetadata(redemptionReqAmount, ColumnMetadata.named("redemption_req_amount").withIndex(10).ofType(Types.NUMERIC).withSize(9));
        addMetadata(redemptionReqQuantity, ColumnMetadata.named("redemption_req_quantity").withIndex(11).ofType(Types.NUMERIC).withSize(3));
        addMetadata(requestDatetime, ColumnMetadata.named("request_datetime").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(server, ColumnMetadata.named("server").withIndex(14).ofType(Types.VARCHAR).withSize(60));
        addMetadata(sysUserNum, ColumnMetadata.named("sys_user_num").withIndex(5).ofType(Types.VARCHAR).withSize(60));
        addMetadata(transactionCode, ColumnMetadata.named("transaction_code").withIndex(3).ofType(Types.CHAR).withSize(4));
        addMetadata(transactionId, ColumnMetadata.named("transaction_id").withIndex(7).ofType(Types.VARCHAR).withSize(60));
    }

}

