package com.humana.pharmacy.constants;

/**
 * The enum defines the error found for product level checks.
 */
public enum AnsRxProductError {
    /**
     * Represents the error of product level checks.
     */
    ITEM_CANNOT_BE_SHIPPED_TO_POBOX("ITEM CANNOT BE SHIPPED TO P.O. BOX"),
    /**
     * Represents the error of product level checks.
     */
    STATE_REQ_NY_TRIPLICATE_SERIAL_NUM_ON_FILE(
            "State Req(NY): requires rx triplicate serial# on-file for CS."),
    /**
     * Represents the error of product level checks.
     */
    STATE_REQ_TX_TRIPLICATE_SERIAL_NUM_ON_FILE(
            "State Req(TX): requires rx triplicate serial# on-file for CS.");

    /**
     * Error message. Set in constructor and will never change.
     */
    private final String message;

    /**
     * Constructor of this enum.
     *
     * @param message Error message
     */
    AnsRxProductError(String message) {
        this.message = message;
    }

    /**
     * Get error message.
     *
     * @return Error message
     */
    public String getMessage() {
        return this.message;
    }
}
