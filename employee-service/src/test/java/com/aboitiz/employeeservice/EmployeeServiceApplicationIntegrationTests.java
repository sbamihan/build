package com.aboitiz.employeeservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aboitiz.employeeservice.model.Employee;
import com.aboitiz.employeeservice.repository.EmployeeRepository;

@SpringBootTest
class EmployeeServiceApplicationIntegrationTests {

	@Autowired
	EmployeeRepository repository;
	
	@Test
	public void testNotEmpty() {
		List<Employee> employees = repository.findAll();
		assertThat(employees).isNotEmpty();
	}

	@Test
	public void testWhenFound_thenReturnEmployee() {
		Optional<Employee> employee = repository.findById(1L);
		assertThat(employee).isPresent();
		assertThat(employee.get()).matches(r -> r.getFirstName() == "Sherwin" && r.getLastName() == "Amihan");
	}

}
