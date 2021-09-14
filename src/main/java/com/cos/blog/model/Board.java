package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  //get set
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private int id;
	
	@Column(nullable = false,length = 100)
	private String title;
	
	@Lob //대용량 데이터
	private String content; //섬머노트 라이브러리 <html> 태그가 포함되어서 디자인 됨.
	
	private int count; //조회수
	
	//사용자..
	@ManyToOne  //Many = Board User = one   한명은 여러 게시글(Board)을 작성할 수 있다..
	@JoinColumn(name="userId")
	private User user;  //db는 오브젝트를 저장할 수없다. FK 자바는 오브젝트를 저장할 수 있다.
	
	@OneToMany(mappedBy = "board") // 하나의 Board에 여러개 Reply(답변) .. // map 연관관계의 주인이 아니다 FK아니다
	@JsonIgnoreProperties({"borad"})
	private List<Reply> replys;
	
	@CreationTimestamp
	private Timestamp createDate;

}
