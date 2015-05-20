package com.delivr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivr.model.Package;
import com.delivr.model.User;
import com.delivr.repository.PackageRepository;
import com.delivr.repository.UserRepository;

@Service("packageService")
public class PackageServiceImpl implements PackageService {

	@Autowired
	private PackageRepository packageRepository;

	public List<Package> getAllPackages() {
		return packageRepository.findAll();
	}

	public List<Package> getAllPendingPackages() {
		return packageRepository.getAllPendingPackages();
	}
	
	public List<Package> getAllProgressPackages(String CustomerId) {
		return packageRepository.getAllInProgressPackages(Long.parseLong(CustomerId));
	}
	
	public List<Package> getAllCompletePackages(String CustomerId) {
		return packageRepository.getAllCompletePackages(Long.parseLong(CustomerId));
	}
	
	public List<Package> getAllPendingPackages(String CustomerId) {
		return packageRepository.getAllPendingPackages(Long.parseLong(CustomerId));
	}
	
	@Transactional
	public Package getPackage(String packageId) {
		return packageRepository.findOne(Long.parseLong(packageId));
	}

	@Transactional
	public void deletePackage(String packageId) {
		packageRepository.delete(Long.parseLong(packageId));
	}

	@Transactional
	public Package createPackage(Package pack) {
		return packageRepository.save(pack);
	}

	@Transactional
	public Package updatePackage(Package pack) {
		return packageRepository.save(pack);
	}
	
	
}
