package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HeaderController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@GetMapping("/header")
	public String question(HttpSession session) {
		
		
		
		return "header";
	}

}