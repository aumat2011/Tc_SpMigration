package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * ThirdPartyClaimResponses is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class ThirdPartyClaimResponses {

    private String authorizationId;

    private java.sql.Timestamp captureDate;

    private String errorCode;

    private String errorDescription;

    private Long id;

    private Long redeemedAmount;

    private Short redeemedQuantity;

    private Long responseCode;

    private String stanId;

    private String transactionId;

    public String getAuthorizationId() {
        return authorizationId;
    }

    public void setAuthorizationId(String authorizationId) {
        this.authorizationId = authorizationId;
    }

    public java.sql.Timestamp getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(java.sql.Timestamp captureDate) {
        this.captureDate = captureDate;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRedeemedAmount() {
        return redeemedAmount;
    }

    public void setRedeemedAmount(Long redeemedAmount) {
        this.redeemedAmount = redeemedAmount;
    }

    public Short getRedeemedQuantity() {
        return redeemedQuantity;
    }

    public void setRedeemedQuantity(Short redeemedQuantity) {
        this.redeemedQuantity = redeemedQuantity;
    }

    public Long getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Long responseCode) {
        this.responseCode = responseCode;
    }

    public String getStanId() {
        return stanId;
    }

    public void setStanId(String stanId) {
        this.stanId = stanId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

}

