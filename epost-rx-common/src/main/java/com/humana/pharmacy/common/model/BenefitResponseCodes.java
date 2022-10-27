package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * BenefitResponseCodes is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class BenefitResponseCodes {

    private String approvalCode;

    private String displayMessage;

    private Long id;

    private String internalDescription;

    private String internalResponseCode;

    private String transactionRespCode;

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInternalDescription() {
        return internalDescription;
    }

    public void setInternalDescription(String internalDescription) {
        this.internalDescription = internalDescription;
    }

    public String getInternalResponseCode() {
        return internalResponseCode;
    }

    public void setInternalResponseCode(String internalResponseCode) {
        this.internalResponseCode = internalResponseCode;
    }

    public String getTransactionRespCode() {
        return transactionRespCode;
    }

    public void setTransactionRespCode(String transactionRespCode) {
        this.transactionRespCode = transactionRespCode;
    }

}

