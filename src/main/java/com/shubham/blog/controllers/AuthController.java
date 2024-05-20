package com.shubham.blog.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.blog.payloads.AuthenticationRequest;
import com.shubham.blog.payloads.AuthenticationResponse;
import com.shubham.blog.payloads.UserDTO;
import com.shubham.blog.services.impl.AuthServiceImpl;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private AuthServiceImpl authService;

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody  UserDTO userDto) throws Exception {
		
		AuthenticationResponse registeredUser = authService.registerUser(userDto);
		return new ResponseEntity<AuthenticationResponse>(registeredUser, HttpStatus.OK);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws Exception {
		
		AuthenticationResponse authenticatedUser = authService.authenticateUser(request);
		return new ResponseEntity<AuthenticationResponse>(authenticatedUser, HttpStatus.OK);
	}
	
}
