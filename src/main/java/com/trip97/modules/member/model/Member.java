package com.trip97.modules.member.model;

import com.trip97.modules.member.model.oauth.OAuthProvider;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Member {

    private Integer id;
    private String email;
    private String nickname;
    private String profileImage;
    private String introduction;
    private String friendCode;
    private Role role;
    private OAuthProvider oAuthProvider;
    private Boolean isDeleted;
    private String refreshToken;
}
