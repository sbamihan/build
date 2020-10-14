/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.subscriptionservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account_subscription", schema = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountSubscription {

	@Id
	@Column(name = "id")
	@JsonIgnore
	private String id;
	@Column(name = "subscribe")
	private Character subscribe;
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	@ManyToOne(optional = false)
	@JsonIgnore
	private Account accountId;
	@JoinColumn(name = "type_code", referencedColumnName = "type_code")
	@ManyToOne(optional = false)
	private SubscriptionType subscriptionType;

}
