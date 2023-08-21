package com.kuang;


import com.kuang.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void testFirstAopMethod() {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringApplication.xml");
        UserService bean = (UserService)context.getBean("firstUserService");
        bean.add();
        bean.delete();
    }

    @Test
    public void secondFirstAopMethod() {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringApplication.xml");
        UserService bean = (UserService)context.getBean("secondUserService");
        bean.add();
        bean.delete();
    }

    @Test
    public void thirdFirstAopMethod() {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringApplication.xml");
        UserService bean = (UserService)context.getBean("thirdUserService");
        bean.add();
        bean.delete();
    }
}
