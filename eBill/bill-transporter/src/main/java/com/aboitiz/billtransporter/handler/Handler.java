package com.aboitiz.billtransporter.handler;

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
	Consumer<Flux<StagedBillEvent>> trasportBill() {
		return flux -> flux.flatMap(sb -> {
			return transportService.sendBill(billService.getBill(sb.getBatchNo())).doOnSubscribe(log::info);
		});
	}

}
