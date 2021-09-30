package com.cos.blog.restTemplete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.restTemplete.model.MemberDTO;
import com.cos.blog.restTemplete.service.RestTempleteService;
import com.cos.blog.service.BoardService;

@Controller
public class RestTempController {
	
 @Autowired
 RestTempleteService restTempleteService;
 
 @GetMapping("/getTest1")
 public String getTest1() {
	 return restTempleteService.getTest();
 }

 
 @GetMapping("/getMember")
 public ResponseEntity<MemberDTO> getMember() {
	 return restTempleteService.getMember();
 }

 @GetMapping("/addHeader")
 public ResponseEntity<MemberDTO> addHeader() {
	 return restTempleteService.addHeader();
 }
 
 @GetMapping("/list")
 public Object[] getList() {
	 return restTempleteService.restFindAll();
 }
 
}
