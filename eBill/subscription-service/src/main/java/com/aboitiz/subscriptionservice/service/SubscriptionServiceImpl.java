package com.aboitiz.subscriptionservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aboitiz.subscriptionservice.model.Subscription;
import com.aboitiz.subscriptionservice.repository.SubscriptionRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

	private final SubscriptionRepository subscriptionRepository;

	public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
		this.subscriptionRepository = subscriptionRepository;
	}

	@Override
	public Flux<Subscription> getAllSubscriptions() {
		return Flux.fromIterable(subscriptionRepository.findAll());
	}

	@Override
	public Mono<Subscription> getSubscriptionById(Integer subscriptionById) {
		return Mono.justOrEmpty(subscriptionRepository.findById(subscriptionById));
	}

	@Override
	public Mono<Subscription> save(Subscription subscription) {
		return Mono.just(subscriptionRepository.save(subscription));
	}

}
