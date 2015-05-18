package com.delivr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.delivr.model.Customer;

@Repository("customerRepository")
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query("select c from Customer c where c.userName = :userName")
	Customer findByUserName(@Param("userName") String userName);
	
	@Query("select distinct d from Customer d join fetch d.packages where d.id = :customerId")
	Customer getAllCustomerpackages(@Param("customerId") long driverId);
	
}
