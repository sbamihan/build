package com.aboitiz.subscriptionservice.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Mono;

@Controller
public class HomeController {

	@Value("/swagger-ui.html")
	private String swaggerUiPath;

	@GetMapping("/")
	public Mono<Void> index(ServerHttpRequest request, ServerHttpResponse response) {
		UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromUriString(swaggerUiPath);
		response.setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
		response.getHeaders().setLocation(URI.create(uriBuilder.build().encode().toString()));
		return response.setComplete();
	}
	
}
