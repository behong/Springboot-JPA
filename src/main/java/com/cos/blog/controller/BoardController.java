package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	@GetMapping({"","/"})
	public String index() {  // 스프링 시큐리티 세션은...
		return "index";
	}
	
	// User 권한이 필요
	@GetMapping({"/board/saveForm"})
	public String saveForm() {  // 스프링 시큐리티 세션은...
		return "board/saveForm";
	}
}
