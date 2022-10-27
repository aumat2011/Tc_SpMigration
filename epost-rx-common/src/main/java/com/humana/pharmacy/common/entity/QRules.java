package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.Rules;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QRules is a Querydsl query type for Rules
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QRules extends com.querydsl.sql.RelationalPathBase<Rules> {

    private static final long serialVersionUID = -1849125667;

    public static final QRules rules = new QRules("rules");

    public final StringPath allowOverride = createString("allowOverride");

    public final NumberPath<Integer> businessHourTypeId = createNumber("businessHourTypeId", Integer.class);

    public final NumberPath<Byte> operatorId = createNumber("operatorId", Byte.class);

    public final StringPath orderBasedRule = createString("orderBasedRule");

    public final NumberPath<Integer> resourceConnectorId = createNumber("resourceConnectorId", Integer.class);

    public final NumberPath<Long> routingTpNum = createNumber("routingTpNum", Long.class);

    public final NumberPath<Byte> ruleActionId = createNumber("ruleActionId", Byte.class);

    public final NumberPath<Integer> ruleCriteriaId = createNumber("ruleCriteriaId", Integer.class);

    public final StringPath ruleDesc = createString("ruleDesc");

    public final StringPath ruleEnabled = createString("ruleEnabled");

    public final NumberPath<Integer> ruleGroupId = createNumber("ruleGroupId", Integer.class);

    public final NumberPath<Long> ruleId = createNumber("ruleId", Long.class);

    public final NumberPath<Integer> ruleListTableId = createNumber("ruleListTableId", Integer.class);

    public final StringPath ruleName = createString("ruleName");

    public final StringPath ruleParams = createString("ruleParams");

    public final NumberPath<Byte> ruleResponseId = createNumber("ruleResponseId", Byte.class);

    public final NumberPath<Byte> ruleRetry = createNumber("ruleRetry", Byte.class);

    public final StringPath ruleValue = createString("ruleValue");

    public final NumberPath<Integer> serialNum = createNumber("serialNum", Integer.class);

    public final NumberPath<Integer> serverNum = createNumber("serverNum", Integer.class);

    public final NumberPath<Long> tpConnectivitySeq = createNumber("tpConnectivitySeq", Long.class);

    public final NumberPath<Long> tpContactSeq = createNumber("tpContactSeq", Long.class);

    public final NumberPath<Long> tradingPartnerNum = createNumber("tradingPartnerNum", Long.class);

    public final NumberPath<Integer> workflowNum = createNumber("workflowNum", Integer.class);

    public final NumberPath<Integer> workflowQueueNum = createNumber("workflowQueueNum", Integer.class);

    public QRules(String variable) {
        super(Rules.class, forVariable(variable), "dbo", "rules");
        addMetadata();
    }

    public QRules(String variable, String schema, String table) {
        super(Rules.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QRules(String variable, String schema) {
        super(Rules.class, forVariable(variable), schema, "rules");
        addMetadata();
    }

    public QRules(Path<? extends Rules> path) {
        super(path.getType(), path.getMetadata(), "dbo", "rules");
        addMetadata();
    }

    public QRules(PathMetadata metadata) {
        super(Rules.class, metadata, "dbo", "rules");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(allowOverride, ColumnMetadata.named("allow_override").withIndex(23).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(businessHourTypeId, ColumnMetadata.named("business_hour_type_id").withIndex(8).ofType(Types.NUMERIC).withSize(5));
        addMetadata(operatorId, ColumnMetadata.named("operator_id").withIndex(13).ofType(Types.NUMERIC).withSize(2));
        addMetadata(orderBasedRule, ColumnMetadata.named("order_based_rule").withIndex(24).ofType(Types.VARCHAR).withSize(1));
        addMetadata(resourceConnectorId, ColumnMetadata.named("resource_connector_id").withIndex(7).ofType(Types.NUMERIC).withSize(7));
        addMetadata(routingTpNum, ColumnMetadata.named("routing_tp_num").withIndex(12).ofType(Types.NUMERIC).withSize(16));
        addMetadata(ruleActionId, ColumnMetadata.named("rule_action_id").withIndex(9).ofType(Types.NUMERIC).withSize(2).notNull());
        addMetadata(ruleCriteriaId, ColumnMetadata.named("rule_criteria_id").withIndex(2).ofType(Types.NUMERIC).withSize(5));
        addMetadata(ruleDesc, ColumnMetadata.named("rule_desc").withIndex(14).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(ruleEnabled, ColumnMetadata.named("rule_enabled").withIndex(20).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(ruleGroupId, ColumnMetadata.named("rule_group_id").withIndex(10).ofType(Types.NUMERIC).withSize(5));
        addMetadata(ruleId, ColumnMetadata.named("rule_id").withIndex(1).ofType(Types.NUMERIC).withSize(16).notNull());
        addMetadata(ruleListTableId, ColumnMetadata.named("rule_list_table_id").withIndex(11).ofType(Types.NUMERIC).withSize(5));
        addMetadata(ruleName, ColumnMetadata.named("rule_name").withIndex(17).ofType(Types.VARCHAR).withSize(60).notNull());
        addMetadata(ruleParams, ColumnMetadata.named("rule_params").withIndex(25).ofType(Types.VARCHAR).withSize(500));
        addMetadata(ruleResponseId, ColumnMetadata.named("rule_response_id").withIndex(5).ofType(Types.NUMERIC).withSize(2));
        addMetadata(ruleRetry, ColumnMetadata.named("rule_retry").withIndex(22).ofType(Types.NUMERIC).withSize(2));
        addMetadata(ruleValue, ColumnMetadata.named("rule_value").withIndex(16).ofType(Types.VARCHAR).withSize(45));
        addMetadata(serialNum, ColumnMetadata.named("serial_num").withIndex(4).ofType(Types.NUMERIC).withSize(5));
        addMetadata(serverNum, ColumnMetadata.named("server_num").withIndex(21).ofType(Types.NUMERIC).withSize(5));
        addMetadata(tpConnectivitySeq, ColumnMetadata.named("tp_connectivity_seq").withIndex(19).ofType(Types.NUMERIC).withSize(16));
        addMetadata(tpContactSeq, ColumnMetadata.named("tp_contact_seq").withIndex(18).ofType(Types.NUMERIC).withSize(16));
        addMetadata(tradingPartnerNum, ColumnMetadata.named("trading_partner_num").withIndex(15).ofType(Types.NUMERIC).withSize(16));
        addMetadata(workflowNum, ColumnMetadata.named("workflow_num").withIndex(6).ofType(Types.NUMERIC).withSize(5).notNull());
        addMetadata(workflowQueueNum, ColumnMetadata.named("workflow_queue_num").withIndex(3).ofType(Types.NUMERIC).withSize(5));
    }

}

