package com.kuang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @RequestMapping("test")
    public String test() {
        return "success！~~";
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/user")
    public List testAddUser() {
        String[] args = new String[]{"敏敏", "20"};
        insert(args);
        return getList();
    }


    private void insert(String[] args) {
        jdbcTemplate.update("insert into t_user_1 (name, age)values (?, ?)", args);
    }

    List getList() {
        return jdbcTemplate.queryForList("select * from t_user_1");
//        System.out.println("maps = " + maps);
    }
}
