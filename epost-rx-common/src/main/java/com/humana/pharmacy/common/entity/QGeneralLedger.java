package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.GeneralLedger;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QGeneralLedger is a Querydsl query type for GeneralLedger
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QGeneralLedger extends com.querydsl.sql.RelationalPathBase<GeneralLedger> {

    private static final long serialVersionUID = -390899113;

    public static final QGeneralLedger generalLedger = new QGeneralLedger("general_ledger");

    public final NumberPath<java.math.BigInteger> glPostNum = createNumber("glPostNum", java.math.BigInteger.class);

    public QGeneralLedger(String variable) {
        super(GeneralLedger.class, forVariable(variable), "dbo", "general_ledger");
        addMetadata();
    }

    public QGeneralLedger(String variable, String schema, String table) {
        super(GeneralLedger.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QGeneralLedger(String variable, String schema) {
        super(GeneralLedger.class, forVariable(variable), schema, "general_ledger");
        addMetadata();
    }

    public QGeneralLedger(Path<? extends GeneralLedger> path) {
        super(path.getType(), path.getMetadata(), "dbo", "general_ledger");
        addMetadata();
    }

    public QGeneralLedger(PathMetadata metadata) {
        super(GeneralLedger.class, metadata, "dbo", "general_ledger");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(glPostNum, ColumnMetadata.named("gl_post_num").withIndex(1).ofType(Types.NUMERIC).withSize(30).notNull());
    }

}

