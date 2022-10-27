package com.humana.pharmacy.service;

import com.humana.pharmacy.dto.RsRxLinkWorkflowMinUtilizationCheckResult;

/**
 * Service to check Rs Rx link workflow min utilization.
 */
public interface RsRxLinkWorkflowMinUtilizationCheckService {
    /**
     * Check Rs Rx link workflow min utilization.
     *
     * @param rxNumber The rx number. Required
     * @return The Rs Rx link workflow min utilization check result or null if no error found
     */
    RsRxLinkWorkflowMinUtilizationCheckResult checkRsRxLinkWorkflowMinUtilization(String rxNumber);
}

