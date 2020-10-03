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
public class ExtractedBillEvent {

	private String uuid;
	private Long batchNo;
	private Date creDttm;

}
