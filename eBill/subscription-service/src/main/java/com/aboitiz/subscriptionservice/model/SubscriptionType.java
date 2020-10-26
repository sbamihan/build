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

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subscription_type", schema = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Subscription Type", description = "Data object for a Subscription Type.")
public class SubscriptionType {

	@Id
	@NotNull
	@Column(name = "type_code")
	@Schema(description = "The code for the type of subscription.", example = "EBIL", allowableValues = { "EBIL",
			"MOAP", "OUTN" }, required = true, readOnly = false)
	private String typeCode;

	@Column(name = "description")
	@Schema(description = "Short description of the type of subscription.", example = "Electronic sending of bill.", required = false, readOnly = true)
	private String description;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "subscriptionType")
	@JsonIgnore
	private List<Subscription> subscriptionList;

}
