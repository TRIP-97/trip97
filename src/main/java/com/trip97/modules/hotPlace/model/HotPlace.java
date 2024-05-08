package com.trip97.modules.hotPlace.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class HotPlace {

    private Integer id;
    private Integer writerId;
    private String writerNickname;
    private String writerProfileImage;
    private String title;
    private String content;
    private String createdAt;
    private Integer viewCount;
    private Integer likeCount;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public void setWriterId(Integer writerId) {
        this.writerId = writerId;
    }
}
