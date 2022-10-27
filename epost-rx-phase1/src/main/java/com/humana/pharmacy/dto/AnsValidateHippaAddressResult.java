package com.humana.pharmacy.dto;

import com.humana.pharmacy.constants.AnsValidateHippaAddressError;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * The dto contains narcotic code check result.
 */
@Setter
@Getter
public class AnsValidateHippaAddressResult {
    /**
     * The new line string.
     */
    private static final String NEW_LINE = "\n";

    /**
     * List of errors.
     */
    private List<AnsValidateHippaAddressError> errors = new ArrayList<>();

    /**
     * Add check error
     *
     * @param error The ans validate hippa address error
     */
    public void addError(AnsValidateHippaAddressError error) {
        errors.add(error);
    }

    /**
     * Get error messages representation.
     *
     * @return Error messages representation
     */
    public String toErrorMessage() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < errors.size(); i++) {
            if (i > 0) {
                sb.append(NEW_LINE);
            }
            sb.append(" (" + (i + 1) + ") ").append(errors.get(i).getMessage());
        }
        return sb.toString();
    }
}

