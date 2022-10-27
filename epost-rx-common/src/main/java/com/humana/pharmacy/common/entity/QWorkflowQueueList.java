package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.WorkflowQueueList;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QWorkflowQueueList is a Querydsl query type for WorkflowQueueList
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QWorkflowQueueList extends com.querydsl.sql.RelationalPathBase<WorkflowQueueList> {

    private static final long serialVersionUID = -868450506;

    public static final QWorkflowQueueList workflowQueueList = new QWorkflowQueueList("workflow_queue_list");

    public final StringPath autoAlert = createString("autoAlert");

    public final NumberPath<Integer> barcodeLibraryId = createNumber("barcodeLibraryId", Integer.class);

    public final NumberPath<Integer> criticalLevel = createNumber("criticalLevel", Integer.class);

    public final NumberPath<Integer> maxDuration = createNumber("maxDuration", Integer.class);

    public final NumberPath<Integer> queuePrioritySeq = createNumber("queuePrioritySeq", Integer.class);

    public final NumberPath<Integer> resourceConnectorId = createNumber("resourceConnectorId", Integer.class);

    public final NumberPath<Integer> serialNum = createNumber("serialNum", Integer.class);

    public final NumberPath<Integer> serverNum = createNumber("serverNum", Integer.class);

    public final StringPath showPrefix = createString("showPrefix");

    public final NumberPath<Long> sysUserNum = createNumber("sysUserNum", Long.class);

    public final NumberPath<Long> tradingPartnerNum = createNumber("tradingPartnerNum", Long.class);

    public final StringPath turnaroundType = createString("turnaroundType");

    public final NumberPath<Integer> warningLevel = createNumber("warningLevel", Integer.class);

    public final NumberPath<Integer> workflowQueueNameId = createNumber("workflowQueueNameId", Integer.class);

    public final NumberPath<Integer> workflowQueueNum = createNumber("workflowQueueNum", Integer.class);

    public QWorkflowQueueList(String variable) {
        super(WorkflowQueueList.class, forVariable(variable), "dbo", "workflow_queue_list");
        addMetadata();
    }

    public QWorkflowQueueList(String variable, String schema, String table) {
        super(WorkflowQueueList.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QWorkflowQueueList(String variable, String schema) {
        super(WorkflowQueueList.class, forVariable(variable), schema, "workflow_queue_list");
        addMetadata();
    }

    public QWorkflowQueueList(Path<? extends WorkflowQueueList> path) {
        super(path.getType(), path.getMetadata(), "dbo", "workflow_queue_list");
        addMetadata();
    }

    public QWorkflowQueueList(PathMetadata metadata) {
        super(WorkflowQueueList.class, metadata, "dbo", "workflow_queue_list");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(autoAlert, ColumnMetadata.named("auto_alert").withIndex(11).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(barcodeLibraryId, ColumnMetadata.named("barcode_library_id").withIndex(3).ofType(Types.NUMERIC).withSize(5));
        addMetadata(criticalLevel, ColumnMetadata.named("critical_level").withIndex(7).ofType(Types.NUMERIC).withSize(5));
        addMetadata(maxDuration, ColumnMetadata.named("max_duration").withIndex(12).ofType(Types.NUMERIC).withSize(5));
        addMetadata(queuePrioritySeq, ColumnMetadata.named("queue_priority_seq").withIndex(15).ofType(Types.NUMERIC).withSize(5));
        addMetadata(resourceConnectorId, ColumnMetadata.named("resource_connector_id").withIndex(10).ofType(Types.NUMERIC).withSize(7));
        addMetadata(serialNum, ColumnMetadata.named("serial_num").withIndex(8).ofType(Types.NUMERIC).withSize(5));
        addMetadata(serverNum, ColumnMetadata.named("server_num").withIndex(9).ofType(Types.NUMERIC).withSize(5));
        addMetadata(showPrefix, ColumnMetadata.named("show_prefix").withIndex(13).ofType(Types.CHAR).withSize(1));
        addMetadata(sysUserNum, ColumnMetadata.named("sys_user_num").withIndex(4).ofType(Types.NUMERIC).withSize(16));
        addMetadata(tradingPartnerNum, ColumnMetadata.named("trading_partner_num").withIndex(5).ofType(Types.NUMERIC).withSize(16));
        addMetadata(turnaroundType, ColumnMetadata.named("turnaround_type").withIndex(14).ofType(Types.CHAR).withSize(1));
        addMetadata(warningLevel, ColumnMetadata.named("warning_level").withIndex(6).ofType(Types.NUMERIC).withSize(5));
        addMetadata(workflowQueueNameId, ColumnMetadata.named("workflow_queue_name_id").withIndex(2).ofType(Types.NUMERIC).withSize(7));
        addMetadata(workflowQueueNum, ColumnMetadata.named("workflow_queue_num").withIndex(1).ofType(Types.NUMERIC).withSize(5).notNull());
    }

}

