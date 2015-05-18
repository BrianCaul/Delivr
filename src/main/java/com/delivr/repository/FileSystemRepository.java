package com.delivr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.delivr.model.FileSystem;

@Repository("fileSystemRepository")
public interface FileSystemRepository extends JpaRepository<FileSystem, Long> {
	
	@Query("select fs from FileSystem fs where fs.name = :name")
	FileSystem findByFileName(@Param("name") String name);
	
	@Query("select distinct fs from FileSystem fs where fs.parentFileSystem is null")
	List<FileSystem> findAllFiles();
	
}
