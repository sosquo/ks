package com.kuang.controller;

import com.kuang.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(UserService.class)
public class WorkController {



}
