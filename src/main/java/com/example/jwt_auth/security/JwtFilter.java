package com.example.jwt_auth.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

	private final JwtService jwtService;
	private final CustomUserDetailsService customUserDetailsService;
	
	public JwtFilter(JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
		// TODO Auto-generated constructor stub
		this.jwtService = jwtService;
		this.customUserDetailsService = customUserDetailsService;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// now perform 7 steps 
		// step 1. locate Authorisation header inside the inbound Http request
		try {
		final String AUTHHEADER = request.getHeader("Authorization");
		final String JWTTOKEN;
		final String USEREMAIL;
		
		// 2. if the header is missing or doesnt start with bearer, ignore and pass down the chain
		if(AUTHHEADER == null || !AUTHHEADER.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		// 3. strip away the prefix 'bearer' to isolate the raw jwt token string
		JWTTOKEN = AUTHHEADER.substring(7);
		USEREMAIL = jwtService.extractEmail(JWTTOKEN);
		
		// 4. If a valid user email is parsed and the current context session isn't already logged in
		if(USEREMAIL != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			//fetch the user state parameters directly out of postgreSql
			UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(USEREMAIL);
			
			// 5. evaluate token structure signature and validity expiration boundaries
			if(jwtService.validToken(JWTTOKEN, USEREMAIL)) {
				
				// Construct a formal authenticated session token capsule
				UsernamePasswordAuthenticationToken authToken =
						new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				// 6. formally unlocks the application context gateway for this request session
				
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
	}catch(Exception e) {
		System.out.println("JWT Filter caught an error processing token: " + e.getMessage());
	}
		// 7. pass request smoothly along to the next element or controller endpoint
		filterChain.doFilter(request, response);
		
	}
	
}
