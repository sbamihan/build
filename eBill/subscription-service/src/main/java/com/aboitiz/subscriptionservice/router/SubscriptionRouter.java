package com.aboitiz.subscriptionservice.router;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.aboitiz.subscriptionservice.handler.SubscriptionHandler;
import com.aboitiz.subscriptionservice.model.Subscription;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Configuration
public class SubscriptionRouter {

	@Bean
	@RouterOperation(operation = @Operation(operationId = "getAllSubscriptions", description = "Get all Subscriptions.", summary = "Get Subscriptions.", security = {
			@SecurityRequirement(name = "bearerScheme") }, tags = { "Subscription" }, responses = {
					@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Subscription.class))),
					@ApiResponse(responseCode = "401", description = "Unauthorized") }))
	public RouterFunction<ServerResponse> getAllSubscriptionsRoute(SubscriptionHandler subscriptionHandler) {
		return route(GET("/subscriptions").and(accept(APPLICATION_JSON)), subscriptionHandler::getAllSubscriptions);
	}

	@Bean
	@RouterOperation(operation = @Operation(operationId = "getSubscriptionById", description = "Get Subscription by ID.", summary = "Get by ID.", security = {
			@SecurityRequirement(name = "bearerScheme") }, tags = { "Subscription" }, parameters = {
					@Parameter(in = ParameterIn.PATH, name = "subscriptionId", description = "Unique identifier of the subscription.") }, responses = {
							@ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = Subscription.class))),
							@ApiResponse(responseCode = "401", description = "Unauthorized"),
							@ApiResponse(responseCode = "404", description = "Not Found") }))
	public RouterFunction<ServerResponse> getSubscriptionByIdRoute(SubscriptionHandler subscriptionHandler) {
		return route(GET("/subscriptions/{subscriptionId}").and(accept(APPLICATION_JSON)),
				subscriptionHandler::getSubscriptionById);
	}

	@Bean
	@RouterOperation(operation = @Operation(operationId = "patchSubscription", description = "Patch Subscription.", summary = "Patch Subscription.", security = {
			@SecurityRequirement(name = "bearerScheme") }, tags = { "Subscription" }, parameters = {
					@Parameter(in = ParameterIn.PATH, name = "subscriptionId", description = "Unique identifier of the subscription."),
					@Parameter(in = ParameterIn.QUERY, name = "statFlg", description = "New status flag.", required = true, schema = @Schema(required = true, defaultValue = "20", allowableValues = {
							"20", "30", "40" })),
					@Parameter(in = ParameterIn.QUERY, name = "subscribe", description = "Subscribe?", required = true, schema = @Schema(required = true, defaultValue = "Y", allowableValues = {
							"Y", "N" })) }, responses = {
									@ApiResponse(responseCode = "201", description = "Ok", content = @Content(schema = @Schema(implementation = Subscription.class))),
									@ApiResponse(responseCode = "401", description = "Unauthorized"),
									@ApiResponse(responseCode = "403", description = "Forbidden"),
									@ApiResponse(responseCode = "404", description = "Not Found") }))
	public RouterFunction<ServerResponse> patchSubscriptionRoute(SubscriptionHandler subscriptionHandler) {
		return route(RequestPredicates.PATCH("/subscriptions/{subscriptionId}").and(accept(APPLICATION_JSON)),
				subscriptionHandler::patchSubscription);
	}

}
