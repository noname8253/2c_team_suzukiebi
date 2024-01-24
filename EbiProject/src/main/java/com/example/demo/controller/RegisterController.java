package com.example.demo.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String getHomePage(Model model) {
        // Retrieve processed codes from the database
        List<String> processedCodes = jdbcTemplate.query("SELECT code FROM codecreate WHERE testID=1", new CodeRowMapper());
        model.addAttribute("processedCodes", processedCodes);

        return "register";
    }

    @PostMapping("register")
    public String handleFormSubmit(HttpServletRequest request, Model model,String code,String subjectID,String courceID,String title) {
//        String code = request.getParameter("code");

        // Insert code into the database
        jdbcTemplate.update("UPDATE codecreate SET code=?,subjectID=?,courceID=?,title=? WHERE testID=1", code,subjectID,courceID,title);

        // Retrieve processed codes from the database
        List<Map<String,Object>> processedCodes = jdbcTemplate.queryForList("SELECT code FROM codecreate WHERE testID=1");
        String testcode = (String) processedCodes.get(0).get("code");
        model.addAttribute("processedCodes", processedCodes.get(0).get("code"));
	    	//ここでgithubにデータを送り、レスポンスを受け取ってDBに格納したい
	        // GitHub APIエンドポイント
	        String githubApiUrl = "https://api.github.com/markdown/raw";
	        String githubToken = "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";
	        
	        
	        // GitHub APIへのリクエストヘッダー設定
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization", "token " + githubToken);
	        headers.set("X-GitHub-Api-Version", "2022-11-28");
	        headers.setContentType(MediaType.TEXT_PLAIN);

	        // GitHub APIへのリクエストボディ設定
//			List<Map<String, Object>> testcode = jdbcTemplate.queryForList("SELECT code FROM codecreate where testID = 1");		
	        String markdownText = testcode.toString();//変数名にして右側に表示する
	        HttpEntity<String> requestEntity = new HttpEntity<>(markdownText, headers);

	        // GitHub APIにリクエストを送信
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> responseEntity = restTemplate.exchange(
	                githubApiUrl,
	                HttpMethod.POST,
	                requestEntity,
	                String.class
	        );

	        // レスポンスの本文（HTML形式のテキスト）を取得
	        String responseBody = responseEntity.getBody();

	        // ここでDBに responseBody を格納する処理を行う（DBへの接続やデータの操作）
	        System.out.println(responseBody);
	        jdbcTemplate.update("UPDATE codecreate SET code = ? WHERE testID=1", responseBody);
	        // レスポンスをModelに設定
	        model.addAttribute("htmlContent", responseBody);

	        return "register";
	    }
    private static class CodeRowMapper implements RowMapper<String> {
        @Override
        public String mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getString("code");
        }
    }
}
