package com.kuang.demos.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Data
@Component
//@PropertySource 就没有prefix 前缀。。,可以用$Value(XX.XX) 一一 赋值
@PropertySource(value = "classpath:myStudent.properties")
public class Student {

    //    自己配置的优先级比较高
    @Value("${stu.name}")
    private String name = "测试人员";

    //    #{SPEL} Spring表达式
    @Value("#{3 * 9}")
    private Integer age = 1;

    @Value("${stu.friendsList}")
    private List<String> friendsList;

    @Value("${stu.friendsListPlus}")
    private String[] friendsListPlus;

//    $Value 不支持封装 对象
//    Cannot convert value of type 'java.lang.String' to required type 'com.kuang.demos.pojo.Teacher': no matching editors or conversion strategy found
//    @Value("${stu.mathTeacher}" )
    private Teacher mathTeacher;

//    $Value 不支持封装 对象
//    @Value("${stu.hobbies}")
    private Map<String, String> hobbies;

    //    字面量
    @Value("true")
    private boolean isWork = false;

    @Value("${stu.birthday}")
    private Date birthday;

}
