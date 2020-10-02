/*
 * To change this license bill, choose License Bill in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billstager.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * @author SBAmihan
 */
public class MeterDetails {

	protected MeterDetailsPK meterDetailsPK;
	private String serialNo;
	private String poleNo;
	private BigDecimal multiplier;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private Date prevReadingDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private Date currReadingDate;
	private BigDecimal connLoad;
	private BigDecimal prevKwhrRdg;
	private BigDecimal currKwhrRdg;
	private BigDecimal regKwhrCons;
	private BigDecimal prevDemandRdg;
	private BigDecimal currDemandRdg;
	private BigDecimal regDemandCons;
	private BigDecimal prevKvarRdg;
	private BigDecimal currKvarRdg;
	private BigDecimal regKvarCons;
	private String meterType;
	private String consumSubFlg;
	private String kwhrConsumSubFlg;
	private String demandConsumSubFlg;
	private String kvarConsumSubFlg;
	private Bill bill;

	public MeterDetails() {
	}

	public MeterDetails(MeterDetailsPK meterDetailsPK) {
		this.meterDetailsPK = meterDetailsPK;
	}

	public MeterDetailsPK getMeterDetailsPK() {
		return meterDetailsPK;
	}

	public void setMeterDetailsPK(MeterDetailsPK meterDetailsPK) {
		this.meterDetailsPK = meterDetailsPK;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getPoleNo() {
		return poleNo;
	}

	public void setPoleNo(String poleNo) {
		this.poleNo = poleNo;
	}

	public BigDecimal getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(BigDecimal multiplier) {
		this.multiplier = multiplier;
	}

	public Date getPrevReadingDate() {
		return prevReadingDate;
	}

	public void setPrevReadingDate(Date prevReadingDate) {
		this.prevReadingDate = prevReadingDate;
	}

	public Date getCurrReadingDate() {
		return currReadingDate;
	}

	public void setCurrReadingDate(Date currReadingDate) {
		this.currReadingDate = currReadingDate;
	}

	public BigDecimal getConnLoad() {
		return connLoad;
	}

	public void setConnLoad(BigDecimal connLoad) {
		this.connLoad = connLoad;
	}

	public BigDecimal getPrevKwhrRdg() {
		return prevKwhrRdg;
	}

	public void setPrevKwhrRdg(BigDecimal prevKwhrRdg) {
		this.prevKwhrRdg = prevKwhrRdg;
	}

	public BigDecimal getCurrKwhrRdg() {
		return currKwhrRdg;
	}

	public void setCurrKwhrRdg(BigDecimal currKwhrRdg) {
		this.currKwhrRdg = currKwhrRdg;
	}

	public BigDecimal getRegKwhrCons() {
		return regKwhrCons;
	}

	public void setRegKwhrCons(BigDecimal regKwhrCons) {
		this.regKwhrCons = regKwhrCons;
	}

	public BigDecimal getPrevDemandRdg() {
		return prevDemandRdg;
	}

	public void setPrevDemandRdg(BigDecimal prevDemandRdg) {
		this.prevDemandRdg = prevDemandRdg;
	}

	public BigDecimal getCurrDemandRdg() {
		return currDemandRdg;
	}

	public void setCurrDemandRdg(BigDecimal currDemandRdg) {
		this.currDemandRdg = currDemandRdg;
	}

	public BigDecimal getRegDemandCons() {
		return regDemandCons;
	}

	public void setRegDemandCons(BigDecimal regDemandCons) {
		this.regDemandCons = regDemandCons;
	}

	public BigDecimal getPrevKvarRdg() {
		return prevKvarRdg;
	}

	public void setPrevKvarRdg(BigDecimal prevKvarRdg) {
		this.prevKvarRdg = prevKvarRdg;
	}

	public BigDecimal getCurrKvarRdg() {
		return currKvarRdg;
	}

	public void setCurrKvarRdg(BigDecimal currKvarRdg) {
		this.currKvarRdg = currKvarRdg;
	}

	public BigDecimal getRegKvarCons() {
		return regKvarCons;
	}

	public void setRegKvarCons(BigDecimal regKvarCons) {
		this.regKvarCons = regKvarCons;
	}

	public String getMeterType() {
		return meterType;
	}

	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}

