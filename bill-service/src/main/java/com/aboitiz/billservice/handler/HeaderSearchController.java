package com.aboitiz.billservice;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aboitiz.billservice.model.Header;
import com.aboitiz.billservice.repository.HeaderRepository;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/bills")
public class HeaderController {

	HeaderRepository headerRepository;

	public HeaderController(HeaderRepository headerRepository) {
		this.headerRepository = headerRepository;
	}

	@GetMapping("/findByBillDate")
	List<Header> findByBillDate(@RequestParam("billDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date billDate) {
		return this.headerRepository.findByBillDate(billDate);
	}

	@GetMapping("/findByBillDateAndAcctNo")
	List<Header> findByBillDateAndAcctNo(
			@RequestParam("billDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date billDate,
			@RequestParam("acctNo") String acctNo) {
		return this.headerRepository.findByBillDateAndAcctNo(billDate, acctNo);
	}

	@GetMapping("/findByTranNo")
	List<Header> findByTranNo(@RequestParam("tranNo") Long tranNo) {
		return this.headerRepository.findByTranNo(tranNo);
	}

	@GetMapping("/findByBatchNo")
	List<Header> findByBatchNo(@RequestParam("batchNo") Long batchNo) {
		return this.headerRepository.findByBatchNo(batchNo);
	}

	@GetMapping("/findByBatchNoAndAcctNo")
	List<Header> findByBatchNoAndAcctNo(@RequestParam("batchNo") Long batchNo, @RequestParam("acctNo") String acctNo) {
		return this.headerRepository.findByBatchNoAndAcctNo(batchNo, acctNo);
	}

}
