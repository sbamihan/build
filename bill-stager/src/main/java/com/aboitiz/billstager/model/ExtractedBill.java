package com.aboitiz.billstager.model;

import java.util.Date;

public class ExtractedBill {

	private Long batchNo;
	private Date creDttm;

	public ExtractedBill() {
	}

	public ExtractedBill(Long batchNo, Date creDttm) {
		this.batchNo = batchNo;
		this.creDttm = creDttm;
	}

	public Long getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(Long batchNo) {
		this.batchNo = batchNo;
	}

	public Date getCreDttm() {
		return creDttm;
	}

	public void setCreDttm(Date creDttm) {
		this.creDttm = creDttm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchNo == null) ? 0 : batchNo.hashCode());
		result = prime * result + ((creDttm == null) ? 0 : creDttm.hashCode());
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
		ExtractedBill other = (ExtractedBill) obj;
		if (batchNo == null) {
			if (other.batchNo != null)
				return false;
		} else if (!batchNo.equals(other.batchNo))
			return false;
		if (creDttm == null) {
			if (other.creDttm != null)
				return false;
		} else if (!creDttm.equals(other.creDttm))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExtractedBill [batchNo=" + batchNo + ", creDttm=" + creDttm + "]";
	}

}
