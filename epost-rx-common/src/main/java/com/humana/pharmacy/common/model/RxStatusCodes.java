package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * RxStatusCodes is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class RxStatusCodes {

    private String rxStatusCodeDesc;

    private Byte rxStatusCodeNum;

    public String getRxStatusCodeDesc() {
        return rxStatusCodeDesc;
    }

    public void setRxStatusCodeDesc(String rxStatusCodeDesc) {
        this.rxStatusCodeDesc = rxStatusCodeDesc;
    }

    public Byte getRxStatusCodeNum() {
        return rxStatusCodeNum;
    }

    public void setRxStatusCodeNum(Byte rxStatusCodeNum) {
        this.rxStatusCodeNum = rxStatusCodeNum;
    }

}

