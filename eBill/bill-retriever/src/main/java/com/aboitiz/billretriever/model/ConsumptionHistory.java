/*
 * To change this license bill, choose License Bill in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billretriever.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BP_CONSUMPTION_HIST")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumptionHistory {

	@EmbeddedId
	protected ConsumptionHistoryPK consumptionHistoryPK;
	@Column(name = "CONSUMPTION")
	private BigDecimal consumption;
	@JoinColumn(name = "TRAN_NO", referencedColumnName = "TRAN_NO", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	@JsonIgnore
	private Bill bill;

}
