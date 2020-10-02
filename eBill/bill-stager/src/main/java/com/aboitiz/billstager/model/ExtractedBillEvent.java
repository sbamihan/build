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
	private Date creDttm;

}
