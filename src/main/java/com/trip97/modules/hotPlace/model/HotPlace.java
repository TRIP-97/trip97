package com.trip97.modules.hotPlace.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class HotPlace {

    private Integer id;
    private Integer writerId;
    private String writerNickname;
    private String writerProfileImage;
    private String writerIntroduction;
    private String title;
    private String content;
    private String createdAt;
    private Integer viewCount;
    private Integer likeCount;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String location;
    private String placeName;
    private String startDate;
    private String endDate;
    private List<FileInfoDto> fileInfos;

}
