package com.kuang.origin.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// {视图解析器前缀} + viewName +{视图解析器后缀}，需要视图解析器
public class ControllerTest implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", "origin-ControllerTest!");
        modelAndView.setViewName("hello");
        return modelAndView;
    }
}
