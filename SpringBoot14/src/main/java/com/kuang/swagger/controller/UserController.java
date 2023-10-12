package com.kuang.swagger.controller;

import com.kuang.bean.User;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/myuser")
public class UserController {

    @GetMapping("/get")
    @ResponseBody
    public User getStudent(@ApiParam("用户名称") @RequestParam("uname") String uname) {
        User user = new User();
        user.setPassword("123456");
        user.setUsername(uname);
        return user;
    }

}
