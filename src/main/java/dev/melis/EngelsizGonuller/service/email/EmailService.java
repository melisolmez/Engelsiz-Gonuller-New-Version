package dev.melis.EngelsizGonuller.service.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaEmailSender;

    public EmailService(JavaMailSender javaEmailSender) {
        this.javaEmailSender = javaEmailSender;
    }

    @Async
    public void sendMail(SimpleMailMessage email){
        javaEmailSender.send(email);
    }
}
