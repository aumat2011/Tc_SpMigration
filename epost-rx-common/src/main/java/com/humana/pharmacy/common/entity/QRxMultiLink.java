package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.RxMultiLink;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QRxMultiLink is a Querydsl query type for RxMultiLink
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QRxMultiLink extends com.querydsl.sql.RelationalPathBase<RxMultiLink> {

    private static final long serialVersionUID = -1593656173;

    public static final QRxMultiLink rxMultiLink = new QRxMultiLink("rx_Multi_Link");

    public final StringPath active = createString("active");

    public final NumberPath<java.math.BigInteger> childEScriptMsgAttributeSeq = createNumber("childEScriptMsgAttributeSeq", java.math.BigInteger.class);

    public final StringPath childRxNumber = createString("childRxNumber");

    public final StringPath comment = createString("comment");

    public final DateTimePath<java.sql.Timestamp> createdDatetime = createDateTime("createdDatetime", java.sql.Timestamp.class);

    public final NumberPath<Long> createdSysUserNum = createNumber("createdSysUserNum", Long.class);

    public final DateTimePath<java.sql.Timestamp> deactivatedDatetime = createDateTime("deactivatedDatetime", java.sql.Timestamp.class);

    public final NumberPath<Long> deactivatedSysUserNum = createNumber("deactivatedSysUserNum", Long.class);

    public final NumberPath<java.math.BigInteger> parentEScriptMsgAttributeSeq = createNumber("parentEScriptMsgAttributeSeq", java.math.BigInteger.class);

    public final StringPath parentRxNumber = createString("parentRxNumber");

    public final NumberPath<Long> rxLinkSeq = createNumber("rxLinkSeq", Long.class);

    public QRxMultiLink(String variable) {
        super(RxMultiLink.class, forVariable(variable), "dbo", "rx_Multi_Link");
        addMetadata();
    }

    public QRxMultiLink(String variable, String schema, String table) {
        super(RxMultiLink.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QRxMultiLink(String variable, String schema) {
        super(RxMultiLink.class, forVariable(variable), schema, "rx_Multi_Link");
        addMetadata();
    }

    public QRxMultiLink(Path<? extends RxMultiLink> path) {
        super(path.getType(), path.getMetadata(), "dbo", "rx_Multi_Link");
        addMetadata();
    }

    public QRxMultiLink(PathMetadata metadata) {
        super(RxMultiLink.class, metadata, "dbo", "rx_Multi_Link");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(active, ColumnMetadata.named("active").withIndex(6).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(childEScriptMsgAttributeSeq, ColumnMetadata.named("child_e_script_msg_attribute_seq").withIndex(5).ofType(Types.NUMERIC).withSize(30).notNull());
        addMetadata(childRxNumber, ColumnMetadata.named("child_rx_number").withIndex(3).ofType(Types.VARCHAR).withSize(60).notNull());
        addMetadata(comment, ColumnMetadata.named("comment").withIndex(11).ofType(Types.VARCHAR).withSize(2147483647));
        addMetadata(createdDatetime, ColumnMetadata.named("created_datetime").withIndex(8).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(createdSysUserNum, ColumnMetadata.named("created_sys_user_num").withIndex(7).ofType(Types.NUMERIC).withSize(16));
        addMetadata(deactivatedDatetime, ColumnMetadata.named("deactivated_datetime").withIndex(10).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(deactivatedSysUserNum, ColumnMetadata.named("deactivated_sys_user_num").withIndex(9).ofType(Types.NUMERIC).withSize(16));
        addMetadata(parentEScriptMsgAttributeSeq, ColumnMetadata.named("parent_e_script_msg_attribute_seq").withIndex(4).ofType(Types.NUMERIC).withSize(30).notNull());
        addMetadata(parentRxNumber, ColumnMetadata.named("parent_rx_number").withIndex(2).ofType(Types.VARCHAR).withSize(60).notNull());
        addMetadata(rxLinkSeq, ColumnMetadata.named("rx_link_seq").withIndex(1).ofType(Types.NUMERIC).withSize(16).notNull());
    }

}

