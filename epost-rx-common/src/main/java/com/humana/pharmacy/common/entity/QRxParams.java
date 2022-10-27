package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.RxParams;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QRxParams is a Querydsl query type for RxParams
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QRxParams extends com.querydsl.sql.RelationalPathBase<RxParams> {

    private static final long serialVersionUID = 1805060550;

    public static final QRxParams rxParams = new QRxParams("rx_params");

    public final StringPath acceptFax = createString("acceptFax");

    public final StringPath autoRefill = createString("autoRefill");

    public final NumberPath<Integer> maxDaysSupply = createNumber("maxDaysSupply", Integer.class);

    public final NumberPath<Integer> minPctUtilization = createNumber("minPctUtilization", Integer.class);

    public final StringPath narcoticCode = createString("narcoticCode");

    public final StringPath notes = createString("notes");

    public final NumberPath<Integer> numDaysExpire = createNumber("numDaysExpire", Integer.class);

    public final NumberPath<Integer> numDaysFromOrigDate = createNumber("numDaysFromOrigDate", Integer.class);

    public final NumberPath<Integer> numRefillsAllowed = createNumber("numRefillsAllowed", Integer.class);

    public final StringPath popupText = createString("popupText");

    public final StringPath sendFax = createString("sendFax");

    public final NumberPath<Integer> stateNum = createNumber("stateNum", Integer.class);

    public final StringPath stateRestricted = createString("stateRestricted");

    public QRxParams(String variable) {
        super(RxParams.class, forVariable(variable), "dbo", "rx_params");
        addMetadata();
    }

    public QRxParams(String variable, String schema, String table) {
        super(RxParams.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QRxParams(String variable, String schema) {
        super(RxParams.class, forVariable(variable), schema, "rx_params");
        addMetadata();
    }

    public QRxParams(Path<? extends RxParams> path) {
        super(path.getType(), path.getMetadata(), "dbo", "rx_params");
        addMetadata();
    }

    public QRxParams(PathMetadata metadata) {
        super(RxParams.class, metadata, "dbo", "rx_params");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(acceptFax, ColumnMetadata.named("accept_fax").withIndex(8).ofType(Types.CHAR).withSize(1));
        addMetadata(autoRefill, ColumnMetadata.named("auto_refill").withIndex(11).ofType(Types.CHAR).withSize(1));
        addMetadata(maxDaysSupply, ColumnMetadata.named("max_days_supply").withIndex(6).ofType(Types.NUMERIC).withSize(5));
        addMetadata(minPctUtilization, ColumnMetadata.named("min_pct_utilization").withIndex(7).ofType(Types.NUMERIC).withSize(5));
        addMetadata(narcoticCode, ColumnMetadata.named("narcotic_code").withIndex(1).ofType(Types.CHAR).withSize(10).notNull());
        addMetadata(notes, ColumnMetadata.named("notes").withIndex(13).ofType(Types.VARCHAR).withSize(160));
        addMetadata(numDaysExpire, ColumnMetadata.named("num_days_expire").withIndex(5).ofType(Types.NUMERIC).withSize(5));
        addMetadata(numDaysFromOrigDate, ColumnMetadata.named("num_days_from_orig_date").withIndex(4).ofType(Types.NUMERIC).withSize(5));
        addMetadata(numRefillsAllowed, ColumnMetadata.named("num_refills_allowed").withIndex(3).ofType(Types.NUMERIC).withSize(5));
        addMetadata(popupText, ColumnMetadata.named("popup_text").withIndex(10).ofType(Types.VARCHAR).withSize(255));
        addMetadata(sendFax, ColumnMetadata.named("send_fax").withIndex(9).ofType(Types.CHAR).withSize(1));
        addMetadata(stateNum, ColumnMetadata.named("state_num").withIndex(2).ofType(Types.NUMERIC).withSize(5).notNull());
        addMetadata(stateRestricted, ColumnMetadata.named("state_restricted").withIndex(12).ofType(Types.CHAR).withSize(1));
    }

}

