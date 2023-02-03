package com.a406.mrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableJpaAuditing
@SpringBootApplication
@EnableMongoRepositories
public class MrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(MrmApplication.class, args);
	}

}
