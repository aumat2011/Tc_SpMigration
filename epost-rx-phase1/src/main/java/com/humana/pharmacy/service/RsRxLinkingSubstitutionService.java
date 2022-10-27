package com.humana.pharmacy.service;

import java.math.BigInteger;

/**
 * Service to check rsRxLinkingSubstitution.
 */
public interface RsRxLinkingSubstitutionService {
    /**
     * Get subEScriptMsgAttributeSeq for eScriptMsgAttributeSeq.
     *
     * @param eScriptMsgAttributeSeq The scriptId. Required
     * @return The sub scriptId
     */
    BigInteger getSubEScriptMsgAttributeSeq(BigInteger eScriptMsgAttributeSeq);
}

