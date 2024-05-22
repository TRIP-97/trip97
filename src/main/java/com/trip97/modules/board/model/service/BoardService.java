package com.trip97.modules.board.model.service;

import java.util.List;
import java.util.Map;

import com.trip97.modules.board.model.Board;
import com.trip97.modules.board.model.BoardListDto;

public interface BoardService {
	
	BoardListDto getBoards(Map<String, String> map) throws Exception;
	Board getBoardByNo(int no) throws Exception;
	int writeBoard(Board board) throws Exception;
	void editBoardViewCount(int no) throws Exception;
	void editBoard(Board board) throws Exception;
	void removeBoard(int no) throws Exception;
	void editBoardLike(int no) throws Exception;
	List<Board> getHotBoards() throws Exception;
}
