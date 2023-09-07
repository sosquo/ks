package com.kuang.demos.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Data
@Component
@ConfigurationProperties(prefix = "stu1")
public class Student1 {

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
