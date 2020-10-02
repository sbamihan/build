package com.aboitiz.ebillapi.handler;

import static java.net.URI.create;
import static java.util.UUID.randomUUID;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.created;

import java.util.Date;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.aboitiz.ebillapi.model.ExtractedBill;
import com.aboitiz.ebillapi.model.ExtractedBillEvent;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class RequestHandler {

	EmitterProcessor<ExtractedBillEvent> processor = EmitterProcessor.create();

	public Mono<ServerResponse> createExtractedBillEvent(ServerRequest serverRequest) {
		log.info("creating event...");
		return serverRequest.bodyToMono(ExtractedBill.class).flatMap(m -> {
			ExtractedBillEvent event = new ExtractedBillEvent();
			event.setUuid(randomUUID().toString());
			event.setBatchNo(m.getBatchNo());
			event.setCreDttm(new Date());
			processor.onNext(event);
			return Mono.just(event);
		}).flatMap(serverResponse -> created(create("/events/" + serverResponse.getUuid()))
				.contentType(APPLICATION_JSON).bodyValue(serverResponse))
				.switchIfEmpty(ServerResponse.badRequest().build());
	}

	@Bean
	public Supplier<Flux<ExtractedBillEvent>> publishExtractedBillEvent() {
		return () -> processor;
	}

}
