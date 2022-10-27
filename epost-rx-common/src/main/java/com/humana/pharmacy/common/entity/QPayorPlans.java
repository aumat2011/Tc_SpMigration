package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.PayorPlans;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QPayorPlans is a Querydsl query type for PayorPlans
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QPayorPlans extends com.querydsl.sql.RelationalPathBase<PayorPlans> {

    private static final long serialVersionUID = 771360889;

    public static final QPayorPlans payorPlans = new QPayorPlans("payor_plans");

    public final StringPath autoRefill = createString("autoRefill");

    public final StringPath autoRefilltoosoon = createString("autoRefilltoosoon");

    public final StringPath benefitPeriodType = createString("benefitPeriodType");

    public final NumberPath<Long> brandLoss = createNumber("brandLoss", Long.class);

    public final StringPath consolidateRxs = createString("consolidateRxs");

    public final StringPath conversionLink = createString("conversionLink");

    public final NumberPath<Long> creditLimit = createNumber("creditLimit", Long.class);

    public final StringPath daw12 = createString("daw12");

    public final StringPath dmeCollection = createString("dmeCollection");

    public final StringPath doctorIdTypeCode = createString("doctorIdTypeCode");

    public final NumberPath<Integer> formGroupSeq = createNumber("formGroupSeq", Integer.class);

    public final StringPath generalStatusCode = createString("generalStatusCode");

    public final NumberPath<Long> genericLoss = createNumber("genericLoss", Long.class);

    public final StringPath hrm = createString("hrm");

    public final StringPath lostMargin = createString("lostMargin");

    public final StringPath minMargin = createString("minMargin");

    public final StringPath myb = createString("myb");

    public final NumberPath<Long> payorNum = createNumber("payorNum", Long.class);

    public final StringPath planAlias = createString("planAlias");

    public final DateTimePath<java.sql.Timestamp> ppActivationDate = createDateTime("ppActivationDate", java.sql.Timestamp.class);

    public final StringPath ppGroupId = createString("ppGroupId");

    public final StringPath ppGroupName = createString("ppGroupName");

    public final NumberPath<Short> ppMaxDaysSupply = createNumber("ppMaxDaysSupply", Short.class);

    public final NumberPath<Long> ppMaxQuantity = createNumber("ppMaxQuantity", Long.class);

    public final NumberPath<Short> ppMaxRefillAllowed = createNumber("ppMaxRefillAllowed", Short.class);

    public final NumberPath<Integer> ppMaxUtilPct = createNumber("ppMaxUtilPct", Integer.class);

    public final NumberPath<Short> ppMinDaysSupply = createNumber("ppMinDaysSupply", Short.class);

    public final NumberPath<Long> ppMinimumCashPrice = createNumber("ppMinimumCashPrice", Long.class);

    public final NumberPath<Long> ppMinQuantity = createNumber("ppMinQuantity", Long.class);

    public final NumberPath<Integer> ppMinUtilPct = createNumber("ppMinUtilPct", Integer.class);

    public final NumberPath<Long> ppNum = createNumber("ppNum", Long.class);

    public final StringPath ppPhoneNumber = createString("ppPhoneNumber");

    public final StringPath ppPlanId = createString("ppPlanId");

    public final StringPath ppPlanName = createString("ppPlanName");

    public final StringPath ppTypeCode = createString("ppTypeCode");

    public final StringPath ppUnion = createString("ppUnion");

    public final StringPath printMarketing = createString("printMarketing");

    public final StringPath restrictedView = createString("restrictedView");

    public final StringPath rxConsent = createString("rxConsent");

    public final StringPath sendUcAsTpprice = createString("sendUcAsTpprice");

    public final NumberPath<Integer> shipMethodId = createNumber("shipMethodId", Integer.class);

    public final StringPath skipPricing = createString("skipPricing");

    public final DateTimePath<java.sql.Timestamp> terminationDate = createDateTime("terminationDate", java.sql.Timestamp.class);

    public final NumberPath<Long> tradingPartnerNum = createNumber("tradingPartnerNum", Long.class);

    public final StringPath uAndCPricing = createString("uAndCPricing");

    public final StringPath useSalePrice = createString("useSalePrice");

    public final StringPath vip = createString("vip");

    public QPayorPlans(String variable) {
        super(PayorPlans.class, forVariable(variable), "dbo", "payor_plans");
        addMetadata();
    }

    public QPayorPlans(String variable, String schema, String table) {
        super(PayorPlans.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QPayorPlans(String variable, String schema) {
        super(PayorPlans.class, forVariable(variable), schema, "payor_plans");
        addMetadata();
    }

    public QPayorPlans(Path<? extends PayorPlans> path) {
        super(path.getType(), path.getMetadata(), "dbo", "payor_plans");
        addMetadata();
    }

    public QPayorPlans(PathMetadata metadata) {
        super(PayorPlans.class, metadata, "dbo", "payor_plans");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(autoRefill, ColumnMetadata.named("auto_refill").withIndex(38).ofType(Types.CHAR).withSize(1));
        addMetadata(autoRefilltoosoon, ColumnMetadata.named("auto_refilltoosoon").withIndex(45).ofType(Types.CHAR).withSize(1));
        addMetadata(benefitPeriodType, ColumnMetadata.named("BenefitPeriodType").withIndex(47).ofType(Types.CHAR).withSize(1));
        addMetadata(brandLoss, ColumnMetadata.named("brand_loss").withIndex(36).ofType(Types.NUMERIC).withSize(15));
        addMetadata(consolidateRxs, ColumnMetadata.named("consolidate_rxs").withIndex(41).ofType(Types.CHAR).withSize(1));
        addMetadata(conversionLink, ColumnMetadata.named("conversion_link").withIndex(17).ofType(Types.VARCHAR).withSize(60));
        addMetadata(creditLimit, ColumnMetadata.named("credit_limit").withIndex(24).ofType(Types.NUMERIC).withSize(12));
        addMetadata(daw12, ColumnMetadata.named("daw12").withIndex(31).ofType(Types.CHAR).withSize(1));
        addMetadata(dmeCollection, ColumnMetadata.named("dme_collection").withIndex(43).ofType(Types.CHAR).withSize(1));
        addMetadata(doctorIdTypeCode, ColumnMetadata.named("doctor_id_type_code").withIndex(19).ofType(Types.VARCHAR).withSize(3));
        addMetadata(formGroupSeq, ColumnMetadata.named("form_group_seq").withIndex(21).ofType(Types.NUMERIC).withSize(7));
        addMetadata(generalStatusCode, ColumnMetadata.named("general_status_code").withIndex(2).ofType(Types.CHAR).withSize(1));
        addMetadata(genericLoss, ColumnMetadata.named("generic_loss").withIndex(35).ofType(Types.NUMERIC).withSize(15));
        addMetadata(hrm, ColumnMetadata.named("hrm").withIndex(40).ofType(Types.CHAR).withSize(1));
        addMetadata(lostMargin, ColumnMetadata.named("lost_margin").withIndex(34).ofType(Types.CHAR).withSize(1));
        addMetadata(minMargin, ColumnMetadata.named("min_margin").withIndex(37).ofType(Types.CHAR).withSize(1));
        addMetadata(myb, ColumnMetadata.named("myb").withIndex(42).ofType(Types.CHAR).withSize(1));
        addMetadata(payorNum, ColumnMetadata.named("payor_num").withIndex(7).ofType(Types.NUMERIC).withSize(16));
        addMetadata(planAlias, ColumnMetadata.named("plan_alias").withIndex(27).ofType(Types.VARCHAR).withSize(60));
        addMetadata(ppActivationDate, ColumnMetadata.named("pp_activation_date").withIndex(8).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(ppGroupId, ColumnMetadata.named("pp_group_id").withIndex(15).ofType(Types.VARCHAR).withSize(15));
        addMetadata(ppGroupName, ColumnMetadata.named("pp_group_name").withIndex(6).ofType(Types.VARCHAR).withSize(100));
        addMetadata(ppMaxDaysSupply, ColumnMetadata.named("pp_max_days_supply").withIndex(11).ofType(Types.NUMERIC).withSize(4));
        addMetadata(ppMaxQuantity, ColumnMetadata.named("pp_max_quantity").withIndex(26).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ppMaxRefillAllowed, ColumnMetadata.named("pp_max_refill_allowed").withIndex(10).ofType(Types.NUMERIC).withSize(3));
        addMetadata(ppMaxUtilPct, ColumnMetadata.named("pp_max_util_pct").withIndex(9).ofType(Types.NUMERIC).withSize(5));
        addMetadata(ppMinDaysSupply, ColumnMetadata.named("pp_min_days_supply").withIndex(28).ofType(Types.NUMERIC).withSize(4));
        addMetadata(ppMinimumCashPrice, ColumnMetadata.named("pp_minimum_cash_price").withIndex(16).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ppMinQuantity, ColumnMetadata.named("pp_min_quantity").withIndex(29).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ppMinUtilPct, ColumnMetadata.named("pp_min_util_pct").withIndex(33).ofType(Types.NUMERIC).withSize(5));
        addMetadata(ppNum, ColumnMetadata.named("pp_num").withIndex(1).ofType(Types.NUMERIC).withSize(16).notNull());
        addMetadata(ppPhoneNumber, ColumnMetadata.named("pp_phone_number").withIndex(30).ofType(Types.VARCHAR).withSize(35));
        addMetadata(ppPlanId, ColumnMetadata.named("pp_plan_id").withIndex(14).ofType(Types.VARCHAR).withSize(8));
        addMetadata(ppPlanName, ColumnMetadata.named("pp_plan_name").withIndex(4).ofType(Types.VARCHAR).withSize(45).notNull());
        addMetadata(ppTypeCode, ColumnMetadata.named("pp_type_code").withIndex(13).ofType(Types.CHAR).withSize(1));
        addMetadata(ppUnion, ColumnMetadata.named("pp_union").withIndex(12).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(printMarketing, ColumnMetadata.named("print_marketing").withIndex(39).ofType(Types.CHAR).withSize(1));
        addMetadata(restrictedView, ColumnMetadata.named("restricted_view").withIndex(44).ofType(Types.CHAR).withSize(1));
        addMetadata(rxConsent, ColumnMetadata.named("rx_consent").withIndex(46).ofType(Types.CHAR).withSize(1));
        addMetadata(sendUcAsTpprice, ColumnMetadata.named("send_uc_as_tpprice").withIndex(32).ofType(Types.CHAR).withSize(1));
        addMetadata(shipMethodId, ColumnMetadata.named("ship_method_id").withIndex(5).ofType(Types.NUMERIC).withSize(5));
        addMetadata(skipPricing, ColumnMetadata.named("skip_pricing").withIndex(22).ofType(Types.CHAR).withSize(1));
        addMetadata(terminationDate, ColumnMetadata.named("termination_date").withIndex(23).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(tradingPartnerNum, ColumnMetadata.named("trading_partner_num").withIndex(3).ofType(Types.NUMERIC).withSize(16));
        addMetadata(uAndCPricing, ColumnMetadata.named("u_and_c_pricing").withIndex(18).ofType(Types.CHAR).withSize(1));
        addMetadata(useSalePrice, ColumnMetadata.named("use_sale_price").withIndex(20).ofType(Types.CHAR).withSize(1));
        addMetadata(vip, ColumnMetadata.named("vip").withIndex(25).ofType(Types.CHAR).withSize(1));
    }

}

