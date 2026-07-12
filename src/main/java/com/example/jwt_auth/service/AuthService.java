package com.example.jwt_auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwt_auth.dto.LoginRequest;
import com.example.jwt_auth.entity.User;
import com.example.jwt_auth.repository.UserRepository;
import com.example.jwt_auth.security.JwtService;

import jakarta.transaction.Transactional;

@Service
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepo;
	// added after register and login 
	private final JwtService jwtService;

	@Autowired
	public AuthService(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	@Transactional
	public String registerUser(Optional<User> userOptionalRegistrationRequest) {

		User userRegistrationRequest = userOptionalRegistrationRequest.orElseThrow(
				() -> new IllegalArgumentException("Registration Failed: Request Payload cannot be empty..."));

		validateInput(userRegistrationRequest);

		// 1. convert plain password to hashed password using BCrypt
		String hashedPassword = passwordEncoder.encode(userRegistrationRequest.getPassword());

		// 2. Replace the plain text value inside the entity with the new safe hash
		userRegistrationRequest.setPassword(hashedPassword);

		if (userRepo.existsByEmail(userRegistrationRequest.getEmail())) {
			throw new IllegalArgumentException("User Already Registered");
		}

		userRepo.save(userRegistrationRequest);

		return "User Registered Successfully...";
	}

	private void validateInput(User user) {
		if (user == null) {
			throw new IllegalArgumentException("Input is Mandatory...");
		}
		if (user.getEmail().trim().isEmpty() || user.getEmail() == null) {
			throw new IllegalArgumentException("Email field is required...");
		}
		if (user.getPassword() == null || user.getPassword().length() < 6) {
			throw new IllegalArgumentException("Password is too short...");
		}
		if (user.getName() == null || user.getName().trim().isEmpty()) {
			throw new IllegalArgumentException("User name cannot be empty...");
		}
	}

	public String loginUser(LoginRequest loginReq) {

		// 1. Basic field validation
		if (loginReq.getEmail() == null || loginReq.getEmail().trim().isEmpty() || loginReq.getPassword() == null
				|| loginReq.getPassword().trim().isEmpty()) {
			throw new IllegalArgumentException("Email or Password are required...!");
		}
		// 2. fetch user by email from postgreSql
		User user = userRepo.findByEmail(loginReq.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("Email or Password is Incorrect...!"));
		
		// 3. Compare the raw inbound password string with the databse BCrypt hash String
		boolean isPasswordMatch = passwordEncoder.matches(loginReq.getPassword(), user.getPassword());

		if (!isPasswordMatch) {
			throw new IllegalArgumentException("Authentication Failed: Incorrect Email or Password...!");
		}

		// 4. Welcome message or provide JWT token here
		//return "Login Success...! Welcome " +user.getName();
	
		// after login and register impl jwt
		String token = jwtService.generateToken(user.getEmail());
		return token;
	}
}
