/*
 * To change this license header, choose License Bill in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billstager.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * @author SBAmihan
 */
public class Bill {

	private Long tranNo;
	private String batchCd;
	private long batchNo;
	private long duSetId;
	private String billingBatchNo;
	private String billColor;
	private String courierCode;
	private String billType;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private Date billMonth;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	private Date billDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private Date dueDate;
	private String billNo;
	private String areaCode;
	private Short readingBatchNo;
	private Integer bookNo;
	private Integer oldSeqNo;
	private Long newSeqNo;
	private String crc;
	private String acctNo;
	private String rateSchedule;
	private String rateScheduleDesc;
	private String customerName;
	private String premiseAdd1;
	private String premiseAdd2;
	private String premiseAdd3;
	private String billingAdd1;
	private String billingAdd2;
	private String billingAdd3;
	private String messageCode;
	private BigDecimal powerFactorValue;
	private BigDecimal billedKwhrCons;
	private BigDecimal billedDemandCons;
	private BigDecimal billedKvarCons;
	private BigDecimal overdueAmt;
	private Short overdueBillCount;
	private BigDecimal billAmt;
	private BigDecimal totalAmtDue;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private Date lastPaymentDate;
	private BigDecimal lastPaymentAmount;
	private String mainSaId;
	private String messengerCode;
	private String altBillId;
	private String locationCode;
	private Character lastBillFlg;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private Date parMonth;
	private BigDecimal parKwhr;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private Date completeDate;
	private BigInteger fltConnection;
	private BigInteger fltWattage;
	private Character noBatchPrtSw;
	private Character ebillOnlySw;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private Date extractedOn;
	private String tin;
	private String busActivity;
	private String busAdd1;
	private String busAdd2;
	private String busAdd3;
	private String busAdd4;
	private String busAdd5;
	private Collection<MeterDetails> meterDetailsCollection;
	private Collection<ConsumptionHistory> consumptionHistoryCollection;
	private Collection<LineDetails> lineDetailsCollection;

	public Bill() {
	}

	public Bill(Long tranNo) {
		this.tranNo = tranNo;
	}

