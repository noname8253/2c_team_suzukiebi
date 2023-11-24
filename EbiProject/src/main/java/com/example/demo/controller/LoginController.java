package com.example.demo.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    //ログイン画面への遷移
	@RequestMapping(path = "/login", method = RequestMethod.GET)
    public String getLogin() {
        return "login";
        }
    

    //ログイン成功時のメニュー画面への遷移
<<<<<<< HEAD
    @PostMapping
    String top() {
        return "top";
    }
}
=======
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(String login-name ,String login-pass,RedirectAttributes redirectAttributes) throws IOException {
		if (login-name.equals("2201085") && login-pass.equals("1920")) {
			return "/top";
		} else {
			redirectAttributes.addFlashAttribute("yuza", yuza);
			redirectAttributes.addFlashAttribute("ID", ID);
			redirectAttributes.addFlashAttribute("PASS", PASS);
			return "redirect:/ng";

		}
	}
>>>>>>> branch 'master' of https://github.com/noname8253/2c_team_suzukiebi.git
