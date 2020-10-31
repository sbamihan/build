package com.aboitiz.billtransporter.service;

import static java.time.Duration.ofMillis;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static reactor.core.publisher.Mono.just;
import static reactor.util.retry.Retry.backoff;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aboitiz.billtransporter.config.ClientConfig;
import com.aboitiz.billtransporter.exception.TransportBillTimeoutException;
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
		String uri = clientConfig.getPrimaryClientCallbackEndpoint();

		return this.client.post().uri(uri)
				.contentType(APPLICATION_JSON).body(just(payload), Payload.class)
				.retrieve()
				.bodyToMono(String.class)
				.retryWhen(backoff(30, ofMillis(100)).jitter(0.5d).doAfterRetry(rs -> {
					log.info("Retried {} at {}", rs.totalRetries(), LocalDateTime.now());
				}).onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> new TransportBillTimeoutException(
						"Timeout reached while sending bill [" + "] to client at " + uri)))
				.onErrorResume(fallback -> {
					log.info(fallback.getMessage());
					return Mono.empty();
				});
	}

}
