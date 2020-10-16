package com.aboitiz.employeeservice.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

	public String greetHello() {
		return "Hello!";
	}
	
	public String greetHi() {
		return "Hi!";
	}
	
	public String greetHelloWorld() {
		return "Hello World!";
	}
	
}
