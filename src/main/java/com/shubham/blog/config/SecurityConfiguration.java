package com.shubham.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.shubham.blog.security.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration{

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	private final AuthenticationProvider authenticationProvider;
	
	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
		
		http
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.requestMatchers("/api/v1/auth/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		
//		http
////		.csrf()
////		.disable()
//		.authorizeHttpRequests((authz) -> 
//				authz
//				.requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
////				.requestMatchers().permitAll()
//				.requestMatchers(HttpMethod.GET).permitAll()
//				.anyRequest().authenticated()
//                )
//		.exceptionHandling(Customizer.withDefaults())
//		.authenticationProvider(daoAuthenticationProvider())
////		.authenticationManager(authenticationManagerBean())
////		.authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
//		.sessionManagement(Customizer.withDefaults());
////		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	
//	http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//	http.authenticationProvider(daoAuthenticationProvider());
//	DefaultSecurityFilterChain build = http.build();
	
	return http.build();
	}
}
