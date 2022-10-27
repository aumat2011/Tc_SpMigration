package com.humana.pharmacy.common.cache.model;

import lombok.Data;

/**
 * The Shipping Method model stored in Infinispan cache.
 */
@Data
public class CShippingMethod {
    /**
     * The ship method id.
     */
    Integer shipMethodId;

    /**
     * The ship method desc.
     */
    String shipMethodDesc;

    /**
     * Default constructor.
     */
    public CShippingMethod() {
        //Empty
    }

    /**
     * Constructor with all fields.
     *
     * @param shipMethodDesc The ship method desc
     * @param shipMethodId   The ship method id
     */
    public CShippingMethod(Integer shipMethodId, String shipMethodDesc) {
        this.shipMethodId = shipMethodId;
        this.shipMethodDesc = shipMethodDesc;
    }
}

