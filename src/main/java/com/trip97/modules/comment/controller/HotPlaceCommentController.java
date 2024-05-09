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
@RequestMapping("/hotplace/{hotplaceId}/comment")
@RequiredArgsConstructor
public class HotPlaceCommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<?> getCommentList(@PathVariable int hotplaceId) {
        List<Comment> list = commentService.getComments(hotplaceId, "hotPlace");
        if (list != null && !list.isEmpty()) {
            return getListResponseEntity(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addComment(@PathVariable int hotplaceId, @RequestBody Comment comment) {
        commentService.registerComment(comment, "hotPlace");
        List<Comment> list = commentService.getComments(hotplaceId, "hotPlace");
        return getListResponseEntity(list);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable int hotplaceId, @RequestBody Comment comment) {
        commentService.editComment(comment, "hotPlace");
        List<Comment> list = commentService.getComments(hotplaceId, "hotPlace");
        return getListResponseEntity(list);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int hotplaceId, @PathVariable int commentId) {
        commentService.removeComment(commentId, "hotPlace");
        List<Comment> list = commentService.getComments(hotplaceId, "hotPlace");
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
