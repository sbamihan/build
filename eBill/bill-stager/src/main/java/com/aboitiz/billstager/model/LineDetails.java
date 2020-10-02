package com.aboitiz.billstager.model;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineDetails {

	protected LineDetailsPK lineDetailsPK;
	private Long printPriority;
	private String description;
	private String rate;
	private BigDecimal amount;
	private Bill bill;

}
