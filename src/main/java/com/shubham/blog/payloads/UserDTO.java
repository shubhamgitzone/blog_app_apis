package com.shubham.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

	private int id;
	
	@NotEmpty
	@Size(min = 4, message = "Username must be min of 4 chars")
	private String name;
	
	@Email(message = "Email id is not valid!!")
//	@Pattern(regexp = " (([a-z]{2,4}|d+)$/i)") //find proper regEX for email
	private String email;
	
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be min of 3 chars and max of 10 chars")
	private String password;
	
	@NotEmpty
	private String about;
	
}
