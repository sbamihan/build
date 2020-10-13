package com.aboitiz.subscriptionservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aboitiz.subscriptionservice.model.Account;
import com.aboitiz.subscriptionservice.repository.AccountRepository;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

@RestController
@Log4j2
@RequestMapping("/accounts/search")
public class AccountSearchController {

	AccountRepository accountRepository;

	public AccountSearchController(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@GetMapping("/findByTypeCode")
	Flux<Account> findByTypeCode(@RequestParam("typeCode") String typeCode) {
		log.info("invoking /search/findByTypeCode?typeCode={}", typeCode);

		return Flux.fromIterable(accountRepository.findByAccountSubscriptions_subscriptionType_typeCode(typeCode));
	}

	@GetMapping("/findByAccountIdAndTypeCode")
	Flux<Account> findByAccountIdAndTypeCode(@RequestParam("accountId") String accountId,
			@RequestParam("typeCode") String typeCode) {
		log.info("invoking /search/findByAccountIdAndTypeCode?accountId={}&typeCode={}", accountId, typeCode);

		return Flux.fromIterable(accountRepository
				.findByAccountIdAndAccountSubscriptions_subscriptionType_typeCode(accountId, typeCode));
	}

}
