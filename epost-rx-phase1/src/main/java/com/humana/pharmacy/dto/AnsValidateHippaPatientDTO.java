package com.humana.pharmacy.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * The dto contains collected data that is used to check AnsValidateHippaAddress base on the table patient_address.
 */
@Setter
@Getter
public class AnsValidateHippaPatientDTO {
    /**
     * The patient num.
     */
    private BigInteger patientNum;

    /**
     * The family id.
     */
    private String familyId;

    /**
     * The consent age.
     */
    private Byte consentAge;

    /**
     * The patient day of birthday.
     */
    private Timestamp patientDob;

    /**
     * The active.
     */
    private String active;

    /**
     * The patient address.
     */
    private String patientAddress;

    /**
     * The address type num.
     */
    private Integer addressTypeNum;

    /**
     * The patient address2.
     */
    private String patientAddress2;

    /**
     * The patient address seq.
     */
    private Long patientAddrSeq;

    /**
     * The csz zip num.
     */
    private Long cszZipNum;
}

