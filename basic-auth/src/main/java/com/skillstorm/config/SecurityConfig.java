package com.skillstorm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.skillstorm.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// injecting our CustomUserDetailsService here for finally authenticating the incoming password against the one in the database
	private final CustomUserDetailsService service;
	
	public SecurityConfig(CustomUserDetailsService service) {
		this.service = service;
	}
	
	// we need a bean for our password encoder -- using bcrypt here!
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// last step!!
	// we need to say HOW we're managing authentication
	// in our case, it's using bcrypt to handle passwords
	// we must feed in both the http object and the password encoder as parameters
	@Bean
	AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) {
		// start building an auth object
		AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
		
		// chain onto that auth object with the services we want to use
		// for us, that's our UserDetailsService object and our PasswordEncoder
		auth.userDetailsService(service).passwordEncoder(passwordEncoder);
		
		// return the build -- now our auth will use both!
		return auth.build();
		
	}
    
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) {
		
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/main").permitAll()
                .requestMatchers(HttpMethod.POST, "/main").authenticated()
                .requestMatchers(HttpMethod.GET, "/main/protected").authenticated()
                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> Customizer.withDefaults());
        
        return http.build();
    }
	
}