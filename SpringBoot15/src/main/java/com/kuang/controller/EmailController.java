package com.kuang.controller;

import com.kuang.service.mail.MyMailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class EmailController {

    @Autowired
    MyMailServiceImpl mailService;

    @RequestMapping("/send/mail1")
    public String sendMail() {
        mailService.sendSimpleMail();
        return "发送成功！";
    }

    @RequestMapping("/send/mail2")
    public String sendMail2() throws MessagingException {
        mailService.sendPlusMail();
        return "发送成功！";
    }
}
