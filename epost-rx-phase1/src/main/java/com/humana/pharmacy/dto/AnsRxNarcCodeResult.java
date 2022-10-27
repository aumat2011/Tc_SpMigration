package com.humana.pharmacy.dto;

import com.humana.pharmacy.constants.AnsRxNarcCodeError;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * The dto contains narcotic code check result.
 */
@Setter
@Getter
public class AnsRxNarcCodeResult {
    /**
     * The new line string.
     */
    private static final String NEW_LINE = "\n";

    /**
     * The error count.
     */
    private static final String ERROR_COUNT = "(%s)";

    /**
     * List of errors.
     */
    private List<String> errors = new ArrayList<>();


    /**
     * Add check error
     *
     * @param error The narcotic code error
     */
    public void addError(AnsRxNarcCodeError error) {
        this.errors.add(error.getMessage());
    }

    /**
     * Get error messages representation.
     *
     * @return Error messages representation
     */
    public String toErrorMessage() {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (String error : errors) {
            if (sb.length() > 0) {
                sb.append(NEW_LINE);
            }
            sb.append(String.format(ERROR_COUNT, count++)).append(error);
        }
        return sb.toString();
    }
}

