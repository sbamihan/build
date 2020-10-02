/*
 * To change this license header, choose License Header in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author SBAmihan
 */
@Embeddable
public class MeterDetailsPK implements Serializable {

	private static final long serialVersionUID = -3984117926313050468L;
	@Column(name = "TRAN_NO")
	private long tranNo;
	@Column(name = "BADGE_NO")
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
		int hash = 0;
		hash += (int) tranNo;
		hash += (badgeNo != null ? badgeNo.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof MeterDetailsPK)) {
			return false;
		}
		MeterDetailsPK other = (MeterDetailsPK) object;
		if (this.tranNo != other.tranNo) {
			return false;
		}
		if ((this.badgeNo == null && other.badgeNo != null)
				|| (this.badgeNo != null && !this.badgeNo.equals(other.badgeNo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MeterDetailsPK [tranNo=" + tranNo + ", badgeNo=" + badgeNo + "]";
	}

}
