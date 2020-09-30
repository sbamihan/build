package com.aboitiz.kafkaspringboottutorial.model;

import java.util.Date;

import lombok.Value;

@Value
public class BillSet {

	private Long batchNo;
	private Date billDate;
	
}
