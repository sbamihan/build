package com.aboitiz.billstager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

	private String accountId;
	private String accountName;
	private String emailAddress;

}
