package com.shubham.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubham.blog.entities.Category;
import com.shubham.blog.entities.Post;
import com.shubham.blog.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
}
