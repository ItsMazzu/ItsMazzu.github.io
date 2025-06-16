package com.gameLib.GameLib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.gameLib.GameLib.repository")
public class GameLibApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameLibApplication.class, args);
	}

}
