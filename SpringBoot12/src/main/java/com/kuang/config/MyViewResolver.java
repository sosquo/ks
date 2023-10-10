package com.kuang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

@Configuration
public class MyViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        System.out.println("注册myViewResolver！成功");
        return null;
    }
}
