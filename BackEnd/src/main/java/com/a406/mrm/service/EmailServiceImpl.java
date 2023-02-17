package com.a406.mrm.service;


import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@PropertySource("classpath:email.properties")
public class EmailServiceImpl implements EmailService{

    @Value("${email.id}")
    private String id;

    @Autowired
    JavaMailSender emailSender;

    private MimeMessage createMessage(String ePw, String to)throws Exception{
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(RecipientType.TO, to);//보내는 대상
        message.setSubject("[마룸모] 비밀번호 찾기 안내 메일");//제목

        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<p><strong>마룸모 비밀번호 찾기 안내 메일 </strong></p>";
        msgg+= "<br>";
        msgg+= "<p>안녕하세요. <p>";
        msgg+= "<p>마룸모 (MRM) 서비스 입니다.<p>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 복사해 입력해주세요.<p>";
        msgg+= "<br>";
        msgg+= "<p>비밀번호 찾기 코드 : ";
        msgg+= "<strong>";
        msgg+= ePw+"</strong></p> ";
        msgg+= "<br>";
        msgg+= "<p>감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress(id,"mrm_service"));//보내는 사람

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }

    @Override
    public String sendMessage(String to)throws Exception {
        String ePw = createKey();
        MimeMessage message = createMessage(ePw, to);
        try{//예외처리
            emailSender.send(message);
        } catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }
}
