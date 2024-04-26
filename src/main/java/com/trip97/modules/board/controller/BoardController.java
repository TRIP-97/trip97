package com.trip97.modules.board.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.trip97.modules.board.model.Board;
import com.trip97.modules.board.model.service.BoardService;


@RestController
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping()
	public ResponseEntity<List<Board>> getBoards() throws Exception{
		return new ResponseEntity<List<Board>>(service.getBoards(),HttpStatus.OK);
	}
	
	@GetMapping("{no}")
	public ResponseEntity<Board> getBoardByNo(@PathVariable("no") int no) throws Exception{
		service.editBoardViewCount(no);
		return new ResponseEntity<Board>(service.getBoardByNo(no),HttpStatus.OK);
	}
	
	@PutMapping("/write")
	public ResponseEntity<Void> writeBoard(@RequestBody Board board) throws Exception{
		int boardNo = service.writeBoard(board);
		String boardUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/board/{boardNo}").buildAndExpand(boardNo).toUriString();
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(new URI(boardUrl))
                .build();
	}
	
	@PutMapping("{no}")
	public ResponseEntity<Void> editBoard(@PathVariable("no") int no,@RequestBody Board board) throws Exception{
		board.setId(no);
		service.editBoard(board);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("{no}")
	public ResponseEntity<Void> removeBoard(@PathVariable("no") int no) throws Exception{
		service.removeBoard(no);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
