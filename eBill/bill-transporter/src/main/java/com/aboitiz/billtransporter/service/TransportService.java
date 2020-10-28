package com.aboitiz.billtransporter.service;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aboitiz.billtransporter.config.ClientConfig;
import com.aboitiz.billtransporter.model.Payload;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class TransportService {

	private final WebClient client;
	private final ClientConfig clientConfig;

	public TransportService(WebClient.Builder webClientBuilder, ClientConfig clientConfig) {
		this.client = webClientBuilder.baseUrl(clientConfig.getPrimaryClientServiceBaseUrl()).build();
		this.clientConfig = clientConfig;
	}

	public Mono<String> sendBill(Payload payload) {
		log.info("sending bills for {}", payload.toString());
		
		return Mono.just("data posted");
		
//		return this.client.post().uri(clientConfig.getPrimaryClientCallbackEndpoint())
//				.contentType(MediaType.APPLICATION_JSON).body(Mono.just(payload), Payload.class).retrieve()
//				.bodyToMono(String.class).timeout(Duration.ofMinutes(1));
	}

}
