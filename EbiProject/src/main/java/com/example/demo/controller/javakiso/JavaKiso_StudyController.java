package com.example.demo.controller.javakiso;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class JavaKiso_StudyController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(path = "/javakiso/javakiso_study/{kiziID}", method = RequestMethod.GET)
	public String subject(Model model,HttpSession session,@PathVariable String kiziID) {
		
		java.util.List<Map<String, Object>> articlelist = jdbcTemplate.queryForList("SELECT * FROM article WHERE articleID =?",kiziID);
		model.addAttribute("articleList", articlelist);
		return "javakiso/javakiso_study";
	}

}