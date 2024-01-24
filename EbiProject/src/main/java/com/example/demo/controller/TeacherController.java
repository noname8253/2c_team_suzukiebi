package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TeacherController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public String displayTeacher(Model model) {
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM teacher");
            model.addAttribute("selectResult", resultList);
            
            return "/teacher";
        }
}