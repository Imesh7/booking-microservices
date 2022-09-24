package com.booking.authentication_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthenticationServiceApplication {

	public static void main(String[] args) {
		System.out.println("app started....");
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

}
