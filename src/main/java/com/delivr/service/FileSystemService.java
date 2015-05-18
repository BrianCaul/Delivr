package com.delivr.service;

import java.util.List;

import com.delivr.model.FileSystem;
import com.delivr.model.User;

public interface FileSystemService {
	FileSystem save(FileSystem file);
	boolean findByFileName(String name);
	List<FileSystem> getAllFiles();
	void deleteFileSystem(String fileId);
	FileSystem createFileSystem(FileSystem file);
	FileSystem updateFileSystem(FileSystem file);
}
