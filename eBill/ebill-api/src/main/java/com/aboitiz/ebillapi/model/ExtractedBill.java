package com.aboitiz.ebillapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtractedBill {

	private Long batchNo;
	private String duCode;

}
