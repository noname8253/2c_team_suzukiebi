package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


	@Controller
public class TestController {	

@Autowired
JdbcTemplate jdbcTemplate;
	
 @RequestMapping(path = "/test", method = RequestMethod.GET)
	public String test() {
		 
		return "test";
	}
	@RequestMapping(path = "/test", method = RequestMethod.POST)
	public String tests(String code,int number){
		java.util.List<Map<String, Object>> searchlist = jdbcTemplate.queryForList("SELECT articleID FROM article");
		List<Integer> articleIds = searchlist.stream()
		        .map(row -> (Integer) row.get("articleID"))
		        .collect(Collectors.toList());
		int flag = 0;
		for(int i = 0;i<searchlist.size();i++) {
			if(articleIds.get(i)== number){
				flag = 1;
			}
			}
		if(flag == 1) {		
			    jdbcTemplate.update("update article set article=? where articleID=?",code,number);
				System.out.println("アップデート");
			}else {
				jdbcTemplate.update("insert into article (article,articleID) values(?,?)",code,number);
				System.out.println("インサート");
		}
	
		return "test";
	}
}
