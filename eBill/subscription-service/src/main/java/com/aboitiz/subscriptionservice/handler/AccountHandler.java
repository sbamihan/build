package com.aboitiz.subscriptionservice.handler;

import static java.net.URI.create;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.created;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.aboitiz.subscriptionservice.model.Account;
import com.aboitiz.subscriptionservice.model.Contact;
import com.aboitiz.subscriptionservice.model.Subscription;
import com.aboitiz.subscriptionservice.service.AccountService;

import reactor.core.publisher.Mono;

@Component
public class AccountHandler {

	private final AccountService accountService;

	public AccountHandler(AccountService accountService) {
		this.accountService = accountService;
	}

	public Mono<ServerResponse> getAllAccounts(ServerRequest request) {
		return ok().contentType(APPLICATION_JSON).body(accountService.getAllAccounts(), Account.class);
	}

	public Mono<ServerResponse> getAccountById(ServerRequest serverRequest) {
		return accountService.getAccountById(serverRequest.pathVariable("accountId"))
				.flatMap(serverResponse -> ok().contentType(APPLICATION_JSON).bodyValue(serverResponse))
				.switchIfEmpty(notFound().build()).onErrorResume(fallback -> ServerResponse.badRequest()
						.contentType(MediaType.TEXT_PLAIN).bodyValue(fallback.getLocalizedMessage()));
	}

	public Mono<ServerResponse> createAccount(ServerRequest serverRequest) {
		return serverRequest.bodyToMono(Account.class).flatMap(accountService::save)
				.flatMap(serverResponse -> created(create("/accounts/" + serverResponse.getAccountId()))
						.contentType(APPLICATION_JSON).bodyValue(serverResponse))
				.switchIfEmpty(ServerResponse.badRequest().build())
				.onErrorResume(fallback -> ServerResponse.badRequest().contentType(MediaType.TEXT_PLAIN)
						.bodyValue(fallback.getLocalizedMessage()));
	}

	public Mono<ServerResponse> findByContactValue(ServerRequest serverRequest) {
		return Mono.just(serverRequest.queryParam("value"))
				.map(monoValue -> monoValue.orElseThrow(() -> new RuntimeException("Invalid Parameter!")))
				.flatMap(value -> ok().contentType(APPLICATION_JSON).body(accountService.findByContactValue(value),
						Account.class))
				.onErrorResume(fallback -> ServerResponse.badRequest().contentType(MediaType.TEXT_PLAIN)
						.bodyValue(fallback.getLocalizedMessage()));
	}

	public Mono<ServerResponse> findBySubscriptionType(ServerRequest serverRequest) {
		return Mono.just(serverRequest.queryParam("subscriptionTypeCode"))
				.map(monoTypeCode -> monoTypeCode.orElseThrow(() -> new RuntimeException("Invalid Parameter!")))
				.map(typeCode -> accountService.findBySubscription(typeCode))
				.flatMap(account -> ok().contentType(APPLICATION_JSON).body(account, Account.class))
				.onErrorResume(fallback -> ServerResponse.badRequest().contentType(MediaType.TEXT_PLAIN)
						.bodyValue(fallback.getLocalizedMessage()));
	}

	public Mono<ServerResponse> getAccountByAccountIdAndFindByAndSubscription(ServerRequest serverRequest) {
		String accountId = serverRequest.pathVariable("accountId");

		return Mono.just(serverRequest.queryParam("subscriptionTypeCode"))
				.map(monoTypeCode -> monoTypeCode.orElseThrow(() -> new RuntimeException("Invalid Parameter!")))
				.map(typeCode -> accountService.findByAccountIdAndSubscription(accountId, typeCode))
				.flatMap(account -> ok().contentType(APPLICATION_JSON).body(account, Account.class))
				.onErrorResume(fallback -> ServerResponse.badRequest().contentType(MediaType.TEXT_PLAIN)
						.bodyValue(fallback.getLocalizedMessage()));
	}

	public Mono<ServerResponse> getContactsByAccountId(ServerRequest serverRequest) {
		return accountService.getAccountById(serverRequest.pathVariable("accountId"))
				.map(account -> account.getContactList())
				.flatMap(request -> ok().contentType(APPLICATION_JSON).bodyValue(request))
				.switchIfEmpty(notFound().build()).onErrorResume(fallback -> ServerResponse.badRequest()
						.contentType(MediaType.TEXT_PLAIN).bodyValue(fallback.getLocalizedMessage()));
	}

	public Mono<ServerResponse> getSubscriptionsByAccountId(ServerRequest serverRequest) {
		return accountService.getAccountById(serverRequest.pathVariable("accountId"))
				.map(account -> account.getSubscriptionList())
				.flatMap(request -> ok().contentType(APPLICATION_JSON).bodyValue(request))
				.switchIfEmpty(notFound().build()).onErrorResume(fallback -> ServerResponse.badRequest()
						.contentType(MediaType.TEXT_PLAIN).bodyValue(fallback.getLocalizedMessage()));
	}

	public Mono<ServerResponse> createContactByAccountId(ServerRequest serverRequest) {
		String accountId = serverRequest.pathVariable("accountId");

		return serverRequest.bodyToMono(Contact.class)
				.flatMap(contact -> accountService.createContactByAccountId(accountId, contact))
				.flatMap(serverResponse -> created(create("/contacts/" + serverResponse.getId()))
						.contentType(APPLICATION_JSON).bodyValue(serverResponse))
				.switchIfEmpty(notFound().build()).onErrorResume(fallback -> ServerResponse.badRequest()
						.contentType(MediaType.TEXT_PLAIN).bodyValue(fallback.getLocalizedMessage()));
	}

	public Mono<ServerResponse> createSubscriptionByAccountId(ServerRequest serverRequest) {
		String accountId = serverRequest.pathVariable("accountId");

		return serverRequest.bodyToMono(Subscription.class)
				.flatMap(subscription -> accountService.createSubscriptionByAccountId(accountId, subscription))
				.flatMap(serverResponse -> created(create("/subscriptions/" + serverResponse.getId()))
						.contentType(APPLICATION_JSON).bodyValue(serverResponse))
				.switchIfEmpty(notFound().build()).onErrorResume(fallback -> ServerResponse.badRequest()
						.contentType(MediaType.TEXT_PLAIN).bodyValue(fallback.getLocalizedMessage()));
	}

}
