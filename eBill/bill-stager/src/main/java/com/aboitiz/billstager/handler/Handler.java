package com.aboitiz.billstager.handler;

import static reactor.core.publisher.Flux.just;

import java.text.SimpleDateFormat;
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
		return flux -> flux.flatMap(event -> {
			return just(new StagedBillEvent(event)).zipWith(subscriptionService.getAccounts().flatMap(a -> {
				Flux<Bill> billFlux = billService.getBill(event.getBatchNo(), a.getAcctId()).map(m -> {
					Collection<Contact> contactCollection = new ArrayList<>();
					contactCollection.add(new Contact(a.getAcctId(), "email", a.getEmail()));
					m.setContactCollection(contactCollection);
					return m;
				});
				return billService.saveAll(billFlux);
			}).map(b -> {
				SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
				log.info("Bill for [batchNo={}, acctNo={}, billMonth={}, customerName={}] saved!", b.getBatchNo(),
						b.getAcctNo(), sdf.format(b.getBillMonth()), b.getCustomerName());
				return b;
			}).collectList()).map(tuple -> {
				StagedBillEvent staged = tuple.getT1();
				staged.setCount(tuple.getT2().size());
				return staged;
			});
		});
	}

}
