package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * StateCsProducts is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class StateCsProducts {

    private String poboxActive;

    private Short prodDea;

    private String prodFilter;

    private Long prodId;

    private Long stateCsProductSeq;

    private Integer stateNum;

    public String getPoboxActive() {
        return poboxActive;
    }

    public void setPoboxActive(String poboxActive) {
        this.poboxActive = poboxActive;
    }

    public Short getProdDea() {
        return prodDea;
    }

    public void setProdDea(Short prodDea) {
        this.prodDea = prodDea;
    }

    public String getProdFilter() {
        return prodFilter;
    }

    public void setProdFilter(String prodFilter) {
        this.prodFilter = prodFilter;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public Long getStateCsProductSeq() {
        return stateCsProductSeq;
    }

    public void setStateCsProductSeq(Long stateCsProductSeq) {
        this.stateCsProductSeq = stateCsProductSeq;
    }

    public Integer getStateNum() {
        return stateNum;
    }

    public void setStateNum(Integer stateNum) {
        this.stateNum = stateNum;
    }

}

