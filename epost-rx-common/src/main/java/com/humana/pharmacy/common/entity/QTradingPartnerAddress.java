package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.TradingPartnerAddress;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QTradingPartnerAddress is a Querydsl query type for TradingPartnerAddress
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QTradingPartnerAddress extends com.querydsl.sql.RelationalPathBase<TradingPartnerAddress> {

    private static final long serialVersionUID = 1054189939;

    public static final QTradingPartnerAddress tradingPartnerAddress = new QTradingPartnerAddress("trading_partner_address");

    public final StringPath active = createString("active");

    public final StringPath addressDesc = createString("addressDesc");

    public final NumberPath<Integer> addressTypeNum = createNumber("addressTypeNum", Integer.class);

    public final NumberPath<Long> cszZipNum = createNumber("cszZipNum", Long.class);

    public final StringPath externalLink = createString("externalLink");

    public final StringPath externalLinkPhone = createString("externalLinkPhone");

    public final StringPath spi = createString("spi");

    public final StringPath spilog = createString("spilog");

    public final StringPath tpAddress = createString("tpAddress");

    public final StringPath tpAddress2 = createString("tpAddress2");

    public final StringPath tpAddress3 = createString("tpAddress3");

    public final NumberPath<Long> tpAddrSeq = createNumber("tpAddrSeq", Long.class);

    public final NumberPath<Long> tpFaxSeq = createNumber("tpFaxSeq", Long.class);

    public final NumberPath<Long> tpPhoneSeq = createNumber("tpPhoneSeq", Long.class);

    public final NumberPath<Short> tpZipFour = createNumber("tpZipFour", Short.class);

    public final NumberPath<Long> tradingPartnerNum = createNumber("tradingPartnerNum", Long.class);

    public QTradingPartnerAddress(String variable) {
        super(TradingPartnerAddress.class, forVariable(variable), "dbo", "trading_partner_address");
        addMetadata();
    }

    public QTradingPartnerAddress(String variable, String schema, String table) {
        super(TradingPartnerAddress.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTradingPartnerAddress(String variable, String schema) {
        super(TradingPartnerAddress.class, forVariable(variable), schema, "trading_partner_address");
        addMetadata();
    }

    public QTradingPartnerAddress(Path<? extends TradingPartnerAddress> path) {
        super(path.getType(), path.getMetadata(), "dbo", "trading_partner_address");
        addMetadata();
    }

    public QTradingPartnerAddress(PathMetadata metadata) {
        super(TradingPartnerAddress.class, metadata, "dbo", "trading_partner_address");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(active, ColumnMetadata.named("active").withIndex(12).ofType(Types.CHAR).withSize(1));
        addMetadata(addressDesc, ColumnMetadata.named("address_desc").withIndex(9).ofType(Types.VARCHAR).withSize(250));
        addMetadata(addressTypeNum, ColumnMetadata.named("address_type_num").withIndex(2).ofType(Types.NUMERIC).withSize(5));
        addMetadata(cszZipNum, ColumnMetadata.named("csz_zip_num").withIndex(4).ofType(Types.NUMERIC).withSize(16));
        addMetadata(externalLink, ColumnMetadata.named("external_link").withIndex(15).ofType(Types.CHAR).withSize(60));
        addMetadata(externalLinkPhone, ColumnMetadata.named("external_link_phone").withIndex(16).ofType(Types.CHAR).withSize(60));
        addMetadata(spi, ColumnMetadata.named("spi").withIndex(13).ofType(Types.VARCHAR).withSize(60));
        addMetadata(spilog, ColumnMetadata.named("spilog").withIndex(14).ofType(Types.VARCHAR).withSize(100));
        addMetadata(tpAddress, ColumnMetadata.named("tp_address").withIndex(5).ofType(Types.VARCHAR).withSize(60).notNull());
        addMetadata(tpAddress2, ColumnMetadata.named("tp_address2").withIndex(8).ofType(Types.VARCHAR).withSize(60));
        addMetadata(tpAddress3, ColumnMetadata.named("tp_address3").withIndex(7).ofType(Types.VARCHAR).withSize(40));
        addMetadata(tpAddrSeq, ColumnMetadata.named("tp_addr_seq").withIndex(3).ofType(Types.NUMERIC).withSize(16).notNull());
        addMetadata(tpFaxSeq, ColumnMetadata.named("tp_fax_seq").withIndex(11).ofType(Types.NUMERIC).withSize(16));
        addMetadata(tpPhoneSeq, ColumnMetadata.named("tp_phone_seq").withIndex(10).ofType(Types.NUMERIC).withSize(16));
        addMetadata(tpZipFour, ColumnMetadata.named("tp_zip_four").withIndex(6).ofType(Types.NUMERIC).withSize(4));
        addMetadata(tradingPartnerNum, ColumnMetadata.named("trading_partner_num").withIndex(1).ofType(Types.NUMERIC).withSize(16).notNull());
    }

}

