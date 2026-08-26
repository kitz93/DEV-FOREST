package com.dev.forest.board.model.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	private final Path fileLocation;

	@Value("${app.upload.base-url:http://localhost/uploads/}")
	private String baseUrl;

	public FileService() {
		this.fileLocation = Paths.get("uploads").toAbsolutePath().normalize();
	}

	public String store(MultipartFile file, String mainFileName) {

		// 이름 바꾸기
		String originName = file.getOriginalFilename();
		String ext = (originName != null && originName.contains("."))
				? originName.substring(originName.lastIndexOf("."))
				: "";
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String randomSuffix = UUID.randomUUID().toString().substring(0, 8);
		String changeName = mainFileName + currentTime + randomSuffix + ext;

		// 파일명 뽑기
		String fileName = Paths.get(changeName).getFileName().toString();

		// 저장 위치 지정
		Path targetLocation = this.fileLocation.resolve(fileName);

		// 저장(복사)
		try {
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return baseUrl + fileName;
		} catch (IOException e) {
			throw new RuntimeException("파일을 찾을 수 없습니다.");
		}

	}

}
