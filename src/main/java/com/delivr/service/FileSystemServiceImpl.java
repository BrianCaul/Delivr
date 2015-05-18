package com.delivr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivr.model.FileSystem;
import com.delivr.repository.FileSystemRepository;

@Service("fileSystemService")
public class FileSystemServiceImpl implements FileSystemService {

	@Autowired
	private FileSystemRepository fileSystemRepository;
	
	@Transactional
	public FileSystem save(FileSystem file) {
		return fileSystemRepository.save(file);
	}

	public boolean findByFileName(String name) {
		FileSystem file = fileSystemRepository.findByFileName(name);
		if(file != null) {
			return true;
		} 
		return false;	
	}

	public List<FileSystem> getAllFiles() {
		return fileSystemRepository.findAllFiles();
	}
	
	@Transactional
	public void deleteFileSystem(String fileId) {
		fileSystemRepository.delete(Long.parseLong(fileId));
	}
	

	public FileSystem findByFileSystemId(String fileId) {
		return fileSystemRepository.findOne(Long.parseLong(fileId));
	}
	
	@Transactional
	public FileSystem createFileSystem(FileSystem file) {
		return fileSystemRepository.save(file);
	}
	
	@Transactional
	public FileSystem updateFileSystem(FileSystem file) {
		return fileSystemRepository.save(file);
	}

}
