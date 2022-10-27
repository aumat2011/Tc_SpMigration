package com.humana.pharmacy.service;

import java.math.BigInteger;

/**
 * Service to insert RS RX linking.
 */
public interface RsRxLinkingInsertService {
    /**
     * Insert RS RX linking.
     *
     * @param eScriptMsgAttributeSeq The EScript msg attribute seq. Required
     * @param userNum                The user number. Required
     * @param mode                   The mode ('I': INSERT; 'D': DEACTIVATE). Required
     * @param deactivationComment    The deactivation comment. Optional
     * @param rFlag                  The R flag. Optional
     */
    public void insertRsRxLinking(
            BigInteger eScriptMsgAttributeSeq,
            Long userNum,
            String mode,
            String deactivationComment,
            String rFlag);
}
