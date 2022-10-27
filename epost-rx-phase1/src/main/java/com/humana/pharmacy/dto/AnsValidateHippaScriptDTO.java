package com.humana.pharmacy.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * The dto contains collected data that is used to check AnsValidateHippaAddress base on the table e_script_msg_attributes.
 */
@Setter
@Getter
public class AnsValidateHippaScriptDTO {
    /**
     * The e_script_msg_attributes's patient num.
     */
    private BigInteger patientNum;

    /**
     * The patient_address's patient num.
     */
    private BigInteger paPatientNum;

    /**
     * The order status num.
     */
    private Byte orderStatusNum;

    /**
     * The patient family's family id.
     */
    private String pfFamilyId;

    /**
     * The order bill ship's family id.
     */
    private String familyId;

    /**
     * The order line status num.
     */
    private Byte orderLineStatusNum;

    /**
     * The patient_plans's pp num.
     */
    private Long ppNum;

    /**
     * The otc pp num.
     */
    private Long otcPpnum;

    /**
     * The rx_fill_aux's pp num.
     */
    private Long rfPpNum;

    /**
     * The patient day of birthday.
     */
    private Timestamp patientDob;

    /**
     * The general status code.
     */
    private String generalStatusCode;

    /**
     * The relationship num.
     */
    private Integer relationshipNum;
}

