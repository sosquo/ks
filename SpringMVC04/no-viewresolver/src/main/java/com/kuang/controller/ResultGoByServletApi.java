package com.kuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//通过设置ServletAPI , 不需要视图解析器 .
@Controller
public class ResultGoByServletApi {

    @GetMapping("/goStr")
    public void goStr(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("blue sky 蓝蓝的天 --- goStr");
    }

    @GetMapping("/goRedirect")
    public void goRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        request的新增属性也是读不到的
        request.setAttribute("msg", "blue sky 蓝蓝的天 --- goRedirect");
        //        重定向访问不了WEB-INF下的jsp
//        response.sendRedirect("/WEB-INF/jsp/hello.jsp");
        response.sendRedirect("index.jsp");
    }

    @GetMapping("/goForward")
    public void goForward(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("msg", "blue sky 蓝蓝的天 --- goForward");
        request.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(request, response);
    }
}
