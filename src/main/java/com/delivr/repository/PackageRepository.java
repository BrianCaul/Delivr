package com.delivr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.delivr.model.Package;

@Repository("packageRepository")
public interface PackageRepository extends JpaRepository<Package, Long> {
	
	@Query("select distinct d from Package d where d.status = 'Pending'")
	List<Package> getAllPendingPackages();
	
	@Query("select distinct d from Package d where customer.id = :customerId AND d.status = 'In Progress'")
	List<Package> getAllInProgressPackages(@Param("customerId") Long customerId);	
	
	@Query("select distinct d from Package d where customer.id = :customerId AND d.status = 'Pending'")
	List<Package> getAllPendingPackages(@Param("customerId") Long customerId);	
	
	@Query("select distinct d from Package d where customer.id = :customerId AND d.status = 'Complete'")
	List<Package> getAllCompletePackages(@Param("customerId") Long customerId);	
	
}
