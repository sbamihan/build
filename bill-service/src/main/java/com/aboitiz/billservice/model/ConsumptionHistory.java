/*
 * To change this license header, choose License Header in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billservice.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author SBAmihan
 */
@Entity
@Table(name = "BP_CONSUMPTION_HIST")
public class ConsumptionHistory {

	@EmbeddedId
	protected ConsumptionHistoryPK consumptionHistoryPK;
	@Column(name = "CONSUMPTION")
	private BigDecimal consumption;
	@JoinColumn(name = "TRAN_NO", referencedColumnName = "TRAN_NO", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Header header;

	public ConsumptionHistory() {
	}

	public ConsumptionHistoryPK getConsumptionHistoryPK() {
		return consumptionHistoryPK;
	}

	public void setConsumptionHistoryPK(ConsumptionHistoryPK consumptionHistoryPK) {
		this.consumptionHistoryPK = consumptionHistoryPK;
	}

	public BigDecimal getConsumption() {
		return consumption;
	}

	public void setConsumption(BigDecimal consumption) {
		this.consumption = consumption;
	}

	@Transient
	@JsonIgnore
	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consumption == null) ? 0 : consumption.hashCode());
		result = prime * result + ((consumptionHistoryPK == null) ? 0 : consumptionHistoryPK.hashCode());
		result = prime * result + ((header == null) ? 0 : header.hashCode());
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
		ConsumptionHistory other = (ConsumptionHistory) obj;
		if (consumption == null) {
			if (other.consumption != null)
				return false;
		} else if (!consumption.equals(other.consumption))
			return false;
		if (consumptionHistoryPK == null) {
			if (other.consumptionHistoryPK != null)
				return false;
		} else if (!consumptionHistoryPK.equals(other.consumptionHistoryPK))
			return false;
		if (header == null) {
			if (other.header != null)
				return false;
		} else if (!header.equals(other.header))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConsumptionHistory [consumptionHistoryPK=" + consumptionHistoryPK + ", consumption=" + consumption
				+ ", header=" + header + "]";
	}

}
