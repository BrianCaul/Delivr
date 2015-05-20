package com.delivr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivr.model.Driver;
import com.delivr.model.User;
import com.delivr.repository.DriverRepository;

@Service("driverService")
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepository driverRepository;
	
	@Transactional
	public Driver save(Driver driver) {
		return driverRepository.save(driver);
	}

	public boolean findByLogin(String userName, String password) {	
		Driver driver = driverRepository.findByUserName(userName);
		
		if(driver != null && driver.getPassword().equals(password)) {
			return true;
		} 
		
		return false;		
	}

	public boolean findByUserName(String userName) {
		Driver driver = driverRepository.findByUserName(userName);
		if(driver != null) {
			return true;
		} 
		return false;	
	}

	public List<Driver> getAllDrivers() {
		return driverRepository.findAll();
	}

	public Driver getDriverByID(String driverId) {
		return driverRepository.findOne(Long.parseLong(driverId));
	}
	
	@Transactional
	public Driver createDriver(Driver driver) {
		return driverRepository.save(driver);
	}

}
