package com.aboitiz.billstager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aboitiz.billstager.model.Header;

import reactor.core.publisher.Flux;

@Service
public class BillService {

	private final WebClient client;

	public BillService(WebClient.Builder webClientBuilder) {
		this.client = webClientBuilder.baseUrl("http://127.0.0.1:5647").build();
	}

	public Flux<Header> getBill(Long batchNo, String accountNo) {
		String uri = "/bills/search/findByBatchNoAndAcctNo?batchNo=" + batchNo + "&acctNo=" + accountNo;
		return this.client.get().uri(uri).retrieve().bodyToFlux(Header.class);
	}

}
