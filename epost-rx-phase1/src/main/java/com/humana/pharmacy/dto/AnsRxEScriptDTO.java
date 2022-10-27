package com.humana.pharmacy.dto;

import com.humana.pharmacy.common.dto.BaseEScriptDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * The dto contains collected data that is used in sanity checking.
 */
@Setter
@Getter
public class AnsRxEScriptDTO extends BaseEScriptDTO {
    /**
     * The order line status num.
     */
    private Byte orderLineStatusNum;

    /**
     * The ship state.
     */
    private String shipState;

    /**
     * The prod id.
     */
    private Long prodId;

    /**
     * The prod dea.
     */
    private Byte prodDea;

    /**
     * The state prod dea.
     */
    private Short stateProdDea;

    /**
     * The rx number.
     */
    private String rxNumber;

    /**
     * The trading parther num.
     */
    private Long tradingPartnerNum;

    /**
     * The patient num.
     */
    private BigInteger patientNum;

    /**
     * The tp addr seq.
     */
    private Long tpAddrSeq;

    /**
     * The doctor num.
     */
    private Long doctorNum;

    /**
     * The days supply.
     */
    private Integer daysSupply;

    /**
     * The num refills.
     */
    private Long numRefills;

    /**
     * The rx status code num.
     */
    private Byte rxStatusCodeNum;

    /**
     * The edi transaction code.
     */
    private String ediTransactionCode;

    /**
     * The pp type code.
     */
    private String ppTypeCode;

    /**
     * The ecs response status.
     */
    private String ecsResponseStatusType;

    /**
     * The written date.
     */
    private Timestamp writtenDate;

    /**
     * The fill status num.
     */
    private Byte fillStatusNum;

    /**
     * The original num refills.
     */
    private Long originalNumRefills;

    /**
     * The payor plan pp num.
     */
    private Long payorPlanPpNum;

    /**
     * The payor bill type num.
     */
    private Integer payorBillTypeNum;

    /**
     * The patient id code.
     */
    private String patientIdCode;

    /**
     * The patient id.
     */
    private String patientId;

    /**
     * The additional patient id type code.
     */
    private String additionalPatientIdTypeCode;

    /**
     * The additional patient id.
     */
    private String additionalPatientId;

    /**
     * The prod generic ref num.
     */
    private String prodGenericRefNum;

    /**
     * The conversation link.
     */
    private String conversionLink;

    /**
     * The prod number.
     */
    private String prodNumber;

    /**
     * The dur resolve date.
     */
    private Timestamp durResolveDate;

    /**
     * The dispensed drug sig.
     */
    private String dispensedDrugSig;

    /**
     * The ERX Prescriber address exist.
     */
    private String prescriberAddress;

    /**
     * The HI Prescriber address exist.
     */
    private String hiPrescriberAddress;

    /**
     * The fill precheck datetime.
     */
    private Timestamp fillPrecheckDatetime;

    /**
     * The fx_fill_dur's e_script_msg_attribute_seq.
     */
    private BigInteger rfdScriptId;

    /**
     * The order_header's order_num.
     */
    private BigInteger orderNum;
}

