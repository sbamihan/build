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
	
	@GetMapping("/findByTranNo")
	List<Header> findByTranNo(@RequestParam("tranNo") Long tranNo) {
		return this.headerRepository.findByTranNo(tranNo);
	}
	
}
