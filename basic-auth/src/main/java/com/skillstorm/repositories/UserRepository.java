package com.skillstorm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	// since we're looking up users via their usernames, we need a custom method to do so
	Optional<User> findByUsername(String username);

}
