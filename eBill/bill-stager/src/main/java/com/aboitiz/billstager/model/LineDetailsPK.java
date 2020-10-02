package com.aboitiz.billstager.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineDetailsPK {

	private Long tranNo;
	private String lineCode;

}
