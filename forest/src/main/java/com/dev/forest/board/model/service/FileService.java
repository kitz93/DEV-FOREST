package com.dev.forest.board.model.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	
	private final Path fileLocation;
	
	public FileService() {
		this.fileLocation = Paths.get("uploads").toAbsolutePath().normalize();
	}
	
	public String store(MultipartFile file, String mainFileName) {
		
		// 이름 바꾸기
		String originName = file.getOriginalFilename();
		String ext = originName.substring(originName.lastIndexOf("."));
		String currentTime = new SimpleDateFormat("yyyymmddHHmmss").format(new Date());
		int randomNum = (int)(Math.random() * 90000) + 10000;
		String changeName = mainFileName + currentTime + randomNum + ext;
		
		// 파일명 뽑기
		String fileName = Paths.get(changeName).getFileName().toString();
		
		// 저장 위치 지정
		Path targetLocation = this.fileLocation.resolve(fileName);
		
		// 저장(복사)
		try {
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return "http://localhost/uploads/" + fileName;
		} catch (IOException e) {
			throw new RuntimeException("파일을 찾을 수 없습니다.");
		}
		
	}

}
