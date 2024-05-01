package com.trip97.modules.comment.model.mapper;

import com.trip97.modules.comment.model.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    Integer insertBoardComment(Comment comment);
    List<Comment> selectBoardCommentsByBoardId(Integer id);
    List<Comment> selectBoardCommentsByMemberId(Integer id);
    Integer updateBoardComment(Comment comment);
    Integer deleteBoardComment(Integer id);

    Integer insertHotPlaceComment(Comment comment);
    List<Comment> selectHotPlaceCommentsByBoardId(Integer id);
    List<Comment> selectHotPlaceCommentsByMemberId(Integer id);
    Integer updateHotPlaceComment(Comment comment);
    Integer deleteHotPlaceComment(Integer id);
}
