package com.example.demo.controller.javakiso;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JavaKiso_TopController {

	@GetMapping("/javakiso/javakiso_top")
	public String subject() {
		return "javakiso/javakiso_top";
	}

}