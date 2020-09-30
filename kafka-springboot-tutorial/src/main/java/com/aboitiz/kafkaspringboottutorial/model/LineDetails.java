/*
 * To change this license header, choose License Header in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.kafkaspringboottutorial.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author SBAmihan
 */
@Entity
@Table(name = "BP_DETAILS")
@NamedQueries({
    @NamedQuery(name = "LineDetails.findAll", query = "SELECT l FROM LineDetails l")})
public class LineDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LineDetailsPK lineDetailsPK;
    @Size(max = 300)
    @Column(name = "LINE_RATE")
    private String lineRate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LINE_AMOUNT")
    private BigDecimal lineAmount;
    @JoinColumn(name = "TRAN_NO", referencedColumnName = "TRAN_NO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Header header;

    public LineDetails() {
    }

    public LineDetails(LineDetailsPK lineDetailsPK) {
        this.lineDetailsPK = lineDetailsPK;
    }

    public LineDetails(long tranNo, String lineCode) {
        this.lineDetailsPK = new LineDetailsPK(tranNo, lineCode);
    }

    public LineDetailsPK getLineDetailsPK() {
        return lineDetailsPK;
    }

    public void setLineDetailsPK(LineDetailsPK lineDetailsPK) {
        this.lineDetailsPK = lineDetailsPK;
    }

    public String getLineRate() {
        return lineRate;
    }

    public void setLineRate(String lineRate) {
        this.lineRate = lineRate;
    }

    public BigDecimal getLineAmount() {
        return lineAmount;
    }

    public void setLineAmount(BigDecimal lineAmount) {
        this.lineAmount = lineAmount;
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
        hash += (lineDetailsPK != null ? lineDetailsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineDetails)) {
            return false;
        }
        LineDetails other = (LineDetails) object;
        if ((this.lineDetailsPK == null && other.lineDetailsPK != null) || (this.lineDetailsPK != null && !this.lineDetailsPK.equals(other.lineDetailsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.test.model.LineDetails[ lineDetailsPK=" + lineDetailsPK + " ]";
    }
    
}
