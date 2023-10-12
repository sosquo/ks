package com.kuang.swagger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/get")
    @ResponseBody
    public String getStudent(@RequestParam("uname") String uname) {
        return "你好！" + uname;
    }

}
