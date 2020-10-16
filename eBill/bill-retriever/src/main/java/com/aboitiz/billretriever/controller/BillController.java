package com.aboitiz.billretriever.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aboitiz.billretriever.repository.BillRepository;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/bills")
@Log4j2
public class BillController {

	BillRepository billRepository;

	public BillController(BillRepository billRepository) {
		this.billRepository = billRepository;
	}
	
	@GetMapping("/countByBatchNo")
	Long countByBatchNo(@RequestParam("batchNo") Long batchNo) {
		Long count = this.billRepository.countByBatchNo(batchNo);
		log.info("countByBatchNo({}) - found {}", batchNo, count);
		
		return count;
	}

}
