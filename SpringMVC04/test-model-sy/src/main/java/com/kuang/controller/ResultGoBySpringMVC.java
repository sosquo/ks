package com.kuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * 通过SpringMVC来实现转发和重定向
 * 总结：
 * 1、需要视图解析器的写法
 *     return "hello"
 * 2、无需视图解析器的写法
 *     return “forward:/xxx”
 *     return “redirect:/xxx”
 *     return “forward:/xx/xxx.jsp”
 *     return “redirect:/xx/xxx.jsp”
 */

@Controller
public class ResultGoBySpringMVC {

    /**
     * 只有这个写法需要 视图解析器
     * */
    @GetMapping("/test")
    public String goForward() {
        System.out.println("test-model-max");
        System.out.println("--- test-model-max ---");
        //直接转发到/WEB-INF/jsp/hello.jsp
        return "hello";
    }

    /**
     * 只有这个写法需要 视图解析器
     * */
    @GetMapping("/test-model-max")
    public String goForwardTmp() {
        System.out.println("test-model-max");
        System.out.println("--- test-model-max ---");
        //直接转发到/WEB-INF/jsp/hello.jsp
        return "hello";
    }


}
