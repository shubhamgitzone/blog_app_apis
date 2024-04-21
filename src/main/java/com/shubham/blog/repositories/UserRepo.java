package com.shubham.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubham.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
