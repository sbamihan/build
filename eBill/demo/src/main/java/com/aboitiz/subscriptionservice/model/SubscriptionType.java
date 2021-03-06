package com.aboitiz.subscriptionservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subscription_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionType {

	@Id
	@NotNull
	@Column(name = "type_code")
	private String typeCode;
	@Column(name = "description")
	private String description;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "subscriptionType")
	@JsonIgnore
	private List<Subscription> subscriptionList;

}
