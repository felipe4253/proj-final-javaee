package com.felipe.gestaoacoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GestaoacoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoacoesApplication.class, args);
	}

}

