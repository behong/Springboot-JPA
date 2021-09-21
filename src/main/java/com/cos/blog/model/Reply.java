package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.blog.dto.ReplySaveRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	//누가 어느게시글에 있는 답변인가..

	@ManyToOne	//연관관계  게시글에는 여러 답변이 가능하다
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne	//한명의 유저는 여러개 답변을 남길수 있다.
	@JoinColumn(name="userId")
	private User user;

	@CreationTimestamp
	private Timestamp createDate;
	
}
