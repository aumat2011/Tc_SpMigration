package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.RxLinkDccoGPIList;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QRxLinkDccoGPIList is a Querydsl query type for RxLinkDccoGPIList
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QRxLinkDccoGPIList extends com.querydsl.sql.RelationalPathBase<RxLinkDccoGPIList> {

    private static final long serialVersionUID = -354534119;

    public static final QRxLinkDccoGPIList rxLinkDccoGPIList = new QRxLinkDccoGPIList("rx_Link_Dcco_GPI_List");

    public final StringPath gpi = createString("gpi");

    public QRxLinkDccoGPIList(String variable) {
        super(RxLinkDccoGPIList.class, forVariable(variable), "dbo", "rx_Link_Dcco_GPI_List");
        addMetadata();
    }

    public QRxLinkDccoGPIList(String variable, String schema, String table) {
        super(RxLinkDccoGPIList.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QRxLinkDccoGPIList(String variable, String schema) {
        super(RxLinkDccoGPIList.class, forVariable(variable), schema, "rx_Link_Dcco_GPI_List");
        addMetadata();
    }

    public QRxLinkDccoGPIList(Path<? extends RxLinkDccoGPIList> path) {
        super(path.getType(), path.getMetadata(), "dbo", "rx_Link_Dcco_GPI_List");
        addMetadata();
    }

    public QRxLinkDccoGPIList(PathMetadata metadata) {
        super(RxLinkDccoGPIList.class, metadata, "dbo", "rx_Link_Dcco_GPI_List");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(gpi, ColumnMetadata.named("GPI").withIndex(1).ofType(Types.VARCHAR).withSize(60));
    }

}

