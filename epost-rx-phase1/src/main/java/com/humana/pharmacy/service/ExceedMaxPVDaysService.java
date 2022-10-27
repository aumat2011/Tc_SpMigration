package com.humana.pharmacy.service;

import com.humana.pharmacy.dto.ExceedMaxPVDaysResult;

import java.math.BigInteger;

/**
 * Service to check where order exceeds max PV days.
 */
public interface ExceedMaxPVDaysService {
    /**
     * Check where order exceeds max PV days.
     *
     * @param orderNum The order num. Required
     * @return Exceed max PV days result. Maybe null if no exceed found.
     */
    ExceedMaxPVDaysResult checkExceedMaxPVDays(BigInteger orderNum);
}

