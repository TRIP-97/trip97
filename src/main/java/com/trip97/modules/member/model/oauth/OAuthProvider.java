package com.trip97.modules.member.model.oauth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OAuthProvider {
    KAKAO("OAUTH_KAKAO", "카카오 OAUTH"),
    NAVER("0AUTH_NAVER", "네이버 OAUTH");

    private final String key;
    private final String value;
}
