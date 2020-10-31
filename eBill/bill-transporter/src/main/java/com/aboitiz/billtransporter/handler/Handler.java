package com.aboitiz.billtransporter.handler;

import static reactor.core.publisher.Mono.just;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.aboitiz.billtransporter.model.StagedBillEvent;
import com.aboitiz.billtransporter.service.BillService;
import com.aboitiz.billtransporter.service.TransportService;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

@Component
@Log4j2
public class Handler {

	private final TransportService transportService;
	private final BillService billService;

	public Handler(TransportService transportService, BillService billService) {
		this.transportService = transportService;
		this.billService = billService;
	}

	@Bean
	Consumer<Flux<StagedBillEvent>> transportBill() {
		return flux -> flux.flatMap(event -> {
			log.info("received event {}", event);
			return billService.getBills(event.getUuid())
					.doOnNext(bill -> log.info("sending bill [{}, {}]", event.getDuCode(), bill.getBillNo()))
					.flatMap(transportService::sendBill)
					.doOnNext(log::info);
		}).onErrorResume(e -> just("ERROR: " + e.getMessage()))
		.subscribe(log::info);
	}

}
