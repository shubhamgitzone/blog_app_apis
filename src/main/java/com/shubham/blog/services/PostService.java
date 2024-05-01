package com.shubham.blog.services;

import java.util.List;

import com.shubham.blog.payloads.PostDTO;

public interface PostService {

	
	PostDTO createPost(PostDTO postDto, Integer categoryId, Integer userId);
	
	PostDTO updatePost(PostDTO postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	List<PostDTO> getAllPosts();
	
	PostDTO getPostById(Integer postId);
	
	List<PostDTO> getPostByCategory(Integer categoryId);
	
	List<PostDTO> getPostByUser(Integer userId);
	
	List<PostDTO> searchPosts(String keyword);
}
