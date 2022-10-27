package com.humana.pharmacy.dto;

import com.humana.pharmacy.common.dto.BaseEScriptDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * The dto contains collected data that is used get the Rx/Plan Parameters.
 */
@Setter
@Getter
public class AnsDefaultRxPlanParamsDTO extends BaseEScriptDTO {
    /**
     * The dispensed product id.
     */
    private Long dispensedProductId;

    /**
     * The order invoice number.
     */
    private String orderInvoiceNumber;

    /**
     * The patient addr seq.
     */
    private Long patientAddrSeq;

    /**
     * The state cs product seq.
     */
    private Long stateCsProductSeq;

    /**
     * The prod id.
     */
    private Long prodId;

    /**
     * The prod dea.
     */
    private Byte prodDea;

    /**
     * The state cs product prod dea.
     */
    private Short stateCsProductProdDea;

    /**
     * The state cs product prod id.
     */
    private Long stateCsProductProdId;
}

