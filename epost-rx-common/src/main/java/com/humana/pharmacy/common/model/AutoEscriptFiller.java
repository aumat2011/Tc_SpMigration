package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * AutoEscriptFiller is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class AutoEscriptFiller {

    private java.sql.Timestamp autoEfillerDatetime;

    private java.math.BigInteger autoEfillerSeq;

    private String batchNumber;

    private String createOrder;

    private java.math.BigInteger escriptId;

    private String locationNum;

    private String logNote;

    private java.sql.Timestamp orderCreationDate;

    private String orderInvoiceNumber;

    private java.math.BigInteger orderNum;

    private java.math.BigInteger patientNum;

    private java.sql.Timestamp processedDatetime;

    private String scanServer;

    private Integer status;

    public java.sql.Timestamp getAutoEfillerDatetime() {
        return autoEfillerDatetime;
    }

    public void setAutoEfillerDatetime(java.sql.Timestamp autoEfillerDatetime) {
        this.autoEfillerDatetime = autoEfillerDatetime;
    }

    public java.math.BigInteger getAutoEfillerSeq() {
        return autoEfillerSeq;
    }

    public void setAutoEfillerSeq(java.math.BigInteger autoEfillerSeq) {
        this.autoEfillerSeq = autoEfillerSeq;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getCreateOrder() {
        return createOrder;
    }

    public void setCreateOrder(String createOrder) {
        this.createOrder = createOrder;
    }

    public java.math.BigInteger getEscriptId() {
        return escriptId;
    }

    public void setEscriptId(java.math.BigInteger escriptId) {
        this.escriptId = escriptId;
    }

    public String getLocationNum() {
        return locationNum;
    }

    public void setLocationNum(String locationNum) {
        this.locationNum = locationNum;
    }

    public String getLogNote() {
        return logNote;
    }

    public void setLogNote(String logNote) {
        this.logNote = logNote;
    }

    public java.sql.Timestamp getOrderCreationDate() {
        return orderCreationDate;
    }

    public void setOrderCreationDate(java.sql.Timestamp orderCreationDate) {
        this.orderCreationDate = orderCreationDate;
    }

    public String getOrderInvoiceNumber() {
        return orderInvoiceNumber;
    }

    public void setOrderInvoiceNumber(String orderInvoiceNumber) {
        this.orderInvoiceNumber = orderInvoiceNumber;
    }

    public java.math.BigInteger getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(java.math.BigInteger orderNum) {
        this.orderNum = orderNum;
    }

    public java.math.BigInteger getPatientNum() {
        return patientNum;
    }

    public void setPatientNum(java.math.BigInteger patientNum) {
        this.patientNum = patientNum;
    }

    public java.sql.Timestamp getProcessedDatetime() {
        return processedDatetime;
    }

    public void setProcessedDatetime(java.sql.Timestamp processedDatetime) {
        this.processedDatetime = processedDatetime;
    }

    public String getScanServer() {
        return scanServer;
    }

    public void setScanServer(String scanServer) {
        this.scanServer = scanServer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}

