package com.skillstorm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// same two annotations as with basic auth config
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		
		// select which endpoints you'd like to have authenticated
		http.authorizeHttpRequests(req -> 
				req.requestMatchers(HttpMethod.GET, "/main/loggedout").permitAll()
				   .anyRequest().authenticated()
		);
		
		// here, we're saying we'd like to use OAuth authentication for these requests
		http.oauth2Login(Customizer.withDefaults());
		
		return http.build();
	}

}
