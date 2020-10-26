package com.aboitiz.subscriptionservice.handler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.accepted;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.aboitiz.subscriptionservice.model.Subscription;
import com.aboitiz.subscriptionservice.service.SubscriptionService;

import reactor.core.publisher.Mono;

@Component
public class SubscriptionHandler {

	private final SubscriptionService subscriptionService;

	public SubscriptionHandler(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}

	public Mono<ServerResponse> getAllSubscriptions(ServerRequest request) {
		return ok().contentType(APPLICATION_JSON).body(subscriptionService.getAllSubscriptions(), Subscription.class);
	}

	public Mono<ServerResponse> getSubscriptionById(ServerRequest serverRequest) {
		Integer subscriptionId = Integer.parseInt(serverRequest.pathVariable("subscriptionId"));

		return subscriptionService.getSubscriptionById(subscriptionId)
				.flatMap(request -> ok().contentType(APPLICATION_JSON).bodyValue(request))
				.switchIfEmpty(notFound().build());
	}

	public Mono<ServerResponse> patchSubscription(ServerRequest serverRequest) {
		Integer subscriptionId = Integer.parseInt(serverRequest.pathVariable("subscriptionId"));
		Optional<String> optionalStatFlg = serverRequest.queryParam("statFlg");
		Optional<String> optionalSubscribe = serverRequest.queryParam("subscribe");

		return subscriptionService.getSubscriptionById(subscriptionId).map(subscription -> {
			optionalStatFlg.ifPresent(subscription::setStatFlg);
			optionalSubscribe.ifPresent(subscribe -> subscription.setSubscribe(subscribe.charAt(0)));
			return subscription;
		}).map(subscriptionService::save)
				.flatMap(request -> accepted().contentType(APPLICATION_JSON).body(request, Subscription.class))
				.switchIfEmpty(notFound().build());

	}

}
