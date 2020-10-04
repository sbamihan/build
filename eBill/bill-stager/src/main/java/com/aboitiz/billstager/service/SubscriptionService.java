package com.aboitiz.billstager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aboitiz.billstager.model.Account;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

@Service
@Log4j2
public class SubscriptionService {

	private final WebClient client;

	public SubscriptionService(WebClient.Builder webClientBuilder) {
		this.client = webClientBuilder.baseUrl("http://localhost:25649").build();
	}

	public Flux<Account> getAccounts() {
		String uri = "/accounts";
		log.info("invoking {}", uri);
		return this.client.get().uri(uri).retrieve().bodyToFlux(Account.class);
	}

}
