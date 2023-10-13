package com.kuang.controller;


import com.kuang.service.async.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class HelloController {

    @Autowired
    CalculateService calculateService;

//    http://127.0.0.1:8080/hi?name=cc
    @RequestMapping("/hi")
    @ResponseBody
    public String hello(String name) {
        System.out.println("控制层开始 = " + name);
        calculateService.calculatePlenty(name);
        System.out.println("控制层结束 = " + name);
        return "ok";
    }
}
