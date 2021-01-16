package P1;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Implementation of the notificationcontroller class which calls methods from the filecontroller class
 *
 * @author anon
 */
public class NotificationController {
	/**
	 * The email of the sender (the program is the sender)
	 */
	private static final String SENDER_EMAIL = "CZ2002SE3Grp1@gmail.com";
	/**
	 * The password used for the sender's email (the program is the sender)
	 */
	private static final String SENDER_PW = "Pr0jectem@i1";
	
	
	/**
	* Sends an email to recipient
	* @param recipient The email of the recipient
	* @param courseID The courseID of the course that the recipient has successfully joined
	*/
	
	/**
	 * Method which sends an email to a receipent
	 * @param text : email text content
	 */
	public static void sendEmail(String[] text) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(SENDER_EMAIL, SENDER_PW);
				}
			});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SENDER_EMAIL));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(text[0]));
			message.setSubject("Waitlist notification");
			message.setText(text[1]);

			Transport.send(message);

			System.out.println("An email has been sent to " + text[0] + "\n");

		} catch (MessagingException e) {
			System.out.println("You are not connected to the internet!");
		}
	}
}

