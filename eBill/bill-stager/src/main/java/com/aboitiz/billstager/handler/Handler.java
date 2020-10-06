package com.aboitiz.billstager.handler;

import static reactor.core.publisher.Flux.just;

import java.time.Duration;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.aboitiz.billstager.model.Bill;
import com.aboitiz.billstager.model.ExtractedBillEvent;
import com.aboitiz.billstager.model.StagedBillEvent;
import com.aboitiz.billstager.service.BillService;
import com.aboitiz.billstager.service.SubscriptionService;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

@Component
@Log4j2
public class Handler {

	private static final String SUBSCRIPTION_TYPE = "EBIL";

	private final BillService billService;
	private final SubscriptionService subscriptionService;

	public Handler(BillService billService, SubscriptionService subscriptionService) {
		this.billService = billService;
		this.subscriptionService = subscriptionService;
	}

	@Bean
	Function<Flux<ExtractedBillEvent>, Flux<StagedBillEvent>> stageBill() {
		return flux -> flux.flatMap(event -> {
			return just(new StagedBillEvent(event)).zipWith(subscriptionService.findByTypeCode(SUBSCRIPTION_TYPE)
					.delayElements(Duration.ofMillis(7)).flatMap(acct -> {
						Flux<Bill> billFlux = billService
								.getBill(event.getDuCode(), event.getBatchNo(), acct.getAccountId()).map(bill -> {
									bill.setContacts(acct.getAccountContacts());
									bill.setUuid(event.getUuid());
									return bill;
								}).onErrorResume(e -> Flux.empty());
						return billService.saveAll(billFlux);
					}).collectList()).map(tuple -> {
						StagedBillEvent staged = tuple.getT1();
						int size = tuple.getT2().size();
						staged.setCount(size);

						log.info("{} bills staged", size);
						return staged;
					});
		});
	}

}
