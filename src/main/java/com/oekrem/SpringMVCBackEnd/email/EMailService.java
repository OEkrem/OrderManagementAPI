package com.oekrem.SpringMVCBackEnd.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EMailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleEmail(EMailRequest eMailRequest) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@mailtrap.io");
        message.setTo(eMailRequest.to());
        message.setSubject(eMailRequest.subject());
        message.setText(eMailRequest.content());
        emailSender.send(message);
    }
}
