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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contact")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "value")
	private String value;
	@NotNull
	@Column(name = "stat_flg")
	private String statFlg;
	@NotNull
	@Column(name = "prim_sw")
	private Character primSw;
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@JsonIgnore
	private Account account;
	@JoinColumn(name = "contact_type", referencedColumnName = "type_code")
	@ManyToOne(optional = false)
	private ContactType contactType;

}
