package com.humana.pharmacy.dto;

import com.humana.pharmacy.constants.RxSanityError;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * The result dto for checking Rx sanity.
 */
@Getter
@Setter
public class RxSanityResult {
    /**
     * The new line string.
     */
    private static final String NEW_LINE = "\n";

    /**
     * List of sanity errors.
     * <p>
     */
    private List<RxSanityError> errors = new ArrayList<>();

    /**
     * Get error messages representation.
     *
     * @return Error messages representation
     */
    public String toErrorMessage() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (RxSanityError error : errors) {
            if (sb.length() > 0) {
                sb.append(NEW_LINE);
            }
            sb.append(" (").append(i).append(") ").append(error.getMessage());
            i++;
        }
        return sb.toString();
    }
}

