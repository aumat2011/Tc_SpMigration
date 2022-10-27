package com.humana.pharmacy.common.cache.model;

import lombok.Data;

/**
 * The rule model stored in Infinispan cache.
 */
@Data
public class CRule {
    /**
     * Rule Id.
     */
    Long ruleId;

    /**
     * Order based rule.
     */
    String orderBasedRule;

    /**
     * The Allow override.
     */
    String allowOverride;

    /**
     * The Business hour type id.
     */
    Integer businessHourTypeId;

    /**
     * The Operator id.
     */
    Byte operatorId;

    /**
     * The Resource connector id.
     */
    Integer resourceConnectorId;

    /**
     * The Routing tp num.
     */
    Long routingTpNum;

    /**
     * The Rule action id.
     */
    Byte ruleActionId;

    /**
     * The Rule criteria id.
     */
    Integer ruleCriteriaId;

    /**
     * The Rule desc.
     */
    String ruleDesc;

    /**
     * The Rule enabled.
     */
    String ruleEnabled;

    /**
     * The Rule group id.
     */
    Integer ruleGroupId;

    /**
     * The Rule list table id.
     */
    Integer ruleListTableId;

    /**
     * The Rule name.
     */
    String ruleName;

    /**
     * The Rule params.
     */
    String ruleParams;

    /**
     * The Rule response id.
     */
    Byte ruleResponseId;

    /**
     * The Rule retry.
     */
    Byte ruleRetry;

    /**
     * The Rule value.
     */
    String ruleValue;

    /**
     * The Serial num.
     */
    Integer serialNum;

    /**
     * The Server num.
     */
    Integer serverNum;

    /**
     * The Tp connectivity seq.
     */
    Long tpConnectivitySeq;

    /**
     * The Tp contact seq.
     */
    Long tpContactSeq;

    /**
     * The Trading partner num.
     */
    Long tradingPartnerNum;

    /**
     * The Workflow num.
     */
    Integer workflowNum;

    /**
     * The Workflow queue num.
     */
    Integer workflowQueueNum;

    /**
     * Instantiates a new C rule.
     */
    public CRule() {
    }

    /**
     * Instantiates a new C rule.
     *
     * @param ruleId         the rule id
     * @param orderBasedRule the order based rule
     */
    public CRule(Long ruleId, String orderBasedRule) {
        this.ruleId = ruleId;
        this.orderBasedRule = orderBasedRule;
    }

    /**
     * Instantiates a new C rule.
     *
     * @param ruleId              the rule id
     * @param orderBasedRule      the order based rule
     * @param allowOverride       the allow override
     * @param businessHourTypeId  the business hour type id
     * @param operatorId          the operator id
     * @param resourceConnectorId the resource connector id
     * @param routingTpNum        the routing tp num
     * @param ruleActionId        the rule action id
     * @param ruleCriteriaId      the rule criteria id
     * @param ruleDesc            the rule desc
     * @param ruleEnabled         the rule enabled
     * @param ruleGroupId         the rule group id
     * @param ruleListTableId     the rule list table id
     * @param ruleName            the rule name
     * @param ruleParams          the rule params
     * @param ruleResponseId      the rule response id
     * @param ruleRetry           the rule retry
     * @param ruleValue           the rule value
     * @param serialNum           the serial num
     * @param serverNum           the server num
     * @param tpConnectivitySeq   the tp connectivity seq
     * @param tpContactSeq        the tp contact seq
     * @param tradingPartnerNum   the trading partner num
     * @param workflowNum         the workflow num
     * @param workflowQueueNum    the workflow queue num
     */
    public CRule(Long ruleId, String orderBasedRule, String allowOverride, Integer businessHourTypeId,
                 Byte operatorId, Integer resourceConnectorId, Long routingTpNum, Byte ruleActionId,
                 Integer ruleCriteriaId, String ruleDesc, String ruleEnabled, Integer ruleGroupId,
                 Integer ruleListTableId, String ruleName, String ruleParams, Byte ruleResponseId,
                 Byte ruleRetry, String ruleValue, Integer serialNum, Integer serverNum,
                 Long tpConnectivitySeq, Long tpContactSeq, Long tradingPartnerNum,
                 Integer workflowNum, Integer workflowQueueNum) {
        this.ruleId = ruleId;
        this.orderBasedRule = orderBasedRule;
        this.allowOverride = allowOverride;
        this.businessHourTypeId = businessHourTypeId;
        this.operatorId = operatorId;
        this.resourceConnectorId = resourceConnectorId;
        this.routingTpNum = routingTpNum;
        this.ruleActionId = ruleActionId;
        this.ruleCriteriaId = ruleCriteriaId;
        this.ruleDesc = ruleDesc;
        this.ruleEnabled = ruleEnabled;
        this.ruleGroupId = ruleGroupId;
        this.ruleListTableId = ruleListTableId;
        this.ruleName = ruleName;
        this.ruleParams = ruleParams;
        this.ruleResponseId = ruleResponseId;
        this.ruleRetry = ruleRetry;
        this.ruleValue = ruleValue;
        this.serialNum = serialNum;
        this.serverNum = serverNum;
        this.tpConnectivitySeq = tpConnectivitySeq;
        this.tpContactSeq = tpContactSeq;
        this.tradingPartnerNum = tradingPartnerNum;
        this.workflowNum = workflowNum;
        this.workflowQueueNum = workflowQueueNum;
    }
}
