package lk.ijse.controller.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSend {

    public static String num;
    public static int code;
    public static int test;
    public static boolean sendEmail(String recepient) throws Exception {
        try {

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.host", "smtp.gmail.com");

            String myAccountEmail = "rhpmservices@gmail.com";
            String password = "mnpybnfchvhptlpo";

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccountEmail, password);
                }
            });
            Message message = preporeMessage(session, myAccountEmail, recepient);
            Transport.send(message);
            System.out.println("email Send");

            return true;
        } catch (Exception e) {
            System.out.println("Email not send");
            return false;
        }
    }

    public static String generateOTP(){
        String randomNo= String.valueOf((int)(Math.random()*9000)+1000);
        String otp=randomNo;
        num=otp;
        return otp;
    }

    private static Message preporeMessage(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Foget Your Password");
            message.setText("Dear admin,\n\nYour verification code is " + generateOTP() + "\n\nFor secury purposes, please do not share this code this anyone.");
            message.setFrom(new InternetAddress(myAccountEmail));
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
