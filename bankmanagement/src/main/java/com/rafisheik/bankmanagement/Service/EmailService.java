package com.rafisheik.bankmanagement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Autowired
  public JavaMailSender javaMailSender;

  @Value("$(spring.mail.username)")
  private String fromEmailId;

  public void sendEmail(String toEmailId, String subject, String body) {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setFrom(fromEmailId);
    simpleMailMessage.setTo(toEmailId);
    simpleMailMessage.setText(body);
    simpleMailMessage.setSubject(subject);

    javaMailSender.send(simpleMailMessage);
  }
}
