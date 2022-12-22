package com.hanghae.gamemini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GameminiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameminiApplication.class, args);
	}

}
