package com.humana.pharmacy.common.service;

import com.humana.pharmacy.common.dto.AnsRxNarcCodeEScriptDTO;
import com.humana.pharmacy.common.dto.RsMultiLinkDTO;
import com.humana.pharmacy.common.dto.RxEScriptDTO;

import java.math.BigInteger;
import java.util.List;

/**
 * This service provides methods corresponding to original database functions.
 */
public interface FunctionsService {
    /**
     * Get escripts which are precheck required.
     *
     * <p>Corresponding to original database function F_itemPrecheckRequired.
     *
     * @param escriptIds the list of escript ids to test if they are precheck required. Required to be
     *                   non-empty
     * @return List of escript ids which are precheck required. May be empty
     */
    List<BigInteger> getPrecheckRequired(List<BigInteger> escriptIds);

    /**
     * Get escripts which are first time to fill.
     *
     * <p>Corresponding to original database function F_IsRxFirstTimeFill.
     *
     * @param escriptIds The list of escript ids to test if they are first time to fill. Required to
     *                   be non-empty
     * @return List of escript ids which are first time to fill. May be empty
     */
    List<BigInteger> getFirstTimeFill(List<BigInteger> escriptIds);

    /**
     * Get escripts which exceed max PV days.
     *
     * <p>Corresponding to original database function F_IsRxExceedMaxPVDays.
     *
     * @param escriptIds The list of escript ids to test if they exceed max PV days. Required to be
     *                   non-empty
     * @return List of escript ids to which exceed max PV days. May be empty
     */
    List<BigInteger> getExceedMaxPVDays(List<BigInteger> escriptIds);

    /**
     * Get escripts paid.
     *
     * <p>Corresponding to original database function F_isPrimaryEcsPaid.
     *
     * @param escriptIds The escript ids to check whether they are paid. Required to be non-empty
     * @return List of escript ids which are paid
     */
    List<BigInteger> getPrimaryEcsPaid(List<BigInteger> escriptIds);

    /**
     * Get total number of remaining refills for the specified Rx number.
     *
     * @param rxNumber      Rx number. Required.
     * @param rxEScriptDTOs List of RxEScriptDTO. Required
     * @return Total number of remaining refills.
     */
    Integer getRemainingRefills(String rxNumber, List<? extends RxEScriptDTO> rxEScriptDTOs);

    /**
     * Get total Rx quantity.
     *
     * <p>Implementation notes:
     *
     * @param scriptId      The scriptId. Required.
     * @param rxEScriptDTOs List of RxEScriptDTO. Required
     * @return Total Rx quantity.
     */
    Long getRxTotalQty(BigInteger scriptId, List<? extends RxEScriptDTO> rxEScriptDTOs);

    /**
     * Get total Rx quantity filled.
     *
     * @param rxNumber      The Rx number. Required
     * @param rxEScriptDTOs List of RxEScriptDTO. Required
     * @return Total Rx quantity filled.
     */
    Long getRxTotalQtyFilled(String rxNumber, List<? extends RxEScriptDTO> rxEScriptDTOs);

    /**
     * Get total ORTF quantity filled.
     *
     * @param rxNumber      The Rx number. Required.
     * @param rxEScriptDTOs List of RxEScriptDTO. Required
     * @return The total ORTF quantity filled.
     */
    Long getRxTotalORTFQtyFilled(String rxNumber, List<? extends RxEScriptDTO> rxEScriptDTOs);

    /**
     * Get total fills.
     *
     * @param scriptId      The scriptId. Required.
     * @param rxEScriptDTOs List of RxEScriptDTO. Required
     * @return Total fills
     */
    Long getRxTotalFills(BigInteger scriptId, List<? extends RxEScriptDTO> rxEScriptDTOs);

    /**
     * Gets claim paid non cash non self adjudicating.
     *
     * @param scriptId The scriptId. Required.
     * @return Claim paid non cash non self adjudicating.
     */
    public Integer getClaimPaidNonCashNonSelfAdjudicating(BigInteger scriptId);

    /**
     * Get product GRI class.
     *
     * @param escripts the escripts
     * @return the GPI class
     */
    String getGPIClass(List<AnsRxNarcCodeEScriptDTO> escripts);

    /**
     * Get the order that has the narcotic code.
     *
     * @param escripts the escripts
     * @return the narcotic code order
     */
    AnsRxNarcCodeEScriptDTO getOrderNarcCode(List<AnsRxNarcCodeEScriptDTO> escripts);

    /**
     * Get product dea.
     *
     * @param orderNarcCode the order narcotic code
     * @param escripts      the escripts
     * @return the product dea
     */
    Byte getDeaProduct(AnsRxNarcCodeEScriptDTO orderNarcCode, List<AnsRxNarcCodeEScriptDTO> escripts);

    /**
     * Get numberics from string.
     *
     * @param numStr the string include numbers
     * @return the number in the string
     */
    String getNumericsFromString(String numStr);

    /**
     * Check if the patient num is only active coverage cash.
     *
     * @param patientNum the patient number
     * @return <code>true</code> if only active coverage cash; <code>false</code> otherwise
     */
    Boolean isOnlyActiveCoverageCash(BigInteger patientNum);

    /**
     * Get the rx qty remaining.
     *
     * @param rxNumber      The rx number
     * @param rxEScriptDTOs The count of rx qty remaining
     * @return The list of rxEScriptDTO
     */
    Long getRxQtyRemaining(String rxNumber, List<? extends RxEScriptDTO> rxEScriptDTOs);

    /**
     * Get rsRxLinking parent and child.
     *
     * @param rxNumber The rx number
     * @param active   The active
     * @return The list of RsMultiLinkDTO
     */
    List<RsMultiLinkDTO> getRsRxLinkingParentChild(String rxNumber, String active);
}
