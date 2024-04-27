package com.trip97.modules.friend.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FriendInfo {

    private Integer memberId;
    private String nickname;
    private String profileImage;
}
