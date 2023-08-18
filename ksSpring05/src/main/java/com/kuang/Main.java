package com.kuang;

import com.kuang.controller.WorkController;
import com.kuang.pojo.Student;
import com.kuang.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student) context.getBean("student");
        System.out.println("- - " + student);

        Student student1 = (Student) context.getBean("student1");
        System.out.println(" -- " + student1);

        WorkController workController = (WorkController) context.getBean("workController");
        System.out.println("workController = " + workController);

        UserService userService = (UserService) context.getBean("userService");
        System.out.println("userService = " + userService);
        System.out.println("Hello world!");
    }
}