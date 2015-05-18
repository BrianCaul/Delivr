package com.delivr.service;

import java.util.List;

import com.delivr.model.Driver;
import com.delivr.model.User;

public interface DriverService {
	Driver save(Driver driver);
	boolean findByLogin(String userName, String password);
	boolean findByUserName(String userName);
	List<Driver> getAllDrivers();
	Driver getDriverByID(String driverId);
	Driver createDriver(Driver driver);
}
