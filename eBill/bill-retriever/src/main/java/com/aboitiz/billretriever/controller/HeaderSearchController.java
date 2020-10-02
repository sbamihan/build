package com.aboitiz.billretriever.controller;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aboitiz.billretriever.model.Header;
import com.aboitiz.billretriever.repository.HeaderRepository;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/bills/search")
@Log4j2
public class HeaderSearchController {

	HeaderRepository headerRepository;

	public HeaderSearchController(HeaderRepository headerRepository) {
		this.headerRepository = headerRepository;
	}

	@GetMapping("/findByBillDate")
	List<Header> findByBillDate(@RequestParam("billDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date billDate) {
		List<Header> list = this.headerRepository.findByBillDate(billDate);
		log.info("findByBillDate({}) - {}", billDate, list.isEmpty() ? "not found" : "found");

		return list;
	}

	@GetMapping("/findByBillDateAndAcctNo")
	List<Header> findByBillDateAndAcctNo(
			@RequestParam("billDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date billDate,
			@RequestParam("acctNo") String acctNo) {
		List<Header> list = this.headerRepository.findByBillDateAndAcctNo(billDate, acctNo);
		log.info("findByBillDateAndAcctNo({},{}) - {}", billDate, acctNo, list.isEmpty() ? "not found" : "found");

		return list;
	}

	@GetMapping("/findByTranNo")
	List<Header> findByTranNo(@RequestParam("tranNo") Long tranNo) {
		List<Header> list = this.headerRepository.findByTranNo(tranNo);
		log.info("findByTranNo({}) - {}", tranNo, list.isEmpty() ? "not found" : "found");

		return list;
	}

	@GetMapping("/findByBatchNo")
	List<Header> findByBatchNo(@RequestParam("batchNo") Long batchNo) {
		List<Header> list = this.headerRepository.findByBatchNo(batchNo);
		log.info("findByBatchNo({}) - {}", batchNo, list.isEmpty() ? "not found" : "found");

		return list;
	}

	@GetMapping("/findByBatchNoAndAcctNo")
	List<Header> findByBatchNoAndAcctNo(@RequestParam("batchNo") Long batchNo, @RequestParam("acctNo") String acctNo) {
		List<Header> list = this.headerRepository.findByBatchNoAndAcctNo(batchNo, acctNo);
		log.info("findByBatchNoAndAcctNo({},{}) - {}", batchNo, acctNo, list.isEmpty() ? "not found" : "found");

		return list;
	}

}
