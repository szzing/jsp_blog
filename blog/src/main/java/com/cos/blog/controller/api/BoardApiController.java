package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Boards;
import com.cos.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Boards board, @AuthenticationPrincipal PrincipalDetail principal) {
//		System.out.println("BoardApiController 호출됨");
		boardService.글쓰기(board, principal.getUser()); // 로그인 한 유저(자바 시큐리티가 가로챈 정보)를 불러옴
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		boardService.글삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Boards board) {
		boardService.글수정하기(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
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
