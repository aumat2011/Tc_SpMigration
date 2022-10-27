package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.CodeValue;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QCodeValue is a Querydsl query type for CodeValue
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QCodeValue extends com.querydsl.sql.RelationalPathBase<CodeValue> {

    private static final long serialVersionUID = -677214102;

    public static final QCodeValue codeValue = new QCodeValue("code_value");

    public final StringPath applicationName = createString("applicationName");

    public final StringPath codeValueDesc = createString("codeValueDesc");

    public final NumberPath<Integer> codeValueId = createNumber("codeValueId", Integer.class);

    public final StringPath codeValueKeyword = createString("codeValueKeyword");

    public final DateTimePath<java.sql.Timestamp> createTs = createDateTime("createTs", java.sql.Timestamp.class);

    public final StringPath fkCodeCatId = createString("fkCodeCatId");

    public final StringPath lastUpdtBy = createString("lastUpdtBy");

    public final DateTimePath<java.sql.Timestamp> lastUpdtTs = createDateTime("lastUpdtTs", java.sql.Timestamp.class);

    public final StringPath restartRequired = createString("restartRequired");

    public final StringPath statusFlag = createString("statusFlag");

    public QCodeValue(String variable) {
        super(CodeValue.class, forVariable(variable), "dbo", "code_value");
        addMetadata();
    }

    public QCodeValue(String variable, String schema, String table) {
        super(CodeValue.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QCodeValue(String variable, String schema) {
        super(CodeValue.class, forVariable(variable), schema, "code_value");
        addMetadata();
    }

    public QCodeValue(Path<? extends CodeValue> path) {
        super(path.getType(), path.getMetadata(), "dbo", "code_value");
        addMetadata();
    }

    public QCodeValue(PathMetadata metadata) {
        super(CodeValue.class, metadata, "dbo", "code_value");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(applicationName, ColumnMetadata.named("application_name").withIndex(10).ofType(Types.VARCHAR).withSize(40).notNull());
        addMetadata(codeValueDesc, ColumnMetadata.named("code_value_desc").withIndex(4).ofType(Types.VARCHAR).withSize(200).notNull());
        addMetadata(codeValueId, ColumnMetadata.named("code_value_id").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(codeValueKeyword, ColumnMetadata.named("code_value_keyword").withIndex(3).ofType(Types.VARCHAR).withSize(200).notNull());
        addMetadata(createTs, ColumnMetadata.named("create_ts").withIndex(6).ofType(Types.TIMESTAMP).withSize(27).withDigits(7).notNull());
        addMetadata(fkCodeCatId, ColumnMetadata.named("fk_code_cat_id").withIndex(2).ofType(Types.CHAR).withSize(8).notNull());
        addMetadata(lastUpdtBy, ColumnMetadata.named("last_updt_by").withIndex(8).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(lastUpdtTs, ColumnMetadata.named("last_updt_ts").withIndex(7).ofType(Types.TIMESTAMP).withSize(27).withDigits(7).notNull());
        addMetadata(restartRequired, ColumnMetadata.named("restart_required").withIndex(9).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(statusFlag, ColumnMetadata.named("status_flag").withIndex(5).ofType(Types.CHAR).withSize(1).notNull());
    }

}

