package com.nected.loan.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
@SpringBootApplication
@ConfigurationPropertiesScan("com.nected.loan.example.configuration")
public class NectedLoanExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(NectedLoanExampleApplication.class, args);
	}

}
