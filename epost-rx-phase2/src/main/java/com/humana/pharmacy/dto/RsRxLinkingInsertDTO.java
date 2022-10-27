package com.humana.pharmacy.dto;

import com.humana.pharmacy.common.dto.BaseEScriptDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * The dto contains collected data that is used to insert RS RX linking.
 */
@Setter
@Getter
public class RsRxLinkingInsertDTO extends BaseEScriptDTO {
    /**
     * The edi transaction code.
     */
    private String ediTransactionCode;

    /**
     * The rx Expiration Date.
     */
    private Timestamp rxExpirationDate;

    /**
     * The Rx number.
     */
    private String rxNumber;

    /**
     * If there is a multi link or not.
     */
    private Integer hasMultiLink;

    /**
     * The patient num.
     */
    private BigInteger patientNum;

    /**
     * The dispensed drug sig.
     */
    private String dispensedDrugSig;

    /**
     * The dispensed drug daw.
     */
    private String dispensedDrugDaw;

    /**
     * The prod generic ref num.
     */
    private String prodGenericRefNum;

    /**
     * The sig group.
     */
    private Integer sigGroup;

    /**
     * The rx link dcco GPI.
     */
    private String rxLinkDccoGPIListGpi;
}
