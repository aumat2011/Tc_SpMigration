package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.SigGroups;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QSigGroups is a Querydsl query type for SigGroups
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QSigGroups extends com.querydsl.sql.RelationalPathBase<SigGroups> {

    private static final long serialVersionUID = 431278827;

    public static final QSigGroups sigGroups = new QSigGroups("sig_groups");

    public final StringPath sig = createString("sig");

    public final NumberPath<Integer> sigGroup = createNumber("sigGroup", Integer.class);

    public QSigGroups(String variable) {
        super(SigGroups.class, forVariable(variable), "dbo", "sig_groups");
        addMetadata();
    }

    public QSigGroups(String variable, String schema, String table) {
        super(SigGroups.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSigGroups(String variable, String schema) {
        super(SigGroups.class, forVariable(variable), schema, "sig_groups");
        addMetadata();
    }

    public QSigGroups(Path<? extends SigGroups> path) {
        super(path.getType(), path.getMetadata(), "dbo", "sig_groups");
        addMetadata();
    }

    public QSigGroups(PathMetadata metadata) {
        super(SigGroups.class, metadata, "dbo", "sig_groups");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(sig, ColumnMetadata.named("sig").withIndex(1).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(sigGroup, ColumnMetadata.named("sig_group").withIndex(2).ofType(Types.INTEGER).withSize(10).notNull());
    }

}

