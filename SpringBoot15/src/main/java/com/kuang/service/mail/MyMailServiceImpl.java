package com.kuang.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MyMailServiceImpl {

    @Autowired
    JavaMailSenderImpl mailSender;

    public void sendSimpleMail() {
//        邮件设置1：一个简单的邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("通知-明天上课");
        message.setText("今晚7点上课，请携带好现金");
        message.setTo("506753586@qq.com");
        message.setFrom("llz132@qq.com");
        mailSender.send(message);
    }


    public void sendPlusMail() throws MessagingException {
//        邮件设置2：一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("通知-明天放假");
        helper.setText("<b style='color:red>今天7点放假</b>'");

//        发送附件
        helper.addAttachment("cron表达式.png", new File(""));
        helper.addAttachment("字符含义.png", new File(""));

        helper.setTo("506753586@qq.com");
        helper.setFrom("llz132@qq.com");
        mailSender.send(mimeMessage);
    }
}
