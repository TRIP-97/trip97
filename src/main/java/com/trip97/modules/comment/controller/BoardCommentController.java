package com.trip97.modules.comment.controller;

import com.trip97.modules.comment.model.Comment;
import com.trip97.modules.comment.model.service.CommentService;
import com.trip97.modules.review.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/board/{boardId}/comment")
@RequiredArgsConstructor
public class BoardCommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<?> getCommentList(@PathVariable int boardId) {
        List<Comment> list = commentService.getCommentsByBoardId(boardId, "board");
        if (list != null && !list.isEmpty()) {
            return getListResponseEntity(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addComment(@PathVariable int boardId, @RequestParam int memberId, @RequestBody Comment comment) {
        comment.setWriterId(memberId);
        commentService.registerComment(comment, "board");
        List<Comment> list = commentService.getCommentsByBoardId(boardId, "board");
        return getListResponseEntity(list);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable int boardId, @RequestBody Comment comment) {
        commentService.editComment(comment, "board");
        List<Comment> list = commentService.getCommentsByBoardId(boardId, "board");
        return getListResponseEntity(list);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int boardId, @PathVariable int commentId) {
        commentService.removeComment(commentId, "board");
        List<Comment> list = commentService.getCommentsByBoardId(boardId, "board");
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
