package com.trip97.modules.board.model;

import java.util.Date;
import java.util.List;

import com.trip97.modules.board.model.file.UploadImage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class Board {
	
	private int id; // 게시판 아이디
	private int writerId; // 작성자 아이디 (OAuth 연결 전)
	private String title; // 제목
	private String content; // 내용
	private Date createdAt; // 작성 날짜
	private int viewCount; // 조회수
	private int likeCount; // 좋아요 수
	private String writerNickname; // 작성자 닉네임
	private String profileImage; // 작성자 프로필 사진
	private List<UploadImage> imageFiles;

	public Board(int writerId, String title, String content, String writerNickname) {
		this.writerId = writerId;
		this.title = title;
		this.content = content;
		this.writerNickname = writerNickname;
	}
	
	public void setId(int id) {
		this.id=id;
	}

	public void setImageFiles(List<UploadImage> imageFiles) {
		this.imageFiles = imageFiles;
	}




}
