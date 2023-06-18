package com.example.demofake;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	@Bean
	UserDetailsService userService(UserRepository repo) {
		return username -> repo.findByUsername(username).asUser();
	}
	
	@Bean
	CommandLineRunner initUsers(UserManagementRepository repository) {
		return args->{
			repository.save(new UserAccount("alice", "password", "ROLE_USER"));
			repository.save(new UserAccount("bob", "password", "ROLE_USER"));
			repository.save(new UserAccount("user","password","ROLE_USER"));
			repository.save(new UserAccount("admin","password","ROLE_ADMIN"));
		};
	}
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		.requestMatchers("/login").permitAll()
		//.requestMatchers("/delete/**").permitAll()
		.requestMatchers("/","/search").authenticated()
		.requestMatchers(HttpMethod.GET, "/api/**")
		.authenticated()
		.requestMatchers(HttpMethod.POST, "/new-video", "/api/**").hasRole("ADMIN")
		.anyRequest().denyAll()
		.and()
		.formLogin()
		.and()
		.httpBasic();
		
		return http.build();
	}
	
 }