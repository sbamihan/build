package com.aboitiz.billstager.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactType {

	private String typeCode;

	private String description;

	@JsonIgnore
	private List<Contact> contactList;

}
