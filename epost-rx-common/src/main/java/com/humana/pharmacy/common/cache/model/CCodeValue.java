package com.humana.pharmacy.common.cache.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * The code value model stored in Infinispan cache.
 */
@Data
public class CCodeValue {
    /**
     * The Application name.
     */
    String applicationName;

    /**
     * The Code value desc.
     */
    String codeValueDesc;

    /**
     * The Code value id.
     */
    Integer codeValueId;

    /**
     * The Code value keyword.
     */
    String codeValueKeyword;

    /**
     * The Create ts.
     */
    Timestamp createTs;

    /**
     * The Fk code cat id.
     */
    String fkCodeCatId;

    /**
     * The Last updt by.
     */
    String lastUpdtBy;

    /**
     * The Last updt ts.
     */
    Timestamp lastUpdtTs;

    /**
     * The Restart required.
     */
    String restartRequired;

    /**
     * The Status flag.
     */
    String statusFlag;

    /**
     * Instantiates a new C code value.
     */
    public CCodeValue() {
    }

    /**
     * Instantiates a new C code value.
     *
     * @param codeValueId      the code value id
     * @param codeValueKeyword the code value keyword
     */
    public CCodeValue(Integer codeValueId, String codeValueKeyword) {
        this.codeValueId = codeValueId;
        this.codeValueKeyword = codeValueKeyword;
    }

    /**
     * Instantiates a new C code value.
     *
     * @param applicationName  the application name
     * @param codeValueDesc    the code value desc
     * @param codeValueId      the code value id
     * @param codeValueKeyword the code value keyword
     * @param createTs         the create ts
     * @param fkCodeCatId      the fk code cat id
     * @param lastUpdtBy       the last updt by
     * @param lastUpdtTs       the last updt ts
     * @param restartRequired  the restart required
     * @param statusFlag       the status flag
     */
    public CCodeValue(String applicationName, String codeValueDesc, Integer codeValueId,
                      String codeValueKeyword, Timestamp createTs, String fkCodeCatId,
                      String lastUpdtBy, Timestamp lastUpdtTs, String restartRequired, String statusFlag) {
        this.applicationName = applicationName;
        this.codeValueDesc = codeValueDesc;
        this.codeValueId = codeValueId;
        this.codeValueKeyword = codeValueKeyword;
        this.createTs = createTs;
        this.fkCodeCatId = fkCodeCatId;
        this.lastUpdtBy = lastUpdtBy;
        this.lastUpdtTs = lastUpdtTs;
        this.restartRequired = restartRequired;
        this.statusFlag = statusFlag;
    }
}
