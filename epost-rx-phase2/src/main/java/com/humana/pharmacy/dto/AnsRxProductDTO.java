package com.humana.pharmacy.dto;

import com.humana.pharmacy.common.dto.BaseEScriptDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * The dto contains collected data that is used in product level checks.
 */
@Getter
@Setter
public class AnsRxProductDTO extends BaseEScriptDTO {

    /**
     * The dispensed product id.
     */
    private Long dispensedProductId;

    /**
     * The trading partner num.
     */
    private Long tradingPartnerNum;

    /**
     * The state code.
     */
    private String stateCode;

    /**
     * The prod dea.
     */
    private Byte prodDea;

    /**
     * The prod generic ref num.
     */
    private String prodGenericRefNum;

    /**
     * The state cs products prod dea.
     */
    private Short stateCsProductsProdDea;

    /**
     * The target value.
     */
    private String targetValue;

    /**
     * The patient address.
     */
    private String patientAddress;

    /**
     * The patient address2.
     */
    private String patientAddress2;

    /**
     * The ship method id.
     */
    private Integer shipMethodId;

    /**
     * The triplicate serial num.
     */
    private String triplicateSerialNum;
}
