package com.delivr.service;

import java.util.List;

import com.delivr.model.Customer;

public interface CustomerService {
	Customer save(Customer customer);
	boolean findByLogin(String userName, String password);
	boolean findByUserName(String userName);
	List<Customer> getAllCustomers();
	Customer getCustomerByID(String customerid);
	Customer createCustomer(Customer customer);
}
