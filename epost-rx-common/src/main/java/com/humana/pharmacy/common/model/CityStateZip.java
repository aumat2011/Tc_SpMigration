package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * CityStateZip is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class CityStateZip {

    private String active;

    private String cszCityName;

    private String cszZipCode;

    private Long cszZipNum;

    private Integer ctryNum;

    private Integer regionNum;

    private Integer stateNum;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCszCityName() {
        return cszCityName;
    }

    public void setCszCityName(String cszCityName) {
        this.cszCityName = cszCityName;
    }

    public String getCszZipCode() {
        return cszZipCode;
    }

    public void setCszZipCode(String cszZipCode) {
        this.cszZipCode = cszZipCode;
    }

    public Long getCszZipNum() {
        return cszZipNum;
    }

    public void setCszZipNum(Long cszZipNum) {
        this.cszZipNum = cszZipNum;
    }

    public Integer getCtryNum() {
        return ctryNum;
    }

    public void setCtryNum(Integer ctryNum) {
        this.ctryNum = ctryNum;
    }

    public Integer getRegionNum() {
        return regionNum;
    }

    public void setRegionNum(Integer regionNum) {
        this.regionNum = regionNum;
    }

    public Integer getStateNum() {
        return stateNum;
    }

    public void setStateNum(Integer stateNum) {
        this.stateNum = stateNum;
    }

}

