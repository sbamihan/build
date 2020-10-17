/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.subscriptionservice.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@Column(name = "type_code")
	private String typeCode;
	@Column(name = "description")
	private String description;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "contactType")
	@JsonIgnore
	private Collection<AccountContact> accountContactCollection;

}
