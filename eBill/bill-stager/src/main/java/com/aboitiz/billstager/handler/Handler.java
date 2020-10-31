package com.aboitiz.billstager.handler;

import static java.time.Duration.ofMillis;
import static reactor.core.publisher.Mono.just;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.aboitiz.billstager.model.Account;
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

	private final BillService billService;
	private final SubscriptionService subscriptionService;

	public Handler(BillService billService, SubscriptionService subscriptionService) {
		this.billService = billService;
		this.subscriptionService = subscriptionService;
	}

	@Bean
	Function<Flux<ExtractedBillEvent>, Flux<StagedBillEvent>> stageBill() {
		return flux -> flux.flatMap(extractedBillEvent -> billService.countBills(extractedBillEvent)
					.doOnNext(count -> log.info("{} accounts", count))
					.filter(count -> count.longValue() > 0)
					.thenMany(subscriptionService.getAccounts(extractedBillEvent))
					.delayElements(ofMillis(5))
					.flatMap(account -> this.getBills(extractedBillEvent, account))
					.flatMap(billService::save)
					.doOnNext(bill -> log.info("Bill [{}, {}] saved", extractedBillEvent.getDuCode(), bill.getBillNo()))
					.collectList()
					.zipWith(just(new StagedBillEvent(extractedBillEvent)))
					.map(tuple -> {
						StagedBillEvent staged = tuple.getT2();
						int size = tuple.getT1().size();
						staged.setCount(size);
						return staged;
					}).doOnNext(stagedBillEvent -> log.info("{} bills staged", stagedBillEvent.getCount()))
		);
	}
	
	private Flux<Bill> getBills(ExtractedBillEvent event, Account account) {
		return billService.getBills(event.getDuCode(), event.getBatchNo(), account.getAccountId())
				.map(bill -> projectBill(event, account, bill));
	}

	private Bill projectBill(ExtractedBillEvent event, Account account, Bill bill) {
		bill.setUuid(event.getUuid());
		bill.setContacts(account.getContactList());

		return bill;
	}

}
