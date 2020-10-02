/*
 * To change this license header, choose License Header in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billstager.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * @author SBAmihan
 */
public class ConsumptionHistoryPK {

	private Long tranNo;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private Date rdgDate;

	public ConsumptionHistoryPK() {
	}

	public ConsumptionHistoryPK(Long tranNo, Date rdgDate) {
		this.tranNo = tranNo;
		this.rdgDate = rdgDate;
	}

	public Long getTranNo() {
		return tranNo;
	}

	public void setTranNo(Long tranNo) {
		this.tranNo = tranNo;
	}

	public Date getRdgDate() {
		return rdgDate;
	}

	public void setRdgDate(Date rdgDate) {
		this.rdgDate = rdgDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rdgDate == null) ? 0 : rdgDate.hashCode());
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
		ConsumptionHistoryPK other = (ConsumptionHistoryPK) obj;
		if (rdgDate == null) {
			if (other.rdgDate != null)
				return false;
		} else if (!rdgDate.equals(other.rdgDate))
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
		return "ConsumptionHistoryPK [tranNo=" + tranNo + ", rdgDate=" + rdgDate + "]";
	}

}
