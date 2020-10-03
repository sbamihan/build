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

//@Component
//class SampleDataCLR implements CommandLineRunner {
//
//	private final AccountRepository accountRepository;
//
//	@Autowired
//	public SampleDataCLR(AccountRepository accountRepository) {
//		this.accountRepository = accountRepository;
//	}
//
//	@Override
//	public void run(String... strings) throws Exception {
//		Collection<Account> accounts = new ArrayList<>();
//		accounts.add(new Account("0969431381", "Sherwin Amihan", "sherwin.amihan@aboitiz.com"));
//		accounts.add(new Account("6947422222", "Evelyn Amihan", "evelyn.amihan@gmail.com"));
//		accounts.add(new Account("8368243456", "Dianna Claire Marie Amihan", "diannaclairemarie.amihan@gmail.com"));
//		accounts.add(new Account("0759410524", "Elijah Raye Vel Amihan", "elijahrayevel.amihan@gmail.com"));
//		accounts.add(new Account("7773200000", "Brandon Boyd Amihan", "brandonboyd.amihan@gmail.com"));
//		accounts.add(new Account("1696004094", "Tabebe Stuffed Toy", "tabebe@gmail.com"));
//
//		accounts.stream().forEach(e -> accountRepository.save(e));
//		accountRepository.findAll().forEach(System.out::println);
//	}
//}