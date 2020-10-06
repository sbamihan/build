package com.aboitiz.billstager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aboitiz.billstager.config.ClientConfig;
import com.aboitiz.billstager.model.Bill;
import com.aboitiz.billstager.repository.BillRepository;

import reactor.core.publisher.Flux;

@Service
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
		return this.client.get().uri(uri).retrieve().bodyToFlux(Bill.class);
	}

	public Flux<Bill> saveAll(Flux<Bill> billFlux) {
		return billRepository.saveAll(billFlux);
	}

}
