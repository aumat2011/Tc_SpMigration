package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * RxLinkLogging is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class RxLinkLogging {

    private String action;

    private java.math.BigInteger childEScriptMsgAttrSeq;

    private String childRxNumber;

    private java.sql.Timestamp logDatetime;

    private Long logUser;

    private java.math.BigInteger parentEScriptMsgAttrSeq;

    private String parentRxNumber;

    private java.math.BigInteger rxLinkLogSeq;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public java.math.BigInteger getChildEScriptMsgAttrSeq() {
        return childEScriptMsgAttrSeq;
    }

    public void setChildEScriptMsgAttrSeq(java.math.BigInteger childEScriptMsgAttrSeq) {
        this.childEScriptMsgAttrSeq = childEScriptMsgAttrSeq;
    }

    public String getChildRxNumber() {
        return childRxNumber;
    }

    public void setChildRxNumber(String childRxNumber) {
        this.childRxNumber = childRxNumber;
    }

    public java.sql.Timestamp getLogDatetime() {
        return logDatetime;
    }

    public void setLogDatetime(java.sql.Timestamp logDatetime) {
        this.logDatetime = logDatetime;
    }

    public Long getLogUser() {
        return logUser;
    }

    public void setLogUser(Long logUser) {
        this.logUser = logUser;
    }

    public java.math.BigInteger getParentEScriptMsgAttrSeq() {
        return parentEScriptMsgAttrSeq;
    }

    public void setParentEScriptMsgAttrSeq(java.math.BigInteger parentEScriptMsgAttrSeq) {
        this.parentEScriptMsgAttrSeq = parentEScriptMsgAttrSeq;
    }

    public String getParentRxNumber() {
        return parentRxNumber;
    }

    public void setParentRxNumber(String parentRxNumber) {
        this.parentRxNumber = parentRxNumber;
    }

    public java.math.BigInteger getRxLinkLogSeq() {
        return rxLinkLogSeq;
    }

    public void setRxLinkLogSeq(java.math.BigInteger rxLinkLogSeq) {
        this.rxLinkLogSeq = rxLinkLogSeq;
    }

}

