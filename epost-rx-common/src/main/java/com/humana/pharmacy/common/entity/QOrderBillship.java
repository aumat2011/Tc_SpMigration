package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.OrderBillship;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QOrderBillship is a Querydsl query type for OrderBillship
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QOrderBillship extends com.querydsl.sql.RelationalPathBase<OrderBillship> {

    private static final long serialVersionUID = -202414569;

    public static final QOrderBillship orderBillship = new QOrderBillship("order_billship");

    public final StringPath addressValidated = createString("addressValidated");

    public final StringPath bankRoutingId = createString("bankRoutingId");

    public final StringPath checkAccount = createString("checkAccount");

    public final NumberPath<Integer> couponNum = createNumber("couponNum", Integer.class);

    public final NumberPath<Long> cszZipNum = createNumber("cszZipNum", Long.class);

    public final DateTimePath<java.sql.Timestamp> estimatedDeliveryDate = createDateTime("estimatedDeliveryDate", java.sql.Timestamp.class);

    public final NumberPath<Long> estShipTax = createNumber("estShipTax", Long.class);

    public final StringPath familyId = createString("familyId");

    public final StringPath forceShipAddress = createString("forceShipAddress");

    public final DateTimePath<java.sql.Timestamp> notified = createDateTime("notified", java.sql.Timestamp.class);

    public final StringPath odcFillLocation = createString("odcFillLocation");

    public final StringPath odcOrderType = createString("odcOrderType");

    public final StringPath orderDispensed = createString("orderDispensed");

    public final NumberPath<java.math.BigInteger> orderNum = createNumber("orderNum", java.math.BigInteger.class);

    public final NumberPath<java.math.BigInteger> orderPaymentOwner = createNumber("orderPaymentOwner", java.math.BigInteger.class);

    public final StringPath ordShipAccountNumber = createString("ordShipAccountNumber");

    public final StringPath ordShipAddress = createString("ordShipAddress");

    public final StringPath ordShipAddress2 = createString("ordShipAddress2");

    public final StringPath ordShipAddress3 = createString("ordShipAddress3");

    public final NumberPath<Long> ordShipCost = createNumber("ordShipCost", Long.class);

    public final DateTimePath<java.sql.Timestamp> ordShipDate = createDateTime("ordShipDate", java.sql.Timestamp.class);

    public final StringPath ordShipFirstName = createString("ordShipFirstName");

    public final StringPath ordShipFreeShipping = createString("ordShipFreeShipping");

    public final StringPath ordShipLastName = createString("ordShipLastName");

    public final StringPath ordShipMiddleName = createString("ordShipMiddleName");

    public final StringPath ordShipNote = createString("ordShipNote");

    public final NumberPath<Long> ordShipPrice = createNumber("ordShipPrice", Long.class);

    public final NumberPath<Long> ordShipTax = createNumber("ordShipTax", Long.class);

    public final StringPath ordShipTrackNum = createString("ordShipTrackNum");

    public final NumberPath<Short> ordShipZipFour = createNumber("ordShipZipFour", Short.class);

    public final NumberPath<Long> patientAddressSeq = createNumber("patientAddressSeq", Long.class);

    public final DateTimePath<java.sql.Timestamp> patientPreauthDatetime = createDateTime("patientPreauthDatetime", java.sql.Timestamp.class);

    public final StringPath patientPreauthNote = createString("patientPreauthNote");

    public final StringPath patientPreauthStatus = createString("patientPreauthStatus");

    public final NumberPath<Long> patientPreauthUser = createNumber("patientPreauthUser", Long.class);

    public final NumberPath<Long> patientShipAddressSeq = createNumber("patientShipAddressSeq", Long.class);

    public final NumberPath<Long> paymentAmount = createNumber("paymentAmount", Long.class);

    public final NumberPath<Long> paymentCardSeqNum = createNumber("paymentCardSeqNum", Long.class);

    public final NumberPath<Integer> paymentMethodId = createNumber("paymentMethodId", Integer.class);

    public final StringPath paymentNumber = createString("paymentNumber");

    public final SimplePath<byte[]> rowversion = createSimple("rowversion", byte[].class);

    public final NumberPath<Byte> shipCheck = createNumber("shipCheck", Byte.class);

    public final NumberPath<Integer> shipMethodId = createNumber("shipMethodId", Integer.class);

    public final StringPath shipperMethod = createString("shipperMethod");

    public final DateTimePath<java.sql.Timestamp> shipperPickupDatetime = createDateTime("shipperPickupDatetime", java.sql.Timestamp.class);

    public final NumberPath<Long> shipWeight = createNumber("shipWeight", Long.class);

    public final NumberPath<Byte> signatureRequired = createNumber("signatureRequired", Byte.class);

    public final NumberPath<Byte> smCheck = createNumber("smCheck", Byte.class);

    public QOrderBillship(String variable) {
        super(OrderBillship.class, forVariable(variable), "dbo", "order_billship");
        addMetadata();
    }

    public QOrderBillship(String variable, String schema, String table) {
        super(OrderBillship.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QOrderBillship(String variable, String schema) {
        super(OrderBillship.class, forVariable(variable), schema, "order_billship");
        addMetadata();
    }

    public QOrderBillship(Path<? extends OrderBillship> path) {
        super(path.getType(), path.getMetadata(), "dbo", "order_billship");
        addMetadata();
    }

    public QOrderBillship(PathMetadata metadata) {
        super(OrderBillship.class, metadata, "dbo", "order_billship");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(addressValidated, ColumnMetadata.named("address_validated").withIndex(42).ofType(Types.CHAR).withSize(1));
        addMetadata(bankRoutingId, ColumnMetadata.named("bank_routing_id").withIndex(8).ofType(Types.VARCHAR).withSize(60));
        addMetadata(checkAccount, ColumnMetadata.named("check_account").withIndex(9).ofType(Types.VARCHAR).withSize(60));
        addMetadata(couponNum, ColumnMetadata.named("coupon_num").withIndex(3).ofType(Types.NUMERIC).withSize(5));
        addMetadata(cszZipNum, ColumnMetadata.named("csz_zip_num").withIndex(10).ofType(Types.NUMERIC).withSize(16));
        addMetadata(estimatedDeliveryDate, ColumnMetadata.named("estimated_delivery_date").withIndex(45).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(estShipTax, ColumnMetadata.named("est_ship_tax").withIndex(44).ofType(Types.NUMERIC).withSize(12));
        addMetadata(familyId, ColumnMetadata.named("family_id").withIndex(35).ofType(Types.VARCHAR).withSize(60));
        addMetadata(forceShipAddress, ColumnMetadata.named("force_ship_address").withIndex(33).ofType(Types.CHAR).withSize(1));
        addMetadata(notified, ColumnMetadata.named("notified").withIndex(32).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(odcFillLocation, ColumnMetadata.named("odc_fill_location").withIndex(46).ofType(Types.VARCHAR).withSize(35));
        addMetadata(odcOrderType, ColumnMetadata.named("odc_order_type").withIndex(47).ofType(Types.VARCHAR).withSize(10));
        addMetadata(orderDispensed, ColumnMetadata.named("order_dispensed").withIndex(48).ofType(Types.CHAR).withSize(1));
        addMetadata(orderNum, ColumnMetadata.named("order_num").withIndex(1).ofType(Types.NUMERIC).withSize(30).notNull());
        addMetadata(orderPaymentOwner, ColumnMetadata.named("order_payment_owner").withIndex(12).ofType(Types.NUMERIC).withSize(20));
        addMetadata(ordShipAccountNumber, ColumnMetadata.named("ord_ship_account_number").withIndex(28).ofType(Types.VARCHAR).withSize(60));
        addMetadata(ordShipAddress, ColumnMetadata.named("ord_ship_address").withIndex(11).ofType(Types.VARCHAR).withSize(60));
        addMetadata(ordShipAddress2, ColumnMetadata.named("ord_ship_address2").withIndex(13).ofType(Types.VARCHAR).withSize(60));
        addMetadata(ordShipAddress3, ColumnMetadata.named("ord_ship_address3").withIndex(17).ofType(Types.VARCHAR).withSize(60));
        addMetadata(ordShipCost, ColumnMetadata.named("ord_ship_cost").withIndex(24).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ordShipDate, ColumnMetadata.named("ord_ship_date").withIndex(19).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(ordShipFirstName, ColumnMetadata.named("ord_ship_first_name").withIndex(20).ofType(Types.VARCHAR).withSize(60));
        addMetadata(ordShipFreeShipping, ColumnMetadata.named("ord_ship_free_shipping").withIndex(23).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(ordShipLastName, ColumnMetadata.named("ord_ship_last_name").withIndex(22).ofType(Types.VARCHAR).withSize(60));
        addMetadata(ordShipMiddleName, ColumnMetadata.named("ord_ship_middle_name").withIndex(21).ofType(Types.VARCHAR).withSize(60));
        addMetadata(ordShipNote, ColumnMetadata.named("ord_ship_note").withIndex(25).ofType(Types.VARCHAR).withSize(1000));
        addMetadata(ordShipPrice, ColumnMetadata.named("ord_ship_price").withIndex(15).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ordShipTax, ColumnMetadata.named("ord_ship_tax").withIndex(16).ofType(Types.NUMERIC).withSize(12));
        addMetadata(ordShipTrackNum, ColumnMetadata.named("ord_ship_track_num").withIndex(18).ofType(Types.VARCHAR).withSize(60));
        addMetadata(ordShipZipFour, ColumnMetadata.named("ord_ship_zip_four").withIndex(14).ofType(Types.NUMERIC).withSize(4));
        addMetadata(patientAddressSeq, ColumnMetadata.named("patient_address_seq").withIndex(29).ofType(Types.NUMERIC).withSize(16));
        addMetadata(patientPreauthDatetime, ColumnMetadata.named("patient_preauth_datetime").withIndex(26).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(patientPreauthNote, ColumnMetadata.named("patient_preauth_note").withIndex(37).ofType(Types.VARCHAR).withSize(255));
        addMetadata(patientPreauthStatus, ColumnMetadata.named("patient_preauth_status").withIndex(36).ofType(Types.VARCHAR).withSize(60));
        addMetadata(patientPreauthUser, ColumnMetadata.named("patient_preauth_user").withIndex(38).ofType(Types.NUMERIC).withSize(16));
        addMetadata(patientShipAddressSeq, ColumnMetadata.named("patient_ship_address_seq").withIndex(30).ofType(Types.NUMERIC).withSize(16));
        addMetadata(paymentAmount, ColumnMetadata.named("payment_amount").withIndex(6).ofType(Types.NUMERIC).withSize(12));
        addMetadata(paymentCardSeqNum, ColumnMetadata.named("payment_card_seq_num").withIndex(4).ofType(Types.NUMERIC).withSize(16));
        addMetadata(paymentMethodId, ColumnMetadata.named("payment_method_id").withIndex(2).ofType(Types.NUMERIC).withSize(5));
        addMetadata(paymentNumber, ColumnMetadata.named("payment_number").withIndex(7).ofType(Types.VARCHAR).withSize(60));
        addMetadata(rowversion, ColumnMetadata.named("rowversion").withIndex(43).ofType(Types.BINARY).withSize(8).notNull());
        addMetadata(shipCheck, ColumnMetadata.named("ship_check").withIndex(40).ofType(Types.NUMERIC).withSize(1));
        addMetadata(shipMethodId, ColumnMetadata.named("ship_method_id").withIndex(5).ofType(Types.NUMERIC).withSize(5));
        addMetadata(shipperMethod, ColumnMetadata.named("shipper_method").withIndex(39).ofType(Types.VARCHAR).withSize(60));
        addMetadata(shipperPickupDatetime, ColumnMetadata.named("shipper_pickup_datetime").withIndex(27).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(shipWeight, ColumnMetadata.named("ship_weight").withIndex(34).ofType(Types.NUMERIC).withSize(12));
        addMetadata(signatureRequired, ColumnMetadata.named("signature_required").withIndex(31).ofType(Types.NUMERIC).withSize(1));
        addMetadata(smCheck, ColumnMetadata.named("sm_check").withIndex(41).ofType(Types.NUMERIC).withSize(1));
    }

}