	public String getConsumSubFlg() {
		return consumSubFlg;
	}

	public void setConsumSubFlg(String consumSubFlg) {
		this.consumSubFlg = consumSubFlg;
	}

	public String getKwhrConsumSubFlg() {
		return kwhrConsumSubFlg;
	}

	public void setKwhrConsumSubFlg(String kwhrConsumSubFlg) {
		this.kwhrConsumSubFlg = kwhrConsumSubFlg;
	}

	public String getDemandConsumSubFlg() {
		return demandConsumSubFlg;
	}

	public void setDemandConsumSubFlg(String demandConsumSubFlg) {
		this.demandConsumSubFlg = demandConsumSubFlg;
	}

	public String getKvarConsumSubFlg() {
		return kvarConsumSubFlg;
	}

	public void setKvarConsumSubFlg(String kvarConsumSubFlg) {
		this.kvarConsumSubFlg = kvarConsumSubFlg;
	}

	public Bill getHeaders() {
		return bill;
	}

	public void setHeaders(Bill bill) {
		this.bill = bill;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((connLoad == null) ? 0 : connLoad.hashCode());
		result = prime * result + ((consumSubFlg == null) ? 0 : consumSubFlg.hashCode());
		result = prime * result + ((currDemandRdg == null) ? 0 : currDemandRdg.hashCode());
		result = prime * result + ((currKvarRdg == null) ? 0 : currKvarRdg.hashCode());
		result = prime * result + ((currKwhrRdg == null) ? 0 : currKwhrRdg.hashCode());
		result = prime * result + ((currReadingDate == null) ? 0 : currReadingDate.hashCode());
		result = prime * result + ((demandConsumSubFlg == null) ? 0 : demandConsumSubFlg.hashCode());
		result = prime * result + ((bill == null) ? 0 : bill.hashCode());
		result = prime * result + ((kvarConsumSubFlg == null) ? 0 : kvarConsumSubFlg.hashCode());
		result = prime * result + ((kwhrConsumSubFlg == null) ? 0 : kwhrConsumSubFlg.hashCode());
		result = prime * result + ((meterDetailsPK == null) ? 0 : meterDetailsPK.hashCode());
		result = prime * result + ((meterType == null) ? 0 : meterType.hashCode());
		result = prime * result + ((multiplier == null) ? 0 : multiplier.hashCode());
		result = prime * result + ((poleNo == null) ? 0 : poleNo.hashCode());
		result = prime * result + ((prevDemandRdg == null) ? 0 : prevDemandRdg.hashCode());
		result = prime * result + ((prevKvarRdg == null) ? 0 : prevKvarRdg.hashCode());
		result = prime * result + ((prevKwhrRdg == null) ? 0 : prevKwhrRdg.hashCode());
		result = prime * result + ((prevReadingDate == null) ? 0 : prevReadingDate.hashCode());
		result = prime * result + ((regDemandCons == null) ? 0 : regDemandCons.hashCode());
		result = prime * result + ((regKvarCons == null) ? 0 : regKvarCons.hashCode());
		result = prime * result + ((regKwhrCons == null) ? 0 : regKwhrCons.hashCode());
		result = prime * result + ((serialNo == null) ? 0 : serialNo.hashCode());
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
		MeterDetails other = (MeterDetails) obj;
		if (connLoad == null) {
			if (other.connLoad != null)
				return false;
		} else if (!connLoad.equals(other.connLoad))
			return false;
		if (consumSubFlg == null) {
			if (other.consumSubFlg != null)
				return false;
		} else if (!consumSubFlg.equals(other.consumSubFlg))
			return false;
		if (currDemandRdg == null) {
			if (other.currDemandRdg != null)
				return false;
		} else if (!currDemandRdg.equals(other.currDemandRdg))
			return false;
		if (currKvarRdg == null) {
			if (other.currKvarRdg != null)
				return false;
		} else if (!currKvarRdg.equals(other.currKvarRdg))
			return false;
		if (currKwhrRdg == null) {
			if (other.currKwhrRdg != null)
				return false;
		} else if (!currKwhrRdg.equals(other.currKwhrRdg))
			return false;
		if (currReadingDate == null) {
			if (other.currReadingDate != null)
				return false;
		} else if (!currReadingDate.equals(other.currReadingDate))
			return false;
		if (demandConsumSubFlg == null) {
			if (other.demandConsumSubFlg != null)
				return false;
		} else if (!demandConsumSubFlg.equals(other.demandConsumSubFlg))
			return false;
		if (bill == null) {
			if (other.bill != null)
				return false;
		} else if (!bill.equals(other.bill))
			return false;
		if (kvarConsumSubFlg == null) {
			if (other.kvarConsumSubFlg != null)
				return false;
		} else if (!kvarConsumSubFlg.equals(other.kvarConsumSubFlg))
			return false;
		if (kwhrConsumSubFlg == null) {
			if (other.kwhrConsumSubFlg != null)
				return false;
		} else if (!kwhrConsumSubFlg.equals(other.kwhrConsumSubFlg))
			return false;
		if (meterDetailsPK == null) {
			if (other.meterDetailsPK != null)
				return false;
		} else if (!meterDetailsPK.equals(other.meterDetailsPK))
			return false;
		if (meterType == null) {
			if (other.meterType != null)
				return false;
		} else if (!meterType.equals(other.meterType))
			return false;
		if (multiplier == null) {
			if (other.multiplier != null)
				return false;
		} else if (!multiplier.equals(other.multiplier))
			return false;
		if (poleNo == null) {
			if (other.poleNo != null)
				return false;
		} else if (!poleNo.equals(other.poleNo))
			return false;
		if (prevDemandRdg == null) {
			if (other.prevDemandRdg != null)
				return false;
		} else if (!prevDemandRdg.equals(other.prevDemandRdg))
			return false;
		if (prevKvarRdg == null) {
			if (other.prevKvarRdg != null)
				return false;
		} else if (!prevKvarRdg.equals(other.prevKvarRdg))
			return false;
		if (prevKwhrRdg == null) {
			if (other.prevKwhrRdg != null)
				return false;
		} else if (!prevKwhrRdg.equals(other.prevKwhrRdg))
			return false;
		if (prevReadingDate == null) {
			if (other.prevReadingDate != null)
				return false;
		} else if (!prevReadingDate.equals(other.prevReadingDate))
			return false;
		if (regDemandCons == null) {
			if (other.regDemandCons != null)
				return false;
		} else if (!regDemandCons.equals(other.regDemandCons))
			return false;
		if (regKvarCons == null) {
			if (other.regKvarCons != null)
				return false;
		} else if (!regKvarCons.equals(other.regKvarCons))
			return false;
		if (regKwhrCons == null) {
			if (other.regKwhrCons != null)
				return false;
		} else if (!regKwhrCons.equals(other.regKwhrCons))
			return false;
		if (serialNo == null) {
			if (other.serialNo != null)
				return false;
		} else if (!serialNo.equals(other.serialNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MeterDetails [meterDetailsPK=" + meterDetailsPK + ", serialNo=" + serialNo + ", poleNo=" + poleNo
				+ ", multiplier=" + multiplier + ", prevReadingDate=" + prevReadingDate + ", currReadingDate="
				+ currReadingDate + ", connLoad=" + connLoad + ", prevKwhrRdg=" + prevKwhrRdg + ", currKwhrRdg="
				+ currKwhrRdg + ", regKwhrCons=" + regKwhrCons + ", prevDemandRdg=" + prevDemandRdg + ", currDemandRdg="
				+ currDemandRdg + ", regDemandCons=" + regDemandCons + ", prevKvarRdg=" + prevKvarRdg + ", currKvarRdg="
				+ currKvarRdg + ", regKvarCons=" + regKvarCons + ", meterType=" + meterType + ", consumSubFlg="
				+ consumSubFlg + ", kwhrConsumSubFlg=" + kwhrConsumSubFlg + ", demandConsumSubFlg=" + demandConsumSubFlg
				+ ", kvarConsumSubFlg=" + kvarConsumSubFlg + ", bill=" + bill + "]";
	}

}
