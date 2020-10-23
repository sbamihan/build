package com.aboitiz.subscriptionservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aboitiz.subscriptionservice.model.Subscription;
import com.aboitiz.subscriptionservice.repository.SubscriptionRepository;

import reactor.core.publisher.Flux;

@RestController
public class SubscriptionController {

	private SubscriptionRepository subscriptionRepository;

	public SubscriptionController(SubscriptionRepository subscriptionRepository) {
		this.subscriptionRepository = subscriptionRepository;
	}

	@GetMapping("/subscriptions")
	private Flux<Subscription> getAllSubscriptions() {
		return Flux.fromIterable(subscriptionRepository.findAll());
	}

}
