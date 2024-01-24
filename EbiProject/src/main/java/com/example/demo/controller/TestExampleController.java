package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestExampleController {	

	
 @RequestMapping(path = "/testexample", method = RequestMethod.GET)
	public String test() {
		 
	 	System.out.println("aaaaaaaa");
	 
		return "test";
	}
}
