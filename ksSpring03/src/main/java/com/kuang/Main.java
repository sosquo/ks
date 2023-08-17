package com.kuang;

import com.kuang.pojo.Student;
import com.kuang.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) context.getBean("student");
        System.out.println("student = " + student.getName());
        System.out.println("student = " + student.getAddress().getAddress());
        System.out.println();
        student.show();

        System.out.println();
        User user = (User) context.getBean("user");
        System.out.println("user = " + user);
        User user1 = (User) context.getBean("user1");
        System.out.println("user = " + user1);
        User user2 = (User) context.getBean("user2");
        System.out.println("user = " + user2);
        System.out.println();

        System.out.println();
        User sy1 = (User) context.getBean("sy1");
        User sy1_1 = (User) context.getBean("sy1");
        /*User sy2 = (User) context.getBean("sy2");
        User sy2_1 = (User) context.getBean("sy2");*/
        User sy3 = (User) context.getBean("sy3");
        User sy3_1 = (User) context.getBean("sy3");
        /*User sy4 = (User) context.getBean("sy4");
        User sy4_1 = (User) context.getBean("sy4");
        User sy5 = (User) context.getBean("sy6");
        User sy5_1 = (User) context.getBean("sy6");*/
        System.out.println("sy1 = " + sy1);
        System.out.println("sy1_1 = " + sy1_1);
        /*System.out.println("sy2 = " + sy2);
        System.out.println("sy2_1 = " + sy2_1);*/
        System.out.println("sy3 = " + sy3);
        System.out.println("sy3_1 = " + sy3_1);
        /*System.out.println("sy4 = " + sy4);
        System.out.println("sy4_1 = " + sy4_1);
        System.out.println("sy5 = " + sy5);
        System.out.println("sy5_1 = " + sy5_1);*/

        System.out.println("Hello world!");
    }
}