package com.aboitiz.billstager.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtractedBillEvent {

	private String uuid;
	private Long batchNo;
	private String duCode;
	private Date creDttm;

}
