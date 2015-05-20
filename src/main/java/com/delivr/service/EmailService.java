package com.delivr.service;


import com.delivr.model.User;

public interface EmailService {
	boolean sendConfirmSignUpEmail(User user);
}
