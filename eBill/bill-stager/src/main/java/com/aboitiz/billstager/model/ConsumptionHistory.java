/*
 * To change this license bill, choose License Bill in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billstager.model;

import java.math.BigDecimal;

/**
 *
 * @author SBAmihan
 */
public class ConsumptionHistory {

	protected ConsumptionHistoryPK consumptionHistoryPK;
	private BigDecimal consumption;
	private Bill bill;

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

	public Bill getHeader() {
		return bill;
	}

	public void setHeader(Bill bill) {
		this.bill = bill;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consumption == null) ? 0 : consumption.hashCode());
		result = prime * result + ((consumptionHistoryPK == null) ? 0 : consumptionHistoryPK.hashCode());
		result = prime * result + ((bill == null) ? 0 : bill.hashCode());
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
		if (bill == null) {
			if (other.bill != null)
				return false;
		} else if (!bill.equals(other.bill))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConsumptionHistory [consumptionHistoryPK=" + consumptionHistoryPK + ", consumption=" + consumption
				+ ", bill=" + bill + "]";
	}

}
