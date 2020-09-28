/*
 * To change this license header, choose License Header in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billservice.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author SBAmihan
 */
@Embeddable
public class MeterDetailsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "TRAN_NO")
    private long tranNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "METER_COUNT")
    private short meterCount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "BADGE_NO")
    private String badgeNo;

    public MeterDetailsPK() {
    }

    public MeterDetailsPK(long tranNo, short meterCount, String badgeNo) {
        this.tranNo = tranNo;
        this.meterCount = meterCount;
        this.badgeNo = badgeNo;
    }

    public long getTranNo() {
        return tranNo;
    }

    public void setTranNo(long tranNo) {
        this.tranNo = tranNo;
    }

    public short getMeterCount() {
        return meterCount;
    }

    public void setMeterCount(short meterCount) {
        this.meterCount = meterCount;
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
        hash += (int) meterCount;
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
        if (this.meterCount != other.meterCount) {
            return false;
        }
        if ((this.badgeNo == null && other.badgeNo != null) || (this.badgeNo != null && !this.badgeNo.equals(other.badgeNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.test.model.MeterDetailsPK[ tranNo=" + tranNo + ", meterCount=" + meterCount + ", badgeNo=" + badgeNo + " ]";
    }
    
}
