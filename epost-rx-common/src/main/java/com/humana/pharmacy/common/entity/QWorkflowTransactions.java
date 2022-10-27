package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.WorkflowTransactions;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QWorkflowTransactions is a Querydsl query type for WorkflowTransactions
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QWorkflowTransactions extends com.querydsl.sql.RelationalPathBase<WorkflowTransactions> {

    private static final long serialVersionUID = -1468351986;

    public static final QWorkflowTransactions workflowTransactions = new QWorkflowTransactions("workflow_transactions");

    public final DateTimePath<java.sql.Timestamp> currentTxnDatetime = createDateTime("currentTxnDatetime", java.sql.Timestamp.class);

    public final NumberPath<java.math.BigInteger> ediMessageId = createNumber("ediMessageId", java.math.BigInteger.class);

    public final NumberPath<java.math.BigInteger> eScriptMsgAttributeSeq = createNumber("eScriptMsgAttributeSeq", java.math.BigInteger.class);

    public final StringPath exceptionSeq = createString("exceptionSeq");

    public final StringPath hostServer = createString("hostServer");

    public final DateTimePath<java.sql.Timestamp> initialTxnSubmitDatetime = createDateTime("initialTxnSubmitDatetime", java.sql.Timestamp.class);

    public final NumberPath<Byte> intransition = createNumber("intransition", Byte.class);

    public final StringPath jmsMsgId = createString("jmsMsgId");

    public final StringPath mode = createString("mode");

    public final NumberPath<java.math.BigInteger> orderNum = createNumber("orderNum", java.math.BigInteger.class);

    public final NumberPath<Byte> orderRunning = createNumber("orderRunning", Byte.class);

    public final StringPath queuename = createString("queuename");

    public final StringPath queuenum = createString("queuenum");

    public final NumberPath<java.math.BigInteger> ruleEngineId = createNumber("ruleEngineId", java.math.BigInteger.class);

    public final NumberPath<Byte> ruleEngineStatusId = createNumber("ruleEngineStatusId", Byte.class);

    public final NumberPath<Long> ruleId = createNumber("ruleId", Long.class);

    public final StringPath ruleOrderNum = createString("ruleOrderNum");

    public final StringPath rxnumber = createString("rxnumber");

    public final NumberPath<Byte> stepBusy = createNumber("stepBusy", Byte.class);

    public final StringPath tradingPartnerNum = createString("tradingPartnerNum");

    public final NumberPath<Integer> workflowNum = createNumber("workflowNum", Integer.class);

    public final StringPath workflowStep = createString("workflowStep");

    public final NumberPath<Byte> workflowStepNum = createNumber("workflowStepNum", Byte.class);

    public final NumberPath<java.math.BigInteger> workflowTxnId = createNumber("workflowTxnId", java.math.BigInteger.class);

    public QWorkflowTransactions(String variable) {
        super(WorkflowTransactions.class, forVariable(variable), "dbo", "workflow_transactions");
        addMetadata();
    }

    public QWorkflowTransactions(String variable, String schema, String table) {
        super(WorkflowTransactions.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QWorkflowTransactions(String variable, String schema) {
        super(WorkflowTransactions.class, forVariable(variable), schema, "workflow_transactions");
        addMetadata();
    }

    public QWorkflowTransactions(Path<? extends WorkflowTransactions> path) {
        super(path.getType(), path.getMetadata(), "dbo", "workflow_transactions");
        addMetadata();
    }

    public QWorkflowTransactions(PathMetadata metadata) {
        super(WorkflowTransactions.class, metadata, "dbo", "workflow_transactions");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(currentTxnDatetime, ColumnMetadata.named("current_txn_datetime").withIndex(9).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(ediMessageId, ColumnMetadata.named("edi_message_id").withIndex(14).ofType(Types.NUMERIC).withSize(35));
        addMetadata(eScriptMsgAttributeSeq, ColumnMetadata.named("e_script_msg_attribute_seq").withIndex(2).ofType(Types.NUMERIC).withSize(35).notNull());
        addMetadata(exceptionSeq, ColumnMetadata.named("exception_seq").withIndex(20).ofType(Types.VARCHAR).withSize(60));
        addMetadata(hostServer, ColumnMetadata.named("host_server").withIndex(23).ofType(Types.VARCHAR).withSize(80));
        addMetadata(initialTxnSubmitDatetime, ColumnMetadata.named("initial_txn_submit_datetime").withIndex(8).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(intransition, ColumnMetadata.named("intransition").withIndex(15).ofType(Types.NUMERIC).withSize(1));
        addMetadata(jmsMsgId, ColumnMetadata.named("jms_msg_id").withIndex(24).ofType(Types.VARCHAR).withSize(35));
        addMetadata(mode, ColumnMetadata.named("mode").withIndex(16).ofType(Types.VARCHAR).withSize(60));
        addMetadata(orderNum, ColumnMetadata.named("order_num").withIndex(5).ofType(Types.NUMERIC).withSize(30));
        addMetadata(orderRunning, ColumnMetadata.named("order_running").withIndex(10).ofType(Types.NUMERIC).withSize(1));
        addMetadata(queuename, ColumnMetadata.named("queuename").withIndex(18).ofType(Types.VARCHAR).withSize(60));
        addMetadata(queuenum, ColumnMetadata.named("queuenum").withIndex(19).ofType(Types.VARCHAR).withSize(60));
        addMetadata(ruleEngineId, ColumnMetadata.named("rule_engine_id").withIndex(13).ofType(Types.NUMERIC).withSize(35));
        addMetadata(ruleEngineStatusId, ColumnMetadata.named("rule_engine_status_id").withIndex(12).ofType(Types.NUMERIC).withSize(2));
        addMetadata(ruleId, ColumnMetadata.named("rule_id").withIndex(4).ofType(Types.NUMERIC).withSize(16));
        addMetadata(ruleOrderNum, ColumnMetadata.named("rule_order_num").withIndex(22).ofType(Types.VARCHAR).withSize(10));
        addMetadata(rxnumber, ColumnMetadata.named("rxnumber").withIndex(17).ofType(Types.VARCHAR).withSize(60));
        addMetadata(stepBusy, ColumnMetadata.named("step_busy").withIndex(11).ofType(Types.NUMERIC).withSize(1));
        addMetadata(tradingPartnerNum, ColumnMetadata.named("trading_partner_num").withIndex(21).ofType(Types.VARCHAR).withSize(60));
        addMetadata(workflowNum, ColumnMetadata.named("workflow_num").withIndex(3).ofType(Types.NUMERIC).withSize(5));
        addMetadata(workflowStep, ColumnMetadata.named("workflow_step").withIndex(6).ofType(Types.VARCHAR).withSize(60));
        addMetadata(workflowStepNum, ColumnMetadata.named("workflow_step_num").withIndex(7).ofType(Types.NUMERIC).withSize(2));
        addMetadata(workflowTxnId, ColumnMetadata.named("workflow_txn_id").withIndex(1).ofType(Types.NUMERIC).withSize(30).notNull());
    }

}

