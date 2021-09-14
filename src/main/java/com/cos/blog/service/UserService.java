package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌 스캔해서 Bean 등록해줌 IOC
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;	
	
	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		return userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
	}

	@Transactional
	public void 회원가입(User user) {
		//DB 인서트
		String rewPassword = user.getPassword(); //원문
		String encPassword = encoder.encode(rewPassword); //해쉬
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
		/*
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UserService 회원가입() "+ e.getMessage());
		}
		return -1;
		*/
	}

	@Transactional
	public void 회원수정(User user) {
		// 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고,영속화된 User 오브젝트를 수정
		// Select를 해서 User오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기위해
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update
		
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		// oauth값이 없을때만 패스워드 수정 처리..
		if(persistance.getOauth() == null || persistance.getOauth().equals("")) {
			String rewPassword = user.getPassword(); //원문
			String encPassword = encoder.encode(rewPassword); //해쉬
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}
		//회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료  = commit 이 자동으로 됨
		//영속화된 persistance 객체의 변화가 감지되면 더티채킹 되어 update 문을 날려줌
	}
	
	
	
	/*
	 * @Transactional(readOnly = true) // select 할 때 트랜잭션 시작,서비스 종료시에 트랜잭션 종료(정합성)
	 * public User 로그인(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword(
	 * )); }
	 */
}
