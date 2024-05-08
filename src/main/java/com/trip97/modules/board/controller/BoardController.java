package com.trip97.modules.board.controller;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.trip97.modules.board.model.Board;
import com.trip97.modules.board.model.WriteForm;
import com.trip97.modules.board.model.file.FileStore;
import com.trip97.modules.board.model.file.UploadImage;
import com.trip97.modules.board.model.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@Autowired
	private FileStore fileStore;
	
	@GetMapping()
	public ResponseEntity<List<Board>> getBoards() throws Exception{
		return new ResponseEntity<List<Board>>(service.getBoards(),HttpStatus.OK);
	}
	
	@GetMapping("{no}")
	public ResponseEntity<Board> getBoardByNo(@PathVariable("id") int no) throws Exception{
		service.editBoardViewCount(no);
		return new ResponseEntity<Board>(service.getBoardByNo(no),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Board> writeBoard(@RequestBody WriteForm form) throws Exception{
		int id=0;
		String nickname="으악";
		
		Board board = new Board(id,form.getTitle(),form.getContent(),nickname);
		
		List<UploadImage> imageFiles = fileStore.storeFiles(form.getImageFiles());
		board.setImageFiles(imageFiles);
		
		int boardNo = service.writeBoard(board);
        return new ResponseEntity<Board>(service.getBoardByNo(boardNo), HttpStatus.CREATED);
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
	
	
	@ResponseBody
	@GetMapping("/images/{filename}")
	public ResponseEntity<Resource> showImage(@PathVariable String filename) throws MalformedURLException {
		UrlResource resource = new UrlResource("file:"+fileStore.getFullPath(filename));
		return new ResponseEntity<Resource>(resource,HttpStatus.OK);
	}
	
}
