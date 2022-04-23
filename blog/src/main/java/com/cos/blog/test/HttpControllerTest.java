package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {
	//http:localhost:8083/http/get(select)
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청 " + m.getId() + " " + m.getUsername();
	}
	
	//http:localhost:8083/http/post(insert)
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return "post 요청 " + m.getId() + " " + m.getUsername();
	}
	
	//http:localhost:8083/http/put(update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청" + m.getId() + " " + m.getUsername();
	}
	
	//http:localhost:8083/http/delete(delete)
	@DeleteMapping("/http/delete")
	public String deleteTest(@RequestBody Member m) {
		return "delete 요청" + m.getId() + " " + m.getUsername();
	}
	
	//http:localhost:8083/http/lombok
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("kang").password("1234").email("email").build();
		System.out.println("getter : " + m.getUsername());
		m.setUsername("ddu");
		System.out.println("setter : " + m.getUsername());
		return "lombok test";
	}
}
