package com.humana.pharmacy.common.cache.model;

import lombok.Data;

/**
 * The workflow queue model stored in Infinispan cache.
 */
@Data
public class CWorkflowQueue {
    /**
     * Workflow queue number.
     */
    Integer workflowQueueNum;

    /**
     * Trading partner number.
     */
    Long tradingPartnerNum;

    /**
     * Workflow queue name.
     */
    String workflowQueueName;

    /**
     * Default constructor.
     */
    public CWorkflowQueue() {
    }

    /**
     * Constructor with all fields.
     *
     * @param queueNum  Workflow queue number
     * @param queueName Workflow queue name
     * @param tpNum     Trading partner number
     */
    public CWorkflowQueue(Integer queueNum, Long tpNum, String queueName) {
        this.workflowQueueNum = queueNum;
        this.tradingPartnerNum = tpNum;
        this.workflowQueueName = queueName;
    }
}
