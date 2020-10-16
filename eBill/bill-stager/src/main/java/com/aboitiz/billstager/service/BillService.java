package com.aboitiz.billstager.service;

import static java.time.Duration.ofMillis;
import static reactor.util.retry.Retry.backoff;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aboitiz.billstager.config.ClientConfig;
import com.aboitiz.billstager.exception.RetrieveBillTimeoutException;
import com.aboitiz.billstager.model.Bill;
import com.aboitiz.billstager.repository.BillRepository;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

	public Mono<Long> countBills(String duCode, Long batchNo) {
		String uri = "/" + duCode + "/bills/countByBatchNo?batchNo=" + batchNo;
		return this.client.get().uri(uri).retrieve().bodyToMono(Long.class);
	}

	public Flux<Bill> getBills(String duCode, Long batchNo) {
		String uri = "/" + duCode + "/bills/search/findByBatchNo?batchNo=" + batchNo;
		return this.client.get().uri(uri).retrieve().bodyToFlux(Bill.class);
	}

	public Flux<Bill> getBills(String duCode, Long batchNo, String accountNo) {
		String uri = "/" + duCode + "/bills/search/findByBatchNoAndAcctNo?batchNo=" + batchNo + "&acctNo=" + accountNo;

		return this.client.get().uri(uri).retrieve().bodyToFlux(Bill.class)
				.retryWhen(backoff(30, ofMillis(100)).jitter(0.5d).doAfterRetry(rs -> {
					log.info("Retried {}: getBill({}, {}, {}) at {}", rs.totalRetries(), duCode, batchNo, accountNo,
							LocalDateTime.now());
				}).onRetryExhaustedThrow((retryBackoffSpec,
						retrySignal) -> new RetrieveBillTimeoutException("Timeout reached while retrieving bill for ["
								+ duCode + ", " + batchNo + ", " + accountNo + "]")))
				.onErrorResume(fallback -> {
					log.info(fallback.getMessage());
					return Flux.empty();
				});
	}

	public Flux<Bill> saveAll(Flux<Bill> billFlux) {
		return billRepository.saveAll(billFlux);
	}

}
