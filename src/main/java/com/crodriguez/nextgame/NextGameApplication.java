package com.crodriguez.nextgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.crodriguez.entities"})
@EnableJpaRepositories(basePackages = {"com.crodriguez.repositories"})
public class NextGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(NextGameApplication.class, args);
	}

}