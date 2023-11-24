package com.example.demo.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class LoginController {
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(String NUMBER, String PASS,RedirectAttributes redirectAttributes) throws IOException {
		if (NUMBER.equals("2201085") && PASS.equals("1920")) {
			return "redirect:/top";
		} else {
			return "redirect:/login";

		}
	}
	
	}