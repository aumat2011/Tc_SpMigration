package com.humana.pharmacy.service;

import com.humana.pharmacy.dto.AnsRxProductResult;

import java.math.BigInteger;

/**
 * Service for customers to add their own custom product level checks.
 */
public interface AnsRxProductService {
    /**
     * Sanity check for customers to add their own custom product level checks. eturns null if no
     * hits, a message(s) if there are hits.
     *
     * @param scriptId The scriptId. Required.
     * @return product level check result. Will return null if no errors found.
     */
    AnsRxProductResult checkAnsRxProduct(BigInteger scriptId);
}
