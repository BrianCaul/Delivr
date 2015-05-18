package com.delivr.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="package")
@Inheritance(strategy=InheritanceType.JOINED)
public class Package {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	@Size(min=4, max=20)
	private String packageName;
	
	@NotEmpty
	private String packageDescription;
	
	@NotEmpty
	private String packageWeight;
	
	@NotEmpty
	private String status;
	
	 @ManyToOne(fetch = FetchType.LAZY, optional = true)
	 @JoinColumn(name="customer_id")
	 private Customer customer;
	
	 @ManyToOne(fetch = FetchType.LAZY, optional = true)
	 @JoinColumn(name = "driver_id")
	 private Driver driver;	
	
	@NotNull
	@Past
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date dateReceived;
	
	@NotNull
	@Past
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date dateDue;
	
	 public Package() {       

	 }
	
	 public Package(String packageName,String packageDescription, String packageWeight, Date dateReceived, Date dateDue) {       
	        this.packageName = packageName;
	        this.packageDescription = packageDescription;
	        this.packageWeight = packageWeight;
	        this.dateDue = dateDue;
	        this.dateReceived = dateReceived;
	 }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageDescription() {
		return packageDescription;
	}

	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}

	public String getPackageWeight() {
		return packageWeight;
	}

	public void setPackageWeight(String packageWeight) {
		this.packageWeight = packageWeight;
	}

	public Date getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}

	public Date getDateDue() {
		return dateDue;
	}

	public void setDateDue(Date dateDue) {
		this.dateDue = dateDue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public void setDriver(Driver driver) {
		this.driver = driver;
	}

}
