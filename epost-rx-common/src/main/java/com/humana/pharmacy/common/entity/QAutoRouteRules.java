package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.AutoRouteRules;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QAutoRouteRules is a Querydsl query type for AutoRouteRules
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QAutoRouteRules extends com.querydsl.sql.RelationalPathBase<AutoRouteRules> {

    private static final long serialVersionUID = 1179323927;

    public static final QAutoRouteRules autoRouteRules = new QAutoRouteRules("auto_route_rules");

    public final NumberPath<java.math.BigInteger> autoRouteRuleSeq = createNumber("autoRouteRuleSeq", java.math.BigInteger.class);

    public final StringPath autoRouteTypeName = createString("autoRouteTypeName");

    public final StringPath locationNum = createString("locationNum");

    public final NumberPath<Integer> priority = createNumber("priority", Integer.class);

    public final StringPath targetDisplayValue = createString("targetDisplayValue");

    public final StringPath targetValue = createString("targetValue");

    public QAutoRouteRules(String variable) {
        super(AutoRouteRules.class, forVariable(variable), "dbo", "auto_route_rules");
        addMetadata();
    }

    public QAutoRouteRules(String variable, String schema, String table) {
        super(AutoRouteRules.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAutoRouteRules(String variable, String schema) {
        super(AutoRouteRules.class, forVariable(variable), schema, "auto_route_rules");
        addMetadata();
    }

    public QAutoRouteRules(Path<? extends AutoRouteRules> path) {
        super(path.getType(), path.getMetadata(), "dbo", "auto_route_rules");
        addMetadata();
    }

    public QAutoRouteRules(PathMetadata metadata) {
        super(AutoRouteRules.class, metadata, "dbo", "auto_route_rules");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(autoRouteRuleSeq, ColumnMetadata.named("auto_route_rule_seq").withIndex(1).ofType(Types.NUMERIC).withSize(20).notNull());
        addMetadata(autoRouteTypeName, ColumnMetadata.named("auto_route_type_name").withIndex(2).ofType(Types.VARCHAR).withSize(80));
        addMetadata(locationNum, ColumnMetadata.named("location_num").withIndex(3).ofType(Types.VARCHAR).withSize(60));
        addMetadata(priority, ColumnMetadata.named("priority").withIndex(6).ofType(Types.NUMERIC).withSize(7));
        addMetadata(targetDisplayValue, ColumnMetadata.named("target_display_value").withIndex(4).ofType(Types.VARCHAR).withSize(255));
        addMetadata(targetValue, ColumnMetadata.named("target_value").withIndex(5).ofType(Types.VARCHAR).withSize(60));
    }

}

