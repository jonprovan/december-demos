package com.skillstorm.controllers;

import java.io.IOException;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/main")
public class MainController {
	
	@GetMapping
	public String hello() {
		return "Hello, and welcome!";
	}
	
	// this method returns the entire principal object, so we can see what's in it
	// the OidcUser is present in the context and can be grabbed via injection
	@GetMapping("/user")
	public OidcUser getUser(@AuthenticationPrincipal OidcUser principal) {
		return principal;
	}
	
	@GetMapping("/jononlysecret")
	public String getSecret(@AuthenticationPrincipal OidcUser principal) {
		
		// by injecting the principal into the method like we do above
		// we now have access to the values included in the token, like the user's e-mail address!
	
		if (principal.getEmail().equals("jon@epmediadesign.com"))
			return "Yes, Hanson IS the best band in history.";
		else
			return "This arcane knowledge is not for you.";
	}
	
	// this method forces the user out of their Google login so they'll have to sign in again
	// the first two redirects can be copy/pasted for other apps
	// the third is custom to wherever you'd like the user to go after logout
	@GetMapping("/logout")
	public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.getSession().invalidate();
		res.sendRedirect("https://accounts.google.com/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://localhost:8080/main/loggedout");
	}
	
	// this endpoint is unprotected (check the security config), so after the user has logged out, they'll come here
	@GetMapping("/loggedout")
	public String loggedOut() {
		return "You are logged out.";
	}
	

}
