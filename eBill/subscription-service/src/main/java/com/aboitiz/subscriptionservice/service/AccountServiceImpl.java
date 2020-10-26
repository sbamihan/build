package com.aboitiz.subscriptionservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aboitiz.subscriptionservice.model.Account;
import com.aboitiz.subscriptionservice.model.Contact;
import com.aboitiz.subscriptionservice.model.ContactType;
import com.aboitiz.subscriptionservice.model.Subscription;
import com.aboitiz.subscriptionservice.model.SubscriptionType;
import com.aboitiz.subscriptionservice.repository.AccountRepository;
import com.aboitiz.subscriptionservice.repository.ContactRepository;
import com.aboitiz.subscriptionservice.repository.ContactTypeRepository;
import com.aboitiz.subscriptionservice.repository.SubscriptionRepository;
import com.aboitiz.subscriptionservice.repository.SubscriptionTypeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	private final ContactRepository contactRepository;
	private final SubscriptionRepository subscriptionRepository;
	private final ContactTypeRepository contactTypeRepository;
	private final SubscriptionTypeRepository subscriptionTypeRepository;

	public AccountServiceImpl(AccountRepository accountRepository, ContactRepository contactRepository,
			ContactTypeRepository contactTypeRepository, SubscriptionRepository subscriptionRepository,
			SubscriptionTypeRepository subscriptionTypeRepository) {
		this.accountRepository = accountRepository;
		this.contactRepository = contactRepository;
		this.subscriptionRepository = subscriptionRepository;
		this.contactTypeRepository = contactTypeRepository;
		this.subscriptionTypeRepository = subscriptionTypeRepository;
	}

	@Override
	public Flux<Account> getAllAccounts() {
		return Flux.fromIterable(accountRepository.findAll());
	}

	@Override
	public Mono<Account> getAccountById(String accountId) {
		return Mono.justOrEmpty(accountRepository.findById(accountId));
	}

	@Override
	public Mono<Account> save(Account account) {
		return Mono.just(accountRepository.save(account));
	}

	@Override
	public Flux<Account> findBySubscription(String subscriptionTypeCode) {
		return Flux
				.fromIterable(accountRepository.findBySubscriptionList_subscriptionType_typeCode(subscriptionTypeCode));
	}

	@Override
	public Flux<Account> findByAccountIdAndSubscription(String accountId, String subscriptionTypeCode) {
		return Flux.fromIterable(accountRepository
				.findByAccountIdAndSubscriptionList_subscriptionType_typeCode(accountId, subscriptionTypeCode));
	}

	@Override
	public Flux<Account> findByContactValue(String value) {
		return Flux.fromIterable(accountRepository.findByContactList_value(value));
	}

	@Override
	public Flux<Contact> getContactsByAccountId(String accountId) {
		return Flux.fromIterable(accountRepository.findById(accountId).orElse(null).getContactList());
	}

	@Override
	public Flux<Subscription> getSubscriptionsByAccountId(String accountId) {
		return Flux.fromIterable(accountRepository.findById(accountId).orElse(null).getSubscriptionList());
	}

	@Override
	public Mono<Contact> createContactByAccountId(String accountId, Contact contact) {
		Optional<ContactType> contactType = contactTypeRepository.findById(contact.getContactType().getTypeCode());

		return contactType.map(m -> {
			return Mono.justOrEmpty(accountRepository.findById(accountId).map(account -> {
				contact.setAccount(account);
				return contact;
			}).map(contactRepository::save));
		}).orElseThrow(() -> new RuntimeException("Invalid Contact Type!"));
	}

	@Override
	public Mono<Subscription> createSubscriptionByAccountId(String accountId, Subscription subscription) {
		Optional<SubscriptionType> subscriptionType = subscriptionTypeRepository
				.findById(subscription.getSubscriptionType().getTypeCode());

		return subscriptionType.map(m -> {
			return Mono.justOrEmpty(accountRepository.findById(accountId).map(account -> {
				subscription.setAccount(account);
				return subscription;
			}).map(subscriptionRepository::save));
		}).orElseThrow(() -> new IllegalStateException("Invalid Subscription Type!"));
	}

}
