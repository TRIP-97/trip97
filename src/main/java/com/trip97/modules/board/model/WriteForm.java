package com.trip97.modules.board.model;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;

@Getter
public class WriteForm {
	
	private int id; // 게시판 아이디
	private String title; // 제목
	private String content; // 내용
	private Date createdAt; // 작성 날짜
	private int viewCount; // 조회수
	private int likeCount; // 좋아요 수
	private String writerNickname; // 작성자 닉네임
	private String profileImage; // 작성자 프로필 사진
	private List<MultipartFile> imageFiles;
	
	public WriteForm(String title, String content, List<MultipartFile> imageFiles) {
		this.title = title;
		this.content = content;
		this.imageFiles = imageFiles;
	}
	
	

}
