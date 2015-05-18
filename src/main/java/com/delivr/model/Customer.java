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
	@Table(name="customer")
	@PrimaryKeyJoinColumn(name="id")
	public class Customer extends User {
	 
	    @Column(name="address")
	    private String address;
	     
	    @Column(name="review")
	    private String review;
	    
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
		@LazyCollection(LazyCollectionOption.FALSE)
	    private Set<Package> packages;
	       
	    public Customer() {
	    }
	     
	    public Customer(User usr, String address, String review) {
	        super(usr.getUserName(), usr.getFirstName(), usr.getLastName(), usr
					.getPassword(), usr.getEmailAddress(), usr.getDateOfBirth());
	         
	        this.address = address;
	        this.review = review;
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

		public Set<Package> getPackages() {
			return packages;
		}

		public void setPackages(Set<Package> packages) {
			this.packages = packages;
		}
	 
	    // Getter and Setter methods, 
	}