	public Bill(Long tranNo, String batchCd, long batchNo, long duSetId, String billColor, String courierCode,
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acctNo == null) ? 0 : acctNo.hashCode());
		result = prime * result + ((altBillId == null) ? 0 : altBillId.hashCode());
		result = prime * result + ((areaCode == null) ? 0 : areaCode.hashCode());
		result = prime * result + ((batchCd == null) ? 0 : batchCd.hashCode());
		result = prime * result + (int) (batchNo ^ (batchNo >>> 32));
		result = prime * result + ((billAmt == null) ? 0 : billAmt.hashCode());
		result = prime * result + ((billColor == null) ? 0 : billColor.hashCode());
		result = prime * result + ((billDate == null) ? 0 : billDate.hashCode());
		result = prime * result + ((billMonth == null) ? 0 : billMonth.hashCode());
		result = prime * result + ((billNo == null) ? 0 : billNo.hashCode());
		result = prime * result + ((billType == null) ? 0 : billType.hashCode());
		result = prime * result + ((billedDemandCons == null) ? 0 : billedDemandCons.hashCode());
		result = prime * result + ((billedKvarCons == null) ? 0 : billedKvarCons.hashCode());
		result = prime * result + ((billedKwhrCons == null) ? 0 : billedKwhrCons.hashCode());
		result = prime * result + ((billingAdd1 == null) ? 0 : billingAdd1.hashCode());
		result = prime * result + ((billingAdd2 == null) ? 0 : billingAdd2.hashCode());
		result = prime * result + ((billingAdd3 == null) ? 0 : billingAdd3.hashCode());
		result = prime * result + ((billingBatchNo == null) ? 0 : billingBatchNo.hashCode());
		result = prime * result + ((bookNo == null) ? 0 : bookNo.hashCode());
		result = prime * result + ((busActivity == null) ? 0 : busActivity.hashCode());
		result = prime * result + ((busAdd1 == null) ? 0 : busAdd1.hashCode());
		result = prime * result + ((busAdd2 == null) ? 0 : busAdd2.hashCode());
		result = prime * result + ((busAdd3 == null) ? 0 : busAdd3.hashCode());
		result = prime * result + ((busAdd4 == null) ? 0 : busAdd4.hashCode());
		result = prime * result + ((busAdd5 == null) ? 0 : busAdd5.hashCode());
		result = prime * result + ((completeDate == null) ? 0 : completeDate.hashCode());
		result = prime * result
				+ ((consumptionHistoryCollection == null) ? 0 : consumptionHistoryCollection.hashCode());
		result = prime * result + ((courierCode == null) ? 0 : courierCode.hashCode());
		result = prime * result + ((crc == null) ? 0 : crc.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + (int) (duSetId ^ (duSetId >>> 32));
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + ((ebillOnlySw == null) ? 0 : ebillOnlySw.hashCode());
		result = prime * result + ((extractedOn == null) ? 0 : extractedOn.hashCode());
		result = prime * result + ((fltConnection == null) ? 0 : fltConnection.hashCode());
		result = prime * result + ((fltWattage == null) ? 0 : fltWattage.hashCode());
		result = prime * result + ((lastBillFlg == null) ? 0 : lastBillFlg.hashCode());
		result = prime * result + ((lastPaymentAmount == null) ? 0 : lastPaymentAmount.hashCode());
		result = prime * result + ((lastPaymentDate == null) ? 0 : lastPaymentDate.hashCode());
		result = prime * result + ((lineDetailsCollection == null) ? 0 : lineDetailsCollection.hashCode());
		result = prime * result + ((locationCode == null) ? 0 : locationCode.hashCode());
		result = prime * result + ((mainSaId == null) ? 0 : mainSaId.hashCode());
		result = prime * result + ((messageCode == null) ? 0 : messageCode.hashCode());
		result = prime * result + ((messengerCode == null) ? 0 : messengerCode.hashCode());
		result = prime * result + ((meterDetailsCollection == null) ? 0 : meterDetailsCollection.hashCode());
		result = prime * result + ((newSeqNo == null) ? 0 : newSeqNo.hashCode());
		result = prime * result + ((noBatchPrtSw == null) ? 0 : noBatchPrtSw.hashCode());
		result = prime * result + ((oldSeqNo == null) ? 0 : oldSeqNo.hashCode());
		result = prime * result + ((overdueAmt == null) ? 0 : overdueAmt.hashCode());
		result = prime * result + ((overdueBillCount == null) ? 0 : overdueBillCount.hashCode());
		result = prime * result + ((parKwhr == null) ? 0 : parKwhr.hashCode());
		result = prime * result + ((parMonth == null) ? 0 : parMonth.hashCode());
		result = prime * result + ((powerFactorValue == null) ? 0 : powerFactorValue.hashCode());
		result = prime * result + ((premiseAdd1 == null) ? 0 : premiseAdd1.hashCode());
		result = prime * result + ((premiseAdd2 == null) ? 0 : premiseAdd2.hashCode());
		result = prime * result + ((premiseAdd3 == null) ? 0 : premiseAdd3.hashCode());
		result = prime * result + ((rateSchedule == null) ? 0 : rateSchedule.hashCode());
		result = prime * result + ((rateScheduleDesc == null) ? 0 : rateScheduleDesc.hashCode());
		result = prime * result + ((readingBatchNo == null) ? 0 : readingBatchNo.hashCode());
		result = prime * result + ((tin == null) ? 0 : tin.hashCode());
		result = prime * result + ((totalAmtDue == null) ? 0 : totalAmtDue.hashCode());
		result = prime * result + ((tranNo == null) ? 0 : tranNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bill other = (Bill) obj;
		if (acctNo == null) {
			if (other.acctNo != null)
				return false;
		} else if (!acctNo.equals(other.acctNo))
			return false;
		if (altBillId == null) {
			if (other.altBillId != null)
				return false;
		} else if (!altBillId.equals(other.altBillId))
			return false;
		if (areaCode == null) {
			if (other.areaCode != null)
				return false;
		} else if (!areaCode.equals(other.areaCode))
			return false;
		if (batchCd == null) {
			if (other.batchCd != null)
				return false;
		} else if (!batchCd.equals(other.batchCd))
			return false;
		if (batchNo != other.batchNo)
			return false;
		if (billAmt == null) {
			if (other.billAmt != null)
				return false;
		} else if (!billAmt.equals(other.billAmt))
			return false;
		if (billColor == null) {
			if (other.billColor != null)
				return false;
		} else if (!billColor.equals(other.billColor))
			return false;
		if (billDate == null) {
			if (other.billDate != null)
				return false;
		} else if (!billDate.equals(other.billDate))
			return false;
		if (billMonth == null) {
			if (other.billMonth != null)
				return false;
		} else if (!billMonth.equals(other.billMonth))
			return false;
		if (billNo == null) {
			if (other.billNo != null)
				return false;
		} else if (!billNo.equals(other.billNo))
			return false;
		if (billType == null) {
			if (other.billType != null)
				return false;
		} else if (!billType.equals(other.billType))
			return false;
		if (billedDemandCons == null) {
			if (other.billedDemandCons != null)
				return false;
		} else if (!billedDemandCons.equals(other.billedDemandCons))
			return false;
		if (billedKvarCons == null) {
			if (other.billedKvarCons != null)
				return false;
		} else if (!billedKvarCons.equals(other.billedKvarCons))
			return false;
		if (billedKwhrCons == null) {
			if (other.billedKwhrCons != null)
				return false;
		} else if (!billedKwhrCons.equals(other.billedKwhrCons))
			return false;
		if (billingAdd1 == null) {
			if (other.billingAdd1 != null)
				return false;
		} else if (!billingAdd1.equals(other.billingAdd1))
			return false;
		if (billingAdd2 == null) {
			if (other.billingAdd2 != null)
				return false;
		} else if (!billingAdd2.equals(other.billingAdd2))
			return false;
		if (billingAdd3 == null) {
			if (other.billingAdd3 != null)
				return false;
		} else if (!billingAdd3.equals(other.billingAdd3))
			return false;
		if (billingBatchNo == null) {
			if (other.billingBatchNo != null)
				return false;
		} else if (!billingBatchNo.equals(other.billingBatchNo))
			return false;
		if (bookNo == null) {
			if (other.bookNo != null)
				return false;
		} else if (!bookNo.equals(other.bookNo))
			return false;
		if (busActivity == null) {
			if (other.busActivity != null)
				return false;
		} else if (!busActivity.equals(other.busActivity))
			return false;
		if (busAdd1 == null) {
			if (other.busAdd1 != null)
				return false;
		} else if (!busAdd1.equals(other.busAdd1))
			return false;
		if (busAdd2 == null) {
			if (other.busAdd2 != null)
				return false;
		} else if (!busAdd2.equals(other.busAdd2))
			return false;
		if (busAdd3 == null) {
			if (other.busAdd3 != null)
				return false;
		} else if (!busAdd3.equals(other.busAdd3))
			return false;
		if (busAdd4 == null) {
			if (other.busAdd4 != null)
				return false;
		} else if (!busAdd4.equals(other.busAdd4))
			return false;
		if (busAdd5 == null) {
			if (other.busAdd5 != null)
				return false;
		} else if (!busAdd5.equals(other.busAdd5))
			return false;
		if (completeDate == null) {
			if (other.completeDate != null)
				return false;
		} else if (!completeDate.equals(other.completeDate))
			return false;
		if (consumptionHistoryCollection == null) {
			if (other.consumptionHistoryCollection != null)
				return false;
		} else if (!consumptionHistoryCollection.equals(other.consumptionHistoryCollection))
			return false;
		if (courierCode == null) {
			if (other.courierCode != null)
				return false;
		} else if (!courierCode.equals(other.courierCode))
			return false;
		if (crc == null) {
			if (other.crc != null)
				return false;
		} else if (!crc.equals(other.crc))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (duSetId != other.duSetId)
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		if (ebillOnlySw == null) {
			if (other.ebillOnlySw != null)
				return false;
		} else if (!ebillOnlySw.equals(other.ebillOnlySw))
			return false;
		if (extractedOn == null) {
			if (other.extractedOn != null)
				return false;
		} else if (!extractedOn.equals(other.extractedOn))
			return false;
		if (fltConnection == null) {
			if (other.fltConnection != null)
				return false;
		} else if (!fltConnection.equals(other.fltConnection))
			return false;
		if (fltWattage == null) {
			if (other.fltWattage != null)
				return false;
		} else if (!fltWattage.equals(other.fltWattage))
			return false;
		if (lastBillFlg == null) {
			if (other.lastBillFlg != null)
				return false;
		} else if (!lastBillFlg.equals(other.lastBillFlg))
			return false;
		if (lastPaymentAmount == null) {
			if (other.lastPaymentAmount != null)
				return false;
		} else if (!lastPaymentAmount.equals(other.lastPaymentAmount))
			return false;
		if (lastPaymentDate == null) {
			if (other.lastPaymentDate != null)
				return false;
		} else if (!lastPaymentDate.equals(other.lastPaymentDate))
			return false;
		if (lineDetailsCollection == null) {
			if (other.lineDetailsCollection != null)
				return false;
		} else if (!lineDetailsCollection.equals(other.lineDetailsCollection))
			return false;
		if (locationCode == null) {
			if (other.locationCode != null)
				return false;
		} else if (!locationCode.equals(other.locationCode))
			return false;
		if (mainSaId == null) {
			if (other.mainSaId != null)
				return false;
		} else if (!mainSaId.equals(other.mainSaId))
			return false;
		if (messageCode == null) {
			if (other.messageCode != null)
				return false;
		} else if (!messageCode.equals(other.messageCode))
			return false;
		if (messengerCode == null) {
			if (other.messengerCode != null)
				return false;
		} else if (!messengerCode.equals(other.messengerCode))
			return false;
		if (meterDetailsCollection == null) {
			if (other.meterDetailsCollection != null)
				return false;
		} else if (!meterDetailsCollection.equals(other.meterDetailsCollection))
			return false;
		if (newSeqNo == null) {
			if (other.newSeqNo != null)
				return false;
		} else if (!newSeqNo.equals(other.newSeqNo))
			return false;
		if (noBatchPrtSw == null) {
			if (other.noBatchPrtSw != null)
				return false;
		} else if (!noBatchPrtSw.equals(other.noBatchPrtSw))
			return false;
		if (oldSeqNo == null) {
			if (other.oldSeqNo != null)
				return false;
		} else if (!oldSeqNo.equals(other.oldSeqNo))
			return false;
		if (overdueAmt == null) {
			if (other.overdueAmt != null)
				return false;
		} else if (!overdueAmt.equals(other.overdueAmt))
			return false;
		if (overdueBillCount == null) {
			if (other.overdueBillCount != null)
				return false;
		} else if (!overdueBillCount.equals(other.overdueBillCount))
			return false;
		if (parKwhr == null) {
			if (other.parKwhr != null)
				return false;
		} else if (!parKwhr.equals(other.parKwhr))
			return false;
		if (parMonth == null) {
			if (other.parMonth != null)
				return false;
		} else if (!parMonth.equals(other.parMonth))
			return false;
		if (powerFactorValue == null) {
			if (other.powerFactorValue != null)
				return false;
		} else if (!powerFactorValue.equals(other.powerFactorValue))
			return false;
		if (premiseAdd1 == null) {
			if (other.premiseAdd1 != null)
				return false;
		} else if (!premiseAdd1.equals(other.premiseAdd1))
			return false;
		if (premiseAdd2 == null) {
			if (other.premiseAdd2 != null)
				return false;
		} else if (!premiseAdd2.equals(other.premiseAdd2))
			return false;
		if (premiseAdd3 == null) {
			if (other.premiseAdd3 != null)
				return false;
		} else if (!premiseAdd3.equals(other.premiseAdd3))
			return false;
		if (rateSchedule == null) {
			if (other.rateSchedule != null)
				return false;
		} else if (!rateSchedule.equals(other.rateSchedule))
			return false;
		if (rateScheduleDesc == null) {
			if (other.rateScheduleDesc != null)
				return false;
		} else if (!rateScheduleDesc.equals(other.rateScheduleDesc))
			return false;
		if (readingBatchNo == null) {
			if (other.readingBatchNo != null)
				return false;
		} else if (!readingBatchNo.equals(other.readingBatchNo))
			return false;
		if (tin == null) {
			if (other.tin != null)
				return false;
		} else if (!tin.equals(other.tin))
			return false;
		if (totalAmtDue == null) {
			if (other.totalAmtDue != null)
				return false;
		} else if (!totalAmtDue.equals(other.totalAmtDue))
			return false;
		if (tranNo == null) {
			if (other.tranNo != null)
				return false;
		} else if (!tranNo.equals(other.tranNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bill [tranNo=" + tranNo + ", batchCd=" + batchCd + ", batchNo=" + batchNo + ", duSetId=" + duSetId
				+ ", billingBatchNo=" + billingBatchNo + ", billColor=" + billColor + ", courierCode=" + courierCode
				+ ", billType=" + billType + ", billMonth=" + billMonth + ", billDate=" + billDate + ", dueDate="
				+ dueDate + ", billNo=" + billNo + ", areaCode=" + areaCode + ", readingBatchNo=" + readingBatchNo
				+ ", bookNo=" + bookNo + ", oldSeqNo=" + oldSeqNo + ", newSeqNo=" + newSeqNo + ", crc=" + crc
				+ ", acctNo=" + acctNo + ", rateSchedule=" + rateSchedule + ", rateScheduleDesc=" + rateScheduleDesc
				+ ", customerName=" + customerName + ", premiseAdd1=" + premiseAdd1 + ", premiseAdd2=" + premiseAdd2
				+ ", premiseAdd3=" + premiseAdd3 + ", billingAdd1=" + billingAdd1 + ", billingAdd2=" + billingAdd2
				+ ", billingAdd3=" + billingAdd3 + ", messageCode=" + messageCode + ", powerFactorValue="
				+ powerFactorValue + ", billedKwhrCons=" + billedKwhrCons + ", billedDemandCons=" + billedDemandCons
				+ ", billedKvarCons=" + billedKvarCons + ", overdueAmt=" + overdueAmt + ", overdueBillCount="
				+ overdueBillCount + ", billAmt=" + billAmt + ", totalAmtDue=" + totalAmtDue + ", lastPaymentDate="
				+ lastPaymentDate + ", lastPaymentAmount=" + lastPaymentAmount + ", mainSaId=" + mainSaId
				+ ", messengerCode=" + messengerCode + ", altBillId=" + altBillId + ", locationCode=" + locationCode
				+ ", lastBillFlg=" + lastBillFlg + ", parMonth=" + parMonth + ", parKwhr=" + parKwhr + ", completeDate="
				+ completeDate + ", fltConnection=" + fltConnection + ", fltWattage=" + fltWattage + ", noBatchPrtSw="
				+ noBatchPrtSw + ", ebillOnlySw=" + ebillOnlySw + ", extractedOn=" + extractedOn + ", tin=" + tin
				+ ", busActivity=" + busActivity + ", busAdd1=" + busAdd1 + ", busAdd2=" + busAdd2 + ", busAdd3="
				+ busAdd3 + ", busAdd4=" + busAdd4 + ", busAdd5=" + busAdd5 + ", meterDetailsCollection="
				+ meterDetailsCollection + ", consumptionHistoryCollection=" + consumptionHistoryCollection
				+ ", lineDetailsCollection=" + lineDetailsCollection + "]";
	}

}
