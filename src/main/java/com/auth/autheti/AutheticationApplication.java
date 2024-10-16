package com.auth.autheti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.auth.autheti")
public class AutheticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutheticationApplication.class, args);
	}

}
