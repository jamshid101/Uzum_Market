package com.example.uzum_market.utils;


import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.Random;

public class EmailService {


    public static boolean sendMessageToEmail(String email, String messageBody) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        String username = "no0404ir@gmail.com";
        String password = "hwtfbucnpygkuibx";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setSubject("Hi guys");
            message.setContent("Active code: "+ messageBody, "text/html");



            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));


            Transport.send(message);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String getGenerationCode(){
        int i = new Random().nextInt(600000);
        return String.valueOf(i);
    }

}
