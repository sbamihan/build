package com.aboitiz.ebillapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bill {

	private long billId;
	private String accountId;
	private String accountName;
}
