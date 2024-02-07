package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class QRegister_CheckController {
	
	    @Autowired
	    private JdbcTemplate jdbcTemplate;
	    @GetMapping("qregister_check")
	    public String handleFormSubmit(HttpServletRequest request, Model model,String text,String subjectID,String questionID,String title,String answer) {
//	        String code = request.getParameter("code");

	    	System.out.println(text);
	    	System.out.println(subjectID);
	    	System.out.println(questionID);
	    	System.out.println(title);
	    	System.out.println(answer);
	        // Insert code into the database
	        jdbcTemplate.update("UPDATE questioncreate SET text=?,subjectID=?,questionID=?,title=?,answer=? WHERE testID=1", text,subjectID,questionID,title,answer);
	        List<Map<String, Object>> qlist = jdbcTemplate.queryForList("SELECT * FROM questioncreate");
		        
	        model.addAttribute("Qlist", qlist);
		        return "qregister_check";
		    }
	    @PostMapping("qregister_check")
	    public String finish(String text,String subjectID,String questionID,String title,String answer){
			java.util.List<Map<String, Object>> checklist = jdbcTemplate.queryForList("SELECT questionID FROM question WHERE questionID = ? and subjectID=?",questionID,subjectID);

			if(checklist.isEmpty()) {
		        jdbcTemplate.update("INSERT INTO question(text,questionID,title,subjectID,answer) VALUES (?,?,?,?,?)", text,questionID,title,subjectID,answer);

	    	}
	        jdbcTemplate.update("UPDATE questioncreate SET text=?,subjectID=?,questionID=?,title=?,answer=? WHERE testID=1", text,subjectID,questionID,title,answer);

	    	return "top";
	    }

	    
	    
	    
	    
	    
	   
}