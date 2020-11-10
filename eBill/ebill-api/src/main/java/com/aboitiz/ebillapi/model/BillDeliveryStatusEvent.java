package com.aboitiz.ebillapi.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDeliveryStatusEvent {

	private Long transId;
	private String msisdn;
	private Long statusCode;
	private Date timestamp;
	private String billUuid;
	private String shortUrl;
	private String longUrl;

}
