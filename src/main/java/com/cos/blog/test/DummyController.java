package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


// html 이 아니라 Data를 리턴해주는 컨트롤러
@RestController
public class DummyController {
	
	@Autowired //의존성 주입 DI
	private UserRepository userRepository;
	
	// {id} 주소로 파라미터 전달
	// 요청 url : http://localhostL8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		// user/4 을 찾으면 데이터베이스에서 못찾아오면 user 는 null 이 될 것 아니냐???
		// return 에 null 이 들어간다...
		// Optional 로 너의 객체 가져올테니 null 체크 해서 리턴해
		// orElseThrow 없으면 IllegalArgumentException 예외 전달한다.
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id );
			}
		});
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 변환 (웹브라우저가 이해할 수 있는 데이터 -> json (Gson 라이브러리)
		// 스프링부트는 MessageConverter라는 것이 응답시 자동 작동
		// 만약 자바 오브젝트를 리턴하게 되면 MessageConverter 가 Jackson 라이브러를 호출해서
		//  user 오브젝트를 json 으로 변환하여 브라우저에게 전달함
		return user;
	}
	// email, password //json 으로 req 받기
	// json 요청을 -> 자바 오브젝트로 변환하여 받아줌 Jackson 이 ㅎㅎㅎ(MessageConverter)
	@Transactional //함수종료시 자동으로 커밋
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id :" +id );
		System.out.println("email :" +requestUser.getEmail() );
		System.out.println("password :" +requestUser.getPassword() );
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정 실패 하였습니다. id : " + id );
		});
		//영속성 ,더티채킹
		// 객체 변경
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		// @Transactional 이용으로 save 필요없음..
		//userRepository.save(user);
		return user;
	}
	
	//삭제
	@DeleteMapping("/dummy/user/{id}")
	public String userDelete(@PathVariable int id ) {
		
		try {
			userRepository.deleteById(id);	
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 아이디 "+ id;
		}
		
		return "삭제되었습니다. id : "+id;
	}
	
	
	// 요청 url : http://localhostL8000/blog/dummy/user
	@GetMapping("/dummy/user")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한페이지당 2건 데이터를 리턴
	@GetMapping("/dummy/user/page")
	public List<User> pagelist(@PageableDefault(size=2,sort="id",direction =Sort.Direction.DESC ) Pageable pageable){
		Page<User> pagingUser= userRepository.findAll(pageable);
		// 여러가지 분기 처리 가능..
		if( pagingUser.isLast()) {
			
		}
		List<User> users = pagingUser.getContent();
		return users;
	}
	
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
