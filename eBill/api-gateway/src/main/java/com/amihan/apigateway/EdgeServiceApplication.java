package com.amihan.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class EdgeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdgeServiceApplication.class, args);
	}

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes().route("head-service", r -> r.path("/api/v1/head/**").uri("lb://HEAD-SERVICE"))
				.route("rewrite-head-service",
						r -> r.path("/requests/**")
								.filters(f -> f.rewritePath("/requests", "/api/v1/head/requests")
										.addRequestHeader("X-first-Header", "first-service-header")
										.hystrix(c -> c.setName("hystrix").setFallbackUri("forward:/hystrixfallback")))
								.uri("lb://HEAD-SERVICE"))
				.build();
	}

	@RequestMapping("/hystrixfallback")
	public String hystrixfallback() {
		return "This is a fallback";
	}

}
