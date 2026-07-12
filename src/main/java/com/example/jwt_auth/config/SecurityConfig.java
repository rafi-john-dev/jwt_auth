package com.example.jwt_auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.jwt_auth.security.CustomUserDetailsService;
import com.example.jwt_auth.security.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtFilter jwtFilter;
	private final CustomUserDetailsService userDetailsService; // 1. Inject your user details service here

	// 1. Inject your custom JWT Filter gatekeeper
	public SecurityConfig(JwtFilter jwtFilter, CustomUserDetailsService userDetailsService) {
		this.jwtFilter = jwtFilter;
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		System.out.println(">>>>>>>> SECURITY CONFIG HAS LOADED SUCCESSFULLY! <<<<<<<<");

		http
				// 2. Disable CSRF (Cross-Site Request Forgery) since REST APIs use stateless
				// JWTs
				.csrf(csrf -> csrf.disable())

				// 3. Define endpoint authorization rules
//		.authorizeHttpRequests(auth -> auth
//				 // Allow anyone to access registration, login, and internal error pages without a token
//				.requestMatchers("/api/auth/**","/error").permitAll()
//				.requestMatchers("/api/users/**").authenticated()
//				
//				// Any other endpoint in the application will require a valid JWT token
//				.anyRequest().authenticated()

				.authorizeHttpRequests(auth -> auth
						// 1. Open up public entry to Auth, system errors, and ALL Swagger UI endpoints
						.requestMatchers(
						        "/api/auth/**",
						        "/error",
						        "/swagger-ui/**",
						        "/swagger-ui.html",
						        "/v3/api-docs/**",
						        "/webjars/**"
						).permitAll()
						
						.requestMatchers("/", "/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()

						// 2. Keep your profiles endpoint strictly locked down
						.requestMatchers("/api/users/**").authenticated().anyRequest().authenticated())

				// 4. Force stateless sessions (Spring won't create or store HTTP sessions
				// server-side)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				// 5. Inject your custom JwtFilter BEFORE the default
				// UsernamePasswordAuthenticationFilter
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	// 6. Define the PasswordEncoder bean used by your AuthService to hash passwords
	// safely
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	// 7. Define the standard AuthenticationManager bean required for core security
	// routing operations
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
