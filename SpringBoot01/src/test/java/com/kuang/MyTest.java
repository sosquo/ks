package com.kuang;

import com.kuang.demos.pojo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyTest {

    @Autowired
    Student student;

    @Autowired
    Student1 student1;

    @Autowired
    Student2 student2;

    @Autowired
    Teacher teacher;

    @Autowired
    Person person;

    @Test
    public void test() {
        System.out.println(" -------------------------  ------------------------- ");
        System.out.println("student = " + student);
        System.out.println(" -------------------------  ------------------------- ");
        System.out.println("student1 = " + student1);
        System.out.println(" -------------------------  ------------------------- ");
        System.out.println("student2 = " + student2);
        System.out.println(" -------------------------  ------------------------- ");
        System.out.println("teacher = " + teacher);
        System.out.println(" -------------------------  ------------------------- ");
        // 手动 实例化的对象 @Validated 无效
        Person personNew = new Person(null, 300, "12580@qq.com");
        System.out.println("personNew = " + personNew);
        System.out.println(" -------------------------  ------------------------- ");
        System.out.println("person = " + person);
        System.out.println(" -------------------------  ------------------------- ");
    }
}
