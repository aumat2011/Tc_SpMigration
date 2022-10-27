package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * EScriptMsgAttributes is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class EScriptMsgAttributes {

    private Long allocated;

    private String autoRefill;

    private String cloneRx;

    private String conversionLink;

    private String copayConsentFlag;

    private Integer couponNum;

    private String cycleFillRunNumber;

    private java.sql.Timestamp dateFirstFilled;

    private java.sql.Timestamp dateLastFilled;

    private java.sql.Timestamp dispensedDate;

    private String dispensedDrugDaw;

    private String dispensedDrugDawDesc;

    private String dispensedDrugForm;

    private String dispensedDrugFormDesc;

    private String dispensedDrugItemAgency;

    private String dispensedDrugSig;

    private String dispensedDrugStrength;

    private String dispensedProduct;

    private Long dispensedProductId;

    private String dispensedProductNumber;

    private Long dispensedQuantity;

    private String doctorAddress;

    private String doctorCity;

    private String doctorContactNumber;

    private String doctorContactType;

    private String doctorContactTypeDesc;

    private String doctorFirstName;

    private String doctorId;

    private String doctorIdType;

    private String doctorIdTypeDesc;

    private String doctorLastName;

    private String doctorMiddleName;

    private Long doctorNum;

    private String doctorPracticeName;

    private String doctorState;

    private String doctorZip;

    private java.math.BigInteger ediMessageId;

    private String ediTransactionCode;

    private String erxId;

    private String erxMsgid;

    private String erxPo;

    private java.math.BigInteger eScriptMsgAttributeSeq;

    private String freeText;

    private String imageAvailable;

    private java.math.BigInteger imageTriageSeq;

    private String isMdpQueue;

    private String isMedsyncQueue;

    private java.sql.Timestamp lastRefillDate;

    private Long numRefills;

    private String orderInvoiceNumber;

    private Long originalNumRefills;

    private Integer originationNum;

    private Long otcPpnum;

    private String outreachMedication;

    private String patientAddress;

    private String patientAddress2;

    private String patientCardholderId;

    private String patientCardholderName;

    private java.math.BigInteger patientCareOrderNum;

    private String patientCity;

    private String patientContactNumber;

    private String patientContactType;

    private String patientContactTypeDesc;

    private java.sql.Timestamp patientDob;

    private String patientFirstName;

    private String patientGender;

    private String patientGroupName;

    private String patientGroupNumber;

    private String patientId;

    private String patientIdType;

    private String patientIdTypeDesc;

    private String patientLastName;

    private String patientMiddleName;

    private java.math.BigInteger patientNum;

    private Long patientPetSeq;

    private String patientState;

    private String patientZip;

    private java.math.BigInteger patPayScheduleSeq;

    private String pharmacistFirstName;

    private String pharmacistLastName;

    private String pharmacistMiddleName;

    private String pharmacyAddress;

    private String pharmacyCity;

    private String pharmacyContactNumber;

    private String pharmacyContactType;

    private String pharmacyContactTypeDesc;

    private String pharmacyId;

    private String pharmacyIdType;

    private String pharmacyIdTypeDesc;

    private String pharmacyName;

    private String pharmacyState;

    private String pharmacyZip;

    private String previousRxNumber;

    private java.sql.Timestamp pwoExpDate;

    private String pwoItem;

    private String refillStatusResponse;

    private String refillStatusResponseDesc;

    private byte[] rowversion;

    private java.sql.Timestamp rxExpirationDate;

    private String rxNumber;

    private java.math.BigInteger rxQtyLeft;

    private Integer rxRefillsLeft;

    private Byte rxStatusCodeNum;

    private String scriptLegible;

    private java.sql.Timestamp shipperPickupDatetime;

    private String sourceHost;

    private String sourceUser;

    private Long sysUserNum;

    private Long sysUserTaskId;

    private Long tpAddrSeq;

    private Long tradingPartnerNum;

    private java.sql.Timestamp writtenDate;

    private String writtenDrugDaw;

    private String writtenDrugDawDesc;

    private String writtenDrugForm;

    private String writtenDrugFormDesc;

    private String writtenDrugItemAgency;

    private String writtenDrugSig;

    private String writtenDrugSigCode;

    private Long writtenDrugSigFreq;

    private String writtenDrugStrength;

    private String writtenProduct;

    private Long writtenProductId;

    private String writtenProductNumber;

    private Long writtenQuantity;

    public Long getAllocated() {
        return allocated;
    }

    public void setAllocated(Long allocated) {
        this.allocated = allocated;
    }

    public String getAutoRefill() {
        return autoRefill;
    }

    public void setAutoRefill(String autoRefill) {
        this.autoRefill = autoRefill;
    }

    public String getCloneRx() {
        return cloneRx;
    }

    public void setCloneRx(String cloneRx) {
        this.cloneRx = cloneRx;
    }

    public String getConversionLink() {
        return conversionLink;
    }

    public void setConversionLink(String conversionLink) {
        this.conversionLink = conversionLink;
    }

    public String getCopayConsentFlag() {
        return copayConsentFlag;
    }

    public void setCopayConsentFlag(String copayConsentFlag) {
        this.copayConsentFlag = copayConsentFlag;
    }

    public Integer getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(Integer couponNum) {
        this.couponNum = couponNum;
    }

    public String getCycleFillRunNumber() {
        return cycleFillRunNumber;
    }

    public void setCycleFillRunNumber(String cycleFillRunNumber) {
        this.cycleFillRunNumber = cycleFillRunNumber;
    }

    public java.sql.Timestamp getDateFirstFilled() {
        return dateFirstFilled;
    }

    public void setDateFirstFilled(java.sql.Timestamp dateFirstFilled) {
        this.dateFirstFilled = dateFirstFilled;
    }

    public java.sql.Timestamp getDateLastFilled() {
        return dateLastFilled;
    }

    public void setDateLastFilled(java.sql.Timestamp dateLastFilled) {
        this.dateLastFilled = dateLastFilled;
    }

    public java.sql.Timestamp getDispensedDate() {
        return dispensedDate;
    }

    public void setDispensedDate(java.sql.Timestamp dispensedDate) {
        this.dispensedDate = dispensedDate;
    }

    public String getDispensedDrugDaw() {
        return dispensedDrugDaw;
    }

    public void setDispensedDrugDaw(String dispensedDrugDaw) {
        this.dispensedDrugDaw = dispensedDrugDaw;
    }

    public String getDispensedDrugDawDesc() {
        return dispensedDrugDawDesc;
    }

    public void setDispensedDrugDawDesc(String dispensedDrugDawDesc) {
        this.dispensedDrugDawDesc = dispensedDrugDawDesc;
    }

    public String getDispensedDrugForm() {
        return dispensedDrugForm;
    }

    public void setDispensedDrugForm(String dispensedDrugForm) {
        this.dispensedDrugForm = dispensedDrugForm;
    }

    public String getDispensedDrugFormDesc() {
        return dispensedDrugFormDesc;
    }

    public void setDispensedDrugFormDesc(String dispensedDrugFormDesc) {
        this.dispensedDrugFormDesc = dispensedDrugFormDesc;
    }

    public String getDispensedDrugItemAgency() {
        return dispensedDrugItemAgency;
    }

    public void setDispensedDrugItemAgency(String dispensedDrugItemAgency) {
        this.dispensedDrugItemAgency = dispensedDrugItemAgency;
    }

    public String getDispensedDrugSig() {
        return dispensedDrugSig;
    }

    public void setDispensedDrugSig(String dispensedDrugSig) {
        this.dispensedDrugSig = dispensedDrugSig;
    }

    public String getDispensedDrugStrength() {
        return dispensedDrugStrength;
    }

    public void setDispensedDrugStrength(String dispensedDrugStrength) {
        this.dispensedDrugStrength = dispensedDrugStrength;
    }

    public String getDispensedProduct() {
        return dispensedProduct;
    }

    public void setDispensedProduct(String dispensedProduct) {
        this.dispensedProduct = dispensedProduct;
    }

    public Long getDispensedProductId() {
        return dispensedProductId;
    }

    public void setDispensedProductId(Long dispensedProductId) {
        this.dispensedProductId = dispensedProductId;
    }

    public String getDispensedProductNumber() {
        return dispensedProductNumber;
    }

    public void setDispensedProductNumber(String dispensedProductNumber) {
        this.dispensedProductNumber = dispensedProductNumber;
    }

    public Long getDispensedQuantity() {
        return dispensedQuantity;
    }

    public void setDispensedQuantity(Long dispensedQuantity) {
        this.dispensedQuantity = dispensedQuantity;
    }

    public String getDoctorAddress() {
        return doctorAddress;
    }

    public void setDoctorAddress(String doctorAddress) {
        this.doctorAddress = doctorAddress;
    }

    public String getDoctorCity() {
        return doctorCity;
    }

    public void setDoctorCity(String doctorCity) {
        this.doctorCity = doctorCity;
    }

    public String getDoctorContactNumber() {
        return doctorContactNumber;
    }

    public void setDoctorContactNumber(String doctorContactNumber) {
        this.doctorContactNumber = doctorContactNumber;
    }

    public String getDoctorContactType() {
        return doctorContactType;
    }

    public void setDoctorContactType(String doctorContactType) {
        this.doctorContactType = doctorContactType;
    }

    public String getDoctorContactTypeDesc() {
        return doctorContactTypeDesc;
    }

    public void setDoctorContactTypeDesc(String doctorContactTypeDesc) {
        this.doctorContactTypeDesc = doctorContactTypeDesc;
    }

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorIdType() {
        return doctorIdType;
    }

    public void setDoctorIdType(String doctorIdType) {
        this.doctorIdType = doctorIdType;
    }

    public String getDoctorIdTypeDesc() {
        return doctorIdTypeDesc;
    }

    public void setDoctorIdTypeDesc(String doctorIdTypeDesc) {
        this.doctorIdTypeDesc = doctorIdTypeDesc;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public String getDoctorMiddleName() {
        return doctorMiddleName;
    }

    public void setDoctorMiddleName(String doctorMiddleName) {
        this.doctorMiddleName = doctorMiddleName;
    }

    public Long getDoctorNum() {
        return doctorNum;
    }

    public void setDoctorNum(Long doctorNum) {
        this.doctorNum = doctorNum;
    }

    public String getDoctorPracticeName() {
        return doctorPracticeName;
    }

    public void setDoctorPracticeName(String doctorPracticeName) {
        this.doctorPracticeName = doctorPracticeName;
    }

    public String getDoctorState() {
        return doctorState;
    }

    public void setDoctorState(String doctorState) {
        this.doctorState = doctorState;
    }

    public String getDoctorZip() {
        return doctorZip;
    }

    public void setDoctorZip(String doctorZip) {
        this.doctorZip = doctorZip;
    }

    public java.math.BigInteger getEdiMessageId() {
        return ediMessageId;
    }

    public void setEdiMessageId(java.math.BigInteger ediMessageId) {
        this.ediMessageId = ediMessageId;
    }

    public String getEdiTransactionCode() {
        return ediTransactionCode;
    }

    public void setEdiTransactionCode(String ediTransactionCode) {
        this.ediTransactionCode = ediTransactionCode;
    }

    public String getErxId() {
        return erxId;
    }

    public void setErxId(String erxId) {
        this.erxId = erxId;
    }

    public String getErxMsgid() {
        return erxMsgid;
    }

    public void setErxMsgid(String erxMsgid) {
        this.erxMsgid = erxMsgid;
    }

    public String getErxPo() {
        return erxPo;
    }

    public void setErxPo(String erxPo) {
        this.erxPo = erxPo;
    }

    public java.math.BigInteger geteScriptMsgAttributeSeq() {
        return eScriptMsgAttributeSeq;
    }

    public void seteScriptMsgAttributeSeq(java.math.BigInteger eScriptMsgAttributeSeq) {
        this.eScriptMsgAttributeSeq = eScriptMsgAttributeSeq;
    }

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    public String getImageAvailable() {
        return imageAvailable;
    }

    public void setImageAvailable(String imageAvailable) {
        this.imageAvailable = imageAvailable;
    }

    public java.math.BigInteger getImageTriageSeq() {
        return imageTriageSeq;
    }

    public void setImageTriageSeq(java.math.BigInteger imageTriageSeq) {
        this.imageTriageSeq = imageTriageSeq;
    }

    public String getIsMdpQueue() {
        return isMdpQueue;
    }

    public void setIsMdpQueue(String isMdpQueue) {
        this.isMdpQueue = isMdpQueue;
    }

    public String getIsMedsyncQueue() {
        return isMedsyncQueue;
    }

    public void setIsMedsyncQueue(String isMedsyncQueue) {
        this.isMedsyncQueue = isMedsyncQueue;
    }

    public java.sql.Timestamp getLastRefillDate() {
        return lastRefillDate;
    }

    public void setLastRefillDate(java.sql.Timestamp lastRefillDate) {
        this.lastRefillDate = lastRefillDate;
    }

    public Long getNumRefills() {
        return numRefills;
    }

    public void setNumRefills(Long numRefills) {
        this.numRefills = numRefills;
    }

    public String getOrderInvoiceNumber() {
        return orderInvoiceNumber;
    }

    public void setOrderInvoiceNumber(String orderInvoiceNumber) {
        this.orderInvoiceNumber = orderInvoiceNumber;
    }

    public Long getOriginalNumRefills() {
        return originalNumRefills;
    }

    public void setOriginalNumRefills(Long originalNumRefills) {
        this.originalNumRefills = originalNumRefills;
    }

    public Integer getOriginationNum() {
        return originationNum;
    }

    public void setOriginationNum(Integer originationNum) {
        this.originationNum = originationNum;
    }

    public Long getOtcPpnum() {
        return otcPpnum;
    }

    public void setOtcPpnum(Long otcPpnum) {
        this.otcPpnum = otcPpnum;
    }

    public String getOutreachMedication() {
        return outreachMedication;
    }

    public void setOutreachMedication(String outreachMedication) {
        this.outreachMedication = outreachMedication;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientAddress2() {
        return patientAddress2;
    }

    public void setPatientAddress2(String patientAddress2) {
        this.patientAddress2 = patientAddress2;
    }

    public String getPatientCardholderId() {
        return patientCardholderId;
    }

    public void setPatientCardholderId(String patientCardholderId) {
        this.patientCardholderId = patientCardholderId;
    }

    public String getPatientCardholderName() {
        return patientCardholderName;
    }

    public void setPatientCardholderName(String patientCardholderName) {
        this.patientCardholderName = patientCardholderName;
    }

    public java.math.BigInteger getPatientCareOrderNum() {
        return patientCareOrderNum;
    }

    public void setPatientCareOrderNum(java.math.BigInteger patientCareOrderNum) {
        this.patientCareOrderNum = patientCareOrderNum;
    }

    public String getPatientCity() {
        return patientCity;
    }

    public void setPatientCity(String patientCity) {
        this.patientCity = patientCity;
    }

    public String getPatientContactNumber() {
        return patientContactNumber;
    }

    public void setPatientContactNumber(String patientContactNumber) {
        this.patientContactNumber = patientContactNumber;
    }

    public String getPatientContactType() {
        return patientContactType;
    }

    public void setPatientContactType(String patientContactType) {
        this.patientContactType = patientContactType;
    }

    public String getPatientContactTypeDesc() {
        return patientContactTypeDesc;
    }

    public void setPatientContactTypeDesc(String patientContactTypeDesc) {
        this.patientContactTypeDesc = patientContactTypeDesc;
    }

    public java.sql.Timestamp getPatientDob() {
        return patientDob;
    }

    public void setPatientDob(java.sql.Timestamp patientDob) {
        this.patientDob = patientDob;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientGroupName() {
        return patientGroupName;
    }

    public void setPatientGroupName(String patientGroupName) {
        this.patientGroupName = patientGroupName;
    }

    public String getPatientGroupNumber() {
        return patientGroupNumber;
    }

    public void setPatientGroupNumber(String patientGroupNumber) {
        this.patientGroupNumber = patientGroupNumber;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientIdType() {
        return patientIdType;
    }

    public void setPatientIdType(String patientIdType) {
        this.patientIdType = patientIdType;
    }

    public String getPatientIdTypeDesc() {
        return patientIdTypeDesc;
    }

    public void setPatientIdTypeDesc(String patientIdTypeDesc) {
        this.patientIdTypeDesc = patientIdTypeDesc;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientMiddleName() {
        return patientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public java.math.BigInteger getPatientNum() {
        return patientNum;
    }

    public void setPatientNum(java.math.BigInteger patientNum) {
        this.patientNum = patientNum;
    }

    public Long getPatientPetSeq() {
        return patientPetSeq;
    }

    public void setPatientPetSeq(Long patientPetSeq) {
        this.patientPetSeq = patientPetSeq;
    }

    public String getPatientState() {
        return patientState;
    }

    public void setPatientState(String patientState) {
        this.patientState = patientState;
    }

    public String getPatientZip() {
        return patientZip;
    }

    public void setPatientZip(String patientZip) {
        this.patientZip = patientZip;
    }

    public java.math.BigInteger getPatPayScheduleSeq() {
        return patPayScheduleSeq;
    }

    public void setPatPayScheduleSeq(java.math.BigInteger patPayScheduleSeq) {
        this.patPayScheduleSeq = patPayScheduleSeq;
    }

    public String getPharmacistFirstName() {
        return pharmacistFirstName;
    }

    public void setPharmacistFirstName(String pharmacistFirstName) {
        this.pharmacistFirstName = pharmacistFirstName;
    }

    public String getPharmacistLastName() {
        return pharmacistLastName;
    }

    public void setPharmacistLastName(String pharmacistLastName) {
        this.pharmacistLastName = pharmacistLastName;
    }

    public String getPharmacistMiddleName() {
        return pharmacistMiddleName;
    }

    public void setPharmacistMiddleName(String pharmacistMiddleName) {
        this.pharmacistMiddleName = pharmacistMiddleName;
    }

    public String getPharmacyAddress() {
        return pharmacyAddress;
    }

    public void setPharmacyAddress(String pharmacyAddress) {
        this.pharmacyAddress = pharmacyAddress;
    }

    public String getPharmacyCity() {
        return pharmacyCity;
    }

    public void setPharmacyCity(String pharmacyCity) {
        this.pharmacyCity = pharmacyCity;
    }

    public String getPharmacyContactNumber() {
        return pharmacyContactNumber;
    }

    public void setPharmacyContactNumber(String pharmacyContactNumber) {
        this.pharmacyContactNumber = pharmacyContactNumber;
    }

    public String getPharmacyContactType() {
        return pharmacyContactType;
    }

    public void setPharmacyContactType(String pharmacyContactType) {
        this.pharmacyContactType = pharmacyContactType;
    }

    public String getPharmacyContactTypeDesc() {
        return pharmacyContactTypeDesc;
    }

    public void setPharmacyContactTypeDesc(String pharmacyContactTypeDesc) {
        this.pharmacyContactTypeDesc = pharmacyContactTypeDesc;
    }

    public String getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(String pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getPharmacyIdType() {
        return pharmacyIdType;
    }

    public void setPharmacyIdType(String pharmacyIdType) {
        this.pharmacyIdType = pharmacyIdType;
    }

    public String getPharmacyIdTypeDesc() {
        return pharmacyIdTypeDesc;
    }

    public void setPharmacyIdTypeDesc(String pharmacyIdTypeDesc) {
        this.pharmacyIdTypeDesc = pharmacyIdTypeDesc;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getPharmacyState() {
        return pharmacyState;
    }

    public void setPharmacyState(String pharmacyState) {
        this.pharmacyState = pharmacyState;
    }

    public String getPharmacyZip() {
        return pharmacyZip;
    }

    public void setPharmacyZip(String pharmacyZip) {
        this.pharmacyZip = pharmacyZip;
    }

    public String getPreviousRxNumber() {
        return previousRxNumber;
    }

    public void setPreviousRxNumber(String previousRxNumber) {
        this.previousRxNumber = previousRxNumber;
    }

    public java.sql.Timestamp getPwoExpDate() {
        return pwoExpDate;
    }

    public void setPwoExpDate(java.sql.Timestamp pwoExpDate) {
        this.pwoExpDate = pwoExpDate;
    }

    public String getPwoItem() {
        return pwoItem;
    }

    public void setPwoItem(String pwoItem) {
        this.pwoItem = pwoItem;
    }

    public String getRefillStatusResponse() {
        return refillStatusResponse;
    }

    public void setRefillStatusResponse(String refillStatusResponse) {
        this.refillStatusResponse = refillStatusResponse;
    }

    public String getRefillStatusResponseDesc() {
        return refillStatusResponseDesc;
    }

    public void setRefillStatusResponseDesc(String refillStatusResponseDesc) {
        this.refillStatusResponseDesc = refillStatusResponseDesc;
    }

    public byte[] getRowversion() {
        return rowversion;
    }

    public void setRowversion(byte[] rowversion) {
        this.rowversion = rowversion;
    }

    public java.sql.Timestamp getRxExpirationDate() {
        return rxExpirationDate;
    }

    public void setRxExpirationDate(java.sql.Timestamp rxExpirationDate) {
        this.rxExpirationDate = rxExpirationDate;
    }

    public String getRxNumber() {
        return rxNumber;
    }

    public void setRxNumber(String rxNumber) {
        this.rxNumber = rxNumber;
    }

    public java.math.BigInteger getRxQtyLeft() {
        return rxQtyLeft;
    }

    public void setRxQtyLeft(java.math.BigInteger rxQtyLeft) {
        this.rxQtyLeft = rxQtyLeft;
    }

    public Integer getRxRefillsLeft() {
        return rxRefillsLeft;
    }

    public void setRxRefillsLeft(Integer rxRefillsLeft) {
        this.rxRefillsLeft = rxRefillsLeft;
    }

    public Byte getRxStatusCodeNum() {
        return rxStatusCodeNum;
    }

    public void setRxStatusCodeNum(Byte rxStatusCodeNum) {
        this.rxStatusCodeNum = rxStatusCodeNum;
    }

    public String getScriptLegible() {
        return scriptLegible;
    }

    public void setScriptLegible(String scriptLegible) {
        this.scriptLegible = scriptLegible;
    }

    public java.sql.Timestamp getShipperPickupDatetime() {
        return shipperPickupDatetime;
    }

    public void setShipperPickupDatetime(java.sql.Timestamp shipperPickupDatetime) {
        this.shipperPickupDatetime = shipperPickupDatetime;
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

    public Long getSysUserNum() {
        return sysUserNum;
    }

    public void setSysUserNum(Long sysUserNum) {
        this.sysUserNum = sysUserNum;
    }

    public Long getSysUserTaskId() {
        return sysUserTaskId;
    }

    public void setSysUserTaskId(Long sysUserTaskId) {
        this.sysUserTaskId = sysUserTaskId;
    }

    public Long getTpAddrSeq() {
        return tpAddrSeq;
    }

    public void setTpAddrSeq(Long tpAddrSeq) {
        this.tpAddrSeq = tpAddrSeq;
    }

    public Long getTradingPartnerNum() {
        return tradingPartnerNum;
    }

    public void setTradingPartnerNum(Long tradingPartnerNum) {
        this.tradingPartnerNum = tradingPartnerNum;
    }

    public java.sql.Timestamp getWrittenDate() {
        return writtenDate;
    }

    public void setWrittenDate(java.sql.Timestamp writtenDate) {
        this.writtenDate = writtenDate;
    }

    public String getWrittenDrugDaw() {
        return writtenDrugDaw;
    }

    public void setWrittenDrugDaw(String writtenDrugDaw) {
        this.writtenDrugDaw = writtenDrugDaw;
    }

    public String getWrittenDrugDawDesc() {
        return writtenDrugDawDesc;
    }

    public void setWrittenDrugDawDesc(String writtenDrugDawDesc) {
        this.writtenDrugDawDesc = writtenDrugDawDesc;
    }

    public String getWrittenDrugForm() {
        return writtenDrugForm;
    }

    public void setWrittenDrugForm(String writtenDrugForm) {
        this.writtenDrugForm = writtenDrugForm;
    }

    public String getWrittenDrugFormDesc() {
        return writtenDrugFormDesc;
    }

    public void setWrittenDrugFormDesc(String writtenDrugFormDesc) {
        this.writtenDrugFormDesc = writtenDrugFormDesc;
    }

    public String getWrittenDrugItemAgency() {
        return writtenDrugItemAgency;
    }

    public void setWrittenDrugItemAgency(String writtenDrugItemAgency) {
        this.writtenDrugItemAgency = writtenDrugItemAgency;
    }

    public String getWrittenDrugSig() {
        return writtenDrugSig;
    }

    public void setWrittenDrugSig(String writtenDrugSig) {
        this.writtenDrugSig = writtenDrugSig;
    }

    public String getWrittenDrugSigCode() {
        return writtenDrugSigCode;
    }

    public void setWrittenDrugSigCode(String writtenDrugSigCode) {
        this.writtenDrugSigCode = writtenDrugSigCode;
    }

    public Long getWrittenDrugSigFreq() {
        return writtenDrugSigFreq;
    }

    public void setWrittenDrugSigFreq(Long writtenDrugSigFreq) {
        this.writtenDrugSigFreq = writtenDrugSigFreq;
    }

    public String getWrittenDrugStrength() {
        return writtenDrugStrength;
    }

    public void setWrittenDrugStrength(String writtenDrugStrength) {
        this.writtenDrugStrength = writtenDrugStrength;
    }

    public String getWrittenProduct() {
        return writtenProduct;
    }

    public void setWrittenProduct(String writtenProduct) {
        this.writtenProduct = writtenProduct;
    }

    public Long getWrittenProductId() {
        return writtenProductId;
    }

    public void setWrittenProductId(Long writtenProductId) {
        this.writtenProductId = writtenProductId;
    }

    public String getWrittenProductNumber() {
        return writtenProductNumber;
    }

    public void setWrittenProductNumber(String writtenProductNumber) {
        this.writtenProductNumber = writtenProductNumber;
    }

    public Long getWrittenQuantity() {
        return writtenQuantity;
    }

    public void setWrittenQuantity(Long writtenQuantity) {
        this.writtenQuantity = writtenQuantity;
    }

}

