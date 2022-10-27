package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * RxLinkSubstitutionLog is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class RxLinkSubstitutionLog {

    private java.sql.Timestamp logDate;

    private java.math.BigInteger orderNumber;

    private String originalDispensedProdName;

    private String originalRxNumber;

    private String originalWrittenProdName;

    private java.math.BigInteger rxLinkSubstituionLogSeq;

    private String substitutedDispensedProdName;

    private String substitutedRxNumber;

    private String substitutedWrittenProdName;

    private String typeOfAction;

    private java.math.BigInteger userNum;

    public java.sql.Timestamp getLogDate() {
        return logDate;
    }

    public void setLogDate(java.sql.Timestamp logDate) {
        this.logDate = logDate;
    }

    public java.math.BigInteger getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(java.math.BigInteger orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOriginalDispensedProdName() {
        return originalDispensedProdName;
    }

    public void setOriginalDispensedProdName(String originalDispensedProdName) {
        this.originalDispensedProdName = originalDispensedProdName;
    }

    public String getOriginalRxNumber() {
        return originalRxNumber;
    }

    public void setOriginalRxNumber(String originalRxNumber) {
        this.originalRxNumber = originalRxNumber;
    }

    public String getOriginalWrittenProdName() {
        return originalWrittenProdName;
    }

    public void setOriginalWrittenProdName(String originalWrittenProdName) {
        this.originalWrittenProdName = originalWrittenProdName;
    }

    public java.math.BigInteger getRxLinkSubstituionLogSeq() {
        return rxLinkSubstituionLogSeq;
    }

    public void setRxLinkSubstituionLogSeq(java.math.BigInteger rxLinkSubstituionLogSeq) {
        this.rxLinkSubstituionLogSeq = rxLinkSubstituionLogSeq;
    }

    public String getSubstitutedDispensedProdName() {
        return substitutedDispensedProdName;
    }

    public void setSubstitutedDispensedProdName(String substitutedDispensedProdName) {
        this.substitutedDispensedProdName = substitutedDispensedProdName;
    }

    public String getSubstitutedRxNumber() {
        return substitutedRxNumber;
    }

    public void setSubstitutedRxNumber(String substitutedRxNumber) {
        this.substitutedRxNumber = substitutedRxNumber;
    }

    public String getSubstitutedWrittenProdName() {
        return substitutedWrittenProdName;
    }

    public void setSubstitutedWrittenProdName(String substitutedWrittenProdName) {
        this.substitutedWrittenProdName = substitutedWrittenProdName;
    }

    public String getTypeOfAction() {
        return typeOfAction;
    }

    public void setTypeOfAction(String typeOfAction) {
        this.typeOfAction = typeOfAction;
    }

    public java.math.BigInteger getUserNum() {
        return userNum;
    }

    public void setUserNum(java.math.BigInteger userNum) {
        this.userNum = userNum;
    }

}

