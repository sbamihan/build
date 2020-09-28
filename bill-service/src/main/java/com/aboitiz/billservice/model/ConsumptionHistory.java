/*
 * To change this license header, choose License Header in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billservice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author SBAmihan
 */
@Entity
@Table(name = "BP_CONSUMPTION_HIST")
@NamedQueries({
    @NamedQuery(name = "ConsumptionHistory.findAll", query = "SELECT c FROM ConsumptionHistory c")})
public class ConsumptionHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConsumptionHistoryPK consumptionHistoryPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CONSUMPTION")
    private BigDecimal consumption;
    @JoinColumn(name = "TRAN_NO", referencedColumnName = "TRAN_NO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Header header;

    public ConsumptionHistory() {
    }

    public ConsumptionHistory(ConsumptionHistoryPK consumptionHistoryPK) {
        this.consumptionHistoryPK = consumptionHistoryPK;
    }

    public ConsumptionHistory(long tranNo, Date rdgDate) {
        this.consumptionHistoryPK = new ConsumptionHistoryPK(tranNo, rdgDate);
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

    @Transient
    @JsonIgnore
    public Header getHeaders() {
        return header;
    }

    public void setHeaders(Header header) {
        this.header = header;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consumptionHistoryPK != null ? consumptionHistoryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsumptionHistory)) {
            return false;
        }
        ConsumptionHistory other = (ConsumptionHistory) object;
        if ((this.consumptionHistoryPK == null && other.consumptionHistoryPK != null) || (this.consumptionHistoryPK != null && !this.consumptionHistoryPK.equals(other.consumptionHistoryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.test.model.ConsumptionHistory[ consumptionHistoryPK=" + consumptionHistoryPK + " ]";
    }
    
}
