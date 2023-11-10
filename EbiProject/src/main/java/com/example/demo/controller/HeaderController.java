package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HeaderController {

	@GetMapping("/header")
	public String question() {
		return "header";
	}

}