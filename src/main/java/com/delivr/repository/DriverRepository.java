package com.delivr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.delivr.model.Driver;

@Repository("driverRepository")
public interface DriverRepository extends JpaRepository<Driver, Long> {
	
	@Query("select d from Driver d where d.userName = :userName")
	Driver findByUserName(@Param("userName") String userName);
		
}