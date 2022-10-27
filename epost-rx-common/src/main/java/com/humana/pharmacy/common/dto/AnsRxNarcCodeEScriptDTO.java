package com.humana.pharmacy.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * The dto contains collected data that is used to check product narcotic code.
 */
@Setter
@Getter
public class AnsRxNarcCodeEScriptDTO extends BaseEScriptDTO {
    /**
     * The edi transaction code.
     */
    private String ediTransactionCode;

    /**
     * The Rx expiration date.
     */
    private Timestamp rxExpirationDate;

    /**
     * The num refills.
     */
    private Long numRefills;

    /**
     * The written date.
     */
    private Timestamp writtenDate;

    /**
     * The origination num.
     */
    private Integer originationNum;

    /**
     * The order invoice number.
     */
    private String orderInvoiceNumber;

    /**
     * The Rx number.
     */
    private String rxNumber;

    /**
     * The prod generic ref num.
     */
    private String prodGenericRefNum;

    /**
     * The scp prod dea.
     */
    private Short scpProdDea;

    /**
     * The prod dea.
     */
    private Byte prodDea;

    /**
     * The order num.
     */
    private BigInteger orderNum;

    /**
     * The state num.
     */
    private Integer stateNum;

    /**
     * The narcotic code.
     */
    private String narcoticCode;

    /**
     * The narc num refills.
     */
    private Integer narcNumRefills;

    /**
     * The narc num days expire.
     */
    private Integer narcNumDaysExpire;

    /**
     * The narc num days supply.
     */
    private Integer narcNumDaysSupply;

    /**
     * The narc num days original.
     */
    private Integer narcNumDaysOriginal;

    /**
     * The narc accept fax.
     */
    private String narcAcceptFax;

    /**
     * The state name.
     */
    private String stateName;

    /**
     * The state code.
     */
    private String stateCode;

    /**
     * The tpa state code.
     */
    private String tpaStateCode;

    /**
     * The gpi class id.
     */
    private String gpiClassId;

    /**
     * The narc num refills gpi.
     */
    private Integer narcNumRefillsGpi;

    /**
     * The narc num days expire gpi.
     */
    private Integer narcNumDaysExpireGpi;

    /**
     * The narc num days supply gpi.
     */
    private Integer narcNumDaysSupplyGpi;

    /**
     * The narc num days original gpi.
     */
    private Integer narcNumDaysOriginalGpi;

    /**
     * The narc accept fax gpi.
     */
    private String narcAcceptFaxGpi;

    /**
     * The state cs product seq.
     */
    private Long stateCsProductSeq;
}

