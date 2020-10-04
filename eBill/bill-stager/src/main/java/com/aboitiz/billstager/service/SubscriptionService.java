package com.aboitiz.billstager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aboitiz.billstager.model.Account;

import reactor.core.publisher.Flux;

@Service
public class SubscriptionService {

	private final WebClient client;

	public SubscriptionService(WebClient.Builder webClientBuilder) {
		this.client = webClientBuilder.baseUrl("http://localhost:25649").build();
	}

	public Flux<Account> getAccounts() {
		String uri = "/accounts";
		return this.client.get().uri(uri).retrieve().bodyToFlux(Account.class);
	}

}
