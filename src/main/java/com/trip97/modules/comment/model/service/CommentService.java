package com.trip97.modules.comment.model.service;

import com.trip97.modules.comment.model.Comment;

import java.util.List;

public interface CommentService {

    Integer registerComment(Comment comment, String boardType);
    List<Comment> getCommentsByBoardId(Integer id, String boardType);
    List<Comment> getCommentsByMemberId(Integer id, String boardType);
    Integer editComment(Comment comment, String boardType);
    Integer removeComment(Integer id, String boardType);
}
