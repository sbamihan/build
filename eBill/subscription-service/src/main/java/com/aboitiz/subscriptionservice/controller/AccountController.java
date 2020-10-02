package com.aboitiz.subscriptionservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aboitiz.subscriptionservice.model.Account;
import com.aboitiz.subscriptionservice.repository.AccountRepository;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/accounts")
@Log4j2
public class AccountController {

	AccountRepository accountRepository;

	public AccountController(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@GetMapping
	List<Account> findAllAccounts() {
		log.info("getting all accounts...");
		List<Account> list = accountRepository.findAll();
		log.info("found {}", list.size());
		
		return list;
	}

}
