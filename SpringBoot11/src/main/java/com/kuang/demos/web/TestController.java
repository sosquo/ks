package com.kuang.demos.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
public class TestController {

    @RequestMapping("/t1")
    public String test(Model model) {
        model.addAttribute("msg", "敏敏");
        return "test";
    }

    @RequestMapping("/t2")
    public String test2(Model model) {
        model.addAttribute("msg", "<h1>奈斯</h1>");
        model.addAttribute("users", Arrays.asList("小爱", "小安"));
        return "test2";
    }
}
