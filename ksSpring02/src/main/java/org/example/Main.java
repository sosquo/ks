package org.example;

import com.kuang.pojo.UserT;
import com.kuang.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config/beans.xml");

        UserServiceImpl serviceImpl = (UserServiceImpl) context.getBean("ServiceImpl");
        System.out.println("serviceImpl = " + serviceImpl);

        UserT userTByConstructIndex = (UserT) context.getBean("userTByConstructIndex");
        UserT userTByConstructParam = (UserT) context.getBean("userTByConstructParam");
        UserT userTByType = (UserT) context.getBean("userTByType");
        userTByConstructIndex.show();
        userTByConstructParam.show();
        userTByType.show();

        UserT userByName = (UserT) context.getBean("h3");
        UserT hello = (UserT) context.getBean("hello");
        System.out.println("userByName == hello = " + (userByName == hello));
        userByName.show();

        UserT userByAlias = (UserT) context.getBean("userNew");
        userByAlias.show();

        System.out.println("Hello world!");
    }
}