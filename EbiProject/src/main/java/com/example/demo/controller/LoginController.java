package com.example.demo.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class LoginController {
	//画面表示用
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	//ログイン検証用
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(String yuza,int NUMBER, int PASS,RedirectAttributes redirectAttributes) throws IOException {
		if ((NUMBER==2201085)&&(PASS==1920)) {
			return "redirect:/top";
		} else {
			redirectAttributes.addFlashAttribute("yuza", yuza);
			redirectAttributes.addFlashAttribute("NUMBER", NUMBER);
			redirectAttributes.addFlashAttribute("PASS", PASS);
			return "redirect:/login";

		}
	}}
