package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.config.auth.PrincipalDetailService;

//빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있는 것

@Configuration	//빈 등록 (IOC 관리)
@EnableWebSecurity //시큐리티 필터 등록  =>  스프링 시큐리리티 활성화 되어 있는데 설정을 여기서 하겠다..
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소 접근하면 권한 및 인증을 미리체크함
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean //Ioc가 되요 !! 스프링이 관리한다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	//시큐리티가 대신 로그인해주는데 password 를 가로채기 하는데
	// 해당  password 가 뭘로 해시가 되어 회원가입이 되었는지 알아야
	// 같은 해시로 암호화해서 DB에 있는 해시랑 비교할 수 있음.
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// auth/** 는 누구나 접근 가능 그 외는 로그인
		http
			.csrf().disable()  //csfr 토큰 비활성화(테스트시 걸어두는 게 좋음)
			.authorizeRequests()
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**","/dummy/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and() // /auth/가 아니면 로그인 페이지로 이동
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc") //스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다.
				.defaultSuccessUrl("/");
	}
	
}
