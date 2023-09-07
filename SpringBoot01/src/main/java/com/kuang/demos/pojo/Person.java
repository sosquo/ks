package com.kuang.demos.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "person")
public class Person {

    @NotNull(message = "用户名不能为空！")
    private String userName;


    @Max(value = 250, message = "年级不能超过250！")
    private int age;

    @Email(message = "你说得对，不是一个合法的电子邮件地址！")
    private String email;


}
