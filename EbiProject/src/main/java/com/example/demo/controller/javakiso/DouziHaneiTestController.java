package com.example.demo.controller.javakiso;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DouziHaneiTestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "javakiso/douzihanneitest", method = RequestMethod.GET)
    public String getHomePage(Model model) {
        // Retrieve processed codes from the database
        List<String> processedCodes = jdbcTemplate.query("SELECT code FROM codecreate", new CodeRowMapper());
        model.addAttribute("processedCodes", processedCodes);

        return "javakiso/douzihanneitest";
    }

    @PostMapping("javakiso/douzihanneitest")
    public String handleFormSubmit(HttpServletRequest request, Model model) {
        String code = request.getParameter("code");

        // Insert code into the database
        jdbcTemplate.update("UPDATE codecreate SET code = ?", code);

        // Retrieve processed codes from the database
        List<String> processedCodes = jdbcTemplate.query("SELECT code FROM codecreate", new CodeRowMapper());
        model.addAttribute("processedCodes", processedCodes);

        return "javakiso/douzihanneitest";
    }

    private static class CodeRowMapper implements RowMapper<String> {
        @Override
        public String mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getString("code");
        }
    }
}
