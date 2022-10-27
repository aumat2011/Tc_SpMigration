package com.humana.pharmacy.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * The dto contains the Rx/Plan Parameters result.
 */
@Setter
@Getter
public class AnsDefaultRxPlanParamsResult {
    /**
     * The narcotic code.
     */
    private String narcoticCode;

    /**
     * The num refills allowed.
     */
    private Integer numRefillsAllowed;

    /**
     * The num days from orig date.
     */
    private Integer numDaysFromOrigDate;

    /**
     * The num days expire.
     */
    private Integer numDaysExpire;

    /**
     * The max days supply.
     */
    private Integer maxDaysSupply;

    /**
     * The min pct utilization.
     */
    private Integer minPctUtil;

    /**
     * The accept fax.
     */
    private String acceptFax;

    /**
     * The plan max refill allowed.
     */
    private Short planNumRefillsAllowed;

    /**
     * The plan min util pct.
     */
    private Integer planMinPctUtil;

    /**
     * The plan max util pct.
     */
    private Integer planMaxPctUtil;

    /**
     * The plan min quantity.
     */
    private Long planMinQuantity;

    /**
     * The plan max quantity.
     */
    private Long planMaxQuantity;

    /**
     * The plan min days supply.
     */
    private Short planMinDaysSupply;

    /**
     * The plan max days supply.
     */
    private Short planMaxDaysSupply;

    /**
     * The future1.
     */
    private String future1;

    /**
     * The future2.
     */
    private String future2;

    /**
     * The future3.
     */
    private String future3;

    /**
     * The future4.
     */
    private String future4;

    /**
     * The future5.
     */
    private String future5;

    /**
     * The future6.
     */
    private String future6;

    /**
     * The future7.
     */
    private String future7;
}

