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

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account", schema = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Account", description = "Data object for a Account.")
public class Account {

	@Id
	@Basic(optional = false)
	@Column(name = "account_id")
	@Schema(description = "Unique identifier of the account.", example = "1000000001", required = true, readOnly = false)
	private String accountId;

	@Column(name = "name")
	@Schema(description = "The name for tha account.", example = "Amihan, Sherwin", required = true, readOnly = false)
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	@LazyCollection(LazyCollectionOption.FALSE)
	@Schema(required = false, readOnly = true)
	private List<Contact> contactList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	@LazyCollection(LazyCollectionOption.FALSE)
	@Schema(required = false, readOnly = true)
	@JsonIgnore
	private List<Subscription> subscriptionList;

}
