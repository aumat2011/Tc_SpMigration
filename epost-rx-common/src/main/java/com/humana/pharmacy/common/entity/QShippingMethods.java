package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.ShippingMethods;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QShippingMethods is a Querydsl query type for ShippingMethods
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QShippingMethods extends com.querydsl.sql.RelationalPathBase<ShippingMethods> {

    private static final long serialVersionUID = -1277293942;

    public static final QShippingMethods shippingMethods = new QShippingMethods("shipping_methods");

    public final StringPath active = createString("active");

    public final StringPath priority = createString("priority");

    public final StringPath shipMethodDesc = createString("shipMethodDesc");

    public final NumberPath<Integer> shipMethodId = createNumber("shipMethodId", Integer.class);

    public final StringPath shippingCod = createString("shippingCod");

    public final StringPath shippingCode = createString("shippingCode");

    public final NumberPath<Byte> shippingDays = createNumber("shippingDays", Byte.class);

    public final NumberPath<Long> shippingMethodPrice = createNumber("shippingMethodPrice", Long.class);

    public final StringPath signatureRequired = createString("signatureRequired");

    public QShippingMethods(String variable) {
        super(ShippingMethods.class, forVariable(variable), "dbo", "shipping_methods");
        addMetadata();
    }

    public QShippingMethods(String variable, String schema, String table) {
        super(ShippingMethods.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QShippingMethods(String variable, String schema) {
        super(ShippingMethods.class, forVariable(variable), schema, "shipping_methods");
        addMetadata();
    }

    public QShippingMethods(Path<? extends ShippingMethods> path) {
        super(path.getType(), path.getMetadata(), "dbo", "shipping_methods");
        addMetadata();
    }

    public QShippingMethods(PathMetadata metadata) {
        super(ShippingMethods.class, metadata, "dbo", "shipping_methods");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(active, ColumnMetadata.named("active").withIndex(8).ofType(Types.CHAR).withSize(1));
        addMetadata(priority, ColumnMetadata.named("priority").withIndex(9).ofType(Types.CHAR).withSize(1));
        addMetadata(shipMethodDesc, ColumnMetadata.named("ship_method_desc").withIndex(2).ofType(Types.VARCHAR).withSize(60).notNull());
        addMetadata(shipMethodId, ColumnMetadata.named("ship_method_id").withIndex(1).ofType(Types.NUMERIC).withSize(5).notNull());
        addMetadata(shippingCod, ColumnMetadata.named("shipping_cod").withIndex(4).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(shippingCode, ColumnMetadata.named("shipping_code").withIndex(6).ofType(Types.VARCHAR).withSize(60));
        addMetadata(shippingDays, ColumnMetadata.named("shipping_days").withIndex(5).ofType(Types.NUMERIC).withSize(2));
        addMetadata(shippingMethodPrice, ColumnMetadata.named("shipping_method_price").withIndex(3).ofType(Types.NUMERIC).withSize(12));
        addMetadata(signatureRequired, ColumnMetadata.named("signature_required").withIndex(7).ofType(Types.CHAR).withSize(1));
    }

}

