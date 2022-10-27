package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.AutoEscriptFiller;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QAutoEscriptFiller is a Querydsl query type for AutoEscriptFiller
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QAutoEscriptFiller extends com.querydsl.sql.RelationalPathBase<AutoEscriptFiller> {

    private static final long serialVersionUID = -1046355849;

    public static final QAutoEscriptFiller autoEscriptFiller = new QAutoEscriptFiller("auto_escript_filler");

    public final DateTimePath<java.sql.Timestamp> autoEfillerDatetime = createDateTime("autoEfillerDatetime", java.sql.Timestamp.class);

    public final NumberPath<java.math.BigInteger> autoEfillerSeq = createNumber("autoEfillerSeq", java.math.BigInteger.class);

    public final StringPath batchNumber = createString("batchNumber");

    public final StringPath createOrder = createString("createOrder");

    public final NumberPath<java.math.BigInteger> escriptId = createNumber("escriptId", java.math.BigInteger.class);

    public final StringPath locationNum = createString("locationNum");

    public final StringPath logNote = createString("logNote");

    public final DateTimePath<java.sql.Timestamp> orderCreationDate = createDateTime("orderCreationDate", java.sql.Timestamp.class);

    public final StringPath orderInvoiceNumber = createString("orderInvoiceNumber");

    public final NumberPath<java.math.BigInteger> orderNum = createNumber("orderNum", java.math.BigInteger.class);

    public final NumberPath<java.math.BigInteger> patientNum = createNumber("patientNum", java.math.BigInteger.class);

    public final DateTimePath<java.sql.Timestamp> processedDatetime = createDateTime("processedDatetime", java.sql.Timestamp.class);

    public final StringPath scanServer = createString("scanServer");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QAutoEscriptFiller(String variable) {
        super(AutoEscriptFiller.class, forVariable(variable), "dbo", "auto_escript_filler");
        addMetadata();
    }

    public QAutoEscriptFiller(String variable, String schema, String table) {
        super(AutoEscriptFiller.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAutoEscriptFiller(String variable, String schema) {
        super(AutoEscriptFiller.class, forVariable(variable), schema, "auto_escript_filler");
        addMetadata();
    }

    public QAutoEscriptFiller(Path<? extends AutoEscriptFiller> path) {
        super(path.getType(), path.getMetadata(), "dbo", "auto_escript_filler");
        addMetadata();
    }

    public QAutoEscriptFiller(PathMetadata metadata) {
        super(AutoEscriptFiller.class, metadata, "dbo", "auto_escript_filler");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(autoEfillerDatetime, ColumnMetadata.named("auto_efiller_datetime").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(autoEfillerSeq, ColumnMetadata.named("auto_efiller_seq").withIndex(1).ofType(Types.NUMERIC).withSize(30).notNull());
        addMetadata(batchNumber, ColumnMetadata.named("batch_number").withIndex(11).ofType(Types.VARCHAR).withSize(60));
        addMetadata(createOrder, ColumnMetadata.named("create_order").withIndex(6).ofType(Types.CHAR).withSize(1));
        addMetadata(escriptId, ColumnMetadata.named("escript_id").withIndex(2).ofType(Types.NUMERIC).withSize(35));
        addMetadata(locationNum, ColumnMetadata.named("location_num").withIndex(3).ofType(Types.VARCHAR).withSize(60));
        addMetadata(logNote, ColumnMetadata.named("log_note").withIndex(12).ofType(Types.VARCHAR).withSize(5000));
        addMetadata(orderCreationDate, ColumnMetadata.named("order_creation_date").withIndex(14).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(orderInvoiceNumber, ColumnMetadata.named("order_invoice_number").withIndex(9).ofType(Types.VARCHAR).withSize(60));
        addMetadata(orderNum, ColumnMetadata.named("order_num").withIndex(8).ofType(Types.NUMERIC).withSize(30));
        addMetadata(patientNum, ColumnMetadata.named("patient_num").withIndex(7).ofType(Types.NUMERIC).withSize(30));
        addMetadata(processedDatetime, ColumnMetadata.named("processed_datetime").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(scanServer, ColumnMetadata.named("scan_server").withIndex(13).ofType(Types.VARCHAR).withSize(60));
        addMetadata(status, ColumnMetadata.named("status").withIndex(10).ofType(Types.INTEGER).withSize(10));
    }

}

