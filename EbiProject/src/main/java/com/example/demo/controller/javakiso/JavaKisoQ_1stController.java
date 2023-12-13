package com.example.demo.controller.javakiso;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JavaKisoQ_1stController {

	@GetMapping("/javakiso/javakisoq_1st")
	public String subject() {
		return "javakiso/javakisoq_1st";
	}

}