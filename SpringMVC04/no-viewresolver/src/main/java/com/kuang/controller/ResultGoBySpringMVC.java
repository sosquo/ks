package com.kuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 通过SpringMVC来实现转发和重定向 - 无需视图解析器；
 * 测试前，需要将视图解析器注释掉
 */

@Controller
@RequestMapping("/ssr")
public class ResultGoBySpringMVC {

    @GetMapping("/goStr")
    public String goStr(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("blue sky 蓝蓝的天 --- goStr");
        return "/WEB-INF/jsp/hello.jsp";
    }

    @GetMapping("/goRedirect")
    public String goRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        request的新增属性也是读不到的
        request.setAttribute("msg", "blue sky 蓝蓝的天 --- goRedirect");
        return "redirect:/index.jsp";
    }

    @GetMapping("/goForward")
    public String goForward(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return "forward:/WEB-INF/jsp/hello.jsp";
    }
}
