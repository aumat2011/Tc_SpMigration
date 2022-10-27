package com.humana.pharmacy.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * The dto to store information to be used while calculating remaining refills.
 */
@Getter
@Setter
public class RxDTO {
    /**
     * The script ID.
     */
    private BigInteger scriptId;

    /**
     * Total remaining refills.
     */
    private Integer refillsRemaining;

    /**
     * The written quantity.
     */
    private Long writtenQuantity;

    /**
     * The dispensed quantity.
     */
    private Long dispensedQuantity;

    /**
     * The last filled quantity.
     */
    private Long lastFillQuantity;
}

