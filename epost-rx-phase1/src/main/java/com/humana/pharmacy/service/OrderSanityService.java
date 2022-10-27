package com.humana.pharmacy.service;

import com.humana.pharmacy.dto.OrderSanityResult;

import java.math.BigInteger;

/**
 * Service to check order sanity.
 */
public interface OrderSanityService {
    /**
     * Check order sanity.
     *
     * @param orderNum      The order num. Required
     * @param queueToBeUsed The queue to be used. Optional
     * @return Order sanity check result. May be null if no error found
     */
    OrderSanityResult checkOrderSanity(BigInteger orderNum, String queueToBeUsed);
}

