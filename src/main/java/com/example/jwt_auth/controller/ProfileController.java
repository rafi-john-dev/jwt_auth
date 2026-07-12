package com.example.jwt_auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class ProfileController {

	@GetMapping("/profiles")
	public ResponseEntity<String> getUserProgfile(){
		// retrive the authenticated user'e email straight out of spring's security context memory
		String currentAuthenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		
		String responseMessage = "Welcome to your private dashboard "+currentAuthenticatedUserEmail+ " ! this data "
				+ "is coming from highly secured endpoint";
		
		return new ResponseEntity<>	(responseMessage, HttpStatus.OK);
		}
}
