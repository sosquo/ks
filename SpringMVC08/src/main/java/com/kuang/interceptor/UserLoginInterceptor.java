package com.kuang.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("UserLoginInterceptor1.5 ----- preHandle");

        // 登录页面
        String requestURI = request.getRequestURI();
        if (requestURI.endsWith("login")) {
            return true;
        }

        // 用户已登录
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        if (!StringUtils.isEmpty(user)) {
            return true;
        }

        // 其他情况，需要跳转到登录页面
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        return false;
    }

}
