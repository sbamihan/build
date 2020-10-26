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
@Table(name = "contact_type", schema = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactType {

	@Id
	@NotNull
	@Column(name = "type_code")
	@Schema(description = "The code for the type of contact.", example = "EADD", allowableValues = { "EADD", "SMSN",
			"PHON" }, required = true, readOnly = false)
	private String typeCode;

	@Column(name = "description")
	@Schema(description = "Short description of the type of contact.", example = "Email Address.", required = false, readOnly = true)
	private String description;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "contactType")
	@JsonIgnore
	private List<Contact> contactList;

}
