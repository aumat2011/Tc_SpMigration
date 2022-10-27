package com.humana.pharmacy.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * The dto to store information from following tables:
 * e_script_msg_attributes
 * rx_fill_aux
 */
@Setter
@Getter
public class RsRxScriptDTO extends RxEScriptDTO {
    /**
     * The edi transaction code.
     */
    private String ediTransactionCode;

    /**
     * The rx expiration date.
     */
    private Timestamp rxExpirationDate;

    /**
     * The fill days supply.
     */
    private Integer fillDaysSupply;
}

