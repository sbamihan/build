/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.com.apdu.meterservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author SBAmihan
 */
@Entity
@Table(name = "ami_meter")
public class Meter {

	@Id
	@Column(name = "MTR_ID")
	private String mtrId;
	@Column(name = "BADGE_NBR")
	private String badgeNbr;
	@Column(name = "MTR_TYPE_CD")
	private String mtrTypeCd;
	@Column(name = "MFG_CD")
	private String mfgCd;
	@Column(name = "MODEL_CD")
	private String modelCd;
	@Column(name = "SERIAL_NBR")
	private String serialNbr;
	@Column(name = "SERVICE_TYPE")
	private String serviceType;

	public Meter() {
	}

	public String getMtrId() {
		return mtrId;
	}

	public void setMtrId(String mtrId) {
		this.mtrId = mtrId;
	}

	public String getBadgeNbr() {
		return badgeNbr;
	}

	public void setBadgeNbr(String badgeNbr) {
		this.badgeNbr = badgeNbr;
	}

	public String getMtrTypeCd() {
		return mtrTypeCd;
	}

	public void setMtrTypeCd(String mtrTypeCd) {
		this.mtrTypeCd = mtrTypeCd;
	}

	public String getMfgCd() {
		return mfgCd;
	}

	public void setMfgCd(String mfgCd) {
		this.mfgCd = mfgCd;
	}

	public String getModelCd() {
		return modelCd;
	}

	public void setModelCd(String modelCd) {
		this.modelCd = modelCd;
	}

	public String getSerialNbr() {
		return serialNbr;
	}

	public void setSerialNbr(String serialNbr) {
		this.serialNbr = serialNbr;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	@Override
	public String toString() {
		return "Meter [mtrId=" + mtrId + ", badgeNbr=" + badgeNbr + ", mtrTypeCd=" + mtrTypeCd + ", mfgCd=" + mfgCd
				+ ", modelCd=" + modelCd + ", serialNbr=" + serialNbr + ", serviceType=" + serviceType + "]";
	}

}
