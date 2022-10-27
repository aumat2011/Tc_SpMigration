package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * PatientAddressTypeAssignments is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class PatientAddressTypeAssignments {

    private Integer addressTypeNum;

    private Long patientAddrSeq;

    private java.math.BigInteger patientNum;

    private java.sql.Timestamp updated;

    private Long updatedUserNum;

    public Integer getAddressTypeNum() {
        return addressTypeNum;
    }

    public void setAddressTypeNum(Integer addressTypeNum) {
        this.addressTypeNum = addressTypeNum;
    }

    public Long getPatientAddrSeq() {
        return patientAddrSeq;
    }

    public void setPatientAddrSeq(Long patientAddrSeq) {
        this.patientAddrSeq = patientAddrSeq;
    }

    public java.math.BigInteger getPatientNum() {
        return patientNum;
    }

    public void setPatientNum(java.math.BigInteger patientNum) {
        this.patientNum = patientNum;
    }

    public java.sql.Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(java.sql.Timestamp updated) {
        this.updated = updated;
    }

    public Long getUpdatedUserNum() {
        return updatedUserNum;
    }

    public void setUpdatedUserNum(Long updatedUserNum) {
        this.updatedUserNum = updatedUserNum;
    }

}

