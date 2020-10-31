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
		return flux -> flux.flatMap(e -> billService.countBills(e)
					.doOnNext(c -> log.info("{} accounts", c))
					.filter(c -> c.longValue() > 0)
					.thenMany(subscriptionService.getAccounts(e))
					.delayElements(ofMillis(5))
					.flatMap(a -> this.getBills(e, a))
					.flatMap(billService::save)
					.doOnNext(b -> log.info("Bill [{}, {}] saved", e.getDuCode(), b.getAltBillId()))
					.collectList()
					.zipWith(just(new StagedBillEvent()))
					.map(tuple -> {
						StagedBillEvent sbe = tuple.getT2();
						int size = tuple.getT1().size();
						sbe.setCount(size);
						return sbe;
					}).doOnNext(sbe -> log.info("{} bills staged", sbe.getCount()))
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
