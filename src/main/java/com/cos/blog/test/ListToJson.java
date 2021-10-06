package com.cos.blog.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListToJson {

	public static void main(String[] args) {
		
		HashMap<String,String> map = new HashMap<String,String>(){{	//초기값 지정
		    put("1","사과");
		    put("2","바나나");
		    put("Type","포도");
		}};		
		
		List<String> list1 = new ArrayList<>();
		list1.add(map.get("1"));
		list1.add(map.get("2"));
		list1.add(map.get("Type"));
		
		System.out.println(list1);
		System.out.println(list1.toString());
		
		
		
	}
			

}
