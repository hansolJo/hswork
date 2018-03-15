package com.hswork.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hswork.spring.service.CouponService;

@SpringBootApplication
public class SpringBootCouponApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCouponApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(CouponService service) {
		return args -> {
			service.insertDefaultData();
		};
	}
}
