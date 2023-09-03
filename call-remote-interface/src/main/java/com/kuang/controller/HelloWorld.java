package com.kuang.controller;

import com.kuang.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author hsq
 */
@RestController
@RequestMapping("/api")
public class HelloWorld {

    private static final Logger log = LoggerFactory.getLogger(HelloWorld.class);

    @GetMapping(value = "/getHello",  produces = "application/json;charset=utf-8")
    public String getHelloWord() {
        System.out.println("进入到api接口.......");
        return "hello world api get 接口数据";
    }

    @PostMapping(value = "/postHello",  produces = "application/json;charset=utf-8")
    public String postHelloWord(@RequestBody User user) {
        log.info("进入post 方法.....");
        System.out.println(user.toString());
        return "hello world api post接口数据";
    }
}
