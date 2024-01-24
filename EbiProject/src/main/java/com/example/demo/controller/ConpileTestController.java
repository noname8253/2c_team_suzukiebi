package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.JavaMaker;

@Controller
public class ConpileTestController {

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

        return "conpileresult";
    }
}
