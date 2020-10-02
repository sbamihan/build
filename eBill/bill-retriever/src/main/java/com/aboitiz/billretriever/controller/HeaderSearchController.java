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
		log.info("findByBillDate({})", billDate);
		return this.headerRepository.findByBillDate(billDate);
	}

	@GetMapping("/findByBillDateAndAcctNo")
	List<Header> findByBillDateAndAcctNo(
			@RequestParam("billDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date billDate,
			@RequestParam("acctNo") String acctNo) {
		log.info("findByBillDateAndAcctNo({},{})", billDate, acctNo);
		return this.headerRepository.findByBillDateAndAcctNo(billDate, acctNo);
	}

	@GetMapping("/findByTranNo")
	List<Header> findByTranNo(@RequestParam("tranNo") Long tranNo) {
		log.info("findByTranNo({})", tranNo);
		return this.headerRepository.findByTranNo(tranNo);
	}

	@GetMapping("/findByBatchNo")
	List<Header> findByBatchNo(@RequestParam("batchNo") Long batchNo) {
		log.info("findByBatchNo({})", batchNo);
		return this.headerRepository.findByBatchNo(batchNo);
	}

	@GetMapping("/findByBatchNoAndAcctNo")
	List<Header> findByBatchNoAndAcctNo(@RequestParam("batchNo") Long batchNo, @RequestParam("acctNo") String acctNo) {
		log.info("findByBatchNoAndAcctNo({},{})", batchNo, acctNo);
		return this.headerRepository.findByBatchNoAndAcctNo(batchNo, acctNo);
	}

}
