package com.Spring.BillHub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class BillHubApplication{

	public static void main(String[] args) {
		SpringApplication.run(BillHubApplication.class, args);
	}

}
