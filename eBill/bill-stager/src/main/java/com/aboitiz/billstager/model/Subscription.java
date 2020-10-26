package com.aboitiz.billstager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {

	@JsonIgnore
	private Integer id;

	private Character subscribe;

	private String statFlg;

	@JsonIgnore
	private Account account;

	private SubscriptionType subscriptionType;

}
