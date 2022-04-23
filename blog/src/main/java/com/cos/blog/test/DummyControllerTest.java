package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UserRepository;

@RestController // 데이터만 리턴
public class DummyControllerTest {

	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;

	// 전체 가져오기
	// http://localhost:8083/dummy/user
	@GetMapping("/dummy/user")
	public List<Users> list() {
		return userRepository.findAll();
	}

	// 정해진 개수만큼 select
	@GetMapping("/dummy/user/page")
	public List<Users> pagelist(
			@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Users> pagingUser = userRepository.findAll(pageable);
		if (pagingUser.isLast()) {
			System.out.println("마지막 요소입니다.");
		}
		List<Users> users = pagingUser.getContent();
		return users;
	}

	// {id} 주소로 파라미터를 전달받을 수 있음
	// http://localhost:8083/dummy/user/id
	@GetMapping("/dummy/user/{id}")
	public Users detail(@PathVariable int id) {
		// DB에는 id가 4가지 밖에 없는데, 만약 주소에 5를 넣으면 null이 리턴될 것
		// 그렇게 되면 에러가 발생하게 되어 optional로 감싸서 리턴됨
		// 리턴 전에 null인지 아닌지 판단이 필요함
		Users user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당유저는 없습니다. id : " + id);
			}
		});
		return user;
	}

	// http://localhost:8083/dummy/join
	// http의 body에 username, password, email 데이터를 가지고 요청
	@PostMapping("dummy/join")
	public String join(Users users) {
		System.out.println("id: " + users.getId());
		System.out.println("username: " + users.getUsername());
		System.out.println("password: " + users.getPassword());
		System.out.println("email: " + users.getEmail());
		System.out.println("role: " + users.getRoles());
		System.out.println("createDate: " + users.getCreateDate());

		users.setRoles(RoleType.USER);
		userRepository.save(users);
		return "회원가입이 완료되었습니다.";
	}
	
	//////////////////////////////
	// Update
	
	@Transactional //더티체킹
	@PutMapping("/dummy/user/{id}")
	public Users updateUser(@PathVariable int id, @RequestBody Users requestUser) {
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		Users user = userRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		user.setUsername(requestUser.getUsername());
		
//		userRepository.save(user);
		
		return null;
	}
	
	////////////////////////
	// DELETE
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			return "삭제 실패. 해당 아이디 없음. id : " + id;
		}
		return "삭제완료. id : " + id;
	}
}
