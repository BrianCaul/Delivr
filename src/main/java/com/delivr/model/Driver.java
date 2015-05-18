package com.delivr.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "driver")
@PrimaryKeyJoinColumn(name = "id")
public class Driver extends User {

	@Column(name = "address")
	private String address;

	@Column(name = "review")
	private String review;

	@Column(name = "type")
	private String type;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "driver")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Package> packages;

	public Driver() {
	}

	public Driver(User usr, String address, String review, String type) {
		super(usr.getUserName(), usr.getFirstName(), usr.getLastName(), usr
				.getPassword(), usr.getEmailAddress(), usr.getDateOfBirth());

		this.address = address;
		this.review = review;
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Package> getPackages() {
		return packages;
	}

	public void setPackages(Set<Package> packages) {
		this.packages = packages;
	}

	// Getter and Setter methods,
}