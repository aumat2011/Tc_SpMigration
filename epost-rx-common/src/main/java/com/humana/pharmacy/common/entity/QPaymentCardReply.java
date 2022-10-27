package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.PaymentCardReply;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QPaymentCardReply is a Querydsl query type for PaymentCardReply
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QPaymentCardReply extends com.querydsl.sql.RelationalPathBase<PaymentCardReply> {

    private static final long serialVersionUID = 623922862;

    public static final QPaymentCardReply paymentCardReply = new QPaymentCardReply("payment_card_reply");

    public final StringPath actionCode = createString("actionCode");

    public final StringPath batch = createString("batch");

    public final DateTimePath<java.sql.Timestamp> batchDatetime = createDateTime("batchDatetime", java.sql.Timestamp.class);

    public final StringPath batchNumber = createString("batchNumber");

    public final StringPath batchStatus = createString("batchStatus");

    public final StringPath fsaCard = createString("fsaCard");

    public final NumberPath<java.math.BigInteger> glPostNum = createNumber("glPostNum", java.math.BigInteger.class);

    public final StringPath manual = createString("manual");

    public final NumberPath<java.math.BigInteger> orderNum = createNumber("orderNum", java.math.BigInteger.class);

    public final StringPath paymentCardNumber = createString("paymentCardNumber");

    public final StringPath paymentCardSeqNum = createString("paymentCardSeqNum");

    public final StringPath pcAvsCode = createString("pcAvsCode");

    public final DateTimePath<java.sql.Timestamp> pcDatetime = createDateTime("pcDatetime", java.sql.Timestamp.class);

    public final StringPath pcLog = createString("pcLog");

    public final NumberPath<Long> pcReplyAmount = createNumber("pcReplyAmount", Long.class);

    public final StringPath pcReplyAuthCode = createString("pcReplyAuthCode");

    public final SimplePath<byte[]> pcReplyMsg = createSimple("pcReplyMsg", byte[].class);

    public final StringPath pcReplyRcode = createString("pcReplyRcode");

    public final StringPath pcReplyRequestId = createString("pcReplyRequestId");

    public final StringPath pcReplyRflag = createString("pcReplyRflag");

    public final StringPath pcReplyRmsg = createString("pcReplyRmsg");

    public final NumberPath<java.math.BigInteger> pcReplySeqNum = createNumber("pcReplySeqNum", java.math.BigInteger.class);

    public final StringPath pcReplyTimestamp = createString("pcReplyTimestamp");

    public final StringPath pcReplyType = createString("pcReplyType");

    public final SimplePath<byte[]> pcRequestMsg = createSimple("pcRequestMsg", byte[].class);

    public final NumberPath<Byte> pcSubStatusNum = createNumber("pcSubStatusNum", Byte.class);

    public final StringPath provider = createString("provider");

    public final StringPath respAvsCode = createString("respAvsCode");

    public final DateTimePath<java.sql.Timestamp> vaultBatchSettledDatetime = createDateTime("vaultBatchSettledDatetime", java.sql.Timestamp.class);

    public final StringPath vaultBillAddress1 = createString("vaultBillAddress1");

    public final StringPath vaultBillAddress2 = createString("vaultBillAddress2");

    public final StringPath vaultBillCity = createString("vaultBillCity");

    public final StringPath vaultBillCountry = createString("vaultBillCountry");

    public final StringPath vaultBillEmail = createString("vaultBillEmail");

    public final StringPath vaultBillPhone = createString("vaultBillPhone");

    public final StringPath vaultBillState = createString("vaultBillState");

    public final StringPath vaultBillZip = createString("vaultBillZip");

    public final StringPath vaultCardType = createString("vaultCardType");

    public final StringPath vaultFirstName = createString("vaultFirstName");

    public final StringPath vaultLastName = createString("vaultLastName");

    public final StringPath vaultMonthExpire = createString("vaultMonthExpire");

    public final StringPath vaultYearExpire = createString("vaultYearExpire");

    public QPaymentCardReply(String variable) {
        super(PaymentCardReply.class, forVariable(variable), "dbo", "payment_card_reply");
        addMetadata();
    }

    public QPaymentCardReply(String variable, String schema, String table) {
        super(PaymentCardReply.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QPaymentCardReply(String variable, String schema) {
        super(PaymentCardReply.class, forVariable(variable), schema, "payment_card_reply");
        addMetadata();
    }

    public QPaymentCardReply(Path<? extends PaymentCardReply> path) {
        super(path.getType(), path.getMetadata(), "dbo", "payment_card_reply");
        addMetadata();
    }

    public QPaymentCardReply(PathMetadata metadata) {
        super(PaymentCardReply.class, metadata, "dbo", "payment_card_reply");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(actionCode, ColumnMetadata.named("action_code").withIndex(24).ofType(Types.VARCHAR).withSize(35));
        addMetadata(batch, ColumnMetadata.named("batch").withIndex(23).ofType(Types.CHAR).withSize(1));
        addMetadata(batchDatetime, ColumnMetadata.named("batch_datetime").withIndex(20).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(batchNumber, ColumnMetadata.named("batch_number").withIndex(19).ofType(Types.VARCHAR).withSize(60));
        addMetadata(batchStatus, ColumnMetadata.named("batch_status").withIndex(21).ofType(Types.VARCHAR).withSize(500));
        addMetadata(fsaCard, ColumnMetadata.named("fsa_card").withIndex(25).ofType(Types.CHAR).withSize(1));
        addMetadata(glPostNum, ColumnMetadata.named("gl_post_num").withIndex(1).ofType(Types.NUMERIC).withSize(30));
        addMetadata(manual, ColumnMetadata.named("manual").withIndex(22).ofType(Types.CHAR).withSize(1));
        addMetadata(orderNum, ColumnMetadata.named("order_num").withIndex(15).ofType(Types.NUMERIC).withSize(30));
        addMetadata(paymentCardNumber, ColumnMetadata.named("payment_card_number").withIndex(17).ofType(Types.VARCHAR).withSize(60));
        addMetadata(paymentCardSeqNum, ColumnMetadata.named("payment_card_seq_num").withIndex(18).ofType(Types.VARCHAR).withSize(60));
        addMetadata(pcAvsCode, ColumnMetadata.named("pc_avs_code").withIndex(11).ofType(Types.CHAR).withSize(1));
        addMetadata(pcDatetime, ColumnMetadata.named("pc_datetime").withIndex(16).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(pcLog, ColumnMetadata.named("pc_log").withIndex(28).ofType(Types.VARCHAR).withSize(1500));
        addMetadata(pcReplyAmount, ColumnMetadata.named("pc_reply_amount").withIndex(9).ofType(Types.NUMERIC).withSize(12));
        addMetadata(pcReplyAuthCode, ColumnMetadata.named("pc_reply_auth_code").withIndex(10).ofType(Types.VARCHAR).withSize(65));
        addMetadata(pcReplyMsg, ColumnMetadata.named("pc_reply_msg").withIndex(14).ofType(Types.LONGVARBINARY).withSize(2147483647));
        addMetadata(pcReplyRcode, ColumnMetadata.named("pc_reply_rcode").withIndex(6).ofType(Types.VARCHAR).withSize(10));
        addMetadata(pcReplyRequestId, ColumnMetadata.named("pc_reply_request_id").withIndex(5).ofType(Types.VARCHAR).withSize(255));
        addMetadata(pcReplyRflag, ColumnMetadata.named("pc_reply_rflag").withIndex(7).ofType(Types.VARCHAR).withSize(255));
        addMetadata(pcReplyRmsg, ColumnMetadata.named("pc_reply_rmsg").withIndex(8).ofType(Types.VARCHAR).withSize(1500));
        addMetadata(pcReplySeqNum, ColumnMetadata.named("pc_reply_seq_num").withIndex(3).ofType(Types.NUMERIC).withSize(35).notNull());
        addMetadata(pcReplyTimestamp, ColumnMetadata.named("pc_reply_timestamp").withIndex(12).ofType(Types.VARCHAR).withSize(80));
        addMetadata(pcReplyType, ColumnMetadata.named("pc_reply_type").withIndex(4).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(pcRequestMsg, ColumnMetadata.named("pc_request_msg").withIndex(13).ofType(Types.LONGVARBINARY).withSize(2147483647));
        addMetadata(pcSubStatusNum, ColumnMetadata.named("pc_sub_status_num").withIndex(2).ofType(Types.NUMERIC).withSize(2));
        addMetadata(provider, ColumnMetadata.named("provider").withIndex(26).ofType(Types.VARCHAR).withSize(60));
        addMetadata(respAvsCode, ColumnMetadata.named("resp_avs_code").withIndex(27).ofType(Types.VARCHAR).withSize(300));
        addMetadata(vaultBatchSettledDatetime, ColumnMetadata.named("vault_batch_settled_datetime").withIndex(42).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(vaultBillAddress1, ColumnMetadata.named("vault_bill_address1").withIndex(31).ofType(Types.VARCHAR).withSize(60));
        addMetadata(vaultBillAddress2, ColumnMetadata.named("vault_bill_address2").withIndex(32).ofType(Types.VARCHAR).withSize(60));
        addMetadata(vaultBillCity, ColumnMetadata.named("vault_bill_city").withIndex(33).ofType(Types.VARCHAR).withSize(60));
        addMetadata(vaultBillCountry, ColumnMetadata.named("vault_bill_country").withIndex(35).ofType(Types.VARCHAR).withSize(10));
        addMetadata(vaultBillEmail, ColumnMetadata.named("vault_bill_email").withIndex(37).ofType(Types.VARCHAR).withSize(60));
        addMetadata(vaultBillPhone, ColumnMetadata.named("vault_bill_phone").withIndex(38).ofType(Types.VARCHAR).withSize(60));
        addMetadata(vaultBillState, ColumnMetadata.named("vault_bill_state").withIndex(34).ofType(Types.VARCHAR).withSize(10));
        addMetadata(vaultBillZip, ColumnMetadata.named("vault_bill_zip").withIndex(36).ofType(Types.VARCHAR).withSize(10));
        addMetadata(vaultCardType, ColumnMetadata.named("vault_card_type").withIndex(41).ofType(Types.VARCHAR).withSize(4));
        addMetadata(vaultFirstName, ColumnMetadata.named("vault_first_name").withIndex(29).ofType(Types.VARCHAR).withSize(60));
        addMetadata(vaultLastName, ColumnMetadata.named("vault_last_name").withIndex(30).ofType(Types.VARCHAR).withSize(60));
        addMetadata(vaultMonthExpire, ColumnMetadata.named("vault_month_expire").withIndex(40).ofType(Types.VARCHAR).withSize(2));
        addMetadata(vaultYearExpire, ColumnMetadata.named("vault_year_expire").withIndex(39).ofType(Types.VARCHAR).withSize(4));
    }

}

