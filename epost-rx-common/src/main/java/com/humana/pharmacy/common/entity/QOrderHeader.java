package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.OrderHeader;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QOrderHeader is a Querydsl query type for OrderHeader
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QOrderHeader extends com.querydsl.sql.RelationalPathBase<OrderHeader> {

    private static final long serialVersionUID = -288331039;

    public static final QOrderHeader orderHeader = new QOrderHeader("order_header");

    public final StringPath arValidated = createString("arValidated");

    public final StringPath autoCancel = createString("autoCancel");

    public final NumberPath<Long> autoDispenseLocation = createNumber("autoDispenseLocation", Long.class);

    public final NumberPath<Long> autoError = createNumber("autoError", Long.class);

    public final StringPath autoRoute = createString("autoRoute");

    public final StringPath autoRtsSplit = createString("autoRtsSplit");

    public final StringPath autoShipComplete = createString("autoShipComplete");

    public final StringPath autoSplit = createString("autoSplit");

    public final NumberPath<Integer> autoWorkflowTypeNum = createNumber("autoWorkflowTypeNum", Integer.class);

    public final StringPath basketNumber = createString("basketNumber");

    public final NumberPath<Byte> batchecs = createNumber("batchecs", Byte.class);

    public final DateTimePath<java.sql.Timestamp> cancelDatetime = createDateTime("cancelDatetime", java.sql.Timestamp.class);

    public final NumberPath<Long> cancelUsernum = createNumber("cancelUsernum", Long.class);

    public final NumberPath<Byte> ccBusy = createNumber("ccBusy", Byte.class);

    public final StringPath conversionLink = createString("conversionLink");

    public final NumberPath<Long> currentBalance = createNumber("currentBalance", Long.class);

    public final StringPath cycleFillRunNumber = createString("cycleFillRunNumber");

    public final DateTimePath<java.sql.Timestamp> dateProcessed = createDateTime("dateProcessed", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> dateReceived = createDateTime("dateReceived", java.sql.Timestamp.class);

    public final StringPath disRecovery = createString("disRecovery");

    public final StringPath externalReview = createString("externalReview");

    public final DateTimePath<java.sql.Timestamp> externalReviewDatetime = createDateTime("externalReviewDatetime", java.sql.Timestamp.class);

    public final StringPath extSrcinfo = createString("extSrcinfo");

    public final StringPath financeValidated = createString("financeValidated");

    public final StringPath hippaValidated = createString("hippaValidated");

    public final DateTimePath<java.sql.Timestamp> inductDatetime = createDateTime("inductDatetime", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> intakeComplete = createDateTime("intakeComplete", java.sql.Timestamp.class);

    public final StringPath intakeFillLocation = createString("intakeFillLocation");

    public final StringPath isMybOrder = createString("isMybOrder");

    public final NumberPath<Byte> locked = createNumber("locked", Byte.class);

    public final NumberPath<java.math.BigInteger> mergeParentOrderNum = createNumber("mergeParentOrderNum", java.math.BigInteger.class);

    public final DateTimePath<java.sql.Timestamp> needByDate = createDateTime("needByDate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> notified = createDateTime("notified", java.sql.Timestamp.class);

    public final NumberPath<Long> orderAmount = createNumber("orderAmount", Long.class);

    public final NumberPath<Byte> orderAutoFilled = createNumber("orderAutoFilled", Byte.class);

    public final DateTimePath<java.sql.Timestamp> orderDate = createDateTime("orderDate", java.sql.Timestamp.class);

    public final NumberPath<Byte> orderDur = createNumber("orderDur", Byte.class);

    public final NumberPath<java.math.BigInteger> orderGlPosted = createNumber("orderGlPosted", java.math.BigInteger.class);

    public final StringPath orderHighPriority = createString("orderHighPriority");

    public final StringPath orderInvoiceNumber = createString("orderInvoiceNumber");

    public final NumberPath<java.math.BigInteger> orderNum = createNumber("orderNum", java.math.BigInteger.class);

    public final StringPath orderOftValidated = createString("orderOftValidated");

    public final StringPath orderPatientValidated = createString("orderPatientValidated");

    public final StringPath orderPicked = createString("orderPicked");

    public final StringPath orderPrint = createString("orderPrint");

    public final DateTimePath<java.sql.Timestamp> orderSplitDate = createDateTime("orderSplitDate", java.sql.Timestamp.class);

    public final NumberPath<Byte> orderStatusNum = createNumber("orderStatusNum", Byte.class);

    public final NumberPath<Long> orderTax = createNumber("orderTax", Long.class);

    public final NumberPath<Long> orderTotalAmount = createNumber("orderTotalAmount", Long.class);

    public final NumberPath<Long> orderTotalPayments = createNumber("orderTotalPayments", Long.class);

    public final StringPath orderToteNumber = createString("orderToteNumber");

    public final StringPath orderType = createString("orderType");

    public final StringPath orderValidated = createString("orderValidated");

    public final NumberPath<Integer> originationNum = createNumber("originationNum", Integer.class);

    public final NumberPath<Byte> pendingShipment = createNumber("pendingShipment", Byte.class);

    public final NumberPath<Integer> pickupDeliveryMethodsSeq = createNumber("pickupDeliveryMethodsSeq", Integer.class);

    public final StringPath postEdit = createString("postEdit");

    public final StringPath privateLabel = createString("privateLabel");

    public final StringPath pwoChecked = createString("pwoChecked");

    public final DateTimePath<java.sql.Timestamp> quoteDatetime = createDateTime("quoteDatetime", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> replaceDatetime = createDateTime("replaceDatetime", java.sql.Timestamp.class);

    public final NumberPath<java.math.BigInteger> replaceParentOrderNum = createNumber("replaceParentOrderNum", java.math.BigInteger.class);

    public final DateTimePath<java.sql.Timestamp> replaceReviewDatetime = createDateTime("replaceReviewDatetime", java.sql.Timestamp.class);

    public final NumberPath<Long> replaceReviewUsernum = createNumber("replaceReviewUsernum", Long.class);

    public final DateTimePath<java.sql.Timestamp> returnDatetime = createDateTime("returnDatetime", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> returnReviewDatetime = createDateTime("returnReviewDatetime", java.sql.Timestamp.class);

    public final NumberPath<Long> returnReviewUsernum = createNumber("returnReviewUsernum", Long.class);

    public final NumberPath<Byte> returnShippingCost = createNumber("returnShippingCost", Byte.class);

    public final SimplePath<byte[]> rowversion = createSimple("rowversion", byte[].class);

    public final StringPath salestaxCalculated = createString("salestaxCalculated");

    public final NumberPath<Byte> sanityok = createNumber("sanityok", Byte.class);

    public final NumberPath<Byte> sanityok2 = createNumber("sanityok2", Byte.class);

    public final NumberPath<Byte> sanityok3 = createNumber("sanityok3", Byte.class);

    public final StringPath sourceHost = createString("sourceHost");

    public final StringPath sourceUser = createString("sourceUser");

    public final BooleanPath specialtyValidated = createBoolean("specialtyValidated");

    public final StringPath suborderValidated = createString("suborderValidated");

    public final NumberPath<Long> sysUserNum = createNumber("sysUserNum", Long.class);

    public final NumberPath<Long> tradingPartnerNum = createNumber("tradingPartnerNum", Long.class);

    public final DateTimePath<java.sql.Timestamp> workflowComplete = createDateTime("workflowComplete", java.sql.Timestamp.class);

    public final NumberPath<Integer> workflowNum = createNumber("workflowNum", Integer.class);

    public QOrderHeader(String variable) {
        super(OrderHeader.class, forVariable(variable), "dbo", "order_header");
        addMetadata();
    }

    public QOrderHeader(String variable, String schema, String table) {
        super(OrderHeader.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QOrderHeader(String variable, String schema) {
        super(OrderHeader.class, forVariable(variable), schema, "order_header");
        addMetadata();
    }

    public QOrderHeader(Path<? extends OrderHeader> path) {
        super(path.getType(), path.getMetadata(), "dbo", "order_header");
        addMetadata();
    }

    public QOrderHeader(PathMetadata metadata) {
        super(OrderHeader.class, metadata, "dbo", "order_header");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(arValidated, ColumnMetadata.named("ar_validated").withIndex(42).ofType(Types.CHAR).withSize(1));
        addMetadata(autoCancel, ColumnMetadata.named("auto_cancel").withIndex(70).ofType(Types.CHAR).withSize(1));
        addMetadata(autoDispenseLocation, ColumnMetadata.named("auto_dispense_location").withIndex(54).ofType(Types.NUMERIC).withSize(16));
        addMetadata(autoError, ColumnMetadata.named("auto_error").withIndex(64).ofType(Types.NUMERIC).withSize(16));
        addMetadata(autoRoute, ColumnMetadata.named("auto_route").withIndex(53).ofType(Types.CHAR).withSize(1));
        addMetadata(autoRtsSplit, ColumnMetadata.named("auto_rts_split").withIndex(69).ofType(Types.CHAR).withSize(1));
        addMetadata(autoShipComplete, ColumnMetadata.named("auto_ship_complete").withIndex(46).ofType(Types.CHAR).withSize(1));
        addMetadata(autoSplit, ColumnMetadata.named("auto_split").withIndex(51).ofType(Types.CHAR).withSize(1));
        addMetadata(autoWorkflowTypeNum, ColumnMetadata.named("auto_workflow_type_num").withIndex(55).ofType(Types.NUMERIC).withSize(7));
        addMetadata(basketNumber, ColumnMetadata.named("basket_number").withIndex(78).ofType(Types.VARCHAR).withSize(40));
        addMetadata(batchecs, ColumnMetadata.named("batchecs").withIndex(29).ofType(Types.NUMERIC).withSize(1));
        addMetadata(cancelDatetime, ColumnMetadata.named("cancel_datetime").withIndex(56).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(cancelUsernum, ColumnMetadata.named("cancel_usernum").withIndex(57).ofType(Types.NUMERIC).withSize(16));
        addMetadata(ccBusy, ColumnMetadata.named("cc_busy").withIndex(24).ofType(Types.NUMERIC).withSize(1));
        addMetadata(conversionLink, ColumnMetadata.named("conversion_link").withIndex(30).ofType(Types.VARCHAR).withSize(60));
        addMetadata(currentBalance, ColumnMetadata.named("current_balance").withIndex(43).ofType(Types.NUMERIC).withSize(12));
        addMetadata(cycleFillRunNumber, ColumnMetadata.named("cycle_fill_run_number").withIndex(47).ofType(Types.VARCHAR).withSize(60));
        addMetadata(dateProcessed, ColumnMetadata.named("date_processed").withIndex(16).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(dateReceived, ColumnMetadata.named("date_received").withIndex(58).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(disRecovery, ColumnMetadata.named("dis_recovery").withIndex(59).ofType(Types.CHAR).withSize(1));
        addMetadata(externalReview, ColumnMetadata.named("external_review").withIndex(37).ofType(Types.CHAR).withSize(1));
        addMetadata(externalReviewDatetime, ColumnMetadata.named("external_review_datetime").withIndex(63).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(extSrcinfo, ColumnMetadata.named("ext_srcinfo").withIndex(62).ofType(Types.VARCHAR).withSize(255));
        addMetadata(financeValidated, ColumnMetadata.named("finance_validated").withIndex(80).ofType(Types.VARCHAR).withSize(1));
        addMetadata(hippaValidated, ColumnMetadata.named("hippa_validated").withIndex(41).ofType(Types.CHAR).withSize(1));
        addMetadata(inductDatetime, ColumnMetadata.named("induct_datetime").withIndex(44).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(intakeComplete, ColumnMetadata.named("intake_complete").withIndex(40).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(intakeFillLocation, ColumnMetadata.named("intake_fill_location").withIndex(39).ofType(Types.VARCHAR).withSize(60));
        addMetadata(isMybOrder, ColumnMetadata.named("is_myb_order").withIndex(76).ofType(Types.CHAR).withSize(1));
        addMetadata(locked, ColumnMetadata.named("locked").withIndex(36).ofType(Types.NUMERIC).withSize(1));
        addMetadata(mergeParentOrderNum, ColumnMetadata.named("merge_parent_order_num").withIndex(61).ofType(Types.NUMERIC).withSize(30));
        addMetadata(needByDate, ColumnMetadata.named("need_by_date").withIndex(48).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(notified, ColumnMetadata.named("notified").withIndex(25).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(orderAmount, ColumnMetadata.named("order_amount").withIndex(8).ofType(Types.NUMERIC).withSize(12));
        addMetadata(orderAutoFilled, ColumnMetadata.named("order_auto_filled").withIndex(19).ofType(Types.NUMERIC).withSize(1));
        addMetadata(orderDate, ColumnMetadata.named("order_date").withIndex(7).ofType(Types.TIMESTAMP).withSize(23).withDigits(3).notNull());
        addMetadata(orderDur, ColumnMetadata.named("order_dur").withIndex(34).ofType(Types.NUMERIC).withSize(1));
        addMetadata(orderGlPosted, ColumnMetadata.named("order_gl_posted").withIndex(14).ofType(Types.NUMERIC).withSize(30));
        addMetadata(orderHighPriority, ColumnMetadata.named("order_high_priority").withIndex(10).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(orderInvoiceNumber, ColumnMetadata.named("order_invoice_number").withIndex(12).ofType(Types.VARCHAR).withSize(60));
        addMetadata(orderNum, ColumnMetadata.named("order_num").withIndex(1).ofType(Types.NUMERIC).withSize(30).notNull());
        addMetadata(orderOftValidated, ColumnMetadata.named("order_oft_validated").withIndex(79).ofType(Types.CHAR).withSize(1));
        addMetadata(orderPatientValidated, ColumnMetadata.named("order_patient_validated").withIndex(26).ofType(Types.CHAR).withSize(1));
        addMetadata(orderPicked, ColumnMetadata.named("order_picked").withIndex(15).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(orderPrint, ColumnMetadata.named("order_print").withIndex(23).ofType(Types.CHAR).withSize(1));
        addMetadata(orderSplitDate, ColumnMetadata.named("order_split_date").withIndex(75).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(orderStatusNum, ColumnMetadata.named("order_status_num").withIndex(4).ofType(Types.NUMERIC).withSize(2));
        addMetadata(orderTax, ColumnMetadata.named("order_tax").withIndex(9).ofType(Types.NUMERIC).withSize(12));
        addMetadata(orderTotalAmount, ColumnMetadata.named("order_total_amount").withIndex(11).ofType(Types.NUMERIC).withSize(12));
        addMetadata(orderTotalPayments, ColumnMetadata.named("order_total_payments").withIndex(13).ofType(Types.NUMERIC).withSize(12));
        addMetadata(orderToteNumber, ColumnMetadata.named("order_tote_number").withIndex(32).ofType(Types.VARCHAR).withSize(60));
        addMetadata(orderType, ColumnMetadata.named("order_type").withIndex(49).ofType(Types.VARCHAR).withSize(60));
        addMetadata(orderValidated, ColumnMetadata.named("order_validated").withIndex(20).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(originationNum, ColumnMetadata.named("origination_num").withIndex(5).ofType(Types.NUMERIC).withSize(5));
        addMetadata(pendingShipment, ColumnMetadata.named("pending_shipment").withIndex(45).ofType(Types.NUMERIC).withSize(1));
        addMetadata(pickupDeliveryMethodsSeq, ColumnMetadata.named("pickup_delivery_methods_seq").withIndex(35).ofType(Types.NUMERIC).withSize(5));
        addMetadata(postEdit, ColumnMetadata.named("post_edit").withIndex(33).ofType(Types.CHAR).withSize(1));
        addMetadata(privateLabel, ColumnMetadata.named("private_label").withIndex(50).ofType(Types.CHAR).withSize(1));
        addMetadata(pwoChecked, ColumnMetadata.named("pwo_checked").withIndex(71).ofType(Types.CHAR).withSize(1));
        addMetadata(quoteDatetime, ColumnMetadata.named("quote_datetime").withIndex(21).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(replaceDatetime, ColumnMetadata.named("replace_datetime").withIndex(18).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(replaceParentOrderNum, ColumnMetadata.named("replace_parent_order_num").withIndex(60).ofType(Types.NUMERIC).withSize(30));
        addMetadata(replaceReviewDatetime, ColumnMetadata.named("replace_review_datetime").withIndex(65).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(replaceReviewUsernum, ColumnMetadata.named("replace_review_usernum").withIndex(66).ofType(Types.NUMERIC).withSize(16));
        addMetadata(returnDatetime, ColumnMetadata.named("return_datetime").withIndex(17).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(returnReviewDatetime, ColumnMetadata.named("return_review_datetime").withIndex(67).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(returnReviewUsernum, ColumnMetadata.named("return_review_usernum").withIndex(68).ofType(Types.NUMERIC).withSize(16));
        addMetadata(returnShippingCost, ColumnMetadata.named("return_shipping_cost").withIndex(27).ofType(Types.NUMERIC).withSize(1));
        addMetadata(rowversion, ColumnMetadata.named("rowversion").withIndex(74).ofType(Types.BINARY).withSize(8).notNull());
        addMetadata(salestaxCalculated, ColumnMetadata.named("salestax_calculated").withIndex(77).ofType(Types.CHAR).withSize(1));
        addMetadata(sanityok, ColumnMetadata.named("sanityok").withIndex(28).ofType(Types.NUMERIC).withSize(1));
        addMetadata(sanityok2, ColumnMetadata.named("sanityok2").withIndex(38).ofType(Types.NUMERIC).withSize(1));
        addMetadata(sanityok3, ColumnMetadata.named("sanityok3").withIndex(52).ofType(Types.NUMERIC).withSize(1));
        addMetadata(sourceHost, ColumnMetadata.named("source_host").withIndex(72).ofType(Types.VARCHAR).withSize(60));
        addMetadata(sourceUser, ColumnMetadata.named("source_user").withIndex(73).ofType(Types.VARCHAR).withSize(60));
        addMetadata(specialtyValidated, ColumnMetadata.named("specialty_validated").withIndex(81).ofType(Types.BIT).withSize(1));
        addMetadata(suborderValidated, ColumnMetadata.named("suborder_validated").withIndex(22).ofType(Types.CHAR).withSize(1));
        addMetadata(sysUserNum, ColumnMetadata.named("sys_user_num").withIndex(6).ofType(Types.NUMERIC).withSize(16));
        addMetadata(tradingPartnerNum, ColumnMetadata.named("trading_partner_num").withIndex(2).ofType(Types.NUMERIC).withSize(16));
        addMetadata(workflowComplete, ColumnMetadata.named("workflow_complete").withIndex(31).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(workflowNum, ColumnMetadata.named("workflow_num").withIndex(3).ofType(Types.NUMERIC).withSize(5));
    }

}

