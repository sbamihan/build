package com.aboitiz.billtransporter.model;

import java.util.Collection;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payload {

	private String uuid;
	private Date creDttm;
	private Collection<Bill> billCollection;
	
	public Payload(StagedBillEvent event) {
		this.uuid = event.getUuid();
		this.creDttm = new Date();
	}
}