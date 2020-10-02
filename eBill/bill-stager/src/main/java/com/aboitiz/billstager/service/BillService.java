package com.aboitiz.billstager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aboitiz.billstager.model.Bill;
import com.aboitiz.billstager.repository.BillRepository;

import reactor.core.publisher.Flux;

@Service
public class BillService {

	private final WebClient client;
	private final BillRepository billRepository;

	public BillService(WebClient.Builder webClientBuilder, BillRepository billRepository) {
		this.client = webClientBuilder.baseUrl("http://127.0.0.1:5647").build();
		this.billRepository = billRepository;
	}

	public Flux<Bill> getBill(Long batchNo, String accountNo) {
		String uri = "/bills/search/findByBatchNoAndAcctNo?batchNo=" + batchNo + "&acctNo=" + accountNo;
		return this.client.get().uri(uri).retrieve().bodyToFlux(Bill.class);
	}

	public Flux<Bill> saveAll(Flux<Bill> billFlux) {
		return billRepository.saveAll(billFlux);
	}

}
