package com.aboitiz.billservice.model;

import java.util.Date;

import lombok.Value;

@Value
public class BillSet {

	private Long batchNo;
	private Date billDate;
	
}
