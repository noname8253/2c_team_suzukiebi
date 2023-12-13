package com.example.demo.controller.javakiso;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DouziHaneiTestController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//コピペ用サンプル(ページ表示用メソッド)
	@RequestMapping(path = "javakiso/douzihanneitest", method = RequestMethod.GET)
	public String copGet(Model model) {
		java.util.List<Map<String, Object>> codelist = jdbcTemplate.queryForList("SELECT * FROM codecreate");
        model.addAttribute("codeList", codelist);        
		return "javakiso/douzihanneitest";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "javakiso/douzihanneitest", method = RequestMethod.POST)
	public String copPost(String code, Model model) {
	    jdbcTemplate.update("update codecreate set code=?",code);
		java.util.List<Map<String, Object>> codelist = jdbcTemplate.queryForList("SELECT * FROM codecreate");
        model.addAttribute("codeList", codelist);        
		return "javakiso/douzihanneitest";
	}
}
