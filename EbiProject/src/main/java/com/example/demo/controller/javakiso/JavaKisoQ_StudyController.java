package com.example.demo.controller.javakiso;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.JavaMaker;

import jakarta.servlet.http.HttpSession;

@Controller
public class JavaKisoQ_StudyController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    private JavaMaker javaMaker = new JavaMaker();

    public JavaKisoQ_StudyController(JavaMaker javaMaker) {
        this.javaMaker = javaMaker;
    }
	
	@RequestMapping(path = "/javakiso/javakisoq_study/{subjectID}/{questionID}", method = RequestMethod.GET)
	public String subject(@PathVariable String subjectID ,@PathVariable String questionID,Model model,HttpSession session) {
/////////////////////////変数とリストの準備		
		int SUbjectID = Integer.parseInt(subjectID);
		int QuestionID = Integer.parseInt(questionID);
		//int SubjectID = Integer.parseInt(subjectID);
		
		java.util.List<Map<String, Object>> questionlist = jdbcTemplate.queryForList("SELECT * FROM question WHERE questionID =? and subjectID=?",QuestionID,SUbjectID);
		
		model.addAttribute("articleList", questionlist);
		return "javakiso/javakisoq_study";
	}
	@RequestMapping(path = "/javakiso/javakisoq_study/{subjectID}/{questionID}", method = RequestMethod.POST)
    public String compileCode(@PathVariable String subjectID ,@PathVariable String questionID,String javatestcode, Model model) {
        System.out.println(javatestcode);
    	javaMaker.javaCodeStart(javatestcode);

        // 結果を取得してモデルにセット
    	int SUbjectID = Integer.parseInt(subjectID);
		int QuestionID = Integer.parseInt(questionID);
        String result = javaMaker.getResult();
        model.addAttribute("result", result);
        
        //正解の取得
        
        String Answer =(""); 
		List<Map<String, Object>> answer = jdbcTemplate.queryForList("SELECT answer FROM question WHERE subjectID = ? AND questionID=? ",SUbjectID,QuestionID);
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
		
		Answer = Answer.replaceAll("[\\r\\n\\s]+", "");
		result = result.replaceAll("[\\r\\n\\s]+", "");
		
		if(result.equals(Answer)) {
			model.addAttribute("answer","正解！！！");
		}else {
			model.addAttribute("answer","不正解！！！");
		}
		List<Map<String, Object>> kaisetu = jdbcTemplate.queryForList("SELECT Explanation FROM question WHERE subjectID = ? AND questionID=? ",SUbjectID,QuestionID);
		String explanationText=("");
		if (!kaisetu.isEmpty()) {
		    Map<String, Object> kaisetuget = kaisetu.get(0);
		    Object explanationValue = kaisetuget.get("Explanation");

		    if (explanationValue != null) {
		         explanationText = explanationValue.toString();}
		}
		model.addAttribute("explanation",explanationText);
		model.addAttribute("correct",Answer);
        return "conpileresult";
    }
}
