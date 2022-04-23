package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Boards;

//@Repository
public interface BoardRepository extends JpaRepository<Boards, Integer> {
	
	/* Users findByUsernameAndPassword(String username, String password); */
	
//	Optional<Boards> findByUsername(String username);

}
