package com.aboitiz.employeeservice;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.aboitiz.employeeservice.controller.EmployeeController;
import com.aboitiz.employeeservice.service.GreetingService;

@WebMvcTest(EmployeeController.class)
public class EmployeeServiceApplicationUnitTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private GreetingService service;
	
	@Test
	public void shouldSolveTheWorldProblem() throws Exception {
		when(service.greetHelloWorld())
			.thenReturn("Hello World!");
		
		this.mockMvc.perform(get("/helloWorld"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Hello World!")));
	}
	
	@Test
	public void shouldReturnHiFromService() throws Exception {
		when(service.greetHi())
			.thenReturn("Hi!");
		
		this.mockMvc.perform(get("/hi"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Hi!")));
	}
	
	@Test
	public void shouldReturnHelloFromService() throws Exception {
		when(service.greetHello())
			.thenReturn("Hello!");
		
		this.mockMvc.perform(get("/hello"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Hello!")));
	}

}
