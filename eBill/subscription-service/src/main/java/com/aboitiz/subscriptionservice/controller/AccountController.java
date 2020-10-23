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
import com.aboitiz.subscriptionservice.repository.ContactRepository;
import com.aboitiz.subscriptionservice.repository.SubscriptionRepository;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Tag(name = "Account Handler", description = "Contains endpoints used for managing the Account.")
public class AccountController {

	private final AccountRepository accountRepository;
	private final ContactRepository contactRepository;
	private final SubscriptionRepository subscriptionRepository;

	public AccountController(AccountRepository accountRepository, ContactRepository contactRepository,
			SubscriptionRepository subscriptionRepository) {
		this.accountRepository = accountRepository;
		this.contactRepository = contactRepository;
		this.subscriptionRepository = subscriptionRepository;
	}

	@GetMapping("/accounts")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Get All Accounts") })
	private Flux<Account> getAllAccounts() {
		return Flux.fromIterable(accountRepository.findAll());
	}

	@PostMapping("/accounts")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Create an Account") })
	private Mono<Account> createAccount(@RequestBody Account account) {
		return Mono.justOrEmpty(accountRepository.save(account));
	}

	@GetMapping("/accounts/search/findBySubscription")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Find Account by Subscription"),
			@ApiResponse(responseCode = "404", description = "Account not found") })
	@Parameter(in = ParameterIn.PATH, name = "subscriptionTypeCode", description = "The code for the type of service the Account is subscribed to.")
	private Flux<Account> findBySubscription(@RequestParam String subscriptionTypeCode) {
		return Flux
				.fromIterable(accountRepository.findBySubscriptionList_subscriptionType_typeCode(subscriptionTypeCode));
	}

	@GetMapping("/accounts/search/findByContactValue")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Find Account by Contact Value"),
			@ApiResponse(responseCode = "404", description = "Account not found") })
	@Parameter(in = ParameterIn.PATH, name = "value", description = "The value of the contact.")
	private Flux<Account> findByContactValue(@RequestParam String value) {
		return Flux.fromIterable(accountRepository.findByContactList_value(value));
	}

	@GetMapping("/accounts/{accountId}")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Get Account by Id"),
			@ApiResponse(responseCode = "404", description = "Account not found") })
	@Parameter(in = ParameterIn.PATH, name = "accountId", description = "Unique identifier of the account.")
	private Mono<Account> getAccountById(@PathVariable String accountId) {
		return Mono.justOrEmpty(accountRepository.findById(accountId).orElse(null));
	}

	@GetMapping("/accounts/{accountId}/contacts")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Get Contacts of the account"),
			@ApiResponse(responseCode = "404", description = "Account not found") })
	@Parameter(in = ParameterIn.PATH, name = "accountId", description = "Unique identifier of the account.")
	private Flux<Contact> getContactsByAccountId(@PathVariable String accountId) {
		Optional<Account> account = accountRepository.findById(accountId);

		if (!account.isPresent()) {
			return Flux.empty();
		}

		return Flux.fromIterable(account.orElse(null).getContactList());
	}

	@GetMapping("/accounts/{accountId}/subscriptions")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Get Subscriptions of the account"),
			@ApiResponse(responseCode = "404", description = "Account not found") })
	@Parameter(in = ParameterIn.PATH, name = "accountId", description = "Unique identifier of the account.")
	private Flux<Subscription> getSubscriptionsByAccountId(@PathVariable String accountId) {
		Optional<Account> account = accountRepository.findById(accountId);

		if (!account.isPresent()) {
			return Flux.empty();
		}

		return Flux.fromIterable(account.orElse(null).getSubscriptionList());
	}

	@PostMapping("/accounts/{accountId}/contacts")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Create Contacts for the account"),
			@ApiResponse(responseCode = "404", description = "Account not found") })
	@Parameter(in = ParameterIn.PATH, name = "accountId", description = "Unique identifier of the account.")
	private Mono<Contact> createContactByAccountId(@PathVariable String accountId, @RequestBody Contact contact) {
		Optional<Account> account = accountRepository.findById(accountId);

		if (!account.isPresent()) {
			return Mono.empty();
		}
		contact.setAccount(account.orElse(null));

		return Mono.justOrEmpty(contactRepository.save(contact));
	}

	@PostMapping("/accounts/{accountId}/subscriptions")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Create Subscriptions for the account"),
			@ApiResponse(responseCode = "404", description = "Account not found") })
	@Parameter(in = ParameterIn.PATH, name = "accountId", description = "Unique identifier of the account.")
	private Mono<Subscription> createSubscriptionByAccountId(@PathVariable String accountId,
			@RequestBody Subscription subscription) {
		Optional<Account> account = accountRepository.findById(accountId);

		if (!account.isPresent()) {
			return Mono.empty();
		}

		return Mono.justOrEmpty(subscriptionRepository.save(subscription));
	}

}
