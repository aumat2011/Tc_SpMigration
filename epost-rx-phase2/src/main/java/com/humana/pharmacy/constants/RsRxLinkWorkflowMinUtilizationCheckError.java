package com.humana.pharmacy.constants;

/**
 * The enum defines the error found for Rs Rx link workflow min utilization check.
 */
public enum RsRxLinkWorkflowMinUtilizationCheckError {
    /**
     * Represents the error of Rs Rx link workflow min utilization check.
     */
    ITEM_IN_WORKFLOW("ITEM IN WORKFLOW"),

    /**
     * Represents the error of Rs Rx link workflow min utilization check.
     */
    STATE_GPI_MIN_UTILIZATION_NOT_MET("%s STATE\\GPI CLASS MIN UTILIZATION NOT MET. REFILL ELIGIBLE ON:%s"),

    /**
     * Represents the error of Rs Rx link workflow min utilization check.
     */
    STATE_NARC_MIN_UTILIZATION_NOT_MET("%s STATE NARC MIN UTILIZATION NOT MET. REFILL ELIGIBLE ON:%s");

    /**
     * Error message. Set in constructor and will never change.
     */
    private final String message;

    /**
     * Error message parameters.
     */
    private Object[] params;

    /**
     * Constructor of this enum.
     *
     * @param message Error message
     */
    private RsRxLinkWorkflowMinUtilizationCheckError(String message) {
        this.message = message;
    }

    /**
     * Add error message parameters.
     *
     * @param params Substitution parameters
     * @return Error message
     */
    public RsRxLinkWorkflowMinUtilizationCheckError withParams(Object... params) {
        this.params = params;
        return this;
    }

    /**
     * Get error message.
     *
     * @return Error message
     */
    public String getMessage() {
        return String.format(this.message, params);
    }
}

