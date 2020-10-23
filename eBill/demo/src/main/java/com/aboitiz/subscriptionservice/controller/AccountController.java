package com.aboitiz.subscriptionservice.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aboitiz.subscriptionservice.model.Account;
import com.aboitiz.subscriptionservice.model.Contact;
import com.aboitiz.subscriptionservice.model.Subscription;
import com.aboitiz.subscriptionservice.repository.AccountRepository;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class AccountController {

	private final AccountRepository accountRepository;

	public AccountController(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@PostMapping("/accounts")
	private Mono<Account> createAccount(@RequestBody Account account) {
		return Mono.justOrEmpty(accountRepository.save(account));
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "get All Accounts") })
	@GetMapping("/accounts")
	private Flux<Account> getAllAccounts() {
		return Flux.fromIterable(accountRepository.findAll());
	}
	
	@GetMapping("/accounts/{accountId}")
	private Mono<Account> getAccountById(@PathVariable String accountId) {
		return Mono.justOrEmpty(accountRepository.findById(accountId).orElse(null));
	}
	
	@GetMapping("/accounts/{accountId}/contacts")
	private Flux<Contact> getContactsByAccountId(@PathVariable String accountId) {
		Optional<Account> account = accountRepository.findById(accountId);

		if (!account.isPresent()) {
			return Flux.empty();
		}

		return Flux.fromIterable(account.orElse(null).getContactList());
	}
	
	@GetMapping("/accounts/{accountId}/subscriptions")
	private Flux<Subscription> getSubscriptionsByAccountId(@PathVariable String accountId) {
		Optional<Account> account = accountRepository.findById(accountId);

		if (!account.isPresent()) {
			return Flux.empty();
		}

		return Flux.fromIterable(account.orElse(null).getSubscriptionList());
	}
	
	@GetMapping("/accounts/search/findBySubscription")
	private Flux<Account> findBySubscription(@RequestParam String subscriptionTypeCode) {
		return Flux
				.fromIterable(accountRepository.findBySubscriptionList_subscriptionType_typeCode(subscriptionTypeCode));
	}

	@GetMapping("/accounts/search/findByContactValue")
	private Flux<Account> findByContactValue(@RequestParam String value) {
		return Flux.fromIterable(accountRepository.findByContactList_value(value));
	}
	
}
