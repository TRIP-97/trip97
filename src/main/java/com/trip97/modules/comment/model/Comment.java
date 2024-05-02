package com.trip97.modules.comment.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Comment {

    private Integer id;
    private Integer boardId;
    private Integer writerId;
    private String content;
    private String createdAt;
    private String writerNickname;
    private String writerProfileImage;

    public void setWriterId(Integer writerId) {
        this.writerId = writerId;
    }
}
