package com.humana.pharmacy.constants;

/**
 * The enum defines the error found for narcotic code check.
 */
public enum AnsRxNarcCodeError {
    /**
     * Represents the error of narcotic code check.
     */
    STATE_GPI_CLASS_LIMITS_NUMBER_REFILLS("%s STATE\\GPI CLASS LIMITS NUMBER OF REFILLS TO: %s RX HAS: %s"),

    /**
     * Represents the error of narcotic code check.
     */
    STATE_GPI_CLASS_LIMITS_DAYS_ORIGINAL("%s STATE\\GPI CLASS LIMITS DAYS FROM ORIGINAL: %s RX DAYS: %s"),

    /**
     * Represents the error of narcotic code check.
     */
    STATE_GPI_CLASS_LIMITS_DAYS_SUPPLY("%s STATE\\GPI CLASS LIMITS DAYS SUPPLY AS: %s RX DAYS SUPPLY: %s"),

    /**
     * Represents the error of narcotic code check.
     */
    STATE_GPI_CLASS_PROHIBITS_ELECTRONIC("STATE: %s GPI CLASS PROHIBITS ELECTRONIC TRANSMISSION (FAX/ERX) OF CONTROLLED SUBSTANCE RX."),

    /**
     * Represents the error of narcotic code check.
     */
    STATE_LA_DOCTOR_RULE("LA in-state doctor rule. CII & CIII opioid prescriptions must be written by a LA licensed physician"),

    /**
     * Represents the error of narcotic code check.
     */
    STATE_LAW_LIMITS_NUMBER_REFILLS("%s STATE LAW LIMITS NUMBER OF REFILLS TO: %s RX HAS: %s"),

    /**
     * Represents the error of narcotic code check.
     */
    STATE_LAW_LIMITS_DAYS_ORIGINAL("%s STATE LAW LIMITS DAYS FROM ORIGINAL: %s RX DAYS: %s"),

    /**
     * Represents the error of narcotic code check.
     */
    STATE_LAW_LIMITS_DAYS_SUPPLY("%s STATE LAW LIMITS DAYS SUPPLY AS: %s RX DAYS SUPPLY: %s"),

    /**
     * Represents the error of narcotic code check.
     */
    STATE_LAW_PROHIBITS_ELECTRONIC("EPOSTRX NARCOTIC CODE PARAMETERS FOR STATE: %s PROHIBITS ELECTRONIC TRANSMISSION (FAX/ERX) OF CONTROLLED SUBSTANCE RX.");

    /**
     * Error message. Set in constructor and will never change.
     */
    private String message;

    /**
     * Error message parameters.
     */
    private Object[] params;

    /**
     * Constructor of this enum.
     *
     * @param message Error message
     */
    AnsRxNarcCodeError(String message) {
        this.message = message;
    }

    /**
     * Add error message parameters.
     *
     * @param params Substitution parameters
     * @return Error message
     */
    public AnsRxNarcCodeError withParams(Object... params) {
        this.params = params;
        return this;
    }

    /**
     * Get error message.
     * <p>
     *
     * @return Error message
     */
    public String getMessage() {
        return String.format(this.message, params);
    }
}

