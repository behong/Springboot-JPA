package com.cos.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;

//스프링이 컴포넌 스캔해서 Bean 등록해줌 IOC
@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	

	@Transactional
	public void 글쓰기(Board board, User user) {
		//DB 인서트
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);

	}


	public List<Board> 글목록() {
		return boardRepository.findAll();
	}

}
