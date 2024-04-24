package com.trip97.modules.user.model;

import com.trip97.modules.user.model.oauth.OAuthProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class User {

    private Integer id;
    private String email;
    private String nickname;
    private String profileImage;
    private String introduction;
    private OAuthProvider oAuthProvider;

    @Builder
    public User(String email, String nickname, OAuthProvider oAuthProvider) {
        this.email = email;
        this.nickname = nickname;
        this.oAuthProvider = oAuthProvider;
    }
}
