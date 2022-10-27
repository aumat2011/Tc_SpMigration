package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * QuantityChangeTracking is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class QuantityChangeTracking {

    private java.sql.Timestamp createdOn;

    private java.math.BigInteger eScriptMsgAttributeSeq;

    private String isQuantityLowered;

    private String rxNumber;

    private java.sql.Timestamp updatedOn;

    public java.sql.Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(java.sql.Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public java.math.BigInteger geteScriptMsgAttributeSeq() {
        return eScriptMsgAttributeSeq;
    }

    public void seteScriptMsgAttributeSeq(java.math.BigInteger eScriptMsgAttributeSeq) {
        this.eScriptMsgAttributeSeq = eScriptMsgAttributeSeq;
    }

    public String getIsQuantityLowered() {
        return isQuantityLowered;
    }

    public void setIsQuantityLowered(String isQuantityLowered) {
        this.isQuantityLowered = isQuantityLowered;
    }

    public String getRxNumber() {
        return rxNumber;
    }

    public void setRxNumber(String rxNumber) {
        this.rxNumber = rxNumber;
    }

    public java.sql.Timestamp getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(java.sql.Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

}

