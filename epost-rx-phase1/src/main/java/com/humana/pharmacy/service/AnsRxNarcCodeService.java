package com.humana.pharmacy.service;

import com.humana.pharmacy.dto.AnsRxNarcCodeResult;

import java.math.BigInteger;

/**
 * Service to check ans rx narcotic code.
 */
public interface AnsRxNarcCodeService {
    /**
     * Check narcotic code by scriptId.
     *
     * @param scriptId The scriptId. Required
     * @param rxNumber The rxNumber. Optional
     * @return Narcotic code check result. Will return null if no errors found.
     */
    AnsRxNarcCodeResult checkAnsRxNarcCode(BigInteger scriptId, String rxNumber);
}

