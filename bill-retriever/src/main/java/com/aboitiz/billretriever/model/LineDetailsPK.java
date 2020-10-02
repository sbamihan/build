package com.aboitiz.billretriever.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author SBAmihan
 */
@Embeddable
public class LineDetailsPK implements Serializable {

	private static final long serialVersionUID = -6136714559873555944L;
	@Column(name = "TRAN_NO")
	private Long tranNo;
	@Column(name = "LINE_CODE")
	private String lineCode;

	public LineDetailsPK() {
	}

	public LineDetailsPK(Long tranNo, String lineCode) {
		this.tranNo = tranNo;
		this.lineCode = lineCode;
	}

	public Long getTranNo() {
		return tranNo;
	}

	public void setTranNo(Long tranNo) {
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lineCode == null) ? 0 : lineCode.hashCode());
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
		LineDetailsPK other = (LineDetailsPK) obj;
		if (lineCode == null) {
			if (other.lineCode != null)
				return false;
		} else if (!lineCode.equals(other.lineCode))
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
		return "LineDetailsPK [tranNo=" + tranNo + ", lineCode=" + lineCode + "]";
	}

}
