import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class send_mail {
    public static void main(String[] args) {

        //Define email details
        String to = "J4vierR3yes11@gmail.com";   // THIS LINE NEEDS TO BE IMPLEMENTED WITH THE NEW USER'S EMAIL FROM THE CREATE ACCOUNT FUNCTION. FOR EXAMPLE user.getemail() from the login database
        String from = "accesspointproject106@gmail.com";
        String password = "nftx urry lvgl adsj";  //

        // Set mail server using gmail's SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        //  Create session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // : Compose the message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Hello from Jakarta Mail!");
            message.setText("This is a test email sent using Jakarta Mail API from Javier.");

            // : Send it
            Transport.send(message);
            System.out.println("Email sent to user successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
