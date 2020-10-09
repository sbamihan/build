package com.aboitiz.billstager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class ClientConfig {

	@Value("${service.bill.url.base}")
	private String billServiceUrl;
	
	@Value("${service.account.url.base}")
	private String accountServiceUrl;

}
