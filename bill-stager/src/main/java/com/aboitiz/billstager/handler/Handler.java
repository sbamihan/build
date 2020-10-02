package com.aboitiz.billstager.handler;

import java.util.Date;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.aboitiz.billstager.model.ExtractedBill;
import com.aboitiz.billstager.model.StagedBill;
import com.aboitiz.billstager.service.BillService;
import com.aboitiz.billstager.service.SubscriptionService;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

@Component
@Log4j2
public class Handler {

	private final BillService billService;
	private final SubscriptionService subscriptionService;

	public Handler(BillService billService, SubscriptionService subscriptionService) {
		this.billService = billService;
		this.subscriptionService = subscriptionService;
	}

	@Bean
	Function<Flux<ExtractedBill>, Flux<StagedBill>> stageBill() {
		return flux -> flux.flatMap(m -> {
			subscriptionService.getAccounts().flatMap(a -> {
				return billService.getBill(m.getBatchNo(), a.getAcctId());
			}).subscribe(log::info);

			StagedBill staged = new StagedBill(m.getBatchNo(), new Date());
			return Flux.just(staged);
		});
	}

}
