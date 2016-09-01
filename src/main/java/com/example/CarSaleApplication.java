package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CarSaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarSaleApplication.class, args);
	}
}
