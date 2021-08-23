package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;


@Entity //User 클래스가 Mysql 에 테이블 생성 된다.
public class User {
	
	@Id  //primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id;  //시퀀스, mysql auto_incremetnt
	
	@Column(nullable = false,length = 30)
	private String username;  // 아이디 
	
	@Column(nullable = false,length = 100) //123456 => 해쉬( 비밀번호 암호화) 할꺼임...
	private String password;
	
	@Column(nullable = false,length = 50)
	private String email;
	
	@ColumnDefault("'user'")
	private String role;  // Enum을 쓰는게 좋다.  --> 도메인(범위)설정 할 수 있다.//admin,user,manager
	
	@CreationTimestamp //시간이 자동입력
	private Timestamp createDate;

}
