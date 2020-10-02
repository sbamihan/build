package com.aboitiz.subscriptionservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aboitiz.subscriptionservice.model.Account;
import com.aboitiz.subscriptionservice.repository.AccountRepository;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	AccountRepository accountRepository;

	public AccountController(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@GetMapping
	List<Account> findAllAccounts() {
		return accountRepository.findAll();
	}

}
