package com.aboitiz.billtransporter.model;

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

}
