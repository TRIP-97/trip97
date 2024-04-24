package com.trip97.modules.user.model.oauth;

public interface OAuthInfoResponse {

    String getEmail();
    String getNickname();
    OAuthProvider getOAuthProvider();
}
