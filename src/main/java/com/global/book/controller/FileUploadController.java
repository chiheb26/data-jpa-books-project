package com.global.book.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.global.book.service.FileUploadService;

@RestController
@RequestMapping("/upload")
public class FileUploadController {
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@PostMapping("/file")
	public ResponseEntity<Object> uploadFile(@RequestParam Long id,@RequestParam String pathType,
			@RequestParam MultipartFile file){
		
		String fileName = fileUploadService.storeFile(fileUploadService.convertMultiPartFileToFile(file), id, pathType);
		return ResponseEntity.ok(fileName);
	}
	
	@PostMapping("/multipleFiles")
	public List<ResponseEntity<Object>> uploadMultipleFiles(@RequestParam Long id,@RequestParam String pathType,
			@RequestParam MultipartFile[] files){
		
		return Arrays.asList(files).stream().map(file->uploadFile(id,pathType,file)).collect(Collectors.toList());
	}
}
