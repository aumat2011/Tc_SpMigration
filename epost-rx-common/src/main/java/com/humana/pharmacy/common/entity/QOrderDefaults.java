package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.OrderDefaults;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QOrderDefaults is a Querydsl query type for OrderDefaults
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QOrderDefaults extends com.querydsl.sql.RelationalPathBase<OrderDefaults> {

    private static final long serialVersionUID = -448678490;

    public static final QOrderDefaults orderDefaults = new QOrderDefaults("order_defaults");

    public final NumberPath<Short> autoSplitDays = createNumber("autoSplitDays", Short.class);

    public final StringPath billCcShippack = createString("billCcShippack");

    public final NumberPath<Integer> couponNum = createNumber("couponNum", Integer.class);

    public final StringPath cpAdjudication = createString("cpAdjudication");

    public final StringPath familyMode = createString("familyMode");

    public final NumberPath<Integer> finalShipStatus = createNumber("finalShipStatus", Integer.class);

    public final StringPath lockOrder = createString("lockOrder");

    public final NumberPath<Short> maxReturnDays = createNumber("maxReturnDays", Short.class);

    public final NumberPath<Short> mYBBypassDays = createNumber("mYBBypassDays", Short.class);

    public final NumberPath<Integer> needByDelta = createNumber("needByDelta", Integer.class);

    public final NumberPath<Integer> noteTypeId = createNumber("noteTypeId", Integer.class);

    public final StringPath ordDefPrintNote = createString("ordDefPrintNote");

    public final NumberPath<Integer> orderHoldReasonNum = createNumber("orderHoldReasonNum", Integer.class);

    public final NumberPath<Byte> orderLineStatusNum = createNumber("orderLineStatusNum", Byte.class);

    public final StringPath orderReturnShipcost = createString("orderReturnShipcost");

    public final NumberPath<Integer> orderSplitHoldReasonNum = createNumber("orderSplitHoldReasonNum", Integer.class);

    public final NumberPath<Integer> orderSplitReasonNum = createNumber("orderSplitReasonNum", Integer.class);

    public final NumberPath<Byte> orderStatusNum = createNumber("orderStatusNum", Byte.class);

    public final NumberPath<Integer> originationNum = createNumber("originationNum", Integer.class);

    public final NumberPath<Integer> paymentMethodId = createNumber("paymentMethodId", Integer.class);

    public final StringPath pcAddressVerification = createString("pcAddressVerification");

    public final NumberPath<Integer> pickupDeliveryMethodsSeq = createNumber("pickupDeliveryMethodsSeq", Integer.class);

    public final NumberPath<Short> quoteDays = createNumber("quoteDays", Short.class);

    public final StringPath replaceCc = createString("replaceCc");

    public final StringPath replaceEcs = createString("replaceEcs");

    public final StringPath replaceInv = createString("replaceInv");

    public final NumberPath<Integer> replaceReasonNum = createNumber("replaceReasonNum", Integer.class);

    public final StringPath returnCc = createString("returnCc");

    public final StringPath returnEcs = createString("returnEcs");

    public final StringPath returnInv = createString("returnInv");

    public final NumberPath<Long> returnLocation = createNumber("returnLocation", Long.class);

    public final NumberPath<Integer> returnReasonNum = createNumber("returnReasonNum", Integer.class);

    public final NumberPath<Short> sameDispensedGPIShippedDays = createNumber("sameDispensedGPIShippedDays", Short.class);

    public final StringPath samePatient = createString("samePatient");

    public final StringPath skipZeroAmt = createString("skipZeroAmt");

    public QOrderDefaults(String variable) {
        super(OrderDefaults.class, forVariable(variable), "dbo", "order_defaults");
        addMetadata();
    }

    public QOrderDefaults(String variable, String schema, String table) {
        super(OrderDefaults.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QOrderDefaults(String variable, String schema) {
        super(OrderDefaults.class, forVariable(variable), schema, "order_defaults");
        addMetadata();
    }

    public QOrderDefaults(Path<? extends OrderDefaults> path) {
        super(path.getType(), path.getMetadata(), "dbo", "order_defaults");
        addMetadata();
    }

    public QOrderDefaults(PathMetadata metadata) {
        super(OrderDefaults.class, metadata, "dbo", "order_defaults");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(autoSplitDays, ColumnMetadata.named("auto_split_days").withIndex(20).ofType(Types.NUMERIC).withSize(4));
        addMetadata(billCcShippack, ColumnMetadata.named("bill_cc_shippack").withIndex(15).ofType(Types.CHAR).withSize(1));
        addMetadata(couponNum, ColumnMetadata.named("coupon_num").withIndex(2).ofType(Types.NUMERIC).withSize(5));
        addMetadata(cpAdjudication, ColumnMetadata.named("cp_adjudication").withIndex(35).ofType(Types.CHAR).withSize(1));
        addMetadata(familyMode, ColumnMetadata.named("family_mode").withIndex(19).ofType(Types.CHAR).withSize(1));
        addMetadata(finalShipStatus, ColumnMetadata.named("final_ship_status").withIndex(31).ofType(Types.NUMERIC).withSize(7));
        addMetadata(lockOrder, ColumnMetadata.named("lock_order").withIndex(18).ofType(Types.CHAR).withSize(1));
        addMetadata(maxReturnDays, ColumnMetadata.named("max_return_days").withIndex(33).ofType(Types.NUMERIC).withSize(3));
        addMetadata(mYBBypassDays, ColumnMetadata.named("MYBBypassDays").withIndex(34).ofType(Types.NUMERIC).withSize(3));
        addMetadata(needByDelta, ColumnMetadata.named("need_by_delta").withIndex(27).ofType(Types.NUMERIC).withSize(5));
        addMetadata(noteTypeId, ColumnMetadata.named("note_type_id").withIndex(7).ofType(Types.NUMERIC).withSize(5));
        addMetadata(ordDefPrintNote, ColumnMetadata.named("ord_def_print_note").withIndex(10).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(orderHoldReasonNum, ColumnMetadata.named("order_hold_reason_num").withIndex(5).ofType(Types.NUMERIC).withSize(5));
        addMetadata(orderLineStatusNum, ColumnMetadata.named("order_line_status_num").withIndex(4).ofType(Types.NUMERIC).withSize(2));
        addMetadata(orderReturnShipcost, ColumnMetadata.named("order_return_shipcost").withIndex(14).ofType(Types.CHAR).withSize(1));
        addMetadata(orderSplitHoldReasonNum, ColumnMetadata.named("order_split_hold_reason_num").withIndex(13).ofType(Types.NUMERIC).withSize(5));
        addMetadata(orderSplitReasonNum, ColumnMetadata.named("order_split_reason_num").withIndex(11).ofType(Types.NUMERIC).withSize(5));
        addMetadata(orderStatusNum, ColumnMetadata.named("order_status_num").withIndex(3).ofType(Types.NUMERIC).withSize(2));
        addMetadata(originationNum, ColumnMetadata.named("origination_num").withIndex(9).ofType(Types.NUMERIC).withSize(5));
        addMetadata(paymentMethodId, ColumnMetadata.named("payment_method_id").withIndex(6).ofType(Types.NUMERIC).withSize(5));
        addMetadata(pcAddressVerification, ColumnMetadata.named("pc_address_verification").withIndex(12).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(pickupDeliveryMethodsSeq, ColumnMetadata.named("pickup_delivery_methods_seq").withIndex(16).ofType(Types.NUMERIC).withSize(5));
        addMetadata(quoteDays, ColumnMetadata.named("quote_days").withIndex(28).ofType(Types.NUMERIC).withSize(3));
        addMetadata(replaceCc, ColumnMetadata.named("replace_cc").withIndex(25).ofType(Types.CHAR).withSize(1));
        addMetadata(replaceEcs, ColumnMetadata.named("replace_ecs").withIndex(24).ofType(Types.CHAR).withSize(1));
        addMetadata(replaceInv, ColumnMetadata.named("replace_inv").withIndex(26).ofType(Types.CHAR).withSize(1));
        addMetadata(replaceReasonNum, ColumnMetadata.named("replace_reason_num").withIndex(1).ofType(Types.NUMERIC).withSize(5));
        addMetadata(returnCc, ColumnMetadata.named("return_cc").withIndex(22).ofType(Types.CHAR).withSize(1));
        addMetadata(returnEcs, ColumnMetadata.named("return_ecs").withIndex(21).ofType(Types.CHAR).withSize(1));
        addMetadata(returnInv, ColumnMetadata.named("return_inv").withIndex(23).ofType(Types.CHAR).withSize(1));
        addMetadata(returnLocation, ColumnMetadata.named("return_location").withIndex(30).ofType(Types.NUMERIC).withSize(16));
        addMetadata(returnReasonNum, ColumnMetadata.named("return_reason_num").withIndex(8).ofType(Types.NUMERIC).withSize(5));
        addMetadata(sameDispensedGPIShippedDays, ColumnMetadata.named("same_dispensed_GPI_shipped_days").withIndex(32).ofType(Types.NUMERIC).withSize(3));
        addMetadata(samePatient, ColumnMetadata.named("same_patient").withIndex(17).ofType(Types.CHAR).withSize(1));
        addMetadata(skipZeroAmt, ColumnMetadata.named("skip_zero_amt").withIndex(29).ofType(Types.CHAR).withSize(1));
    }

}

