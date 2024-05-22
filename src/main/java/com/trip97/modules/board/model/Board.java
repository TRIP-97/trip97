package com.trip97.modules.board.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Board {
	
	private int id; // 게시판 아이디
	private int writerId; // 작성자 아이디
	private String title; // 제목
	private String content; // 내용
	private Date createdAt; // 작성 날짜
	private int viewCount; // 조회수
	private int likeCount; // 좋아요 수
	private String writerNickname; // 작성자 닉네임
	private String profileImage; // 작성자 프로필 사진

}
