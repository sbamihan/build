package com.aboitiz.subscriptionservice.service;

import com.aboitiz.subscriptionservice.model.Account;
import com.aboitiz.subscriptionservice.model.Contact;
import com.aboitiz.subscriptionservice.model.Subscription;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

	Flux<Account> getAllAccounts();
	
	Mono<Account> getAccountById(String accountId);
	
	Mono<Account> save(Account account);
	
	Flux<Account> findBySubscription(String subscriptionTypeCode);
	
	Flux<Account> findByAccountIdAndSubscription(String accountId, String subscriptionTypeCode);
	
	Flux<Account> findByContactValue(String value);
	
	Flux<Contact> getContactsByAccountId(String accountId);
	
	Flux<Subscription> getSubscriptionsByAccountId(String accountId);
	
	Mono<Contact> createContactByAccountId(String accountId, Contact contact);
	
	Mono<Subscription> createSubscriptionByAccountId(String accountId, Subscription subscription);
	
}
