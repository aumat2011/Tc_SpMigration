package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.RxLinkLogging;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QRxLinkLogging is a Querydsl query type for RxLinkLogging
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QRxLinkLogging extends com.querydsl.sql.RelationalPathBase<RxLinkLogging> {

    private static final long serialVersionUID = 571719109;

    public static final QRxLinkLogging rxLinkLogging = new QRxLinkLogging("rx_link_logging");

    public final StringPath action = createString("action");

    public final NumberPath<java.math.BigInteger> childEScriptMsgAttrSeq = createNumber("childEScriptMsgAttrSeq", java.math.BigInteger.class);

    public final StringPath childRxNumber = createString("childRxNumber");

    public final DateTimePath<java.sql.Timestamp> logDatetime = createDateTime("logDatetime", java.sql.Timestamp.class);

    public final NumberPath<Long> logUser = createNumber("logUser", Long.class);

    public final NumberPath<java.math.BigInteger> parentEScriptMsgAttrSeq = createNumber("parentEScriptMsgAttrSeq", java.math.BigInteger.class);

    public final StringPath parentRxNumber = createString("parentRxNumber");

    public final NumberPath<java.math.BigInteger> rxLinkLogSeq = createNumber("rxLinkLogSeq", java.math.BigInteger.class);

    public QRxLinkLogging(String variable) {
        super(RxLinkLogging.class, forVariable(variable), "dbo", "rx_link_logging");
        addMetadata();
    }

    public QRxLinkLogging(String variable, String schema, String table) {
        super(RxLinkLogging.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QRxLinkLogging(String variable, String schema) {
        super(RxLinkLogging.class, forVariable(variable), schema, "rx_link_logging");
        addMetadata();
    }

    public QRxLinkLogging(Path<? extends RxLinkLogging> path) {
        super(path.getType(), path.getMetadata(), "dbo", "rx_link_logging");
        addMetadata();
    }

    public QRxLinkLogging(PathMetadata metadata) {
        super(RxLinkLogging.class, metadata, "dbo", "rx_link_logging");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(action, ColumnMetadata.named("ACTION").withIndex(6).ofType(Types.VARCHAR).withSize(500));
        addMetadata(childEScriptMsgAttrSeq, ColumnMetadata.named("child_e_script_msg_attr_seq").withIndex(5).ofType(Types.NUMERIC).withSize(35));
        addMetadata(childRxNumber, ColumnMetadata.named("child_rx_number").withIndex(4).ofType(Types.VARCHAR).withSize(60));
        addMetadata(logDatetime, ColumnMetadata.named("LOG_DATETIME").withIndex(7).ofType(Types.TIMESTAMP).withSize(27).withDigits(7));
        addMetadata(logUser, ColumnMetadata.named("LOG_USER").withIndex(8).ofType(Types.NUMERIC).withSize(10));
        addMetadata(parentEScriptMsgAttrSeq, ColumnMetadata.named("parent_e_script_msg_attr_seq").withIndex(3).ofType(Types.NUMERIC).withSize(35));
        addMetadata(parentRxNumber, ColumnMetadata.named("parent_rx_number").withIndex(2).ofType(Types.VARCHAR).withSize(60));
        addMetadata(rxLinkLogSeq, ColumnMetadata.named("rx_link_log_seq").withIndex(1).ofType(Types.NUMERIC).withSize(35).notNull());
    }

}

