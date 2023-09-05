package com.kuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/jumplogin")
    public String jumpLogin() {
        return "login";
    }

    @RequestMapping("/jumpSuccess")
    public String jumpSuccess() {
        return "success";
    }

    @RequestMapping("/login")
    public String login(HttpSession session, String username, String pwd){
        session.setAttribute("user", username);
        return "success";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
//        让session 过期
        session.invalidate();
        return "login";
    }
}
