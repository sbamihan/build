package com.aboitiz.billretriever.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BP_MESSAGE_CODES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillMessage {

	@Id
	@Column(name = "MESSAGE_CODE")
	private String messageCode;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "MESSAGE_TEXT")
	private String messageText;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "billMessage")
	@JsonIgnore
	private List<Bill> billList;

}
