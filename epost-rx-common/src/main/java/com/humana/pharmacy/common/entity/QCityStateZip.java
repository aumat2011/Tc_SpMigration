package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.CityStateZip;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QCityStateZip is a Querydsl query type for CityStateZip
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QCityStateZip extends com.querydsl.sql.RelationalPathBase<CityStateZip> {

    private static final long serialVersionUID = -1437266763;

    public static final QCityStateZip cityStateZip = new QCityStateZip("city_state_zip");

    public final StringPath active = createString("active");

    public final StringPath cszCityName = createString("cszCityName");

    public final StringPath cszZipCode = createString("cszZipCode");

    public final NumberPath<Long> cszZipNum = createNumber("cszZipNum", Long.class);

    public final NumberPath<Integer> ctryNum = createNumber("ctryNum", Integer.class);

    public final NumberPath<Integer> regionNum = createNumber("regionNum", Integer.class);

    public final NumberPath<Integer> stateNum = createNumber("stateNum", Integer.class);

    public QCityStateZip(String variable) {
        super(CityStateZip.class, forVariable(variable), "dbo", "city_state_zip");
        addMetadata();
    }

    public QCityStateZip(String variable, String schema, String table) {
        super(CityStateZip.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QCityStateZip(String variable, String schema) {
        super(CityStateZip.class, forVariable(variable), schema, "city_state_zip");
        addMetadata();
    }

    public QCityStateZip(Path<? extends CityStateZip> path) {
        super(path.getType(), path.getMetadata(), "dbo", "city_state_zip");
        addMetadata();
    }

    public QCityStateZip(PathMetadata metadata) {
        super(CityStateZip.class, metadata, "dbo", "city_state_zip");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(active, ColumnMetadata.named("active").withIndex(7).ofType(Types.CHAR).withSize(1));
        addMetadata(cszCityName, ColumnMetadata.named("csz_city_name").withIndex(2).ofType(Types.VARCHAR).withSize(35).notNull());
        addMetadata(cszZipCode, ColumnMetadata.named("csz_zip_code").withIndex(6).ofType(Types.VARCHAR).withSize(10).notNull());
        addMetadata(cszZipNum, ColumnMetadata.named("csz_zip_num").withIndex(1).ofType(Types.NUMERIC).withSize(16).notNull());
        addMetadata(ctryNum, ColumnMetadata.named("ctry_num").withIndex(5).ofType(Types.NUMERIC).withSize(5).notNull());
        addMetadata(regionNum, ColumnMetadata.named("region_num").withIndex(3).ofType(Types.NUMERIC).withSize(5));
        addMetadata(stateNum, ColumnMetadata.named("state_num").withIndex(4).ofType(Types.NUMERIC).withSize(5).notNull());
    }

}

