package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * RxFillAux is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class RxFillAux {

    private String allowConsolidate;

    private String attestCollected;

    private String autoFillBin;

    private Long awpPrice;

    private Long awpUnitPrice;

    private Byte batchEcs;

    private Integer calculatedDaysSupply;

    private Long calculatedInvPrice;

    private Long calculatedInvWacPrice;

    private Long calculatedPlanPrice;

    private String clinicId;

    private Byte cobCheck;

    private Long compProdSeq;

    private java.sql.Timestamp consentDatetime;

    private String consentStatus;

    private Long consentUsernum;

    private String consolidated;

    private String conversionLink;

    private Byte dailyTests;

    private java.sql.Timestamp dateProcessed;

    private String deFirstName;

    private String deLastName;

    private String dePhoneAreaCode;

    private String dePhoneNumber;

    private String directEntryValue;

    private String drugDbcode;

    private String drugDbcodeQualifier;

    private Long drugdbWacUnit;

    private Byte ecsBusy;

    private java.math.BigDecimal ecsclarMetricQuantity;

    private String erxByPassed;

    private java.sql.Timestamp erxReviewDatetime;

    private Long erxReviewUsernum;

    private java.math.BigInteger eScriptMsgAttributeSeq;

    private String externalLink;

    private Byte fillAutoStatus;

    private java.math.BigInteger fillCob3Status;

    private java.math.BigInteger fillCobStatus;

    private Integer fillDaysSupply;

    private java.sql.Timestamp fillDiscardDate;

    private java.sql.Timestamp fillDispenseDate;

    private java.sql.Timestamp fillEcsOverrideDate;

    private java.math.BigInteger fillEcsStatus;

    private java.sql.Timestamp fillExpirationDate;

    private Short fillInvStatus;

    private java.sql.Timestamp fillPostcheckDatetime;

    private String fillPostcheckRphInitials;

    private java.sql.Timestamp fillPrecheckDatetime;

    private String fillPrecheckRphInitials;

    private Long fillQtyDispensed;

    private String fillRxRphInitials;

    private java.sql.Timestamp fillShipDate;

    private Byte fillStatusNum;

    private String followUpPrescriberFirstName;

    private String followUpPrescriberLastName;

    private String followUpPrescriberMiddleName;

    private Integer ftfMasterDays;

    private Integer ftfMasterQty;

    private Long ingredientPaidAmt;

    private Integer initialFillDays;

    private Integer initialFillQty;

    private Integer itemsDispensed;

    private String messageId;

    private Long metricQuantity;

    private String mybInfo;

    private Integer numberMetricItems;

    private String originalRxNumber;

    private Long ortfQty;

    private Long patientCopay;

    private Byte patientLocationId;

    private Long payorPaidAmt;

    private Long pcDiagnosisNum;

    private String postEditRx;

    private Long ppNum;

    private String prescriberOrderNumber;

    private String prescribingReason;

    private String prohibitRenewalRequest;

    private java.sql.Timestamp refillByDate;

    private String relatesToMessageid;

    private Integer relationshipNum;

    private java.sql.Timestamp retailSoldDatetime;

    private String rewriteDueTo;

    private byte[] rowversion;

    private String rxFillIndicator;

    private java.math.BigInteger rxLink;

    private String rxNumber;

    private Byte rxoverride;

    private Long rxRefillNum;

    private Long rxUnitPrice;

    private Long rxWacUnitPrice;

    private Byte sanityok;

    private Byte sanityok2;

    private Byte sanityok3;

    private Long secondaryPpNum;

    private String sendFax;

    private String sourceHost;

    private String sourceUser;

    private Long tertiaryPpNum;

    private java.math.BigInteger tpGlCredited;

    private java.math.BigInteger tpGlPosted;

    private java.sql.Timestamp translatedDatetime;

    private String translatedImagePath;

    private String translatedInitials;

    private Integer translatedLanguageId;

    private String translatedSig;

    private String translatedSigParaphraseId;

    private String translatedSigPharaphrase;

    private String triplicateSerialNum;

    private Byte validated;

    private Long voucherAmount;

    private java.sql.Timestamp voucherDatetime;

    private String voucherNumber;

    private Long voucherOrigPatcopay;

    private Long voucherPatientAmount;

    private java.sql.Timestamp workflowComplete;

    public String getAllowConsolidate() {
        return allowConsolidate;
    }

    public void setAllowConsolidate(String allowConsolidate) {
        this.allowConsolidate = allowConsolidate;
    }

    public String getAttestCollected() {
        return attestCollected;
    }

    public void setAttestCollected(String attestCollected) {
        this.attestCollected = attestCollected;
    }

    public String getAutoFillBin() {
        return autoFillBin;
    }

    public void setAutoFillBin(String autoFillBin) {
        this.autoFillBin = autoFillBin;
    }

    public Long getAwpPrice() {
        return awpPrice;
    }

    public void setAwpPrice(Long awpPrice) {
        this.awpPrice = awpPrice;
    }

    public Long getAwpUnitPrice() {
        return awpUnitPrice;
    }

    public void setAwpUnitPrice(Long awpUnitPrice) {
        this.awpUnitPrice = awpUnitPrice;
    }

    public Byte getBatchEcs() {
        return batchEcs;
    }

    public void setBatchEcs(Byte batchEcs) {
        this.batchEcs = batchEcs;
    }

    public Integer getCalculatedDaysSupply() {
        return calculatedDaysSupply;
    }

    public void setCalculatedDaysSupply(Integer calculatedDaysSupply) {
        this.calculatedDaysSupply = calculatedDaysSupply;
    }

    public Long getCalculatedInvPrice() {
        return calculatedInvPrice;
    }

    public void setCalculatedInvPrice(Long calculatedInvPrice) {
        this.calculatedInvPrice = calculatedInvPrice;
    }

    public Long getCalculatedInvWacPrice() {
        return calculatedInvWacPrice;
    }

    public void setCalculatedInvWacPrice(Long calculatedInvWacPrice) {
        this.calculatedInvWacPrice = calculatedInvWacPrice;
    }

    public Long getCalculatedPlanPrice() {
        return calculatedPlanPrice;
    }

    public void setCalculatedPlanPrice(Long calculatedPlanPrice) {
        this.calculatedPlanPrice = calculatedPlanPrice;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public Byte getCobCheck() {
        return cobCheck;
    }

    public void setCobCheck(Byte cobCheck) {
        this.cobCheck = cobCheck;
    }

    public Long getCompProdSeq() {
        return compProdSeq;
    }

    public void setCompProdSeq(Long compProdSeq) {
        this.compProdSeq = compProdSeq;
    }

    public java.sql.Timestamp getConsentDatetime() {
        return consentDatetime;
    }

    public void setConsentDatetime(java.sql.Timestamp consentDatetime) {
        this.consentDatetime = consentDatetime;
    }

    public String getConsentStatus() {
        return consentStatus;
    }

    public void setConsentStatus(String consentStatus) {
        this.consentStatus = consentStatus;
    }

    public Long getConsentUsernum() {
        return consentUsernum;
    }

    public void setConsentUsernum(Long consentUsernum) {
        this.consentUsernum = consentUsernum;
    }

    public String getConsolidated() {
        return consolidated;
    }

    public void setConsolidated(String consolidated) {
        this.consolidated = consolidated;
    }

    public String getConversionLink() {
        return conversionLink;
    }

    public void setConversionLink(String conversionLink) {
        this.conversionLink = conversionLink;
    }

    public Byte getDailyTests() {
        return dailyTests;
    }

    public void setDailyTests(Byte dailyTests) {
        this.dailyTests = dailyTests;
    }

    public java.sql.Timestamp getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(java.sql.Timestamp dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    public String getDeFirstName() {
        return deFirstName;
    }

    public void setDeFirstName(String deFirstName) {
        this.deFirstName = deFirstName;
    }

    public String getDeLastName() {
        return deLastName;
    }

    public void setDeLastName(String deLastName) {
        this.deLastName = deLastName;
    }

    public String getDePhoneAreaCode() {
        return dePhoneAreaCode;
    }

    public void setDePhoneAreaCode(String dePhoneAreaCode) {
        this.dePhoneAreaCode = dePhoneAreaCode;
    }

    public String getDePhoneNumber() {
        return dePhoneNumber;
    }

    public void setDePhoneNumber(String dePhoneNumber) {
        this.dePhoneNumber = dePhoneNumber;
    }

    public String getDirectEntryValue() {
        return directEntryValue;
    }

    public void setDirectEntryValue(String directEntryValue) {
        this.directEntryValue = directEntryValue;
    }

    public String getDrugDbcode() {
        return drugDbcode;
    }

    public void setDrugDbcode(String drugDbcode) {
        this.drugDbcode = drugDbcode;
    }

    public String getDrugDbcodeQualifier() {
        return drugDbcodeQualifier;
    }

    public void setDrugDbcodeQualifier(String drugDbcodeQualifier) {
        this.drugDbcodeQualifier = drugDbcodeQualifier;
    }

    public Long getDrugdbWacUnit() {
        return drugdbWacUnit;
    }

    public void setDrugdbWacUnit(Long drugdbWacUnit) {
        this.drugdbWacUnit = drugdbWacUnit;
    }

    public Byte getEcsBusy() {
        return ecsBusy;
    }

    public void setEcsBusy(Byte ecsBusy) {
        this.ecsBusy = ecsBusy;
    }

    public java.math.BigDecimal getEcsclarMetricQuantity() {
        return ecsclarMetricQuantity;
    }

    public void setEcsclarMetricQuantity(java.math.BigDecimal ecsclarMetricQuantity) {
        this.ecsclarMetricQuantity = ecsclarMetricQuantity;
    }

    public String getErxByPassed() {
        return erxByPassed;
    }

    public void setErxByPassed(String erxByPassed) {
        this.erxByPassed = erxByPassed;
    }

    public java.sql.Timestamp getErxReviewDatetime() {
        return erxReviewDatetime;
    }

    public void setErxReviewDatetime(java.sql.Timestamp erxReviewDatetime) {
        this.erxReviewDatetime = erxReviewDatetime;
    }

    public Long getErxReviewUsernum() {
        return erxReviewUsernum;
    }

    public void setErxReviewUsernum(Long erxReviewUsernum) {
        this.erxReviewUsernum = erxReviewUsernum;
    }

    public java.math.BigInteger geteScriptMsgAttributeSeq() {
        return eScriptMsgAttributeSeq;
    }

    public void seteScriptMsgAttributeSeq(java.math.BigInteger eScriptMsgAttributeSeq) {
        this.eScriptMsgAttributeSeq = eScriptMsgAttributeSeq;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    public Byte getFillAutoStatus() {
        return fillAutoStatus;
    }

    public void setFillAutoStatus(Byte fillAutoStatus) {
        this.fillAutoStatus = fillAutoStatus;
    }

    public java.math.BigInteger getFillCob3Status() {
        return fillCob3Status;
    }

    public void setFillCob3Status(java.math.BigInteger fillCob3Status) {
        this.fillCob3Status = fillCob3Status;
    }

    public java.math.BigInteger getFillCobStatus() {
        return fillCobStatus;
    }

    public void setFillCobStatus(java.math.BigInteger fillCobStatus) {
        this.fillCobStatus = fillCobStatus;
    }

    public Integer getFillDaysSupply() {
        return fillDaysSupply;
    }

    public void setFillDaysSupply(Integer fillDaysSupply) {
        this.fillDaysSupply = fillDaysSupply;
    }

    public java.sql.Timestamp getFillDiscardDate() {
        return fillDiscardDate;
    }

    public void setFillDiscardDate(java.sql.Timestamp fillDiscardDate) {
        this.fillDiscardDate = fillDiscardDate;
    }

    public java.sql.Timestamp getFillDispenseDate() {
        return fillDispenseDate;
    }

    public void setFillDispenseDate(java.sql.Timestamp fillDispenseDate) {
        this.fillDispenseDate = fillDispenseDate;
    }

    public java.sql.Timestamp getFillEcsOverrideDate() {
        return fillEcsOverrideDate;
    }

    public void setFillEcsOverrideDate(java.sql.Timestamp fillEcsOverrideDate) {
        this.fillEcsOverrideDate = fillEcsOverrideDate;
    }

    public java.math.BigInteger getFillEcsStatus() {
        return fillEcsStatus;
    }

    public void setFillEcsStatus(java.math.BigInteger fillEcsStatus) {
        this.fillEcsStatus = fillEcsStatus;
    }

    public java.sql.Timestamp getFillExpirationDate() {
        return fillExpirationDate;
    }

    public void setFillExpirationDate(java.sql.Timestamp fillExpirationDate) {
        this.fillExpirationDate = fillExpirationDate;
    }

    public Short getFillInvStatus() {
        return fillInvStatus;
    }

    public void setFillInvStatus(Short fillInvStatus) {
        this.fillInvStatus = fillInvStatus;
    }

    public java.sql.Timestamp getFillPostcheckDatetime() {
        return fillPostcheckDatetime;
    }

    public void setFillPostcheckDatetime(java.sql.Timestamp fillPostcheckDatetime) {
        this.fillPostcheckDatetime = fillPostcheckDatetime;
    }

    public String getFillPostcheckRphInitials() {
        return fillPostcheckRphInitials;
    }

    public void setFillPostcheckRphInitials(String fillPostcheckRphInitials) {
        this.fillPostcheckRphInitials = fillPostcheckRphInitials;
    }

    public java.sql.Timestamp getFillPrecheckDatetime() {
        return fillPrecheckDatetime;
    }

    public void setFillPrecheckDatetime(java.sql.Timestamp fillPrecheckDatetime) {
        this.fillPrecheckDatetime = fillPrecheckDatetime;
    }

    public String getFillPrecheckRphInitials() {
        return fillPrecheckRphInitials;
    }

    public void setFillPrecheckRphInitials(String fillPrecheckRphInitials) {
        this.fillPrecheckRphInitials = fillPrecheckRphInitials;
    }

    public Long getFillQtyDispensed() {
        return fillQtyDispensed;
    }

    public void setFillQtyDispensed(Long fillQtyDispensed) {
        this.fillQtyDispensed = fillQtyDispensed;
    }

    public String getFillRxRphInitials() {
        return fillRxRphInitials;
    }

    public void setFillRxRphInitials(String fillRxRphInitials) {
        this.fillRxRphInitials = fillRxRphInitials;
    }

    public java.sql.Timestamp getFillShipDate() {
        return fillShipDate;
    }

    public void setFillShipDate(java.sql.Timestamp fillShipDate) {
        this.fillShipDate = fillShipDate;
    }

    public Byte getFillStatusNum() {
        return fillStatusNum;
    }

    public void setFillStatusNum(Byte fillStatusNum) {
        this.fillStatusNum = fillStatusNum;
    }

    public String getFollowUpPrescriberFirstName() {
        return followUpPrescriberFirstName;
    }

    public void setFollowUpPrescriberFirstName(String followUpPrescriberFirstName) {
        this.followUpPrescriberFirstName = followUpPrescriberFirstName;
    }

    public String getFollowUpPrescriberLastName() {
        return followUpPrescriberLastName;
    }

    public void setFollowUpPrescriberLastName(String followUpPrescriberLastName) {
        this.followUpPrescriberLastName = followUpPrescriberLastName;
    }

    public String getFollowUpPrescriberMiddleName() {
        return followUpPrescriberMiddleName;
    }

    public void setFollowUpPrescriberMiddleName(String followUpPrescriberMiddleName) {
        this.followUpPrescriberMiddleName = followUpPrescriberMiddleName;
    }

    public Integer getFtfMasterDays() {
        return ftfMasterDays;
    }

    public void setFtfMasterDays(Integer ftfMasterDays) {
        this.ftfMasterDays = ftfMasterDays;
    }

    public Integer getFtfMasterQty() {
        return ftfMasterQty;
    }

    public void setFtfMasterQty(Integer ftfMasterQty) {
        this.ftfMasterQty = ftfMasterQty;
    }

    public Long getIngredientPaidAmt() {
        return ingredientPaidAmt;
    }

    public void setIngredientPaidAmt(Long ingredientPaidAmt) {
        this.ingredientPaidAmt = ingredientPaidAmt;
    }

    public Integer getInitialFillDays() {
        return initialFillDays;
    }

    public void setInitialFillDays(Integer initialFillDays) {
        this.initialFillDays = initialFillDays;
    }

    public Integer getInitialFillQty() {
        return initialFillQty;
    }

    public void setInitialFillQty(Integer initialFillQty) {
        this.initialFillQty = initialFillQty;
    }

    public Integer getItemsDispensed() {
        return itemsDispensed;
    }

    public void setItemsDispensed(Integer itemsDispensed) {
        this.itemsDispensed = itemsDispensed;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Long getMetricQuantity() {
        return metricQuantity;
    }

    public void setMetricQuantity(Long metricQuantity) {
        this.metricQuantity = metricQuantity;
    }

    public String getMybInfo() {
        return mybInfo;
    }

    public void setMybInfo(String mybInfo) {
        this.mybInfo = mybInfo;
    }

    public Integer getNumberMetricItems() {
        return numberMetricItems;
    }

    public void setNumberMetricItems(Integer numberMetricItems) {
        this.numberMetricItems = numberMetricItems;
    }

    public String getOriginalRxNumber() {
        return originalRxNumber;
    }

    public void setOriginalRxNumber(String originalRxNumber) {
        this.originalRxNumber = originalRxNumber;
    }

    public Long getOrtfQty() {
        return ortfQty;
    }

    public void setOrtfQty(Long ortfQty) {
        this.ortfQty = ortfQty;
    }

    public Long getPatientCopay() {
        return patientCopay;
    }

    public void setPatientCopay(Long patientCopay) {
        this.patientCopay = patientCopay;
    }

    public Byte getPatientLocationId() {
        return patientLocationId;
    }

    public void setPatientLocationId(Byte patientLocationId) {
        this.patientLocationId = patientLocationId;
    }

    public Long getPayorPaidAmt() {
        return payorPaidAmt;
    }

    public void setPayorPaidAmt(Long payorPaidAmt) {
        this.payorPaidAmt = payorPaidAmt;
    }

    public Long getPcDiagnosisNum() {
        return pcDiagnosisNum;
    }

    public void setPcDiagnosisNum(Long pcDiagnosisNum) {
        this.pcDiagnosisNum = pcDiagnosisNum;
    }

    public String getPostEditRx() {
        return postEditRx;
    }

    public void setPostEditRx(String postEditRx) {
        this.postEditRx = postEditRx;
    }

    public Long getPpNum() {
        return ppNum;
    }

    public void setPpNum(Long ppNum) {
        this.ppNum = ppNum;
    }

    public String getPrescriberOrderNumber() {
        return prescriberOrderNumber;
    }

    public void setPrescriberOrderNumber(String prescriberOrderNumber) {
        this.prescriberOrderNumber = prescriberOrderNumber;
    }

    public String getPrescribingReason() {
        return prescribingReason;
    }

    public void setPrescribingReason(String prescribingReason) {
        this.prescribingReason = prescribingReason;
    }

    public String getProhibitRenewalRequest() {
        return prohibitRenewalRequest;
    }

    public void setProhibitRenewalRequest(String prohibitRenewalRequest) {
        this.prohibitRenewalRequest = prohibitRenewalRequest;
    }

    public java.sql.Timestamp getRefillByDate() {
        return refillByDate;
    }

    public void setRefillByDate(java.sql.Timestamp refillByDate) {
        this.refillByDate = refillByDate;
    }

    public String getRelatesToMessageid() {
        return relatesToMessageid;
    }

    public void setRelatesToMessageid(String relatesToMessageid) {
        this.relatesToMessageid = relatesToMessageid;
    }

    public Integer getRelationshipNum() {
        return relationshipNum;
    }

    public void setRelationshipNum(Integer relationshipNum) {
        this.relationshipNum = relationshipNum;
    }

    public java.sql.Timestamp getRetailSoldDatetime() {
        return retailSoldDatetime;
    }

    public void setRetailSoldDatetime(java.sql.Timestamp retailSoldDatetime) {
        this.retailSoldDatetime = retailSoldDatetime;
    }

    public String getRewriteDueTo() {
        return rewriteDueTo;
    }

    public void setRewriteDueTo(String rewriteDueTo) {
        this.rewriteDueTo = rewriteDueTo;
    }

    public byte[] getRowversion() {
        return rowversion;
    }

    public void setRowversion(byte[] rowversion) {
        this.rowversion = rowversion;
    }

    public String getRxFillIndicator() {
        return rxFillIndicator;
    }

    public void setRxFillIndicator(String rxFillIndicator) {
        this.rxFillIndicator = rxFillIndicator;
    }

    public java.math.BigInteger getRxLink() {
        return rxLink;
    }

    public void setRxLink(java.math.BigInteger rxLink) {
        this.rxLink = rxLink;
    }

    public String getRxNumber() {
        return rxNumber;
    }

    public void setRxNumber(String rxNumber) {
        this.rxNumber = rxNumber;
    }

    public Byte getRxoverride() {
        return rxoverride;
    }

    public void setRxoverride(Byte rxoverride) {
        this.rxoverride = rxoverride;
    }

    public Long getRxRefillNum() {
        return rxRefillNum;
    }

    public void setRxRefillNum(Long rxRefillNum) {
        this.rxRefillNum = rxRefillNum;
    }

    public Long getRxUnitPrice() {
        return rxUnitPrice;
    }

    public void setRxUnitPrice(Long rxUnitPrice) {
        this.rxUnitPrice = rxUnitPrice;
    }

    public Long getRxWacUnitPrice() {
        return rxWacUnitPrice;
    }

    public void setRxWacUnitPrice(Long rxWacUnitPrice) {
        this.rxWacUnitPrice = rxWacUnitPrice;
    }

    public Byte getSanityok() {
        return sanityok;
    }

    public void setSanityok(Byte sanityok) {
        this.sanityok = sanityok;
    }

    public Byte getSanityok2() {
        return sanityok2;
    }

    public void setSanityok2(Byte sanityok2) {
        this.sanityok2 = sanityok2;
    }

    public Byte getSanityok3() {
        return sanityok3;
    }

    public void setSanityok3(Byte sanityok3) {
        this.sanityok3 = sanityok3;
    }

    public Long getSecondaryPpNum() {
        return secondaryPpNum;
    }

    public void setSecondaryPpNum(Long secondaryPpNum) {
        this.secondaryPpNum = secondaryPpNum;
    }

    public String getSendFax() {
        return sendFax;
    }

    public void setSendFax(String sendFax) {
        this.sendFax = sendFax;
    }

    public String getSourceHost() {
        return sourceHost;
    }

    public void setSourceHost(String sourceHost) {
        this.sourceHost = sourceHost;
    }

    public String getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(String sourceUser) {
        this.sourceUser = sourceUser;
    }

    public Long getTertiaryPpNum() {
        return tertiaryPpNum;
    }

    public void setTertiaryPpNum(Long tertiaryPpNum) {
        this.tertiaryPpNum = tertiaryPpNum;
    }

    public java.math.BigInteger getTpGlCredited() {
        return tpGlCredited;
    }

    public void setTpGlCredited(java.math.BigInteger tpGlCredited) {
        this.tpGlCredited = tpGlCredited;
    }

    public java.math.BigInteger getTpGlPosted() {
        return tpGlPosted;
    }

    public void setTpGlPosted(java.math.BigInteger tpGlPosted) {
        this.tpGlPosted = tpGlPosted;
    }

    public java.sql.Timestamp getTranslatedDatetime() {
        return translatedDatetime;
    }

    public void setTranslatedDatetime(java.sql.Timestamp translatedDatetime) {
        this.translatedDatetime = translatedDatetime;
    }

    public String getTranslatedImagePath() {
        return translatedImagePath;
    }

    public void setTranslatedImagePath(String translatedImagePath) {
        this.translatedImagePath = translatedImagePath;
    }

    public String getTranslatedInitials() {
        return translatedInitials;
    }

    public void setTranslatedInitials(String translatedInitials) {
        this.translatedInitials = translatedInitials;
    }

    public Integer getTranslatedLanguageId() {
        return translatedLanguageId;
    }

    public void setTranslatedLanguageId(Integer translatedLanguageId) {
        this.translatedLanguageId = translatedLanguageId;
    }

    public String getTranslatedSig() {
        return translatedSig;
    }

    public void setTranslatedSig(String translatedSig) {
        this.translatedSig = translatedSig;
    }

    public String getTranslatedSigParaphraseId() {
        return translatedSigParaphraseId;
    }

    public void setTranslatedSigParaphraseId(String translatedSigParaphraseId) {
        this.translatedSigParaphraseId = translatedSigParaphraseId;
    }

    public String getTranslatedSigPharaphrase() {
        return translatedSigPharaphrase;
    }

    public void setTranslatedSigPharaphrase(String translatedSigPharaphrase) {
        this.translatedSigPharaphrase = translatedSigPharaphrase;
    }

    public String getTriplicateSerialNum() {
        return triplicateSerialNum;
    }

    public void setTriplicateSerialNum(String triplicateSerialNum) {
        this.triplicateSerialNum = triplicateSerialNum;
    }

    public Byte getValidated() {
        return validated;
    }

    public void setValidated(Byte validated) {
        this.validated = validated;
    }

    public Long getVoucherAmount() {
        return voucherAmount;
    }

    public void setVoucherAmount(Long voucherAmount) {
        this.voucherAmount = voucherAmount;
    }

    public java.sql.Timestamp getVoucherDatetime() {
        return voucherDatetime;
    }

    public void setVoucherDatetime(java.sql.Timestamp voucherDatetime) {
        this.voucherDatetime = voucherDatetime;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public Long getVoucherOrigPatcopay() {
        return voucherOrigPatcopay;
    }

    public void setVoucherOrigPatcopay(Long voucherOrigPatcopay) {
        this.voucherOrigPatcopay = voucherOrigPatcopay;
    }

    public Long getVoucherPatientAmount() {
        return voucherPatientAmount;
    }

    public void setVoucherPatientAmount(Long voucherPatientAmount) {
        this.voucherPatientAmount = voucherPatientAmount;
    }

    public java.sql.Timestamp getWorkflowComplete() {
        return workflowComplete;
    }

    public void setWorkflowComplete(java.sql.Timestamp workflowComplete) {
        this.workflowComplete = workflowComplete;
    }

}

