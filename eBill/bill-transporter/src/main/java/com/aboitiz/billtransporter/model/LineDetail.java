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
public class LineDetail {

	protected LineDetailPK lineDetailPK;
	private Long printPriority;
	private String description;
	private String rate;
	private BigDecimal amount;
	@JsonIgnore
	private Bill bill;

}
