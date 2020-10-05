package com.amihan.edgeservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EdgeServiceApplicationTests {

	@LocalServerPort
	int port;
	private WebTestClient client;

	@BeforeEach
	public void setup() {
		client = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();
	}

	@Test
	public void headServiceRouteWorks() {
		client.get()
			.uri("/api/v1/head/requests")
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.consumeWith(result -> {
				assertThat(result.getResponseBody()).isNotEmpty();
			});
	}

}
