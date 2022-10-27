package com.humana.pharmacy.service;

import com.humana.pharmacy.dto.AnsDefaultRxPlanParamsResult;

import java.math.BigInteger;

/**
 * Service to get the Rx/Plan Parameters.
 */
public interface AnsDefaultRxPlanParamsService {
    /**
     * Get the Rx/Plan Parameters.
     *
     * @param prodId   The prod id. Optional
     * @param ppNum    The pp num. Optional
     * @param scriptId The script id. Optional
     * @return The Rx/Plan Parameters result
     */
    AnsDefaultRxPlanParamsResult getAnsDefaultRxPlanParams(Long prodId, Long ppNum, BigInteger scriptId);
}

