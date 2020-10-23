package com.aboitiz.subscriptionservice.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

	@Id
	@Basic(optional = false)
	@Column(name = "account_id")
	private String accountId;
	@Column(name = "name")
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Contact> contactList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Subscription> subscriptionList;

}
