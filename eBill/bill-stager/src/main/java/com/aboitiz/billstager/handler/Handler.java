package com.aboitiz.billstager.handler;

import static reactor.core.publisher.Flux.just;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.aboitiz.billstager.model.Bill;
import com.aboitiz.billstager.model.Contact;
import com.aboitiz.billstager.model.ExtractedBillEvent;
import com.aboitiz.billstager.model.StagedBillEvent;
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
	Function<Flux<ExtractedBillEvent>, Flux<StagedBillEvent>> stageBill() {
		log.info("staging bills...");
		return flux -> flux.flatMap(event -> {
			return just(new StagedBillEvent(event)).zipWith(subscriptionService.getAccounts().flatMap(acct -> {
				Flux<Bill> billFlux = billService.getBill(event.getBatchNo(), acct.getAcctId()).map(bill -> {
					Collection<Contact> contactCollection = new ArrayList<>();
					contactCollection.add(new Contact(acct.getAcctId(), "email", acct.getEmail()));
					bill.setContactCollection(contactCollection);
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
