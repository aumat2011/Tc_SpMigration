package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * AutoRouteRules is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class AutoRouteRules {

    private java.math.BigInteger autoRouteRuleSeq;

    private String autoRouteTypeName;

    private String locationNum;

    private Integer priority;

    private String targetDisplayValue;

    private String targetValue;

    public java.math.BigInteger getAutoRouteRuleSeq() {
        return autoRouteRuleSeq;
    }

    public void setAutoRouteRuleSeq(java.math.BigInteger autoRouteRuleSeq) {
        this.autoRouteRuleSeq = autoRouteRuleSeq;
    }

    public String getAutoRouteTypeName() {
        return autoRouteTypeName;
    }

    public void setAutoRouteTypeName(String autoRouteTypeName) {
        this.autoRouteTypeName = autoRouteTypeName;
    }

    public String getLocationNum() {
        return locationNum;
    }

    public void setLocationNum(String locationNum) {
        this.locationNum = locationNum;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTargetDisplayValue() {
        return targetDisplayValue;
    }

    public void setTargetDisplayValue(String targetDisplayValue) {
        this.targetDisplayValue = targetDisplayValue;
    }

    public String getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue;
    }

}

