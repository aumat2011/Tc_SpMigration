package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * PatientFamily is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class PatientFamily {

    private String conversionLink;

    private String creditAccountStatus;

    private Long creditLimit;

    private String familyId;

    private String headOfHousehold;

    private Long patientFamilySeq;

    private java.math.BigInteger patientNum;

    private String webAccess;

    public String getConversionLink() {
        return conversionLink;
    }

    public void setConversionLink(String conversionLink) {
        this.conversionLink = conversionLink;
    }

    public String getCreditAccountStatus() {
        return creditAccountStatus;
    }

    public void setCreditAccountStatus(String creditAccountStatus) {
        this.creditAccountStatus = creditAccountStatus;
    }

    public Long getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Long creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    public String getHeadOfHousehold() {
        return headOfHousehold;
    }

    public void setHeadOfHousehold(String headOfHousehold) {
        this.headOfHousehold = headOfHousehold;
    }

    public Long getPatientFamilySeq() {
        return patientFamilySeq;
    }

    public void setPatientFamilySeq(Long patientFamilySeq) {
        this.patientFamilySeq = patientFamilySeq;
    }

    public java.math.BigInteger getPatientNum() {
        return patientNum;
    }

    public void setPatientNum(java.math.BigInteger patientNum) {
        this.patientNum = patientNum;
    }

    public String getWebAccess() {
        return webAccess;
    }

    public void setWebAccess(String webAccess) {
        this.webAccess = webAccess;
    }

}

