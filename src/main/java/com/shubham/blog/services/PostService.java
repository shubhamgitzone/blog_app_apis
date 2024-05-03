package com.shubham.blog.services;

import java.util.List;

import com.shubham.blog.payloads.PostDTO;
import com.shubham.blog.payloads.PostResponse;

public interface PostService {

	PostDTO createPost(PostDTO postDto, Integer categoryId, Integer userId);

	PostDTO updatePost(PostDTO postDto, Integer postId);

	void deletePost(Integer postId);

	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);

	PostDTO getPostById(Integer postId);

	PostResponse getPostByCategory(Integer categoryId,Integer pageNumber, Integer pageSize);

	PostResponse getPostByUser(Integer userId,Integer pageNumber, Integer pageSize);

	List<PostDTO> searchPosts(String keyword);
}
