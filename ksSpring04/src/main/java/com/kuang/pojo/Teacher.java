package com.kuang.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

public class Teacher {
    
    private String teacherName;

    /**
     * 1、未指定@Qualifier则根据stu关键字到到容器查找id/name=stu的bean
     * 2、未找到则根据类型查找bean，类型相同的Student有多个则报错 org.springframework.beans.factory.NoUniqueBeanDefinitionException
     */
    @Autowired
    private Student stu;

    @Autowired
    private Dog dog0;

    /**
     * 1、指定@Qualifier则根据id/name=ssr1到容器查找bean，未找到则报错 org.springframework.beans.factory.NoSuchBeanDefinitionException:
     */
    @Autowired
    @Qualifier(value = "ssr1")
    private Student stu1;

    /**
     * 1、指定@Qualifier则根据id/name=ssr2到容器查找bean，未找到也没事
     */
    @Autowired(required = false)
    @Qualifier(value = "ssr2")
    private Student stu2;


    /**
     * 1、指定@QResource则根据id/name=ssr3到容器查找bean，未找到则报错 NoSuchBeanDefinitionException
     */
//    @Resource(name = "ssr3")
    private Student stu3;

    /**
     * 1、byName
     * 1、byType 多个 NoUniqueBeanDefinitionException
     */
    @Resource
    private Student stu4;

    public String getTeacherName() {
        return teacherName;
    }

    public Student getStu() {
        return stu;
    }

    public Student getStu1() {
        return stu1;
    }

    public Student getStu2() {
        return stu2;
    }

    public Student getStu3() {
        return stu3;
    }

    public Student getStu4() {
        return stu4;
    }

    public Dog getDog0() {
        return dog0;
    }
}
