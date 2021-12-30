package com.github.pedroluiznogueira.spsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpsApiApplication.class, args);
		System.out.println("Running...");
	}

}
