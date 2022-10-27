package com.humana.pharmacy.service;

import com.humana.pharmacy.dto.AnsValidateHippaAddressResult;

import java.math.BigInteger;

/**
 * Service to check ans validate hippa address.
 */
public interface AnsValidateHippaAddressService {
    /**
     * Check ans validate hippa address by orderNum.
     *
     * @param orderNum The orderNum. Required
     * @return Ans validate hippa address check result. Will return null if no errors found.
     */
    public AnsValidateHippaAddressResult checkAnsValidateHippaAddress(BigInteger orderNum);
}

