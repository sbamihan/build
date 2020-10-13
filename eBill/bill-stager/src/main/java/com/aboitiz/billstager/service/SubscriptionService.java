package com.aboitiz.billstager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aboitiz.billstager.config.ClientConfig;
import com.aboitiz.billstager.model.Account;
import com.aboitiz.billstager.model.ExtractedBillEvent;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
		String uri = "/" + event.getDuCode() + "/accounts/search";
		if (event.getAccountId() != null) {
			uri = uri + "/findByAccountIdAndTypeCode?accountId=" + event.getAccountId() + "&typeCode=" + SUBSCRIPTION_TYPE;
		} else {
			uri = uri + "/findByTypeCode?typeCode=" + SUBSCRIPTION_TYPE;
		}

		log.info("uri {}", uri);
		return this.client.get().uri(uri).retrieve().bodyToFlux(Account.class);
	}

	public Flux<Account> getAccounts(String duCode) {
		String uri = "/" + duCode + "/accounts/search/findByTypeCode?typeCode=" + SUBSCRIPTION_TYPE;

		return this.client.get().uri(uri).retrieve().bodyToFlux(Account.class);
	}
	
	public Mono<Account> getAccount(String duCode, String accountId) {
		String uri = "/" + duCode + "/accounts/search";
		if (!accountId.isEmpty()) {
			uri = uri + "/findByAccountIdAndTypeCode?accountId=" + accountId + "&typeCode=" + SUBSCRIPTION_TYPE;
		} else {
			uri = uri + "/findByTypeCode?typeCode=" + SUBSCRIPTION_TYPE;
		}

		log.info("uri {}", uri);
		return this.client.get().uri(uri).retrieve().bodyToMono(Account.class);
	}

	public Flux<Account> findByTypeCode(String typeCode) {
		String uri = "/dlpc/accounts/search/findByTypeCode?typeCode=" + typeCode;
		
		log.info("uri {}", uri);
		return this.client.get().uri(uri).retrieve().bodyToFlux(Account.class);
	}

}
