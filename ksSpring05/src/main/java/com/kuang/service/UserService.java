package com.kuang.service;

import com.kuang.pojo.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserService {

    @Bean
    public Student student1() {
        return new Student("小俺同学");
    }
}
