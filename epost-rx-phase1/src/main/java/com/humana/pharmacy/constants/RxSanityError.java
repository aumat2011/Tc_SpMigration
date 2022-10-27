package com.humana.pharmacy.constants;

/**
 * This enum defines the error found for Rx sanity.
 */
public enum RxSanityError {
    /**
     * Represents the error when Rx contains hydrocodone product with refills.
     */
    HYDROCODONE_PRODUCT_WITH_REFILLS("Hydrocodone Product with Refills. Please Review.");

    /**
     * Error message. Set in constructor and will never change.
     */
    private String message;

    /**
     * Constructor of this enum.
     *
     * @param message Error message
     */
    private RxSanityError(String message) {
        this.message = message;
    }

    /**
     * Get error message.
     *
     * @return Error message.
     */
    public String getMessage() {
        return this.message;
    }
}

