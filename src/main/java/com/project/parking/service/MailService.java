package com.project.parking.service;

import com.project.parking.configuration.ApplicationProperties;
import com.project.parking.service.behavior.IMailService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
@RequiredArgsConstructor
public class MailService implements IMailService {

    @NonNull
    private final ApplicationProperties properties;

    @NonNull
    private final JavaMailSender mailSender;

    @Override
    public void sendSimpleMail(String to, String subject, String body) {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//        try {
//            helper.setFrom(properties.getOriginEmail());
//            helper.setReplyTo(properties.getReplyEmail());
//            helper.setTo(to);
//            helper.setText(body, false);
//            helper.setSubject(subject);
//            mailSender.send(message);
//        } catch (MessagingException e) {
//            System.out.println("Error: MailService[36] +"+e.getMessage());
//        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
