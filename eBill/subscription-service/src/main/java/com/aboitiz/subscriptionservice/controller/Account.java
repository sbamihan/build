package com.aboitiz.subscriptionservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ACCOUNT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

	@Id
	@Column(name = "ACCOUNT_ID")
	private String accountId;
	@Column(name = "ACCOUNT_NAME")
	private String accountName;
	@Column(name = "email_address")
	private String emailAddress;

}
