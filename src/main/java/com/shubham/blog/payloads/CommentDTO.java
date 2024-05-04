package com.shubham.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {

	private Integer id;

	private String content;

	// find a way to show just user id and postId for commentDTO

	// NOTE :::::::::: if we use userDTO everywhere, we have a chance to leak
	// passWord. For user creation, userDTO will be different and for user linking
	// dto will be different

//	private PostDTO post;

//	private UserDTO user;

}