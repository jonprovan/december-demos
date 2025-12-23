package com.skillstorm.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {
	
	@GetMapping
	public String getStuffUnauthenticated() {
		return "You don't have to be authenticated to see this!";
	}
	
	@GetMapping("/protected")
	public String getStuffAuthenticated() {
		return "You DO have to be authenticated to see this!";
	}
	
	@PostMapping
	public String postStuffAuthenticated(@RequestBody String string) {
		return string + " -- you had to be authenticated to post that!";
	}
	

}
