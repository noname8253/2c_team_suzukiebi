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
	
	@RequestMapping(path = "/javakiso/javakiso_study/{subjectID}/{courceID}", method = RequestMethod.GET)
	public String subject(@PathVariable String courceID ,@PathVariable String subjectID,Model model,HttpSession session) {
/////////////////////////変数とリストの準備		
		String rebox=("");
		String retitle = ("");
		int SUbjectID = Integer.parseInt(subjectID);
		int CourceID = Integer.parseInt(courceID);
		//int SubjectID = Integer.parseInt(subjectID);
		
		java.util.List<Map<String, Object>> articlelist = jdbcTemplate.queryForList("SELECT code FROM cource WHERE courceID =? and subjectID=?",CourceID,SUbjectID);
		java.util.List<Map<String, Object>> courcelist = jdbcTemplate.queryForList("SELECT * FROM cource WHERE courceID =? and subjectID=?",CourceID,SUbjectID);
		
//////////////////////////本文をhtmlコードから変換/////////////////////////////
		if (!articlelist.isEmpty()) {
		    Map<String, Object> firstArticle = articlelist.get(0);
		    Map<String, Object> firstTitle = courcelist.get(0);

		    // "code"というキーに対応する値を取得
		    Object codeValue = firstArticle.get("code");
		    Object titleValue = firstTitle.get("title");

		    // もしString型に変換できる場合は変換して利用
		    if (codeValue instanceof String) {
		         rebox = (String) codeValue;
		         retitle = (String) titleValue;
		    } else {
		        System.out.println("codeの値がString型ではありません。");
		    }
		} else {
		    System.out.println("該当するデータがありません。");
		}
		model.addAttribute("articleList", rebox);
		model.addAttribute("courceList", retitle);
		return "javakiso/javakiso_study";
	}
	

}