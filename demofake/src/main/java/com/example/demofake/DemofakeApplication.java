package com.example.demofake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//import com.example.demofake.VideoEntity;
import com.example.demofake.VideoRepository;

@SpringBootApplication
public class DemofakeApplication {
	@Autowired
	private VideoRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DemofakeApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner() {
		return args -> {
			repository.save(new VideoEntity("alice","Need HELP with your SPRING BOOT 3 App?",""));
			repository.save(new VideoEntity("bob", "Don't do THIS to your own CODE!", ""));
			//repository.save(new VideoEntity("SECRETS to fix BROKEN CODE!",""));
		};
	}
}