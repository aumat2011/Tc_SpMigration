package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * PaymentCardReply is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class PaymentCardReply {

    private String actionCode;

    private String batch;

    private java.sql.Timestamp batchDatetime;

    private String batchNumber;

    private String batchStatus;

    private String fsaCard;

    private java.math.BigInteger glPostNum;

    private String manual;

    private java.math.BigInteger orderNum;

    private String paymentCardNumber;

    private String paymentCardSeqNum;

    private String pcAvsCode;

    private java.sql.Timestamp pcDatetime;

    private String pcLog;

    private Long pcReplyAmount;

    private String pcReplyAuthCode;

    private byte[] pcReplyMsg;

    private String pcReplyRcode;

    private String pcReplyRequestId;

    private String pcReplyRflag;

    private String pcReplyRmsg;

    private java.math.BigInteger pcReplySeqNum;

    private String pcReplyTimestamp;

    private String pcReplyType;

    private byte[] pcRequestMsg;

    private Byte pcSubStatusNum;

    private String provider;

    private String respAvsCode;

    private java.sql.Timestamp vaultBatchSettledDatetime;

    private String vaultBillAddress1;

    private String vaultBillAddress2;

    private String vaultBillCity;

    private String vaultBillCountry;

    private String vaultBillEmail;

    private String vaultBillPhone;

    private String vaultBillState;

    private String vaultBillZip;

    private String vaultCardType;

    private String vaultFirstName;

    private String vaultLastName;

    private String vaultMonthExpire;

    private String vaultYearExpire;

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public java.sql.Timestamp getBatchDatetime() {
        return batchDatetime;
    }

    public void setBatchDatetime(java.sql.Timestamp batchDatetime) {
        this.batchDatetime = batchDatetime;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(String batchStatus) {
        this.batchStatus = batchStatus;
    }

    public String getFsaCard() {
        return fsaCard;
    }

    public void setFsaCard(String fsaCard) {
        this.fsaCard = fsaCard;
    }

    public java.math.BigInteger getGlPostNum() {
        return glPostNum;
    }

    public void setGlPostNum(java.math.BigInteger glPostNum) {
        this.glPostNum = glPostNum;
    }

    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }

    public java.math.BigInteger getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(java.math.BigInteger orderNum) {
        this.orderNum = orderNum;
    }

    public String getPaymentCardNumber() {
        return paymentCardNumber;
    }

    public void setPaymentCardNumber(String paymentCardNumber) {
        this.paymentCardNumber = paymentCardNumber;
    }

    public String getPaymentCardSeqNum() {
        return paymentCardSeqNum;
    }

    public void setPaymentCardSeqNum(String paymentCardSeqNum) {
        this.paymentCardSeqNum = paymentCardSeqNum;
    }

    public String getPcAvsCode() {
        return pcAvsCode;
    }

    public void setPcAvsCode(String pcAvsCode) {
        this.pcAvsCode = pcAvsCode;
    }

    public java.sql.Timestamp getPcDatetime() {
        return pcDatetime;
    }

    public void setPcDatetime(java.sql.Timestamp pcDatetime) {
        this.pcDatetime = pcDatetime;
    }

    public String getPcLog() {
        return pcLog;
    }

    public void setPcLog(String pcLog) {
        this.pcLog = pcLog;
    }

    public Long getPcReplyAmount() {
        return pcReplyAmount;
    }

    public void setPcReplyAmount(Long pcReplyAmount) {
        this.pcReplyAmount = pcReplyAmount;
    }

    public String getPcReplyAuthCode() {
        return pcReplyAuthCode;
    }

    public void setPcReplyAuthCode(String pcReplyAuthCode) {
        this.pcReplyAuthCode = pcReplyAuthCode;
    }

    public byte[] getPcReplyMsg() {
        return pcReplyMsg;
    }

    public void setPcReplyMsg(byte[] pcReplyMsg) {
        this.pcReplyMsg = pcReplyMsg;
    }

    public String getPcReplyRcode() {
        return pcReplyRcode;
    }

    public void setPcReplyRcode(String pcReplyRcode) {
        this.pcReplyRcode = pcReplyRcode;
    }

    public String getPcReplyRequestId() {
        return pcReplyRequestId;
    }

    public void setPcReplyRequestId(String pcReplyRequestId) {
        this.pcReplyRequestId = pcReplyRequestId;
    }

    public String getPcReplyRflag() {
        return pcReplyRflag;
    }

    public void setPcReplyRflag(String pcReplyRflag) {
        this.pcReplyRflag = pcReplyRflag;
    }

    public String getPcReplyRmsg() {
        return pcReplyRmsg;
    }

    public void setPcReplyRmsg(String pcReplyRmsg) {
        this.pcReplyRmsg = pcReplyRmsg;
    }

    public java.math.BigInteger getPcReplySeqNum() {
        return pcReplySeqNum;
    }

    public void setPcReplySeqNum(java.math.BigInteger pcReplySeqNum) {
        this.pcReplySeqNum = pcReplySeqNum;
    }

    public String getPcReplyTimestamp() {
        return pcReplyTimestamp;
    }

    public void setPcReplyTimestamp(String pcReplyTimestamp) {
        this.pcReplyTimestamp = pcReplyTimestamp;
    }

    public String getPcReplyType() {
        return pcReplyType;
    }

    public void setPcReplyType(String pcReplyType) {
        this.pcReplyType = pcReplyType;
    }

    public byte[] getPcRequestMsg() {
        return pcRequestMsg;
    }

    public void setPcRequestMsg(byte[] pcRequestMsg) {
        this.pcRequestMsg = pcRequestMsg;
    }

    public Byte getPcSubStatusNum() {
        return pcSubStatusNum;
    }

    public void setPcSubStatusNum(Byte pcSubStatusNum) {
        this.pcSubStatusNum = pcSubStatusNum;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getRespAvsCode() {
        return respAvsCode;
    }

    public void setRespAvsCode(String respAvsCode) {
        this.respAvsCode = respAvsCode;
    }

    public java.sql.Timestamp getVaultBatchSettledDatetime() {
        return vaultBatchSettledDatetime;
    }

    public void setVaultBatchSettledDatetime(java.sql.Timestamp vaultBatchSettledDatetime) {
        this.vaultBatchSettledDatetime = vaultBatchSettledDatetime;
    }

    public String getVaultBillAddress1() {
        return vaultBillAddress1;
    }

    public void setVaultBillAddress1(String vaultBillAddress1) {
        this.vaultBillAddress1 = vaultBillAddress1;
    }

    public String getVaultBillAddress2() {
        return vaultBillAddress2;
    }

    public void setVaultBillAddress2(String vaultBillAddress2) {
        this.vaultBillAddress2 = vaultBillAddress2;
    }

    public String getVaultBillCity() {
        return vaultBillCity;
    }

    public void setVaultBillCity(String vaultBillCity) {
        this.vaultBillCity = vaultBillCity;
    }

    public String getVaultBillCountry() {
        return vaultBillCountry;
    }

    public void setVaultBillCountry(String vaultBillCountry) {
        this.vaultBillCountry = vaultBillCountry;
    }

    public String getVaultBillEmail() {
        return vaultBillEmail;
    }

    public void setVaultBillEmail(String vaultBillEmail) {
        this.vaultBillEmail = vaultBillEmail;
    }

    public String getVaultBillPhone() {
        return vaultBillPhone;
    }

    public void setVaultBillPhone(String vaultBillPhone) {
        this.vaultBillPhone = vaultBillPhone;
    }

    public String getVaultBillState() {
        return vaultBillState;
    }

    public void setVaultBillState(String vaultBillState) {
        this.vaultBillState = vaultBillState;
    }

    public String getVaultBillZip() {
        return vaultBillZip;
    }

    public void setVaultBillZip(String vaultBillZip) {
        this.vaultBillZip = vaultBillZip;
    }

    public String getVaultCardType() {
        return vaultCardType;
    }

    public void setVaultCardType(String vaultCardType) {
        this.vaultCardType = vaultCardType;
    }

    public String getVaultFirstName() {
        return vaultFirstName;
    }

    public void setVaultFirstName(String vaultFirstName) {
        this.vaultFirstName = vaultFirstName;
    }

    public String getVaultLastName() {
        return vaultLastName;
    }

    public void setVaultLastName(String vaultLastName) {
        this.vaultLastName = vaultLastName;
    }

    public String getVaultMonthExpire() {
        return vaultMonthExpire;
    }

    public void setVaultMonthExpire(String vaultMonthExpire) {
        this.vaultMonthExpire = vaultMonthExpire;
    }

    public String getVaultYearExpire() {
        return vaultYearExpire;
    }

    public void setVaultYearExpire(String vaultYearExpire) {
        this.vaultYearExpire = vaultYearExpire;
    }

}

