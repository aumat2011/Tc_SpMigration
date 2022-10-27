package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.RuleQueueException;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QRuleQueueException is a Querydsl query type for RuleQueueException
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QRuleQueueException extends com.querydsl.sql.RelationalPathBase<RuleQueueException> {

    private static final long serialVersionUID = 285453876;

    public static final QRuleQueueException ruleQueueException = new QRuleQueueException("rule_queue_exception");

    public final StringPath available = createString("available");

    public final NumberPath<java.math.BigInteger> eScriptMsgAttributeSeq = createNumber("eScriptMsgAttributeSeq", java.math.BigInteger.class);

    public final NumberPath<java.math.BigInteger> orderNum = createNumber("orderNum", java.math.BigInteger.class);

    public final NumberPath<java.math.BigInteger> outboundFaxSeq = createNumber("outboundFaxSeq", java.math.BigInteger.class);

    public final NumberPath<java.math.BigInteger> ruleEngineId = createNumber("ruleEngineId", java.math.BigInteger.class);

    public final StringPath ruleExceptionContext = createString("ruleExceptionContext");

    public final StringPath ruleExceptionContextRfc = createString("ruleExceptionContextRfc");

    public final DateTimePath<java.sql.Timestamp> ruleExceptionDatetime = createDateTime("ruleExceptionDatetime", java.sql.Timestamp.class);

    public final StringPath ruleExceptionKeynum = createString("ruleExceptionKeynum");

    public final StringPath ruleExceptionKeynum1 = createString("ruleExceptionKeynum1");

    public final StringPath ruleExceptionKeynum2 = createString("ruleExceptionKeynum2");

    public final NumberPath<java.math.BigInteger> ruleExceptionLink = createNumber("ruleExceptionLink", java.math.BigInteger.class);

    public final StringPath ruleExceptionScriptText = createString("ruleExceptionScriptText");

    public final StringPath ruleExceptionSeqnum = createString("ruleExceptionSeqnum");

    public final StringPath ruleExceptionSoft = createString("ruleExceptionSoft");

    public final StringPath ruleExceptionSubcontext = createString("ruleExceptionSubcontext");

    public final StringPath ruleExceptionText = createString("ruleExceptionText");

    public final NumberPath<java.math.BigInteger> ruleQueueExceptionSeq = createNumber("ruleQueueExceptionSeq", java.math.BigInteger.class);

    public final NumberPath<java.math.BigInteger> ruleQueueTransferSeq = createNumber("ruleQueueTransferSeq", java.math.BigInteger.class);

    public final StringPath rxNumber = createString("rxNumber");

    public final NumberPath<Long> underReviewByUser = createNumber("underReviewByUser", Long.class);

    public final NumberPath<Integer> workflowQueueNum = createNumber("workflowQueueNum", Integer.class);

    public final StringPath workflowStep = createString("workflowStep");

    public final StringPath workflowStepNum = createString("workflowStepNum");

    public final NumberPath<java.math.BigInteger> workflowTxnId = createNumber("workflowTxnId", java.math.BigInteger.class);

    public QRuleQueueException(String variable) {
        super(RuleQueueException.class, forVariable(variable), "dbo", "rule_queue_exception");
        addMetadata();
    }

    public QRuleQueueException(String variable, String schema, String table) {
        super(RuleQueueException.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QRuleQueueException(String variable, String schema) {
        super(RuleQueueException.class, forVariable(variable), schema, "rule_queue_exception");
        addMetadata();
    }

    public QRuleQueueException(Path<? extends RuleQueueException> path) {
        super(path.getType(), path.getMetadata(), "dbo", "rule_queue_exception");
        addMetadata();
    }

    public QRuleQueueException(PathMetadata metadata) {
        super(RuleQueueException.class, metadata, "dbo", "rule_queue_exception");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(available, ColumnMetadata.named("available").withIndex(5).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(eScriptMsgAttributeSeq, ColumnMetadata.named("e_script_msg_attribute_seq").withIndex(8).ofType(Types.NUMERIC).withSize(35).notNull());
        addMetadata(orderNum, ColumnMetadata.named("order_num").withIndex(12).ofType(Types.NUMERIC).withSize(30));
        addMetadata(outboundFaxSeq, ColumnMetadata.named("outbound_fax_seq").withIndex(13).ofType(Types.NUMERIC).withSize(25));
        addMetadata(ruleEngineId, ColumnMetadata.named("rule_engine_id").withIndex(11).ofType(Types.NUMERIC).withSize(32));
        addMetadata(ruleExceptionContext, ColumnMetadata.named("rule_exception_context").withIndex(17).ofType(Types.VARCHAR).withSize(60));
        addMetadata(ruleExceptionContextRfc, ColumnMetadata.named("rule_exception_context_rfc").withIndex(18).ofType(Types.VARCHAR).withSize(60));
        addMetadata(ruleExceptionDatetime, ColumnMetadata.named("rule_exception_datetime").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(3).notNull());
        addMetadata(ruleExceptionKeynum, ColumnMetadata.named("rule_exception_keynum").withIndex(20).ofType(Types.VARCHAR).withSize(60));
        addMetadata(ruleExceptionKeynum1, ColumnMetadata.named("rule_exception_keynum1").withIndex(21).ofType(Types.VARCHAR).withSize(60));
        addMetadata(ruleExceptionKeynum2, ColumnMetadata.named("rule_exception_keynum2").withIndex(22).ofType(Types.VARCHAR).withSize(60));
        addMetadata(ruleExceptionLink, ColumnMetadata.named("rule_exception_link").withIndex(6).ofType(Types.NUMERIC).withSize(35));
        addMetadata(ruleExceptionScriptText, ColumnMetadata.named("rule_exception_script_text").withIndex(16).ofType(Types.VARCHAR).withSize(2500));
        addMetadata(ruleExceptionSeqnum, ColumnMetadata.named("rule_exception_seqnum").withIndex(19).ofType(Types.VARCHAR).withSize(60));
        addMetadata(ruleExceptionSoft, ColumnMetadata.named("rule_exception_soft").withIndex(23).ofType(Types.CHAR).withSize(1));
        addMetadata(ruleExceptionSubcontext, ColumnMetadata.named("rule_exception_subcontext").withIndex(25).ofType(Types.VARCHAR).withSize(60));
        addMetadata(ruleExceptionText, ColumnMetadata.named("rule_exception_text").withIndex(15).ofType(Types.VARCHAR).withSize(3500));
        addMetadata(ruleQueueExceptionSeq, ColumnMetadata.named("rule_queue_exception_seq").withIndex(1).ofType(Types.NUMERIC).withSize(35).notNull());
        addMetadata(ruleQueueTransferSeq, ColumnMetadata.named("rule_queue_transfer_seq").withIndex(24).ofType(Types.NUMERIC).withSize(30));
        addMetadata(rxNumber, ColumnMetadata.named("rx_number").withIndex(14).ofType(Types.VARCHAR).withSize(60));
        addMetadata(underReviewByUser, ColumnMetadata.named("under_review_by_user").withIndex(2).ofType(Types.NUMERIC).withSize(16));
        addMetadata(workflowQueueNum, ColumnMetadata.named("workflow_queue_num").withIndex(3).ofType(Types.NUMERIC).withSize(5));
        addMetadata(workflowStep, ColumnMetadata.named("workflow_step").withIndex(9).ofType(Types.VARCHAR).withSize(60));
        addMetadata(workflowStepNum, ColumnMetadata.named("workflow_step_num").withIndex(10).ofType(Types.VARCHAR).withSize(60));
        addMetadata(workflowTxnId, ColumnMetadata.named("workflow_txn_id").withIndex(7).ofType(Types.NUMERIC).withSize(30));
    }

}

