package com.easyapp.adm.mathias.service;

import com.easyapp.adm.mathias.model.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    @Value("${email.service.username}")
    private String username;
    @Value("${email.service.password}")
    private String password;
    @Value("${email.service.host}")
    private String host;
    @Value("${email.service.port}")
    private String port;

    public ResponseEntity<String> sendEmail(Email email){
       try {
            Transport.send(createMessage(email));
            return ResponseEntity.ok().build();
        } catch (MessagingException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    private Session getSession(){
        var properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private Message createMessage(Email email) throws MessagingException {
        Message message = new MimeMessage(getSession());
        message.setFrom(new InternetAddress(username));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo()));
        message.setSubject(email.getSubject());
        message.setText(email.getMessage());
        return message;
    }

}
