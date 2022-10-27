package com.humana.pharmacy.dto;

import com.humana.pharmacy.constants.AnsRxProductError;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * The result dto for product level checks.
 */
@Setter
@Getter
public class AnsRxProductResult {

    /**
     * List of sanity errors.
     */
    private final List<String> errors = new ArrayList<>();

    /**
     * Override queue.
     */
    private String overrideQueue;

    /**
     * Override queue name.
     */
    private String overrideQname;

    /**
     * Add check error.
     *
     * @param error The product level checks error
     */
    public void addError(AnsRxProductError error) {
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
            sb.append(String.format("(%s) ", count++)).append(error);
            sb.append("\n");
        }
        return sb.toString();
    }
}
