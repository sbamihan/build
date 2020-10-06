/*
 * To change this license bill, choose License Bill in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billretriever.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BP_METER_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeterDetail {

	@EmbeddedId
	protected MeterDetailPK meterDetailPK;
	@Column(name = "SERIAL_NO")
	private String serialNo;
	@Column(name = "POLE_NO")
	private String poleNo;
	@Column(name = "MULTIPLIER")
	private BigDecimal multiplier;
	@Column(name = "PREV_READING_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date prevReadingDate;
	@Column(name = "CURR_READING_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date currReadingDate;
	@Column(name = "CONN_LOAD")
	private BigDecimal connLoad;
	@Column(name = "PREV_KWHR_RDG")
	private BigDecimal prevKwhrRdg;
	@Column(name = "CURR_KWHR_RDG")
	private BigDecimal currKwhrRdg;
	@Column(name = "REG_KWHR_CONS")
	private BigDecimal regKwhrCons;
	@Column(name = "PREV_DEMAND_RDG")
	private BigDecimal prevDemandRdg;
	@Column(name = "CURR_DEMAND_RDG")
	private BigDecimal currDemandRdg;
	@Column(name = "REG_DEMAND_CONS")
	private BigDecimal regDemandCons;
	@Column(name = "PREV_KVAR_RDG")
	private BigDecimal prevKvarRdg;
	@Column(name = "CURR_KVAR_RDG")
	private BigDecimal currKvarRdg;
	@Column(name = "REG_KVAR_CONS")
	private BigDecimal regKvarCons;
	@Column(name = "METER_TYPE")
	private String meterType;
	@Column(name = "CONSUM_SUB_FLG")
	private String consumSubFlg;
	@Column(name = "KWHR_CONSUM_SUB_FLG")
	private String kwhrConsumSubFlg;
	@Column(name = "DEMAND_CONSUM_SUB_FLG")
	private String demandConsumSubFlg;
	@Column(name = "KVAR_CONSUM_SUB_FLG")
	private String kvarConsumSubFlg;
	@JoinColumn(name = "TRAN_NO", referencedColumnName = "TRAN_NO", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	@JsonIgnore
	private Bill bill;

}
