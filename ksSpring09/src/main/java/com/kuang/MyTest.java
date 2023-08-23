package com.kuang;

import com.kuang.dao.UserMapper;
import com.kuang.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserMapper userDao = (UserMapper) context.getBean("userDao");


        try {
            userDao.pretendAddUser();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            userDao.failedPretendAddUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        userDao.selectUser().forEach(u -> System.out.println("uF = " + u));
    }
}
