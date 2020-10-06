package com.aboitiz.billstager.controller;

import org.springframework.web.bind.annotation.RestController;

import com.aboitiz.billstager.repository.BillRepository;

@RestController
public class BillController {

	private final BillRepository billRepository;

	public BillController(BillRepository billRepository) {
		this.billRepository = billRepository;
	}
	
	

}
