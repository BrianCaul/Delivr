package com.delivr.service;

import java.util.List;

import com.delivr.model.User;

public interface UserService {
	User save(User user);
	boolean findByLogin(String userName, String password);
	boolean findByUserName(String userName);
	List<User> getAllUsers();
	void deleteUser(String userid);
	User createUser(User user);
	User findByUserId(String userid);
	User updateUser(User user);
}
