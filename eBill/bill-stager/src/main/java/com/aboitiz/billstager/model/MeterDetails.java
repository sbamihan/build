/*
 * To change this license bill, choose License Bill in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billstager.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MeterDetails {

	protected MeterDetailsPK meterDetailsPK;
	private String serialNo;
	private String poleNo;
	private BigDecimal multiplier;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private Date prevReadingDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private Date currReadingDate;
	private BigDecimal connLoad;
	private BigDecimal prevKwhrRdg;
	private BigDecimal currKwhrRdg;
	private BigDecimal regKwhrCons;
	private BigDecimal prevDemandRdg;
	private BigDecimal currDemandRdg;
	private BigDecimal regDemandCons;
	private BigDecimal prevKvarRdg;
	private BigDecimal currKvarRdg;
	private BigDecimal regKvarCons;
	private String meterType;
	private String consumSubFlg;
	private String kwhrConsumSubFlg;
	private String demandConsumSubFlg;
	private String kvarConsumSubFlg;
	private Bill bill;

}
