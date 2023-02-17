package com.a406.mrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableJpaAuditing
@SpringBootApplication
@EnableMongoRepositories
@CrossOrigin("http://localhost:3000")
public class MrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(MrmApplication.class, args);
	}
}
