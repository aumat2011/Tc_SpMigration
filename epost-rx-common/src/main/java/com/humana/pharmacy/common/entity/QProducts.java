package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.Products;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QProducts is a Querydsl query type for Products
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QProducts extends com.querydsl.sql.RelationalPathBase<Products> {

    private static final long serialVersionUID = -1820249154;

    public static final QProducts products = new QProducts("products");

    public final StringPath autotestfs = createString("autotestfs");

    public final StringPath backorderProduct = createString("backorderProduct");

    public final NumberPath<Integer> compoundAdminRoute = createNumber("compoundAdminRoute", Integer.class);

    public final StringPath compoundFormCode = createString("compoundFormCode");

    public final StringPath compoundType = createString("compoundType");

    public final NumberPath<Byte> compoundUnitForm = createNumber("compoundUnitForm", Byte.class);

    public final StringPath conversionLink = createString("conversionLink");

    public final StringPath csPotencyUnitCode = createString("csPotencyUnitCode");

    public final StringPath dataCollection = createString("dataCollection");

    public final DateTimePath<java.sql.Timestamp> dateAdded = createDateTime("dateAdded", java.sql.Timestamp.class);

    public final StringPath dmeProduct = createString("dmeProduct");

    public final StringPath dosageFormId = createString("dosageFormId");

    public final StringPath drugdbInactiveDate = createString("drugdbInactiveDate");

    public final StringPath drugMaker = createString("drugMaker");

    public final StringPath hrmActive = createString("hrmActive");

    public final StringPath hrmEligible = createString("hrmEligible");

    public final StringPath hrmProduct = createString("hrmProduct");

    public final DateTimePath<java.sql.Timestamp> lastUpdate = createDateTime("lastUpdate", java.sql.Timestamp.class);

    public final StringPath manallocProduct = createString("manallocProduct");

    public final NumberPath<Long> minimumProductMargin = createNumber("minimumProductMargin", Long.class);

    public final StringPath multiSourceCode = createString("multiSourceCode");

    public final StringPath mybProduct = createString("mybProduct");

    public final NumberPath<Integer> packageQuantity = createNumber("packageQuantity", Integer.class);

    public final StringPath potencyUnitCode = createString("potencyUnitCode");

    public final StringPath prodActive = createString("prodActive");

    public final StringPath prodAdminRoute = createString("prodAdminRoute");

    public final StringPath prodAttributes = createString("prodAttributes");

    public final NumberPath<Long> prodAwpDefaultprice = createNumber("prodAwpDefaultprice", Long.class);

    public final NumberPath<Long> prodAwpDefaultunitprice = createNumber("prodAwpDefaultunitprice", Long.class);

    public final StringPath prodCompound = createString("prodCompound");

    public final StringPath prodConsolidate = createString("prodConsolidate");

    public final NumberPath<Byte> prodDea = createNumber("prodDea", Byte.class);

    public final NumberPath<Integer> prodDefaultDays = createNumber("prodDefaultDays", Integer.class);

    public final NumberPath<Long> prodDefaultDrugWacunit = createNumber("prodDefaultDrugWacunit", Long.class);

    public final NumberPath<Integer> prodDefaultPackqty = createNumber("prodDefaultPackqty", Integer.class);

    public final StringPath prodDefaultPrescribeReason = createString("prodDefaultPrescribeReason");

    public final NumberPath<Integer> prodDefaultQuantity = createNumber("prodDefaultQuantity", Integer.class);

    public final StringPath prodDefaultSig = createString("prodDefaultSig");

    public final NumberPath<Long> prodDefPackageSize = createNumber("prodDefPackageSize", Long.class);

    public final NumberPath<Long> prodDrugAwpPrice = createNumber("prodDrugAwpPrice", Long.class);

    public final NumberPath<Long> prodDrugAwpUnitPrice = createNumber("prodDrugAwpUnitPrice", Long.class);

    public final NumberPath<Integer> prodDrugWacunit = createNumber("prodDrugWacunit", Integer.class);

    public final StringPath prodFormattedId = createString("prodFormattedId");

    public final StringPath prodFormTypeCode = createString("prodFormTypeCode");

    public final StringPath prodGenericRefNum = createString("prodGenericRefNum");

    public final NumberPath<Integer> prodHandlingSeq1 = createNumber("prodHandlingSeq1", Integer.class);

    public final NumberPath<Integer> prodHandlingSeq2 = createNumber("prodHandlingSeq2", Integer.class);

    public final NumberPath<Integer> prodHandlingSeq3 = createNumber("prodHandlingSeq3", Integer.class);

    public final NumberPath<Integer> prodHandlingSeq4 = createNumber("prodHandlingSeq4", Integer.class);

    public final NumberPath<Long> prodId = createNumber("prodId", Long.class);

    public final StringPath prodName = createString("prodName");

    public final StringPath prodNameDesc = createString("prodNameDesc");

    public final StringPath prodNameType = createString("prodNameType");

    public final StringPath prodNumber = createString("prodNumber");

    public final NumberPath<Long> prodPackageSize = createNumber("prodPackageSize", Long.class);

    public final StringPath prodPacksizeUnitcode = createString("prodPacksizeUnitcode");

    public final StringPath prodReferenceNum = createString("prodReferenceNum");

    public final StringPath prodRxRequired = createString("prodRxRequired");

    public final StringPath prodStrength = createString("prodStrength");

    public final StringPath prodStrengthUnit = createString("prodStrengthUnit");

    public final StringPath prodTeeCode = createString("prodTeeCode");

    public final NumberPath<Integer> prodTypeNum = createNumber("prodTypeNum", Integer.class);

    public final StringPath productColor = createString("productColor");

    public final StringPath productImprint = createString("productImprint");

    public final StringPath productServiceId = createString("productServiceId");

    public final StringPath productShape = createString("productShape");

    public final StringPath prodUnitOfUse = createString("prodUnitOfUse");

    public final NumberPath<Integer> qtyPerLabel = createNumber("qtyPerLabel", Integer.class);

    public final StringPath reviewProduct = createString("reviewProduct");

    public final SimplePath<byte[]> rowversion = createSimple("rowversion", byte[].class);

    public final NumberPath<java.math.BigInteger> sigisId = createNumber("sigisId", java.math.BigInteger.class);

    public final StringPath soundAlike = createString("soundAlike");

    public final StringPath specialityProduct = createString("specialityProduct");

    public final StringPath specialTeeCode = createString("specialTeeCode");

    public final StringPath storageCode = createString("storageCode");

    public final StringPath universalProductId = createString("universalProductId");

    public QProducts(String variable) {
        super(Products.class, forVariable(variable), "dbo", "products");
        addMetadata();
    }

    public QProducts(String variable, String schema, String table) {
        super(Products.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QProducts(String variable, String schema) {
        super(Products.class, forVariable(variable), schema, "products");
        addMetadata();
    }

    public QProducts(Path<? extends Products> path) {
        super(path.getType(), path.getMetadata(), "dbo", "products");
        addMetadata();
    }

    public QProducts(PathMetadata metadata) {
        super(Products.class, metadata, "dbo", "products");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(autotestfs, ColumnMetadata.named("autotestfs").withIndex(72).ofType(Types.CHAR).withSize(1));
        addMetadata(backorderProduct, ColumnMetadata.named("backorder_product").withIndex(63).ofType(Types.CHAR).withSize(1));
        addMetadata(compoundAdminRoute, ColumnMetadata.named("compound_admin_route").withIndex(36).ofType(Types.NUMERIC).withSize(5));
        addMetadata(compoundFormCode, ColumnMetadata.named("compound_form_code").withIndex(35).ofType(Types.VARCHAR).withSize(35));
        addMetadata(compoundType, ColumnMetadata.named("compound_type").withIndex(52).ofType(Types.CHAR).withSize(2));
        addMetadata(compoundUnitForm, ColumnMetadata.named("compound_unit_form").withIndex(42).ofType(Types.NUMERIC).withSize(1));
        addMetadata(conversionLink, ColumnMetadata.named("conversion_link").withIndex(23).ofType(Types.VARCHAR).withSize(60));
        addMetadata(csPotencyUnitCode, ColumnMetadata.named("cs_potency_unit_code").withIndex(71).ofType(Types.VARCHAR).withSize(60));
        addMetadata(dataCollection, ColumnMetadata.named("data_collection").withIndex(51).ofType(Types.CHAR).withSize(1));
        addMetadata(dateAdded, ColumnMetadata.named("date_added").withIndex(68).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(dmeProduct, ColumnMetadata.named("dme_product").withIndex(55).ofType(Types.CHAR).withSize(1));
        addMetadata(dosageFormId, ColumnMetadata.named("dosage_form_id").withIndex(13).ofType(Types.CHAR).withSize(2));
        addMetadata(drugdbInactiveDate, ColumnMetadata.named("drugdb_inactive_date").withIndex(54).ofType(Types.VARCHAR).withSize(60));
        addMetadata(drugMaker, ColumnMetadata.named("drug_maker").withIndex(26).ofType(Types.VARCHAR).withSize(60));
        addMetadata(hrmActive, ColumnMetadata.named("hrm_active").withIndex(74).ofType(Types.CHAR).withSize(1));
        addMetadata(hrmEligible, ColumnMetadata.named("hrm_eligible").withIndex(75).ofType(Types.CHAR).withSize(1));
        addMetadata(hrmProduct, ColumnMetadata.named("hrm_product").withIndex(56).ofType(Types.CHAR).withSize(1));
        addMetadata(lastUpdate, ColumnMetadata.named("last_update").withIndex(49).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(manallocProduct, ColumnMetadata.named("manalloc_product").withIndex(62).ofType(Types.CHAR).withSize(1));
        addMetadata(minimumProductMargin, ColumnMetadata.named("minimum_product_margin").withIndex(22).ofType(Types.NUMERIC).withSize(12));
        addMetadata(multiSourceCode, ColumnMetadata.named("multi_source_code").withIndex(46).ofType(Types.VARCHAR).withSize(3));
        addMetadata(mybProduct, ColumnMetadata.named("myb_product").withIndex(61).ofType(Types.CHAR).withSize(1));
        addMetadata(packageQuantity, ColumnMetadata.named("package_quantity").withIndex(32).ofType(Types.NUMERIC).withSize(5));
        addMetadata(potencyUnitCode, ColumnMetadata.named("potency_unit_code").withIndex(70).ofType(Types.VARCHAR).withSize(60));
        addMetadata(prodActive, ColumnMetadata.named("prod_active").withIndex(4).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(prodAdminRoute, ColumnMetadata.named("prod_admin_route").withIndex(53).ofType(Types.VARCHAR).withSize(60));
        addMetadata(prodAttributes, ColumnMetadata.named("prod_attributes").withIndex(18).ofType(Types.VARCHAR).withSize(1500));
        addMetadata(prodAwpDefaultprice, ColumnMetadata.named("prod_awp_defaultprice").withIndex(21).ofType(Types.NUMERIC).withSize(10));
        addMetadata(prodAwpDefaultunitprice, ColumnMetadata.named("prod_awp_defaultunitprice").withIndex(41).ofType(Types.NUMERIC).withSize(12));
        addMetadata(prodCompound, ColumnMetadata.named("prod_compound").withIndex(24).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(prodConsolidate, ColumnMetadata.named("prod_consolidate").withIndex(58).ofType(Types.CHAR).withSize(1));
        addMetadata(prodDea, ColumnMetadata.named("prod_dea").withIndex(12).ofType(Types.NUMERIC).withSize(1));
        addMetadata(prodDefaultDays, ColumnMetadata.named("prod_default_days").withIndex(31).ofType(Types.NUMERIC).withSize(5));
        addMetadata(prodDefaultDrugWacunit, ColumnMetadata.named("prod_default_drug_wacunit").withIndex(59).ofType(Types.NUMERIC).withSize(12));
        addMetadata(prodDefaultPackqty, ColumnMetadata.named("prod_default_packqty").withIndex(44).ofType(Types.NUMERIC).withSize(5));
        addMetadata(prodDefaultPrescribeReason, ColumnMetadata.named("prod_default_prescribe_reason").withIndex(43).ofType(Types.VARCHAR).withSize(255));
        addMetadata(prodDefaultQuantity, ColumnMetadata.named("prod_default_quantity").withIndex(30).ofType(Types.NUMERIC).withSize(5));
        addMetadata(prodDefaultSig, ColumnMetadata.named("prod_default_sig").withIndex(29).ofType(Types.VARCHAR).withSize(255));
        addMetadata(prodDefPackageSize, ColumnMetadata.named("prod_def_package_size").withIndex(25).ofType(Types.NUMERIC).withSize(12));
        addMetadata(prodDrugAwpPrice, ColumnMetadata.named("prod_drug_awp_price").withIndex(33).ofType(Types.NUMERIC).withSize(12));
        addMetadata(prodDrugAwpUnitPrice, ColumnMetadata.named("prod_drug_awp_unit_price").withIndex(34).ofType(Types.NUMERIC).withSize(12));
        addMetadata(prodDrugWacunit, ColumnMetadata.named("prod_drug_wacunit").withIndex(60).ofType(Types.NUMERIC).withSize(7));
        addMetadata(prodFormattedId, ColumnMetadata.named("prod_formatted_id").withIndex(27).ofType(Types.VARCHAR).withSize(60));
        addMetadata(prodFormTypeCode, ColumnMetadata.named("prod_form_type_code").withIndex(17).ofType(Types.CHAR).withSize(1));
        addMetadata(prodGenericRefNum, ColumnMetadata.named("prod_generic_ref_num").withIndex(16).ofType(Types.VARCHAR).withSize(60));
        addMetadata(prodHandlingSeq1, ColumnMetadata.named("prod_handling_seq1").withIndex(64).ofType(Types.NUMERIC).withSize(7));
        addMetadata(prodHandlingSeq2, ColumnMetadata.named("prod_handling_seq2").withIndex(65).ofType(Types.NUMERIC).withSize(7));
        addMetadata(prodHandlingSeq3, ColumnMetadata.named("prod_handling_seq3").withIndex(66).ofType(Types.NUMERIC).withSize(7));
        addMetadata(prodHandlingSeq4, ColumnMetadata.named("prod_handling_seq4").withIndex(67).ofType(Types.NUMERIC).withSize(7));
        addMetadata(prodId, ColumnMetadata.named("prod_id").withIndex(1).ofType(Types.NUMERIC).withSize(16).notNull());
        addMetadata(prodName, ColumnMetadata.named("prod_name").withIndex(2).ofType(Types.VARCHAR).withSize(60));
        addMetadata(prodNameDesc, ColumnMetadata.named("prod_name_desc").withIndex(6).ofType(Types.VARCHAR).withSize(60).notNull());
        addMetadata(prodNameType, ColumnMetadata.named("prod_name_type").withIndex(14).ofType(Types.CHAR).withSize(1));
        addMetadata(prodNumber, ColumnMetadata.named("prod_number").withIndex(19).ofType(Types.VARCHAR).withSize(60).notNull());
        addMetadata(prodPackageSize, ColumnMetadata.named("prod_package_size").withIndex(10).ofType(Types.NUMERIC).withSize(11));
        addMetadata(prodPacksizeUnitcode, ColumnMetadata.named("prod_packsize_unitcode").withIndex(20).ofType(Types.VARCHAR).withSize(15));
        addMetadata(prodReferenceNum, ColumnMetadata.named("prod_reference_num").withIndex(7).ofType(Types.VARCHAR).withSize(60));
        addMetadata(prodRxRequired, ColumnMetadata.named("prod_rx_required").withIndex(5).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(prodStrength, ColumnMetadata.named("prod_strength").withIndex(8).ofType(Types.VARCHAR).withSize(15));
        addMetadata(prodStrengthUnit, ColumnMetadata.named("prod_strength_unit").withIndex(9).ofType(Types.VARCHAR).withSize(15));
        addMetadata(prodTeeCode, ColumnMetadata.named("prod_tee_code").withIndex(69).ofType(Types.VARCHAR).withSize(60));
        addMetadata(prodTypeNum, ColumnMetadata.named("prod_type_num").withIndex(28).ofType(Types.NUMERIC).withSize(5));
        addMetadata(productColor, ColumnMetadata.named("product_color").withIndex(38).ofType(Types.VARCHAR).withSize(60));
        addMetadata(productImprint, ColumnMetadata.named("product_imprint").withIndex(40).ofType(Types.VARCHAR).withSize(255));
        addMetadata(productServiceId, ColumnMetadata.named("product_service_id").withIndex(3).ofType(Types.CHAR).withSize(2));
        addMetadata(productShape, ColumnMetadata.named("product_shape").withIndex(39).ofType(Types.VARCHAR).withSize(60));
        addMetadata(prodUnitOfUse, ColumnMetadata.named("prod_unit_of_use").withIndex(11).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(qtyPerLabel, ColumnMetadata.named("qty_per_label").withIndex(37).ofType(Types.NUMERIC).withSize(5));
        addMetadata(reviewProduct, ColumnMetadata.named("review_product").withIndex(50).ofType(Types.CHAR).withSize(1));
        addMetadata(rowversion, ColumnMetadata.named("rowversion").withIndex(73).ofType(Types.BINARY).withSize(8).notNull());
        addMetadata(sigisId, ColumnMetadata.named("sigis_id").withIndex(45).ofType(Types.NUMERIC).withSize(20));
        addMetadata(soundAlike, ColumnMetadata.named("sound_alike").withIndex(57).ofType(Types.VARCHAR).withSize(500));
        addMetadata(specialityProduct, ColumnMetadata.named("speciality_product").withIndex(47).ofType(Types.CHAR).withSize(1));
        addMetadata(specialTeeCode, ColumnMetadata.named("Special_tee_code").withIndex(76).ofType(Types.VARCHAR).withSize(60));
        addMetadata(storageCode, ColumnMetadata.named("storage_code").withIndex(48).ofType(Types.VARCHAR).withSize(60));
        addMetadata(universalProductId, ColumnMetadata.named("universal_product_id").withIndex(15).ofType(Types.VARCHAR).withSize(60));
    }

}

