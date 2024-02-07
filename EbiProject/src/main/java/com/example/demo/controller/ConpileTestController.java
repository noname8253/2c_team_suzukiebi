package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.JavaMaker;


@Controller
public class ConpileTestController {

	@Autowired
	JdbcTemplate jdbcTemplate;

    private JavaMaker javaMaker = new JavaMaker();

    public ConpileTestController(JavaMaker javaMaker) {
        this.javaMaker = javaMaker;
    }

    @RequestMapping("/conpiletest")
    public String getCompileForm() {
        return "conpiletest";
    }

    @PostMapping("/conpiletest")
    public String compileCode(String javatestcode, Model model) {
        System.out.println(javatestcode);
    	javaMaker.javaCodeStart(javatestcode);

        // 結果を取得してモデルにセット
    	
        String result = javaMaker.getResult();
        model.addAttribute("result", result);
        
        //正解の取得
        
        String Answer =(""); 
		List<Map<String, Object>> answer = jdbcTemplate.queryForList("SELECT answer FROM question");
		if (!answer.isEmpty()) {
		    Map<String, Object> answerget = answer.get(0);

		    // "code"というキーに対応する値を取得
		    Object Value = answerget.get("answer");

		    // もしString型に変換できる場合は変換して利用
		    if (Value instanceof String) {
		         Answer = (String) Value;
		    } else {
		        System.out.println("codeの値がString型ではありません。");
		    }
		} else {
		    System.out.println("該当するデータがありません。");
		}	
		if(result.equals(Answer)) {
			model.addAttribute("answer","正解！！！");
		}else {
			model.addAttribute("answer","不正解！！！");
		}
        return "conpileresult";
    }
}
