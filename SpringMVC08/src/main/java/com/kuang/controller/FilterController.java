package com.kuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/filter")
public class FilterController {

    @RequestMapping("/test")
    public void test() {
        System.out.println("enter Method -- test");
    }
}
