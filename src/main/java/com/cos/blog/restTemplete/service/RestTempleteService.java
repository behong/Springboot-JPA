package com.cos.blog.restTemplete.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.TypeUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cos.blog.restTemplete.model.MemberDTO;

@Service
public class RestTempleteService {
	
	private final Logger LOGGER= LoggerFactory.getLogger(RestTempleteService.class);
	
	public String getTest() {
		LOGGER.info("getTest start :{} " );
		URI  uri = UriComponentsBuilder
				.fromUriString("http://localhost:8833")
				.path("/api/server/test1")
				.encode()
				.build()
				.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		
		LOGGER.info("status code :{} ", responseEntity.getStatusCode()   );
		LOGGER.info("body :{} ", responseEntity.getBody()   );
		
		return responseEntity.getBody();
	}
	
	public ResponseEntity<MemberDTO> getMember() {
		LOGGER.info("getMember start :{} " );
		
		URI  uri = UriComponentsBuilder
				.fromUriString("http://localhost:8833")
				.path("/api/server/getMember")
				.queryParam("name", "지유소유")
				.queryParam("email", "jisou@email.com")
				.queryParam("organnization", "jusou")
				.encode()
				.build()
				.toUri();		
		
		//dto도 전달할 수 있다.
		MemberDTO mem = new MemberDTO();
		mem.setName("아빠");
		mem.setEmail("father@email.com");
		mem.setOrganization("nayha");
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<MemberDTO> responseEntity = restTemplate.postForEntity(uri, mem, MemberDTO.class);

		LOGGER.info("status code :{} ", responseEntity.getStatusCode()   );
		LOGGER.info("body :{} ", responseEntity.getBody()   );
		
		return responseEntity;
	}
	
	public ResponseEntity<MemberDTO> addHeader() {
		LOGGER.info("addHeader start :{} " );
		
		URI  uri = UriComponentsBuilder
				.fromUriString("http://localhost:8833")
				.path("/api/server/addHeader")
				.queryParam("name", "지유소유")
				.queryParam("email", "jisou@email.com")
				.queryParam("organnization", "jusou")
				.encode()
				.build()
				.toUri();		
		
		
		MemberDTO mem = new MemberDTO();
		mem.setName("아빠");
		mem.setEmail("father@email.com");
		mem.setOrganization("nayha");
		
		//헤더 생성
		RequestEntity<MemberDTO> requestEntity = RequestEntity
												 .post(uri)
												 .header("customHeader", "커스텀 헤더")
												 .body(mem);
				
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<MemberDTO> responseEntity = restTemplate.exchange(requestEntity, MemberDTO.class);

		LOGGER.info("status code :{} ", responseEntity.getStatusCode()   );
		LOGGER.info("body :{} ", responseEntity.getBody()   );
		
		return responseEntity;
	}	
	
	public Object[] restFindAll() {
		
		LOGGER.info("addHeaderList start :{} " );
		
		URI  uri = UriComponentsBuilder
				.fromUriString("http://localhost:8833")
				.path("/api/server/addHeaderList")
				.encode()
				.build()
				.toUri();			
		
		MemberDTO mem = new MemberDTO();
		mem.setName("아빠");
		mem.setEmail("father@email.com");
		mem.setOrganization("nayha");
		
		List<Object> list = new ArrayList<>();
		
		list.add(mem);
		//헤더 생성
		RequestEntity<List<?>> requestEntity = RequestEntity
												 .post(uri)
												 .header("customHeader", "커스텀 헤더")
												 .body(list);		
		
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Object[]> responseEntity = restTemplate.exchange(requestEntity,Object[].class);
		Object[] objects = responseEntity.getBody();
		return objects;
	}
}
