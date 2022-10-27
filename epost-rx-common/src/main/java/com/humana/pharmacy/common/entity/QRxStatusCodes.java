package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.RxStatusCodes;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QRxStatusCodes is a Querydsl query type for RxStatusCodes
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QRxStatusCodes extends com.querydsl.sql.RelationalPathBase<RxStatusCodes> {

    private static final long serialVersionUID = 1353223956;

    public static final QRxStatusCodes rxStatusCodes = new QRxStatusCodes("rx_status_codes");

    public final StringPath rxStatusCodeDesc = createString("rxStatusCodeDesc");

    public final NumberPath<Byte> rxStatusCodeNum = createNumber("rxStatusCodeNum", Byte.class);

    public QRxStatusCodes(String variable) {
        super(RxStatusCodes.class, forVariable(variable), "dbo", "rx_status_codes");
        addMetadata();
    }

    public QRxStatusCodes(String variable, String schema, String table) {
        super(RxStatusCodes.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QRxStatusCodes(String variable, String schema) {
        super(RxStatusCodes.class, forVariable(variable), schema, "rx_status_codes");
        addMetadata();
    }

    public QRxStatusCodes(Path<? extends RxStatusCodes> path) {
        super(path.getType(), path.getMetadata(), "dbo", "rx_status_codes");
        addMetadata();
    }

    public QRxStatusCodes(PathMetadata metadata) {
        super(RxStatusCodes.class, metadata, "dbo", "rx_status_codes");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(rxStatusCodeDesc, ColumnMetadata.named("rx_status_code_desc").withIndex(2).ofType(Types.VARCHAR).withSize(30).notNull());
        addMetadata(rxStatusCodeNum, ColumnMetadata.named("rx_status_code_num").withIndex(1).ofType(Types.NUMERIC).withSize(2).notNull());
    }

}

