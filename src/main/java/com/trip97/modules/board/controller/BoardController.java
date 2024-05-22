package com.trip97.modules.board.controller;

import com.trip97.modules.board.model.Board;
import com.trip97.modules.board.model.BoardListDto;
import com.trip97.modules.board.model.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
	
	@GetMapping
	public ResponseEntity<BoardListDto> getBoards(@RequestParam Map<String, String> map) throws Exception{
		BoardListDto boards = service.getBoards(map);
		return new ResponseEntity<BoardListDto>(boards,HttpStatus.OK);
	}
	
	@GetMapping("{no}")
	public ResponseEntity<Board> getBoardByNo(@PathVariable("no") int no) throws Exception{
		service.editBoardViewCount(no);
		return new ResponseEntity<Board>(service.getBoardByNo(no),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Integer> writeBoard(@RequestBody Board board) throws Exception{
		System.out.println("들어옴 ????");

		int BoardId = service.writeBoard(board);
		return ResponseEntity.ok(BoardId);
	}

	@PostMapping("/upload")
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {

		System.out.println("저장되고있는거임?");

		if (file == null || file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty or null file");
		}

		try {
			String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
			String folder = "C:/trip97/board/";
			File directory = new File(folder);
			if (!directory.exists()) {
				directory.mkdirs(); // 폴더가 존재하지 않으면 생성
			}

			file.transferTo(new File(folder, fileName));

			System.out.println("폴더"+folder);
			System.out.println("파일이름"+fileName);

			String fileUrl = "http://localhost:8080"+"/images/board/" + fileName;

			System.out.println(fileUrl);
			return ResponseEntity.ok(fileUrl);

		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
		}
	}
	
	@PutMapping("{no}")
	public ResponseEntity<Void> editBoard(@RequestBody Board board) throws Exception{
		service.editBoard(board);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("{no}")
	public ResponseEntity<Void> removeBoard(@PathVariable("no") int no) throws Exception{
		service.removeBoard(no);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/hot")
	public ResponseEntity<?> getHotBoards() throws Exception{
		List<Board> list = service.getHotBoards();
		if (list != null && !list.isEmpty()) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
}
