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
public class LineDetailsPK implements Serializable {

	@Column(name = "TRAN_NO")
	private long tranNo;
	@Column(name = "LINE_CODE")
	private String lineCode;

	public LineDetailsPK() {
	}

	public LineDetailsPK(long tranNo, String lineCode) {
		this.tranNo = tranNo;
		this.lineCode = lineCode;
	}

	public long getTranNo() {
		return tranNo;
	}

	public void setTranNo(long tranNo) {
		this.tranNo = tranNo;
	}

	public String getLineCode() {
		return lineCode;
	}

	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) tranNo;
		hash += (lineCode != null ? lineCode.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof LineDetailsPK)) {
			return false;
		}
		LineDetailsPK other = (LineDetailsPK) object;
		if (this.tranNo != other.tranNo) {
			return false;
		}
		if ((this.lineCode == null && other.lineCode != null)
				|| (this.lineCode != null && !this.lineCode.equals(other.lineCode))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.example.test.model.LineDetailsPK[ tranNo=" + tranNo + ", lineCode=" + lineCode + " ]";
	}

}
