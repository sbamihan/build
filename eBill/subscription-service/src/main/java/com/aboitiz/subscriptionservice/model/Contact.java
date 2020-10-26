package com.aboitiz.subscriptionservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contact", schema = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Contact", description = "Data object for a Contact.")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Schema(description = "Unique identifier of the contact.", example = "1", required = false, readOnly = true)
	private Integer id;

	@Column(name = "value")
	@Schema(description = "The contact value.", example = "sherwin.amihan@aboitiz.com", required = true, readOnly = false)
	private String value;

	@NotNull
	@Column(name = "stat_flg")
	@Schema(description = "The status of contact.", example = "20", allowableValues = { "20", "30",
			"40" }, required = true, readOnly = false)
	private String statFlg;

	@NotNull
	@Column(name = "prim_sw")
	@Schema(description = "Whether or not the contact is set to primary.", example = "Y", allowableValues = { "Y",
			"N" }, required = true, readOnly = false)
	private Character primSw;

	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@JsonIgnore
	@Schema(description = "The account where the contact is to be linked.", example = "1000000001", required = true, readOnly = false)
	private Account account;

	@JoinColumn(name = "contact_type", referencedColumnName = "type_code")
	@ManyToOne(optional = false)
	private ContactType contactType;

}
