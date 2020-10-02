/*
 * To change this license header, choose License Header in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billretriever.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author SBAmihan
 */
@Entity
@Table(name = "BP_HEADERS")
public class Header {

	@Id
	@Column(name = "TRAN_NO")
	private Long tranNo;
	@Column(name = "BATCH_CD")
	private String batchCd;
	@Column(name = "BATCH_NO")
	private long batchNo;
	@Column(name = "DU_SET_ID")
	private long duSetId;
	@Column(name = "BILLING_BATCH_NO")
	private String billingBatchNo;
	@Column(name = "BILL_COLOR")
	private String billColor;
	@Column(name = "COURIER_CODE")
	private String courierCode;
	@Column(name = "BILL_TYPE")
	private String billType;
	@Column(name = "BILL_MONTH")
	@Temporal(TemporalType.TIMESTAMP)
	private Date billMonth;
	@Column(name = "BILL_DATE")
	@Temporal(TemporalType.DATE)
	private Date billDate;
	@Column(name = "DUE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dueDate;
	@Column(name = "BILL_NO")
	private String billNo;
	@Column(name = "AREA_CODE")
	private String areaCode;
	@Column(name = "READING_BATCH_NO")
	private Short readingBatchNo;
	@Column(name = "BOOK_NO")
	private Integer bookNo;
	@Column(name = "OLD_SEQ_NO")
	private Integer oldSeqNo;
	@Column(name = "NEW_SEQ_NO")
	private Long newSeqNo;
	@Column(name = "CRC")
	private String crc;
	@Column(name = "ACCT_NO")
	private String acctNo;
	@Column(name = "RATE_SCHEDULE")
	private String rateSchedule;
	@Column(name = "RATE_SCHEDULE_DESC")
	private String rateScheduleDesc;
	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	@Column(name = "PREMISE_ADD1")
	private String premiseAdd1;
	@Column(name = "PREMISE_ADD2")
	private String premiseAdd2;
	@Column(name = "PREMISE_ADD3")
	private String premiseAdd3;
	@Column(name = "BILLING_ADD1")
	private String billingAdd1;
	@Column(name = "BILLING_ADD2")
	private String billingAdd2;
	@Column(name = "BILLING_ADD3")
	private String billingAdd3;
	@Column(name = "MESSAGE_CODE")
	private String messageCode;
	@Column(name = "POWER_FACTOR_VALUE")
	private BigDecimal powerFactorValue;
	@Column(name = "BILLED_KWHR_CONS")
	private BigDecimal billedKwhrCons;
	@Column(name = "BILLED_DEMAND_CONS")
	private BigDecimal billedDemandCons;
	@Column(name = "BILLED_KVAR_CONS")
	private BigDecimal billedKvarCons;
	@Column(name = "OVERDUE_AMT")
	private BigDecimal overdueAmt;
	@Column(name = "OVERDUE_BILL_COUNT")
	private Short overdueBillCount;
	@Column(name = "BILL_AMT")
	private BigDecimal billAmt;
	@Column(name = "TOTAL_AMT_DUE")
	private BigDecimal totalAmtDue;
	@Column(name = "LAST_PAYMENT_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastPaymentDate;
	@Column(name = "LAST_PAYMENT_AMOUNT")
	private BigDecimal lastPaymentAmount;
	@Column(name = "MAIN_SA_ID")
	private String mainSaId;
	@Column(name = "MESSENGER_CODE")
	private String messengerCode;
	@Column(name = "ALT_BILL_ID")
	private String altBillId;
	@Column(name = "LOCATION_CODE")
	private String locationCode;
	@Column(name = "LAST_BILL_FLG")
	private Character lastBillFlg;
	@Column(name = "PAR_MONTH")
	@Temporal(TemporalType.TIMESTAMP)
	private Date parMonth;
	@Column(name = "PAR_KWHR")
	private BigDecimal parKwhr;
	@Column(name = "COMPLETE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date completeDate;
	@Column(name = "FLT_CONNECTION")
	private BigInteger fltConnection;
	@Column(name = "FLT_WATTAGE")
	private BigInteger fltWattage;
	@Column(name = "NO_BATCH_PRT_SW")
	private Character noBatchPrtSw;
	@Column(name = "EBILL_ONLY_SW")
	private Character ebillOnlySw;
	@Column(name = "EXTRACTED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date extractedOn;
	@Column(name = "TIN")
	private String tin;
	@Column(name = "BUS_ACTIVITY")
	private String busActivity;
	@Column(name = "BUS_ADD1")
	private String busAdd1;
	@Column(name = "BUS_ADD2")
	private String busAdd2;
	@Column(name = "BUS_ADD3")
	private String busAdd3;
	@Column(name = "BUS_ADD4")
	private String busAdd4;
	@Column(name = "BUS_ADD5")
	private String busAdd5;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "header")
	private Collection<MeterDetails> meterDetailsCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "header")
	private Collection<ConsumptionHistory> consumptionHistoryCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "header")
	private Collection<LineDetails> lineDetailsCollection;

	public Header() {
	}

	public Header(Long tranNo) {
		this.tranNo = tranNo;
	}

	public Header(Long tranNo, String batchCd, long batchNo, long duSetId, String billColor, String courierCode,
			Date billMonth, Date billDate, Date dueDate, String billNo, String crc, String acctNo, String customerName,
			BigDecimal billAmt, BigDecimal totalAmtDue) {
		this.tranNo = tranNo;
		this.batchCd = batchCd;
		this.batchNo = batchNo;
		this.duSetId = duSetId;
		this.billColor = billColor;
		this.courierCode = courierCode;
		this.billMonth = billMonth;
		this.billDate = billDate;
		this.dueDate = dueDate;
		this.billNo = billNo;
		this.crc = crc;
		this.acctNo = acctNo;
		this.customerName = customerName;
		this.billAmt = billAmt;
		this.totalAmtDue = totalAmtDue;
	}

	public Long getTranNo() {
		return tranNo;
	}

	public void setTranNo(Long tranNo) {
		this.tranNo = tranNo;
	}

	public String getBatchCd() {
		return batchCd;
	}

	public void setBatchCd(String batchCd) {
		this.batchCd = batchCd;
	}

	public long getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(long batchNo) {
		this.batchNo = batchNo;
	}

	public long getDuSetId() {
		return duSetId;
	}

	public void setDuSetId(long duSetId) {
		this.duSetId = duSetId;
	}

	public String getBillingBatchNo() {
		return billingBatchNo;
	}

	public void setBillingBatchNo(String billingBatchNo) {
		this.billingBatchNo = billingBatchNo;
	}

	public String getBillColor() {
		return billColor;
	}

	public void setBillColor(String billColor) {
		this.billColor = billColor;
	}

	public String getCourierCode() {
		return courierCode;
	}

	public void setCourierCode(String courierCode) {
		this.courierCode = courierCode;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public Date getBillMonth() {
		return billMonth;
	}

	public void setBillMonth(Date billMonth) {
		this.billMonth = billMonth;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Short getReadingBatchNo() {
		return readingBatchNo;
	}

	public void setReadingBatchNo(Short readingBatchNo) {
		this.readingBatchNo = readingBatchNo;
	}

	public Integer getBookNo() {
		return bookNo;
	}

	public void setBookNo(Integer bookNo) {
		this.bookNo = bookNo;
	}

	public Integer getOldSeqNo() {
		return oldSeqNo;
	}

	public void setOldSeqNo(Integer oldSeqNo) {
		this.oldSeqNo = oldSeqNo;
	}

	public Long getNewSeqNo() {
		return newSeqNo;
	}

	public void setNewSeqNo(Long newSeqNo) {
		this.newSeqNo = newSeqNo;
	}

	public String getCrc() {
		return crc;
	}

	public void setCrc(String crc) {
		this.crc = crc;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getRateSchedule() {
		return rateSchedule;
	}

	public void setRateSchedule(String rateSchedule) {
		this.rateSchedule = rateSchedule;
	}

	public String getRateScheduleDesc() {
		return rateScheduleDesc;
	}

	public void setRateScheduleDesc(String rateScheduleDesc) {
		this.rateScheduleDesc = rateScheduleDesc;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPremiseAdd1() {
		return premiseAdd1;
	}

	public void setPremiseAdd1(String premiseAdd1) {
		this.premiseAdd1 = premiseAdd1;
	}

	public String getPremiseAdd2() {
		return premiseAdd2;
	}

	public void setPremiseAdd2(String premiseAdd2) {
		this.premiseAdd2 = premiseAdd2;
	}

	public String getPremiseAdd3() {
		return premiseAdd3;
	}

	public void setPremiseAdd3(String premiseAdd3) {
		this.premiseAdd3 = premiseAdd3;
	}

	public String getBillingAdd1() {
		return billingAdd1;
	}

	public void setBillingAdd1(String billingAdd1) {
		this.billingAdd1 = billingAdd1;
	}

	public String getBillingAdd2() {
		return billingAdd2;
	}

	public void setBillingAdd2(String billingAdd2) {
		this.billingAdd2 = billingAdd2;
	}

	public String getBillingAdd3() {
		return billingAdd3;
	}

	public void setBillingAdd3(String billingAdd3) {
		this.billingAdd3 = billingAdd3;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public BigDecimal getPowerFactorValue() {
		return powerFactorValue;
	}

	public void setPowerFactorValue(BigDecimal powerFactorValue) {
		this.powerFactorValue = powerFactorValue;
	}

	public BigDecimal getBilledKwhrCons() {
		return billedKwhrCons;
	}

	public void setBilledKwhrCons(BigDecimal billedKwhrCons) {
		this.billedKwhrCons = billedKwhrCons;
	}

	public BigDecimal getBilledDemandCons() {
		return billedDemandCons;
	}

	public void setBilledDemandCons(BigDecimal billedDemandCons) {
		this.billedDemandCons = billedDemandCons;
	}

	public BigDecimal getBilledKvarCons() {
		return billedKvarCons;
	}

	public void setBilledKvarCons(BigDecimal billedKvarCons) {
		this.billedKvarCons = billedKvarCons;
	}

	public BigDecimal getOverdueAmt() {
		return overdueAmt;
	}

	public void setOverdueAmt(BigDecimal overdueAmt) {
		this.overdueAmt = overdueAmt;
	}

	public Short getOverdueBillCount() {
		return overdueBillCount;
	}

	public void setOverdueBillCount(Short overdueBillCount) {
		this.overdueBillCount = overdueBillCount;
	}

	public BigDecimal getBillAmt() {
		return billAmt;
	}

	public void setBillAmt(BigDecimal billAmt) {
		this.billAmt = billAmt;
	}

	public BigDecimal getTotalAmtDue() {
		return totalAmtDue;
	}

	public void setTotalAmtDue(BigDecimal totalAmtDue) {
		this.totalAmtDue = totalAmtDue;
	}

	public Date getLastPaymentDate() {
		return lastPaymentDate;
	}

	public void setLastPaymentDate(Date lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}

	public BigDecimal getLastPaymentAmount() {
		return lastPaymentAmount;
	}

	public void setLastPaymentAmount(BigDecimal lastPaymentAmount) {
		this.lastPaymentAmount = lastPaymentAmount;
	}

	public String getMainSaId() {
		return mainSaId;
	}

	public void setMainSaId(String mainSaId) {
		this.mainSaId = mainSaId;
	}

	public String getMessengerCode() {
		return messengerCode;
	}

	public void setMessengerCode(String messengerCode) {
		this.messengerCode = messengerCode;
	}

	public String getAltBillId() {
		return altBillId;
	}

	public void setAltBillId(String altBillId) {
		this.altBillId = altBillId;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public Character getLastBillFlg() {
		return lastBillFlg;
	}

	public void setLastBillFlg(Character lastBillFlg) {
		this.lastBillFlg = lastBillFlg;
	}

	public Date getParMonth() {
		return parMonth;
	}

	public void setParMonth(Date parMonth) {
		this.parMonth = parMonth;
	}

	public BigDecimal getParKwhr() {
		return parKwhr;
	}

	public void setParKwhr(BigDecimal parKwhr) {
		this.parKwhr = parKwhr;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public BigInteger getFltConnection() {
		return fltConnection;
	}

	public void setFltConnection(BigInteger fltConnection) {
		this.fltConnection = fltConnection;
	}

	public BigInteger getFltWattage() {
		return fltWattage;
	}

	public void setFltWattage(BigInteger fltWattage) {
		this.fltWattage = fltWattage;
	}

	public Character getNoBatchPrtSw() {
		return noBatchPrtSw;
	}

	public void setNoBatchPrtSw(Character noBatchPrtSw) {
		this.noBatchPrtSw = noBatchPrtSw;
	}

	public Character getEbillOnlySw() {
		return ebillOnlySw;
	}

	public void setEbillOnlySw(Character ebillOnlySw) {
		this.ebillOnlySw = ebillOnlySw;
	}

	public Date getExtractedOn() {
		return extractedOn;
	}

	public void setExtractedOn(Date extractedOn) {
		this.extractedOn = extractedOn;
	}

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public String getBusActivity() {
		return busActivity;
	}

	public void setBusActivity(String busActivity) {
		this.busActivity = busActivity;
	}

	public String getBusAdd1() {
		return busAdd1;
	}

	public void setBusAdd1(String busAdd1) {
		this.busAdd1 = busAdd1;
	}

	public String getBusAdd2() {
		return busAdd2;
	}

	public void setBusAdd2(String busAdd2) {
		this.busAdd2 = busAdd2;
	}

	public String getBusAdd3() {
		return busAdd3;
	}

	public void setBusAdd3(String busAdd3) {
		this.busAdd3 = busAdd3;
	}

	public String getBusAdd4() {
		return busAdd4;
	}

	public void setBusAdd4(String busAdd4) {
		this.busAdd4 = busAdd4;
	}

	public String getBusAdd5() {
		return busAdd5;
	}

	public void setBusAdd5(String busAdd5) {
		this.busAdd5 = busAdd5;
	}

	public Collection<MeterDetails> getMeterDetailsCollection() {
		return meterDetailsCollection;
	}

	public void setMeterDetailsCollection(Collection<MeterDetails> meterDetailsCollection) {
		this.meterDetailsCollection = meterDetailsCollection;
	}

	public Collection<ConsumptionHistory> getConsumptionHistoryCollection() {
		return consumptionHistoryCollection;
	}

	public void setConsumptionHistoryCollection(Collection<ConsumptionHistory> consumptionHistoryCollection) {
		this.consumptionHistoryCollection = consumptionHistoryCollection;
	}

	public Collection<LineDetails> getLineDetailsCollection() {
		return lineDetailsCollection;
	}

	public void setLineDetailsCollection(Collection<LineDetails> lineDetailsCollection) {
		this.lineDetailsCollection = lineDetailsCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tranNo != null ? tranNo.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Header)) {
			return false;
		}
		Header other = (Header) object;
		if ((this.tranNo == null && other.tranNo != null)
				|| (this.tranNo != null && !this.tranNo.equals(other.tranNo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.example.test.model.Headers[ tranNo=" + tranNo + " ]";
	}

}
