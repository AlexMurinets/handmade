package com.example.handmadeBackend.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SystemMailService {
    public MailSender mailSender;
}
