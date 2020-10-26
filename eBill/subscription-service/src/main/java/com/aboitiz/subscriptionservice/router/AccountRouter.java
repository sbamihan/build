package com.aboitiz.subscriptionservice.router;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.aboitiz.subscriptionservice.handler.AccountHandler;
import com.aboitiz.subscriptionservice.model.Account;
import com.aboitiz.subscriptionservice.model.Contact;
import com.aboitiz.subscriptionservice.model.Subscription;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Configuration
public class AccountRouter {

	@Bean
	@RouterOperation(operation = @Operation(operationId = "getAllAccounts", description = "Get all accounts.", summary = "Get Accounts.", security = {
			@SecurityRequirement(name = "bearerScheme") }, tags = { "Account" }, responses = {
					@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Account.class))),
					@ApiResponse(responseCode = "401", description = "Unauthorized") }))
	public RouterFunction<ServerResponse> getAllAccountsRoute(AccountHandler accountHandler) {
		return route(GET("/accounts").and(accept(APPLICATION_JSON)), accountHandler::getAllAccounts);
	}

	@Bean
	@RouterOperation(operation = @Operation(operationId = "getAccountById", description = "Get Account by ID.", summary = "Get by ID.", security = {
			@SecurityRequirement(name = "bearerScheme") }, tags = { "Account" }, parameters = {
					@Parameter(in = ParameterIn.PATH, name = "accountId", description = "Unique identifier of the account.") }, responses = {
							@ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = Account.class))),
							@ApiResponse(responseCode = "401", description = "Unauthorized"),
							@ApiResponse(responseCode = "404", description = "Not Found") }))
	public RouterFunction<ServerResponse> getAccountById(AccountHandler accountHandler) {
		return route(GET("/accounts/{accountId}").and(accept(APPLICATION_JSON)), accountHandler::getAccountById);
	}

	@Bean
	@RouterOperation(operation = @Operation(operationId = "createAccount", description = "Create an account", summary = "Create Account.", security = {
			@SecurityRequirement(name = "bearerScheme") }, tags = {
					"Account" }, requestBody = @RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Account.class))), responses = {
							@ApiResponse(responseCode = "201", description = "Ok", content = @Content(schema = @Schema(implementation = Account.class))),
							@ApiResponse(responseCode = "401", description = "Unauthorized"),
							@ApiResponse(responseCode = "403", description = "Forbidden") }))
	public RouterFunction<ServerResponse> createAccountRoute(AccountHandler accountHandler) {
		return route(POST("/accounts").and(accept(APPLICATION_JSON)), accountHandler::createAccount);
	}

	@Bean
	@RouterOperation(operation = @Operation(operationId = "findByContactValue", description = "Find Account by Contact Value.", summary = "Find by Contact.", security = {
			@SecurityRequirement(name = "bearerScheme") }, tags = { "Account" }, parameters = {
					@Parameter(in = ParameterIn.QUERY, required = true, name = "value", description = "The value of the contact.", schema = @Schema(required = true)) }, responses = {
							@ApiResponse(responseCode = "201", description = "Ok", content = @Content(schema = @Schema(implementation = Account.class))),
							@ApiResponse(responseCode = "401", description = "Unauthorized"),
							@ApiResponse(responseCode = "403", description = "Forbidden"),
							@ApiResponse(responseCode = "404", description = "Not Found") }))
	public RouterFunction<ServerResponse> findByContactValueRoute(AccountHandler accountHandler) {
		return route(GET("/accounts/search/findByContactValue").and(accept(APPLICATION_JSON)),
				accountHandler::findByContactValue);
	}

	@Bean
	@RouterOperation(operation = @Operation(operationId = "findBySubscriptionType", description = "Find Account by Subscription Type.", summary = "Find by Subscription.", security = {
			@SecurityRequirement(name = "bearerScheme") }, tags = { "Account" }, parameters = {
					@Parameter(in = ParameterIn.QUERY, required = true, name = "subscriptionTypeCode", description = "The code for the type of service the Account is subscribed to.", schema = @Schema(required = true, allowableValues = {
							"EBIL", "MOAP", "NEWS" })) }, responses = {
									@ApiResponse(responseCode = "201", description = "Ok", content = @Content(schema = @Schema(implementation = Account.class))),
									@ApiResponse(responseCode = "401", description = "Unauthorized"),
									@ApiResponse(responseCode = "403", description = "Forbidden"),
									@ApiResponse(responseCode = "404", description = "Not Found") }))
	public RouterFunction<ServerResponse> findBySubscriptionTypeRoute(AccountHandler accountHandler) {
		return route(GET("/accounts/search/findBySubscriptionType").and(accept(APPLICATION_JSON)),
				accountHandler::findBySubscriptionType);
	}

	@Bean
	@RouterOperation(operation = @Operation(operationId = "findByAccountAndSubscription", description = "Find by Account ID and Subscription.", summary = "Find by Account and Subscription.", security = {
			@SecurityRequirement(name = "bearerScheme") }, tags = { "Account" }, parameters = {
					@Parameter(in = ParameterIn.PATH, required = true, name = "accountId", description = "Unique identifier of the account."),
					@Parameter(in = ParameterIn.QUERY, required = true, name = "subscriptionTypeCode", description = "The code for the type of service the Account is subscribed to.", schema = @Schema(required = true, allowableValues = {
							"EBIL", "MOAP", "NEWS" })) }, responses = {
									@ApiResponse(responseCode = "201", description = "Ok", content = @Content(schema = @Schema(implementation = Account.class))),
									@ApiResponse(responseCode = "401", description = "Unauthorized"),
									@ApiResponse(responseCode = "403", description = "Forbidden"),
									@ApiResponse(responseCode = "404", description = "Not Found") }))
	public RouterFunction<ServerResponse> findByAccountAndSubscriptionRoute(AccountHandler accountHandler) {
		return route(
				GET("/accounts/{accountId}/subscriptions/search/findBySubscriptionType").and(accept(APPLICATION_JSON)),
				accountHandler::getAccountByAccountIdAndFindByAndSubscription);
	}

	@Bean
	@RouterOperation(operation = @Operation(operationId = "getContactsByAccountId", description = "Get Contacts by Account by ID.", summary = "Get Contacts by Account.", security = {
			@SecurityRequirement(name = "bearerScheme") }, tags = { "Account" }, parameters = {
					@Parameter(in = ParameterIn.PATH, name = "accountId", description = "Unique identifier of the account.") }, responses = {
							@ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = Account.class))),
							@ApiResponse(responseCode = "401", description = "Unauthorized"),
							@ApiResponse(responseCode = "404", description = "Not Found") }))
	public RouterFunction<ServerResponse> getContactsByAccountId(AccountHandler accountHandler) {
		return route(GET("/accounts/{accountId}/contacts").and(accept(APPLICATION_JSON)),
				accountHandler::getContactsByAccountId);
	}

	@Bean
	@RouterOperation(operation = @Operation(operationId = "getSubscriptionsByAccountId", description = "Get Subscriptions by Account by ID.", summary = "Get Subscriptions by Account.", security = {
			@SecurityRequirement(name = "bearerScheme") }, tags = { "Account" }, parameters = {
					@Parameter(in = ParameterIn.PATH, name = "accountId", description = "Unique identifier of the account.") }, responses = {
							@ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = Account.class))),
							@ApiResponse(responseCode = "401", description = "Unauthorized"),
							@ApiResponse(responseCode = "404", description = "Not Found") }))
	public RouterFunction<ServerResponse> getSubscriptionsByAccountId(AccountHandler accountHandler) {
		return route(GET("/accounts/{accountId}/subscriptions").and(accept(APPLICATION_JSON)),
				accountHandler::getSubscriptionsByAccountId);
	}

	@Bean
	@RouterOperation(operation = @Operation(operationId = "createContactByAccountId", description = "Create Contact by Account ID", summary = "Create Contact by Account.", security = {
			@SecurityRequirement(name = "bearerScheme") }, tags = { "Account" }, parameters = {
					@Parameter(in = ParameterIn.PATH, name = "accountId", description = "Unique identifier of the account.") }, requestBody = @RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Contact.class))), responses = {
							@ApiResponse(responseCode = "201", description = "Ok", content = @Content(schema = @Schema(implementation = Contact.class))),
							@ApiResponse(responseCode = "401", description = "Unauthorized"),
							@ApiResponse(responseCode = "403", description = "Forbidden"),
							@ApiResponse(responseCode = "404", description = "Not Found") }))
	public RouterFunction<ServerResponse> createContactByAccountId(AccountHandler accountHandler) {
		return route(POST("/accounts/{accountId}/contacts").and(accept(APPLICATION_JSON)),
				accountHandler::createContactByAccountId);
	}

	@Bean
	@RouterOperation(operation = @Operation(operationId = "createSubscriptionByAccountId", description = "Create Subscription by Account ID", summary = "Create Subscription by Account.", security = {
			@SecurityRequirement(name = "bearerScheme") }, tags = { "Account" }, parameters = {
					@Parameter(in = ParameterIn.PATH, name = "accountId", description = "Unique identifier of the account.") }, requestBody = @RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Subscription.class))), responses = {
							@ApiResponse(responseCode = "201", description = "Ok", content = @Content(schema = @Schema(implementation = Subscription.class))),
							@ApiResponse(responseCode = "401", description = "Unauthorized"),
							@ApiResponse(responseCode = "403", description = "Forbidden"),
							@ApiResponse(responseCode = "404", description = "Not Found") }))
	public RouterFunction<ServerResponse> createSubscriptionByAccountId(AccountHandler accountHandler) {
		return route(POST("/accounts/{accountId}/subscriptions").and(accept(APPLICATION_JSON)),
				accountHandler::createSubscriptionByAccountId);
	}

}
