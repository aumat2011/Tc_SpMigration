package com.humana.pharmacy.common.cache.model;

import lombok.Data;

/**
 * The RX defaults model stored in Infinispan cache.
 *
 * <p>All fields within this class are annotated with @ProtoField, so that it transferred to Infinispan remote server
 * cache using Protobuf. The all fields constructor with @ProtoFactory annotation is also provided.
 */
@Data
public class CRxDefaults {
    /**
     * Max PV days.
     */
    Short maxPvDays;

    /**
     * Whether need OTC check.
     */
    String otcAsRxPrecheck;

    /**
     * Whether need precheck all.
     */
    String precheckAll;

    /**
     * Whether need DME check.
     */
    String pwoPrecheck;

    /**
     * Whether need to check narcotic refills.
     */
    String checkNarcoticRefills;

    /**
     * Default constructor.
     */
    public CRxDefaults() {
        // Empty
    }

    /**
     * Constructor with all fields.
     *
     * @param maxPvDays            Max PV days.
     * @param otcAsRxPrecheck      Whether need OTC check.
     * @param precheckAll          Whether need precheck all.
     * @param pwoPrecheck          Whether need DME check.
     * @param checkNarcoticRefills Whether need to check narcotic code.
     */
    public CRxDefaults(Short maxPvDays, String otcAsRxPrecheck, String precheckAll, String pwoPrecheck,
                       String checkNarcoticRefills) {
        this.maxPvDays = maxPvDays;
        this.otcAsRxPrecheck = otcAsRxPrecheck;
        this.precheckAll = precheckAll;
        this.pwoPrecheck = pwoPrecheck;
        this.checkNarcoticRefills = checkNarcoticRefills;
    }
}

