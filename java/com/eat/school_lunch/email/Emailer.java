package com.eat.school_lunch.email;

import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.utils.GenPropHelper;
import java.io.Serializable;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author DoverD
 */
public class Emailer implements Serializable {
    
    /**
     * sends the email based on the parameter
     * @param dbUser
     * @param to
     * @param subject
     * @param msg
     */
    public static void sendEmail(BaseDBSessionBean dbUser, String to, String subject, String msg) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", GenPropHelper.getGenPropByKeyName(dbUser, "mail.smtp.auth").getKeyValue());
        properties.put("mail.smtp.socketFactory.port", GenPropHelper.getGenPropByKeyName(dbUser, "mail.smtp.port").getKeyValue());
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.host", GenPropHelper.getGenPropByKeyName(dbUser, "mail.smtp.host").getKeyValue());
        properties.put("mail.smtp.port", GenPropHelper.getGenPropByKeyName(dbUser, "mail.smtp.port").getKeyValue());
        final String loginUserName = GenPropHelper.getGenPropByKeyName(dbUser, "mail.login.username").getKeyValue();
                final String loginPassword = GenPropHelper.getGenPropByKeyName(dbUser, "mail.login.password").getKeyValue();
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {                
                return new PasswordAuthentication(loginUserName, loginPassword);
        }
      });

      try {
          MimeMessage message = new MimeMessage(session);
          message.setFrom(new InternetAddress(GenPropHelper.getGenPropByKeyName(dbUser, "mail.from").getKeyValue()));
          message.setRecipients(Message.RecipientType.TO,
          InternetAddress.parse(to));
          message.setSubject(subject);
          message.setText(msg, "UTF-8", "html");
          Transport.send(message);
          System.out.println("Message send successfully....");
      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
   }
}
