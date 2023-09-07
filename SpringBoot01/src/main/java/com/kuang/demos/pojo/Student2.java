package com.kuang.demos.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Data
@Component
@ConfigurationProperties
//@PropertySource("classpath:myStudent.yml")
//# 哈哈 自定义的myStudent.yml 无效
public class Student2 {

    private String name;

    private Integer age = 1;

    private List<String> friendsList;

    private String[] friendsListPlus;

    private Teacher mathTeacher;

    private Map<String, String> hobbies;

    private boolean isWork = false;

    private Date birthday;

    private Set<String> colleaguesSet;

}
