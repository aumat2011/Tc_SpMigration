package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * PatientPlans is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class PatientPlans {

    private Byte coverageTypeId;

    private java.sql.Timestamp cpActivationDate;

    private String cpAltPlanId;

    private Long cpCopayAmount;

    private java.sql.Timestamp cpDeactivationDate;

    private java.sql.Timestamp cpEffectiveDate;

    private java.sql.Timestamp cpExpirationDate;

    private String cpFirstName;

    private String cpHomePlan;

    private String cpHostPlan;

    private String cpLastName;

    private String cpMiddleName;

    private String cpPersonCode;

    private String cpPlanId;

    private String cpPlanIdExt;

    private String externalLink;

    private String overrideGroupId;

    private java.math.BigInteger patientNum;

    private Long ppNum;

    private Integer relationshipNum;

    private byte[] rowversion;

    private String sigSourceCode;

    public Byte getCoverageTypeId() {
        return coverageTypeId;
    }

    public void setCoverageTypeId(Byte coverageTypeId) {
        this.coverageTypeId = coverageTypeId;
    }

    public java.sql.Timestamp getCpActivationDate() {
        return cpActivationDate;
    }

    public void setCpActivationDate(java.sql.Timestamp cpActivationDate) {
        this.cpActivationDate = cpActivationDate;
    }

    public String getCpAltPlanId() {
        return cpAltPlanId;
    }

    public void setCpAltPlanId(String cpAltPlanId) {
        this.cpAltPlanId = cpAltPlanId;
    }

    public Long getCpCopayAmount() {
        return cpCopayAmount;
    }

    public void setCpCopayAmount(Long cpCopayAmount) {
        this.cpCopayAmount = cpCopayAmount;
    }

    public java.sql.Timestamp getCpDeactivationDate() {
        return cpDeactivationDate;
    }

    public void setCpDeactivationDate(java.sql.Timestamp cpDeactivationDate) {
        this.cpDeactivationDate = cpDeactivationDate;
    }

    public java.sql.Timestamp getCpEffectiveDate() {
        return cpEffectiveDate;
    }

    public void setCpEffectiveDate(java.sql.Timestamp cpEffectiveDate) {
        this.cpEffectiveDate = cpEffectiveDate;
    }

    public java.sql.Timestamp getCpExpirationDate() {
        return cpExpirationDate;
    }

    public void setCpExpirationDate(java.sql.Timestamp cpExpirationDate) {
        this.cpExpirationDate = cpExpirationDate;
    }

    public String getCpFirstName() {
        return cpFirstName;
    }

    public void setCpFirstName(String cpFirstName) {
        this.cpFirstName = cpFirstName;
    }

    public String getCpHomePlan() {
        return cpHomePlan;
    }

    public void setCpHomePlan(String cpHomePlan) {
        this.cpHomePlan = cpHomePlan;
    }

    public String getCpHostPlan() {
        return cpHostPlan;
    }

    public void setCpHostPlan(String cpHostPlan) {
        this.cpHostPlan = cpHostPlan;
    }

    public String getCpLastName() {
        return cpLastName;
    }

    public void setCpLastName(String cpLastName) {
        this.cpLastName = cpLastName;
    }

    public String getCpMiddleName() {
        return cpMiddleName;
    }

    public void setCpMiddleName(String cpMiddleName) {
        this.cpMiddleName = cpMiddleName;
    }

    public String getCpPersonCode() {
        return cpPersonCode;
    }

    public void setCpPersonCode(String cpPersonCode) {
        this.cpPersonCode = cpPersonCode;
    }

    public String getCpPlanId() {
        return cpPlanId;
    }

    public void setCpPlanId(String cpPlanId) {
        this.cpPlanId = cpPlanId;
    }

    public String getCpPlanIdExt() {
        return cpPlanIdExt;
    }

    public void setCpPlanIdExt(String cpPlanIdExt) {
        this.cpPlanIdExt = cpPlanIdExt;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    public String getOverrideGroupId() {
        return overrideGroupId;
    }

    public void setOverrideGroupId(String overrideGroupId) {
        this.overrideGroupId = overrideGroupId;
    }

    public java.math.BigInteger getPatientNum() {
        return patientNum;
    }

    public void setPatientNum(java.math.BigInteger patientNum) {
        this.patientNum = patientNum;
    }

    public Long getPpNum() {
        return ppNum;
    }

    public void setPpNum(Long ppNum) {
        this.ppNum = ppNum;
    }

    public Integer getRelationshipNum() {
        return relationshipNum;
    }

    public void setRelationshipNum(Integer relationshipNum) {
        this.relationshipNum = relationshipNum;
    }

    public byte[] getRowversion() {
        return rowversion;
    }

    public void setRowversion(byte[] rowversion) {
        this.rowversion = rowversion;
    }

    public String getSigSourceCode() {
        return sigSourceCode;
    }

    public void setSigSourceCode(String sigSourceCode) {
        this.sigSourceCode = sigSourceCode;
    }

}

