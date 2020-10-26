package com.aboitiz.billstager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

	@JsonIgnore
	private Integer id;

	private String value;

	private String statFlg;

	private Character primSw;

	@JsonIgnore
	private Account account;

	private ContactType contactType;

}
