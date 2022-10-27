package com.humana.pharmacy.dto;

import com.humana.pharmacy.common.dto.RxEScriptDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * The dto to store information from following tables:
 * e_script_msg_attributes
 * rx_fill_aux
 * order_lines
 * order_header
 * patient_attributes
 * rule_queue_exception
 * patient_plans
 * payor_plans
 * trading_partner_general
 * quantity_change_tracking
 * auto_escript_filler
 */
@Setter
@Getter
public class RsRxLinkingSubstitutionDTO extends RxEScriptDTO {
    /**
     * The rx Expiration Date.
     */
    private Timestamp rxExpirationDate;

    /**
     * The written Date.
     */
    private Timestamp writtenDate;

    /**
     * The written product id.
     */
    private Long writtenProductId;

    /**
     * The dispensed product id.
     */
    private Long dispensedProductId;

    /**
     * The trading partner num.
     */
    private Long tradingPartnerNum;

    /**
     * The origination num.
     */
    private Integer originationNum;

    /**
     * The order invoice number.
     */
    private String orderInvoiceNumber;

    /**
     * The fill days supply.
     */
    private Integer fillDaysSupply;

    /**
     * The allow consolidate.
     */
    private String allowConsolidate;

    /**
     * The pp num.
     */
    private Long ppNum;

    /**
     * The order num.
     */
    private BigInteger orderNum;

    /**
     * The order line status num.
     */
    private Byte orderLineStatusNum;

    /**
     * The is quantity lowered.
     */
    private String isQuantityLowered;

    /**
     * The auto efiller seq.
     */
    private BigInteger autoEfillerSeq;

    /**
     * The prod consolidate.
     */
    private String prodConsolidate;

    /**
     * The Order header's order invoice number.
     */
    private String ohOrderInvoiceNumber;

    /**
     * The order status num.
     */
    private Byte orderStatusNum;

    /**
     * The coverage type id.
     */
    private Byte coverageTypeId;

    /**
     * The payor pp num.
     */
    private Long payorPpNum;

    /**
     * The pp plan name.
     */
    private String ppPlanName;

    /**
     * The rule script id.
     */
    private BigInteger ruleScriptId;

    /**
     * The rx status code num.
     */
    private Byte rxStatusCodeNum;

    /**
     * The tp name.
     */
    private String tpName;


}

