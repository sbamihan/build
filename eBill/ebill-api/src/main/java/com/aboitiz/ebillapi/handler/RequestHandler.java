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

import com.aboitiz.ebillapi.model.BillDeliveryStatus;
import com.aboitiz.ebillapi.model.ExtractedBill;
import com.aboitiz.ebillapi.model.ExtractedBillEvent;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class RequestHandler {

	EmitterProcessor<ExtractedBillEvent> extractedBillProcessor = EmitterProcessor.create();
	EmitterProcessor<BillDeliveryStatus> billDeliveryStatusProcessor = EmitterProcessor.create();

	public Mono<ServerResponse> createExtractedBillEvent(ServerRequest serverRequest) {
		log.info("publishing ExtractedBill event...");

		return serverRequest.bodyToMono(ExtractedBill.class).flatMap(body -> {
			ExtractedBillEvent event = new ExtractedBillEvent();
			event.setUuid(randomUUID().toString());
			event.setDuCode(body.getDuCode());
			event.setBatchNo(body.getBatchNo());
			event.setAccountId(body.getAccountId());
			event.setCreDttm(new Date());

			extractedBillProcessor.onNext(event);
			return Mono.just(event);
		}).doOnSuccess(event -> {
			log.info("ExtractedBill event {} published", event.getUuid());
		}).flatMap(serverResponse -> created(create("/events/" + serverResponse.getUuid()))
				.contentType(APPLICATION_JSON).bodyValue(serverResponse))
				.switchIfEmpty(ServerResponse.badRequest().build());
	}

	public Mono<ServerResponse> createBillDeliveryStatusEvent(ServerRequest serverRequest) {
		log.info("publishing BillDeliveryStatus event...");

		return serverRequest.bodyToMono(BillDeliveryStatus.class).flatMap(body -> {
			billDeliveryStatusProcessor.onNext(body);
			return Mono.just(body);
		}).doOnSuccess(event -> {
			log.info("BillDeliveryStatus event {} published!", event.getRcvd_transid());
		}).flatMap(serverResponse -> created(create(serverResponse.getShort_url())).contentType(APPLICATION_JSON)
				.bodyValue(serverResponse)).switchIfEmpty(ServerResponse.badRequest().build());
	}

	@Bean
	public Supplier<Flux<ExtractedBillEvent>> publishExtractedBillEvent() {
		return () -> extractedBillProcessor;
	}

	@Bean
	public Supplier<Flux<BillDeliveryStatus>> publishBillDeliveryStatusEvent() {
		return () -> billDeliveryStatusProcessor;
	}

}
