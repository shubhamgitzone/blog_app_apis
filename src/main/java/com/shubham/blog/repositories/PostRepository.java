package com.shubham.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shubham.blog.entities.Category;
import com.shubham.blog.entities.Post;
import com.shubham.blog.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	Page<Post> findByUser(User user, Pageable p);

	Page<Post> findByCategory(Category category, Pageable p);

	List<Post> findByTitleContaining(String title);

//	@Query("select p from Post p where p.title like :key") @Param("key")
//	List<Post> searchByTitle(@Param("key") String title);

	/*
	 * This above code will help us to understand we can write query as needed over
	 * any repo call
	 * 
	 * To call this method, we have to call like below
	 * 
	 * postRepo.searchByTitle("%"+keywordToSearchWith+"%")
	 * 
	 * Also, this query writing helps us to override the query that JPA will run on
	 * its own
	 * 
	 */
}
