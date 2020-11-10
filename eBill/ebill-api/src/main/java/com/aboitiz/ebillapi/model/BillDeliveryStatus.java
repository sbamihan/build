package com.aboitiz.ebillapi.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDeliveryStatus {

	private Long transid;
	private String msisdn;
	private Long status_code;
	private Date timestamp;
	private String rcvd_transid;
	private String short_url;
	private String long_url;

}
