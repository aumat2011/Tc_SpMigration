package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.RxDefaults;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QRxDefaults is a Querydsl query type for RxDefaults
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QRxDefaults extends com.querydsl.sql.RelationalPathBase<RxDefaults> {

    private static final long serialVersionUID = -1030753646;

    public static final QRxDefaults rxDefaults = new QRxDefaults("rx_defaults");

    public final StringPath advancePrecheck = createString("advancePrecheck");

    public final StringPath allowConsolidate = createString("allowConsolidate");

    public final StringPath autoFillMode = createString("autoFillMode");

    public final StringPath autorefillCc = createString("autorefillCc");

    public final NumberPath<Integer> autoRefillerDelta = createNumber("autoRefillerDelta", Integer.class);

    public final StringPath autosendDrfax = createString("autosendDrfax");

    public final NumberPath<Long> brandLoss = createNumber("brandLoss", Long.class);

    public final StringPath checkDupes = createString("checkDupes");

    public final StringPath checkNarcoticRefills = createString("checkNarcoticRefills");

    public final NumberPath<Long> copayDifferential = createNumber("copayDifferential", Long.class);

    public final NumberPath<Integer> dawCode = createNumber("dawCode", Integer.class);

    public final NumberPath<Integer> daysPastExpiration = createNumber("daysPastExpiration", Integer.class);

    public final StringPath ecsLogging = createString("ecsLogging");

    public final StringPath forceProductSelection = createString("forceProductSelection");

    public final StringPath generalStatusCode = createString("generalStatusCode");

    public final StringPath genericLinkType = createString("genericLinkType");

    public final NumberPath<Long> genericLoss = createNumber("genericLoss", Long.class);

    public final NumberPath<Byte> hrmAge = createNumber("hrmAge", Byte.class);

    public final StringPath icdMode = createString("icdMode");

    public final StringPath includeGpi = createString("includeGpi");

    public final StringPath includeTeecode = createString("includeTeecode");

    public final NumberPath<Short> maxPvDays = createNumber("maxPvDays", Short.class);

    public final NumberPath<Integer> maxSigLabel = createNumber("maxSigLabel", Integer.class);

    public final NumberPath<Integer> noteTypeId = createNumber("noteTypeId", Integer.class);

    public final NumberPath<Integer> numRefills = createNumber("numRefills", Integer.class);

    public final NumberPath<Integer> originationNum = createNumber("originationNum", Integer.class);

    public final StringPath otcAsRxPostcheck = createString("otcAsRxPostcheck");

    public final StringPath otcAsRxPrecheck = createString("otcAsRxPrecheck");

    public final NumberPath<Byte> patientLocationId = createNumber("patientLocationId", Byte.class);

    public final StringPath precheckAll = createString("precheckAll");

    public final StringPath pwoPostcheck = createString("pwoPostcheck");

    public final StringPath pwoPrecheck = createString("pwoPrecheck");

    public final NumberPath<Integer> refillByUtilization = createNumber("refillByUtilization", Integer.class);

    public final StringPath rxDefAutoRefill = createString("rxDefAutoRefill");

    public final NumberPath<Integer> rxHoldReasonNum = createNumber("rxHoldReasonNum", Integer.class);

    public final NumberPath<Byte> rxStatusCodeNum = createNumber("rxStatusCodeNum", Byte.class);

    public final NumberPath<Integer> showExpiration = createNumber("showExpiration", Integer.class);

    public final StringPath showQuantity = createString("showQuantity");

    public final NumberPath<Integer> specialityDelta = createNumber("specialityDelta", Integer.class);

    public QRxDefaults(String variable) {
        super(RxDefaults.class, forVariable(variable), "dbo", "rx_defaults");
        addMetadata();
    }

    public QRxDefaults(String variable, String schema, String table) {
        super(RxDefaults.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QRxDefaults(String variable, String schema) {
        super(RxDefaults.class, forVariable(variable), schema, "rx_defaults");
        addMetadata();
    }

    public QRxDefaults(Path<? extends RxDefaults> path) {
        super(path.getType(), path.getMetadata(), "dbo", "rx_defaults");
        addMetadata();
    }

    public QRxDefaults(PathMetadata metadata) {
        super(RxDefaults.class, metadata, "dbo", "rx_defaults");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(advancePrecheck, ColumnMetadata.named("advance_precheck").withIndex(32).ofType(Types.CHAR).withSize(1));
        addMetadata(allowConsolidate, ColumnMetadata.named("allow_consolidate").withIndex(34).ofType(Types.CHAR).withSize(1));
        addMetadata(autoFillMode, ColumnMetadata.named("auto_fill_mode").withIndex(16).ofType(Types.CHAR).withSize(1));
        addMetadata(autorefillCc, ColumnMetadata.named("autorefill_cc").withIndex(28).ofType(Types.CHAR).withSize(1));
        addMetadata(autoRefillerDelta, ColumnMetadata.named("auto_refiller_delta").withIndex(12).ofType(Types.NUMERIC).withSize(5));
        addMetadata(autosendDrfax, ColumnMetadata.named("autosend_drfax").withIndex(15).ofType(Types.CHAR).withSize(1));
        addMetadata(brandLoss, ColumnMetadata.named("brand_loss").withIndex(25).ofType(Types.NUMERIC).withSize(15));
        addMetadata(checkDupes, ColumnMetadata.named("check_dupes").withIndex(17).ofType(Types.CHAR).withSize(1));
        addMetadata(checkNarcoticRefills, ColumnMetadata.named("check_narcotic_refills").withIndex(35).ofType(Types.CHAR).withSize(1));
        addMetadata(copayDifferential, ColumnMetadata.named("copay_differential").withIndex(26).ofType(Types.NUMERIC).withSize(12));
        addMetadata(dawCode, ColumnMetadata.named("daw_code").withIndex(7).ofType(Types.NUMERIC).withSize(5));
        addMetadata(daysPastExpiration, ColumnMetadata.named("days_past_expiration").withIndex(9).ofType(Types.NUMERIC).withSize(5));
        addMetadata(ecsLogging, ColumnMetadata.named("ecs_logging").withIndex(20).ofType(Types.CHAR).withSize(1));
        addMetadata(forceProductSelection, ColumnMetadata.named("force_product_selection").withIndex(27).ofType(Types.CHAR).withSize(1));
        addMetadata(generalStatusCode, ColumnMetadata.named("general_status_code").withIndex(1).ofType(Types.CHAR).withSize(1));
        addMetadata(genericLinkType, ColumnMetadata.named("generic_link_type").withIndex(21).ofType(Types.CHAR).withSize(1));
        addMetadata(genericLoss, ColumnMetadata.named("generic_loss").withIndex(24).ofType(Types.NUMERIC).withSize(15));
        addMetadata(hrmAge, ColumnMetadata.named("hrm_age").withIndex(36).ofType(Types.NUMERIC).withSize(2));
        addMetadata(icdMode, ColumnMetadata.named("icd_mode").withIndex(38).ofType(Types.VARCHAR).withSize(10));
        addMetadata(includeGpi, ColumnMetadata.named("include_gpi").withIndex(19).ofType(Types.CHAR).withSize(1));
        addMetadata(includeTeecode, ColumnMetadata.named("include_teecode").withIndex(33).ofType(Types.CHAR).withSize(1));
        addMetadata(maxPvDays, ColumnMetadata.named("max_pv_days").withIndex(39).ofType(Types.NUMERIC).withSize(3));
        addMetadata(maxSigLabel, ColumnMetadata.named("max_sig_label").withIndex(23).ofType(Types.NUMERIC).withSize(5));
        addMetadata(noteTypeId, ColumnMetadata.named("note_type_id").withIndex(5).ofType(Types.NUMERIC).withSize(5));
        addMetadata(numRefills, ColumnMetadata.named("num_refills").withIndex(11).ofType(Types.NUMERIC).withSize(5));
        addMetadata(originationNum, ColumnMetadata.named("origination_num").withIndex(6).ofType(Types.NUMERIC).withSize(5));
        addMetadata(otcAsRxPostcheck, ColumnMetadata.named("otc_as_rx_postcheck").withIndex(14).ofType(Types.CHAR).withSize(1));
        addMetadata(otcAsRxPrecheck, ColumnMetadata.named("otc_as_rx_precheck").withIndex(13).ofType(Types.CHAR).withSize(1));
        addMetadata(patientLocationId, ColumnMetadata.named("patient_location_id").withIndex(4).ofType(Types.NUMERIC).withSize(2));
        addMetadata(precheckAll, ColumnMetadata.named("precheck_all").withIndex(37).ofType(Types.CHAR).withSize(1));
        addMetadata(pwoPostcheck, ColumnMetadata.named("pwo_postcheck").withIndex(30).ofType(Types.CHAR).withSize(1));
        addMetadata(pwoPrecheck, ColumnMetadata.named("pwo_precheck").withIndex(29).ofType(Types.CHAR).withSize(1));
        addMetadata(refillByUtilization, ColumnMetadata.named("refill_by_utilization").withIndex(10).ofType(Types.NUMERIC).withSize(5));
        addMetadata(rxDefAutoRefill, ColumnMetadata.named("rx_def_auto_refill").withIndex(8).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(rxHoldReasonNum, ColumnMetadata.named("rx_hold_reason_num").withIndex(3).ofType(Types.NUMERIC).withSize(5));
        addMetadata(rxStatusCodeNum, ColumnMetadata.named("rx_status_code_num").withIndex(2).ofType(Types.NUMERIC).withSize(2));
        addMetadata(showExpiration, ColumnMetadata.named("show_expiration").withIndex(18).ofType(Types.NUMERIC).withSize(5));
        addMetadata(showQuantity, ColumnMetadata.named("show_quantity").withIndex(31).ofType(Types.CHAR).withSize(1));
        addMetadata(specialityDelta, ColumnMetadata.named("speciality_delta").withIndex(22).ofType(Types.NUMERIC).withSize(5));
    }

}

