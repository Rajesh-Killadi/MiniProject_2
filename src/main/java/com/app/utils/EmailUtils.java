//package com.app.utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Component;
//@Component
//public class EmailUtils {
//
//	@Autowired
//	private JavaMailSender mailSender;
//	
//	/*
//	 * @Autowired public void setMailSender(JavaMailSender mailSender) {
//	 * System.out.println("mail sender is injecting"); this.mailSender = mailSender;
//	 * System.out.println(mailSender == null);
//	 * System.out.println("mail sender is injected ... .hureeeh"); }
//	 */
//
//	public boolean sendEmail(String to , String subject , String body) {
//		
//	   SimpleMailMessage message = new SimpleMailMessage();
//	   message.setTo(to);
//	   message.setSubject(subject);
//	   message.setText(body);
//	   
//	   mailSender.send(message);
//	   
//	   
//	   
//		return true;
//		
//	}
//}
