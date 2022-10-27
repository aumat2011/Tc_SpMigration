package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * ShippingMethods is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class ShippingMethods {

    private String active;

    private String priority;

    private String shipMethodDesc;

    private Integer shipMethodId;

    private String shippingCod;

    private String shippingCode;

    private Byte shippingDays;

    private Long shippingMethodPrice;

    private String signatureRequired;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getShipMethodDesc() {
        return shipMethodDesc;
    }

    public void setShipMethodDesc(String shipMethodDesc) {
        this.shipMethodDesc = shipMethodDesc;
    }

    public Integer getShipMethodId() {
        return shipMethodId;
    }

    public void setShipMethodId(Integer shipMethodId) {
        this.shipMethodId = shipMethodId;
    }

    public String getShippingCod() {
        return shippingCod;
    }

    public void setShippingCod(String shippingCod) {
        this.shippingCod = shippingCod;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    public Byte getShippingDays() {
        return shippingDays;
    }

    public void setShippingDays(Byte shippingDays) {
        this.shippingDays = shippingDays;
    }

    public Long getShippingMethodPrice() {
        return shippingMethodPrice;
    }

    public void setShippingMethodPrice(Long shippingMethodPrice) {
        this.shippingMethodPrice = shippingMethodPrice;
    }

    public String getSignatureRequired() {
        return signatureRequired;
    }

    public void setSignatureRequired(String signatureRequired) {
        this.signatureRequired = signatureRequired;
    }

}

