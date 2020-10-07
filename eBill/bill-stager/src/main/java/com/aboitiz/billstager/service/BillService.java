package com.aboitiz.billstager.service;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aboitiz.billstager.config.ClientConfig;
import com.aboitiz.billstager.model.Bill;
import com.aboitiz.billstager.repository.BillRepository;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

@Service
@Log4j2
public class BillService {

	private final ClientConfig config;
	private final WebClient client;
	private final BillRepository billRepository;

	public BillService(ClientConfig config, BillRepository billRepository, WebClient.Builder webClientBuilder) {
		this.config = config;
		this.billRepository = billRepository;
		this.client = webClientBuilder.baseUrl(this.config.getBillServiceUrl()).build();
	}

	public Flux<Bill> getBill(String duCode, Long batchNo) {
		String uri = "/" + duCode + "/bills/search/findByBatchNo?batchNo=" + batchNo;
		return this.client.get().uri(uri).retrieve().bodyToFlux(Bill.class);
	}

	public Flux<Bill> getBill(String duCode, Long batchNo, String accountNo) {
		String uri = "/" + duCode + "/bills/search/findByBatchNoAndAcctNo?batchNo=" + batchNo + "&acctNo=" + accountNo;
		return this.client.get().uri(uri)
				.retrieve()
				.bodyToFlux(Bill.class)
				.retryWhen(Retry.backoff(3, Duration.ofSeconds(5)).jitter(0d).doAfterRetry(retrySignal -> {
					log.info("Retried {}: getBill({}, {}, {})", retrySignal.totalRetries(), duCode, batchNo, accountNo);
				}).onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> new TimeoutException()));
	}

	public Flux<Bill> saveAll(Flux<Bill> billFlux) {
		return billRepository.saveAll(billFlux);
	}

}
