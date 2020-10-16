package com.aboitiz.employeeservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aboitiz.employeeservice.service.GreetingService;

@RestController
public class EmployeeController {
	
	private final GreetingService service;

	public EmployeeController(GreetingService service) {
		this.service = service;
	}

	@GetMapping("/hello")
	public String hello() {
		return service.greetHello();
	}
	
	@GetMapping("/helloWorld")
	public String helloWorld() {
		return service.greetHelloWorld();
	}
	
	@GetMapping("/hi")
	public String hi() {
		return service.greetHi();
	}
	
}
