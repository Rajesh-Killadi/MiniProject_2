package com.app.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailUtils {
	@Autowired
	private static JavaMailSender mailSender;

	public static boolean sendEmail(String to , String subject , String body) {
		
	   SimpleMailMessage message = new SimpleMailMessage();
	   message.setTo(to);
	   message.setSubject(subject);
	   message.setText(body);
	   
	   mailSender.send(message);
	   
	   
	   
		return true;
		
	}
}
