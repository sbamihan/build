package com.aboitiz.billservice;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.aboitiz.billservice.model.BillSet;
import com.aboitiz.billservice.model.Header;
import com.aboitiz.billservice.repository.HeaderRepository;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class BillHandler {

	HeaderRepository headerRepository;

	public BillHandler(HeaderRepository headerRepository) {
		this.headerRepository = headerRepository;
	}

	@Bean
	Function<Flux<BillSet>, Flux<String>> stageBill() {
		return flux -> flux.flatMap(bs -> {
			List<Header> headerList = headerRepository.findByBatchNo(bs.getBatchNo());
			Flux<Header> headerFlux = Mono.just(headerList).flatMapMany(Flux::fromIterable);
			return headerFlux;
		}).map(m -> m.getAcctNo());
	}

	@Bean
	public Consumer<String> displayStagedBill() {
		return s -> Flux.just(s).subscribe(log::info);
	}

}
