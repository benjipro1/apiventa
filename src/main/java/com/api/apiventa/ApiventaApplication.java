package com.api.apiventa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.api.apiventa")
public class ApiventaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiventaApplication.class, args);
	}

}
