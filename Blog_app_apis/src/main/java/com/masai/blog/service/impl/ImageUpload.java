package com.masai.blog.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.masai.blog.service.FileUpload;
@Service
public class ImageUpload implements FileUpload {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		//get file name
		String name = file.getOriginalFilename();
		
		String randamName = UUID.randomUUID().toString();
		String fileName = randamName.concat(name.substring(name.lastIndexOf(".")));
		System.out.println(fileName);
		
		// full path
		String fullPath = path+File.separator +fileName;
		
		//create folder if not created
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		// file copy
		Files.copy(file.getInputStream(), Paths.get(fullPath));
		
		return fileName;
	}

	@Override
	public InputStream getImage(String path, String fileName) throws FileNotFoundException {
		String fullPathName = path + File.separator +fileName;
		InputStream fileInputStream = new FileInputStream(fullPathName);
		return fileInputStream;
	}

}
