package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.RxParamsGpis;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QRxParamsGpis is a Querydsl query type for RxParamsGpis
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QRxParamsGpis extends com.querydsl.sql.RelationalPathBase<RxParamsGpis> {

    private static final long serialVersionUID = 1374858937;

    public static final QRxParamsGpis rxParamsGpis = new QRxParamsGpis("rx_params_gpis");

    public final StringPath acceptFax = createString("acceptFax");

    public final StringPath gpiClassDesc = createString("gpiClassDesc");

    public final StringPath gpiClassId = createString("gpiClassId");

    public final NumberPath<Integer> maxDaysSupply = createNumber("maxDaysSupply", Integer.class);

    public final NumberPath<Integer> minPctUtilization = createNumber("minPctUtilization", Integer.class);

    public final NumberPath<Integer> numDaysExpire = createNumber("numDaysExpire", Integer.class);

    public final NumberPath<Integer> numDaysFromOrigDate = createNumber("numDaysFromOrigDate", Integer.class);

    public final NumberPath<Integer> numRefillsAllowed = createNumber("numRefillsAllowed", Integer.class);

    public final StringPath sendFax = createString("sendFax");

    public final NumberPath<Integer> stateNum = createNumber("stateNum", Integer.class);

    public QRxParamsGpis(String variable) {
        super(RxParamsGpis.class, forVariable(variable), "dbo", "rx_params_gpis");
        addMetadata();
    }

    public QRxParamsGpis(String variable, String schema, String table) {
        super(RxParamsGpis.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QRxParamsGpis(String variable, String schema) {
        super(RxParamsGpis.class, forVariable(variable), schema, "rx_params_gpis");
        addMetadata();
    }

    public QRxParamsGpis(Path<? extends RxParamsGpis> path) {
        super(path.getType(), path.getMetadata(), "dbo", "rx_params_gpis");
        addMetadata();
    }

    public QRxParamsGpis(PathMetadata metadata) {
        super(RxParamsGpis.class, metadata, "dbo", "rx_params_gpis");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(acceptFax, ColumnMetadata.named("accept_fax").withIndex(8).ofType(Types.CHAR).withSize(1));
        addMetadata(gpiClassDesc, ColumnMetadata.named("gpi_class_desc").withIndex(10).ofType(Types.VARCHAR).withSize(80));
        addMetadata(gpiClassId, ColumnMetadata.named("gpi_class_id").withIndex(1).ofType(Types.VARCHAR).withSize(60).notNull());
        addMetadata(maxDaysSupply, ColumnMetadata.named("max_days_supply").withIndex(6).ofType(Types.NUMERIC).withSize(5));
        addMetadata(minPctUtilization, ColumnMetadata.named("min_pct_utilization").withIndex(7).ofType(Types.NUMERIC).withSize(5));
        addMetadata(numDaysExpire, ColumnMetadata.named("num_days_expire").withIndex(5).ofType(Types.NUMERIC).withSize(5));
        addMetadata(numDaysFromOrigDate, ColumnMetadata.named("num_days_from_orig_date").withIndex(4).ofType(Types.NUMERIC).withSize(5));
        addMetadata(numRefillsAllowed, ColumnMetadata.named("num_refills_allowed").withIndex(3).ofType(Types.NUMERIC).withSize(5));
        addMetadata(sendFax, ColumnMetadata.named("send_fax").withIndex(9).ofType(Types.CHAR).withSize(1));
        addMetadata(stateNum, ColumnMetadata.named("state_num").withIndex(2).ofType(Types.NUMERIC).withSize(5).notNull());
    }

}

