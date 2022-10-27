package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.Payors;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QPayors is a Querydsl query type for Payors
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QPayors extends com.querydsl.sql.RelationalPathBase<Payors> {

    private static final long serialVersionUID = -1563652574;

    public static final QPayors payors = new QPayors("payors");

    public final StringPath autoRefill = createString("autoRefill");

    public final StringPath autoRefilltoosoon = createString("autoRefilltoosoon");

    public final StringPath consolidateRxs = createString("consolidateRxs");

    public final StringPath conversionLink = createString("conversionLink");

    public final StringPath daw12 = createString("daw12");

    public final StringPath dmeCollection = createString("dmeCollection");

    public final StringPath generalStatusCode = createString("generalStatusCode");

    public final StringPath hrm = createString("hrm");

    public final StringPath myb = createString("myb");

    public final StringPath payorAcceptAssignment = createString("payorAcceptAssignment");

    public final NumberPath<Integer> payorBillTypeNum = createNumber("payorBillTypeNum", Integer.class);

    public final StringPath payorCode = createString("payorCode");

    public final DateTimePath<java.sql.Timestamp> payorDateAdded = createDateTime("payorDateAdded", java.sql.Timestamp.class);

    public final StringPath payorGroup = createString("payorGroup");

    public final NumberPath<Integer> payorMaxBillingDays = createNumber("payorMaxBillingDays", Integer.class);

    public final NumberPath<Integer> payorMinUtilPct = createNumber("payorMinUtilPct", Integer.class);

    public final StringPath payorName = createString("payorName");

    public final NumberPath<Long> payorNum = createNumber("payorNum", Long.class);

    public final StringPath payorProviderNum = createString("payorProviderNum");

    public final NumberPath<Integer> payorTypeNum = createNumber("payorTypeNum", Integer.class);

    public final StringPath printCost = createString("printCost");

    public final StringPath printMarketing = createString("printMarketing");

    public final StringPath restrictedView = createString("restrictedView");

    public final StringPath rxConsent = createString("rxConsent");

    public final DateTimePath<java.sql.Timestamp> terminationDate = createDateTime("terminationDate", java.sql.Timestamp.class);

    public final NumberPath<Integer> turnaroundDays = createNumber("turnaroundDays", Integer.class);

    public final StringPath webUrl = createString("webUrl");

    public QPayors(String variable) {
        super(Payors.class, forVariable(variable), "dbo", "payors");
        addMetadata();
    }

    public QPayors(String variable, String schema, String table) {
        super(Payors.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QPayors(String variable, String schema) {
        super(Payors.class, forVariable(variable), schema, "payors");
        addMetadata();
    }

    public QPayors(Path<? extends Payors> path) {
        super(path.getType(), path.getMetadata(), "dbo", "payors");
        addMetadata();
    }

    public QPayors(PathMetadata metadata) {
        super(Payors.class, metadata, "dbo", "payors");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(autoRefill, ColumnMetadata.named("auto_refill").withIndex(19).ofType(Types.CHAR).withSize(1));
        addMetadata(autoRefilltoosoon, ColumnMetadata.named("auto_refilltoosoon").withIndex(26).ofType(Types.CHAR).withSize(1));
        addMetadata(consolidateRxs, ColumnMetadata.named("consolidate_rxs").withIndex(22).ofType(Types.CHAR).withSize(1));
        addMetadata(conversionLink, ColumnMetadata.named("conversion_link").withIndex(11).ofType(Types.VARCHAR).withSize(60));
        addMetadata(daw12, ColumnMetadata.named("daw12").withIndex(15).ofType(Types.CHAR).withSize(1));
        addMetadata(dmeCollection, ColumnMetadata.named("dme_collection").withIndex(24).ofType(Types.CHAR).withSize(1));
        addMetadata(generalStatusCode, ColumnMetadata.named("general_status_code").withIndex(3).ofType(Types.CHAR).withSize(1));
        addMetadata(hrm, ColumnMetadata.named("hrm").withIndex(21).ofType(Types.CHAR).withSize(1));
        addMetadata(myb, ColumnMetadata.named("myb").withIndex(23).ofType(Types.CHAR).withSize(1));
        addMetadata(payorAcceptAssignment, ColumnMetadata.named("payor_accept_assignment").withIndex(9).ofType(Types.CHAR).withSize(1));
        addMetadata(payorBillTypeNum, ColumnMetadata.named("payor_bill_type_num").withIndex(5).ofType(Types.NUMERIC).withSize(5));
        addMetadata(payorCode, ColumnMetadata.named("payor_code").withIndex(1).ofType(Types.VARCHAR).withSize(10).notNull());
        addMetadata(payorDateAdded, ColumnMetadata.named("payor_date_added").withIndex(7).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(payorGroup, ColumnMetadata.named("payor_group").withIndex(12).ofType(Types.VARCHAR).withSize(60));
        addMetadata(payorMaxBillingDays, ColumnMetadata.named("payor_max_billing_days").withIndex(10).ofType(Types.NUMERIC).withSize(5));
        addMetadata(payorMinUtilPct, ColumnMetadata.named("payor_min_util_pct").withIndex(17).ofType(Types.NUMERIC).withSize(5));
        addMetadata(payorName, ColumnMetadata.named("payor_name").withIndex(6).ofType(Types.VARCHAR).withSize(60).notNull());
        addMetadata(payorNum, ColumnMetadata.named("payor_num").withIndex(2).ofType(Types.NUMERIC).withSize(16).notNull());
        addMetadata(payorProviderNum, ColumnMetadata.named("payor_provider_num").withIndex(8).ofType(Types.VARCHAR).withSize(15));
        addMetadata(payorTypeNum, ColumnMetadata.named("payor_type_num").withIndex(4).ofType(Types.NUMERIC).withSize(5));
        addMetadata(printCost, ColumnMetadata.named("print_cost").withIndex(14).ofType(Types.CHAR).withSize(1));
        addMetadata(printMarketing, ColumnMetadata.named("print_marketing").withIndex(20).ofType(Types.CHAR).withSize(1));
        addMetadata(restrictedView, ColumnMetadata.named("restricted_view").withIndex(25).ofType(Types.CHAR).withSize(1));
        addMetadata(rxConsent, ColumnMetadata.named("rx_consent").withIndex(27).ofType(Types.CHAR).withSize(1));
        addMetadata(terminationDate, ColumnMetadata.named("termination_date").withIndex(16).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(turnaroundDays, ColumnMetadata.named("turnaround_days").withIndex(13).ofType(Types.NUMERIC).withSize(7));
        addMetadata(webUrl, ColumnMetadata.named("web_url").withIndex(18).ofType(Types.VARCHAR).withSize(255));
    }

}

