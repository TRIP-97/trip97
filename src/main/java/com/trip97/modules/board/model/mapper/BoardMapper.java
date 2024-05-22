package com.trip97.modules.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.trip97.modules.board.model.Board;

@Mapper
public interface BoardMapper {
	
	// 게시판 조회 (글 전체 조회)
	List<Board> selectBoardsNew(Map<String, Object> param) throws SQLException;
	List<Board> selectBoardsOld(Map<String, Object> param) throws SQLException;

	int getTotalBoardCount(Map<String, Object> param);

	// 게시물 하나 조회
	Board selectBoardByNo(int no) throws SQLException;
	
	// 게시물 조회수 증가
	void updateBoardView(int no) throws SQLException;
	
	// 게시물 작성
	int insertBoard(Board board) throws SQLException;
	
	// 게시물 수정
	void updateBoard(Board board) throws SQLException;
	
	// 게시물 삭제
	void deleteBoard(int no) throws SQLException;
	
	// 게시물 좋아요 수 업데이트
	void updateBoardLike(int no) throws SQLException;

	// 인기 게시글 조회
	List<Board> selectHotBoard() throws SQLException;

	
}
