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
@Table(name = "BP_QUERY")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineDetail {

	@EmbeddedId
	protected LineDetailPK lineDetailPK;
	@Column(name = "PRINT_PRIORITY")
	private Long printPriority;
	@Column(name = "DIS_DESCRIPTION")
	private String description;
	@Column(name = "DIS_LINE_RATE")
	private String rate;
	@Column(name = "DIS_LINE_AMOUNT")
	private BigDecimal amount;
	@JoinColumn(name = "TRAN_NO", referencedColumnName = "TRAN_NO", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	@JsonIgnore
	private Bill bill;

}
