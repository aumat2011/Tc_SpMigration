package com.humana.pharmacy.dto;

import com.humana.pharmacy.common.dto.BaseEScriptDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AnsPatientDmeCheckDTO {

    /**
     * The patient number.
     */
    private Long patientNum;

    /**
     * The origin number.
     */
    private Integer originNum;

    /**
     * The edi transaction code.
     */
    private String editxnCode;

    /**
     * The dispended product number.
     */
    private String ndcNumber;

    /**
     * The pwo Item.
     */
    private Character pwoItem;

    /**
     * The pwo expiration date.
     */
    private Date pwoExpDate;

    /**
     * The daily tests.
     */
    private Integer dailyTests;

    /**
     * To attest collected.
     */
    private Character attestCollect;

    /**
     * The pdg elig
     */
    private Character pdgElig;

    /**
     * The mapd_elig
     */
    private Character mapdElig;

    /**
     * The overutilizer
     */
    private Character overutilizer;

    /**
     * The insulindep
     */
    private Character insulindep;

    /**
     * The dme collection
     */
    private Character dmeCollection;

    /**
     * The jurisdiction
     */
    private String jurisdiction;

    /**
     * The id Seq 1
     */
    private Long idSeq1;

    /**
     * The id Seq 2
     */
    private Long idSeq2;

    /**
     * The orgdesc
     */
    private String orgDesc;

}
