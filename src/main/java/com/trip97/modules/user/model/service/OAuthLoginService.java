package com.trip97.modules.user.model.service;

import com.trip97.modules.user.model.User;
import com.trip97.modules.user.model.jwt.AuthTokens;
import com.trip97.modules.user.model.jwt.AuthTokensGenerator;
import com.trip97.modules.user.model.mapper.UserMapper;
import com.trip97.modules.user.model.oauth.OAuthInfoResponse;
import com.trip97.modules.user.model.oauth.OAuthLoginParams;
import com.trip97.modules.user.model.oauth.RequestOAuthInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {

    private final UserMapper userMapper;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Integer memberId = findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId);
    }

    private Integer findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return userMapper.findByEmail(oAuthInfoResponse.getEmail())
                .map(User::getId)
                .orElseGet(() -> newUser(oAuthInfoResponse));
    }

    private int newUser(OAuthInfoResponse oAuthInfoResponse) {
        User member = User.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(oAuthInfoResponse.getNickname())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return userMapper.save(member);
    }

    public List<User> findUsers() {
        return userMapper.findAll();
    }
}
