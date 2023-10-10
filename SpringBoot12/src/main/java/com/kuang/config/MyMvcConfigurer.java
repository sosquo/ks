package com.kuang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
/**
 * @EnableWebMvc全面接管WebMvc，默认配置失效，http://127.0.0.1:8080/1.jpg e.g. 访问不到
 * 理由 @EnableWebMvc将WebMvcConfigurationSupport组件导入进来了；
 * WebMvcAutoConfiguration 类 @ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
 * 而导入的WebMvcConfigurationSupport只是SpringMVC最基本的功能！
 * 开发中，不推荐使用全面接管SpringMVC*/
//@EnableWebMvc
public class MyMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/my/test").setViewName("test");
    }
}
