/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billtransporter.model;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

	private String accountId;
	private String accountName;
	@JsonIgnore
	private Collection<AccountSubscription> accountSubscriptions;
	private Collection<AccountContact> accountContacts;

}
