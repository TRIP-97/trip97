package com.trip97.modules.friend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendInfo {

    private Integer memberId;
    private String nickname;
    private String profileImage;
    private String introduction;
}
