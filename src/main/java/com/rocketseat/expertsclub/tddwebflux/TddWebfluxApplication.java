package com.rocketseat.expertsclub.tddwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class TddWebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(TddWebfluxApplication.class, args);
	}

}
