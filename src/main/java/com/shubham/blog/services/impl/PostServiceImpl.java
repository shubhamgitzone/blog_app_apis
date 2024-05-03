package com.shubham.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shubham.blog.entities.Category;
import com.shubham.blog.entities.Post;
import com.shubham.blog.entities.User;
import com.shubham.blog.exceptions.ResourceNotFoundException;
import com.shubham.blog.payloads.PostDTO;
import com.shubham.blog.payloads.PostResponse;
import com.shubham.blog.repositories.CategoryRepo;
import com.shubham.blog.repositories.PostRepository;
import com.shubham.blog.repositories.UserRepo;
import com.shubham.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDTO createPost(PostDTO postDto, Integer categoryId, Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category id", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);

		Post savedPost = this.postRepo.save(post);
		return this.modelMapper.map(savedPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDto, Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());

		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDTO.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {

		Sort sort = (sortDirection.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allPosts = pagePost.getContent();

		List<PostDTO> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();

		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public PostDTO getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
		return this.modelMapper.map(post, PostDTO.class);
	}

	@Override
	public PostResponse getPostByCategory(Integer categoryId,Integer pageNumber, Integer pageSize) {

		Pageable p = PageRequest.of(pageNumber, pageSize);

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		Page<Post> pagePost = this.postRepo.findByCategory(category,p);
		List<Post> allPosts =pagePost.getContent();
		List<PostDTO> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();

		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public PostResponse getPostByUser(Integer userId,Integer pageNumber, Integer pageSize) {
		
		Pageable p = PageRequest.of(pageNumber, pageSize);
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
		Page<Post> pagePost = this.postRepo.findByUser(user,p);
		List<Post> allPosts = pagePost.getContent();
		List<PostDTO> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class)).toList();

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public List<PostDTO> searchPosts(String keyword) {

		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		return posts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class)).toList();
	}

}
