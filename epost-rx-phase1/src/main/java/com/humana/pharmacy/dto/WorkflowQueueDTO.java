package com.humana.pharmacy.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * Represents workflow queue.
 */
@Getter
@Setter
public class WorkflowQueueDTO {
    /**
     * Order number. Derived from rule_queue_exception#order_num column.
     */
    private BigInteger orderNum;

    /**
     * Workflow queue number. Derived from rule_queue_exception#workflow_queue_num column.
     */
    private Integer workflowQueueNum;

    /**
     * Order based rule. Derived from rules#order_based_rule column.
     */
    private String orderBasedRule;

    /**
     * Workflow queue name. Derived from workflow_queue_names#workflow_queue_name column.
     */
    private String workflowQueueName;

    /**
     * Product generic ref num. Derived from products#prod_generic_ref_num column.
     */
    private String prodGenericRefNum;
}
