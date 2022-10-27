package com.humana.pharmacy.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.humana.pharmacy.common.model.SystemUsers;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;

import java.sql.Types;


/**
 * QSystemUsers is a Querydsl query type for SystemUsers
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QSystemUsers extends com.querydsl.sql.RelationalPathBase<SystemUsers> {

    private static final long serialVersionUID = 1160935551;

    public static final QSystemUsers systemUsers = new QSystemUsers("system_users");

    public final NumberPath<Long> accountNum = createNumber("accountNum", Long.class);

    public final StringPath adUserId = createString("adUserId");

    public final StringPath conversionLink = createString("conversionLink");

    public final NumberPath<Integer> ctryNum = createNumber("ctryNum", Integer.class);

    public final StringPath externalLink = createString("externalLink");

    public final StringPath generalStatusCode = createString("generalStatusCode");

    public final NumberPath<Integer> languageId = createNumber("languageId", Integer.class);

    public final StringPath lockout = createString("lockout");

    public final NumberPath<Integer> loginServer = createNumber("loginServer", Integer.class);

    public final StringPath miscellaneous = createString("miscellaneous");

    public final NumberPath<java.math.BigInteger> patientNum = createNumber("patientNum", java.math.BigInteger.class);

    public final NumberPath<Integer> phoneTypeNum = createNumber("phoneTypeNum", Integer.class);

    public final NumberPath<Integer> regionNum = createNumber("regionNum", Integer.class);

    public final SimplePath<byte[]> rowversion = createSimple("rowversion", byte[].class);

    public final StringPath secondaryPassword = createString("secondaryPassword");

    public final NumberPath<Short> sessionTimeout = createNumber("sessionTimeout", Short.class);

    public final NumberPath<Integer> systemUserTypeNum = createNumber("systemUserTypeNum", Integer.class);

    public final NumberPath<Short> sysUserAreaCode = createNumber("sysUserAreaCode", Short.class);

    public final StringPath sysUserEmailAddress = createString("sysUserEmailAddress");

    public final StringPath sysUserFirstName = createString("sysUserFirstName");

    public final StringPath sysUserInitials = createString("sysUserInitials");

    public final StringPath sysUserLastName = createString("sysUserLastName");

    public final StringPath sysUserLogin = createString("sysUserLogin");

    public final StringPath sysUserMiddleName = createString("sysUserMiddleName");

    public final NumberPath<Long> sysUserNum = createNumber("sysUserNum", Long.class);

    public final StringPath sysUserPassword = createString("sysUserPassword");

    public final NumberPath<Integer> sysUserPhoneExt = createNumber("sysUserPhoneExt", Integer.class);

    public final NumberPath<Integer> sysUserPhoneNum = createNumber("sysUserPhoneNum", Integer.class);

    public final NumberPath<Long> tradingPartnerNum = createNumber("tradingPartnerNum", Long.class);

    public final StringPath userClassName = createString("userClassName");

    public QSystemUsers(String variable) {
        super(SystemUsers.class, forVariable(variable), "dbo", "system_users");
        addMetadata();
    }

    public QSystemUsers(String variable, String schema, String table) {
        super(SystemUsers.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSystemUsers(String variable, String schema) {
        super(SystemUsers.class, forVariable(variable), schema, "system_users");
        addMetadata();
    }

    public QSystemUsers(Path<? extends SystemUsers> path) {
        super(path.getType(), path.getMetadata(), "dbo", "system_users");
        addMetadata();
    }

    public QSystemUsers(PathMetadata metadata) {
        super(SystemUsers.class, metadata, "dbo", "system_users");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(accountNum, ColumnMetadata.named("account_num").withIndex(18).ofType(Types.NUMERIC).withSize(16));
        addMetadata(adUserId, ColumnMetadata.named("ad_user_id").withIndex(30).ofType(Types.VARCHAR).withSize(60));
        addMetadata(conversionLink, ColumnMetadata.named("conversion_link").withIndex(23).ofType(Types.VARCHAR).withSize(60));
        addMetadata(ctryNum, ColumnMetadata.named("ctry_num").withIndex(15).ofType(Types.NUMERIC).withSize(5));
        addMetadata(externalLink, ColumnMetadata.named("external_link").withIndex(27).ofType(Types.VARCHAR).withSize(60));
        addMetadata(generalStatusCode, ColumnMetadata.named("general_status_code").withIndex(11).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(languageId, ColumnMetadata.named("language_id").withIndex(24).ofType(Types.NUMERIC).withSize(5));
        addMetadata(lockout, ColumnMetadata.named("lockout").withIndex(28).ofType(Types.CHAR).withSize(1));
        addMetadata(loginServer, ColumnMetadata.named("login_server").withIndex(26).ofType(Types.NUMERIC).withSize(5));
        addMetadata(miscellaneous, ColumnMetadata.named("miscellaneous").withIndex(20).ofType(Types.VARCHAR).withSize(80));
        addMetadata(patientNum, ColumnMetadata.named("patient_num").withIndex(19).ofType(Types.NUMERIC).withSize(20));
        addMetadata(phoneTypeNum, ColumnMetadata.named("phone_type_num").withIndex(16).ofType(Types.NUMERIC).withSize(5));
        addMetadata(regionNum, ColumnMetadata.named("region_num").withIndex(21).ofType(Types.NUMERIC).withSize(5));
        addMetadata(rowversion, ColumnMetadata.named("rowversion").withIndex(29).ofType(Types.BINARY).withSize(8).notNull());
        addMetadata(secondaryPassword, ColumnMetadata.named("secondary_password").withIndex(22).ofType(Types.VARCHAR).withSize(35));
        addMetadata(sessionTimeout, ColumnMetadata.named("session_timeout").withIndex(25).ofType(Types.NUMERIC).withSize(4));
        addMetadata(systemUserTypeNum, ColumnMetadata.named("system_user_type_num").withIndex(3).ofType(Types.NUMERIC).withSize(5));
        addMetadata(sysUserAreaCode, ColumnMetadata.named("sys_user_area_code").withIndex(12).ofType(Types.NUMERIC).withSize(3));
        addMetadata(sysUserEmailAddress, ColumnMetadata.named("sys_user_email_address").withIndex(9).ofType(Types.VARCHAR).withSize(45));
        addMetadata(sysUserFirstName, ColumnMetadata.named("sys_user_first_name").withIndex(6).ofType(Types.VARCHAR).withSize(60).notNull());
        addMetadata(sysUserInitials, ColumnMetadata.named("sys_user_initials").withIndex(10).ofType(Types.VARCHAR).withSize(7));
        addMetadata(sysUserLastName, ColumnMetadata.named("sys_user_last_name").withIndex(8).ofType(Types.VARCHAR).withSize(60).notNull());
        addMetadata(sysUserLogin, ColumnMetadata.named("sys_user_login").withIndex(4).ofType(Types.VARCHAR).withSize(30).notNull());
        addMetadata(sysUserMiddleName, ColumnMetadata.named("sys_user_middle_name").withIndex(7).ofType(Types.VARCHAR).withSize(60));
        addMetadata(sysUserNum, ColumnMetadata.named("sys_user_num").withIndex(1).ofType(Types.NUMERIC).withSize(16).notNull());
        addMetadata(sysUserPassword, ColumnMetadata.named("sys_user_password").withIndex(5).ofType(Types.VARCHAR).withSize(30));
        addMetadata(sysUserPhoneExt, ColumnMetadata.named("sys_user_phone_ext").withIndex(14).ofType(Types.NUMERIC).withSize(7));
        addMetadata(sysUserPhoneNum, ColumnMetadata.named("sys_user_phone_num").withIndex(13).ofType(Types.NUMERIC).withSize(7));
        addMetadata(tradingPartnerNum, ColumnMetadata.named("trading_partner_num").withIndex(17).ofType(Types.NUMERIC).withSize(16));
        addMetadata(userClassName, ColumnMetadata.named("user_class_name").withIndex(2).ofType(Types.VARCHAR).withSize(30).notNull());
    }

}

