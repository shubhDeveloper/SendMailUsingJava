package com.javamail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.NewsAddress;
import javax.swing.plaf.synth.SynthTextPaneUI;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Loading message" );
        String message = "hello this is my first java mail";
        String subject = "PanchalTechnologies";
        String to = "shubham.panchal2745@gmail.com";
        String from = "panchalshubham574@gmail.com";
        
        //static method
        sendEmail(message,subject,to,from);
    }

//  this method responsible to send mail
	private static void sendEmail(String message, String subject, String to, String from) {
		
	    //variable for gmail
		String host = "smtp.gmail.com";
		
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println("Properties is = "+properties);
		
		//Settring important information to properties object 
		
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		
		//step 1: to get the session object 
		Session session = Session.getInstance(properties,new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication("shubham.panchal2745@gmail.com","*********");
			}
			
		});
		
		session.setDebug(true);
		
//		step 2: compose the message [text ,photo,]
		
		MimeMessage m = new MimeMessage(session);
		
		try {
		
			//From email id 
			m.setFrom(to);
			
			//adding recipient to message'
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(from));
			
			//adding subject to message
			m.setSubject(subject);
			
			//adding text to message
			m.setText(message);
			
			//send
			
			//Step 3 : send the message using transport class
			Transport.send(m);
			
			System.out.println("send success ........");		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
