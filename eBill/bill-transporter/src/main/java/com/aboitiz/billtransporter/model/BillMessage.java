package com.aboitiz.billtransporter.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BillMessage {

	private String messageCode;
	private String description;
	private String messageText;
	private Bill bill;

}
