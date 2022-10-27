package com.humana.pharmacy.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * The result dto for checking Rx sanity.
 */
@Setter
@Getter
public class RsMultiLinkDTO {
    /**
     * The rxLinkSeq
     */
    private Long rxLinkSeq;

    /**
     * The childRxNumber
     */
    private String childRxNumber;

    /**
     * The parentRxNumber
     */
    private String parentRxNumber;

    /**
     * The parent eScriptMsgAttributeSeq
     */
    private BigInteger parentEScriptMsgAttributeSeq;

    /**
     * The child eScriptMsgAttributeSeq
     */
    private BigInteger childEScriptMsgAttributeSeq;

    /**
     * The active
     */
    private String active;

    /**
     * The created sys user num
     */
    private Long createdSysUserNum;

    /**
     * the sys user login
     */
    private String sysUserLogin;

    /**
     * the created datetime
     */
    private Timestamp createdDatetime;

    /**
     * the deactivated sys user num
     */
    private Long deactivatedSysUserNum;

    /**
     * the deactivated datetime
     */
    private Timestamp deactivatedDatetime;

    /**
     * the comment
     */
    private String comment;
}

