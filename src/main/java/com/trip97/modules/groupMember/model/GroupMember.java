package com.trip97.modules.groupMember.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GroupMember {

    private Integer id;
    private Integer memberId;
    private Integer groupId;
    private GroupMemberStatus status;
    private String memberNickname;
    private String memberProfileImage;

}
