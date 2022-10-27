package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.TradingPartnerGeneral;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QTradingPartnerGeneral is a Querydsl query type for TradingPartnerGeneral
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QTradingPartnerGeneral extends com.querydsl.sql.RelationalPathBase<TradingPartnerGeneral> {

    private static final long serialVersionUID = 2121733735;

    public static final QTradingPartnerGeneral tradingPartnerGeneral = new QTradingPartnerGeneral("trading_partner_general");

    public final NumberPath<Long> accountNum = createNumber("accountNum", Long.class);

    public final StringPath catalogPriceType = createString("catalogPriceType");

    public final StringPath commonDoctor = createString("commonDoctor");

    public final StringPath conversionLink = createString("conversionLink");

    public final StringPath daw12 = createString("daw12");

    public final NumberPath<Integer> doctorSpecialityNum = createNumber("doctorSpecialityNum", Integer.class);

    public final StringPath drType = createString("drType");

    public final StringPath externalLink = createString("externalLink");

    public final StringPath externalRequestMode = createString("externalRequestMode");

    public final StringPath familyMode = createString("familyMode");

    public final StringPath faxImagePath = createString("faxImagePath");

    public final StringPath generalStatusCode = createString("generalStatusCode");

    public final NumberPath<Long> hubAffiliate = createNumber("hubAffiliate", Long.class);

    public final StringPath inPatientService = createString("inPatientService");

    public final StringPath inventoryAreaVerification = createString("inventoryAreaVerification");

    public final StringPath inventoryVerification = createString("inventoryVerification");

    public final StringPath is24hr = createString("is24hr");

    public final StringPath masterPhysicianIndex = createString("masterPhysicianIndex");

    public final StringPath noDrfax = createString("noDrfax");

    public final StringPath overrideDataValidation = createString("overrideDataValidation");

    public final StringPath overrideMandatoryFields = createString("overrideMandatoryFields");

    public final StringPath picInitials = createString("picInitials");

    public final StringPath picLicense = createString("picLicense");

    public final StringPath picName = createString("picName");

    public final NumberPath<Integer> picStateNum = createNumber("picStateNum", Integer.class);

    public final StringPath pmpOutputPath = createString("pmpOutputPath");

    public final StringPath pmpTemplatePath = createString("pmpTemplatePath");

    public final NumberPath<Integer> regionNum = createNumber("regionNum", Integer.class);

    public final StringPath rxImagePath = createString("rxImagePath");

    public final NumberPath<Integer> serialNum = createNumber("serialNum", Integer.class);

    public final NumberPath<Integer> serverNum = createNumber("serverNum", Integer.class);

    public final StringPath sourceHost = createString("sourceHost");

    public final StringPath sourceUser = createString("sourceUser");

    public final StringPath sup810 = createString("sup810");

    public final StringPath sup832Group = createString("sup832Group");

    public final StringPath supAccount = createString("supAccount");

    public final NumberPath<Integer> supLeadTime = createNumber("supLeadTime", Integer.class);

    public final StringPath supReceiverId = createString("supReceiverId");

    public final StringPath tpAlias = createString("tpAlias");

    public final StringPath tpCode = createString("tpCode");

    public final NumberPath<Integer> tpCriticalLevel = createNumber("tpCriticalLevel", Integer.class);

    public final NumberPath<Short> tpDefaultVersionId = createNumber("tpDefaultVersionId", Short.class);

    public final StringPath tpEmailAddress = createString("tpEmailAddress");

    public final StringPath tpFirstName = createString("tpFirstName");

    public final StringPath tpLastName = createString("tpLastName");

    public final StringPath tpMiddleName = createString("tpMiddleName");

    public final StringPath tpName = createString("tpName");

    public final StringPath tpPassword = createString("tpPassword");

    public final StringPath tpRoutingId = createString("tpRoutingId");

    public final StringPath tpRoutingName = createString("tpRoutingName");

    public final NumberPath<Integer> tpWarningLevel = createNumber("tpWarningLevel", Integer.class);

    public final StringPath tpWebAddress = createString("tpWebAddress");

    public final StringPath tpXmissionCode = createString("tpXmissionCode");

    public final NumberPath<Long> tradingPartnerNum = createNumber("tradingPartnerNum", Long.class);

    public final NumberPath<Integer> tradingPartnerTypeId = createNumber("tradingPartnerTypeId", Integer.class);

    public final StringPath translationMode = createString("translationMode");

    public final NumberPath<Byte> turnaroundDays = createNumber("turnaroundDays", Byte.class);

    public final StringPath useCatalogPrice = createString("useCatalogPrice");

    public final StringPath useLocationBillship = createString("useLocationBillship");

    public final StringPath usesInventory = createString("usesInventory");

    public final NumberPath<Byte> wlNumLabels = createNumber("wlNumLabels", Byte.class);

    public final StringPath wlVendorCode = createString("wlVendorCode");

    public final StringPath workflowAutoNotify = createString("workflowAutoNotify");

    public QTradingPartnerGeneral(String variable) {
        super(TradingPartnerGeneral.class, forVariable(variable), "dbo", "trading_partner_general");
        addMetadata();
    }

    public QTradingPartnerGeneral(String variable, String schema, String table) {
        super(TradingPartnerGeneral.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTradingPartnerGeneral(String variable, String schema) {
        super(TradingPartnerGeneral.class, forVariable(variable), schema, "trading_partner_general");
        addMetadata();
    }

    public QTradingPartnerGeneral(Path<? extends TradingPartnerGeneral> path) {
        super(path.getType(), path.getMetadata(), "dbo", "trading_partner_general");
        addMetadata();
    }

    public QTradingPartnerGeneral(PathMetadata metadata) {
        super(TradingPartnerGeneral.class, metadata, "dbo", "trading_partner_general");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(accountNum, ColumnMetadata.named("account_num").withIndex(5).ofType(Types.NUMERIC).withSize(16).notNull());
        addMetadata(catalogPriceType, ColumnMetadata.named("catalog_price_type").withIndex(51).ofType(Types.VARCHAR).withSize(10));
        addMetadata(commonDoctor, ColumnMetadata.named("common_doctor").withIndex(22).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(conversionLink, ColumnMetadata.named("conversion_link").withIndex(33).ofType(Types.VARCHAR).withSize(60));
        addMetadata(daw12, ColumnMetadata.named("daw12").withIndex(45).ofType(Types.CHAR).withSize(1));
        addMetadata(doctorSpecialityNum, ColumnMetadata.named("doctor_speciality_num").withIndex(3).ofType(Types.NUMERIC).withSize(5));
        addMetadata(drType, ColumnMetadata.named("dr_type").withIndex(46).ofType(Types.CHAR).withSize(1));
        addMetadata(externalLink, ColumnMetadata.named("external_link").withIndex(34).ofType(Types.VARCHAR).withSize(60));
        addMetadata(externalRequestMode, ColumnMetadata.named("external_request_mode").withIndex(55).ofType(Types.CHAR).withSize(1));
        addMetadata(familyMode, ColumnMetadata.named("family_mode").withIndex(49).ofType(Types.CHAR).withSize(1));
        addMetadata(faxImagePath, ColumnMetadata.named("fax_image_path").withIndex(50).ofType(Types.VARCHAR).withSize(80));
        addMetadata(generalStatusCode, ColumnMetadata.named("general_status_code").withIndex(7).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(hubAffiliate, ColumnMetadata.named("hub_affiliate").withIndex(18).ofType(Types.NUMERIC).withSize(16));
        addMetadata(inPatientService, ColumnMetadata.named("in_patient_service").withIndex(47).ofType(Types.CHAR).withSize(1));
        addMetadata(inventoryAreaVerification, ColumnMetadata.named("inventory_area_verification").withIndex(40).ofType(Types.CHAR).withSize(1));
        addMetadata(inventoryVerification, ColumnMetadata.named("inventory_verification").withIndex(17).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(is24hr, ColumnMetadata.named("is24hr").withIndex(19).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(masterPhysicianIndex, ColumnMetadata.named("master_physician_index").withIndex(32).ofType(Types.VARCHAR).withSize(60));
        addMetadata(noDrfax, ColumnMetadata.named("no_drfax").withIndex(42).ofType(Types.CHAR).withSize(1));
        addMetadata(overrideDataValidation, ColumnMetadata.named("override_data_validation").withIndex(21).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(overrideMandatoryFields, ColumnMetadata.named("override_mandatory_fields").withIndex(20).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(picInitials, ColumnMetadata.named("pic_initials").withIndex(38).ofType(Types.VARCHAR).withSize(7));
        addMetadata(picLicense, ColumnMetadata.named("pic_license").withIndex(36).ofType(Types.VARCHAR).withSize(60));
        addMetadata(picName, ColumnMetadata.named("pic_name").withIndex(35).ofType(Types.VARCHAR).withSize(80));
        addMetadata(picStateNum, ColumnMetadata.named("pic_state_num").withIndex(37).ofType(Types.NUMERIC).withSize(5));
        addMetadata(pmpOutputPath, ColumnMetadata.named("pmp_output_path").withIndex(63).ofType(Types.VARCHAR).withSize(500));
        addMetadata(pmpTemplatePath, ColumnMetadata.named("pmp_template_path").withIndex(62).ofType(Types.VARCHAR).withSize(500));
        addMetadata(regionNum, ColumnMetadata.named("region_num").withIndex(4).ofType(Types.NUMERIC).withSize(5).notNull());
        addMetadata(rxImagePath, ColumnMetadata.named("rx_image_path").withIndex(44).ofType(Types.VARCHAR).withSize(80));
        addMetadata(serialNum, ColumnMetadata.named("serial_num").withIndex(25).ofType(Types.NUMERIC).withSize(5));
        addMetadata(serverNum, ColumnMetadata.named("server_num").withIndex(24).ofType(Types.NUMERIC).withSize(5));
        addMetadata(sourceHost, ColumnMetadata.named("source_host").withIndex(58).ofType(Types.VARCHAR).withSize(60));
        addMetadata(sourceUser, ColumnMetadata.named("source_user").withIndex(59).ofType(Types.VARCHAR).withSize(60));
        addMetadata(sup810, ColumnMetadata.named("sup_810").withIndex(57).ofType(Types.CHAR).withSize(1));
        addMetadata(sup832Group, ColumnMetadata.named("sup_832_group").withIndex(60).ofType(Types.VARCHAR).withSize(80));
        addMetadata(supAccount, ColumnMetadata.named("sup_account").withIndex(61).ofType(Types.VARCHAR).withSize(35));
        addMetadata(supLeadTime, ColumnMetadata.named("sup_lead_time").withIndex(56).ofType(Types.NUMERIC).withSize(5));
        addMetadata(supReceiverId, ColumnMetadata.named("sup_receiver_id").withIndex(39).ofType(Types.VARCHAR).withSize(35));
        addMetadata(tpAlias, ColumnMetadata.named("tp_alias").withIndex(54).ofType(Types.VARCHAR).withSize(60));
        addMetadata(tpCode, ColumnMetadata.named("tp_code").withIndex(15).ofType(Types.VARCHAR).withSize(35));
        addMetadata(tpCriticalLevel, ColumnMetadata.named("tp_critical_level").withIndex(53).ofType(Types.NUMERIC).withSize(5));
        addMetadata(tpDefaultVersionId, ColumnMetadata.named("tp_default_version_id").withIndex(16).ofType(Types.NUMERIC).withSize(3));
        addMetadata(tpEmailAddress, ColumnMetadata.named("tp_email_address").withIndex(8).ofType(Types.VARCHAR).withSize(80));
        addMetadata(tpFirstName, ColumnMetadata.named("tp_first_name").withIndex(13).ofType(Types.VARCHAR).withSize(60));
        addMetadata(tpLastName, ColumnMetadata.named("tp_last_name").withIndex(14).ofType(Types.VARCHAR).withSize(60));
        addMetadata(tpMiddleName, ColumnMetadata.named("tp_middle_name").withIndex(9).ofType(Types.VARCHAR).withSize(60));
        addMetadata(tpName, ColumnMetadata.named("tp_name").withIndex(10).ofType(Types.VARCHAR).withSize(60));
        addMetadata(tpPassword, ColumnMetadata.named("tp_password").withIndex(12).ofType(Types.VARCHAR).withSize(35));
        addMetadata(tpRoutingId, ColumnMetadata.named("tp_routing_id").withIndex(11).ofType(Types.VARCHAR).withSize(35));
        addMetadata(tpRoutingName, ColumnMetadata.named("tp_routing_name").withIndex(6).ofType(Types.VARCHAR).withSize(35));
        addMetadata(tpWarningLevel, ColumnMetadata.named("tp_warning_level").withIndex(52).ofType(Types.NUMERIC).withSize(5));
        addMetadata(tpWebAddress, ColumnMetadata.named("tp_web_address").withIndex(28).ofType(Types.VARCHAR).withSize(255));
        addMetadata(tpXmissionCode, ColumnMetadata.named("tp_xmission_code").withIndex(23).ofType(Types.VARCHAR).withSize(6));
        addMetadata(tradingPartnerNum, ColumnMetadata.named("trading_partner_num").withIndex(1).ofType(Types.NUMERIC).withSize(16).notNull());
        addMetadata(tradingPartnerTypeId, ColumnMetadata.named("trading_partner_type_id").withIndex(2).ofType(Types.NUMERIC).withSize(5).notNull());
        addMetadata(translationMode, ColumnMetadata.named("translation_mode").withIndex(30).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(turnaroundDays, ColumnMetadata.named("turnaround_days").withIndex(29).ofType(Types.NUMERIC).withSize(2));
        addMetadata(useCatalogPrice, ColumnMetadata.named("use_catalog_price").withIndex(48).ofType(Types.CHAR).withSize(1));
        addMetadata(useLocationBillship, ColumnMetadata.named("use_location_billship").withIndex(41).ofType(Types.CHAR).withSize(1));
        addMetadata(usesInventory, ColumnMetadata.named("uses_inventory").withIndex(43).ofType(Types.CHAR).withSize(1));
        addMetadata(wlNumLabels, ColumnMetadata.named("wl_num_labels").withIndex(27).ofType(Types.NUMERIC).withSize(2));
        addMetadata(wlVendorCode, ColumnMetadata.named("wl_vendor_code").withIndex(26).ofType(Types.VARCHAR).withSize(60));
        addMetadata(workflowAutoNotify, ColumnMetadata.named("workflow_auto_notify").withIndex(31).ofType(Types.CHAR).withSize(1).notNull());
    }

}

