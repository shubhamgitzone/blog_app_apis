package com.shubham.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.blog.payloads.ApiResponse;
import com.shubham.blog.payloads.PostDTO;
import com.shubham.blog.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(
			@Valid @RequestBody PostDTO postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		
		PostDTO createdPost = this.postService.createPost(postDto, categoryId, userId);
		
		return new ResponseEntity<PostDTO>(createdPost, HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable Integer userId){
		List<PostDTO> posts = this.postService.getPostByUser(userId);
		return new ResponseEntity<>(posts,HttpStatus.FOUND);
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDTO> posts = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<>(posts,HttpStatus.FOUND);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
		PostDTO post = this.postService.getPostById(postId);
		return new ResponseEntity<>(post,HttpStatus.FOUND);
	}

	@GetMapping("/posts")
	public ResponseEntity<List<PostDTO>> getAllPosts(){
		List<PostDTO> posts = this.postService.getAllPosts();
		return new ResponseEntity<>(posts,HttpStatus.FOUND);
	}
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity<>(new ApiResponse("Post deleted successfully", true),HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> updatePostById(@Valid @RequestBody PostDTO postDTO, @PathVariable Integer postId){
		PostDTO updatedPost = this.postService.updatePost(postDTO,postId);
		return new ResponseEntity<>(updatedPost, HttpStatus.ACCEPTED);
	}
	
}
