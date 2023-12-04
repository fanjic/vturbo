package com.fan.vturbo.service.Impl;

import com.fan.vturbo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailServiceImpl implements MailService {

    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail() {

        try {
            /*SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("404844679@qq.com");
            message.setTo("404844679@qq.com");
            message.setSubject("测试邮件");
            message.setText("测试邮件正文");*/

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("404844679@qq.com");
            helper.setTo("364871076@qq.com");
            helper.setSubject("测试邮件");
            // helper.setText("测试邮件正文");
            helper.setText("<a href='https://www.csdn.net/'>CSDN</a>", true);

            File file = new File("C:\\Users\\Administrator\\Desktop\\fan_file\\html文件\\imgP/10.jpg");
            helper.addAttachment(file.getName(), file);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
