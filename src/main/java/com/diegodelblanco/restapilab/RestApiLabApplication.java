package com.diegodelblanco.restapilab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class RestApiLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiLabApplication.class, args);
	}

}

