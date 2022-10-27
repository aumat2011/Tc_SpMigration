package com.humana.pharmacy.service;

import com.humana.pharmacy.dto.RxSanityResult;

import java.math.BigInteger;

/**
 * Service to check Rx sanity.
 */
public interface RxSanityService {
    /**
     * Check Rx sanity
     *
     * @param scriptId The scriptId. Required
     * @param rxNumber The rxNumber
     * @return Rx sanity check result. Will return NULL if no errors found.
     */
    RxSanityResult checkRxSanity(BigInteger scriptId, String rxNumber);
}

