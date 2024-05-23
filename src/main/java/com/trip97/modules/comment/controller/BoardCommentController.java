package com.trip97.modules.comment.controller;

import com.trip97.modules.comment.model.Comment;
import com.trip97.modules.comment.model.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/board/comment")
@RequiredArgsConstructor
public class BoardCommentController {

    private final CommentService commentService;

    @GetMapping("/{boardId}")
    public ResponseEntity<?> getCommentList(@PathVariable int boardId) {
        List<Comment> list = commentService.getComments(boardId, "board");
        if (list != null && !list.isEmpty()) {
            return getListResponseEntity(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody Comment comment) {
        commentService.registerComment(comment, "board");
        List<Comment> list = commentService.getComments(comment.getBoardId(), "board");
        return getListResponseEntity(list);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@RequestBody Comment comment) {
        commentService.editComment(comment, "board");
        List<Comment> list = commentService.getComments(comment.getBoardId(), "board");
        return getListResponseEntity(list);
    }

    @DeleteMapping("/{commentId}/{boardId}")
    public ResponseEntity<?> deleteComment(@PathVariable int boardId, @PathVariable int commentId) {
        commentService.removeComment(commentId, "board");
        List<Comment> list = commentService.getComments(boardId, "board");
        if (list != null && !list.isEmpty()) {
            return getListResponseEntity(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    private static ResponseEntity<List<Comment>> getListResponseEntity(List<Comment> list) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(list);
    }
}
