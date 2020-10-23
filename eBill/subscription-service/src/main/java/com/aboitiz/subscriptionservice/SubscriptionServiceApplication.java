package com.aboitiz.subscriptionservice;

import java.util.TimeZone;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class SubscriptionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionServiceApplication.class, args);
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
		return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
	}

	@Bean
	public OpenAPI prepaidMeteringAPI() {
		return new OpenAPI()
//				.components(new Components().addSecuritySchemes("bearerScheme",
//						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
//								.description("JWT Authorization")))
				.info(new Info().title("Subscription Service").description("Application used for managing subscriptions").version("v0.0.1")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation().description("Subscription Service Wiki Documentation")
						.url("https://springshop.wiki.github.org/docs"));
	}

	@Bean
	public GroupedOpenApi accountOpenApi() {
		String[] paths = { "/accounts/**" };
		return GroupedOpenApi.builder().group("accounts").pathsToMatch(paths).build();
	}

	@Bean
	public GroupedOpenApi contactOpenApi() {
		String[] paths = { "/contacts/**", "/contactTypes/**" };
		return GroupedOpenApi.builder().group("contacts").pathsToMatch(paths).build();
	}
	
	@Bean
	public GroupedOpenApi subscriptionOpenApi() {
		String[] paths = { "/subscriptions/**", "/subscriptionTypes/**" };
		return GroupedOpenApi.builder().group("subscriptions").pathsToMatch(paths).build();
	}

}
