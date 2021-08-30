package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;


// DAO
// 자동으로 bean 등록이 된다.
//@Repository  //생략 가능
public interface UserRepository extends JpaRepository<User, Integer>{
	//로그인을 위한 함수 
	//JPA Naming 전략 쿼리
	// SELECT * FROM user WHERE username= ? AND password= ?;
	
	// 스프링 시큐리트 사용으로 주석처리
	// User findByUsernameAndPassword(String username, String password);
}
