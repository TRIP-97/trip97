package com.trip97.modules.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip97.modules.board.model.Board;
import com.trip97.modules.board.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper mapper;
	
	@Override
	public List<Board> getBoards() throws Exception {
		List<Board> boards = mapper.selectBoards();
		return boards;
	}

	@Override
	public Board getBoardByNo(int no) throws Exception {
		Board board = mapper.selectBoardByNo(no);
		return board;
	}
	
	@Override
	public int writeBoard(Board board) throws Exception {
		return mapper.insertBoard(board);
	}

	@Override
	public void editBoardViewCount(int no) throws Exception {
		mapper.updateBoardView(no);
	}

	@Override
	public void editBoard(Board board) throws Exception {
		mapper.updateBoard(board);
	}

	@Override
	public void removeBoard(int no) throws Exception {
		mapper.deleteBoard(no);
	}

	@Override
	public void editBoardLike(int no) throws Exception {
		mapper.updateBoardLike(no);
	}

	
}
