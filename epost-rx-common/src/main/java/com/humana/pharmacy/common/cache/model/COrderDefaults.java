package com.humana.pharmacy.common.cache.model;

import lombok.Data;

/**
 * The Order defaults model stored in Infinispan cache.
 */
@Data
public class COrderDefaults {
    /**
     * The familyMode.
     */
    String familyMode;

    /**
     * Default constructor.
     */
    public COrderDefaults() {
        // Empty
    }

    /**
     * Constructor with all fields.
     *
     * @param familyMode The familyMode
     */
    public COrderDefaults(String familyMode) {
        this.familyMode = familyMode;
    }
}

