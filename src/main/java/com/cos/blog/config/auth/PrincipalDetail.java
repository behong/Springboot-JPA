package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

public class PrincipalDetail implements UserDetails {
	private User user;  //콤포지션  객체를 품고 있는것
	
	public PrincipalDetail(User user) {
		this.user = user;
	}

	// spring 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료 되면 UserDetails 타입의 오브젝트를
	// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정 만료 유무체크
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정 잠겼는지 체크
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호 만료 여부
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정 활성화
	@Override
	public boolean isEnabled() {
		return true;
	}

	//계정의 권한 (권한이 여러개면 루프를 돌아야한다)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		
		/*
		 * collectors.add(new GrantedAuthority() {
		 * 
		 * @Override public String getAuthority() { return "ROLE_"+user.getRole();
		 * //ROLE_USER 가 리턴.. } });
		 */
		
		collectors.add(()->{ return  "ROLE_"+user.getRole();	});
		
		return collectors;
	}

}
