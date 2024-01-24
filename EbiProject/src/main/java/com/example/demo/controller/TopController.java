package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class TopController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/top", method = RequestMethod.GET)
	public String top(HttpSession session) {
		
		return "top";
	}
	
	@RequestMapping(path = "/top/{name}", method = RequestMethod.GET)
	public String top(@PathVariable String name) {
		
		return "";
	}

}