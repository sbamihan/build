/*
 * To change this license header, choose License Header in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billservice.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author SBAmihan
 */
@Entity
@Table(name = "BP_METER_DETAILS")
public class MeterDetails {

	@EmbeddedId
	protected MeterDetailsPK meterDetailsPK;
	@Column(name = "SERIAL_NO")
	private String serialNo;
	@Column(name = "POLE_NO")
	private String poleNo;
	@Column(name = "MULTIPLIER")
	private BigDecimal multiplier;
	@Column(name = "PREV_READING_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date prevReadingDate;
	@Column(name = "CURR_READING_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date currReadingDate;
	@Column(name = "CONN_LOAD")
	private BigDecimal connLoad;
	@Column(name = "PREV_KWHR_RDG")
	private BigDecimal prevKwhrRdg;
	@Column(name = "CURR_KWHR_RDG")
	private BigDecimal currKwhrRdg;
	@Column(name = "REG_KWHR_CONS")
	private BigDecimal regKwhrCons;
	@Column(name = "PREV_DEMAND_RDG")
	private BigDecimal prevDemandRdg;
	@Column(name = "CURR_DEMAND_RDG")
	private BigDecimal currDemandRdg;
	@Column(name = "REG_DEMAND_CONS")
	private BigDecimal regDemandCons;
	@Column(name = "PREV_KVAR_RDG")
	private BigDecimal prevKvarRdg;
	@Column(name = "CURR_KVAR_RDG")
	private BigDecimal currKvarRdg;
	@Column(name = "REG_KVAR_CONS")
	private BigDecimal regKvarCons;
	@Column(name = "METER_TYPE")
	private String meterType;
	@Column(name = "CONSUM_SUB_FLG")
	private String consumSubFlg;
	@Column(name = "KWHR_CONSUM_SUB_FLG")
	private String kwhrConsumSubFlg;
	@Column(name = "DEMAND_CONSUM_SUB_FLG")
	private String demandConsumSubFlg;
	@Column(name = "KVAR_CONSUM_SUB_FLG")
	private String kvarConsumSubFlg;
	@JoinColumn(name = "TRAN_NO", referencedColumnName = "TRAN_NO", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Header header;

	public MeterDetails() {
	}

	public MeterDetails(MeterDetailsPK meterDetailsPK) {
		this.meterDetailsPK = meterDetailsPK;
	}

//	public MeterDetails(long tranNo, String badgeNo) {
//		this.meterDetailsPK = new MeterDetailsPK(tranNo, badgeNo);
//	}

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

	@Transient
	@JsonIgnore
	public Header getHeaders() {
		return header;
	}

	public void setHeaders(Header header) {
		this.header = header;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (meterDetailsPK != null ? meterDetailsPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof MeterDetails)) {
			return false;
		}
		MeterDetails other = (MeterDetails) object;
		if ((this.meterDetailsPK == null && other.meterDetailsPK != null)
				|| (this.meterDetailsPK != null && !this.meterDetailsPK.equals(other.meterDetailsPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.example.test.model.MeterDetails[ meterDetailsPK=" + meterDetailsPK + " ]";
	}

}
