package dev.melis.EngelsizGonuller.service.email;

import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendVerificationEmail(String toEmail, String token){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setFrom("devmelisdenememaili");
        message.setSubject("Email Doğrulama");
        message.setText("Doğrulama linkiniz: http://localhost:8080/verify?token=" + token);
        mailSender.send(message);
    }
}
