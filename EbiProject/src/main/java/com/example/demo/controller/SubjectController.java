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
public class SubjectController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	
	
	@RequestMapping(path = "/subject", method = RequestMethod.GET)
	public String subject(Model model,HttpSession httpsession) {
		
		java.util.List<Map<String, Object>> subjectlist = jdbcTemplate.queryForList("SELECT * FROM krcsite.subject");
		model.addAttribute("SubjectList", subjectlist);		
		System.out.println(subjectlist.get(0));
		return "subject";
	}

}