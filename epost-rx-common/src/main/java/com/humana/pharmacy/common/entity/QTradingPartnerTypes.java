package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.TradingPartnerTypes;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QTradingPartnerTypes is a Querydsl query type for TradingPartnerTypes
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QTradingPartnerTypes extends com.querydsl.sql.RelationalPathBase<TradingPartnerTypes> {

    private static final long serialVersionUID = -928204360;

    public static final QTradingPartnerTypes tradingPartnerTypes = new QTradingPartnerTypes("trading_partner_types");

    public final StringPath tradingPartnerTypeDesc = createString("tradingPartnerTypeDesc");

    public final NumberPath<Integer> tradingPartnerTypeId = createNumber("tradingPartnerTypeId", Integer.class);

    public QTradingPartnerTypes(String variable) {
        super(TradingPartnerTypes.class, forVariable(variable), "dbo", "trading_partner_types");
        addMetadata();
    }

    public QTradingPartnerTypes(String variable, String schema, String table) {
        super(TradingPartnerTypes.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTradingPartnerTypes(String variable, String schema) {
        super(TradingPartnerTypes.class, forVariable(variable), schema, "trading_partner_types");
        addMetadata();
    }

    public QTradingPartnerTypes(Path<? extends TradingPartnerTypes> path) {
        super(path.getType(), path.getMetadata(), "dbo", "trading_partner_types");
        addMetadata();
    }

    public QTradingPartnerTypes(PathMetadata metadata) {
        super(TradingPartnerTypes.class, metadata, "dbo", "trading_partner_types");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(tradingPartnerTypeDesc, ColumnMetadata.named("trading_partner_type_desc").withIndex(2).ofType(Types.VARCHAR).withSize(60).notNull());
        addMetadata(tradingPartnerTypeId, ColumnMetadata.named("trading_partner_type_id").withIndex(1).ofType(Types.NUMERIC).withSize(5).notNull());
    }

}

