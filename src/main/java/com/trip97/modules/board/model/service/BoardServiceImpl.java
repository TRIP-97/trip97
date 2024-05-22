package com.trip97.modules.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trip97.modules.board.model.BoardListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip97.modules.board.model.Board;
import com.trip97.modules.board.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper mapper;
	
	@Override
	public BoardListDto getBoards(Map<String, String> map) throws Exception {

		Map<String, Object> param = new HashMap<>();

		int currentPage = Integer.parseInt(map.get("pgno")==null? "1" : map.get("pgno"));
		int sizePerPage = Integer.parseInt(map.get("spp")==null? "10" : map.get("spp"));
		int start = currentPage * sizePerPage - sizePerPage;

		param.put("start", start);
		param.put("listsize", sizePerPage);


		String key = map.get("key");
		param.put("key", key==null ? "" : key);

		String word = map.get("word");
		param.put("word", word==null ? "" : word);

		String filter = map.get("filter");

		List<Board> list = new ArrayList<>();

		if(filter.equals("")||filter.equals("newest")){
			list = mapper.selectBoardsNew(param);
		}else if(filter.equals("oldest")){
			list = mapper.selectBoardsOld(param);
		}

		int totalArticleCount = mapper.getTotalBoardCount(param);
		int totalPageCount = (totalArticleCount-1)/sizePerPage+1;

		BoardListDto boardListDto = new BoardListDto();
		boardListDto.setList(list);
		boardListDto.setCurrentPage(currentPage);
		boardListDto.setTotalPageCount(totalPageCount);


		return boardListDto;
	}

	@Override
	public Board getBoardByNo(int no) throws Exception {
		Board board = mapper.selectBoardByNo(no);
		return board;
	}
	
	@Override
	public int writeBoard(Board board) throws Exception {
		mapper.insertBoard(board);
		return board.getId();
	}

	@Override
	public void editBoardViewCount(int no) throws Exception {
		mapper.updateBoardView(no);
	}

	@Override
	public void editBoard(Board board) throws Exception {

		Map<String, Object> param = new HashMap<>();

		param.put("title",board.getTitle());
		param.put("content",board.getContent());
		param.put("id",board.getId());

		mapper.updateBoard(param);
	}

	@Override
	public void removeBoard(int no) throws Exception {
		mapper.deleteBoard(no);
	}

	@Override
	public void editBoardLike(int no) throws Exception {
		mapper.updateBoardLike(no);
	}

	@Override
	public List<Board> getHotBoards() throws Exception {
		return mapper.selectHotBoard();
	}
}
