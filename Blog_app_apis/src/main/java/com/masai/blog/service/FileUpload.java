package com.masai.blog.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileUpload {
	String uploadImage(String path , MultipartFile file) throws IOException;
	
	InputStream getImage(String path , String fileName) throws FileNotFoundException;

}
