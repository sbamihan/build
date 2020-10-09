package com.aboitiz.billstager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aboitiz.billstager.config.ClientConfig;
import com.aboitiz.billstager.model.Account;
import com.aboitiz.billstager.model.ExtractedBillEvent;

import reactor.core.publisher.Flux;

@Service
public class SubscriptionService {

	private static final String SUBSCRIPTION_TYPE = "EBIL";

	private final ClientConfig config;
	private final WebClient client;

	public SubscriptionService(WebClient.Builder webClientBuilder, ClientConfig config) {
		this.config = config;
		this.client = webClientBuilder.baseUrl(this.config.getAccountServiceUrl()).build();
	}

	public Flux<Account> getAccounts(ExtractedBillEvent event) {
		String uri = "/" + event.getDuCode() + "/accounts/search";
		if (!event.getAccountId().isEmpty()) {
			uri = "/findByAccountIdAndTypeCode?accountId=" + event.getAccountId() + "&typeCode=" + SUBSCRIPTION_TYPE;
		} else {
			uri = "/findByTypeCode?typeCode=" + SUBSCRIPTION_TYPE;
		}

		return this.client.get().uri(uri).retrieve().bodyToFlux(Account.class);
	}

	public Flux<Account> findByTypeCode(String typeCode) {
		String uri = "/accounts/search/findByTypeCode?typeCode=" + typeCode;
		return this.client.get().uri(uri).retrieve().bodyToFlux(Account.class);
	}

}
