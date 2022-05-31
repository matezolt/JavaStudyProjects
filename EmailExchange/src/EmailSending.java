import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

public class EmailSending {
    public static void main(String[] args) throws Exception{

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true); //Session tries to authenticate the user
        //Server address of GMAIL
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Port number to define
        properties.put("mail.smtp.port", 587);
        //
        properties.put("mail.smtp.starttls.enable", true);//switches to tls protected connection when true
        properties.put("mail.transport.protocol", "smtp"); //default transfport protocol

        //Authenticator knows how toobtain info for a network connection
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("philozomat@gmail.com", "Cocacola123+");  //called when authorization needed
            }
        });  //Singelton Object
        Message message =new MimeMessage(session);
        message.setSubject("Email from Java Program");
//        message.setContent("<h1>Email from my Java program!!</h1>", "text/html");

        Address addressTo = new InternetAddress("philozomat@gmail.com");
        message.setRecipient(Message.RecipientType.TO, addressTo);
//        message.setRecipient(Message, MimeMessage.RecipientType.CC);
//        message.addRecipient(Message.RecipientType.TO, addressMore);

        MimeMultipart multipart = new MimeMultipart();

        MimeBodyPart attachement = new MimeBodyPart();
        attachement.attachFile(new File("docs/PDF.pdf"));
        MimeBodyPart attachement2 = new MimeBodyPart();
        attachement2.attachFile(new File("docs/Pic.jpg"));
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("<h1>Email from my Java program!!</h1>", "text/html");

        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(attachement);
        multipart.addBodyPart(attachement2);

        message.setContent(multipart);

        Transport.send(message);

    }
}










