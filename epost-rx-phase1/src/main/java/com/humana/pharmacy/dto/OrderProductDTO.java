package com.humana.pharmacy.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * Represent an order and related product info. Note a given order may contain multiple products.
 */
@Getter
@Setter
public class OrderProductDTO {
    /**
     * Order number. Derived from order_header#order_num column.
     */
    private BigInteger orderNum;

    /**
     * Order status. Derived from order_header#order_status_num column.
     */
    private Byte orderStatusNum;

    /**
     * Order invoice number. Derived from order_header#order_invoice_number column.
     */
    private String orderInvoiceNumber;

    /**
     * Product generic ref num. Derived from products#prod_generic_ref_num column.
     */
    private String prodGenericRefNum;

    /**
     * Product name description. Derived from products#prod_name_desc column.
     */
    private String prodNameDesc;

    /**
     * Order based queue num. Derived from subquery.
     */
    private Integer orderQueueNum;

    /**
     * Non-order based queue num. Derived from subquery.
     */
    private Integer nonOrderQueueNum;
}
