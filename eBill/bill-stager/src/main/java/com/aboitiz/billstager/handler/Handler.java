package com.aboitiz.billstager.handler;

import java.time.Duration;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

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

//	@Bean
//	Function<Flux<ExtractedBillEvent>, Flux<StagedBillEvent>> stageBill() {
//		return flux -> flux.flatMap(event -> {
//			StagedBillEvent staged = new StagedBillEvent();
//			staged.setDuCode(event.getDuCode());
//			staged.setBatchNo(event.getBatchNo());
//			staged.setCreDttm(new Date());
//			staged.setCount(0);
//			
//			log.info("publishing event {}", staged);
//			return Flux.just(staged);
//		});
//	}

	@Bean
	Function<Flux<ExtractedBillEvent>, Flux<StagedBillEvent>> stageBill() {
		return flux -> flux.flatMap(event -> {
			return Flux.just(new StagedBillEvent(event)).zipWith(
					subscriptionService.getAccounts(event).delayElements(Duration.ofMillis(100)).flatMap(acct -> {
						return billService.saveAll(billService
								.getBills(event.getDuCode(), event.getBatchNo(), acct.getAccountId()).map(bill -> {
									bill.setContacts(acct.getAccountContacts());
									bill.setUuid(event.getUuid());
									return bill;
								}).map(b -> {
									log.info("saving bill {} to DB...", b.getBillNo());
									return b;
								}).onErrorResume(e -> Flux.empty()));
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
