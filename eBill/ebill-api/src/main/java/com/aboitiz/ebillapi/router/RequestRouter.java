package com.aboitiz.ebillapi.router;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.aboitiz.ebillapi.handler.RequestHandler;

@Configuration
public class RequestRouter {

	@Bean
	public RouterFunction<ServerResponse> requestReadRoute(RequestHandler requestHandler) {
		return route(POST("/events").and(accept(APPLICATION_JSON)), requestHandler::createExtractedBillEvent);
	}

}
