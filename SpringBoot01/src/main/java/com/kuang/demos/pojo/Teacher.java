package com.kuang.demos.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
//@ConfigurationProperties 可以加 prefix 前缀。
@ConfigurationProperties(prefix = "teacher")
@Component
public class Teacher {

    private String contribution;
    private String tName;
    private String tAge;
    private String loves;
}
