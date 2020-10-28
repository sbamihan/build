package com.aboitiz.billtransporter.model;

import java.util.Collection;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payload {

	private String uuid;
	private String duCode;
	private Date creDttm;
	private Collection<Bill> bills;
	
	public Payload(StagedBillEvent event) {
		this.uuid = event.getUuid();
		this.duCode = event.getDuCode();
		this.creDttm = new Date();
	}
}
