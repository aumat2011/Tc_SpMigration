package com.humana.pharmacy.service;

import com.humana.pharmacy.dto.AnsRxSanityResult;

import java.math.BigInteger;

/**
 * Service to check ans rx sanity.
 */
public interface AnsRxSanityService {
    /**
     * Check ans rx sanity
     *
     * @param scriptId The scriptId. Required.
     * @param rxNumber The rxNumber.
     * @return Ans rx sanity check result. Will return null if no errors found.
     */
    AnsRxSanityResult checkAnsRxSanity(BigInteger scriptId, String rxNumber);
}

