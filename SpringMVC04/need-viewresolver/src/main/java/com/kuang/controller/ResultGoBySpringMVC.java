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
@RequestMapping("/ssr")
public class ResultGoBySpringMVC {

    /**
     * 只有这个写法需要 视图解析器
     * */
    @GetMapping("/goForward")
    public String goForward() {
        //直接转发到/WEB-INF/jsp/hello.jsp
        return "hello";
    }

    /**
     *这个写法不行 会识别成 hello.jsp.jsp
     * */
    @GetMapping("/goForward4")
    public String goForward4() {
        //直接转发到/WEB-INF/jsp/hello.jsp.jsp
        return "hello.jsp";
    }

    @GetMapping("/goForward0")
    public String goForward0() {
        return "forward:/helloTest";
    }

    /**
     *这个写法不行, 因为也没用到视图解析器
     * */
    @GetMapping("/goForward1")
    public String goForward1() {
        return "forward:/hello.jsp";
    }

    @GetMapping("/goForward2")
    public String goForward2() {
        return "forward:/index.jsp";
    }

    @GetMapping("/goForward3")
    public String goForward3() {
        return "forward:/WEB-INF/jsp/hello.jsp";
    }

    /**
     * 注意 redirect也不需要 视图解析器
     *
     */
    @GetMapping("/goRedirect")
    public String goRedirect(String type) {
        if ("action".equals(type)) {
//            相当于 /ssr/helloTest
//            return "redirect:helloTest";
            return "redirect:/helloTest";
        }
        return "redirect:/index.jsp";
    }

}
