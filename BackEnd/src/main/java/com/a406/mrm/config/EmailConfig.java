package com.a406.mrm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("classpath:email.properties")
public class EmailConfig {

    @Value("${email.id}")
    private String id;
    @Value("${email.password}")
    private String password;

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setUsername(id);
        javaMailSender.setPassword(password);
        javaMailSender.setPort(465);
        javaMailSender.setDefaultEncoding("UTF-8");

        System.out.println("JavaMailSender : "+id+" "+password);
        javaMailSender.setJavaMailProperties(getMailProperties());
        return javaMailSender;
    }

    private Properties getMailProperties()
    {
        Properties pt = new Properties();
        pt.put("mail.smtp.auth", true);
        pt.put("mail.smtp.starttls.enable", true);
//        pt.put("mail.smtp.host", "smtp.gmail.com");
//        pt.put("mail.smtp.port", 465);
//        pt.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        pt.put("mail.smtp.socketFactory.port", 465);
        pt.put("mail.smtp.starttls.required", true);
        pt.put("mail.smtp.socketFactory.fallback",false);
        pt.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        return pt;
    }
}
