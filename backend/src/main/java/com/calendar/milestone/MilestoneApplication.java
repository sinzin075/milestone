package com.calendar.milestone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MilestoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(MilestoneApplication.class, args);
	}
	
	@Bean
    	CommandLineRunner runFlyway(Flyway flyway) {
        return args -> {
            System.out.println(">>> FLYWAY IS ALIVE <<<");
            System.out.println(">>> Current version: " + flyway.info().current());
            flyway.migrate();
        };
    }

}
