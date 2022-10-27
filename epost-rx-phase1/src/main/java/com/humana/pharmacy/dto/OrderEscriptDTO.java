package com.humana.pharmacy.dto;

import com.humana.pharmacy.common.model.EScriptMsgAttributes;
import com.humana.pharmacy.common.model.OrderLines;
import com.humana.pharmacy.common.model.PatientGeneral;
import com.humana.pharmacy.common.model.PatientPlans;
import com.humana.pharmacy.common.model.PayorPlans;
import com.humana.pharmacy.common.model.Payors;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * The order escript dto.
 */
@Setter
@Getter
public class OrderEscriptDTO {
    /**
     * The order line.
     */
    private OrderLines orderLine;

    /**
     * The escript.
     */
    private EScriptMsgAttributes escript;

    /**
     * The payor plan.
     */
    private PayorPlans payorPlan;

    /**
     * The payor.
     */
    private Payors payor;

    /**
     * The patient.
     */
    private PatientGeneral patient;

    /**
     * The patient plan.
     */
    private PatientPlans patientPlan;

    /**
     * The bill address.
     */
    private Long billAddress;

    /**
     * The ship address.
     */
    private Long shipAddress;

    /**
     * The paid claim id.
     */
    private Long paidClaimId;

    /**
     * The paid ECS number.
     */
    private BigInteger paidEcsNum;

    /**
     * The total dispensed quantity.
     */
    private Long totalDispensedQuantity;
}

