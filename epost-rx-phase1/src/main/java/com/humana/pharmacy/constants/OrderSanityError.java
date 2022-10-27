package com.humana.pharmacy.constants;

/**
 * This enum definies the error found for order sanity.
 */
public enum OrderSanityError {
    /**
     * Represents the error when order contains more than one member.
     */
    MULTIPLE_PATIENTS("Order contains more than one member - Need to verify"),

    /**
     * Represents the error when order contains expired Rx.
     */
    EXPIRED_RX("Order contains expired Rx"),

    /**
     * Represents the error when order contains Rx has insufficient quantity to fill.
     */
    INSUFFICIENT_RX("Order contains Rx has insufficient quantity to fill"),

    /**
     * Represents the error when order contains RXs without a paid ECS status.
     */
    NOT_PAID_RX("Order contains RXs WITHOUT A PAID ECS STATUS. Please verify."),

    /**
     * Represents the error when order is not open.
     */
    ORDER_NOT_OPEN("Order is not open!"),

    /**
     * Represents the error when a patient on order is not valid.
     */
    WRONG_PATIENT_ON_ORDER("A patient on this order is *NOT* valid (please verify all members)."),

    /**
     * Represents the error when address does not belong to a member within the shipment.
     */
    HIPAA_BAD_ADDRESS("HIPAA ship-to violation.  Address does not belong to a member within the shipment"),

    /**
     * Represents the error when there is no bill address.
     */
    NO_BILL_ADDRESS("Addr Error.  No Default Bill on file"),

    /**
     * Represents the error when there is no ship address.
     */
    NO_SHIP_ADDRESS("Addr Error.  No Default Ship on File"),

    /**
     * Represents the error when there is no proper ship member.
     */
    NO_PROPER_SHIP_MEMBER("Addr Error.  No Proper Ship Member"),

    /**
     * Represents the error when order does not have a valid AUTH record.
     */
    NO_AUTH_RECORD("Order with CC Payment Method does not have a valid AUTH record (please verify).");

    /**
     * Error message. Set in constructor and will never change.
     */
    private final String error;

    /**
     * Constructor of this enum.
     *
     * @param error The error message
     */
    OrderSanityError(String error) {
        this.error = error;
    }

    /**
     * Get error message.
     *
     * @return Error message
     */
    public String getError() {
        return error;
    }
}

