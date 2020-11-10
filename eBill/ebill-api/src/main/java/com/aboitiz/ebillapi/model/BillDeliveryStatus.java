package com.aboitiz.ebillapi.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDeliveryStatus {

	private Long transId;
	private String msIsdn;
	private Long statusCode;
	private Date timestamp;
	private String rcvdTransId;
	private String shortUrl;
	private String longUrl;

}
