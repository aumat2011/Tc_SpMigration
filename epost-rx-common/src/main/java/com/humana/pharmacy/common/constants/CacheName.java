package com.humana.pharmacy.common.constants;

public enum CacheName {
    /**
     * The cache for rx_defaults table. There will be only one data item in this cache.
     * <p>
     * Cached data key is "RX_DEFAULTS" string, value is CRxDefaults model.
     */
    RX_DEFAULTS,

    /**
     * The cache for code value status.
     * <p>
     * Cache data key is code value keyword string, value is status string.
     */
    CODE_VALUE_STATUS,

    /**
     * The cache for workflow queues.
     * <p>
     * Cache data key is workflow queue number, value is CWorkflowQueue model.
     */
    WORKFLOW_QUEUES,

    /**
     * The cache for trading partner types.
     * <p>
     * Cache data key is trading partner number, value is trading partner type string.
     */
    TRADING_PARTNER_TYPES,

    /**
     * The cache for rx status code.
     * <p>
     * Cache data key is rx status code number, value is rx status code description string.
     */
    RX_STATUS_CODE,

    /**
     * The cache for order_defaults table. There will be only one data item in this cache.
     * <p>
     * Cached data key is "ORDER_DEFAULTS" string, value is COrderDefaults model.
     */
    ORDER_DEFAULTS,


    /**
     * The cache for shipping_methods table.
     * <p>
     * Cached data key is "SHIPPING_METHODS" string, value is CShippingMethod model.
     */
    SHIPPING_METHODS,
    RULES,
    CODE_VALUES
}
