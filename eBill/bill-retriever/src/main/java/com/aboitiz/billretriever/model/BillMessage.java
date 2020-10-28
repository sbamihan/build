package com.aboitiz.billretriever.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

}
