package com.shubham.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.blog.payloads.ApiResponse;
import com.shubham.blog.payloads.UserDTO;
import com.shubham.blog.services.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto) {
		UserDTO createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<UserDTO>(createUserDto, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDto, @PathVariable Integer id) {
		UserDTO updatedUserDto = this.userService.updateUser(userDto, id);
		return ResponseEntity.ok(updatedUserDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findUserById(@PathVariable Integer id) {
		UserDTO userDto = this.userService.getUserById(id);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> findAllUser() {
		List<UserDTO> users = this.userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	//ADMIN
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> removeUserById(@PathVariable Integer id) {
		this.userService.deleteUser(id);
		
//		return new ResponseEntity(Map.of("message", "User deleted successfully"), HttpStatus.OK);
		
		// instead we can use custom class like APIResponse
		
		return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true), HttpStatus.OK);
	}
}
