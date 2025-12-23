package com.skillstorm.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.skillstorm.models.CustomUserDetails;
import com.skillstorm.models.User;
import com.skillstorm.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	// this class needs access to our repository to grab the user's information, so we inject it here
	private final UserRepository repo;
	
	public CustomUserDetailsService(UserRepository repo) {
		this.repo = repo;
	}

	// when this service is asked for UserDetails, it will head to the repo and look for a user with the supplied name
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No user found with that name"));
		
		return new CustomUserDetails(user);
	}

}
