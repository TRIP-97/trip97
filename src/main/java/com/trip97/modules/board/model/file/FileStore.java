package com.trip97.modules.board.model.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileStore {
	
	// 사진 저장할 경로 지정
	private final String rootPath = System.getProperty("user.dir");
	private final String fileDir = rootPath + "/images/";
	
	public String getFullPath(String filename) {
		return fileDir + filename;
	}
	
	public UploadImage storeFile(MultipartFile multipartFile) throws IOException {
		
		if(multipartFile.isEmpty()) {
			return null;
		}
		
		// 작성자가 업로드한 파일명 -> 서버 내부에서 관리하는 파일명
		String originalFilename = multipartFile.getOriginalFilename();
		// 파일명 중복 X 을 위한 생성 + 확장자 그대로
		String storeFilename = UUID.randomUUID() + "." + extractExt(originalFilename);
		
		multipartFile.transferTo(new File(getFullPath(storeFilename)));
		
		return new UploadImage(originalFilename,storeFilename);
	}
	
	public List<UploadImage> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
		List<UploadImage> storeFileResult = new ArrayList<>();
		for(MultipartFile multipartFile : multipartFiles) {
			if(!multipartFile.isEmpty()) {
				storeFileResult.add(storeFile(multipartFile));
			}
		}
		return storeFileResult;
	}
	
	private String extractExt(String originalFilename) {
		int pos = originalFilename.lastIndexOf(".");
		return originalFilename.substring(pos+1);
	}

}
