package com.example.OTP.otpTask.service;

import lombok.RequiredArgsConstructor;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMailService {
    private final JavaMailSender javaMailSender;

    public void sendEmail(String phoneNumber, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(phoneNumber);
        message.setSubject("otp code");
        message.setText("your otp code " + otp);
        javaMailSender.send(message);
    }
}
