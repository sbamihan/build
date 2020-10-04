package com.aboitiz.billtransporter.service;

import org.springframework.stereotype.Service;

import com.aboitiz.billtransporter.model.Bill;
import com.aboitiz.billtransporter.repository.BillRepository;

import reactor.core.publisher.Flux;

@Service
public class BillService {

	private final BillRepository billRepository;

	public BillService(BillRepository billRepository) {
		this.billRepository = billRepository;
	}

	public Flux<Bill> getBill(Long batchNo) {
		return Flux.fromIterable(billRepository.findByBatchNo(batchNo));
	}

}
