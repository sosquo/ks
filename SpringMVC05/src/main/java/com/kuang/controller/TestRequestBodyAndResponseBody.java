package com.kuang.controller;

import com.kuang.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestRequestBodyAndResponseBody {


    @RequestMapping(value = "/testBody", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String testBody(@RequestBody User user) {
        System.out.println("user = " + user);
        return "success 欢迎！";
    }
}
