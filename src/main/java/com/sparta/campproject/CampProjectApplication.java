package com.sparta.campproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class CampProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampProjectApplication.class, args);
	}

}
