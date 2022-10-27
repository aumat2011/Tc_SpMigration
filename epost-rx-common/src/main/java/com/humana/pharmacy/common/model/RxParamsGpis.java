package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * RxParamsGpis is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class RxParamsGpis {

    private String acceptFax;

    private String gpiClassDesc;

    private String gpiClassId;

    private Integer maxDaysSupply;

    private Integer minPctUtilization;

    private Integer numDaysExpire;

    private Integer numDaysFromOrigDate;

    private Integer numRefillsAllowed;

    private String sendFax;

    private Integer stateNum;

    public String getAcceptFax() {
        return acceptFax;
    }

    public void setAcceptFax(String acceptFax) {
        this.acceptFax = acceptFax;
    }

    public String getGpiClassDesc() {
        return gpiClassDesc;
    }

    public void setGpiClassDesc(String gpiClassDesc) {
        this.gpiClassDesc = gpiClassDesc;
    }

    public String getGpiClassId() {
        return gpiClassId;
    }

    public void setGpiClassId(String gpiClassId) {
        this.gpiClassId = gpiClassId;
    }

    public Integer getMaxDaysSupply() {
        return maxDaysSupply;
    }

    public void setMaxDaysSupply(Integer maxDaysSupply) {
        this.maxDaysSupply = maxDaysSupply;
    }

    public Integer getMinPctUtilization() {
        return minPctUtilization;
    }

    public void setMinPctUtilization(Integer minPctUtilization) {
        this.minPctUtilization = minPctUtilization;
    }

    public Integer getNumDaysExpire() {
        return numDaysExpire;
    }

    public void setNumDaysExpire(Integer numDaysExpire) {
        this.numDaysExpire = numDaysExpire;
    }

    public Integer getNumDaysFromOrigDate() {
        return numDaysFromOrigDate;
    }

    public void setNumDaysFromOrigDate(Integer numDaysFromOrigDate) {
        this.numDaysFromOrigDate = numDaysFromOrigDate;
    }

    public Integer getNumRefillsAllowed() {
        return numRefillsAllowed;
    }

    public void setNumRefillsAllowed(Integer numRefillsAllowed) {
        this.numRefillsAllowed = numRefillsAllowed;
    }

    public String getSendFax() {
        return sendFax;
    }

    public void setSendFax(String sendFax) {
        this.sendFax = sendFax;
    }

    public Integer getStateNum() {
        return stateNum;
    }

    public void setStateNum(Integer stateNum) {
        this.stateNum = stateNum;
    }

}

