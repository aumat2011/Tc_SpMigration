package com.humana.pharmacy.dto;

import com.humana.pharmacy.constants.RsRxLinkWorkflowMinUtilizationCheckError;
import lombok.Getter;
import lombok.Setter;

/**
 * The dto contains Rs Rx link workflow min utilization check result.
 */
@Setter
@Getter
public class RsRxLinkWorkflowMinUtilizationCheckResult {
    /**
     * The error.
     */
    private RsRxLinkWorkflowMinUtilizationCheckError error;

    /**
     * Override queue number.
     */
    private String overrideQueue;

    /**
     * Override queue name.
     */
    private String overrideQname;


    /**
     * Get error messages representation.
     *
     * @return Error messages representation
     */
    public String toErrorMessage() {
        return error == null ? "" : error.getMessage();
    }
}

