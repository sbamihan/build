package com.aboitiz.billtransporter.service;

import org.springframework.stereotype.Service;

import com.aboitiz.billtransporter.model.Bill;
import com.aboitiz.billtransporter.repository.BillRepository;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

@Service
@Log4j2
public class BillService {

	private final BillRepository billRepository;

	public BillService(BillRepository billRepository) {
		this.billRepository = billRepository;
	}

	public Flux<Bill> getBill(String uuid) {
		log.info("getting bills ...");
		return billRepository.findByUuid(uuid);
	}

}
