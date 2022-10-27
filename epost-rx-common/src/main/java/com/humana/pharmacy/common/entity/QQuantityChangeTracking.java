package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.QuantityChangeTracking;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QQuantityChangeTracking is a Querydsl query type for QuantityChangeTracking
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QQuantityChangeTracking extends com.querydsl.sql.RelationalPathBase<QuantityChangeTracking> {

    private static final long serialVersionUID = -2006121268;

    public static final QQuantityChangeTracking quantityChangeTracking = new QQuantityChangeTracking("quantity_change_tracking");

    public final DateTimePath<java.sql.Timestamp> createdOn = createDateTime("createdOn", java.sql.Timestamp.class);

    public final NumberPath<java.math.BigInteger> eScriptMsgAttributeSeq = createNumber("eScriptMsgAttributeSeq", java.math.BigInteger.class);

    public final StringPath isQuantityLowered = createString("isQuantityLowered");

    public final StringPath rxNumber = createString("rxNumber");

    public final DateTimePath<java.sql.Timestamp> updatedOn = createDateTime("updatedOn", java.sql.Timestamp.class);

    public QQuantityChangeTracking(String variable) {
        super(QuantityChangeTracking.class, forVariable(variable), "dbo", "quantity_change_tracking");
        addMetadata();
    }

    public QQuantityChangeTracking(String variable, String schema, String table) {
        super(QuantityChangeTracking.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QQuantityChangeTracking(String variable, String schema) {
        super(QuantityChangeTracking.class, forVariable(variable), schema, "quantity_change_tracking");
        addMetadata();
    }

    public QQuantityChangeTracking(Path<? extends QuantityChangeTracking> path) {
        super(path.getType(), path.getMetadata(), "dbo", "quantity_change_tracking");
        addMetadata();
    }

    public QQuantityChangeTracking(PathMetadata metadata) {
        super(QuantityChangeTracking.class, metadata, "dbo", "quantity_change_tracking");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdOn, ColumnMetadata.named("created_on").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(eScriptMsgAttributeSeq, ColumnMetadata.named("e_script_msg_attribute_seq").withIndex(1).ofType(Types.NUMERIC).withSize(35).notNull());
        addMetadata(isQuantityLowered, ColumnMetadata.named("is_quantity_lowered").withIndex(3).ofType(Types.CHAR).withSize(1));
        addMetadata(rxNumber, ColumnMetadata.named("rx_number").withIndex(2).ofType(Types.VARCHAR).withSize(60));
        addMetadata(updatedOn, ColumnMetadata.named("updated_on").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
    }

}

