package com.cos.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyController {
	
	@Autowired //의존성 주입 DI
	private UserRepository userRepository;
	
	//http://localhostL8000/blog/dummy/jon (요청)
	//http의 body 에 username,password,email 데이터를 가지고 요청  변수명만 맞으면 자동 매핑
	@PostMapping("/dummy/join")
	public String join(User user) {
			System.out.println("username : " + user.getUsername());
			System.out.println("password : " + user.getPassword());
			System.out.println("email : " + user.getEmail());
			
			user.setRole(RoleType.USER);
			userRepository.save(user);
		return "회원가입 완료 !!";
	}

}
