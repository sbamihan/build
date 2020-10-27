package com.aboitiz.billstager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aboitiz.billstager.config.ClientConfig;
import com.aboitiz.billstager.model.Account;
import com.aboitiz.billstager.model.ExtractedBillEvent;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

@Service
@Log4j2
public class SubscriptionService {

	private static final String SUBSCRIPTION_TYPE = "EBIL";

	private final ClientConfig config;
	private final WebClient client;

	public SubscriptionService(WebClient.Builder webClientBuilder, ClientConfig config) {
		this.config = config;
		this.client = webClientBuilder.baseUrl(this.config.getAccountServiceUrl()).build();
	}

	public Flux<Account> getAccounts(ExtractedBillEvent event) {
		String uri = "/" + event.getDuCode() + "/accounts";
		if (event.getAccountId() != null) {
			uri = uri + "/" + event.getAccountId() + "/subscriptions/search/findBySubscriptionType?subscriptionTypeCode="
					+ SUBSCRIPTION_TYPE;
		} else {
			uri = uri + "/search/findBySubscription?subscriptionTypeCode=" + SUBSCRIPTION_TYPE;
		}

		log.info("uri {}", uri);
		return this.client.get().uri(uri).retrieve().bodyToFlux(Account.class);
	}

}
