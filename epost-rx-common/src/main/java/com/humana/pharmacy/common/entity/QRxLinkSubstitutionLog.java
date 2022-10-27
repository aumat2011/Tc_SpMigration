package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.RxLinkSubstitutionLog;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QRxLinkSubstitutionLog is a Querydsl query type for RxLinkSubstitutionLog
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QRxLinkSubstitutionLog extends com.querydsl.sql.RelationalPathBase<RxLinkSubstitutionLog> {

    private static final long serialVersionUID = -985786515;

    public static final QRxLinkSubstitutionLog rxLinkSubstitutionLog = new QRxLinkSubstitutionLog("rx_link_substitution_log");

    public final DateTimePath<java.sql.Timestamp> logDate = createDateTime("logDate", java.sql.Timestamp.class);

    public final NumberPath<java.math.BigInteger> orderNumber = createNumber("orderNumber", java.math.BigInteger.class);

    public final StringPath originalDispensedProdName = createString("originalDispensedProdName");

    public final StringPath originalRxNumber = createString("originalRxNumber");

    public final StringPath originalWrittenProdName = createString("originalWrittenProdName");

    public final NumberPath<java.math.BigInteger> rxLinkSubstituionLogSeq = createNumber("rxLinkSubstituionLogSeq", java.math.BigInteger.class);

    public final StringPath substitutedDispensedProdName = createString("substitutedDispensedProdName");

    public final StringPath substitutedRxNumber = createString("substitutedRxNumber");

    public final StringPath substitutedWrittenProdName = createString("substitutedWrittenProdName");

    public final StringPath typeOfAction = createString("typeOfAction");

    public final NumberPath<java.math.BigInteger> userNum = createNumber("userNum", java.math.BigInteger.class);

    public QRxLinkSubstitutionLog(String variable) {
        super(RxLinkSubstitutionLog.class, forVariable(variable), "dbo", "rx_link_substitution_log");
        addMetadata();
    }

    public QRxLinkSubstitutionLog(String variable, String schema, String table) {
        super(RxLinkSubstitutionLog.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QRxLinkSubstitutionLog(String variable, String schema) {
        super(RxLinkSubstitutionLog.class, forVariable(variable), schema, "rx_link_substitution_log");
        addMetadata();
    }

    public QRxLinkSubstitutionLog(Path<? extends RxLinkSubstitutionLog> path) {
        super(path.getType(), path.getMetadata(), "dbo", "rx_link_substitution_log");
        addMetadata();
    }

    public QRxLinkSubstitutionLog(PathMetadata metadata) {
        super(RxLinkSubstitutionLog.class, metadata, "dbo", "rx_link_substitution_log");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(logDate, ColumnMetadata.named("Log_Date").withIndex(11).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(orderNumber, ColumnMetadata.named("order_number").withIndex(8).ofType(Types.NUMERIC).withSize(35));
        addMetadata(originalDispensedProdName, ColumnMetadata.named("original_dispensed_prod_name").withIndex(5).ofType(Types.VARCHAR).withSize(60));
        addMetadata(originalRxNumber, ColumnMetadata.named("original_rx_number").withIndex(2).ofType(Types.VARCHAR).withSize(60));
        addMetadata(originalWrittenProdName, ColumnMetadata.named("original_written_prod_name").withIndex(4).ofType(Types.VARCHAR).withSize(60));
        addMetadata(rxLinkSubstituionLogSeq, ColumnMetadata.named("rx_link_substituion_log_seq").withIndex(1).ofType(Types.NUMERIC).withSize(35).notNull());
        addMetadata(substitutedDispensedProdName, ColumnMetadata.named("substituted_dispensed_prod_name").withIndex(7).ofType(Types.VARCHAR).withSize(60));
        addMetadata(substitutedRxNumber, ColumnMetadata.named("substituted_rx_number").withIndex(3).ofType(Types.VARCHAR).withSize(60));
        addMetadata(substitutedWrittenProdName, ColumnMetadata.named("substituted_written_prod_name").withIndex(6).ofType(Types.VARCHAR).withSize(60));
        addMetadata(typeOfAction, ColumnMetadata.named("type_of_action").withIndex(10).ofType(Types.VARCHAR).withSize(500));
        addMetadata(userNum, ColumnMetadata.named("User_num").withIndex(9).ofType(Types.NUMERIC).withSize(35));
    }

}

