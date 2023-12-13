package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/login")
public class LoginController {
    //ログイン画面への遷移
    @GetMapping
    String getLogin() {
        return "login";
    }
    //ログイン成功時のメニュー画面への遷移
    @PostMapping
    public static String top(String password,String number) {
        if (number.equals("2201085") && password.equals("1920") ){
            System.out.println("ログイン成功！");
            return "redirect:/top";
        } else {
            System.out.println("ログイン失敗。");
    		return "redirect:/login";
    	}
    }
}