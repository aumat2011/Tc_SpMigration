package com.humana.pharmacy.common.model;

import javax.annotation.Generated;

/**
 * OrderHeader is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class OrderHeader {

    private String arValidated;

    private String autoCancel;

    private Long autoDispenseLocation;

    private Long autoError;

    private String autoRoute;

    private String autoRtsSplit;

    private String autoShipComplete;

    private String autoSplit;

    private Integer autoWorkflowTypeNum;

    private String basketNumber;

    private Byte batchecs;

    private java.sql.Timestamp cancelDatetime;

    private Long cancelUsernum;

    private Byte ccBusy;

    private String conversionLink;

    private Long currentBalance;

    private String cycleFillRunNumber;

    private java.sql.Timestamp dateProcessed;

    private java.sql.Timestamp dateReceived;

    private String disRecovery;

    private String externalReview;

    private java.sql.Timestamp externalReviewDatetime;

    private String extSrcinfo;

    private String financeValidated;

    private String hippaValidated;

    private java.sql.Timestamp inductDatetime;

    private java.sql.Timestamp intakeComplete;

    private String intakeFillLocation;

    private String isMybOrder;

    private Byte locked;

    private java.math.BigInteger mergeParentOrderNum;

    private java.sql.Timestamp needByDate;

    private java.sql.Timestamp notified;

    private Long orderAmount;

    private Byte orderAutoFilled;

    private java.sql.Timestamp orderDate;

    private Byte orderDur;

    private java.math.BigInteger orderGlPosted;

    private String orderHighPriority;

    private String orderInvoiceNumber;

    private java.math.BigInteger orderNum;

    private String orderOftValidated;

    private String orderPatientValidated;

    private String orderPicked;

    private String orderPrint;

    private java.sql.Timestamp orderSplitDate;

    private Byte orderStatusNum;

    private Long orderTax;

    private Long orderTotalAmount;

    private Long orderTotalPayments;

    private String orderToteNumber;

    private String orderType;

    private String orderValidated;

    private Integer originationNum;

    private Byte pendingShipment;

    private Integer pickupDeliveryMethodsSeq;

    private String postEdit;

    private String privateLabel;

    private String pwoChecked;

    private java.sql.Timestamp quoteDatetime;

    private java.sql.Timestamp replaceDatetime;

    private java.math.BigInteger replaceParentOrderNum;

    private java.sql.Timestamp replaceReviewDatetime;

    private Long replaceReviewUsernum;

    private java.sql.Timestamp returnDatetime;

    private java.sql.Timestamp returnReviewDatetime;

    private Long returnReviewUsernum;

    private Byte returnShippingCost;

    private byte[] rowversion;

    private String salestaxCalculated;

    private Byte sanityok;

    private Byte sanityok2;

    private Byte sanityok3;

    private String sourceHost;

    private String sourceUser;

    private Boolean specialtyValidated;

    private String suborderValidated;

    private Long sysUserNum;

    private Long tradingPartnerNum;

    private java.sql.Timestamp workflowComplete;

    private Integer workflowNum;

    public String getArValidated() {
        return arValidated;
    }

    public void setArValidated(String arValidated) {
        this.arValidated = arValidated;
    }

    public String getAutoCancel() {
        return autoCancel;
    }

    public void setAutoCancel(String autoCancel) {
        this.autoCancel = autoCancel;
    }

    public Long getAutoDispenseLocation() {
        return autoDispenseLocation;
    }

    public void setAutoDispenseLocation(Long autoDispenseLocation) {
        this.autoDispenseLocation = autoDispenseLocation;
    }

    public Long getAutoError() {
        return autoError;
    }

    public void setAutoError(Long autoError) {
        this.autoError = autoError;
    }

    public String getAutoRoute() {
        return autoRoute;
    }

    public void setAutoRoute(String autoRoute) {
        this.autoRoute = autoRoute;
    }

    public String getAutoRtsSplit() {
        return autoRtsSplit;
    }

    public void setAutoRtsSplit(String autoRtsSplit) {
        this.autoRtsSplit = autoRtsSplit;
    }

    public String getAutoShipComplete() {
        return autoShipComplete;
    }

    public void setAutoShipComplete(String autoShipComplete) {
        this.autoShipComplete = autoShipComplete;
    }

    public String getAutoSplit() {
        return autoSplit;
    }

    public void setAutoSplit(String autoSplit) {
        this.autoSplit = autoSplit;
    }

    public Integer getAutoWorkflowTypeNum() {
        return autoWorkflowTypeNum;
    }

    public void setAutoWorkflowTypeNum(Integer autoWorkflowTypeNum) {
        this.autoWorkflowTypeNum = autoWorkflowTypeNum;
    }

    public String getBasketNumber() {
        return basketNumber;
    }

    public void setBasketNumber(String basketNumber) {
        this.basketNumber = basketNumber;
    }

    public Byte getBatchecs() {
        return batchecs;
    }

    public void setBatchecs(Byte batchecs) {
        this.batchecs = batchecs;
    }

    public java.sql.Timestamp getCancelDatetime() {
        return cancelDatetime;
    }

    public void setCancelDatetime(java.sql.Timestamp cancelDatetime) {
        this.cancelDatetime = cancelDatetime;
    }

    public Long getCancelUsernum() {
        return cancelUsernum;
    }

    public void setCancelUsernum(Long cancelUsernum) {
        this.cancelUsernum = cancelUsernum;
    }

    public Byte getCcBusy() {
        return ccBusy;
    }

    public void setCcBusy(Byte ccBusy) {
        this.ccBusy = ccBusy;
    }

    public String getConversionLink() {
        return conversionLink;
    }

    public void setConversionLink(String conversionLink) {
        this.conversionLink = conversionLink;
    }

    public Long getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Long currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getCycleFillRunNumber() {
        return cycleFillRunNumber;
    }

    public void setCycleFillRunNumber(String cycleFillRunNumber) {
        this.cycleFillRunNumber = cycleFillRunNumber;
    }

    public java.sql.Timestamp getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(java.sql.Timestamp dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    public java.sql.Timestamp getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(java.sql.Timestamp dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getDisRecovery() {
        return disRecovery;
    }

    public void setDisRecovery(String disRecovery) {
        this.disRecovery = disRecovery;
    }

    public String getExternalReview() {
        return externalReview;
    }

    public void setExternalReview(String externalReview) {
        this.externalReview = externalReview;
    }

    public java.sql.Timestamp getExternalReviewDatetime() {
        return externalReviewDatetime;
    }

    public void setExternalReviewDatetime(java.sql.Timestamp externalReviewDatetime) {
        this.externalReviewDatetime = externalReviewDatetime;
    }

    public String getExtSrcinfo() {
        return extSrcinfo;
    }

    public void setExtSrcinfo(String extSrcinfo) {
        this.extSrcinfo = extSrcinfo;
    }

    public String getFinanceValidated() {
        return financeValidated;
    }

    public void setFinanceValidated(String financeValidated) {
        this.financeValidated = financeValidated;
    }

    public String getHippaValidated() {
        return hippaValidated;
    }

    public void setHippaValidated(String hippaValidated) {
        this.hippaValidated = hippaValidated;
    }

    public java.sql.Timestamp getInductDatetime() {
        return inductDatetime;
    }

    public void setInductDatetime(java.sql.Timestamp inductDatetime) {
        this.inductDatetime = inductDatetime;
    }

    public java.sql.Timestamp getIntakeComplete() {
        return intakeComplete;
    }

    public void setIntakeComplete(java.sql.Timestamp intakeComplete) {
        this.intakeComplete = intakeComplete;
    }

    public String getIntakeFillLocation() {
        return intakeFillLocation;
    }

    public void setIntakeFillLocation(String intakeFillLocation) {
        this.intakeFillLocation = intakeFillLocation;
    }

    public String getIsMybOrder() {
        return isMybOrder;
    }

    public void setIsMybOrder(String isMybOrder) {
        this.isMybOrder = isMybOrder;
    }

    public Byte getLocked() {
        return locked;
    }

    public void setLocked(Byte locked) {
        this.locked = locked;
    }

    public java.math.BigInteger getMergeParentOrderNum() {
        return mergeParentOrderNum;
    }

    public void setMergeParentOrderNum(java.math.BigInteger mergeParentOrderNum) {
        this.mergeParentOrderNum = mergeParentOrderNum;
    }

    public java.sql.Timestamp getNeedByDate() {
        return needByDate;
    }

    public void setNeedByDate(java.sql.Timestamp needByDate) {
        this.needByDate = needByDate;
    }

    public java.sql.Timestamp getNotified() {
        return notified;
    }

    public void setNotified(java.sql.Timestamp notified) {
        this.notified = notified;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Byte getOrderAutoFilled() {
        return orderAutoFilled;
    }

    public void setOrderAutoFilled(Byte orderAutoFilled) {
        this.orderAutoFilled = orderAutoFilled;
    }

    public java.sql.Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(java.sql.Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Byte getOrderDur() {
        return orderDur;
    }

    public void setOrderDur(Byte orderDur) {
        this.orderDur = orderDur;
    }

    public java.math.BigInteger getOrderGlPosted() {
        return orderGlPosted;
    }

    public void setOrderGlPosted(java.math.BigInteger orderGlPosted) {
        this.orderGlPosted = orderGlPosted;
    }

    public String getOrderHighPriority() {
        return orderHighPriority;
    }

    public void setOrderHighPriority(String orderHighPriority) {
        this.orderHighPriority = orderHighPriority;
    }

    public String getOrderInvoiceNumber() {
        return orderInvoiceNumber;
    }

    public void setOrderInvoiceNumber(String orderInvoiceNumber) {
        this.orderInvoiceNumber = orderInvoiceNumber;
    }

    public java.math.BigInteger getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(java.math.BigInteger orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderOftValidated() {
        return orderOftValidated;
    }

    public void setOrderOftValidated(String orderOftValidated) {
        this.orderOftValidated = orderOftValidated;
    }

    public String getOrderPatientValidated() {
        return orderPatientValidated;
    }

    public void setOrderPatientValidated(String orderPatientValidated) {
        this.orderPatientValidated = orderPatientValidated;
    }

    public String getOrderPicked() {
        return orderPicked;
    }

    public void setOrderPicked(String orderPicked) {
        this.orderPicked = orderPicked;
    }

    public String getOrderPrint() {
        return orderPrint;
    }

    public void setOrderPrint(String orderPrint) {
        this.orderPrint = orderPrint;
    }

    public java.sql.Timestamp getOrderSplitDate() {
        return orderSplitDate;
    }

    public void setOrderSplitDate(java.sql.Timestamp orderSplitDate) {
        this.orderSplitDate = orderSplitDate;
    }

    public Byte getOrderStatusNum() {
        return orderStatusNum;
    }

    public void setOrderStatusNum(Byte orderStatusNum) {
        this.orderStatusNum = orderStatusNum;
    }

    public Long getOrderTax() {
        return orderTax;
    }

    public void setOrderTax(Long orderTax) {
        this.orderTax = orderTax;
    }

    public Long getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(Long orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public Long getOrderTotalPayments() {
        return orderTotalPayments;
    }

    public void setOrderTotalPayments(Long orderTotalPayments) {
        this.orderTotalPayments = orderTotalPayments;
    }

    public String getOrderToteNumber() {
        return orderToteNumber;
    }

    public void setOrderToteNumber(String orderToteNumber) {
        this.orderToteNumber = orderToteNumber;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderValidated() {
        return orderValidated;
    }

    public void setOrderValidated(String orderValidated) {
        this.orderValidated = orderValidated;
    }

    public Integer getOriginationNum() {
        return originationNum;
    }

    public void setOriginationNum(Integer originationNum) {
        this.originationNum = originationNum;
    }

    public Byte getPendingShipment() {
        return pendingShipment;
    }

    public void setPendingShipment(Byte pendingShipment) {
        this.pendingShipment = pendingShipment;
    }

    public Integer getPickupDeliveryMethodsSeq() {
        return pickupDeliveryMethodsSeq;
    }

    public void setPickupDeliveryMethodsSeq(Integer pickupDeliveryMethodsSeq) {
        this.pickupDeliveryMethodsSeq = pickupDeliveryMethodsSeq;
    }

    public String getPostEdit() {
        return postEdit;
    }

    public void setPostEdit(String postEdit) {
        this.postEdit = postEdit;
    }

    public String getPrivateLabel() {
        return privateLabel;
    }

    public void setPrivateLabel(String privateLabel) {
        this.privateLabel = privateLabel;
    }

    public String getPwoChecked() {
        return pwoChecked;
    }

    public void setPwoChecked(String pwoChecked) {
        this.pwoChecked = pwoChecked;
    }

    public java.sql.Timestamp getQuoteDatetime() {
        return quoteDatetime;
    }

    public void setQuoteDatetime(java.sql.Timestamp quoteDatetime) {
        this.quoteDatetime = quoteDatetime;
    }

    public java.sql.Timestamp getReplaceDatetime() {
        return replaceDatetime;
    }

    public void setReplaceDatetime(java.sql.Timestamp replaceDatetime) {
        this.replaceDatetime = replaceDatetime;
    }

    public java.math.BigInteger getReplaceParentOrderNum() {
        return replaceParentOrderNum;
    }

    public void setReplaceParentOrderNum(java.math.BigInteger replaceParentOrderNum) {
        this.replaceParentOrderNum = replaceParentOrderNum;
    }

    public java.sql.Timestamp getReplaceReviewDatetime() {
        return replaceReviewDatetime;
    }

    public void setReplaceReviewDatetime(java.sql.Timestamp replaceReviewDatetime) {
        this.replaceReviewDatetime = replaceReviewDatetime;
    }

    public Long getReplaceReviewUsernum() {
        return replaceReviewUsernum;
    }

    public void setReplaceReviewUsernum(Long replaceReviewUsernum) {
        this.replaceReviewUsernum = replaceReviewUsernum;
    }

    public java.sql.Timestamp getReturnDatetime() {
        return returnDatetime;
    }

    public void setReturnDatetime(java.sql.Timestamp returnDatetime) {
        this.returnDatetime = returnDatetime;
    }

    public java.sql.Timestamp getReturnReviewDatetime() {
        return returnReviewDatetime;
    }

    public void setReturnReviewDatetime(java.sql.Timestamp returnReviewDatetime) {
        this.returnReviewDatetime = returnReviewDatetime;
    }

    public Long getReturnReviewUsernum() {
        return returnReviewUsernum;
    }

    public void setReturnReviewUsernum(Long returnReviewUsernum) {
        this.returnReviewUsernum = returnReviewUsernum;
    }

    public Byte getReturnShippingCost() {
        return returnShippingCost;
    }

    public void setReturnShippingCost(Byte returnShippingCost) {
        this.returnShippingCost = returnShippingCost;
    }

    public byte[] getRowversion() {
        return rowversion;
    }

    public void setRowversion(byte[] rowversion) {
        this.rowversion = rowversion;
    }

    public String getSalestaxCalculated() {
        return salestaxCalculated;
    }

    public void setSalestaxCalculated(String salestaxCalculated) {
        this.salestaxCalculated = salestaxCalculated;
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

    public Boolean getSpecialtyValidated() {
        return specialtyValidated;
    }

    public void setSpecialtyValidated(Boolean specialtyValidated) {
        this.specialtyValidated = specialtyValidated;
    }

    public String getSuborderValidated() {
        return suborderValidated;
    }

    public void setSuborderValidated(String suborderValidated) {
        this.suborderValidated = suborderValidated;
    }

    public Long getSysUserNum() {
        return sysUserNum;
    }

    public void setSysUserNum(Long sysUserNum) {
        this.sysUserNum = sysUserNum;
    }

    public Long getTradingPartnerNum() {
        return tradingPartnerNum;
    }

    public void setTradingPartnerNum(Long tradingPartnerNum) {
        this.tradingPartnerNum = tradingPartnerNum;
    }

    public java.sql.Timestamp getWorkflowComplete() {
        return workflowComplete;
    }

    public void setWorkflowComplete(java.sql.Timestamp workflowComplete) {
        this.workflowComplete = workflowComplete;
    }

    public Integer getWorkflowNum() {
        return workflowNum;
    }

    public void setWorkflowNum(Integer workflowNum) {
        this.workflowNum = workflowNum;
    }

}

