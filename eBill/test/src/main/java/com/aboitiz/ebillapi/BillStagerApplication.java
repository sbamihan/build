package com.aboitiz.ebillapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
@Log4j2
public class BillStagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillStagerApplication.class, args);

		Flux<Bill> billFlux = getBillFlux();
		Flux<Account> accountFlux = getAccountFlux();
		
		billFlux.filterWhen(p -> accountFlux.hasElements().map(b -> !b)).subscribe(log::info);

//		billFlux.flatMap(b -> {
//			return Flux.just(b).zipWith(accountFlux.filter(p -> p.getAccountId().equals(b.getAccountId()))).map(f -> {
//				Bill bill = f.getT1();
//				Account account = f.getT2();
//				b.setAccountName(account.getName());
//
//				return bill;
//			});
//		}).subscribe(log::info);

	}

	private static Flux<Bill> getBillFlux() {
		log.info("called for getBillFlux()");
		List<Bill> bills = new ArrayList<>();
		bills.add(new Bill(1, "11111", null));
		bills.add(new Bill(2, "11112", null));
		bills.add(new Bill(3, "11113", null));
		bills.add(new Bill(4, "11114", null));
		bills.add(new Bill(5, "11115", null));

		return Flux.fromIterable(bills).doOnNext(onNext -> {
//			log.info("onNext() {}", onNext);
		});
	}

	private static Flux<Account> getAccountFlux() {
		log.info("called for getAccountFlux()");
		List<Account> accounts = new ArrayList<>();
		accounts.add(new Account("11111", "Account1"));
		accounts.add(new Account("11114", "Account4"));
//		accounts.add(new Account("11115", "Account7"));
//		accounts.add(new Account("11115", "Account6"));
		accounts.add(new Account("11115", "Account5"));

		return Flux.fromIterable(accounts).doOnNext(onNext -> {
//			log.info("onNext() {}", onNext);
		});
	}

}
