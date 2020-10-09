package com.aboitiz.ebillapi.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtractedBillEvent {

	private String uuid;
	private String duCode;
	private Long batchNo;
	private String accountId;
	private Date creDttm;

}
