package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@GetMapping({"","/"})
	public String index(Model model) {  // 스프링 시큐리티 세션은...
		model.addAttribute("boards",boardService.글목록());
		return "index";   //viewResolver 작동 !!
	}
	
	// User 권한이 필요
	@GetMapping({"/board/saveForm"})
	public String saveForm() {  // 스프링 시큐리티 세션은...
		return "board/saveForm";
	}
}
