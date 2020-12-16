package com.javamail;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.NewsAddress;
import javax.swing.plaf.synth.SynthTextPaneUI;

public class App {
	public static void main(String[] args) {
		System.out.println("Loading message");
		String message = "hello this is my first java mail";
		String subject = "PanchalTechnologies";
		String to = "shubham.panchal2745@gmail.com";
		String from = "tech.shubham574@gmail.com";

//      static method only for message
//      sendEmail(message,subject,to,from);
		sendAttach(message, subject, to, from);
	}

//  this method responsible to send mail with attachment
	private static void sendAttach(String message, String subject, String to, String from) {

		// variable for gmail
		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();
		System.out.println("Properties is = " + properties);

		// Settring important information to properties object

		// host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// step 1: to get the session object
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("shubham.panchal2745@gmail.com", "***********");
			}

		});

		session.setDebug(true);

//	step 2: compose the message [text ,photo,]

		MimeMessage m = new MimeMessage(session);

		try {

			// From email id
			m.setFrom(to);

			// adding recipient to message'
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(from));

			// adding subject to message
			m.setSubject(subject);

			// attachment

//		    File path
			System.out.println("setting path");
			String path = "C:\\Users\\hp\\Downloads\\name.jpg";

			MimeMultipart mimeMultipart = new MimeMultipart();

			// text
			// file

			MimeBodyPart textMime = new MimeBodyPart();

			MimeBodyPart fileMime = new MimeBodyPart();

			try {

				textMime.setText(message);

				File file = new File(path);
				fileMime.attachFile(file);

				mimeMultipart.addBodyPart(textMime);
				mimeMultipart.addBodyPart(fileMime);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			m.setContent(mimeMultipart);

			// send ...............

			// Step 3 : send the message using transport class
			
			Transport.send(m);

			System.out.println("send success ........");

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

//  this method responsible to send mail
	private static void sendEmail(String message, String subject, String to, String from) {

		// variable for gmail
		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();
		System.out.println("Properties is = " + properties);

		// Settring important information to properties object

		// host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// step 1: to get the session object
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("shubham.panchal2745@gmail.com", "*********");
			}

		});

		session.setDebug(true);

//		step 2: compose the message [text ,photo,]

		MimeMessage m = new MimeMessage(session);

		try {

			// From email id
			m.setFrom(to);

			// adding recipient to message'
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(from));

			// adding subject to message
			m.setSubject(subject);

			// adding text to message
			m.setText(message);

			// send

			// Step 3 : send the message using transport class
			Transport.send(m);

			System.out.println("send success ........");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
