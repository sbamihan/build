package com.aboitiz.billretriever.controller;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aboitiz.billretriever.model.Bill;
import com.aboitiz.billretriever.repository.BillRepository;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/bills/search")
@Log4j2
public class BillSearchController {

	BillRepository billRepository;

	public BillSearchController(BillRepository billRepository) {
		this.billRepository = billRepository;
	}
	
	@GetMapping("/findTop12ByAcctNo")
	List<Bill> findTop12ByAcctNo(@RequestParam("acctNo") String acctNo) {
		List<Bill> list = this.billRepository.findTop12ByAcctNoOrderByBillDateDesc(acctNo);

		if (!list.isEmpty()) {
			log.info("findTop12ByAcctNo({}) - found {}", acctNo, list.size());
		}

		return list;
	}

	@GetMapping("/findByBillDate")
	List<Bill> findByBillDate(@RequestParam("billDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date billDate) {
		List<Bill> list = this.billRepository.findByBillDate(billDate);

		if (!list.isEmpty()) {
			log.info("findByBillDate({}) - found", billDate);
		}

		return list;
	}

	@GetMapping("/findByBillDateAndAcctNo")
	List<Bill> findByBillDateAndAcctNo(@RequestParam("billDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date billDate,
			@RequestParam("acctNo") String acctNo) {
		List<Bill> list = this.billRepository.findByBillDateAndAcctNo(billDate, acctNo);

		if (!list.isEmpty()) {
			log.info("findByBillDateAndAcctNo({},{}) - found", billDate, acctNo);
		}

		return list;
	}

	@GetMapping("/findByTranNo")
	List<Bill> findByTranNo(@RequestParam("tranNo") Long tranNo) {
		List<Bill> list = this.billRepository.findByTranNo(tranNo);

		if (!list.isEmpty()) {
			log.info("findByTranNo({}) - found", tranNo);
		}

		return list;
	}

	@GetMapping("/findByBatchNo")
	List<Bill> findByBatchNo(@RequestParam("batchNo") Long batchNo) {
		List<Bill> list = this.billRepository.findByBatchNo(batchNo);

		if (!list.isEmpty()) {
			log.info("findByBatchNo({}) - found", batchNo);
		}

		return list;
	}

	@GetMapping("/findByBatchNoAndAcctNo")
	List<Bill> findByBatchNoAndAcctNo(@RequestParam("batchNo") Long batchNo, @RequestParam("acctNo") String acctNo) {
		List<Bill> list = this.billRepository.findByBatchNoAndAcctNo(batchNo, acctNo);

		if (!list.isEmpty()) {
			log.info("findByBatchNoAndAcctNo({},{}) - found", batchNo, acctNo);
		}

		return list;
	}

}
