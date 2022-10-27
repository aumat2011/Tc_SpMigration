package com.humana.pharmacy.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * The dto to store information from following tables:
 * <pre>
 * e_script_msg_attributes
 * products
 * rx_fill_aux
 * ok_hydrocodone_sanity_products</pre>
 */
@Getter
@Setter
public class RxEScriptDTO extends BaseEScriptDTO {
    /**
     * Rx refills left.
     */
    protected Integer rxRefillsLeft;

    /**
     * The written quantity.
     */
    protected Long writtenQuantity;

    /**
     * Dispensed quantity.
     */
    protected Long dispensedQuantity;

    /**
     * The Rx number.
     */
    protected String rxNumber;

    /**
     * Original number of refills
     */
    protected Long originalNumRefills;

    /**
     * Total refills
     */
    protected Long numRefills;

    /**
     * Fill status.
     */
    protected Byte fillStatusNum;

    /**
     * Filled dispensed quantity
     */
    protected Long fillQtyDispensed;

    /**
     * Total ORTF quantity.
     */
    protected Long ortfQty;

    /**
     * Product DEA
     */
    protected Byte prodDea;

    /**
     * The rxQtyLeft.
     */
    protected BigInteger rxQtyLeft;
}

