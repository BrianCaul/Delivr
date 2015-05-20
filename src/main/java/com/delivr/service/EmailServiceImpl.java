package com.delivr.service;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


import org.springframework.stereotype.Service;

import com.delivr.model.User;


@Service("emailService")
public class EmailServiceImpl implements EmailService {
	
	//TODO: remove hardcoded creds
	final String username = "bcaul1990@gmail.com";
	final String password = "Cliffhotel123";

	public boolean sendConfirmSignUpEmail(User user) {
	
		 // Recipient's email ID needs to be mentioned.
	      String to = user.getEmailAddress();

	      // Sender's email ID needs to be mentioned
	      String from = "bcaul1990@gmail.com";
	      Properties props  = System.getProperties();
	      props.put("mail.smtp.port", "587");
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
		  
		  Session session = Session.getDefaultInstance(props, null);

	      try{
	    	  
	         MimeMessage message = new MimeMessage(session);

	         message.setFrom(new InternetAddress(from));
	         
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));

	         message.setSubject("Welcome to Delivr, the Mobile Delivery Service");

	         message.setContent("<h1>Welcome " +user.getFirstName()+"</h1><p>Please confirm your account by clicking here</p>",
	                            "text/html" );

	         Transport transport = session.getTransport("smtp");
	 		
	 		transport.connect("smtp.gmail.com", username, password);
	 		transport.sendMessage(message, message.getAllRecipients());
	 		transport.close();
	        
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
		
		return false;
	}

	

}
