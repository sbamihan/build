package com.aboitiz.subscriptionservice.service;

import com.aboitiz.subscriptionservice.model.Subscription;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SubscriptionService {

	Flux<Subscription> getAllSubscriptions();

	Mono<Subscription> getSubscriptionById(Integer subscriptionById);

	Mono<Subscription> save(Subscription subscription);

}
