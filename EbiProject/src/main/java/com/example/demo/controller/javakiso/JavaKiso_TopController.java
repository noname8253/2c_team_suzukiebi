package com.example.demo.controller.javakiso;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JavaKiso_TopController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("javakiso/javakiso_top")
	public String javakiso_top(Model model) {
		
		List<Map<String, Object>> resultList;
		String resultlist = ("");
		resultList = jdbcTemplate.queryForList("select 科目名 from 科目");
		model.addAttribute(resultlist,resultList);
		
		return "javakiso/javakiso_top";
	}

}