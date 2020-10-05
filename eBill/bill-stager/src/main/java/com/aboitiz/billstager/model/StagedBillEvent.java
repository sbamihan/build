package com.aboitiz.billstager.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StagedBillEvent {

	private String uuid;
	private Long batchNo;
	private String duCode;
	private Date creDttm;
	private int count;

	public StagedBillEvent(ExtractedBillEvent extractedBillEvent) {
		this.uuid = extractedBillEvent.getUuid();
		this.batchNo = extractedBillEvent.getBatchNo();
		this.duCode = extractedBillEvent.getDuCode();
		this.creDttm = extractedBillEvent.getCreDttm();
	}

}
