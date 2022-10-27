package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.OKHydrocodoneSanityProducts;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QOKHydrocodoneSanityProducts is a Querydsl query type for OKHydrocodoneSanityProducts
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QOKHydrocodoneSanityProducts extends com.querydsl.sql.RelationalPathBase<OKHydrocodoneSanityProducts> {

    private static final long serialVersionUID = -711837222;

    public static final QOKHydrocodoneSanityProducts OKHydrocodoneSanityProducts = new QOKHydrocodoneSanityProducts("OK_hydrocodone_sanity_products");

    public final StringPath category = createString("category");

    public final StringPath displayName = createString("displayName");

    public final StringPath federalClass = createString("federalClass");

    public final StringPath genericName = createString("genericName");

    public final StringPath gpi = createString("gpi");

    public final StringPath ndc = createString("ndc");

    public QOKHydrocodoneSanityProducts(String variable) {
        super(OKHydrocodoneSanityProducts.class, forVariable(variable), "dbo", "OK_hydrocodone_sanity_products");
        addMetadata();
    }

    public QOKHydrocodoneSanityProducts(String variable, String schema, String table) {
        super(OKHydrocodoneSanityProducts.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QOKHydrocodoneSanityProducts(String variable, String schema) {
        super(OKHydrocodoneSanityProducts.class, forVariable(variable), schema, "OK_hydrocodone_sanity_products");
        addMetadata();
    }

    public QOKHydrocodoneSanityProducts(Path<? extends OKHydrocodoneSanityProducts> path) {
        super(path.getType(), path.getMetadata(), "dbo", "OK_hydrocodone_sanity_products");
        addMetadata();
    }

    public QOKHydrocodoneSanityProducts(PathMetadata metadata) {
        super(OKHydrocodoneSanityProducts.class, metadata, "dbo", "OK_hydrocodone_sanity_products");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(category, ColumnMetadata.named("Category").withIndex(1).ofType(Types.NVARCHAR).withSize(255));
        addMetadata(displayName, ColumnMetadata.named("Display Name").withIndex(5).ofType(Types.NVARCHAR).withSize(255));
        addMetadata(federalClass, ColumnMetadata.named("Federal Class").withIndex(6).ofType(Types.NVARCHAR).withSize(255));
        addMetadata(genericName, ColumnMetadata.named("Generic Name").withIndex(4).ofType(Types.NVARCHAR).withSize(255));
        addMetadata(gpi, ColumnMetadata.named("GPI").withIndex(2).ofType(Types.NVARCHAR).withSize(255));
        addMetadata(ndc, ColumnMetadata.named("NDC").withIndex(3).ofType(Types.NVARCHAR).withSize(255));
    }

}

