package com.aboitiz.billtransporter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aboitiz.billtransporter.model.Bill;
import com.aboitiz.billtransporter.repository.BillRepository;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/bills/staged")
public class BillController {

	private BillRepository billRepository;

	public BillController(BillRepository billRepository) {
		this.billRepository = billRepository;
	}

	@GetMapping
	public Flux<Bill> findAll() {
		return billRepository.findAll();
	}

	@GetMapping("/findByUuid")
	public Flux<Bill> findByUuid(@RequestParam("uuid") String uuid) {
		return billRepository.findByUuid(uuid);
	}
	
}
