package com.example.jwt_auth.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.jwt_auth.entity.User;
import com.example.jwt_auth.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		// TODO Auto-generated constructor stub
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		
		// 1. fetch user entity details from postgreSql using your existing repository method
		User user = userRepository.findByEmail(email)
				.orElseThrow(()-> new UsernameNotFoundException("Username not found with email Id "+email));

		// 2. Convert custom user entity onto spring security's native UserDetails object wrapper
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getPassword(),
				new ArrayList<>() 	// empty list with no other granular roles/authorities yet
				);
	}
	
	
}
