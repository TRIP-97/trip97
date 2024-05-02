package com.trip97.modules.member.model;

import com.trip97.modules.member.model.oauth.OAuthProvider;

import jakarta.annotation.security.DenyAll;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;

@Getter
@Builder
public class Member {

    private Integer id;
    private String email;
    private String nickname;
    private String profileImage;
    private String introduction;
    private String friendCode;
    private OAuthProvider oAuthProvider;
    private Boolean isDeleted;
}
