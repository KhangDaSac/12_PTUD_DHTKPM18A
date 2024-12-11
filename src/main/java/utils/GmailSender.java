package utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class GmailSender {
    public static void guiTinNhan(String emailNhan, String noiDung){
        final String username = "natrirailwaycompany@gmail.com";
        final String password = "fxsq xhzx jpoq kuyo";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("natrirailwaycompany@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailNhan)
            );
            message.setSubject("Tin nhắn được giử từ NaTri Railway Company");
            message.setText(noiDung);

            Transport.send(message);

            System.out.println("Email đã được gửi thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
