package com.trip97.modules.groupMember.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupRequest {

    private Integer requestId;
    private Integer groupId;
    private String groupName;
    private Integer creatorId;
    private String creatorNickname;
    private String creatorProfileImage;
}
