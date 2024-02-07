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
public class JavaKiso_TopController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(path = "/javakiso/javakiso_top/{subjectID}", method = RequestMethod.GET)
	public String subjectget(@PathVariable("subjectID") String subjectID,Model model,HttpSession session) {
		int SubjectID = Integer.parseInt(subjectID);
		session.setAttribute("subjectID",SubjectID);
		java.util.List<Map<String, Object>> courcelist = jdbcTemplate.queryForList("SELECT * FROM cource WHERE subjectID =?",SubjectID);
		model.addAttribute("CourceList", courcelist);		
		return "javakiso/javakiso_top";
//		return "javakiso/javakiso_top/{subjectID}";
	}
}