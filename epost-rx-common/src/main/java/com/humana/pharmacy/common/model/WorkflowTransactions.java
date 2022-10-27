package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * WorkflowTransactions is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class WorkflowTransactions {

    private java.sql.Timestamp currentTxnDatetime;

    private java.math.BigInteger ediMessageId;

    private java.math.BigInteger eScriptMsgAttributeSeq;

    private String exceptionSeq;

    private String hostServer;

    private java.sql.Timestamp initialTxnSubmitDatetime;

    private Byte intransition;

    private String jmsMsgId;

    private String mode;

    private java.math.BigInteger orderNum;

    private Byte orderRunning;

    private String queuename;

    private String queuenum;

    private java.math.BigInteger ruleEngineId;

    private Byte ruleEngineStatusId;

    private Long ruleId;

    private String ruleOrderNum;

    private String rxnumber;

    private Byte stepBusy;

    private String tradingPartnerNum;

    private Integer workflowNum;

    private String workflowStep;

    private Byte workflowStepNum;

    private java.math.BigInteger workflowTxnId;

    public java.sql.Timestamp getCurrentTxnDatetime() {
        return currentTxnDatetime;
    }

    public void setCurrentTxnDatetime(java.sql.Timestamp currentTxnDatetime) {
        this.currentTxnDatetime = currentTxnDatetime;
    }

    public java.math.BigInteger getEdiMessageId() {
        return ediMessageId;
    }

    public void setEdiMessageId(java.math.BigInteger ediMessageId) {
        this.ediMessageId = ediMessageId;
    }

    public java.math.BigInteger geteScriptMsgAttributeSeq() {
        return eScriptMsgAttributeSeq;
    }

    public void seteScriptMsgAttributeSeq(java.math.BigInteger eScriptMsgAttributeSeq) {
        this.eScriptMsgAttributeSeq = eScriptMsgAttributeSeq;
    }

    public String getExceptionSeq() {
        return exceptionSeq;
    }

    public void setExceptionSeq(String exceptionSeq) {
        this.exceptionSeq = exceptionSeq;
    }

    public String getHostServer() {
        return hostServer;
    }

    public void setHostServer(String hostServer) {
        this.hostServer = hostServer;
    }

    public java.sql.Timestamp getInitialTxnSubmitDatetime() {
        return initialTxnSubmitDatetime;
    }

    public void setInitialTxnSubmitDatetime(java.sql.Timestamp initialTxnSubmitDatetime) {
        this.initialTxnSubmitDatetime = initialTxnSubmitDatetime;
    }

    public Byte getIntransition() {
        return intransition;
    }

    public void setIntransition(Byte intransition) {
        this.intransition = intransition;
    }

    public String getJmsMsgId() {
        return jmsMsgId;
    }

    public void setJmsMsgId(String jmsMsgId) {
        this.jmsMsgId = jmsMsgId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public java.math.BigInteger getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(java.math.BigInteger orderNum) {
        this.orderNum = orderNum;
    }

    public Byte getOrderRunning() {
        return orderRunning;
    }

    public void setOrderRunning(Byte orderRunning) {
        this.orderRunning = orderRunning;
    }

    public String getQueuename() {
        return queuename;
    }

    public void setQueuename(String queuename) {
        this.queuename = queuename;
    }

    public String getQueuenum() {
        return queuenum;
    }

    public void setQueuenum(String queuenum) {
        this.queuenum = queuenum;
    }

    public java.math.BigInteger getRuleEngineId() {
        return ruleEngineId;
    }

    public void setRuleEngineId(java.math.BigInteger ruleEngineId) {
        this.ruleEngineId = ruleEngineId;
    }

    public Byte getRuleEngineStatusId() {
        return ruleEngineStatusId;
    }

    public void setRuleEngineStatusId(Byte ruleEngineStatusId) {
        this.ruleEngineStatusId = ruleEngineStatusId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleOrderNum() {
        return ruleOrderNum;
    }

    public void setRuleOrderNum(String ruleOrderNum) {
        this.ruleOrderNum = ruleOrderNum;
    }

    public String getRxnumber() {
        return rxnumber;
    }

    public void setRxnumber(String rxnumber) {
        this.rxnumber = rxnumber;
    }

    public Byte getStepBusy() {
        return stepBusy;
    }

    public void setStepBusy(Byte stepBusy) {
        this.stepBusy = stepBusy;
    }

    public String getTradingPartnerNum() {
        return tradingPartnerNum;
    }

    public void setTradingPartnerNum(String tradingPartnerNum) {
        this.tradingPartnerNum = tradingPartnerNum;
    }

    public Integer getWorkflowNum() {
        return workflowNum;
    }

    public void setWorkflowNum(Integer workflowNum) {
        this.workflowNum = workflowNum;
    }

    public String getWorkflowStep() {
        return workflowStep;
    }

    public void setWorkflowStep(String workflowStep) {
        this.workflowStep = workflowStep;
    }

    public Byte getWorkflowStepNum() {
        return workflowStepNum;
    }

    public void setWorkflowStepNum(Byte workflowStepNum) {
        this.workflowStepNum = workflowStepNum;
    }

    public java.math.BigInteger getWorkflowTxnId() {
        return workflowTxnId;
    }

    public void setWorkflowTxnId(java.math.BigInteger workflowTxnId) {
        this.workflowTxnId = workflowTxnId;
    }

}

