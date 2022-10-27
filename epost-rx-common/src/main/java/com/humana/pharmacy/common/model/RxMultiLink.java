package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * RxMultiLink is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class RxMultiLink {

    private String active;

    private java.math.BigInteger childEScriptMsgAttributeSeq;

    private String childRxNumber;

    private String comment;

    private java.sql.Timestamp createdDatetime;

    private Long createdSysUserNum;

    private java.sql.Timestamp deactivatedDatetime;

    private Long deactivatedSysUserNum;

    private java.math.BigInteger parentEScriptMsgAttributeSeq;

    private String parentRxNumber;

    private Long rxLinkSeq;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public java.math.BigInteger getChildEScriptMsgAttributeSeq() {
        return childEScriptMsgAttributeSeq;
    }

    public void setChildEScriptMsgAttributeSeq(java.math.BigInteger childEScriptMsgAttributeSeq) {
        this.childEScriptMsgAttributeSeq = childEScriptMsgAttributeSeq;
    }

    public String getChildRxNumber() {
        return childRxNumber;
    }

    public void setChildRxNumber(String childRxNumber) {
        this.childRxNumber = childRxNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public java.sql.Timestamp getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(java.sql.Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public Long getCreatedSysUserNum() {
        return createdSysUserNum;
    }

    public void setCreatedSysUserNum(Long createdSysUserNum) {
        this.createdSysUserNum = createdSysUserNum;
    }

    public java.sql.Timestamp getDeactivatedDatetime() {
        return deactivatedDatetime;
    }

    public void setDeactivatedDatetime(java.sql.Timestamp deactivatedDatetime) {
        this.deactivatedDatetime = deactivatedDatetime;
    }

    public Long getDeactivatedSysUserNum() {
        return deactivatedSysUserNum;
    }

    public void setDeactivatedSysUserNum(Long deactivatedSysUserNum) {
        this.deactivatedSysUserNum = deactivatedSysUserNum;
    }

    public java.math.BigInteger getParentEScriptMsgAttributeSeq() {
        return parentEScriptMsgAttributeSeq;
    }

    public void setParentEScriptMsgAttributeSeq(java.math.BigInteger parentEScriptMsgAttributeSeq) {
        this.parentEScriptMsgAttributeSeq = parentEScriptMsgAttributeSeq;
    }

    public String getParentRxNumber() {
        return parentRxNumber;
    }

    public void setParentRxNumber(String parentRxNumber) {
        this.parentRxNumber = parentRxNumber;
    }

    public Long getRxLinkSeq() {
        return rxLinkSeq;
    }

    public void setRxLinkSeq(Long rxLinkSeq) {
        this.rxLinkSeq = rxLinkSeq;
    }

}

