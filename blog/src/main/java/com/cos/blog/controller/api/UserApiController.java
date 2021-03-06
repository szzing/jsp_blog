package com.cos.blog.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Users;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	@Autowired
	private UserService userService;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody Users user) {
		System.out.println("UserApiController 호출됨");

		//user.setRoles(RoleType.USER);

		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody Users user) {
		userService.회원수정(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody Users user, HttpSession session) {
//		System.out.println("UserApiController: login 호출됨");
//		Users principal = userService.로그인(user);
//
//		if (principal != null) {
//			session.setAttribute("principal", principal);
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}
	
}
