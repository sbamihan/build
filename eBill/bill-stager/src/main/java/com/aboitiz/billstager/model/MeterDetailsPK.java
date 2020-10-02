/*
 * To change this license header, choose License Header in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billstager.model;

/**
 *
 * @author SBAmihan
 */
public class MeterDetailsPK {

	private long tranNo;
	private String badgeNo;

	public MeterDetailsPK() {
	}

	public MeterDetailsPK(long tranNo, String badgeNo) {
		this.tranNo = tranNo;
		this.badgeNo = badgeNo;
	}

	public long getTranNo() {
		return tranNo;
	}

	public void setTranNo(long tranNo) {
		this.tranNo = tranNo;
	}

	public String getBadgeNo() {
		return badgeNo;
	}

	public void setBadgeNo(String badgeNo) {
		this.badgeNo = badgeNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((badgeNo == null) ? 0 : badgeNo.hashCode());
		result = prime * result + (int) (tranNo ^ (tranNo >>> 32));
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
		MeterDetailsPK other = (MeterDetailsPK) obj;
		if (badgeNo == null) {
			if (other.badgeNo != null)
				return false;
		} else if (!badgeNo.equals(other.badgeNo))
			return false;
		if (tranNo != other.tranNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MeterDetailsPK [tranNo=" + tranNo + ", badgeNo=" + badgeNo + "]";
	}

}
