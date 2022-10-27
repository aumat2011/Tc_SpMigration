package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.WorkflowQueueNames;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QWorkflowQueueNames is a Querydsl query type for WorkflowQueueNames
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QWorkflowQueueNames extends com.querydsl.sql.RelationalPathBase<WorkflowQueueNames> {

    private static final long serialVersionUID = -1150559312;

    public static final QWorkflowQueueNames workflowQueueNames = new QWorkflowQueueNames("workflow_queue_names");

    public final StringPath workflowQueueDesc = createString("workflowQueueDesc");

    public final StringPath workflowQueueName = createString("workflowQueueName");

    public final NumberPath<Integer> workflowQueueNameId = createNumber("workflowQueueNameId", Integer.class);

    public QWorkflowQueueNames(String variable) {
        super(WorkflowQueueNames.class, forVariable(variable), "dbo", "workflow_queue_names");
        addMetadata();
    }

    public QWorkflowQueueNames(String variable, String schema, String table) {
        super(WorkflowQueueNames.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QWorkflowQueueNames(String variable, String schema) {
        super(WorkflowQueueNames.class, forVariable(variable), schema, "workflow_queue_names");
        addMetadata();
    }

    public QWorkflowQueueNames(Path<? extends WorkflowQueueNames> path) {
        super(path.getType(), path.getMetadata(), "dbo", "workflow_queue_names");
        addMetadata();
    }

    public QWorkflowQueueNames(PathMetadata metadata) {
        super(WorkflowQueueNames.class, metadata, "dbo", "workflow_queue_names");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(workflowQueueDesc, ColumnMetadata.named("workflow_queue_desc").withIndex(3).ofType(Types.VARCHAR).withSize(255));
        addMetadata(workflowQueueName, ColumnMetadata.named("workflow_queue_name").withIndex(2).ofType(Types.VARCHAR).withSize(60).notNull());
        addMetadata(workflowQueueNameId, ColumnMetadata.named("workflow_queue_name_id").withIndex(1).ofType(Types.NUMERIC).withSize(7).notNull());
    }

}

