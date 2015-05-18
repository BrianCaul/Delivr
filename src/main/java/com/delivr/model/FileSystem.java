package com.delivr.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="filesystem")
public class FileSystem {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	private String entityType;
	
	@NotEmpty
	private String name;
	


	public void setParentFileSystem(FileSystem parentFileSystem) {
		this.parentFileSystem = parentFileSystem;
	}


	public List<FileSystem> getChildFileSystems() {
		return childFileSystems;
	}


	public void setChildFileSystems(List<FileSystem> childFileSystems) {
		this.childFileSystems = childFileSystems;
	}



	 @ManyToOne(fetch = FetchType.LAZY, optional = true)
	 @JoinColumn(name = "parentId")
	private FileSystem parentFileSystem;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parentFileSystem")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<FileSystem> childFileSystems;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEntityType() {
		return entityType;
	}


	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getSizeInBytes() {
		return sizeInBytes;
	}


	public void setSizeInBytes(int sizeInBytes) {
		this.sizeInBytes = sizeInBytes;
	}


	
	@NotEmpty
	private int sizeInBytes;
	
	
}
