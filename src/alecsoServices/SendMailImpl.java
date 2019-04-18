/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecsoServices;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author BADIS
 */
public class SendMailImpl {

    private String smtpHost = "localhost";

    public void sendMessage(String recipients,String nomConnected,String prenomConnected)
    throws MessagingException {
        final String username = "sibadorf@gmail.com";
        final String password = "Kaka1234";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipients)
            );
            message.setSubject("Alesco education");
            message.setText("Monsieur ,"+nomConnected+" "+prenomConnected+" Souhaite vous contacter");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    }

  




