package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있는 것

@Configuration	//빈 등록 (IOC 관리)
@EnableWebSecurity //시큐리티 필터 등록  =>  스프링 시큐리리티 활성화 되어 있는데 설정을 여기서 하겠다..
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소 접근하면 권한 및 인증을 미리체크함
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// auth/** 는 누구나 접근 가능 그 외는 로그인
		http
			.authorizeRequests()
				.antMatchers("/auth/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and() // /auth/가 아니면 로그인 페이지로 이동
				.formLogin()
				.loginPage("/auth/loginForm");
	}
	
}
