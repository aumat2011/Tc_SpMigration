package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.OrderLines;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QOrderLines is a Querydsl query type for OrderLines
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QOrderLines extends com.querydsl.sql.RelationalPathBase<OrderLines> {

    private static final long serialVersionUID = 2072734763;

    public static final QOrderLines orderLines = new QOrderLines("order_lines");

    public final StringPath benefitMonth = createString("benefitMonth");

    public final DateTimePath<java.sql.Timestamp> cancelDatetime = createDateTime("cancelDatetime", java.sql.Timestamp.class);

    public final NumberPath<Long> cancelUsernum = createNumber("cancelUsernum", Long.class);

    public final StringPath careKit = createString("careKit");

    public final NumberPath<Long> cityTax = createNumber("cityTax", Long.class);

    public final StringPath copayWaived = createString("copayWaived");

    public final NumberPath<Long> countyTax = createNumber("countyTax", Long.class);

    public final NumberPath<java.math.BigInteger> eScriptMsgAttributeSeq = createNumber("eScriptMsgAttributeSeq", java.math.BigInteger.class);

    public final NumberPath<Long> estimatedLineTax = createNumber("estimatedLineTax", Long.class);

    public final DateTimePath<java.sql.Timestamp> expirationDate = createDateTime("expirationDate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> expirationDate2 = createDateTime("expirationDate2", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> expirationDate3 = createDateTime("expirationDate3", java.sql.Timestamp.class);

    public final StringPath fillMode = createString("fillMode");

    public final DateTimePath<java.sql.Timestamp> intakeDatetime = createDateTime("intakeDatetime", java.sql.Timestamp.class);

    public final NumberPath<Long> intakeUser = createNumber("intakeUser", Long.class);

    public final StringPath lotNumber = createString("lotNumber");

    public final StringPath lotNumber2 = createString("lotNumber2");

    public final StringPath lotNumber3 = createString("lotNumber3");

    public final NumberPath<Long> orderLineAmount = createNumber("orderLineAmount", Long.class);

    public final NumberPath<Integer> orderLineChecked = createNumber("orderLineChecked", Integer.class);

    public final DateTimePath<java.sql.Timestamp> orderLineCreationDatetime = createDateTime("orderLineCreationDatetime", java.sql.Timestamp.class);

    public final NumberPath<Long> orderLineGenSavings = createNumber("orderLineGenSavings", Long.class);

    public final NumberPath<Short> orderLineInvStatus = createNumber("orderLineInvStatus", Short.class);

    public final NumberPath<Integer> orderLineItemTypeID = createNumber("orderLineItemTypeID", Integer.class);

    public final NumberPath<java.math.BigInteger> orderLineNum = createNumber("orderLineNum", java.math.BigInteger.class);

    public final NumberPath<Long> orderLineQty = createNumber("orderLineQty", Long.class);

    public final NumberPath<Long> orderLineQuote = createNumber("orderLineQuote", Long.class);

    public final DateTimePath<java.sql.Timestamp> orderLineQuoteDatetime = createDateTime("orderLineQuoteDatetime", java.sql.Timestamp.class);

    public final NumberPath<Byte> orderLineStatusNum = createNumber("orderLineStatusNum", Byte.class);

    public final NumberPath<Long> orderLineTax = createNumber("orderLineTax", Long.class);

    public final StringPath orderLineTaxRate = createString("orderLineTaxRate");

    public final NumberPath<Long> orderLineTotalAmt = createNumber("orderLineTotalAmt", Long.class);

    public final NumberPath<Byte> orderLineTypeNum = createNumber("orderLineTypeNum", Byte.class);

    public final NumberPath<Long> orderLineUnitPrice = createNumber("orderLineUnitPrice", Long.class);

    public final NumberPath<Long> orderLineWacUnitPrice = createNumber("orderLineWacUnitPrice", Long.class);

    public final DateTimePath<java.sql.Timestamp> orderManualPackDatetime = createDateTime("orderManualPackDatetime", java.sql.Timestamp.class);

    public final NumberPath<Long> orderManualPackUser = createNumber("orderManualPackUser", Long.class);

    public final DateTimePath<java.sql.Timestamp> orderManualqaDatetime = createDateTime("orderManualqaDatetime", java.sql.Timestamp.class);

    public final NumberPath<Long> orderManualqaUser = createNumber("orderManualqaUser", Long.class);

    public final DateTimePath<java.sql.Timestamp> orderManualtestDatetime = createDateTime("orderManualtestDatetime", java.sql.Timestamp.class);

    public final NumberPath<Long> orderManualtestUser = createNumber("orderManualtestUser", Long.class);

    public final NumberPath<java.math.BigInteger> orderNum = createNumber("orderNum", java.math.BigInteger.class);

    public final DateTimePath<java.sql.Timestamp> orderPrecheckDatetime = createDateTime("orderPrecheckDatetime", java.sql.Timestamp.class);

    public final NumberPath<Long> orderPrecheckUser = createNumber("orderPrecheckUser", Long.class);

    public final DateTimePath<java.sql.Timestamp> orderShippackDatetime = createDateTime("orderShippackDatetime", java.sql.Timestamp.class);

    public final NumberPath<Long> orderShippackUser = createNumber("orderShippackUser", Long.class);

    public final NumberPath<Integer> orderSplitReasonNum = createNumber("orderSplitReasonNum", Integer.class);

    public final NumberPath<Long> orderSuborderSeq = createNumber("orderSuborderSeq", Long.class);

    public final NumberPath<Long> originalOrderLineAmount = createNumber("originalOrderLineAmount", Long.class);

    public final NumberPath<Long> productFlatSalesTaxSubmitted = createNumber("productFlatSalesTaxSubmitted", Long.class);

    public final NumberPath<Integer> replaceReasonNum = createNumber("replaceReasonNum", Integer.class);

    public final DateTimePath<java.sql.Timestamp> replaceReviewDatetime = createDateTime("replaceReviewDatetime", java.sql.Timestamp.class);

    public final StringPath replaceReviewStatus = createString("replaceReviewStatus");

    public final NumberPath<Long> replaceReviewUsernum = createNumber("replaceReviewUsernum", Long.class);

    public final NumberPath<java.math.BigInteger> returnInventoryLocation = createNumber("returnInventoryLocation", java.math.BigInteger.class);

    public final NumberPath<Integer> returnReasonNum = createNumber("returnReasonNum", Integer.class);

    public final NumberPath<Long> returnReplaceAmount = createNumber("returnReplaceAmount", Long.class);

    public final DateTimePath<java.sql.Timestamp> returnReplaceDatetime = createDateTime("returnReplaceDatetime", java.sql.Timestamp.class);

    public final StringPath returnReplaceEcs = createString("returnReplaceEcs");

    public final NumberPath<java.math.BigInteger> returnReplaceGl = createNumber("returnReplaceGl", java.math.BigInteger.class);

    public final StringPath returnReplaceInventory = createString("returnReplaceInventory");

    public final StringPath returnReplacePaymentCard = createString("returnReplacePaymentCard");

    public final NumberPath<Long> returnReplaceQuantity = createNumber("returnReplaceQuantity", Long.class);

    public final NumberPath<Long> returnReplaceTotalAmt = createNumber("returnReplaceTotalAmt", Long.class);

    public final NumberPath<Long> returnReplaceUser = createNumber("returnReplaceUser", Long.class);

    public final StringPath returnReplaceZeroPrice = createString("returnReplaceZeroPrice");

    public final DateTimePath<java.sql.Timestamp> returnReviewDatetime = createDateTime("returnReviewDatetime", java.sql.Timestamp.class);

    public final StringPath returnReviewStatus = createString("returnReviewStatus");

    public final NumberPath<Long> returnReviewUsernum = createNumber("returnReviewUsernum", Long.class);

    public final NumberPath<Long> routingTpNum = createNumber("routingTpNum", Long.class);

    public final DateTimePath<java.sql.Timestamp> splitOrderDatetime = createDateTime("splitOrderDatetime", java.sql.Timestamp.class);

    public final NumberPath<Short> splitOrderLineNum = createNumber("splitOrderLineNum", Short.class);

    public final NumberPath<java.math.BigInteger> splitOrderNum = createNumber("splitOrderNum", java.math.BigInteger.class);

    public final NumberPath<Long> splitOrderUsernum = createNumber("splitOrderUsernum", Long.class);

    public final NumberPath<Long> stateTax = createNumber("stateTax", Long.class);

    public QOrderLines(String variable) {
        super(OrderLines.class, forVariable(variable), "dbo", "order_lines");
        addMetadata();
    }

    public QOrderLines(String variable, String schema, String table) {
        super(OrderLines.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QOrderLines(String variable, String schema) {
        super(OrderLines.class, forVariable(variable), schema, "order_lines");
        addMetadata();
    }

    public QOrderLines(Path<? extends OrderLines> path) {
        super(path.getType(), path.getMetadata(), "dbo", "order_lines");
        addMetadata();
    }

    public QOrderLines(PathMetadata metadata) {
        super(OrderLines.class, metadata, "dbo", "order_lines");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(benefitMonth, ColumnMetadata.named("benefit_month").withIndex(75).ofType(Types.VARCHAR).withSize(10));
        addMetadata(cancelDatetime, ColumnMetadata.named("cancel_datetime").withIndex(56).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(cancelUsernum, ColumnMetadata.named("cancel_usernum").withIndex(57).ofType(Types.NUMERIC).withSize(16));
        addMetadata(careKit, ColumnMetadata.named("care_kit").withIndex(39).ofType(Types.VARCHAR).withSize(60));
        addMetadata(cityTax, ColumnMetadata.named("city_tax").withIndex(53).ofType(Types.NUMERIC).withSize(12));
        addMetadata(copayWaived, ColumnMetadata.named("copay_waived").withIndex(70).ofType(Types.CHAR).withSize(1));
        addMetadata(countyTax, ColumnMetadata.named("county_tax").withIndex(55).ofType(Types.NUMERIC).withSize(12));
        addMetadata(eScriptMsgAttributeSeq, ColumnMetadata.named("e_script_msg_attribute_seq").withIndex(3).ofType(Types.NUMERIC).withSize(35).notNull());
        addMetadata(estimatedLineTax, ColumnMetadata.named("estimated_line_tax").withIndex(68).ofType(Types.NUMERIC).withSize(12));
        addMetadata(expirationDate, ColumnMetadata.named("expiration_date").withIndex(40).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(expirationDate2, ColumnMetadata.named("expiration_date2").withIndex(46).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(expirationDate3, ColumnMetadata.named("expiration_date3").withIndex(47).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(fillMode, ColumnMetadata.named("fill_mode").withIndex(48).ofType(Types.VARCHAR).withSize(60));
        addMetadata(intakeDatetime, ColumnMetadata.named("intake_datetime").withIndex(45).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(intakeUser, ColumnMetadata.named("intake_user").withIndex(44).ofType(Types.NUMERIC).withSize(16));
        addMetadata(lotNumber, ColumnMetadata.named("lot_number").withIndex(41).ofType(Types.VARCHAR).withSize(60));
        addMetadata(lotNumber2, ColumnMetadata.named("lot_number2").withIndex(42).ofType(Types.VARCHAR).withSize(60));
        addMetadata(lotNumber3, ColumnMetadata.named("lot_number3").withIndex(43).ofType(Types.VARCHAR).withSize(60));
        addMetadata(orderLineAmount, ColumnMetadata.named("order_line_amount").withIndex(12).ofType(Types.NUMERIC).withSize(12));
        addMetadata(orderLineChecked, ColumnMetadata.named("order_line_checked").withIndex(73).ofType(Types.NUMERIC).withSize(5));
        addMetadata(orderLineCreationDatetime, ColumnMetadata.named("order_line_creation_datetime").withIndex(9).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(orderLineGenSavings, ColumnMetadata.named("order_line_gen_savings").withIndex(19).ofType(Types.NUMERIC).withSize(12));
        addMetadata(orderLineInvStatus, ColumnMetadata.named("order_line_inv_status").withIndex(20).ofType(Types.NUMERIC).withSize(3));
        addMetadata(orderLineItemTypeID, ColumnMetadata.named("OrderLineItemTypeID").withIndex(72).ofType(Types.INTEGER).withSize(10));
        addMetadata(orderLineNum, ColumnMetadata.named("order_line_num").withIndex(1).ofType(Types.NUMERIC).withSize(35).notNull());
        addMetadata(orderLineQty, ColumnMetadata.named("order_line_qty").withIndex(10).ofType(Types.NUMERIC).withSize(15));
        addMetadata(orderLineQuote, ColumnMetadata.named("order_line_quote").withIndex(49).ofType(Types.NUMERIC).withSize(12));
        addMetadata(orderLineQuoteDatetime, ColumnMetadata.named("order_line_quote_datetime").withIndex(51).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(orderLineStatusNum, ColumnMetadata.named("order_line_status_num").withIndex(7).ofType(Types.NUMERIC).withSize(2));
        addMetadata(orderLineTax, ColumnMetadata.named("order_line_tax").withIndex(13).ofType(Types.NUMERIC).withSize(12));
        addMetadata(orderLineTaxRate, ColumnMetadata.named("order_line_tax_rate").withIndex(67).ofType(Types.VARCHAR).withSize(20));
        addMetadata(orderLineTotalAmt, ColumnMetadata.named("order_line_total_amt").withIndex(14).ofType(Types.NUMERIC).withSize(12));
        addMetadata(orderLineTypeNum, ColumnMetadata.named("order_line_type_num").withIndex(8).ofType(Types.NUMERIC).withSize(2).notNull());
        addMetadata(orderLineUnitPrice, ColumnMetadata.named("order_line_unit_price").withIndex(11).ofType(Types.NUMERIC).withSize(12));
        addMetadata(orderLineWacUnitPrice, ColumnMetadata.named("order_line_wac_unit_price").withIndex(50).ofType(Types.NUMERIC).withSize(15));
        addMetadata(orderManualPackDatetime, ColumnMetadata.named("order_manual_pack_datetime").withIndex(36).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(orderManualPackUser, ColumnMetadata.named("order_manual_pack_user").withIndex(35).ofType(Types.NUMERIC).withSize(16));
        addMetadata(orderManualqaDatetime, ColumnMetadata.named("order_manualqa_datetime").withIndex(31).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(orderManualqaUser, ColumnMetadata.named("order_manualqa_user").withIndex(33).ofType(Types.NUMERIC).withSize(16));
        addMetadata(orderManualtestDatetime, ColumnMetadata.named("order_manualtest_datetime").withIndex(21).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(orderManualtestUser, ColumnMetadata.named("order_manualtest_user").withIndex(22).ofType(Types.NUMERIC).withSize(16));
        addMetadata(orderNum, ColumnMetadata.named("order_num").withIndex(2).ofType(Types.NUMERIC).withSize(30).notNull());
        addMetadata(orderPrecheckDatetime, ColumnMetadata.named("order_precheck_datetime").withIndex(37).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(orderPrecheckUser, ColumnMetadata.named("order_precheck_user").withIndex(38).ofType(Types.NUMERIC).withSize(16));
        addMetadata(orderShippackDatetime, ColumnMetadata.named("order_shippack_datetime").withIndex(23).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(orderShippackUser, ColumnMetadata.named("order_shippack_user").withIndex(24).ofType(Types.NUMERIC).withSize(16));
        addMetadata(orderSplitReasonNum, ColumnMetadata.named("order_split_reason_num").withIndex(18).ofType(Types.NUMERIC).withSize(5));
        addMetadata(orderSuborderSeq, ColumnMetadata.named("order_suborder_seq").withIndex(6).ofType(Types.NUMERIC).withSize(16));
        addMetadata(originalOrderLineAmount, ColumnMetadata.named("original_order_line_amount").withIndex(69).ofType(Types.NUMERIC).withSize(12));
        addMetadata(productFlatSalesTaxSubmitted, ColumnMetadata.named("product_flat_sales_tax_submitted").withIndex(71).ofType(Types.NUMERIC).withSize(12));
        addMetadata(replaceReasonNum, ColumnMetadata.named("replace_reason_num").withIndex(4).ofType(Types.NUMERIC).withSize(5));
        addMetadata(replaceReviewDatetime, ColumnMetadata.named("replace_review_datetime").withIndex(58).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(replaceReviewStatus, ColumnMetadata.named("replace_review_status").withIndex(62).ofType(Types.VARCHAR).withSize(60));
        addMetadata(replaceReviewUsernum, ColumnMetadata.named("replace_review_usernum").withIndex(59).ofType(Types.NUMERIC).withSize(16));
        addMetadata(returnInventoryLocation, ColumnMetadata.named("return_inventory_location").withIndex(64).ofType(Types.NUMERIC).withSize(30));
        addMetadata(returnReasonNum, ColumnMetadata.named("return_reason_num").withIndex(5).ofType(Types.NUMERIC).withSize(5));
        addMetadata(returnReplaceAmount, ColumnMetadata.named("return_replace_amount").withIndex(30).ofType(Types.NUMERIC).withSize(12));
        addMetadata(returnReplaceDatetime, ColumnMetadata.named("return_replace_datetime").withIndex(25).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(returnReplaceEcs, ColumnMetadata.named("return_replace_ecs").withIndex(27).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(returnReplaceGl, ColumnMetadata.named("return_replace_gl").withIndex(34).ofType(Types.NUMERIC).withSize(30));
        addMetadata(returnReplaceInventory, ColumnMetadata.named("return_replace_inventory").withIndex(28).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(returnReplacePaymentCard, ColumnMetadata.named("return_replace_payment_card").withIndex(29).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(returnReplaceQuantity, ColumnMetadata.named("return_replace_quantity").withIndex(17).ofType(Types.NUMERIC).withSize(15));
        addMetadata(returnReplaceTotalAmt, ColumnMetadata.named("return_replace_total_amt").withIndex(32).ofType(Types.NUMERIC).withSize(12));
        addMetadata(returnReplaceUser, ColumnMetadata.named("return_replace_user").withIndex(26).ofType(Types.NUMERIC).withSize(16));
        addMetadata(returnReplaceZeroPrice, ColumnMetadata.named("return_replace_zero_price").withIndex(52).ofType(Types.CHAR).withSize(1));
        addMetadata(returnReviewDatetime, ColumnMetadata.named("return_review_datetime").withIndex(60).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(returnReviewStatus, ColumnMetadata.named("return_review_status").withIndex(63).ofType(Types.VARCHAR).withSize(60));
        addMetadata(returnReviewUsernum, ColumnMetadata.named("return_review_usernum").withIndex(61).ofType(Types.NUMERIC).withSize(16));
        addMetadata(routingTpNum, ColumnMetadata.named("routing_tp_num").withIndex(74).ofType(Types.NUMERIC).withSize(16));
        addMetadata(splitOrderDatetime, ColumnMetadata.named("split_order_datetime").withIndex(66).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(splitOrderLineNum, ColumnMetadata.named("split_order_line_num").withIndex(16).ofType(Types.NUMERIC).withSize(3));
        addMetadata(splitOrderNum, ColumnMetadata.named("split_order_num").withIndex(15).ofType(Types.NUMERIC).withSize(20));
        addMetadata(splitOrderUsernum, ColumnMetadata.named("split_order_usernum").withIndex(65).ofType(Types.NUMERIC).withSize(16));
        addMetadata(stateTax, ColumnMetadata.named("state_tax").withIndex(54).ofType(Types.NUMERIC).withSize(12));
    }

}

