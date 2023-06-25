package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {
	
	@Autowired
	private VideoRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@PostConstruct
	void initDatabase() {
		repository.save(new VideoEntity("alice", "Need HELP with your SPRING BOOT 3 App?", "SPRING BOOT 3 will only speed things up and make it"));
		repository.save(new VideoEntity("alice", "Don't do THIS to your own CODE!", "As a pro developer, never ever EVER do this to yourn code. Because you'll ultimately be doing it to YOURSELF!"));
		repository.save(new VideoEntity("bob", "SECRETS to fix BROKEN CODE!", "Discover ways to not only debug your code, but to regain your confidence and get back in the game as a software developer."));
	}

	/*
	@Bean
	CommandLineRunner runner() {
		return args -> {
			repository.save(new VideoEntity("alice","Need HELP with your SPRING BOOT 3 App?",""));
			repository.save(new VideoEntity("bob", "Don't do THIS to your own CODE!", ""));
			//repository.save(new VideoEntity("SECRETS to fix BROKEN CODE!",""));
		};
	}
	*/

}