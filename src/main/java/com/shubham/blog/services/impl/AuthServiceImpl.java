package com.shubham.blog.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shubham.blog.entities.Role;
import com.shubham.blog.entities.User;
import com.shubham.blog.payloads.AuthenticationRequest;
import com.shubham.blog.payloads.AuthenticationResponse;
import com.shubham.blog.payloads.UserDTO;
import com.shubham.blog.repositories.UserRepo;
import com.shubham.blog.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {

	private final UserRepo userRepo;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationMananger;

	public AuthenticationResponse registerUser(UserDTO userDto) {

		User user = new User();
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setRole(Role.ADMIN);

		userRepo.save(user);

		String token = jwtService.generateToken(user);

		return AuthenticationResponse.builder().token(token).build();
	}

	public AuthenticationResponse authenticateUser(AuthenticationRequest request) {
		authenticationMananger
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		Optional<User> user = this.userRepo.findByEmail(request.getUsername());
		String token = jwtService.generateToken(user.get());

		return AuthenticationResponse.builder().token(token).build();
	}

}
