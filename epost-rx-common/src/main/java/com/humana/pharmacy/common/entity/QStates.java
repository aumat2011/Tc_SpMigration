package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.States;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QStates is a Querydsl query type for States
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QStates extends com.querydsl.sql.RelationalPathBase<States> {

    private static final long serialVersionUID = -1460928804;

    public static final QStates states = new QStates("states");

    public final StringPath calcEcsBasis = createString("calcEcsBasis");

    public final StringPath chargeEcs = createString("chargeEcs");

    public final StringPath chargeEcsBasis = createString("chargeEcsBasis");

    public final StringPath collectTax = createString("collectTax");

    public final NumberPath<Byte> consentAge = createNumber("consentAge", Byte.class);

    public final StringPath csDayOfWeek = createString("csDayOfWeek");

    public final StringPath csEmptyFile = createString("csEmptyFile");

    public final StringPath csExtension = createString("csExtension");

    public final StringPath csFilename = createString("csFilename");

    public final StringPath csFilenameDates = createString("csFilenameDates");

    public final StringPath csFillReport = createString("csFillReport");

    public final NumberPath<Integer> csFrequency = createNumber("csFrequency", Integer.class);

    public final DateTimePath<java.sql.Timestamp> csLastDate = createDateTime("csLastDate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> csNextDate = createDateTime("csNextDate", java.sql.Timestamp.class);

    public final NumberPath<Integer> csPatientReportSeq = createNumber("csPatientReportSeq", Integer.class);

    public final NumberPath<Integer> csReportSeq = createNumber("csReportSeq", Integer.class);

    public final StringPath csSkipC1 = createString("csSkipC1");

    public final StringPath csSkipC2 = createString("csSkipC2");

    public final StringPath csSkipC3 = createString("csSkipC3");

    public final StringPath csSkipC4 = createString("csSkipC4");

    public final StringPath csSkipC5 = createString("csSkipC5");

    public final StringPath csSkipC6 = createString("csSkipC6");

    public final StringPath csSkipRun = createString("csSkipRun");

    public final NumberPath<Integer> ctryNum = createNumber("ctryNum", Integer.class);

    public final StringPath daw12 = createString("daw12");

    public final StringPath dmeOnly = createString("dmeOnly");

    public final StringPath ncpdpStateCode = createString("ncpdpStateCode");

    public final BooleanPath pmpEnabled = createBoolean("pmpEnabled");

    public final BooleanPath realTimeReportingOn = createBoolean("realTimeReportingOn");

    public final StringPath rphStateLicense = createString("rphStateLicense");

    public final NumberPath<Long> salesTax = createNumber("salesTax", Long.class);

    public final StringPath salesTaxType = createString("salesTaxType");

    public final NumberPath<Integer> serverNum = createNumber("serverNum", Integer.class);

    public final StringPath stateCode = createString("stateCode");

    public final StringPath stateLicense = createString("stateLicense");

    public final StringPath stateName = createString("stateName");

    public final NumberPath<Integer> stateNum = createNumber("stateNum", Integer.class);

    public QStates(String variable) {
        super(States.class, forVariable(variable), "dbo", "states");
        addMetadata();
    }

    public QStates(String variable, String schema, String table) {
        super(States.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QStates(String variable, String schema) {
        super(States.class, forVariable(variable), schema, "states");
        addMetadata();
    }

    public QStates(Path<? extends States> path) {
        super(path.getType(), path.getMetadata(), "dbo", "states");
        addMetadata();
    }

    public QStates(PathMetadata metadata) {
        super(States.class, metadata, "dbo", "states");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(calcEcsBasis, ColumnMetadata.named("calc_ecs_basis").withIndex(34).ofType(Types.VARCHAR).withSize(60));
        addMetadata(chargeEcs, ColumnMetadata.named("charge_ecs").withIndex(32).ofType(Types.CHAR).withSize(1));
        addMetadata(chargeEcsBasis, ColumnMetadata.named("charge_ecs_basis").withIndex(33).ofType(Types.VARCHAR).withSize(60));
        addMetadata(collectTax, ColumnMetadata.named("collect_tax").withIndex(30).ofType(Types.CHAR).withSize(1));
        addMetadata(consentAge, ColumnMetadata.named("consent_age").withIndex(28).ofType(Types.NUMERIC).withSize(2));
        addMetadata(csDayOfWeek, ColumnMetadata.named("cs_day_of_week").withIndex(16).ofType(Types.VARCHAR).withSize(60));
        addMetadata(csEmptyFile, ColumnMetadata.named("cs_empty_file").withIndex(12).ofType(Types.CHAR).withSize(1));
        addMetadata(csExtension, ColumnMetadata.named("cs_extension").withIndex(17).ofType(Types.VARCHAR).withSize(120));
        addMetadata(csFilename, ColumnMetadata.named("cs_filename").withIndex(24).ofType(Types.VARCHAR).withSize(120));
        addMetadata(csFilenameDates, ColumnMetadata.named("cs_filename_dates").withIndex(25).ofType(Types.CHAR).withSize(1));
        addMetadata(csFillReport, ColumnMetadata.named("cs_fill_report").withIndex(27).ofType(Types.CHAR).withSize(1));
        addMetadata(csFrequency, ColumnMetadata.named("cs_frequency").withIndex(13).ofType(Types.NUMERIC).withSize(7));
        addMetadata(csLastDate, ColumnMetadata.named("cs_last_date").withIndex(15).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(csNextDate, ColumnMetadata.named("cs_next_date").withIndex(14).ofType(Types.TIMESTAMP).withSize(23).withDigits(3));
        addMetadata(csPatientReportSeq, ColumnMetadata.named("cs_patient_report_seq").withIndex(10).ofType(Types.NUMERIC).withSize(7));
        addMetadata(csReportSeq, ColumnMetadata.named("cs_report_seq").withIndex(7).ofType(Types.NUMERIC).withSize(5));
        addMetadata(csSkipC1, ColumnMetadata.named("cs_skip_c1").withIndex(18).ofType(Types.CHAR).withSize(1));
        addMetadata(csSkipC2, ColumnMetadata.named("cs_skip_c2").withIndex(19).ofType(Types.CHAR).withSize(1));
        addMetadata(csSkipC3, ColumnMetadata.named("cs_skip_c3").withIndex(20).ofType(Types.CHAR).withSize(1));
        addMetadata(csSkipC4, ColumnMetadata.named("cs_skip_c4").withIndex(21).ofType(Types.CHAR).withSize(1));
        addMetadata(csSkipC5, ColumnMetadata.named("cs_skip_c5").withIndex(22).ofType(Types.CHAR).withSize(1));
        addMetadata(csSkipC6, ColumnMetadata.named("cs_skip_c6").withIndex(23).ofType(Types.CHAR).withSize(1));
        addMetadata(csSkipRun, ColumnMetadata.named("cs_skip_run").withIndex(26).ofType(Types.CHAR).withSize(1));
        addMetadata(ctryNum, ColumnMetadata.named("ctry_num").withIndex(4).ofType(Types.NUMERIC).withSize(5));
        addMetadata(daw12, ColumnMetadata.named("daw12").withIndex(29).ofType(Types.CHAR).withSize(1));
        addMetadata(dmeOnly, ColumnMetadata.named("dme_only").withIndex(35).ofType(Types.CHAR).withSize(1));
        addMetadata(ncpdpStateCode, ColumnMetadata.named("ncpdp_state_code").withIndex(6).ofType(Types.VARCHAR).withSize(3));
        addMetadata(pmpEnabled, ColumnMetadata.named("PMP_ENABLED").withIndex(36).ofType(Types.BIT).withSize(1).notNull());
        addMetadata(realTimeReportingOn, ColumnMetadata.named("REAL_TIME_REPORTING_ON").withIndex(37).ofType(Types.BIT).withSize(1).notNull());
        addMetadata(rphStateLicense, ColumnMetadata.named("rph_state_license").withIndex(11).ofType(Types.VARCHAR).withSize(60));
        addMetadata(salesTax, ColumnMetadata.named("sales_tax").withIndex(5).ofType(Types.NUMERIC).withSize(10));
        addMetadata(salesTaxType, ColumnMetadata.named("sales_tax_type").withIndex(31).ofType(Types.VARCHAR).withSize(60));
        addMetadata(serverNum, ColumnMetadata.named("server_num").withIndex(9).ofType(Types.NUMERIC).withSize(5));
        addMetadata(stateCode, ColumnMetadata.named("state_code").withIndex(1).ofType(Types.VARCHAR).withSize(4));
        addMetadata(stateLicense, ColumnMetadata.named("state_license").withIndex(8).ofType(Types.VARCHAR).withSize(60));
        addMetadata(stateName, ColumnMetadata.named("state_name").withIndex(3).ofType(Types.VARCHAR).withSize(30).notNull());
        addMetadata(stateNum, ColumnMetadata.named("state_num").withIndex(2).ofType(Types.NUMERIC).withSize(5).notNull());
    }

}

