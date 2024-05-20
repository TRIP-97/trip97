package com.trip97.modules.review.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Review {

    private Integer id;
    private Integer attractionId;
    private Integer writerId;
    private String writerNickname;
    private String writerProfileImage;
    private String content;
    private Integer rating;
    private String createdAt;
}
