package com.aboitiz.billtransporter.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aboitiz.billtransporter.model.Bill;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransportService {

	private final WebClient client;

	public TransportService(WebClient.Builder webClientBuilder) {
		this.client = webClientBuilder.baseUrl("http://127.0.0.1:8080").build();
	}

	public Mono<String> sendBill(Flux<Bill> billFlux) {
		return this.client.post()
				.uri("/ami/test123.php")
				.contentType(MediaType.APPLICATION_JSON)
				.body(billFlux, Bill.class)
				.retrieve()
				.bodyToMono(String.class);
	}

}
