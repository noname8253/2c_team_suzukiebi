package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
@Controller
public class LoginController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
    
	//ログイン画面への遷移
	@RequestMapping(path = "/login", method = RequestMethod.GET)
    String getLogin() {
        return "login";
    }
	
    //ログイン成功時のメニュー画面への遷移
	@RequestMapping(path = "/login", method = RequestMethod.POST)
    public String top(int password,int number,Model model,HttpSession session) {
		
		List<Map<String, Object>> passlist = jdbcTemplate.queryForList("SELECT * FROM user WHERE s_number = ? and password = ?",number,password);		
		if(passlist.size() != 0) {
			
		    Object NameValue = passlist.get(0).get("name");
			String NameString = NameValue.toString();
			System.out.println(NameString);
			
			session.setAttribute("Name",NameString);
						return "redirect:/top";
					}else {
						return "redirect:/login";
				}
			}
	}
	
    
