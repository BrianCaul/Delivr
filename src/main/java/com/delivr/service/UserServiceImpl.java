package com.delivr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivr.model.User;
import com.delivr.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}

	public boolean findByLogin(String userName, String password) {	
		User user = userRepository.findByUserName(userName);
		
		if(user != null && user.getPassword().equals(password)) {
			return true;
		} 
		
		return false;		
	}

	public boolean findByUserName(String userName) {
		User user = userRepository.findByUserName(userName);
		if(user != null) {
			return true;
		} 
		return false;	
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Transactional
	public void deleteUser(String userId) {
		userRepository.delete(Long.parseLong(userId));
	}
	

	public User findByUserId(String userId) {
		return userRepository.findOne(Long.parseLong(userId));
	}
	
	@Transactional
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	@Transactional
	public User updateUser(User user) {
		return userRepository.save(user);
	}

}
