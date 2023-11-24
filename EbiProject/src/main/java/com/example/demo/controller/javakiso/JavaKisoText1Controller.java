package com.example.demo.controller.javakiso;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JavaKisoText1Controller {

	@GetMapping("/javakiso/javakisotext1")
	public String javakisotext1() {
		return "javakiso/javakisotext1";
	}

}