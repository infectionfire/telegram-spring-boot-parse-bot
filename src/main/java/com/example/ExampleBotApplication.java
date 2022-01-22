package com.example;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleBotApplication {

	private static final Logger log = Logger.getLogger(ExampleBotApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ExampleBotApplication.class, args);
	}

}
