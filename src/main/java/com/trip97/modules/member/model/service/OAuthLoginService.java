package com.trip97.modules.member.model.service;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.trip97.modules.friendship.model.mapper.FriendshipMapper;
import com.trip97.modules.member.model.Member;
import com.trip97.modules.member.model.jwt.AuthTokens;
import com.trip97.modules.member.model.jwt.AuthTokensGenerator;
import com.trip97.modules.member.model.mapper.MemberMapper;
import com.trip97.modules.member.model.oauth.OAuthInfoResponse;
import com.trip97.modules.member.model.oauth.OAuthLoginParams;
import com.trip97.modules.member.model.oauth.RequestOAuthInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthLoginService {

    private final MemberMapper memberMapper;
    private final FriendshipMapper friendshipMapper;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Integer memberId = findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId);
    }

    private Integer findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return memberMapper.selectMemberByEmail(oAuthInfoResponse.getEmail())
                .map(Member::getId)
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }

    private int newMember(OAuthInfoResponse oAuthInfoResponse) {
    	String randomFriendCode = makeFriendCode();
    	
    	Member member = Member.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(oAuthInfoResponse.getNickname())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .friendCode(randomFriendCode)
                .build();
    	
        return memberMapper.insertMember(member);
    }

    private String makeFriendCode() {
    	while (true) {
    		String randomCode = RandomStringUtils.random(10, true, true);
    		if (friendshipMapper.findCode(randomCode) == null) {
    			log.debug("result: {}", friendshipMapper.findCode(randomCode));
    			return randomCode;
    		}
    	}
    }
    
}
