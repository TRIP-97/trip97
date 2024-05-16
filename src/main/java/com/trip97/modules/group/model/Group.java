package com.trip97.modules.group.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Group {

    private Integer id;
    private Integer creatorId;
    private String creatorNickname;
    private String creatorProfileImage;
    private String creatorIntroduction;
    private String name;
    private String overview;
    private Integer maxMemberCount;
    private Integer currentMemberCount;
    private String location;
    private String startDate;
    private String endDate;
    private String createdDate;
    List<GroupFileInfoDto> fileInfos;
}
