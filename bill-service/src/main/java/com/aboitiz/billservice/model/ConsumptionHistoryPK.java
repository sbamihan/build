/*
 * To change this license header, choose License Header in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billservice.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author SBAmihan
 */
@Embeddable
public class ConsumptionHistoryPK implements Serializable {

    @Column(name = "TRAN_NO")
    private long tranNo;
    @Column(name = "RDG_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rdgDate;

    public ConsumptionHistoryPK() {
    }

    public ConsumptionHistoryPK(long tranNo, Date rdgDate) {
        this.tranNo = tranNo;
        this.rdgDate = rdgDate;
    }

    public long getTranNo() {
        return tranNo;
    }

    public void setTranNo(long tranNo) {
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
        int hash = 0;
        hash += (int) tranNo;
        hash += (rdgDate != null ? rdgDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsumptionHistoryPK)) {
            return false;
        }
        ConsumptionHistoryPK other = (ConsumptionHistoryPK) object;
        if (this.tranNo != other.tranNo) {
            return false;
        }
        if ((this.rdgDate == null && other.rdgDate != null) || (this.rdgDate != null && !this.rdgDate.equals(other.rdgDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.test.model.ConsumptionHistoryPK[ tranNo=" + tranNo + ", rdgDate=" + rdgDate + " ]";
    }
    
}
