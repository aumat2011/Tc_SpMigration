package com.humana.pharmacy.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * The dto contains collected data that is used to check AnsValidateHippaAddress base on the table patient_family.
 */
@Setter
@Getter
public class AnsValidateHippaFamilyDTO {
    /**
     * The patient num.
     */
    private BigInteger patientNum;

    /**
     * The head of house hold.
     */
    private String headOfHousehold;

    /**
     * The pp num.
     */
    private Long ppNum;

    /**
     * The relationship num.
     */
    private Integer relationshipNum;

    /**
     * The coverage type id.
     */
    private Byte coverageTypeId;

    /**
     * The patient address seq.
     */
    private Long patientAddrSeq;
}

