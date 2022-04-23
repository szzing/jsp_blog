package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Users;

//@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	
	/* Users findByUsernameAndPassword(String username, String password); */
	
	Optional<Users> findByUsername(String username);

}
