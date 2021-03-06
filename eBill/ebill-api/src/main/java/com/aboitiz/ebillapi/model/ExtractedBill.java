package com.aboitiz.ebillapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtractedBill {

	private String duCode;
	private Long batchNo;
	private String accountId;

}
