package com.humana.pharmacy.constants;

/**
 * The enum defines the error found for ans validate hippa address check.
 */
public enum AnsValidateHippaAddressError {
    /**
     * Represents the error of ans validate hippa address check.
     */
    CURRIER_IS_NOT_USPS("CANNOT BE SEND TO GENERAL DELIVERY ADDRESS BECAUSE CURRIER IS NOT USPS."),

    /**
     * Represents the error of ans validate hippa address check.
     */
    ADDRESS_IS_NOT_VERIFIED_AND_NOT_OVERRIDDEN("SHIP ADDRESS IS NOT USPS VERIFIED AND NOT OVERRIDDEN."),

    /**
     * Represents the error of ans validate hippa address check.
     */
    ADDRESS_IS_NOT_VERIFIED_AND_ADDRESS_SERVICE_IS_DOWN("SHIP ADDRESS IS NOT USPS VERIFIED. ENTERPRISE ADDRESS SERVICE IS DOWN."),

    /**
     * Represents the error of ans validate hippa address check.
     */
    ADDRESS_LINE_IS_OVERED("ADDRESS LINE IS OVER 35 CHARACTERS."),

    /**
     * Represents the error of ans validate hippa address check.
     */
    MANUAL_VERIFICATION_REQUIRED("MANUAL VERIFICATION REQUIRED FOR FREE-TEXT ADDRESS ENTRY."),

    /**
     * Represents the error of ans validate hippa address check.
     */
    ADDRESS_STATUS_IS_INACTIVE("SHIP TO ADDRESS STATUS IS INACTIVE."),

    /**
     * Represents the error of ans validate hippa address check.
     */
    NO_FAMILY_RECORD("Family Mode and Patient Has No Family Record "),

    /**
     * Represents the error of ans validate hippa address check.
     */
    PATIENT_IS_NOT_VALID("A patient on this order is *NOT* valid. (please verify all members)."),

    /**
     * Represents the error of ans validate hippa address check.
     */
    ADDRESS_NOT_BELONG_TO_MEMBER("ON-FILE ADDRESS MODIFIED VIA FREE-TEXT. ADDRESS DOES NOT BELONG TO SHIP MEMBER(Reselect)"),

    /**
     * Represents the error of ans validate hippa address check.
     */
    NO_CARDHOLDER("Addr Error.  No Cardholder Found for Minor Shipment "),

    /**
     * Represents the error of ans validate hippa address check.
     */
    NO_MINOR_ADDRESS("Addr Error.  Minor Address Not Found on Cardholder Profile "),

    /**
     * Represents the error of ans validate hippa address check.
     */
    ADDRESS_NOT_COMMON("Addr Error.  Ship-To address not common to all shipment members ");

    /**
     * Error message. Set in constructor and will never change.
     */
    private String message;

    /**
     * Constructor of this enum.
     *
     * @param message Error message
     */
    private AnsValidateHippaAddressError(String message) {
        this.message = message;
    }

    /**
     * Get error message.
     *
     * @return Error message
     */
    public String getMessage() {
        return message;
    }
}

