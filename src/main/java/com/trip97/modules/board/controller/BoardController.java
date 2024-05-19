package com.trip97.modules.board.controller;

import com.trip97.modules.board.model.Board;
import com.trip97.modules.board.model.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/board")
public class BoardController {

	@Value("${file.path}")
	private String uploadPath;

	@Value("${local.domain}")
	private String localDomain;

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
	
	@PostMapping
	public ResponseEntity<Integer> writeBoard(@RequestBody Board board) throws Exception{
		int BoardId = service.writeBoard(board);
		return ResponseEntity.ok(BoardId);
	}

	@PostMapping("/upload")
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {

		if (file == null || file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty or null file");
		}

		try {
			String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
			Files.copy(file.getInputStream(), Paths.get(uploadPath, fileName));

			String fileUrl = "/uploads/" + fileName;
			return ResponseEntity.ok(new UploadResponse(fileUrl));

		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
		}
	}

	static class UploadResponse {
		private String fileUrl;

		public UploadResponse(String fileUrl) {
			this.fileUrl = fileUrl;
		}

		public String getFileUrl() {
			return fileUrl;
		}

		public void setFileUrl(String fileUrl) {
			this.fileUrl = fileUrl;
		}
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
