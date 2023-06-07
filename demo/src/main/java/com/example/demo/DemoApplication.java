package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
	
	@Autowired
	private VideoRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			repository.save(new VideoEntity("","Need HELP with your SPRING BOOT 3 App?",""));
			repository.save(new VideoEntity("", "Don't do THIS to your own CODE!", ""));
			//repository.save(new VideoEntity("SECRETS to fix BROKEN CODE!",""));
		};
	}

}