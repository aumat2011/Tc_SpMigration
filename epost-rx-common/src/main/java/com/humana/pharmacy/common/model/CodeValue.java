package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * CodeValue is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class CodeValue {

    private String applicationName;

    private String codeValueDesc;

    private Integer codeValueId;

    private String codeValueKeyword;

    private java.sql.Timestamp createTs;

    private String fkCodeCatId;

    private String lastUpdtBy;

    private java.sql.Timestamp lastUpdtTs;

    private String restartRequired;

    private String statusFlag;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getCodeValueDesc() {
        return codeValueDesc;
    }

    public void setCodeValueDesc(String codeValueDesc) {
        this.codeValueDesc = codeValueDesc;
    }

    public Integer getCodeValueId() {
        return codeValueId;
    }

    public void setCodeValueId(Integer codeValueId) {
        this.codeValueId = codeValueId;
    }

    public String getCodeValueKeyword() {
        return codeValueKeyword;
    }

    public void setCodeValueKeyword(String codeValueKeyword) {
        this.codeValueKeyword = codeValueKeyword;
    }

    public java.sql.Timestamp getCreateTs() {
        return createTs;
    }

    public void setCreateTs(java.sql.Timestamp createTs) {
        this.createTs = createTs;
    }

    public String getFkCodeCatId() {
        return fkCodeCatId;
    }

    public void setFkCodeCatId(String fkCodeCatId) {
        this.fkCodeCatId = fkCodeCatId;
    }

    public String getLastUpdtBy() {
        return lastUpdtBy;
    }

    public void setLastUpdtBy(String lastUpdtBy) {
        this.lastUpdtBy = lastUpdtBy;
    }

    public java.sql.Timestamp getLastUpdtTs() {
        return lastUpdtTs;
    }

    public void setLastUpdtTs(java.sql.Timestamp lastUpdtTs) {
        this.lastUpdtTs = lastUpdtTs;
    }

    public String getRestartRequired() {
        return restartRequired;
    }

    public void setRestartRequired(String restartRequired) {
        this.restartRequired = restartRequired;
    }

    public String getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(String statusFlag) {
        this.statusFlag = statusFlag;
    }

}

