package com.aboitiz.billstager.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

	private String accountId;

	private String name;

	private List<Contact> contactList;

	@JsonIgnore
	private List<Subscription> subscriptionList;

}
