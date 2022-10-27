package com.humana.pharmacy.service;

import java.math.BigInteger;

/**
 * Service to check duplicate GPI for patient.
 *
 * <p>Duplicate GPI means patient's orders having same prod_generic_ref_num.
 *
 * <p>It is possible that duplicate GPI exist within a same order, or exist across multiple orders.
 *
 * <p>It is also possible that not only one prod_generic_ref_num is duplicated, but multiple of
 * them.
 *
 * <p>For example, assuming a patient has 3 orders: Order1, Order2 and Order2. And Order1 has
 * [p_num1, p_num1, p_num2, p_num2, p_num3], Order2 has [p_num2], Order3 has [p_num3]. Then
 * duplicate exist: 1. p_num1 and p_num2 are duplicated within same Order1. 2. p_num2 is duplicated
 * across Order1 and Order2. 3. p_num3 is duplicated across Order1 and Order3.
 */
public interface DuplicateGPIService {
    /**
     * Check duplicate GPI for given patient and order invoice number.
     *
     * @param patientNum      The patient number. Required to be non-null.
     * @param orderInvoiceNum The order invoice number to check duplicate. Required to be non-empty.
     * @return Duplicate message. Empty string means no duplicate found
     */
    String checkDuplicateGPI(BigInteger patientNum, String orderInvoiceNum);
}
