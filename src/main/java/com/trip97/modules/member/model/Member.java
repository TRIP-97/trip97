package com.trip97.modules.member.model;

import com.trip97.modules.member.model.oauth.OAuthProvider;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Member {

    private Integer id;
    private String email;
    private String nickname;
    private String profileImage;
    private String introduction;
    private String friendCode;
    private OAuthProvider oAuthProvider;

    @Builder
    public Member(String email, String nickname, OAuthProvider oAuthProvider, String friendCode) {
        this.email = email;
        this.nickname = nickname;
        this.oAuthProvider = oAuthProvider;
        this.friendCode = friendCode;
    }
    
}
