package com.humana.pharmacy.constants;

/**
 * The enum defines the error found for ans rx sanity.
 */
public enum AnsRxSanityError {
    /**
     * Represents the error of ans rx sanity check.
     */
    RX_SIG_OR_GPI_DOES_NOT_MATCH("Rx SIG or GPI does not match the prechecked Rx."),

    /**
     * Represents the error of ans rx sanity check.
     */
    CONTROLLED_FILL_COUNT_EXCEEDS("Controlled substance fill count exceeds # of refills written."),

    /**
     * Represents the error of ans rx sanity check.
     */
    SHOULD_BE_LESS_THAN_6("Controlled substance has been refilled [@CONTROLLED] times. Should be less than 6."),

    /**
     * Represents the error of ans rx sanity check.
     */
    RX_IS_NOT_ACTIVE("Rx is not active, current status is [@TMP_ERROR]."),

    /**
     * Represents the error of ans rx sanity check.
     */
    PRESCRIBER_IS_ERX_ELIGIBLE("Prescriber is ERX eligible;no prescriber address attached to script.  Please edit the prescription and select a prescriber address."),

    /**
     * Represents the error of ans rx sanity check.
     */
    RX_NOT_PAID("RX NOT PAID!! CURRENT STATUS = (@PAID; [A = Accepted Reversal, R = Rejected, D = Duplicate Paid (need to verify the claim is Paid)"),

    /**
     * Represents the error of ans rx sanity check.
     */
    WRITTEN_PAST_DATE("FILLING > 1-YEAR PAST WRITTEN DATE"),

    /**
     * Represents the error of ans rx sanity check.
     */
    WRITTEN_FUTURE_DATE("DATE WRITTEN IS A FUTURE DATE"),

    /**
     * Represents the error of ans rx sanity check.
     */
    REQUIRES_SSN_ON_FILE("State Req(@SHIP_STATE): requires SSN on-file for Controls."),

    /**
     * Represents the error of ans rx sanity check.
     */
    FL_EPILEPTIC_SUBSTITION("State Req(FL): Epileptic Substition, requires patient/doctor contact."),

    /**
     * Represents the error of ans rx sanity check.
     */
    CONTROLLED_REFILL_ATTEMPT("Controlled Substance Refill Attempt"),

    /**
     * Represents the error of ans rx sanity check.
     */
    DISP_WRIT_GPI_NOT_EQUIVILENT("DISPENSE GPI: [@DISP_GPI]; WRITTEN GPI: [@WRIT_GPI] NOT EQUIVILENT."),

    /**
     * Represents the error of ans rx sanity check.
     */
    ALERT_GPI_WO_SPECIAL_TEE_CODES("ALERT QUALITY - subbing issue - GPI w/o Special Tee Codes"),

    /**
     * Represents the error of ans rx sanity check.
     */
    ALERT_GPI_W_SPECIAL_TEE_CODES("ALERT QUALITY - subbing issue - GPI w/ Special Tee Codes"),

    /**
     * Represents the error of ans rx sanity check.
     */
    ALERT_DAW_1_2_DRUG("ALERT QUALITY - subbing issue - DAW 1,2 Drug"),

    /**
     * Represents the error of ans rx sanity check.
     */
    ALERT_GPI_WITH_DAW_8_DRUG("ALERT QUALITY - subbing issue - GPI with DAW 8 Drug"),

    /**
     * Represents the error of ans rx sanity check.
     */
    UNRESOLVED_DUR_FOUND("Unresolved DUR found during RX Sanity Check 1.28."),

    /**
     * Represents the error of ans rx sanity check.
     */
    HI_MUST_HAVE_PRESCRIBER_ADDRESS("State Req (HI): Federal and State Specific Control shipping to HI must have HI prescriber address."),

    /**
     * Represents the error of ans rx sanity check.
     */
    HI_REQUIRES_DOCUMENTS("State Req (HI): requires Driver''s License, State ID, or Passport number on-file for Federal and State Specific Controls.");

    /**
     * Error message. Set in constructor and will never change.
     */
    private final String message;

    /**
     * Constructor of this enum.
     * <p>
     * Implemenation Notes:
     * <p>
     * this.message = message;
     *
     * @param message Error message
     */
    AnsRxSanityError(String message) {
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

