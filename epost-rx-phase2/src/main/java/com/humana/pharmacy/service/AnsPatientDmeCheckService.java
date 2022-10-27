package com.humana.pharmacy.service;

import com.humana.pharmacy.dto.AnsPatientDmeCheckResult;
import java.math.BigInteger;

/**
 * Fonksiyon ne iş yapıyorsa onu yazacağız.
 */
public interface AnsPatientDmeCheckService {
    /**
     * Sanity check for customers to add their own custom product level checks. eturns null if no
     * hits, a message(s) if there are hits.
     *
     * @param scriptId The scriptId. Required.
     * @return product level check result. Will return null if no errors found.
     * Burası sp'ye göre düzeltilecek.
     */
    AnsPatientDmeCheckResult checkPatientDme(BigInteger scriptId);
}
