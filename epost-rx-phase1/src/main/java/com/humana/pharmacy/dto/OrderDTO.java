package com.humana.pharmacy.dto;

import com.humana.pharmacy.common.model.OrderBillship;
import com.humana.pharmacy.common.model.OrderHeader;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * The order DTO.
 */
@Setter
@Getter
public class OrderDTO {
    /**
     * The order header.
     */
    private OrderHeader header;

    /**
     * The order bill ship.
     */
    private OrderBillship billship;

    /**
     * The order bill address.
     */
    private Long billAddress;

    /**
     * The order ship address.
     */
    private Long shipAddress;

    /**
     * The order auth reply record num.
     */
    private BigInteger authRecordNum;

    /**
     * The order ship patient number.
     */
    private BigInteger shipPatientNum;

    /**
     * The trading partner type.
     */
    private String tradingPartnerType;
}

