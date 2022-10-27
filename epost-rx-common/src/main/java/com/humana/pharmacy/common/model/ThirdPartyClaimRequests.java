package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * ThirdPartyClaimRequests is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class ThirdPartyClaimRequests {

    private java.math.BigInteger benefitCardNum;

    private Long eScriptMsgAttributeSeq;

    private Long id;

    private Boolean manual;

    private Integer orderAmount;

    private java.math.BigInteger orderInvoiceNumber;

    private String processor;

    private Integer redemptionReqAmount;

    private Short redemptionReqQuantity;

    private java.sql.Timestamp requestDatetime;

    private String server;

    private String sysUserNum;

    private String transactionCode;

    private String transactionId;

    public java.math.BigInteger getBenefitCardNum() {
        return benefitCardNum;
    }

    public void setBenefitCardNum(java.math.BigInteger benefitCardNum) {
        this.benefitCardNum = benefitCardNum;
    }

    public Long geteScriptMsgAttributeSeq() {
        return eScriptMsgAttributeSeq;
    }

    public void seteScriptMsgAttributeSeq(Long eScriptMsgAttributeSeq) {
        this.eScriptMsgAttributeSeq = eScriptMsgAttributeSeq;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getManual() {
        return manual;
    }

    public void setManual(Boolean manual) {
        this.manual = manual;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public java.math.BigInteger getOrderInvoiceNumber() {
        return orderInvoiceNumber;
    }

    public void setOrderInvoiceNumber(java.math.BigInteger orderInvoiceNumber) {
        this.orderInvoiceNumber = orderInvoiceNumber;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public Integer getRedemptionReqAmount() {
        return redemptionReqAmount;
    }

    public void setRedemptionReqAmount(Integer redemptionReqAmount) {
        this.redemptionReqAmount = redemptionReqAmount;
    }

    public Short getRedemptionReqQuantity() {
        return redemptionReqQuantity;
    }

    public void setRedemptionReqQuantity(Short redemptionReqQuantity) {
        this.redemptionReqQuantity = redemptionReqQuantity;
    }

    public java.sql.Timestamp getRequestDatetime() {
        return requestDatetime;
    }

    public void setRequestDatetime(java.sql.Timestamp requestDatetime) {
        this.requestDatetime = requestDatetime;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getSysUserNum() {
        return sysUserNum;
    }

    public void setSysUserNum(String sysUserNum) {
        this.sysUserNum = sysUserNum;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

}

