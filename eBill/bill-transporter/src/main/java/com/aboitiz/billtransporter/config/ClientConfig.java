/**
 * 
 */
package com.aboitiz.billtransporter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@RefreshScope
@Data
public class ClientConfig {

	@Value("${client.service.primary.baseUrl}")
	private String primaryClientServiceBaseUrl;

	@Value("${client.service.primary.callbackEndpoint}")
	private String primaryClientCallbackEndpoint;

}
