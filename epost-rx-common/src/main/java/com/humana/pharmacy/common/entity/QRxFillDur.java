package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.RxFillDur;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QRxFillDur is a Querydsl query type for RxFillDur
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QRxFillDur extends com.querydsl.sql.RelationalPathBase<RxFillDur> {

    private static final long serialVersionUID = 60980542;

    public static final QRxFillDur rxFillDur = new QRxFillDur("rx_fill_dur");

    public final StringPath coAgentId = createString("coAgentId");

    public final StringPath coAgentQualifier = createString("coAgentQualifier");

    public final StringPath durComment = createString("durComment");

    public final DateTimePath<java.sql.Timestamp> durDate = createDateTime("durDate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> durResolveDate = createDateTime("durResolveDate", java.sql.Timestamp.class);

    public final NumberPath<java.math.BigInteger> durSeq = createNumber("durSeq", java.math.BigInteger.class);

    public final StringPath durSeverityCode = createString("durSeverityCode");

    public final NumberPath<java.math.BigInteger> eScriptMsgAttributeSeq = createNumber("eScriptMsgAttributeSeq", java.math.BigInteger.class);

    public final StringPath externalMed = createString("externalMed");

    public final NumberPath<Byte> interactCode = createNumber("interactCode", Byte.class);

    public final NumberPath<Byte> interactDdxcn = createNumber("interactDdxcn", Byte.class);

    public final NumberPath<Byte> interactDdxcnSn = createNumber("interactDdxcnSn", Byte.class);

    public final NumberPath<java.math.BigInteger> interactEscriptSeq = createNumber("interactEscriptSeq", java.math.BigInteger.class);

    public final StringPath interactionDesc = createString("interactionDesc");

    public final StringPath interactMsgHash = createString("interactMsgHash");

    public final StringPath levelOfEffort = createString("levelOfEffort");

    public final NumberPath<java.math.BigInteger> orderNum = createNumber("orderNum", java.math.BigInteger.class);

    public final NumberPath<java.math.BigInteger> patientAllergySeq = createNumber("patientAllergySeq", java.math.BigInteger.class);

    public final NumberPath<java.math.BigInteger> patientDiseaseSeq = createNumber("patientDiseaseSeq", java.math.BigInteger.class);

    public final StringPath prodRefNum = createString("prodRefNum");

    public final StringPath profSvcCode = createString("profSvcCode");

    public final StringPath reasonSvcCode = createString("reasonSvcCode");

    public final StringPath resultSvcCode = createString("resultSvcCode");

    public final NumberPath<Byte> rxInteractionNum = createNumber("rxInteractionNum", Byte.class);

    public final StringPath rxNumber = createString("rxNumber");

    public final NumberPath<Long> sysUserNum = createNumber("sysUserNum", Long.class);

    public final NumberPath<Long> tradingPartnerNum = createNumber("tradingPartnerNum", Long.class);

    public QRxFillDur(String variable) {
        super(RxFillDur.class, forVariable(variable), "dbo", "rx_fill_dur");
        addMetadata();
    }

    public QRxFillDur(String variable, String schema, String table) {
        super(RxFillDur.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QRxFillDur(String variable, String schema) {
        super(RxFillDur.class, forVariable(variable), schema, "rx_fill_dur");
        addMetadata();
    }

    public QRxFillDur(Path<? extends RxFillDur> path) {
        super(path.getType(), path.getMetadata(), "dbo", "rx_fill_dur");
        addMetadata();
    }

    public QRxFillDur(PathMetadata metadata) {
        super(RxFillDur.class, metadata, "dbo", "rx_fill_dur");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(coAgentId, ColumnMetadata.named("co_agent_id").withIndex(25).ofType(Types.VARCHAR).withSize(60));
        addMetadata(coAgentQualifier, ColumnMetadata.named("co_agent_qualifier").withIndex(26).ofType(Types.VARCHAR).withSize(60));
        addMetadata(durComment, ColumnMetadata.named("dur_comment").withIndex(10).ofType(Types.VARCHAR).withSize(1000));
        addMetadata(durDate, ColumnMetadata.named("dur_date").withIndex(3).ofType(Types.TIMESTAMP).withSize(23).withDigits(3).notNull());
        addMetadata(durResolveDate, ColumnMetadata.named("dur_resolve_date").withIndex(11).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(durSeq, ColumnMetadata.named("dur_seq").withIndex(6).ofType(Types.NUMERIC).withSize(20).notNull());
        addMetadata(durSeverityCode, ColumnMetadata.named("dur_severity_code").withIndex(4).ofType(Types.CHAR).withSize(1));
        addMetadata(eScriptMsgAttributeSeq, ColumnMetadata.named("e_script_msg_attribute_seq").withIndex(1).ofType(Types.NUMERIC).withSize(35));
        addMetadata(externalMed, ColumnMetadata.named("external_med").withIndex(27).ofType(Types.CHAR).withSize(1));
        addMetadata(interactCode, ColumnMetadata.named("interact_code").withIndex(14).ofType(Types.NUMERIC).withSize(2));
        addMetadata(interactDdxcn, ColumnMetadata.named("interact_ddxcn").withIndex(15).ofType(Types.NUMERIC).withSize(2));
        addMetadata(interactDdxcnSn, ColumnMetadata.named("interact_ddxcn_sn").withIndex(16).ofType(Types.NUMERIC).withSize(2));
        addMetadata(interactEscriptSeq, ColumnMetadata.named("interact_escript_seq").withIndex(12).ofType(Types.NUMERIC).withSize(25));
        addMetadata(interactionDesc, ColumnMetadata.named("interaction_desc").withIndex(13).ofType(Types.VARCHAR).withSize(2500));
        addMetadata(interactMsgHash, ColumnMetadata.named("interact_msg_hash").withIndex(20).ofType(Types.VARCHAR).withSize(60));
        addMetadata(levelOfEffort, ColumnMetadata.named("level_of_effort").withIndex(24).ofType(Types.VARCHAR).withSize(60));
        addMetadata(orderNum, ColumnMetadata.named("order_num").withIndex(23).ofType(Types.NUMERIC).withSize(30));
        addMetadata(patientAllergySeq, ColumnMetadata.named("patient_allergy_seq").withIndex(18).ofType(Types.NUMERIC).withSize(20));
        addMetadata(patientDiseaseSeq, ColumnMetadata.named("patient_disease_seq").withIndex(17).ofType(Types.NUMERIC).withSize(20));
        addMetadata(prodRefNum, ColumnMetadata.named("prod_ref_num").withIndex(19).ofType(Types.VARCHAR).withSize(60));
        addMetadata(profSvcCode, ColumnMetadata.named("prof_svc_code").withIndex(8).ofType(Types.CHAR).withSize(2));
        addMetadata(reasonSvcCode, ColumnMetadata.named("reason_svc_code").withIndex(9).ofType(Types.CHAR).withSize(2));
        addMetadata(resultSvcCode, ColumnMetadata.named("result_svc_code").withIndex(7).ofType(Types.CHAR).withSize(2));
        addMetadata(rxInteractionNum, ColumnMetadata.named("rx_interaction_num").withIndex(5).ofType(Types.NUMERIC).withSize(2));
        addMetadata(rxNumber, ColumnMetadata.named("rx_number").withIndex(21).ofType(Types.VARCHAR).withSize(60));
        addMetadata(sysUserNum, ColumnMetadata.named("sys_user_num").withIndex(2).ofType(Types.NUMERIC).withSize(16));
        addMetadata(tradingPartnerNum, ColumnMetadata.named("trading_partner_num").withIndex(22).ofType(Types.NUMERIC).withSize(16));
    }

}

