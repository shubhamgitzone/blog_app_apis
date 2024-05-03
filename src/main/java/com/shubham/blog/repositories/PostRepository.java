package com.shubham.blog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shubham.blog.entities.Category;
import com.shubham.blog.entities.Post;
import com.shubham.blog.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	Page<Post> findByUser(User user, Pageable p);
	
	Page<Post> findByCategory(Category category, Pageable p);
}
