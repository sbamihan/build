package com.aboitiz.kafkaspringboottutorial;

import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.aboitiz.kafkaspringboottutorial.model.Passenger;
import com.aboitiz.kafkaspringboottutorial.model.SuspectedPassenger;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Component
@Log4j2
public class ApplicationHandler {

	public ApplicationHandler() {
	}

//	@Bean
	Function<Flux<Passenger>, Flux<SuspectedPassenger>> processPassengerEvent() {
		return flux -> flux.filter(this::isSuspected).map(this::concealInfo);
	}

	private boolean isSuspected(Passenger passenger) {
		return passenger.getBodyTemperature() >= 36.9 && passenger.isOverseasTravelHistory();
	}

	private SuspectedPassenger concealInfo(Passenger passenger) {
		return new SuspectedPassenger(passenger.getUuId(), passenger.getAge(), passenger.getGender(),
				passenger.getBodyTemperature());
	}

//	@Bean
	public Consumer<SuspectedPassenger> processCovidSuspected() {
		return s -> Mono.just(s).map(m -> "M".equalsIgnoreCase(m.getGender()) ? "Male"
				: "Female" + " passenger " + m.getUuId() + " suspected with COVID.").subscribe(log::info);
	}

}
