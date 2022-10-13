package de.jonasrotert.conferenceplanner.app;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ConferencePlannerApplication {

	public static void main(final String[] args) {


		SpringApplication.run(ConferencePlannerApplication.class, args);
		final var testUuid = UUID.randomUUID();
		log.info("Test-UUID: {}", testUuid);
	}

}
