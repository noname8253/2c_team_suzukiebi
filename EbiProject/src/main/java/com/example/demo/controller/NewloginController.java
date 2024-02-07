package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NewloginController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(path = "/newlogin", method = RequestMethod.GET)
    String newgetlogin() {
        return "newlogin";
    }
	@RequestMapping(path = "/newlogin", method =  RequestMethod.POST)
    public String newpostlogin(Model model,String name,String number,String pass,String syozoku) {
		int Snumber = Integer.parseInt(number);
		int Password = Integer.parseInt(pass);
		int lennum = String.valueOf( Snumber ).length();
		int lenpass = String.valueOf( Password ).length();
		
		if(lennum !=7 || lenpass != 4) {
			model.addAttribute("e","パスワードは4桁数字、学籍番号は7桁数字で入力してください");
			return "newlogin";
		}else {
			System.out.println(syozoku);
			System.out.println(name);
			System.out.println(number);
			System.out.println(pass);
/////////////////////////////////////////SQLのエラーでNumberというカラムが存在しないと言われる//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        jdbcTemplate.update("INSERT INTO krcsite.user(name,snumber,password,class) VALUES (?,?,?,?)",name,Snumber,Password,syozoku);
		}
		return "login";
}
	}
