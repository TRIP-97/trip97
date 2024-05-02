package com.trip97.modules.comment.model.service;

import com.trip97.modules.comment.model.Comment;
import com.trip97.modules.comment.model.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Override
    public Integer registerComment(Comment comment, String boardType) {
        if (boardType.equals("board")) {
            return commentMapper.insertBoardComment(comment);
        } else {
            return commentMapper.insertHotPlaceComment(comment);
        }
    }

    @Override
    public List<Comment> getComments(Integer id, String boardType) {
        if (boardType.equals("board")) {
            return commentMapper.selectBoardCommentsByBoardId(id);
        } else {
            return commentMapper.selectHotPlaceCommentsByBoardId(id);
        }
    }

    @Override
    public List<Comment> getCommentsByMemberId(Integer id, String boardType) {
        if (boardType.equals("board")) {
            return commentMapper.selectBoardCommentsByMemberId(id);
        } else {
            return commentMapper.selectHotPlaceCommentsByMemberId(id);
        }
    }

    @Override
    public Integer editComment(Comment comment, String boardType) {
        if (boardType.equals("board")) {
            return commentMapper.updateBoardComment(comment);
        } else {
            return commentMapper.updateHotPlaceComment(comment);
        }
    }

    @Override
    public Integer removeComment(Integer id, String boardType) {
        if (boardType.equals("board")) {
            return commentMapper.deleteBoardComment(id);
        } else {
            return commentMapper.deleteHotPlaceComment(id);
        }
    }
}
