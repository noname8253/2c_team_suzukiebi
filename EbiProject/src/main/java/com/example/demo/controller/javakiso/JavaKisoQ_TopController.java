package com.example.demo.controller.javakiso;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JavaKisoQ_TopController {

	@GetMapping("/javakiso/javakisoq_top")
	public String subject() {
		return "javakiso/javakisoq_top";
	}

}