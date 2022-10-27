package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.StateCsProducts;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QStateCsProducts is a Querydsl query type for StateCsProducts
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QStateCsProducts extends com.querydsl.sql.RelationalPathBase<StateCsProducts> {

    private static final long serialVersionUID = -1848781365;

    public static final QStateCsProducts stateCsProducts = new QStateCsProducts("state_cs_products");

    public final StringPath poboxActive = createString("poboxActive");

    public final NumberPath<Short> prodDea = createNumber("prodDea", Short.class);

    public final StringPath prodFilter = createString("prodFilter");

    public final NumberPath<Long> prodId = createNumber("prodId", Long.class);

    public final NumberPath<Long> stateCsProductSeq = createNumber("stateCsProductSeq", Long.class);

    public final NumberPath<Integer> stateNum = createNumber("stateNum", Integer.class);

    public QStateCsProducts(String variable) {
        super(StateCsProducts.class, forVariable(variable), "dbo", "state_cs_products");
        addMetadata();
    }

    public QStateCsProducts(String variable, String schema, String table) {
        super(StateCsProducts.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QStateCsProducts(String variable, String schema) {
        super(StateCsProducts.class, forVariable(variable), schema, "state_cs_products");
        addMetadata();
    }

    public QStateCsProducts(Path<? extends StateCsProducts> path) {
        super(path.getType(), path.getMetadata(), "dbo", "state_cs_products");
        addMetadata();
    }

    public QStateCsProducts(PathMetadata metadata) {
        super(StateCsProducts.class, metadata, "dbo", "state_cs_products");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(poboxActive, ColumnMetadata.named("pobox_active").withIndex(6).ofType(Types.VARCHAR).withSize(1));
        addMetadata(prodDea, ColumnMetadata.named("prod_dea").withIndex(4).ofType(Types.NUMERIC).withSize(3));
        addMetadata(prodFilter, ColumnMetadata.named("prod_filter").withIndex(5).ofType(Types.VARCHAR).withSize(10));
        addMetadata(prodId, ColumnMetadata.named("prod_id").withIndex(3).ofType(Types.NUMERIC).withSize(16));
        addMetadata(stateCsProductSeq, ColumnMetadata.named("state_cs_product_seq").withIndex(1).ofType(Types.NUMERIC).withSize(16).notNull());
        addMetadata(stateNum, ColumnMetadata.named("state_num").withIndex(2).ofType(Types.NUMERIC).withSize(5));
    }

}

