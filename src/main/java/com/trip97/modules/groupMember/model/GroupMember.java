package com.trip97.modules.groupMember.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroupMember {

    private Integer id;
    private Integer memberId;
    private Integer groupId;
    private GroupMemberStatus status;
    private String memberNickname;
    private String memberProfileImage;
    private String memberIntroduction;

    public GroupMember(Integer groupId, Integer memberId, GroupMemberStatus status) {
        this.groupId = groupId;
        this.memberId = memberId;
        this.status = status;
    }
}
