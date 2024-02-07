package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class QuestionController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(path = "/question", method = RequestMethod.GET)
	public String question(Model model,HttpSession httpsession) {
		
		java.util.List<Map<String, Object>> questionlist = jdbcTemplate.queryForList("SELECT * FROM krcsite.subject");
		model.addAttribute("QuestionList", questionlist);		
		System.out.println(questionlist.get(0));
		return "question";
	}


}