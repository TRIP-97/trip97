package com.trip97.modules.board.model.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadImage {
	
	private int boardId;
	private String uploadFilename;
	private String storeFilename;
	
	public UploadImage(String uploadFilename, String storeFilename) {
		this.uploadFilename = uploadFilename;
		this.storeFilename = storeFilename;
	}	

}
