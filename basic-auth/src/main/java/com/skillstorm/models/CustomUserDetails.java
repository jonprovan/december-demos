package com.skillstorm.models;

import java.util.Collection;
import java.util.LinkedList;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
	
	// this class will be what Spring accesses to get a hold of the User details
	// we implement UserDetails because Spring is looking for something in the context of type UserDetails
	// we need to extract the User from the context for use here
	private final User user;
	
	public CustomUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new LinkedList<>();
	}

	@Override
	public @Nullable String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	// this is not required, but we'd have access to their roles with our custom User
	public String getRole() {
		return user.getRole();
	}

}
