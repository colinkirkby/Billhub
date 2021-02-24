package com.Spring.BillHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class BillHubApplication{
	public static void main(String[] args) {
		SpringApplication.run(BillHubApplication.class, args);
	}

}
