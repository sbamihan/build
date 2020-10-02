package com.aboitiz.billstager.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
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

	@Bean
	Function<Flux<ExtractedBillEvent>, Flux<StagedBillEvent>> stageBill() {
		return flux -> flux.flatMap(m -> {
			subscriptionService.getAccounts().flatMap(a -> {
				return billService.saveAll(billService.getBill(m.getBatchNo(), a.getAcctId()));
			}).subscribe(b -> {
				SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
				log.info("Bill for [Batch:{}, Account:{}, Month:{}, Name:{}] saved!", b.getBatchNo(), b.getAcctNo(), sdf.format(b.getBillMonth()), b.getCustomerName());
			});

			StagedBillEvent staged = new StagedBillEvent();
			staged.setUuid(m.getUuid());
			staged.setBatchNo(m.getBatchNo());
			staged.setCreDttm(new Date());
			return Flux.just(staged);
		});
	}

}
