package com.humana.pharmacy.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * The dto contains collected data that is used to check AnsValidateHippaAddress base on the table order_billship.
 */
@Setter
@Getter
public class AnsValidateHippaOrderDTO {
    /**
     * The consent age.
     */
    private Byte consentAge;

    /**
     * The force ship address.
     */
    private String forceShipAddress;

    /**
     * The patient num.
     */
    private BigInteger patientNum;

    /**
     * The patient ship address seq.
     */
    private Long patientShipAddressSeq;

    /**
     * The patient address seq.
     */
    private Long patientAddressSeq;

    /**
     * The patient address.
     */
    private String patientAddress;

    /**
     * The patient address2.
     */
    private String patientAddress2;

    /**
     * The active.
     */
    private String active;

    /**
     * The verified.
     */
    private Boolean verified;

    /**
     * The override reason id.
     */
    private Byte overrideReasonId;

    /**
     * The last verification.
     */
    private Timestamp lastVerification;

    /**
     * The family id.
     */
    private String familyId;

    /**
     * The ship method id.
     */
    private Integer shipMethodId;
}

