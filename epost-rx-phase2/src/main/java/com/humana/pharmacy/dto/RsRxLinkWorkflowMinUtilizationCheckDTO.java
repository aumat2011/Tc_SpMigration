package com.humana.pharmacy.dto;

import com.humana.pharmacy.common.dto.BaseEScriptDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * The dto contains collected data that is used to check Rs Rx link workflow min utilization.
 */
@Setter
@Getter
public class RsRxLinkWorkflowMinUtilizationCheckDTO extends BaseEScriptDTO {
    /**
     * The fill days supply.
     */
    private Integer fillDaysSupply;

    /**
     * The fill dispense date.
     */
    private Timestamp fillDispenseDate;

    /**
     * The prod generic ref num.
     */
    private String prodGenericRefNum;

    /**
     * The child escript msg attribute seq.
     */
    private BigInteger childEScriptMsgAttributeSeq;

    /**
     * The rx number.
     */
    private String rxNumber;

    /**
     * The fill status num.
     */
    private Byte fillStatusNum;

    /**
     * The edi transaction code.
     */
    private String ediTransactionCode;

    /**
     * The order invoice number.
     */
    private String orderInvoiceNumber;

    /**
     * The address type num.
     */
    private Integer addressTypeNum;

    /**
     * The patient addr seq.
     */
    private Long patientAddrSeq;

    /**
     * The state cs product seq.
     */
    private Long stateCsProductSeq;

    /**
     * The state cs product state num.
     */
    private Integer stateCsProductStateNum;

    /**
     * The prod dea.
     */
    private Byte prodDea;

    /**
     * The state cs product prod dea.
     */
    private Short stateCsProductProdDea;
}

