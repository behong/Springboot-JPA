package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data  //get set
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
@Entity //User 클래스가 Mysql 에 테이블 생성 된다.
//@DynamicInsert //  insert 할때 null 인것 제외하여 db에 전달한다.
public class User {
	
	@Id  //primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id;  //시퀀스, mysql auto_incremetnt
	
	@Column(nullable = false,length = 100,unique = true)
	private String username;  // 아이디 
	
	@Column(nullable = false,length = 100) //123456 => 해쉬( 비밀번호 암호화) 할꺼임...
	private String password;
	
	@Column(nullable = false,length = 50)
	private String email;
	
	//@ColumnDefault("'user")
	//DB는 RoleType이 없다
	@Enumerated(EnumType.STRING)
	private RoleType role;  // Enum을 쓰는게 좋다.  --> 도메인(범위)설정 할 수 있다.//ADMIN,USER
	
	private String oauth; //kakao, google ...
	
	@CreationTimestamp //시간이 자동입력
	private Timestamp createDate;

}
