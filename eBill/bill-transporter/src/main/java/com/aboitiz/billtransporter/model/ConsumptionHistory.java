/*
 * To change this license bill, choose License Bill in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billtransporter.model;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConsumptionHistory {

	protected ConsumptionHistoryPK consumptionHistoryPK;
	private BigDecimal consumption;
	@JsonIgnore
	private Bill bill;

}
