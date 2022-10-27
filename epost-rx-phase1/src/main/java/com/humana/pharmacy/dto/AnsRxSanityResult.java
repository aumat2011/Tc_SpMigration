package com.humana.pharmacy.dto;

import com.humana.pharmacy.constants.AnsRxSanityError;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * The result dto for check ans rx sanity.
 */
@Setter
@Getter
public class AnsRxSanityResult {
    /**
     * The new line string.
     */
    private static final String NEW_LINE = "\n";

    /**
     * List of sanity errors.
     */
    private List<String> errors = new ArrayList<>();

    /**
     * Override queue number.
     */
    private Integer overrideQueue;

    /**
     * Override queue name.
     */
    private String overrideQname;

    public void addError(AnsRxSanityError ansRxSanityError, String... args) {
        String errorMessage = ansRxSanityError.getMessage();
        for (String arg : args) {
            errorMessage = errorMessage.replaceFirst("@[a-zA-Z_]+", arg);
        }
        errors.add(errorMessage);
    }


    /**
     * Get error messages representation.
     *
     * @return Error messages representation
     */
    public String toErrorMessage() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < errors.size(); i++) {
            if (sb.length() > 0) {
                sb.append(NEW_LINE);
            }
            sb.append(" (").append(i + 1).append(") ").append(errors.get(i));
        }
        return sb.toString();
    }
}

