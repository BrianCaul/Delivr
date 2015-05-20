package com.delivr.service;

import java.util.List;

import com.delivr.model.Package;

public interface PackageService {
	List<Package> getAllPackages();
	List<Package> getAllPendingPackages();
	List<Package> getAllProgressPackages(String customerId);
	List<Package> getAllPendingPackages(String customerId);
	List<Package> getAllCompletePackages(String customerId);
	Package getPackage(String packageId);
	void deletePackage(String packageId);
	Package createPackage(Package pack);
	Package updatePackage(Package pack);
}
