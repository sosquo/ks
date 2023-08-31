package com.kuang.controller;

import com.kuang.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(String name) {
        System.out.println("name = " + name);
        return "hello";
    }

    @RequestMapping("/hello1")
    public String hello1(@RequestParam("name") String username) {
        System.out.println("name = " + username);
        return "hello";
    }

    @RequestMapping("/hello2")
    public String hello2(User user) {
        System.out.println("user = " + user);
        return "hello";
    }

    @RequestMapping("/hello3")
    public String hello3(User user, Model model) {
        model.addAttribute("msg", user.getName());
        return "hello";
    }

    @RequestMapping("/hello4")
    public String hello4(User user, ModelMap modelMap) {
        modelMap.addAttribute("msg", user.getName());
        return "hello";
    }

    @RequestMapping("/hello5")
    public String hello4(@RequestParam("name") String name, ModelMap modelMap) {
        System.out.println("name = " + name);
        modelMap.addAttribute("msg", name);
        return "hello";
    }
}
