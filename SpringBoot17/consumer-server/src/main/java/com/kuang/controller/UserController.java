package com.kuang.controller;

import com.kuang.comsumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/ticket")
    public String getTicket() {
        return userService.bugTicket();
    }
}
