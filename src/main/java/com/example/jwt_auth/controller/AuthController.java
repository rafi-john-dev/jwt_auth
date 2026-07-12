package com.example.jwt_auth.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt_auth.dto.LoginRequest;
import com.example.jwt_auth.entity.User;
import com.example.jwt_auth.service.AuthService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

	private final AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("register")
	public ResponseEntity<String> registerUser(@RequestBody User userRequest)
	{
		try {
			Optional<User> optionalUser = Optional.ofNullable(userRequest);
			
			String message = authService.registerUser(optionalUser);
			
			return new ResponseEntity<>(message, HttpStatus.CREATED);
		}
		catch(IllegalArgumentException ie) {
			return new ResponseEntity<>(ie.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Un-Expected Error...!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("login")
	public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest){
		
		try {
			// Execute the login service pipeline
			String responseMessage = authService.loginUser(loginRequest);
			
			// return HTTP 200 ok along with session confirmation string
			 return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		}
		catch(IllegalArgumentException ie) {
			return new ResponseEntity<>(ie.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		catch(Exception e) {
			return new ResponseEntity<>("An internal server error...! ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
