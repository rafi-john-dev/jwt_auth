package com.example.jwt_auth.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	// A secured 256 bit secret key generated dynamically for encryption signing
	private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	// Token validity setup here 24 hrs in milli seconds
	private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;

	// method to create a fresh token using user's email or username
	public String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SECRET_KEY)
				.compact();
	}

	// Extract the email/username out of the existing token
	public String extractEmail(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(SECRET_KEY)
				.build()
				.parseClaimsJws(token) // Correctly reads the raw token string
				.getBody()
				.getSubject();
	}
	
	// Verify if the token is still valid and has not expired yet
	public boolean validToken(String token, String email) {
		String extractedEmail = extractEmail(token); 
		
		// FIX 1: Pass the actual 'token' string here, NOT the extracted email string!
		boolean isExpired = Jwts.parserBuilder()
				.setSigningKey(SECRET_KEY)
				.build()
				.parseClaimsJws(token) 
				.getBody()
				.getExpiration()
				.before(new Date());
				
		// FIX 2: Compare the extracted email against the incoming 'email' parameter variable!
		return extractedEmail.equals(email) && !isExpired; 
	}
}
