package com.trip97.modules.member.model.oauth;

public interface OAuthInfoResponse {

    String getEmail();
    String getNickname();
    OAuthProvider getOAuthProvider();
}
