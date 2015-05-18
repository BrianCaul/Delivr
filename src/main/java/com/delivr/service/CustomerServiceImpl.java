package com.delivr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivr.model.Customer;
import com.delivr.model.Driver;
import com.delivr.repository.CustomerRepository;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Transactional
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	public boolean findByLogin(String userName, String password) {	
		Customer customer = customerRepository.findByUserName(userName);
		
		if(customer != null && customer.getPassword().equals(password)) {
			return true;
		} 
		
		return false;		
	}

	public boolean findByUserName(String userName) {
		Customer customer = customerRepository.findByUserName(userName);
		if(customer != null) {
			return true;
		} 
		return false;	
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	public Customer getCustomerByID(String customerId) {
		return customerRepository.getAllCustomerpackages(Long.parseLong(customerId));
	}
	
	@Transactional
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

}
