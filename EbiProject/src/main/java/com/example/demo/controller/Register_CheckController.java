package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Register_CheckController {
	
	    @Autowired
	    private JdbcTemplate jdbcTemplate;

	    
	    
	    
	    
	    
	    
	   
	    @RequestMapping(value = "/register_check", method = RequestMethod.GET)
	    public String getHomePage(Model model) {
	        // Retrieve processed codes from the database
			java.util.List<Map<String, Object>> relist = jdbcTemplate.queryForList("SELECT courceID,code,subjectID,title FROM codecreate WHERE testID=1");
	        model.addAttribute("relist", relist);
	        return "register_check";
	    }
	    
	    
	    
	    
	    
	    
	    @RequestMapping(value = "/register_check", method = RequestMethod.POST)
	    public String postHomePage(Model model,String subjectID,String courceID,String code,String title) {
	        // Retrieve processed codes from the database
	    	System.out.println(code+courceID+title+subjectID);
			java.util.List<Map<String, Object>> checklist = jdbcTemplate.queryForList("SELECT courceID FROM cource WHERE courceID = ? and subjectID=?",courceID,subjectID);
			
			if(checklist.isEmpty()) {
		        jdbcTemplate.update("INSERT INTO cource(code,courceID,title,subjectID) VALUES (?,?,?,?)", code,courceID,title,subjectID);
		        System.out.println("新しい回数");
			}else {
	        jdbcTemplate.update("UPDATE cource SET code=?,courceID=?,title=? where subjectID=? and courceID = ?", code,courceID,title,subjectID,courceID);
	        		
			}
	        return "top";
	    }
	    
	   
	    	
	}