package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	interface GrantedAuthorityCnv extends Converter<String, GrantedAuthority> {}

	@Bean
	@ConfigurationPropertiesBinding
	GrantedAuthorityCnv converter() {
		return SimpleGrantedAuthority::new;
	}

	@Bean
	UserDetailsService userService(UserRepository repo) {
		return username -> repo.findByUsername(username).asUser();
	}
	
	@Bean
	CommandLineRunner initUsers(UserManagementRepository repository, AppConfig appConfig) {
		return args -> repository.saveAll(appConfig.users());
	}
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		.requestMatchers("/login").permitAll()
		.requestMatchers("/delete/**").permitAll()
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