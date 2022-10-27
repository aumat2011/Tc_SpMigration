package com.humana.pharmacy.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * This is an "aggregated" version of OrderProductDTO.
 *
 * <p>Within the scope of a given order, the duplicate products (having same prodGenericRerNum) have
 * their prodNameDesc concatenated and stored as OrderGPIDTO.prodNameDesc. OrderGPIDTO.gpiCount
 * represents the duplicate count.
 */
@Setter
@Getter
public class OrderGPIDTO extends OrderProductDTO {
    /**
     * GPI count. Represents the duplicate count.
     */
    private int gpiCount;
}
