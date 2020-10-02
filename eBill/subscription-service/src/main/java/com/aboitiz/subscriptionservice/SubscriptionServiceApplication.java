package com.aboitiz.subscriptionservice;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.aboitiz.subscriptionservice.model.Account;
import com.aboitiz.subscriptionservice.repository.AccountRepository;

@SpringBootApplication
public class SubscriptionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionServiceApplication.class, args);
	}

}

@Component
class SampleDataCLR implements CommandLineRunner {

	private final AccountRepository accountRepository;

	@Autowired
	public SampleDataCLR(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		Collection<Account> accounts = new ArrayList<>();
		accounts.add(new Account("2937265565", "Sherwin Amihan", "sherwin.amihan@aboitiz.com"));
		accounts.add(new Account("6281258220", "Evelyn Amihan", "evelyn.amihan@gmail.com"));
		accounts.add(new Account("1841058749", "Dianna Claire Marie Amihan", "diannaclairemarie.amihan@gmail.com"));
		accounts.add(new Account("5951409201", "Elijah Raye Vel Amihan", "elijahrayevel.amihan@gmail.com"));
		accounts.add(new Account("0263248034", "Brandon Boyd Amihan", "brandonboyd.amihan@gmail.com"));
		accounts.add(new Account("0672255976", "Tabebe Stuffed Toy", "tabebe@gmail.com"));

		accounts.stream().forEach(e -> accountRepository.save(e));
		accountRepository.findAll().forEach(System.out::println);
	}
}