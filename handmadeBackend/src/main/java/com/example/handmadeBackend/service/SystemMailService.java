package com.example.handmadeBackend.service;

import com.example.handmadeBackend.security.config.MailConfig;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.Properties;

@Service
@MailConfig
@AllArgsConstructor
public class SystemMailService {
    @Value("${mail.transport.protocol}")
    private final String transportProtocol;

    @Value("${mail.smtp.auth}")
    private final String smtpAuth;

    @Value("${mail.smtp.starttls.enable}")
    private final String smtpStarttlsEnable;

    @Value("${mail.debug}")
    private final String mailDebug;

    public JavaMailSenderImpl javaMailSender;

    @PostConstruct
    public void init(){
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);

        Properties jvProps = javaMailSender.getJavaMailProperties();
        jvProps.put("mail.transport.protocol", transportProtocol);
        jvProps.put("mail.smtp.auth", smtpAuth);
        jvProps.put("mail.smtp.starttls.enable", smtpStarttlsEnable);
        jvProps.put("mail.debug", mailDebug);
    }


    public void sendSimpleMessage(@Valid Mail mail){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        BeanUtils.copyProperties(this.javaMailSender, javaMailSender);
        javaMailSender.setUsername(mail.getEmail());
        javaMailSender.setPassword(mail.getPassword());

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mail.getEmail());
        simpleMailMessage.setTo(mail.getTo());
        simpleMailMessage.setSubject(mail.getSubject());
        simpleMailMessage.setText(mail.getText());
        javaMailSender.send(simpleMailMessage);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Mail {

        @Email
        @NotNull
        private String email;
        @NotNull
        private String password;

        @Email
        @NotNull
        private String to;
        @NotNull
        private String subject;
        @NotNull
        private String text;
    }
}
