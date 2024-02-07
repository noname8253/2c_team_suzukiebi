package com.example.demo.controller;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
        model.addAttribute("processedCodes", processedCodes.get(0).get("code"));
	    	//ここでgithubにデータを送り、レスポンスを受け取ってDBに格納したい
	        // GitHub APIエンドポイント
	        String githubApiUrl = "https://api.github.com/markdown/raw";
	        //String githubToken = "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";
	        
	        
	        // GitHub APIへのリクエストヘッダー設定
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Content-Type", "text/plain; charset=UTF-8"); // UTF-8を指定

	        /**HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization", "token " + githubToken);
	        headers.set("X-GitHub-Api-Version", "2022-11-28");
	        headers.setContentType(MediaType.TEXT_PLAIN);
**/
	        // GitHub APIへのリクエストボディ設定
//			// testcodeから文字列に変換
	        List<Map<String, Object>> testcodee = jdbcTemplate.queryForList("SELECT code FROM codecreate where testID = 1");
	        
	        String markdownText = testcodee.stream()
	                .map(map -> map.get("code").toString()) // マップから"code"に対応する値を取得
	                .collect(Collectors.joining("\n"));	
	        
	        byte[] utf8Bytes = markdownText.getBytes(StandardCharsets.UTF_8);
	        markdownText = new String(utf8Bytes, StandardCharsets.UTF_8);

	     
	     // 以下、同じ
	     HttpEntity<String> requestEntity = new HttpEntity<>(markdownText, headers);
	     // ...

	        // GitHub APIにリクエストを送信
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> responseEntity = restTemplate.exchange(
	                githubApiUrl,
	                HttpMethod.POST,
	                requestEntity,
	                String.class
	        );
	        
	        System.out.println("Status code: " + responseEntity.getStatusCode());
	        System.out.println("Headers: " + responseEntity.getHeaders());


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
